package com.ror.engine;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

public class AdventurePanel extends JComponent {
    private static final Color OVERLAY_DIM = new Color(60, 40, 30, 145);
    private static final Color DIALOG_BG = new Color(90, 73, 58, 245);
    private static final Color DIALOG_BORDER = new Color(130, 84, 70);
    private static final Color DIALOG_TEXT = new Color(246, 240, 232);
    private static final Color BUTTON_BG = new Color(118, 81, 53);
    private static final Color BUTTON_BG_HOVER = new Color(132, 92, 62);
    private static final Color BUTTON_BG_PRESSED = new Color(96, 65, 42);
    private static final Color BUTTON_BORDER = new Color(130, 84, 70);
    private static final Color BUTTON_TEXT = new Color(246, 240, 232);

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
    private Timer messageTypewriterTimer;
    private Timer overlayFadeTimer;
    private String fullMessage = "";
    private static final int MESSAGE_TYPEWRITER_DELAY_MS = 12;
    private static final int OVERLAY_FADE_STEPS = 10;
    private static final int OVERLAY_FADE_DELAY_MS = 16;
    private float renderAlpha = 1f;
    private boolean fadeAnimating = false;
    private boolean closeWithFade = true;

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
        dialogBox.setSize(Math.min(getWidth() - 120, 680), Math.min(getHeight() - 120, 260));
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

    private int showDialog(String title, String message, String[] buttons, int defaultButtonIndex, boolean fadeIn,
            boolean fadeOut) {
        if (!SwingUtilities.isEventDispatchThread()) {
            final int[] result = new int[1];
            try {
                SwingUtilities.invokeAndWait(
                        () -> result[0] = showDialog(title, message, buttons, defaultButtonIndex, fadeIn, fadeOut));
            } catch (Exception ignored) {
            }
            return result[0];
        }

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
        JPanel row = new JPanel();
        row.setOpaque(false);
        row.setName("buttonRow");
        row.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 16, 0));

        for (int i = 0; i < buttons.length; i++) {
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
            button.setPreferredSize(new Dimension(120, 44));
            button.addActionListener(e -> closeOverlay(index));
            row.add(button);
        }

        buttonPanel.add(row, BorderLayout.CENTER);
        buttonPanel.revalidate();
        buttonPanel.repaint();
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
        String html = message.replace("\n", "<br>");
        return "<html><div style='text-align:center;margin:0px;padding:0px;'>" + html + "</div></html>";
    }
}
