package com.ror.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BattlePanel extends JPanel {
    // Battle-related fields moved from GameWindow
    private final JLabel battleTitleValue = new JLabel("Battle");
    private final JLabel battleRoundValue = new JLabel("Round 1");
    private final JLabel battleHeroNameValue = new JLabel("-");
    private final JLabel battleHeroLevelValue = new JLabel("Level -");
    private final JLabel battleEnemyNameValue = new JLabel("-");
    private final JLabel battleHeroSpriteLabel = new JLabel(
            new ImageIcon("src/com/ror/models/assets/images/heroes/placeholder.png"));
    private final JLabel battleEnemySpriteLabel = new JLabel(
            new ImageIcon("src/com/ror/models/assets/images/enemies/placeholder.png"));
    private final JProgressBar battleHeroHpBar = new JProgressBar();
    private final JProgressBar battleHeroManaBar = new JProgressBar();
    private final JProgressBar battleEnemyHpBar = new JProgressBar();
    private final JProgressBar battleEnemyManaBar = new JProgressBar();
    private final JTextArea battleLogArea = new JTextArea();
    private JPanel battleActionCards;
    private JPanel battleButtonsPanel;
    private JPanel battleImagePanel;
    private JButton battleBasicButton;
    private JButton battlePotionButton;
    private JButton battleRunButton;
    private JButton battleSkill1Button;
    private JButton battleSkill2Button;
    private JButton battleUltimateButton;
    private boolean battleButtonsVisible = true;

    // Colors
    private static final Color COLOR_BATTLE_PANEL = new Color(128, 99, 84);
    private static final Color COLOR_BATTLE_SCREEN_BG = new Color(239, 235, 228);
    private static final Color COLOR_BATTLE_SURFACE = new Color(147, 119, 99);
    private static final Color COLOR_BATTLE_SURFACE_HOVER = new Color(161, 132, 110);
    private static final Color COLOR_BATTLE_SURFACE_PRESSED = new Color(126, 101, 84);
    private static final Color COLOR_BATTLE_SURFACE_DISABLED = new Color(132, 118, 107);
    private static final Color COLOR_BATTLE_BG = new Color(71, 54, 44);
    private static final Color COLOR_BATTLE_PLACEHOLDER = new Color(125, 100, 88);
    private static final Color COLOR_BATTLE_BORDER = new Color(109, 88, 70);
    private static final Color COLOR_BATTLE_TITLE = new Color(46, 31, 20);
    private static final Color COLOR_BATTLE_INFO_SURFACE = new Color(239, 235, 228);
    private static final Color COLOR_BATTLE_INFO_TEXT = new Color(46, 31, 20);
    private static final Color COLOR_TEXT_DARK = Color.WHITE;
    private static final Color COLOR_TEXT_MUTED = Color.WHITE;
    private static final Color COLOR_BORDER = Color.decode("#4D5B9E");
    private static final Color COLOR_HERO_HP = new Color(164, 54, 54);
    private static final Color COLOR_HERO_MANA = new Color(52, 92, 156);

    private final Font headingFont;
    private final Font bodyFont;
    private final BattleActionListener actionListener;

    public interface BattleActionListener {
        void onBattleAction(int action);

        void onShowAttackPanel();
    }

    public BattlePanel(Font headingFont, Font bodyFont, BattleActionListener actionListener) {
        this.headingFont = headingFont;
        this.bodyFont = bodyFont;
        this.actionListener = actionListener;
        initialize();
    }

    private void initialize() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(true);
        setBackground(COLOR_BATTLE_SCREEN_BG);
        // Keep vertical breathing room but let center arena touch horizontal edges.
        setBorder(BorderFactory.createEmptyBorder(16, 0, 16, 0));

        battleTitleValue.setForeground(COLOR_BATTLE_TITLE);
        battleTitleValue.setFont(getHeadingFont(34f));
        battleHeroLevelValue.setFont(getBodyFont(14f).deriveFont(Font.BOLD, 14f));
        battleHeroLevelValue.setForeground(COLOR_TEXT_MUTED);

        JPanel topRow = new JPanel(new BorderLayout(12, 0));
        topRow.setOpaque(false);
        topRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 160));

        JPanel enemySummary = createEnemySummaryPanel();
        enemySummary.setPreferredSize(new Dimension(660, 108));
        enemySummary.setMinimumSize(new Dimension(660, 108));
        enemySummary.setMaximumSize(new Dimension(660, 108));

        JPanel titleWrap = new JPanel(new FlowLayout(FlowLayout.LEFT, 22, 10));
        titleWrap.setOpaque(false);
        titleWrap.add(battleTitleValue);

        JPanel enemyWrap = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        enemyWrap.setOpaque(false);
        enemyWrap.add(enemySummary);

        topRow.add(titleWrap, BorderLayout.WEST);
        topRow.add(enemyWrap, BorderLayout.CENTER);

        add(topRow);
        add(Box.createVerticalStrut(18));
        add(createBattleArenaPanel());
        add(Box.createVerticalStrut(22));

        battleBasicButton = createBattleButton("Fight");
        battlePotionButton = createBattleButton("Inventory");
        battleRunButton = createBattleButton("Run");
        battleSkill1Button = createBattleButton("Skill 1");
        battleSkill2Button = createBattleButton("Skill 2");
        battleUltimateButton = createBattleButton("Ultimate");

        battleBasicButton.addActionListener(event -> actionListener.onShowAttackPanel());
        battlePotionButton.addActionListener(event -> actionListener.onBattleAction(4));
        battleRunButton.addActionListener(event -> actionListener.onBattleAction(5));
        battleSkill1Button.addActionListener(event -> actionListener.onBattleAction(1));
        battleSkill2Button.addActionListener(event -> actionListener.onBattleAction(2));
        battleUltimateButton.addActionListener(event -> actionListener.onBattleAction(3));

        battleActionCards = new JPanel(new CardLayout());
        battleActionCards.setOpaque(true);
        battleActionCards.setBackground(COLOR_BATTLE_SCREEN_BG);

        battleButtonsPanel = new JPanel(new GridLayout(1, 3, 18, 0));
        battleButtonsPanel.setOpaque(true);
        battleButtonsPanel.setBackground(COLOR_BATTLE_PANEL);
        battleButtonsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BATTLE_BORDER, 1),
                BorderFactory.createEmptyBorder(14, 14, 14, 14)));
        battleButtonsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 88));
        battleButtonsPanel.add(battleBasicButton);
        battleButtonsPanel.add(battlePotionButton);
        battleButtonsPanel.add(battleRunButton);

        JPanel attackPanel = new JPanel();
        attackPanel.setLayout(new BoxLayout(attackPanel, BoxLayout.Y_AXIS));
        attackPanel.setOpaque(true);
        attackPanel.setBackground(COLOR_BATTLE_PANEL);
        attackPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BATTLE_BORDER, 1),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)));
        JPanel attackRow = new JPanel(new GridLayout(1, 3, 18, 0));
        attackRow.setOpaque(false);
        JButton battleAttackButton = createBattleButton("Attack");
        battleAttackButton.addActionListener(e -> actionListener.onBattleAction(0));
        attackRow.add(battleAttackButton);
        attackRow.add(battleSkill1Button);
        attackRow.add(battleSkill2Button);
        attackPanel.add(attackRow);
        attackPanel.add(Box.createVerticalStrut(16));
        JPanel ultimateRow = new JPanel(new GridLayout(1, 1));
        ultimateRow.setOpaque(false);
        battleUltimateButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 56));
        ultimateRow.add(battleUltimateButton);
        attackPanel.add(ultimateRow);

        battleImagePanel = new JPanel(new BorderLayout());
        battleImagePanel.setOpaque(true);
        battleImagePanel.setBackground(COLOR_BATTLE_PLACEHOLDER);
        battleImagePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BATTLE_BORDER, 1),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)));
        JLabel battleImageLabel = new JLabel("<html><center>Battle Image Placeholder</center></html>",
                SwingConstants.CENTER);
        battleImageLabel.setFont(getBodyFont(16f).deriveFont(Font.BOLD, 16f));
        battleImageLabel.setForeground(COLOR_TEXT_DARK);
        battleImagePanel.add(battleImageLabel, BorderLayout.CENTER);

        battleActionCards.add(battleButtonsPanel, "buttons");
        battleActionCards.add(attackPanel, "attack");
        battleActionCards.add(battleImagePanel, "image");

        JPanel heroPanel = createBattleHeroPanel();
        heroPanel.setPreferredSize(new Dimension(360, 200));

        JPanel bottomRow = new JPanel(new BorderLayout(18, 0));
        bottomRow.setOpaque(false);
        bottomRow.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 0));
        bottomRow.add(heroPanel, BorderLayout.WEST);
        bottomRow.add(battleActionCards, BorderLayout.CENTER);

        add(bottomRow);
        add(Box.createVerticalStrut(12));
    }

    private Font getHeadingFont(float size) {
        return headingFont.deriveFont(size);
    }

    private Font getBodyFont(float size) {
        return bodyFont.deriveFont(size);
    }

    private JButton createBattleButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics graphics) {
                Graphics2D graphics2D = (Graphics2D) graphics.create();
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                ButtonModel model = getModel();
                Color fill = getBackground();
                if (!model.isEnabled()) {
                    fill = COLOR_BATTLE_SURFACE_DISABLED;
                } else if (model.isPressed()) {
                    fill = COLOR_BATTLE_SURFACE_PRESSED;
                } else if (model.isRollover()) {
                    fill = COLOR_BATTLE_SURFACE_HOVER;
                }
                graphics2D.setColor(fill);
                graphics2D.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 18, 18);
                graphics2D.dispose();
                super.paintComponent(graphics);
            }

            @Override
            protected void paintBorder(Graphics graphics) {
                Graphics2D graphics2D = (Graphics2D) graphics.create();
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics2D.setColor(COLOR_BATTLE_BORDER);
                graphics2D.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 18, 18);
                graphics2D.dispose();
            }
        };
        button.setFont(getBodyFont(16f).deriveFont(Font.BOLD, 16f));
        button.setForeground(COLOR_TEXT_DARK);
        button.setBackground(COLOR_BATTLE_SURFACE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setRolloverEnabled(true);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 56));
        return button;
    }

    private JPanel createBattleSummaryCard(String label, JLabel name, JProgressBar hp, JProgressBar mana) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setOpaque(true);
        card.setBackground(COLOR_BATTLE_SURFACE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                BorderFactory.createEmptyBorder(14, 14, 14, 14)));

        name.setForeground(COLOR_TEXT_DARK);
        name.setFont(getBodyFont(28f).deriveFont(Font.BOLD, 28f));

        if (label != null && !label.isBlank()) {
            JLabel title = new JLabel(label);
            title.setFont(getBodyFont(14f).deriveFont(Font.BOLD, 14f));
            title.setForeground(COLOR_TEXT_DARK);
            card.add(title);
            card.add(Box.createVerticalStrut(4));
        }
        card.add(name);
        card.add(Box.createVerticalStrut(18));

        hp.setStringPainted(true);
        hp.setForeground(COLOR_HERO_HP);
        hp.setBackground(COLOR_BATTLE_BG);
        mana.setStringPainted(true);
        mana.setForeground(COLOR_HERO_MANA);
        mana.setBackground(COLOR_BATTLE_BG);

        JLabel hpLabel = new JLabel("Health");
        hpLabel.setFont(getBodyFont(13f).deriveFont(Font.BOLD, 13f));
        hpLabel.setForeground(COLOR_TEXT_DARK);
        card.add(hpLabel);
        card.add(hp);
        card.add(Box.createVerticalStrut(8));
        JLabel manaLabel = new JLabel("Mana");
        manaLabel.setFont(getBodyFont(13f).deriveFont(Font.BOLD, 13f));
        manaLabel.setForeground(COLOR_TEXT_DARK);
        card.add(manaLabel);
        card.add(mana);
        return card;
    }

    private JPanel createBattleHeroPanel() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setOpaque(true);
        card.setBackground(COLOR_BATTLE_INFO_SURFACE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                BorderFactory.createEmptyBorder(14, 14, 14, 14)));

        battleHeroNameValue.setForeground(COLOR_BATTLE_INFO_TEXT);
        battleHeroNameValue.setFont(getBodyFont(28f).deriveFont(Font.BOLD, 28f));
        battleHeroLevelValue.setForeground(COLOR_BATTLE_INFO_TEXT);
        battleHeroLevelValue.setFont(getBodyFont(16f).deriveFont(Font.PLAIN, 16f));

        battleHeroHpBar.setStringPainted(true);
        battleHeroHpBar.setForeground(COLOR_HERO_HP);
        battleHeroHpBar.setBackground(COLOR_BATTLE_BG);
        battleHeroHpBar.setFont(getBodyFont(14f));
        battleHeroManaBar.setStringPainted(true);
        battleHeroManaBar.setForeground(COLOR_HERO_MANA);
        battleHeroManaBar.setBackground(COLOR_BATTLE_BG);
        battleHeroManaBar.setFont(getBodyFont(14f));

        card.add(battleHeroNameValue);
        card.add(Box.createVerticalStrut(4));
        card.add(battleHeroLevelValue);
        card.add(Box.createVerticalStrut(18));
        JLabel hpLabel = new JLabel("Health");
        hpLabel.setFont(getBodyFont(13f).deriveFont(Font.BOLD, 13f));
        hpLabel.setForeground(COLOR_BATTLE_INFO_TEXT);
        card.add(hpLabel);
        card.add(battleHeroHpBar);
        card.add(Box.createVerticalStrut(8));
        JLabel manaLabel = new JLabel("Mana");
        manaLabel.setFont(getBodyFont(13f).deriveFont(Font.BOLD, 13f));
        manaLabel.setForeground(COLOR_BATTLE_INFO_TEXT);
        card.add(manaLabel);
        card.add(battleHeroManaBar);
        return card;
    }

    private JPanel createBattleArenaPanel() {
        JPanel arena = new JPanel(new BorderLayout());
        arena.setOpaque(true);
        arena.setBackground(COLOR_BATTLE_BG);
        arena.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)));
        arena.setPreferredSize(new Dimension(0, 260));

        JPanel spriteStrip = new JPanel();
        spriteStrip.setLayout(new BoxLayout(spriteStrip, BoxLayout.X_AXIS));
        spriteStrip.setOpaque(true);
        spriteStrip.setBackground(COLOR_BATTLE_PLACEHOLDER);
        spriteStrip.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BATTLE_BORDER, 1),
                BorderFactory.createEmptyBorder(8, 16, 8, 16)));

        battleHeroSpriteLabel.setHorizontalAlignment(SwingConstants.LEFT);
        battleHeroSpriteLabel.setVerticalAlignment(SwingConstants.CENTER);
        battleEnemySpriteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        battleEnemySpriteLabel.setVerticalAlignment(SwingConstants.CENTER);

        spriteStrip.add(battleHeroSpriteLabel);
        spriteStrip.add(Box.createHorizontalGlue());
        spriteStrip.add(battleEnemySpriteLabel);
        arena.add(spriteStrip, BorderLayout.CENTER);

        return arena;
    }

    private JPanel createEnemySummaryPanel() {
        JPanel card = new JPanel(new BorderLayout(20, 0));
        card.setOpaque(true);
        card.setBackground(COLOR_BATTLE_INFO_SURFACE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                BorderFactory.createEmptyBorder(10, 14, 10, 14)));

        battleEnemyNameValue.setForeground(COLOR_BATTLE_INFO_TEXT);
        battleEnemyNameValue.setFont(getBodyFont(20f).deriveFont(Font.BOLD, 20f));
        battleEnemyNameValue.setHorizontalAlignment(SwingConstants.LEFT);

        battleEnemyHpBar.setForeground(COLOR_HERO_HP);
        battleEnemyManaBar.setForeground(COLOR_HERO_MANA);

        JPanel barsPanel = new JPanel();
        barsPanel.setLayout(new BoxLayout(barsPanel, BoxLayout.Y_AXIS));
        barsPanel.setOpaque(false);

        barsPanel.add(createEnemyBarRow("Health", battleEnemyHpBar));
        barsPanel.add(Box.createVerticalStrut(10));
        barsPanel.add(createEnemyBarRow("Mana", battleEnemyManaBar));

        card.add(battleEnemyNameValue, BorderLayout.WEST);
        card.add(barsPanel, BorderLayout.CENTER);
        return card;
    }

    private JPanel createEnemyBarRow(String labelText, JProgressBar bar) {
        JPanel row = new JPanel(new BorderLayout(12, 0));
        row.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(getBodyFont(14f).deriveFont(Font.PLAIN, 14f));
        label.setForeground(COLOR_BATTLE_INFO_TEXT);
        label.setPreferredSize(new Dimension(62, 20));

        bar.setStringPainted(true);
        bar.setFont(getBodyFont(13f).deriveFont(Font.BOLD, 13f));
        bar.setPreferredSize(new Dimension(360, 20));
        bar.setBackground(COLOR_BATTLE_BG);

        row.add(label, BorderLayout.WEST);
        row.add(bar, BorderLayout.CENTER);
        return row;
    }

    // Methods to control the battle UI
    public void showBattleImage() {
        if (battleActionCards == null)
            return;
        CardLayout layout = (CardLayout) battleActionCards.getLayout();
        layout.show(battleActionCards, "image");
        battleButtonsVisible = false;
    }

    public void showAttackPanel() {
        if (battleActionCards == null)
            return;
        CardLayout layout = (CardLayout) battleActionCards.getLayout();
        layout.show(battleActionCards, "attack");
        battleButtonsVisible = false;
    }

    public void restoreBattleButtons() {
        if (battleActionCards == null)
            return;
        CardLayout layout = (CardLayout) battleActionCards.getLayout();
        layout.show(battleActionCards, "buttons");
        battleButtonsVisible = true;
        setBattleButtonsEnabled(true);
    }

    public void tryRestoreBattleButtons() {
        if (!battleButtonsVisible) {
            restoreBattleButtons();
        }
    }

    public void setBattleButtonsEnabled(boolean enabled) {
        battleBasicButton.setEnabled(enabled);
        battlePotionButton.setEnabled(enabled);
        battleRunButton.setEnabled(enabled);
        battleSkill1Button.setEnabled(enabled);
        battleSkill2Button.setEnabled(enabled);
        battleUltimateButton.setEnabled(enabled);
    }

    // Getters for updating UI
    public JLabel getBattleTitleValue() {
        return battleTitleValue;
    }

    public JLabel getBattleRoundValue() {
        return battleRoundValue;
    }

    public JLabel getBattleHeroNameValue() {
        return battleHeroNameValue;
    }

    public JLabel getBattleHeroLevelValue() {
        return battleHeroLevelValue;
    }

    public JLabel getBattleEnemyNameValue() {
        return battleEnemyNameValue;
    }

    public JProgressBar getBattleHeroHpBar() {
        return battleHeroHpBar;
    }

    public JProgressBar getBattleHeroManaBar() {
        return battleHeroManaBar;
    }

    public JProgressBar getBattleEnemyHpBar() {
        return battleEnemyHpBar;
    }

    public JProgressBar getBattleEnemyManaBar() {
        return battleEnemyManaBar;
    }

    public JTextArea getBattleLogArea() {
        return battleLogArea;
    }

    public JLabel getBattleHeroSpriteLabel() {
        return battleHeroSpriteLabel;
    }

    public JLabel getBattleEnemySpriteLabel() {
        return battleEnemySpriteLabel;
    }
}
