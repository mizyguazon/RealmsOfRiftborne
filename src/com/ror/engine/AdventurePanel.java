package com.ror.engine;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import javax.swing.*;

public class AdventurePanel extends JComponent {
    private static final Color OVERLAY_DIM = new Color(12, 10, 18, 165);
    private static final Color DIALOG_BG = new Color(30, 28, 40, 246);
    private static final Color DIALOG_BORDER = new Color(93, 87, 111);
    private static final Color DIALOG_TEXT = new Color(246, 239, 221);
    private static final Color BUTTON_BG = new Color(43, 39, 58);
    private static final Color BUTTON_BG_HOVER = new Color(58, 53, 78);
    private static final Color BUTTON_BG_PRESSED = new Color(24, 22, 34);
    private static final Color BUTTON_BORDER = new Color(120, 255, 225, 110);
    private static final Color BUTTON_TEXT = new Color(246, 239, 221);
    private static final int MESSAGE_TYPEWRITER_DELAY_MS = 18;
    private static final int OVERLAY_FADE_DELAY_MS = 16;
    private static final int OVERLAY_FADE_STEPS = 12;

    private final JFrame window;
    private final Font headingFont;
    private final Font bodyFont;
    private final JPanel dialogBox;
    private final JLabel titleLabel;
    private final JLabel messageLabel;
    private final JPanel buttonPanel;
    private volatile int selectedIndex = -1;
    private java.awt.SecondaryLoop secondaryLoop;
    private int defaultOptionIndex = 0;
    private int optionButtonCount = 1;
    private int optionRowCount = 1;
    private float renderAlpha = 1f;
    private boolean slotChooserMode;
    private boolean closeWithFade = true;
    private boolean fadeAnimating;
    private String fullMessage = "";
    private Timer messageTypewriterTimer;
    private Timer overlayFadeTimer;

