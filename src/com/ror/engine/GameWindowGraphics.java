package com.ror.engine;

import com.ror.models.Entity;
import com.ror.models.Mobs.Goblin;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Deque;

final class GameWindowGraphics {
    private static final String UI_IMAGE_DIRECTORY = "assets/images/ui/";
    private static final String ENEMY_IMAGE_DIRECTORY = "assets/images/enemies/";
    private static final String HERO_IMAGE_DIRECTORY = "assets/images/heroes/";
    private static final int LANDING_SECONDARY_BUTTON_WIDTH = 170;
    private static final int LANDING_SECONDARY_BUTTON_HEIGHT = 78;
    private static final Color BUTTON_PRIMARY = new Color(0x0B1033);
    private static final Color BUTTON_HOVER = new Color(0x1A2159);
    private static final Color BUTTON_PRESSED = new Color(0x06081F);
    private static final Color BUTTON_BORDER = new Color(0x2B356E);

    private Font headingFont = new Font("Serif", Font.BOLD, 28);
    private Font bodyFont = new Font("SansSerif", Font.PLAIN, 14);
    private float headingFontSize = 28f;
    private float bodyFontSize = 14f;

    private final BufferedImage[] landingBackgroundFrames;
    private int landingBackgroundIndex = 0;
    private final ButtonSkin primaryButtonSkin;
    private final ButtonSkin secondaryButtonSkin;
    private final BufferedImage landingPlayButtonImage;
    private final BufferedImage landingAboutButtonImage;
    private final BufferedImage landingLoadButtonImage;
    private final BufferedImage landingOptionsButtonImage;
    private final BufferedImage landingExitButtonImage;

    GameWindowGraphics() {
        loadFonts();
        landingBackgroundFrames = loadLandingBackgroundFrames();
        primaryButtonSkin = loadButtonSkin("button-primary");
        secondaryButtonSkin = loadButtonSkin("button-secondary");
        landingPlayButtonImage = loadLandingButtonImage("play-button.png");
        landingAboutButtonImage = loadLandingButtonImage("about-button.png");
        landingLoadButtonImage = loadLandingButtonImage("load-button.png");
        landingOptionsButtonImage = loadLandingButtonImage("options-button.png");
        landingExitButtonImage = loadLandingButtonImage("exit-button.png");
    }

