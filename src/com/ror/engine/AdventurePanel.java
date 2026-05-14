package com.ror.engine;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

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
    public void doLayout() {
        super.doLayout();
        int targetWidth = optionButtonCount > 4 ? 820 : 680;
        int targetHeight = optionRowCount > 1 ? 330 : 260;
        dialogBox.setSize(Math.min(getWidth() - 120, targetWidth), Math.min(getHeight() - 120, targetHeight));
        dialogBox.setLocation((getWidth() - dialogBox.getWidth()) / 2, (getHeight() - dialogBox.getHeight()) / 2);

        for (Component child : dialogBox.getComponents()) {
            if (child instanceof JPanel && "buttonRow".equals(child.getName())) {
                child.setLocation((dialogBox.getWidth() - child.getWidth()) / 2,
                        dialogBox.getHeight() - child.getHeight() - 10);
            }
        }
    }

    public int showMessage(String title, String message) {
        return showDialog(title, message, new String[] { "OK" }, 0);
    }

    public int showConfirm(String title, String message) {
        return showDialog(title, message, new String[] { "Yes", "No" }, 0);
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
        return showDialog(title, message, labels, defaultIndex);
    }

    private int showDialog(String title, String message, String[] buttons, int defaultButtonIndex) {
        if (!SwingUtilities.isEventDispatchThread()) {
            final int[] result = new int[1];
            try {
                SwingUtilities.invokeAndWait(() -> result[0] = showDialog(title, message, buttons, defaultButtonIndex));
            } catch (Exception ignored) {
            }
            return result[0];
        }

        titleLabel.setText(title);
        messageLabel.setText(formatMessage(message));
        defaultOptionIndex = Math.max(0, Math.min(defaultButtonIndex, buttons.length - 1));
        selectedIndex = -1;
        populateButtons(buttons);

        setVisible(true);
        window.getGlassPane().setVisible(true);
        window.getGlassPane().requestFocusInWindow();
        requestFocusInWindow();

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

    private void chooseDefaultOption() {
        if (selectedIndex < 0) {
            closeOverlay(defaultOptionIndex);
        }
    }

    private void closeOverlay(int choice) {
        selectedIndex = choice;
        setVisible(false);
        window.getGlassPane().setVisible(false);
        if (secondaryLoop != null) {
            secondaryLoop.exit();
            secondaryLoop = null;
        }
    }

    private String formatMessage(String message) {
        if (message == null) {
            return "";
        }
        String html = message.replace("\n", "<br>");
        return "<html><div style='text-align:center;margin:0px;padding:0px;'>" + html + "</div></html>";
    }
}