    public AdventurePanel(JFrame window, Font headingFont, Font bodyFont) {
        this.window = window;
        this.headingFont = headingFont != null ? headingFont : new Font("Serif", Font.BOLD, 28);
        this.bodyFont = bodyFont != null ? bodyFont : new Font("SansSerif", Font.PLAIN, 16);
        setOpaque(false);
        setLayout(null);

        dialogBox = new JPanel(new BorderLayout(16, 16));
        dialogBox.setOpaque(true);
        dialogBox.setBackground(DIALOG_BG);
        dialogBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(DIALOG_BORDER, 2),
                BorderFactory.createEmptyBorder(24, 24, 24, 24)));

        titleLabel = new JLabel("", SwingConstants.CENTER);
        titleLabel.setForeground(DIALOG_TEXT);
        titleLabel.setFont(headingFont.deriveFont(Font.BOLD, headingFont.getSize2D()));

        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(DIALOG_TEXT);
        messageLabel.setFont(bodyFont.deriveFont(Font.PLAIN, bodyFont.getSize2D()));
        messageLabel.setVerticalAlignment(SwingConstants.CENTER);
        messageLabel.setOpaque(false);

        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BorderLayout());

        dialogBox.add(titleLabel, BorderLayout.NORTH);
        dialogBox.add(messageLabel, BorderLayout.CENTER);
        dialogBox.add(buttonPanel, BorderLayout.SOUTH);

        add(dialogBox);

        addMouseListener(new MouseAdapter() {
        });
        addMouseMotionListener(new MouseMotionAdapter() {
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!isVisible()) {
                    return;
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    chooseDefaultOption();
                    return;
                }
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    closeOverlay(JOptionPane.CLOSED_OPTION);
                }
            }
        });
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2 = (Graphics2D) graphics.create();
        g2.setColor(OVERLAY_DIM);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics.create();
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, renderAlpha));
        super.paint(graphics2D);
        graphics2D.dispose();
    }

    @Override
    public void doLayout() {
        super.doLayout();
        int dialogWidth;
        int dialogHeight;

        if (slotChooserMode) {
            dialogWidth = 760;
            dialogHeight = 360;
        } else {
            dialogWidth = optionButtonCount > 4 ? 820 : 680;
            dialogHeight = optionRowCount > 1 ? 330 : 260;
        }

        dialogBox.setSize(
                Math.min(getWidth() - 80, dialogWidth),
                Math.min(getHeight() - 80, dialogHeight)
        );

        dialogBox.setLocation((getWidth() - dialogBox.getWidth()) / 2, (getHeight() - dialogBox.getHeight()) / 2);

        for (Component child : dialogBox.getComponents()) {
            if (child instanceof JPanel && "buttonRow".equals(child.getName())) {
                child.setLocation((dialogBox.getWidth() - child.getWidth()) / 2,
                        dialogBox.getHeight() - child.getHeight() - 10);
            }
        }
    }

    public int showMessage(String title, String message) {
        return showDialog(title, message, new String[] { "OK" }, 0, true, true);
    }

    public int showMessageSequence(String title, String message, boolean fadeIn, boolean fadeOut) {
        return showDialog(title, message, new String[] { "OK" }, 0, fadeIn, fadeOut);
    }

    public int showConfirm(String title, String message) {
        return showDialog(title, message, new String[] { "Yes", "No" }, 0, true, true);
    }

    public int showOptions(String title, String message, Object[] options, Object initial) {
        String[] labels = new String[options.length];
        for (int i = 0; i < options.length; i++) {
            labels[i] = String.valueOf(options[i]);
        }
        int defaultIndex = 0;
        if (initial != null) {
            for (int i = 0; i < options.length; i++) {
                if (options[i].equals(initial)) {
                    defaultIndex = i;
                    break;
                }
            }
        }
        return showDialog(title, message, labels, defaultIndex, true, true);
    }

    public int showSlotChooser(String title, String prompt, Load.SlotInfo[] slots, boolean allowEmptySlots) {
        if (!SwingUtilities.isEventDispatchThread()) {
            final int[] result = new int[] { JOptionPane.CLOSED_OPTION };
            try {
                SwingUtilities.invokeAndWait(
                        () -> result[0] = showSlotChooser(title, prompt, slots, allowEmptySlots));
            } catch (InterruptedException | InvocationTargetException ignored) {
            }
            return result[0];
        }

        slotChooserMode = true;
        titleLabel.setText(title);
        fullMessage = prompt == null ? "" : prompt;
        messageLabel.setText(formatMessage(fullMessage));
        defaultOptionIndex = 0;
        selectedIndex = -1;
        closeWithFade = true;
        populateSlotCards(slots, allowEmptySlots);

        setVisible(true);
        window.getGlassPane().setVisible(true);
        window.getGlassPane().requestFocusInWindow();
        requestFocusInWindow();
        setRenderAlpha(0f);
        startOverlayFadeIn(null);

        secondaryLoop = Toolkit.getDefaultToolkit().getSystemEventQueue().createSecondaryLoop();
        if (secondaryLoop != null) {
            secondaryLoop.enter();
        }

        slotChooserMode = false;
        return selectedIndex;
    }

    private int showDialog(String title, String message, String[] buttons, int defaultButtonIndex, boolean fadeIn,
            boolean fadeOut) {
        if (!SwingUtilities.isEventDispatchThread()) {
            final int[] result = new int[1];
            try {
                SwingUtilities.invokeAndWait(
                        () -> result[0] = showDialog(title, message, buttons, defaultButtonIndex, fadeIn, fadeOut));
            } catch (InterruptedException | InvocationTargetException ignored) {
            }
            return result[0];
        }

        slotChooserMode = false;
        titleLabel.setText(title);
        fullMessage = message == null ? "" : message;
        messageLabel.setText(formatMessage(""));
        defaultOptionIndex = Math.max(0, Math.min(defaultButtonIndex, buttons.length - 1));
        selectedIndex = -1;
        closeWithFade = fadeOut;
        populateButtons(buttons);

        setVisible(true);
        window.getGlassPane().setVisible(true);
        window.getGlassPane().requestFocusInWindow();
        requestFocusInWindow();
        if (fadeIn) {
            setRenderAlpha(0f);
            startOverlayFadeIn(() -> startMessageTypewriter(fullMessage));
        } else {
            setRenderAlpha(1f);
            startMessageTypewriter(fullMessage);
        }

        secondaryLoop = Toolkit.getDefaultToolkit().getSystemEventQueue().createSecondaryLoop();
        if (secondaryLoop != null) {
            secondaryLoop.enter();
        }

        return selectedIndex;
    }

    private void populateButtons(String[] buttons) {
        buttonPanel.removeAll();
        optionButtonCount = Math.max(1, buttons.length);
        int columns = Math.min(4, optionButtonCount);
        optionRowCount = (int) Math.ceil(optionButtonCount / (double) columns);

        JPanel rows = new JPanel();
        rows.setOpaque(false);
        rows.setLayout(new BoxLayout(rows, BoxLayout.Y_AXIS));
        JPanel currentRow = null;

        for (int i = 0; i < buttons.length; i++) {
            if (i % columns == 0) {
                if (i > 0) {
                    rows.add(Box.createVerticalStrut(10));
                }
                currentRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
                currentRow.setOpaque(false);
                currentRow.setAlignmentX(Component.CENTER_ALIGNMENT);
                rows.add(currentRow);
            }
            final int index = i;
            JButton button = new JButton(buttons[i]) {
                @Override
                protected void paintComponent(Graphics graphics) {
                    Graphics2D graphics2D = (Graphics2D) graphics.create();
                    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    ButtonModel model = getModel();
                    Color fill = getBackground();
                    if (model.isPressed()) {
                        fill = BUTTON_BG_PRESSED;
                    } else if (model.isRollover()) {
                        fill = BUTTON_BG_HOVER;
                    }
                    graphics2D.setColor(fill);
                    graphics2D.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
                    graphics2D.dispose();
                    super.paintComponent(graphics);
                }

                @Override
                protected void paintBorder(Graphics graphics) {
                    Graphics2D graphics2D = (Graphics2D) graphics.create();
                    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    graphics2D.setColor(BUTTON_BORDER);
                    graphics2D.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
                    graphics2D.dispose();
                }
            };
            button.setFocusable(false);
            button.setForeground(BUTTON_TEXT);
            button.setBackground(BUTTON_BG);
            button.setRolloverEnabled(true);
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
            button.setFocusPainted(false);
            button.setFont(bodyFont.deriveFont(Font.BOLD, Math.max(14f, bodyFont.getSize2D())));
            button.setPreferredSize(new Dimension(150, 44));
            button.addActionListener(e -> closeOverlay(index));
            currentRow.add(button);
        }

        buttonPanel.add(rows, BorderLayout.CENTER);
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private void populateSlotCards(Load.SlotInfo[] slots, boolean allowEmptySlots) {
        buttonPanel.removeAll();

        JPanel wrapper = new JPanel(new BorderLayout(0, 14));
        wrapper.setOpaque(false);

        JPanel cards = new JPanel(new GridLayout(1, Math.max(1, slots.length), 12, 0));
        cards.setOpaque(false);
        cards.setName("buttonRow");

        for (int i = 0; i < slots.length; i++) {
            Load.SlotInfo slot = slots[i];
            final int index = i;
            JButton button = createSlotButton(slot);
            boolean selectable = slot.isReadable() && (allowEmptySlots || slot.isOccupied());
            button.setEnabled(selectable);
            button.addActionListener(event -> closeOverlay(index));
            cards.add(button);
        }

        JButton cancelButton = createOverlayButton("Cancel", 160, 38);
        cancelButton.addActionListener(event -> closeOverlay(JOptionPane.CLOSED_OPTION));

        JPanel cancelRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        cancelRow.setOpaque(false);
        cancelRow.add(cancelButton);

        wrapper.add(cards, BorderLayout.CENTER);
        wrapper.add(cancelRow, BorderLayout.SOUTH);
        buttonPanel.add(wrapper, BorderLayout.CENTER);
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private JButton createSlotButton(Load.SlotInfo slot) {
        JButton button = createOverlayButton(buildSlotCardText(slot), 205, 118);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setVerticalAlignment(SwingConstants.TOP);
        button.setFont(bodyFont.deriveFont(Font.BOLD, 13f));
        button.setMargin(new Insets(8, 12, 8, 12));
        return button;
    }

    private JButton createOverlayButton(String text, int width, int height) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics graphics) {
                Graphics2D graphics2D = (Graphics2D) graphics.create();
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                ButtonModel model = getModel();
                Color fill = getBackground();
                if (!isEnabled()) {
                    fill = new Color(31, 29, 42);
                } else if (model.isPressed()) {
                    fill = BUTTON_BG_PRESSED;
                } else if (model.isRollover()) {
                    fill = BUTTON_BG_HOVER;
                }
                graphics2D.setColor(fill);
                graphics2D.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
                graphics2D.dispose();
                super.paintComponent(graphics);
            }

            @Override
            protected void paintBorder(Graphics graphics) {
                Graphics2D graphics2D = (Graphics2D) graphics.create();
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics2D.setColor(isEnabled() ? BUTTON_BORDER : new Color(90, 86, 105, 90));
                graphics2D.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
                graphics2D.dispose();
            }
        };
        button.setFocusable(false);
        button.setForeground(BUTTON_TEXT);
        button.setBackground(BUTTON_BG);
        button.setRolloverEnabled(true);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(width, height));
        return button;
    }

    private String buildSlotCardText(Load.SlotInfo slot) {
        if (!slot.isOccupied()) {
            return "<html><b>Slot " + slot.getSlot() + "</b><br><br>Empty<br><br>Ready for a new save</html>";
        }
        if (!slot.isReadable()) {
            return "<html><b>Slot " + slot.getSlot() + "</b><br><br>Unreadable save<br><br>Cannot load this slot</html>";
        }

        String heroName = escapeHtml(slot.getHeroName() == null ? "Unknown Hero" : slot.getHeroName());
        String heroClass = escapeHtml(slot.getHeroClass() == null ? "Unknown Class" : slot.getHeroClass());
        String savedAt = escapeHtml(slot.getSavedAt() == null ? "Unknown time" : slot.getSavedAt());
        return "<html><b>Slot " + slot.getSlot() + "</b><br>"
                + heroName + "<br>"
                + heroClass + " | Lv. " + slot.getLevel() + "<br><br>"
                + "Saved:<br>" + savedAt + "</html>";
    }

    private void chooseDefaultOption() {
        if (fadeAnimating) {
            return;
        }

        if (messageTypewriterTimer != null && messageTypewriterTimer.isRunning()) {
            completeMessageTypewriter();
            return;
        }

        if (selectedIndex < 0) {
            closeOverlay(defaultOptionIndex);
        }
    }

    private void closeOverlay(int choice) {
        if (fadeAnimating) {
            return;
        }

        if (messageTypewriterTimer != null) {
            messageTypewriterTimer.stop();
            messageTypewriterTimer = null;
        }

        selectedIndex = choice;
        if (closeWithFade) {
            startOverlayFadeOut(() -> {
                setVisible(false);
                window.getGlassPane().setVisible(false);
                if (secondaryLoop != null) {
                    secondaryLoop.exit();
                    secondaryLoop = null;
                }
            });
        } else {
            if (secondaryLoop != null) {
                secondaryLoop.exit();
                secondaryLoop = null;
            }
        }
    }

    private void startMessageTypewriter(String message) {
        if (messageTypewriterTimer != null) {
            messageTypewriterTimer.stop();
        }

        fullMessage = message == null ? "" : message;
        messageLabel.setText(formatMessage(""));

        if (fullMessage.isEmpty()) {
            return;
        }

        final int[] index = { 0 };
        messageTypewriterTimer = new Timer(MESSAGE_TYPEWRITER_DELAY_MS, event -> {
            if (index[0] >= fullMessage.length()) {
                ((Timer) event.getSource()).stop();
                return;
            }

            index[0]++;
            messageLabel.setText(formatMessage(fullMessage.substring(0, index[0])));
        });
        messageTypewriterTimer.start();
    }

    private void completeMessageTypewriter() {
        if (messageTypewriterTimer != null) {
            messageTypewriterTimer.stop();
        }
        messageLabel.setText(formatMessage(fullMessage));
    }

    private void startOverlayFadeIn(Runnable onComplete) {
        if (overlayFadeTimer != null) {
            overlayFadeTimer.stop();
        }

        fadeAnimating = true;
        final int[] step = { 0 };
        overlayFadeTimer = new Timer(OVERLAY_FADE_DELAY_MS, null);
        overlayFadeTimer.addActionListener(event -> {
            step[0]++;
            setRenderAlpha(Math.min(1f, step[0] / (float) OVERLAY_FADE_STEPS));

            if (step[0] < OVERLAY_FADE_STEPS) {
                return;
            }

            ((Timer) event.getSource()).stop();
            fadeAnimating = false;
            if (onComplete != null) {
                onComplete.run();
            }
        });
        overlayFadeTimer.start();
    }

    private void startOverlayFadeOut(Runnable onComplete) {
        if (overlayFadeTimer != null) {
            overlayFadeTimer.stop();
        }

        fadeAnimating = true;
        final int[] step = { OVERLAY_FADE_STEPS };
        overlayFadeTimer = new Timer(OVERLAY_FADE_DELAY_MS, null);
        overlayFadeTimer.addActionListener(event -> {
            step[0]--;
            setRenderAlpha(Math.max(0f, step[0] / (float) OVERLAY_FADE_STEPS));

            if (step[0] > 0) {
                return;
            }

            ((Timer) event.getSource()).stop();
            fadeAnimating = false;
            if (onComplete != null) {
                onComplete.run();
            }
        });
        overlayFadeTimer.start();
    }

    private void setRenderAlpha(float alpha) {
        renderAlpha = Math.max(0f, Math.min(1f, alpha));
        repaint();
    }

    private String formatMessage(String message) {
        if (message == null) {
            return "";
        }
        String html = escapeHtml(message).replace("\n", "<br>");
        return "<html><div style='text-align:center;margin:0px;padding:0px;'>" + html + "</div></html>";
    }

    private String escapeHtml(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}