    JPanel createCardPanel(Color borderColor, Color panelColor) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor, 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        panel.setBackground(panelColor);
        panel.setOpaque(true);
        return panel;
    }

    Font getHeadingFont(float size) {
        if (headingFont != null) return headingFont.deriveFont(Font.BOLD, size);
        return new Font("Serif", Font.BOLD, Math.round(size));
    }

    Font getBodyFont(float size) {
        if (bodyFont != null) return bodyFont.deriveFont(Font.PLAIN, size);
        return new Font("SansSerif", Font.PLAIN, Math.round(size));
    }

    String styleHtml(String htmlBody) {
        String family = bodyFont != null ? bodyFont.getFamily() : "SansSerif";
        int size = Math.round(bodyFontSize);
        String cleaned = htmlBody
                .replaceAll("^<html>", "")
                .replaceAll("</html>$", "");
        return "<html><div style='font-family: " + family + "; font-size: " + size + "pt;'>" + cleaned + "</div></html>";
    }

    String styleRaw(String text) {
        String family = bodyFont != null ? bodyFont.getFamily() : "SansSerif";
        int size = Math.round(bodyFontSize);
        return "<html><div style='font-family: " + family + "; font-size: " + size + "pt;'>" + text + "</div></html>";
    }

    JLabel createHeading(String text, Color textColor) {
        JLabel label = new JLabel(text);
        label.putClientProperty("fontRole", "heading");
        label.setFont(getHeadingFont(headingFontSize));
        label.setForeground(textColor);
        return label;
    }

    JLabel createBody(String text, Color textColor) {
        JLabel label = new JLabel(styleRaw(text));
        label.putClientProperty("fontRole", "body");
        label.setFont(getBodyFont(bodyFontSize));
        label.setForeground(textColor);
        return label;
    }

    JButton createPrimaryButton(String text) {
        return createStyledButton(text, primaryButtonSkin, BUTTON_PRIMARY,
                Color.WHITE, getBodyFont(14f).deriveFont(Font.BOLD, 14f), 42);
    }

    JButton createSecondaryButton(String text) {
        return createStyledButton(text, secondaryButtonSkin, BUTTON_PRIMARY,
                Color.WHITE, getBodyFont(13f).deriveFont(Font.BOLD, 13f), 40);
    }

    JButton createBattleButton(String text, Color fallbackColor) {
        return createStyledButton(text, null, fallbackColor,
                Color.WHITE, getBodyFont(15f).deriveFont(Font.BOLD, 15f), 58);
    }

    JButton createCharacterSelectButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics graphics) {
                Graphics2D g2 = (Graphics2D) graphics.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                ButtonModel model = getModel();
                Color fill = BUTTON_PRIMARY;
                if (model.isPressed()) fill = BUTTON_PRESSED;
                else if (model.isRollover()) fill = BUTTON_HOVER;
                g2.setColor(fill);
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 14, 14);
                g2.dispose();
                super.paintComponent(graphics);
            }

            @Override
            protected void paintBorder(Graphics graphics) {
                Graphics2D g2 = (Graphics2D) graphics.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(BUTTON_BORDER);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 14, 14);
                g2.dispose();
            }
        };
        button.setFont(getBodyFont(14f).deriveFont(Font.BOLD, 14f));
        button.setForeground(Color.WHITE);
        button.setBackground(BUTTON_PRIMARY);
        button.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        button.setRolloverEnabled(true);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        return button;
    }

    JPanel buildLandingScreen(JFrame frame, Runnable onPlay, Runnable onExit) {
        final int playButtonWidth = 448;
        final int playButtonHeight = 133;
        final int secondaryButtonWidth = 225;
        final int secondaryButtonHeight = 110;
        final int secondaryButtonGap = 18;
        final int buttonBlockWidth = (secondaryButtonWidth * 2) + secondaryButtonGap;

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                Graphics2D g2 = (Graphics2D) graphics.create();
                g2.setColor(new Color(18, 12, 10));
                g2.fillRect(0, 0, getWidth(), getHeight());

                BufferedImage landingBackground = getCurrentLandingBackground();
                if (landingBackground != null) {
                    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
                    double scale = Math.max(
                            getWidth() / (double) landingBackground.getWidth(),
                            getHeight() / (double) landingBackground.getHeight());
                    int drawWidth = (int) Math.ceil(landingBackground.getWidth() * scale);
                    int drawHeight = (int) Math.ceil(landingBackground.getHeight() * scale);
                    int drawX = (getWidth() - drawWidth) / 2;
                    int drawY = (getHeight() - drawHeight) / 2;
                    g2.drawImage(landingBackground, drawX, drawY, drawWidth, drawHeight, null);
                    g2.setColor(new Color(6, 8, 18, 70));
                    g2.fillRect(0, 0, getWidth(), getHeight());
                }
                g2.dispose();
            }
        };

        panel.setLayout(new GridBagLayout());
        if (landingBackgroundFrames.length > 1) {
            Timer frameTimer = new Timer(280, event -> {
                landingBackgroundIndex = (landingBackgroundIndex + 1) % landingBackgroundFrames.length;
                panel.repaint();
            });
            frameTimer.start();
        }

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));
        content.setMaximumSize(new Dimension(buttonBlockWidth, Integer.MAX_VALUE));

        JPanel topMenuRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        topMenuRow.setOpaque(false);
        topMenuRow.setMaximumSize(new Dimension(buttonBlockWidth, playButtonHeight));
        topMenuRow.setPreferredSize(new Dimension(buttonBlockWidth, playButtonHeight));
        topMenuRow.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton playButton = landingPlayButtonImage != null
                ? createLandingImageButton(landingPlayButtonImage, playButtonWidth, playButtonHeight, "PLAY")
                : createLandingPlaceholderButton("PLAY", 220, 48);
        playButton.addActionListener(event -> onPlay.run());
        topMenuRow.add(playButton);

        JPanel middleMenuRow = new JPanel(new GridLayout(1, 2, 1, 0));
        middleMenuRow.setOpaque(false);
        middleMenuRow.setMaximumSize(new Dimension(buttonBlockWidth, secondaryButtonHeight));
        middleMenuRow.setPreferredSize(new Dimension(buttonBlockWidth, secondaryButtonHeight));
        middleMenuRow.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton aboutButton = landingAboutButtonImage != null
                ? createLandingImageButton(landingAboutButtonImage,
                        secondaryButtonWidth, secondaryButtonHeight, "ABOUT")
                : createLandingPlaceholderButton("ABOUT");
        aboutButton.addActionListener(event -> JOptionPane.showMessageDialog(
                frame,
                "Mystvale Academy RPG\nA story-driven fantasy adventure.",
                "About",
                JOptionPane.INFORMATION_MESSAGE));
        JButton loadButton = landingLoadButtonImage != null
                ? createLandingImageButton(landingLoadButtonImage,
                        secondaryButtonWidth, secondaryButtonHeight, "LOAD")
                : createLandingPlaceholderButton("LOAD");
        loadButton.addActionListener(event -> JOptionPane.showMessageDialog(
                frame,
                "Load is not available yet.",
                "Load",
                JOptionPane.INFORMATION_MESSAGE));
        middleMenuRow.add(aboutButton);
        middleMenuRow.add(loadButton);

        JPanel bottomMenuRow = new JPanel(new GridLayout(1, 2, 1, 0));
        bottomMenuRow.setOpaque(false);
        bottomMenuRow.setMaximumSize(new Dimension(buttonBlockWidth, secondaryButtonHeight));
        bottomMenuRow.setPreferredSize(new Dimension(buttonBlockWidth, secondaryButtonHeight));
        bottomMenuRow.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton optionsButton = landingOptionsButtonImage != null
                ? createLandingImageButton(landingOptionsButtonImage,
                        secondaryButtonWidth, secondaryButtonHeight, "OPTIONS")
                : createLandingPlaceholderButton("OPTIONS");
        optionsButton.addActionListener(event -> JOptionPane.showMessageDialog(
                frame,
                "Options is not available yet.",
                "Options",
                JOptionPane.INFORMATION_MESSAGE));

        JButton exitButton = landingExitButtonImage != null
                ? createLandingImageButton(landingExitButtonImage,
                        secondaryButtonWidth, secondaryButtonHeight, "EXIT")
                : createLandingPlaceholderButton("EXIT");
        exitButton.addActionListener(event -> onExit.run());
        bottomMenuRow.add(optionsButton);
        bottomMenuRow.add(exitButton);

        content.add(topMenuRow);
        content.add(Box.createVerticalStrut(-20));
        content.add(middleMenuRow);
        content.add(Box.createVerticalStrut(-10));
        content.add(bottomMenuRow);
        content.add(Box.createVerticalGlue());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.SOUTHWEST;
        constraints.insets = new Insets(0, 70, 35, 0);
        panel.add(content, constraints);
        return panel;
    }

    BufferedImage loadCharacterPortrait(String title) {
        String normalizedTitle = title == null ? "" : title.trim().toLowerCase().replace(' ', '-');
        String[] candidates = {
                HERO_IMAGE_DIRECTORY + normalizedTitle + ".png",
                HERO_IMAGE_DIRECTORY + normalizedTitle + ".jpg",
                HERO_IMAGE_DIRECTORY + normalizedTitle + ".jpeg",
                "src/com/ror/models/" + HERO_IMAGE_DIRECTORY + normalizedTitle + ".png",
                "src/com/ror/models/" + HERO_IMAGE_DIRECTORY + normalizedTitle + ".jpg",
                "src/com/ror/models/" + HERO_IMAGE_DIRECTORY + normalizedTitle + ".jpeg"
        };

        for (String candidate : candidates) {
            BufferedImage image = loadImageAsset(candidate);
            if (image != null) return image;
        }
        return null;
    }

    Icon createScaledPortraitIcon(BufferedImage portrait, int targetWidth, int targetHeight) {
        BufferedImage scaled = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = scaled.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double scale = Math.max(
                (double) targetWidth / portrait.getWidth(),
                (double) targetHeight / portrait.getHeight());
        int drawWidth = (int) Math.round(portrait.getWidth() * scale);
        int drawHeight = (int) Math.round(portrait.getHeight() * scale);
        int drawX = (targetWidth - drawWidth) / 2;
        int drawY = (targetHeight - drawHeight) / 2;

        g2.drawImage(portrait, drawX, drawY, drawWidth, drawHeight, null);
        g2.dispose();
        return new ImageIcon(scaled);
    }

    void setGameFonts(Font heading, Font body, Container rootPanel) {
        if (heading != null) {
            headingFont = heading;
            headingFontSize = heading.getSize2D();
        }
        if (body != null) {
            bodyFont = body;
            bodyFontSize = body.getSize2D();
        }
        applyFontsToAllComponents(rootPanel);
    }

    void setGameFontSizes(float headingSize, float bodySize, Container rootPanel) {
        headingFontSize = headingSize;
        bodyFontSize = bodySize;
        if (headingFont != null) headingFont = headingFont.deriveFont(Font.BOLD, headingSize);
        if (bodyFont != null) bodyFont = bodyFont.deriveFont(Font.PLAIN, bodySize);
        applyGlobalUIFont();
        applyFontsToAllComponents(rootPanel);
    }

    void updateEnemySprite(Entity enemy, BattlePanel battlePanel) {
        BufferedImage sprite = loadEnemySprite(enemy);
        if (sprite == null) {
            BufferedImage placeholder = loadImageAsset(ENEMY_IMAGE_DIRECTORY + "placeholder.png");
            if (placeholder != null) {
                battlePanel.getBattleEnemySpriteLabel().setText("");
                battlePanel.getBattleEnemySpriteLabel().setIcon(createScaledSpriteIcon(placeholder, 96, 96));
            } else {
                battlePanel.getBattleEnemySpriteLabel().setIcon(null);
                battlePanel.getBattleEnemySpriteLabel().setText("");
            }
            return;
        }
        battlePanel.getBattleEnemySpriteLabel().setText("");
        battlePanel.getBattleEnemySpriteLabel().setIcon(createScaledSpriteIcon(sprite, 96, 96));
    }

    private JButton createStyledButton(String text, ButtonSkin skin, Color fallbackColor,
            Color textColor, Font font, int height) {
        PixelButton button = new PixelButton(text, skin, fallbackColor);
        button.setForeground(textColor);
        button.setFont(font);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        button.setMinimumSize(new Dimension(160, height));
        button.setPreferredSize(new Dimension(220, height));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        return button;
    }

    private JButton createLandingPlaceholderButton(String text) {
        return createLandingPlaceholderButton(text, 160, 48);
    }

    private JButton createLandingPlaceholderButton(String text, int width, int height) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics graphics) {
                Graphics2D g2 = (Graphics2D) graphics.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color fill = new Color(242, 247, 250, 220);
                Color border = new Color(31, 157, 231);
                Color textColor = new Color(24, 156, 229);

                if (getModel().isPressed()) {
                    fill = new Color(220, 237, 246, 235);
                } else if (getModel().isRollover()) {
                    fill = new Color(248, 251, 253, 235);
                }

                g2.setColor(fill);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(border);
                g2.setStroke(new BasicStroke(3f));
                g2.drawRect(1, 1, getWidth() - 3, getHeight() - 3);

                Font font = getHeadingFont(14f);
                g2.setFont(font);
                FontMetrics metrics = g2.getFontMetrics(font);
                int textX = (getWidth() - metrics.stringWidth(getText())) / 2;
                int textY = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                g2.setColor(textColor);
                g2.drawString(getText(), textX, textY);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics graphics) {
            }
        };

        button.setPreferredSize(new Dimension(width, height));
        button.setMinimumSize(new Dimension(width, height));
        button.setMaximumSize(new Dimension(width, height));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setRolloverEnabled(true);
        return button;
    }

    private JButton createLandingImageButton(BufferedImage image, int width, String fallbackText) {
        int safeWidth = Math.max(1, width);
        int safeHeight = Math.max(1, (int) Math.round(safeWidth * (image.getHeight() / (double) image.getWidth())));
        return createLandingImageButton(image, safeWidth, safeHeight, fallbackText);
    }

    private JButton createLandingImageButton(BufferedImage image, int width, int height, String fallbackText) {
        int safeWidth = Math.max(1, width);
        int safeHeight = Math.max(1, height);

        JButton button = new JButton(fallbackText) {
            @Override
            protected void paintComponent(Graphics graphics) {
                Graphics2D g2 = (Graphics2D) graphics.create();
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                int pressOffset = getModel().isPressed() ? 2 : 0;
                int hoverInset = getModel().isRollover() ? 4 : 0;
                int drawX = hoverInset / 2;
                int drawY = pressOffset + (hoverInset / 2);
                int drawWidth = Math.max(1, getWidth() - hoverInset);
                int drawHeight = Math.max(1, getHeight() - hoverInset - pressOffset);

                g2.drawImage(image, drawX, drawY, drawWidth, drawHeight, null);

                if (getModel().isRollover()) {
                    g2.setColor(new Color(196, 255, 234, 34));
                    g2.fillRoundRect(drawX + 8, drawY + 8,
                            Math.max(0, drawWidth - 16), Math.max(0, drawHeight - 16), 18, 18);
                }
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics graphics) {
            }
        };

        button.setPreferredSize(new Dimension(safeWidth, safeHeight));
        button.setMinimumSize(new Dimension(safeWidth, safeHeight));
        button.setMaximumSize(new Dimension(safeWidth, safeHeight));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setRolloverEnabled(true);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private BufferedImage loadImageAsset(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (Exception ignored) {
            return null;
        }
    }

    private BufferedImage[] loadLandingBackgroundFrames() {
        java.util.List<BufferedImage> frames = new java.util.ArrayList<>();

        String[][] frameCandidates = {
                {
                        "src/com/ror/models/assets/images/mainscreen/main-screen-background-1.png",
                },
                {
                        "src/com/ror/models/assets/images/mainscreen/main-screen-background-2.png",
                },
                {
                        "src/com/ror/models/assets/images/mainscreen/main-screen-background-3.png",
                },
                {
                        "src/com/ror/models/assets/images/mainscreen/main-screen-background-4.png",
                },
                {
                        "src/com/ror/models/assets/images/mainscreen/main-screen-background-5.png",
                },
                {
                        "src/com/ror/models/assets/images/mainscreen/main-screen-background-6.png",
                },
                {
                        "src/com/ror/models/assets/images/mainscreen/main-screen-background-7.png",
                },
                {
                        "src/com/ror/models/assets/images/mainscreen/main-screen-background-8.png",
                },
                {
                        "src/com/ror/models/assets/images/mainscreen/main-screen-background-9.png",
                },
                {
                        "src/com/ror/models/assets/images/mainscreen/main-screen-background-10.png",
                },
                {
                        "src/com/ror/models/assets/images/mainscreen/main-screen-background-11.png",
                },
                {
                        "src/com/ror/models/assets/images/mainscreen/main-screen-background-12.png",
                }
        };

        for (String[] candidateGroup : frameCandidates) {
            BufferedImage frame = loadFirstAvailableImage(candidateGroup);
            if (frame != null) {
                frames.add(frame);
            }
        }

        return frames.toArray(new BufferedImage[0]);
    }

    private BufferedImage getCurrentLandingBackground() {
        if (landingBackgroundFrames.length == 0) {
            return null;
        }
        int safeIndex = Math.floorMod(landingBackgroundIndex, landingBackgroundFrames.length);
        return landingBackgroundFrames[safeIndex];
    }

    private BufferedImage loadFirstAvailableImage(String... candidates) {
        for (String candidate : candidates) {
            if (candidate.startsWith("/")) {
                try (InputStream stream = getClass().getResourceAsStream(candidate)) {
                    if (stream != null) {
                        BufferedImage image = ImageIO.read(stream);
                        if (image != null) {
                            return image;
                        }
                    }
                } catch (Exception ignored) {
                }
            } else {
                BufferedImage image = loadImageAsset(candidate);
                if (image != null) {
                    return image;
                }
            }
        }

        return null;
    }

    private BufferedImage loadLandingButtonImage(String fileName) {
        return loadFirstAvailableImage(
                "/com/ror/models/assets/images/ui/" + fileName,
                "src/com/ror/models/assets/images/ui/" + fileName,
                "assets/images/ui/" + fileName);
    }

    private BufferedImage prepareUiButtonImage(String fileName) {
        BufferedImage raw = loadFirstAvailableImage(
                "/com/ror/models/assets/images/ui/" + fileName,
                "src/com/ror/models/assets/images/ui/" + fileName,
                "assets/images/ui/" + fileName);
        if (raw == null) return null;

        BufferedImage cleaned = removeCornerBackground(raw);
        BufferedImage trimmed = cropUiButtonBounds(cleaned);
        return trimmed;
    }

    private BufferedImage prepareUiButtonSprite(String fileName, Rectangle sourceBounds) {
        BufferedImage sheet = loadFirstAvailableImage(
                "/com/ror/models/assets/images/ui/" + fileName,
                "src/com/ror/models/assets/images/ui/" + fileName,
                "assets/images/ui/" + fileName);
        if (sheet == null || sourceBounds == null) return null;

        Rectangle safeBounds = sourceBounds.intersection(new Rectangle(0, 0, sheet.getWidth(), sheet.getHeight()));
        if (safeBounds.width <= 0 || safeBounds.height <= 0) return null;

        BufferedImage cropped = new BufferedImage(safeBounds.width, safeBounds.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = cropped.createGraphics();
        g2.drawImage(sheet, 0, 0, safeBounds.width, safeBounds.height,
                safeBounds.x, safeBounds.y, safeBounds.x + safeBounds.width, safeBounds.y + safeBounds.height, null);
        g2.dispose();

        BufferedImage cleaned = removeCornerBackground(cropped);
        return cropUiButtonBounds(cleaned);
    }

    private BufferedImage firstAvailableButton(BufferedImage primary, BufferedImage fallback) {
        return primary != null ? primary : fallback;
    }

    private BufferedImage normalizeLandingSecondaryButton(BufferedImage source) {
        if (source == null) return null;
        BufferedImage normalized = new BufferedImage(
                LANDING_SECONDARY_BUTTON_WIDTH, LANDING_SECONDARY_BUTTON_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = normalized.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        double scale = Math.min(
                LANDING_SECONDARY_BUTTON_WIDTH / (double) Math.max(1, source.getWidth()),
                LANDING_SECONDARY_BUTTON_HEIGHT / (double) Math.max(1, source.getHeight()));
        int drawWidth = Math.max(1, (int) Math.round(source.getWidth() * scale));
        int drawHeight = Math.max(1, (int) Math.round(source.getHeight() * scale));
        int drawX = (LANDING_SECONDARY_BUTTON_WIDTH - drawWidth) / 2;
        int drawY = (LANDING_SECONDARY_BUTTON_HEIGHT - drawHeight) / 2;

        g2.drawImage(source, drawX, drawY, drawWidth, drawHeight, null);
        g2.dispose();
        return normalized;
    }

    private BufferedImage cropUiButtonBounds(BufferedImage source) {
        int minX = source.getWidth();
        int minY = source.getHeight();
        int maxX = -1;
        int maxY = -1;

        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                int argb = source.getRGB(x, y);
                if (isUiBackgroundColor(argb)) continue;
                minX = Math.min(minX, x);
                minY = Math.min(minY, y);
                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);
            }
        }

        if (maxX < minX || maxY < minY) return source;

        int padding = 6;
        int cropX = Math.max(0, minX - padding);
        int cropY = Math.max(0, minY - padding);
        int cropWidth = Math.min(source.getWidth() - cropX, (maxX - minX + 1) + (padding * 2));
        int cropHeight = Math.min(source.getHeight() - cropY, (maxY - minY + 1) + (padding * 2));

        BufferedImage cropped = new BufferedImage(cropWidth, cropHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = cropped.createGraphics();
        g2.drawImage(source, 0, 0, cropWidth, cropHeight,
                cropX, cropY, cropX + cropWidth, cropY + cropHeight, null);
        g2.dispose();
        return cropped;
    }

    private BufferedImage removeCornerBackground(BufferedImage source) {
        BufferedImage cleaned = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = cleaned.createGraphics();
        g2.drawImage(source, 0, 0, null);
        g2.dispose();

        boolean[][] visited = new boolean[cleaned.getHeight()][cleaned.getWidth()];
        floodClearBackground(cleaned, visited, 0, 0);
        floodClearBackground(cleaned, visited, cleaned.getWidth() - 1, 0);
        floodClearBackground(cleaned, visited, 0, cleaned.getHeight() - 1);
        floodClearBackground(cleaned, visited, cleaned.getWidth() - 1, cleaned.getHeight() - 1);
        return cleaned;
    }

    private void floodClearBackground(BufferedImage image, boolean[][] visited, int startX, int startY) {
        if (startX < 0 || startY < 0 || startX >= image.getWidth() || startY >= image.getHeight()) return;
        if (!isUiBackgroundColor(image.getRGB(startX, startY))) return;

        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(startX, startY));

        while (!queue.isEmpty()) {
            Point point = queue.removeFirst();
            int x = point.x;
            int y = point.y;

            if (x < 0 || y < 0 || x >= image.getWidth() || y >= image.getHeight()) continue;
            if (visited[y][x]) continue;
            visited[y][x] = true;

            if (!isUiBackgroundColor(image.getRGB(x, y))) continue;

            image.setRGB(x, y, 0x00000000);
            queue.addLast(new Point(x + 1, y));
            queue.addLast(new Point(x - 1, y));
            queue.addLast(new Point(x, y + 1));
            queue.addLast(new Point(x, y - 1));
        }
    }

    private boolean isUiBackgroundColor(int argb) {
        int alpha = (argb >>> 24) & 0xFF;
        int red = (argb >>> 16) & 0xFF;
        int green = (argb >>> 8) & 0xFF;
        int blue = argb & 0xFF;

        if (alpha < 12) return true;

        boolean chromaGreen = green >= 150 && red <= 120 && blue <= 120 && (green - red) >= 40;
        boolean screenshotWhite = red >= 245 && green >= 245 && blue >= 245;
        return chromaGreen || screenshotWhite;
    }

    private Icon createScaledSpriteIcon(BufferedImage sprite, int targetWidth, int targetHeight) {
        BufferedImage scaled = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = scaled.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        int drawWidth = sprite.getWidth();
        int drawHeight = sprite.getHeight();
        double scale = Math.min(
                (double) targetWidth / Math.max(1, drawWidth),
                (double) targetHeight / Math.max(1, drawHeight));
        int scaledWidth = Math.max(1, (int) Math.round(drawWidth * scale));
        int scaledHeight = Math.max(1, (int) Math.round(drawHeight * scale));
        int x = (targetWidth - scaledWidth) / 2;
        int y = (targetHeight - scaledHeight) / 2;

        g2.drawImage(sprite, x, y, scaledWidth, scaledHeight, null);
        g2.dispose();
        return new ImageIcon(scaled);
    }

    private void loadFonts() {
        try (InputStream cinzelStream = getClass()
                .getResourceAsStream("/com/ror/models/assets/fonts/Cinzel-VariableFont_wght.ttf");
             InputStream garamondStream = getClass()
                .getResourceAsStream("/com/ror/models/assets/fonts/EBGaramond-VariableFont_wght.ttf")) {

            if (cinzelStream != null) {
                Font loaded = Font.createFont(Font.TRUETYPE_FONT, cinzelStream).deriveFont(Font.BOLD, headingFontSize);
                headingFont = loaded;
                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(loaded);
            }

            if (garamondStream != null) {
                Font loaded = Font.createFont(Font.TRUETYPE_FONT, garamondStream).deriveFont(Font.PLAIN, bodyFontSize);
                bodyFont = loaded;
                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(loaded);
            }

            applyGlobalUIFont();
        } catch (Exception exception) {
            headingFont = new Font("Serif", Font.BOLD, Math.round(headingFontSize));
            bodyFont = new Font("SansSerif", Font.PLAIN, Math.round(bodyFontSize));
            applyGlobalUIFont();
        }
    }

    private void applyGlobalUIFont() {
        FontUIResource uiFont = new FontUIResource(bodyFont.deriveFont(bodyFontSize));
        UIManager.put("Label.font", uiFont);
        UIManager.put("Button.font", uiFont);
        UIManager.put("TextField.font", uiFont);
        UIManager.put("TextArea.font", uiFont);
        UIManager.put("TextPane.font", uiFont);
        UIManager.put("ComboBox.font", uiFont);
        UIManager.put("List.font", uiFont);
        UIManager.put("Table.font", uiFont);
        UIManager.put("ProgressBar.selectionForeground", Color.WHITE);
        UIManager.put("ProgressBar.selectionBackground", Color.WHITE);
    }

    private void applyFontsToAllComponents(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JLabel label) {
                Object role = label.getClientProperty("fontRole");
                label.setFont("heading".equals(role) ? getHeadingFont(headingFontSize) : getBodyFont(bodyFontSize));
                String text = label.getText();
                if (text != null && text.startsWith("<html>")) {
                    label.setText(styleHtml(text.substring(6, text.length() - 7)));
                }
            } else if (comp instanceof JButton button) {
                button.setFont(getBodyFont(button.getFont().getSize2D()));
            } else if (comp instanceof JTextArea textArea) {
                textArea.setFont(getBodyFont(bodyFontSize));
            } else if (comp instanceof JTextField textField) {
                textField.setFont(getBodyFont(bodyFontSize));
            }
            if (comp instanceof Container child) {
                applyFontsToAllComponents(child);
            }
        }
    }

    private BufferedImage loadEnemySprite(Entity enemy) {
        if (enemy == null) return null;
        if (enemy instanceof Goblin) return loadImageAsset(ENEMY_IMAGE_DIRECTORY + "goblin.png");
        return null;
    }

    private ButtonSkin loadButtonSkin(String baseName) {
        BufferedImage normal = loadImageAsset(UI_IMAGE_DIRECTORY + baseName + ".png");
        BufferedImage hover = loadImageAsset(UI_IMAGE_DIRECTORY + baseName + "-hover.png");
        BufferedImage pressed = loadImageAsset(UI_IMAGE_DIRECTORY + baseName + "-pressed.png");
        BufferedImage disabled = loadImageAsset(UI_IMAGE_DIRECTORY + baseName + "-disabled.png");
        return new ButtonSkin(normal, hover, pressed, disabled);
    }

    private static final class ButtonSkin {
        private final BufferedImage normal;
        private final BufferedImage hover;
        private final BufferedImage pressed;
        private final BufferedImage disabled;

        private ButtonSkin(BufferedImage normal, BufferedImage hover, BufferedImage pressed, BufferedImage disabled) {
            this.normal = normal;
            this.hover = hover;
            this.pressed = pressed;
            this.disabled = disabled;
        }

        private BufferedImage resolve(ButtonModel model, boolean rollover) {
            if (!model.isEnabled()) return disabled != null ? disabled : normal;
            if (model.isPressed()) return pressed != null ? pressed : hover != null ? hover : normal;
            if (rollover && hover != null) return hover;
            return normal;
        }

        private boolean hasAnyImage() {
            return normal != null || hover != null || pressed != null || disabled != null;
        }
    }

    private static final class PixelButton extends JButton {
        private final ButtonSkin skin;
        private final Color fallbackColor;
        private boolean rollover;

        private PixelButton(String text, ButtonSkin skin, Color fallbackColor) {
            super(text);
            this.skin = skin;
            this.fallbackColor = fallbackColor;

            setFocusPainted(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
            setHorizontalTextPosition(JButton.CENTER);
            setVerticalTextPosition(JButton.CENTER);
            setRolloverEnabled(true);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent event) {
                    rollover = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent event) {
                    rollover = false;
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D g2 = (Graphics2D) graphics.create();
            if (skin != null && skin.hasAnyImage()) {
                BufferedImage image = skin.resolve(getModel(), rollover);
                if (image != null) {
                    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
                    g2.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                }
            } else {
                g2.setColor(resolveFallbackColor());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            }
            g2.dispose();
            super.paintComponent(graphics);
        }

        private Color resolveFallbackColor() {
            if (!isEnabled()) return fallbackColor.darker();
            if (getModel().isPressed()) return fallbackColor.darker();
            if (rollover) return fallbackColor.brighter();
            return fallbackColor;
        }
    }
}
