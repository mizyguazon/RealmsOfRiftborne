package com.ror.engine;

import com.ror.models.*;
import com.ror.models.Boss.*; //God forbid we have two Inventory classes and we import the wrong one by accident
import com.ror.models.Inventory.Inventory;
import com.ror.models.Mobs.*;
import com.ror.models.training.StatProgress;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.FontUIResource;

public class GameWindow implements BattlePanel.BattleActionListener {

    // Directory ug string assignments for loading assets and managing card layout
    // screens. If you add new screens or asset folders, add new constants here to
    // keep things organized.
    private static final String UI_IMAGE_DIRECTORY = "assets/images/ui/";
    private static final String ENEMY_IMAGE_DIRECTORY = "assets/images/enemies/";
    private static final String ROOT_LANDING = "landing";
    private static final String ROOT_GAME = "game";
    private static final String SCREEN_HOME = "home";
    private static final String SCREEN_CHARACTER = "character";
    private static final String SCREEN_STORY = "story";
    private static final String SCREEN_MAIN = "main";
    private static final String SCREEN_ACADEMY = "academy";
    private static final String SCREEN_SHOP = "shop";
    private static final String SCREEN_INVENTORY = "inventory";
    private static final String SCREEN_PROFILE = "profile";
    private static final String SCREEN_BATTLE = "battle";

    private final DecimalFormat statFormat = new DecimalFormat("#,##0");
    private final Random random = new Random();
    private final StatProgress statProgress = new StatProgress();

    // Fallback fonts if wala ang custom fonts
    private Font headingFont = new Font("Serif", Font.BOLD, 28);
    private Font bodyFont = new Font("SansSerif", Font.PLAIN, 14);
    private float headingFontSize = 28f;
    private float bodyFontSize = 14f;

    private final JTextArea outputArea = new JTextArea();
    private final JTextField inputField = new JTextField();
    private final JButton sendButton = new JButton("Enter");
    private final StringBuilder pendingLegacyOutput = new StringBuilder();
    private static final int LEGACY_OUTPUT_TAIL_LIMIT = 500;
    private static final Pattern BOXED_YES_NO_PROMPT = Pattern.compile(
            "(?s)\\n?┌[^\\n]*┐\\n(?:│[^\\n]*\\n)+?└[^\\n]*┘\\n?(?:-->\\|\\s*)?");
    private static final Pattern INLINE_YES_NO_PROMPT = Pattern.compile(
            "\\n?[^\\n]*\\(y/n\\):\\s*");

    private final CardLayout rootLayout = new CardLayout();
    private final JPanel rootPanel = new JPanel(rootLayout);
    private final CardLayout screenLayout = new CardLayout();
    private final JPanel screenPanel = new JPanel(screenLayout);

    // Theme colors who the hell reads RGBA colors, gamit sa uban functions like
    // panels
    private static final Color COLOR_BACKGROUND = new Color(227, 221, 212); // warm stone background
    private static final Color COLOR_PANEL = new Color(239, 235, 228); // parchment panel fill
    private static final Color COLOR_BATTLE_PANEL = new Color(128, 99, 84);
    private static final Color COLOR_BATTLE_SURFACE = new Color(147, 119, 99);
    private static final Color COLOR_BATTLE_BG = new Color(71, 54, 44);
    private static final Color COLOR_BATTLE_PLACEHOLDER = new Color(125, 100, 88);
    private static final Color COLOR_BATTLE_BORDER = new Color(109, 88, 70);
    private static final Color COLOR_TEXT_DARK = new Color(46, 31, 20); // deep umber text
    private static final Color COLOR_TEXT_MUTED = new Color(106, 79, 60); // muted bronze text
    private static final Color COLOR_BORDER = new Color(145, 114, 91);
    private static final Color COLOR_HERO_HP = new Color(164, 54, 54); // elderberry red (HP bar)
    private static final Color COLOR_HERO_MANA = new Color(52, 92, 156); // deep royal blue (Mana bar)

    private static final Color COLOR_CHARSEL_BACKGROUND = new Color(227, 221, 212); // warm stone background
    private static final Color COLOR_CHARSEL_PANEL = new Color(239, 235, 228); // parchment panel fill
    private static final Color COLOR_CHARSEL_TEXT_DARK = new Color(46, 31, 20); // deep umber text
    private static final Color COLOR_CHARSEL_TEXT_MUTED = new Color(106, 79, 60); // muted bronze text

    private final JLabel titleLabel = new JLabel("Mystvale Academy");
    private final JLabel subtitleLabel = new JLabel("GUI RPG");
    private JPanel headerPanel;
    private JButton headerExitButton;
    private JPanel leftPane;
    private Border defaultLeftPaneBorder;

    private final JLabel heroNameValue = new JLabel("-");
    private final JLabel heroClassValue = new JLabel("-");
    private final JLabel heroLevelValue = new JLabel("-");
    private final JLabel heroGoldValue = new JLabel("-");
    private final JProgressBar hpBar = new JProgressBar();
    private final JProgressBar manaBar = new JProgressBar();

    private final JLabel journeyLocationLabel = createBody("Current location: No hero selected");
    private final JLabel journeyCompletionLabel = createBody("No hero chosen yet.");
    private final JLabel journeyObjectivesLabel = createBody("No objectives yet. Choose a hero to start.");

    private final JLabel inventorySummary = new JLabel("No hero selected.");
    private final JLabel smallHealthCount = new JLabel("0");
    private final JLabel mediumHealthCount = new JLabel("0");
    private final JLabel largeHealthCount = new JLabel("0");
    private final JLabel smallManaCount = new JLabel("0");
    private final JLabel mediumManaCount = new JLabel("0");
    private final JLabel largeManaCount = new JLabel("0");
    private final JLabel shopGoldLabel = new JLabel("Gold: -");
    private final JLabel shopStatusLabel = new JLabel("Choose an item and quantity.");

    private final JLabel profileName = new JLabel("-");
    private final JLabel profileClass = new JLabel("-");
    private final JLabel profileWeapon = new JLabel("-");
    private final JLabel profileLevel = new JLabel("-");
    private final JLabel profileAttack = new JLabel("-");
    private final JLabel profileDefense = new JLabel("-");
    private final JLabel profileSpeed = new JLabel("-");
    private final JLabel profileSkill1 = new JLabel("-");
    private final JLabel profileSkill2 = new JLabel("-");
    private final JLabel profileUltimate = new JLabel("-");

    private final JTextArea storyTextArea = new JTextArea();
    private final JLabel storyTitleLabel = new JLabel("Story");
    private final JLabel storyProgressLabel = new JLabel("Scene 1 / 1");
    private Timer storyTypewriterTimer;
    private String currentStoryLine = "";
    private static final int STORY_TYPEWRITER_DELAY_MS = 14;

    private JButton forestButton;
    private JButton swampButton;
    private JButton forsakenButton;
    private JButton storyNextButton;
    private JButton storySkipButton;
    private JButton storyStartButton;
    private JPanel quickChoicePanel;
    private JLabel quickChoicePromptLabel;
    private JButton yesChoiceButton;
    private JButton noChoiceButton;

    private final Object battleActionLock = new Object();
    private volatile Integer pendingBattleAction = null;
    private final transitions transitionManager = new transitions();

    private Hero hero;
    private String[] currentStorySequence = new String[0];
    private int currentStoryIndex = 0;
    private boolean inventoryNarrationShown = false;
    private JFrame frame;
    private AdventurePanel overlay;
    private BattlePanel battlePanel;
    private BufferedImage landingBackground;
    private final ButtonSkin primaryButtonSkin;
    private final ButtonSkin secondaryButtonSkin;

    private final PipedInputStream gameInputStream;
    private final PipedOutputStream gameInputWriter;

    public GameWindow() {
        loadFonts();

        try {
            gameInputStream = new PipedInputStream();
            gameInputWriter = new PipedOutputStream(gameInputStream);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to initialize GUI input stream.", exception);
        }

        landingBackground = loadLandingBackground();
        // Main screen ra guro
        primaryButtonSkin = loadButtonSkin("button-primary");
        secondaryButtonSkin = loadButtonSkin("button-secondary");
    }

    public void launchGame() {
        wireConsoleStreams();
        frame = buildFrame();
        setupBattleBackspaceBinding();
        frame.setVisible(true);
        showLandingScreen();
    }

    private JFrame buildFrame() {
        JFrame window = new JFrame("Mystvale Academy RPG");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setMinimumSize(new Dimension(1280, 800));
        window.setSize(1280, 800);
        window.setResizable(false);
        window.setContentPane(rootPanel);

        overlay = new AdventurePanel(window, getHeadingFont(30f), getBodyFont(16f));
        window.setGlassPane(overlay);

        battlePanel = new BattlePanel(getHeadingFont(30f), getBodyFont(16f), this);

        rootPanel.add(buildLandingScreen(), ROOT_LANDING);
        rootPanel.add(buildGameShell(), ROOT_GAME);
        window.setLocationRelativeTo(null);

        return window;
    }

    private JPanel buildGameShell() {
        // Root container for in-game interface (header + main content area).
        JPanel panel = new JPanel(new BorderLayout(18, 18));
        panel.setBackground(COLOR_BACKGROUND);

        headerPanel = buildHeader();
        leftPane = buildLeftPane();

        // Header at top, main content (with card layout) center.
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(leftPane, BorderLayout.CENTER);
        return panel;
    }

    private void setupBattleBackspaceBinding() {
        if (frame == null) {
            return;
        }
        InputMap inputMap = frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = frame.getRootPane().getActionMap();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "battle.restoreButtons");
        actionMap.put("battle.restoreButtons", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                battlePanel.tryRestoreBattleButtons();
            }
        });
    }

    private JPanel buildHeader() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(18, 22, 0, 22));
        panel.setBackground(COLOR_BACKGROUND);

        titleLabel.setFont(getHeadingFont(30f));
        titleLabel.setForeground(COLOR_TEXT_DARK);

        subtitleLabel.setFont(getBodyFont(15f));
        subtitleLabel.setForeground(COLOR_TEXT_MUTED);

        JPanel textBlock = new JPanel();
        textBlock.setLayout(new BoxLayout(textBlock, BoxLayout.Y_AXIS));
        textBlock.setOpaque(false);
        textBlock.add(titleLabel);
        textBlock.add(Box.createVerticalStrut(4));
        textBlock.add(subtitleLabel);

        headerExitButton = new JButton("Exit");
        headerExitButton.setFont(getBodyFont(13f).deriveFont(Font.BOLD, 13f));
        headerExitButton.setForeground(COLOR_TEXT_DARK);
        headerExitButton.setFocusPainted(false);
        headerExitButton.setContentAreaFilled(false);
        headerExitButton.setOpaque(false);
        headerExitButton.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));
        headerExitButton.setPreferredSize(new Dimension(90, 34));
        headerExitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                headerExitButton.setBorder(BorderFactory.createLineBorder(new Color(118, 81, 53), 1));
            }

            @Override
            public void mouseExited(MouseEvent event) {
                headerExitButton.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));
            }
        });
        headerExitButton.addActionListener(event -> {
            int choice = showConfirmSync("Exit", "Do you want to go back to the main menu?");
            if (choice == JOptionPane.YES_OPTION) {
                showLandingScreen();
            }
        });

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        actions.setOpaque(false);
        actions.add(headerExitButton);

        panel.add(textBlock, BorderLayout.WEST);
        panel.add(actions, BorderLayout.EAST);
        return panel;
    }

    private JPanel buildLeftPane() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(1220, 720));
        defaultLeftPaneBorder = BorderFactory.createEmptyBorder(0, 22, 22, 22);
        panel.setBorder(defaultLeftPaneBorder);
        panel.setBackground(COLOR_BACKGROUND);

        screenPanel.setOpaque(false);
        screenPanel.add(buildHomeScreen(), SCREEN_HOME);
        screenPanel.add(buildCharacterScreen(), SCREEN_CHARACTER);
        screenPanel.add(buildStoryScreen(), SCREEN_STORY);
        screenPanel.add(buildMainScreen(), SCREEN_MAIN);
        screenPanel.add(buildAcademyScreen(), SCREEN_ACADEMY);
        screenPanel.add(buildShopScreen(), SCREEN_SHOP);
        screenPanel.add(buildInventoryScreen(), SCREEN_INVENTORY);
        screenPanel.add(buildProfileScreen(), SCREEN_PROFILE);
        screenPanel.add(battlePanel, SCREEN_BATTLE);

        panel.add(screenPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel buildRightPane() {
        JPanel panel = new JPanel(new BorderLayout(0, 12));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 22, 22));
        panel.setBackground(COLOR_BACKGROUND);

        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setFont(getBodyFont(14f));
        outputArea.setBackground(new Color(20, 24, 31));
        outputArea.setForeground(new Color(234, 239, 245));
        outputArea.setMargin(new Insets(14, 14, 14, 14));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(61, 48, 39), 1),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)));

        JPanel inputPanel = new JPanel(new BorderLayout(8, 8));
        inputPanel.setBackground(COLOR_BACKGROUND);

        JLabel inputHelp = new JLabel("Game input");
        inputHelp.setForeground(COLOR_TEXT_MUTED);

        inputField.addActionListener(event -> submitLegacyInput());
        sendButton.addActionListener(event -> submitLegacyInput());

        JButton clearButton = createSecondaryButton("Clear Log");
        clearButton.addActionListener(event -> outputArea.setText(""));

        JPanel row = new JPanel(new BorderLayout(8, 0));
        row.setOpaque(false);
        row.add(inputField, BorderLayout.CENTER);
        row.add(sendButton, BorderLayout.EAST);

        quickChoicePanel = new JPanel(new GridLayout(1, 2, 8, 0));
        quickChoicePanel.setOpaque(false);
        quickChoicePromptLabel = new JLabel("Choose:");
        quickChoicePromptLabel.setForeground(COLOR_TEXT_MUTED);
        quickChoicePromptLabel.setVisible(false);
        yesChoiceButton = createSecondaryButton("Yes");
        noChoiceButton = createSecondaryButton("No");
        yesChoiceButton.addActionListener(event -> submitLegacyShortcut("y"));
        noChoiceButton.addActionListener(event -> submitLegacyShortcut("n"));
        quickChoicePanel.add(yesChoiceButton);
        quickChoicePanel.add(noChoiceButton);
        quickChoicePanel.setVisible(false);

        JPanel footer = new JPanel(new BorderLayout());
        footer.setOpaque(false);
        footer.add(inputHelp, BorderLayout.CENTER);
        footer.add(clearButton, BorderLayout.EAST);

        inputPanel.add(footer, BorderLayout.NORTH);
        inputPanel.add(row, BorderLayout.CENTER);

        JPanel quickChoiceWrapper = new JPanel(new BorderLayout(0, 6));
        quickChoiceWrapper.setOpaque(false);
        quickChoiceWrapper.add(quickChoicePromptLabel, BorderLayout.NORTH);
        quickChoiceWrapper.add(quickChoicePanel, BorderLayout.CENTER);
        inputPanel.add(quickChoiceWrapper, BorderLayout.SOUTH);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel buildHomeScreen() {
        JPanel panel = createCardPanel();

        JLabel heading = createHeading("Main Screen");
        JLabel body = createBody("Choose your hero and begin.");

        JButton chooseHeroButton = createPrimaryButton("Choose Hero");
        chooseHeroButton.addActionListener(event -> {
            subtitleLabel.setText("Choose a hero to begin.");
            showScreen(SCREEN_CHARACTER);
        });

        JButton titleScreenButton = createSecondaryButton("Return to Title");
        titleScreenButton.addActionListener(event -> showLandingScreen());

        panel.add(heading);
        panel.add(Box.createVerticalStrut(10));
        panel.add(body);
        panel.add(Box.createVerticalStrut(18));
        panel.add(chooseHeroButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(titleScreenButton);
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel buildCharacterScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(true);
        panel.setBackground(COLOR_CHARSEL_BACKGROUND);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                BorderFactory.createEmptyBorder(20, 24, 20, 24)));

        JLabel heading = createHeading("Choose Character");
        heading.setForeground(COLOR_CHARSEL_TEXT_DARK);
        panel.add(heading);
        panel.add(Box.createVerticalStrut(8));
        JLabel introLabel = createBody("Pick your character.");
        introLabel.setForeground(COLOR_CHARSEL_TEXT_MUTED);
        panel.add(introLabel);
        panel.add(Box.createVerticalStrut(18));

        // If you later add separate image paths per hero, these are the three
        // createCharacterButton(...) calls to update.
        panel.add(createCharacterButton(
                "Swordsman",
                "Frontline fighter",
                () -> selectHero(new Swordsman())));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createCharacterButton(
                "Gunner",
                "Ranged attacker",
                () -> selectHero(new Gunner())));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createCharacterButton(
                "Mage",
                "Spell caster",
                () -> selectHero(new Mage())));
        panel.add(Box.createVerticalStrut(18));

        JButton backButton = createCharacterSelectButton("Back to Main Page");
        backButton.addActionListener(event -> {
            showLandingScreen();
        });

        panel.add(backButton);
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel buildMainScreen() {
        // Main game dashboard with adventure, status, map and travel controls.
        JPanel panel = new JPanel(new BorderLayout(12, 12));
        panel.setBackground(COLOR_PANEL);

        // Top row: Adventure overview + journey status
        JPanel topRow = new JPanel(new GridLayout(1, 2, 12, 0));
        topRow.setBackground(COLOR_PANEL);

        JPanel adventPanel = createCardPanel();
        adventPanel.setLayout(new BoxLayout(adventPanel, BoxLayout.Y_AXIS));
        adventPanel.add(createHeading("Adventure Overview"));
        adventPanel.add(Box.createVerticalStrut(10));
        adventPanel.add(buildHeroSummaryCard());

        JPanel statusPanel = createCardPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        statusPanel.add(createHeading("Journey Status"));
        statusPanel.add(Box.createVerticalStrut(10));

        statusPanel.add(journeyLocationLabel);
        statusPanel.add(Box.createVerticalStrut(8));

        statusPanel.add(journeyCompletionLabel);
        statusPanel.add(Box.createVerticalStrut(10));

        statusPanel.add(createHeading("Objectives"));
        statusPanel.add(Box.createVerticalStrut(8));

        statusPanel.add(journeyObjectivesLabel);

        topRow.add(adventPanel);
        topRow.add(statusPanel);

        // Middle row: region map + travel destinations
        JPanel midRow = new JPanel(new BorderLayout(12, 12));
        midRow.setOpaque(false);

        JPanel mapCard = createCardPanel();
        mapCard.setLayout(new BorderLayout(10, 10));
        JLabel mapTitle = createHeading("Region Map");
        mapCard.add(mapTitle, BorderLayout.NORTH);

        JPanel mapPlaceholder = new JPanel();
        mapPlaceholder.setBackground(new Color(22, 26, 24));
        mapPlaceholder.setPreferredSize(new Dimension(760, 420));
        mapPlaceholder.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 2));

        mapCard.add(mapPlaceholder, BorderLayout.CENTER);

        JPanel destinations = createCardPanel();
        destinations.setLayout(new BoxLayout(destinations, BoxLayout.Y_AXIS));

        JLabel destinationsTitle = createHeading("Travel Destinations");
        destinationsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        destinations.add(destinationsTitle);
        destinations.add(Box.createVerticalStrut(22));

        JButton academyButton = createPrimaryButton("Go to Academy");
        academyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        academyButton.setMaximumSize(new Dimension(200, 42));
        academyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        academyButton.addActionListener(event -> {
            if (requireHero()) {
                boolean firstVisit = !hero.hasVisitedAcademy();
                transitionManager.runTransition(frame,
                        () -> {
                            hero.setHasVisitedAcademy(true);
                            subtitleLabel.setText("Explore Mystvale Academy.");
                            showScreen(SCREEN_ACADEMY);
                        },
                        () -> {
                            if (firstVisit) {
                                playNarrationSequence("Academy Narration", buildAcademyNarration());
                            }
                        });
            }
        });

        JButton forestButtonLocal = createPrimaryButton("Forest of Reverie");
        forestButtonLocal.setMaximumSize(new Dimension(200, 42));
        forestButtonLocal.setAlignmentX(Component.CENTER_ALIGNMENT);
        forestButtonLocal.addActionListener(event -> {
            if (hero != null && hero.hasUnlockedArea1()) {
                transitionManager.runTransition(frame, null, this::launchArea1);
            } else {
                launchArea1();
            }
        });

        JButton edgeButton = createPrimaryButton("Reverie's Edge");
        edgeButton.setMaximumSize(new Dimension(200, 42));
        edgeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        edgeButton.addActionListener(event -> {
            if (hero != null && hero.hasUnlockedArea2()) {
                transitionManager.runTransition(frame, null, this::launchArea2);
            } else {
                launchArea2();
            }
        });

        JButton forsakenButtonLocal = createPrimaryButton("Forsaken Lands");
        forsakenButtonLocal.setMaximumSize(new Dimension(200, 42));
        forsakenButtonLocal.setAlignmentX(Component.CENTER_ALIGNMENT);
        forsakenButtonLocal.addActionListener(event -> {
            if (hero != null && hero.hasUnlockedArea3()) {
                transitionManager.runTransition(frame, null, this::launchArea3);
            } else {
                launchArea3();
            }
        });

        destinations.add(academyButton);
        destinations.add(Box.createVerticalStrut(8));
        destinations.add(forestButtonLocal);
        destinations.add(Box.createVerticalStrut(8));
        destinations.add(edgeButton);
        destinations.add(Box.createVerticalStrut(8));
        destinations.add(forsakenButtonLocal);
        destinations.add(Box.createVerticalGlue());

        midRow.add(mapCard, BorderLayout.CENTER);
        midRow.add(destinations, BorderLayout.EAST);

        panel.add(topRow, BorderLayout.NORTH);
        panel.add(midRow, BorderLayout.CENTER);

        return panel;
    }

    private JPanel buildAcademyScreen() {
        JPanel panel = createCardPanel();

        panel.add(createHeading("Academy (Inside)"));
        panel.add(Box.createVerticalStrut(8));
        panel.add(createBody("Library, Training Ground, Shop, Principal's Office"));
        panel.add(Box.createVerticalStrut(18));

        JPanel academyMapCard = new JPanel(new BorderLayout(10, 10));
        academyMapCard.setOpaque(true);
        academyMapCard.setBackground(COLOR_PANEL);
        academyMapCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)));
        academyMapCard.setAlignmentX(Component.LEFT_ALIGNMENT);
        academyMapCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 260));

        JLabel academyMapTitle = createHeading("Academy Map");
        academyMapTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        academyMapCard.add(academyMapTitle, BorderLayout.NORTH);

        JPanel academyMapPlaceholder = new JPanel();
        academyMapPlaceholder.setBackground(new Color(22, 26, 24));
        academyMapPlaceholder.setPreferredSize(new Dimension(900, 180));
        academyMapPlaceholder.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 2));
        academyMapCard.add(academyMapPlaceholder, BorderLayout.CENTER);

        panel.add(academyMapCard);
        panel.add(Box.createVerticalStrut(18));

        JButton libraryButton = createPrimaryButton("Library");
        libraryButton.addActionListener(event -> openLibraryGui());

        JButton trainingButton = createPrimaryButton("Training Ground");
        trainingButton.addActionListener(event -> openTrainingGui());

        JButton officeButton = createPrimaryButton("Principal Office");
        officeButton.addActionListener(event -> openPrincipalOfficeGui());

        JButton shopButton = createPrimaryButton("Shop");
        shopButton.addActionListener(event -> openShopGui());

        JButton backButton = createSecondaryButton("Back to Main Menu");
        backButton.addActionListener(event -> {
            transitionManager.runTransition(frame,
                    () -> {
                        subtitleLabel.setText("Adventure overview.");
                        refreshHeroDashboard();
                        showScreen(SCREEN_MAIN);
                    },
                    null);
        });

        panel.add(libraryButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(trainingButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(officeButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(shopButton);
        panel.add(Box.createVerticalStrut(18));
        panel.add(backButton);
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel buildStoryScreen() {
        JPanel panel = createCardPanel();

        storyTitleLabel.setFont(getHeadingFont(28f));
        storyTitleLabel.setForeground(COLOR_TEXT_DARK);
        storyTitleLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        storyProgressLabel.setFont(getBodyFont(13f));
        storyProgressLabel.setForeground(COLOR_TEXT_MUTED);
        storyProgressLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        storyTextArea.setEditable(false);
        storyTextArea.setLineWrap(true);
        storyTextArea.setWrapStyleWord(true);
        storyTextArea.setFont(getBodyFont(18f));
        storyTextArea.setBackground(COLOR_PANEL);
        storyTextArea.setForeground(COLOR_TEXT_DARK);
        storyTextArea.setMargin(new Insets(16, 16, 16, 16));

        JScrollPane storyScrollPane = new JScrollPane(storyTextArea);
        storyScrollPane.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        storyScrollPane.setPreferredSize(new Dimension(920, 360));
        storyScrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 360));
        storyScrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)));

        storyNextButton = createPrimaryButton("Next");
        storyNextButton.addActionListener(event -> advanceStoryBeat());

        storySkipButton = createSecondaryButton("Skip Story");
        storySkipButton.addActionListener(
                event -> finishStorySequence("Story skipped. You can replay it anytime from the main menu."));

        storyStartButton = createPrimaryButton("Start Adventure");
        storyStartButton.addActionListener(event -> finishStorySequence("Story complete. Your journey continues."));
        storyStartButton.setVisible(false);

        JButton backButton = createSecondaryButton("Back to Character Select");
        backButton.addActionListener(event -> {
            subtitleLabel.setText("Choose a hero to begin.");
            showScreen(SCREEN_CHARACTER);
        });

        JPanel buttonRow = new JPanel(new GridLayout(1, 4, 10, 0));
        buttonRow.setOpaque(false);
        buttonRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        buttonRow.add(storyNextButton);
        buttonRow.add(storySkipButton);
        buttonRow.add(storyStartButton);
        buttonRow.add(backButton);

        panel.add(storyTitleLabel);
        panel.add(Box.createVerticalStrut(6));
        panel.add(storyProgressLabel);
        panel.add(Box.createVerticalStrut(14));
        panel.add(storyScrollPane);
        panel.add(Box.createVerticalStrut(14));
        panel.add(buttonRow);
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel buildShopScreen() {
        JPanel content = createCardPanel();

        content.add(createHeading("Shop"));
        content.add(Box.createVerticalStrut(6));
        content.add(createBody("Buy potions here without leaving the academy screen."));
        content.add(Box.createVerticalStrut(10));

        shopGoldLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        shopGoldLabel.setForeground(COLOR_TEXT_DARK);
        content.add(shopGoldLabel);
        content.add(Box.createVerticalStrut(6));

        shopStatusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        shopStatusLabel.setForeground(COLOR_TEXT_MUTED);
        content.add(shopStatusLabel);
        content.add(Box.createVerticalStrut(12));

        content.add(createShopRow("Small Health Potion", 450, 0));
        content.add(Box.createVerticalStrut(6));
        content.add(createShopRow("Medium Health Potion", 1350, 1));
        content.add(Box.createVerticalStrut(6));
        content.add(createShopRow("Large Health Potion", 2750, 2));
        content.add(Box.createVerticalStrut(6));
        content.add(createShopRow("Small Mana Potion", 450, 3));
        content.add(Box.createVerticalStrut(6));
        content.add(createShopRow("Medium Mana Potion", 1350, 4));
        content.add(Box.createVerticalStrut(6));
        content.add(createShopRow("Large Mana Potion", 2750, 5));
        content.add(Box.createVerticalStrut(14));

        JButton bottomBackButton = createSecondaryButton("Back to Academy");
        bottomBackButton.addActionListener(event -> {
            subtitleLabel.setText("Explore Mystvale Academy.");
            refreshHeroDashboard();
            showScreen(SCREEN_ACADEMY);
        });

        content.add(bottomBackButton);
        content.add(Box.createVerticalGlue());

        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_PANEL);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel buildInventoryScreen() {
        JPanel panel = createCardPanel();

        inventorySummary.setForeground(COLOR_TEXT_DARK);
        panel.add(createHeading("Inventory"));
        panel.add(Box.createVerticalStrut(8));
        panel.add(inventorySummary);
        panel.add(Box.createVerticalStrut(14));

        panel.add(createInventoryRow("Small Health Potion", smallHealthCount, () -> usePotion("SH")));
        panel.add(Box.createVerticalStrut(8));
        panel.add(createInventoryRow("Medium Health Potion", mediumHealthCount, () -> usePotion("MH")));
        panel.add(Box.createVerticalStrut(8));
        panel.add(createInventoryRow("Large Health Potion", largeHealthCount, () -> usePotion("LH")));
        panel.add(Box.createVerticalStrut(8));
        panel.add(createInventoryRow("Small Mana Potion", smallManaCount, () -> usePotion("SM")));
        panel.add(Box.createVerticalStrut(8));
        panel.add(createInventoryRow("Medium Mana Potion", mediumManaCount, () -> usePotion("MM")));
        panel.add(Box.createVerticalStrut(8));
        panel.add(createInventoryRow("Large Mana Potion", largeManaCount, () -> usePotion("LM")));
        panel.add(Box.createVerticalStrut(18));

        JButton backButton = createSecondaryButton("Back to Main Menu");
        backButton.addActionListener(event -> {
            transitionManager.runTransition(frame,
                    () -> {
                        subtitleLabel.setText("Adventure overview.");
                        refreshHeroDashboard();
                        showScreen(SCREEN_MAIN);
                    },
                    null);
        });

        panel.add(backButton);
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel buildProfileScreen() {
        JPanel panel = createCardPanel();

        profileName.setForeground(COLOR_TEXT_DARK);
        profileClass.setForeground(COLOR_TEXT_DARK);
        profileWeapon.setForeground(COLOR_TEXT_DARK);
        profileLevel.setForeground(COLOR_TEXT_DARK);
        profileAttack.setForeground(COLOR_TEXT_DARK);
        profileDefense.setForeground(COLOR_TEXT_DARK);
        profileSpeed.setForeground(COLOR_TEXT_DARK);
        profileSkill1.setForeground(COLOR_TEXT_DARK);
        profileSkill2.setForeground(COLOR_TEXT_DARK);
        profileUltimate.setForeground(COLOR_TEXT_DARK);

        panel.add(createHeading("Hero Details"));
        panel.add(Box.createVerticalStrut(12));
        panel.add(createStatLine("Name", profileName));
        panel.add(createStatLine("Class", profileClass));
        panel.add(createStatLine("Weapon", profileWeapon));
        panel.add(createStatLine("Level", profileLevel));
        panel.add(createStatLine("Attack", profileAttack));
        panel.add(createStatLine("Defense", profileDefense));
        panel.add(createStatLine("Speed", profileSpeed));
        panel.add(createStatLine("Skill 1", profileSkill1));
        panel.add(createStatLine("Skill 2", profileSkill2));
        panel.add(createStatLine("Ultimate", profileUltimate));
        panel.add(Box.createVerticalStrut(18));

        JButton backButton = createSecondaryButton("Back to Main Menu");
        backButton.addActionListener(event -> {
            transitionManager.runTransition(frame,
                    () -> {
                        subtitleLabel.setText("Adventure overview.");
                        refreshHeroDashboard();
                        showScreen(SCREEN_MAIN);
                    },
                    null);
        });

        panel.add(backButton);
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel buildHeroSummaryCard() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(COLOR_PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                BorderFactory.createEmptyBorder(14, 14, 14, 14)));

        heroNameValue.setFont(getBodyFont(20f).deriveFont(Font.BOLD, 20f));
        heroNameValue.setForeground(COLOR_TEXT_DARK);
        heroClassValue.setFont(getBodyFont(14f));
        heroClassValue.setForeground(COLOR_TEXT_MUTED);
        heroLevelValue.setFont(getBodyFont(14f));
        heroLevelValue.setForeground(COLOR_TEXT_MUTED);
        heroGoldValue.setFont(getBodyFont(14f));
        heroGoldValue.setForeground(COLOR_TEXT_MUTED);

        hpBar.setStringPainted(true);
        hpBar.setForeground(COLOR_HERO_HP); // red health bar
        hpBar.setBackground(Color.decode("#14215A"));
        hpBar.setFont(getBodyFont(14f));
        manaBar.setStringPainted(true);
        manaBar.setForeground(COLOR_HERO_MANA); // blue mana bar
        manaBar.setBackground(Color.decode("#14215A"));
        manaBar.setFont(getBodyFont(14f));

        panel.add(heroNameValue);
        panel.add(Box.createVerticalStrut(4));
        heroClassValue.setFont(getBodyFont(14f));
        heroLevelValue.setFont(getBodyFont(14f));
        heroGoldValue.setFont(getBodyFont(14f));
        panel.add(heroClassValue);
        panel.add(heroLevelValue);
        panel.add(heroGoldValue);
        panel.add(Box.createVerticalStrut(10));
        JLabel healthText = new JLabel("Health");
        healthText.setFont(getBodyFont(14f).deriveFont(Font.BOLD, 14f));
        healthText.setForeground(COLOR_TEXT_DARK);
        panel.add(healthText);
        panel.add(hpBar);
        panel.add(Box.createVerticalStrut(8));
        JLabel manaText = new JLabel("Mana");
        manaText.setFont(getBodyFont(14f).deriveFont(Font.BOLD, 14f));
        manaText.setForeground(COLOR_TEXT_DARK);
        panel.add(manaText);
        panel.add(manaBar);

        return panel;
    }

    private String getCurrentLocationForHero() {
        if (hero == null) {
            return "No hero selected";
        }
        if (hero.hasUnlockedArea3()) {
            return "Forsaken Lands";
        }
        if (hero.hasUnlockedArea2()) {
            return "Reverie's Edge";
        }
        if (hero.hasUnlockedArea1()) {
            return "Forest of Reverie";
        }
        if (hero.hasVisitedAcademy()) {
            return "Mystvale Academy";
        }
        return "Academy";
    }

    private String getAdventureProgressText() {
        if (hero == null) {
            return "No data available. Choose a hero.";
        }

        int trainingCompleted = 0;
        trainingCompleted += hero.hasFinishedEndurance() ? 1 : 0;
        trainingCompleted += hero.hasFinishedStrength() ? 1 : 0;
        trainingCompleted += hero.hasFinishedDurability() ? 1 : 0;
        trainingCompleted += hero.hasFinishedManaRefinement() ? 1 : 0;

        StringBuilder sb = new StringBuilder("<html>");
        sb.append("Training: ").append(trainingCompleted).append(" / 4 completed");
        sb.append("<br>");

        sb.append("Unlocked: ");
        sb.append(hero.hasUnlockedArea3() ? "Forsaken Lands"
                : hero.hasUnlockedArea2() ? "Reverie's Edge"
                        : hero.hasUnlockedArea1() ? "Forest of Reverie" : "Mystvale Academy");
        sb.append("<br>");

        sb.append("Boss progress: ");
        sb.append(hero.getHaveDefeatedArea1Boss() ? "Forest boss defeated" : "Forest boss alive");
        sb.append(", ");
        sb.append(hero.getHaveDefeatedArea2Boss() ? "Reverie's Edge boss defeated" : "Reverie's Edge boss alive");
        sb.append("<br>");

        sb.append("Gold: ").append(statFormat.format(hero.getGold()));
        sb.append("<br>");
        sb.append("Level: ").append(hero.getLevel());
        sb.append("</html>");

        String content = sb.toString();
        if (content.startsWith("<html>")) {
            content = content.substring(6, content.length() - 7);
        }
        return styleHtml(content);
    }

    // Syncs journey status section sa main screen + the current hero's progress.
    private void refreshJourneyStatus() {
        if (hero == null) {
            journeyLocationLabel.setText(styleRaw("Current location: No hero selected"));
            journeyCompletionLabel.setText(styleRaw("No hero chosen yet."));
            journeyObjectivesLabel.setText(styleRaw("No objectives yet. Choose a hero to start."));
            return;
        }

        journeyLocationLabel.setText(styleRaw("Current location: " + getCurrentLocationForHero()));
        journeyCompletionLabel.setText(getCompletionStatusForHero());
        journeyObjectivesLabel.setText(getAdventureObjectivesText());
    }

    private String getCompletionStatusForHero() {
        if (hero == null) {
            return "No hero chosen yet.";
        }

        int trainingCompleted = 0;
        trainingCompleted += hero.hasFinishedEndurance() ? 1 : 0;
        trainingCompleted += hero.hasFinishedStrength() ? 1 : 0;
        trainingCompleted += hero.hasFinishedDurability() ? 1 : 0;
        trainingCompleted += hero.hasFinishedManaRefinement() ? 1 : 0;

        String unlockedAreaName = hero.hasUnlockedArea3() ? "Forsaken Lands"
                : hero.hasUnlockedArea2() ? "Reverie's Edge"
                        : hero.hasUnlockedArea1() ? "Forest of Reverie"
                                : hero.hasVisitedAcademy() ? "Mystvale Academy" : "Academy";

        int bossesDefeated = (hero.getHaveDefeatedArea1Boss() ? 1 : 0) + (hero.getHaveDefeatedArea2Boss() ? 1 : 0)
                + (hero.getHaveDefeatedArea3Boss() ? 1 : 0);

        String content = "Completion: " + trainingCompleted + " / 4 training completed<br>Unlocked: " + unlockedAreaName
                + "<br>Bosses defeated: " + bossesDefeated + " / 3";
        return styleHtml(content);
    }

    private String getAdventureObjectivesText() {
        if (hero == null) {
            return "No objectives yet. Choose a hero to start.";
        }

        StringBuilder sb = new StringBuilder("<html>");
        sb.append("Training progress: ");
        sb.append(hero.hasFinishedAllTraining() ? "All training tasks complete"
                : "Continue training through Academy and areas");
        sb.append("<br>");

        sb.append("Area unlocks: ");
        sb.append(hero.hasUnlockedArea3() ? "Forsaken Lands"
                : hero.hasUnlockedArea2() ? "Reverie's Edge"
                        : hero.hasUnlockedArea1() ? "Forest of Reverie" : "Mystvale Academy");
        sb.append("<br>");

        sb.append("Next boss: ");
        if (!hero.getHaveDefeatedArea1Boss()) {
            sb.append("Forest boss (area 1)");
        } else if (!hero.getHaveDefeatedArea2Boss()) {
            sb.append("Reverie's Edge boss (area 2)");
        } else if (!hero.getHaveDefeatedArea3Boss()) {
            sb.append("Forsaken Lands boss (area 3)");
        } else {
            sb.append("All bosses defeated");
        }

        sb.append("</html>");

        String content = sb.toString();
        if (content.startsWith("<html>")) {
            content = content.substring(6, content.length() - 7);
        }
        return styleHtml(content);
    }

    private JPanel createInventoryRow(String name, JLabel countLabel, Runnable action) {
        JPanel row = new JPanel(new BorderLayout(12, 0));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        row.setBackground(COLOR_PANEL);
        row.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setForeground(COLOR_TEXT_DARK);
        countLabel.setForeground(COLOR_TEXT_DARK);
        countLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton useButton = createSecondaryButton("Use");
        useButton.setPreferredSize(new Dimension(84, 30));
        useButton.addActionListener(event -> action.run());

        JPanel right = new JPanel(new GridLayout(1, 2, 10, 0));
        right.setOpaque(false);
        right.add(countLabel);
        right.add(useButton);

        row.add(nameLabel, BorderLayout.CENTER);
        row.add(right, BorderLayout.EAST);
        return row;
    }

    private JPanel createShopRow(String name, int price, int itemIndex) {
        JPanel row = new JPanel(new BorderLayout(10, 6));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        row.setBackground(COLOR_PANEL);
        row.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));

        JLabel nameLabel = new JLabel(name + "  |  " + statFormat.format(price) + " gold each");
        nameLabel.setForeground(COLOR_TEXT_DARK);
        row.add(nameLabel, BorderLayout.NORTH);

        JPanel actions = new JPanel(new GridLayout(2, 2, 8, 6));
        actions.setOpaque(false);

        int[] quantities = { 1, 5, 10, 20 };
        for (int quantity : quantities) {
            JButton buyButton = createSecondaryButton("Buy x" + quantity);
            buyButton.setPreferredSize(new Dimension(150, 34));
            buyButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
            buyButton.addActionListener(event -> buyShopItem(itemIndex, quantity));
            actions.add(buyButton);
        }

        row.add(actions, BorderLayout.CENTER);
        return row;
    }

    private JPanel createStatLine(String label, JLabel value) {
        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));

        JLabel name = new JLabel(label);
        name.setForeground(COLOR_TEXT_MUTED);

        value.setHorizontalAlignment(SwingConstants.RIGHT);
        value.setForeground(COLOR_TEXT_DARK);

        row.add(name, BorderLayout.WEST);
        row.add(value, BorderLayout.EAST);
        return row;
    }

    private JPanel createCharacterButton(String title, String description, Runnable action) {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBackground(COLOR_CHARSEL_PANEL);
        wrapper.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)));
        wrapper.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(getHeadingFont(22f));
        titleLabel.setForeground(COLOR_CHARSEL_TEXT_DARK);

        JLabel bodyLabel = createBody("<html>" + description + "</html>");
        bodyLabel.setForeground(COLOR_CHARSEL_TEXT_MUTED);

        // Add the character image here for each selection card.
        // Example:
        // JLabel imageLabel = new JLabel(new
        // ImageIcon("assets/images/characters/swordsman.png"));
        // imageLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        // wrapper.add(imageLabel);
        // wrapper.add(Box.createVerticalStrut(10));
        // If you want different images per hero, update each createCharacterButton(...)
        // call in buildCharacterScreen() and pass the correct image path into this
        // method.

        JButton chooseButton = createCharacterSelectButton("Play " + title);
        chooseButton.addActionListener(event -> action.run());

        wrapper.add(titleLabel);
        wrapper.add(Box.createVerticalStrut(6));
        if (description != null && !description.isBlank()) {
            wrapper.add(bodyLabel);
            wrapper.add(Box.createVerticalStrut(10));
        } else {
            wrapper.add(Box.createVerticalStrut(2));
        }
        wrapper.add(chooseButton);
        return wrapper;
    }

    private JButton createCharacterSelectButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics graphics) {
                Graphics2D graphics2D = (Graphics2D) graphics.create();
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                ButtonModel model = getModel();
                Color fill = new Color(118, 81, 53);
                if (model.isPressed()) {
                    fill = new Color(96, 65, 42);
                } else if (model.isRollover()) {
                    fill = new Color(132, 92, 62);
                }
                graphics2D.setColor(fill);
                graphics2D.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 14, 14);
                graphics2D.dispose();
                super.paintComponent(graphics);
            }

            @Override
            protected void paintBorder(Graphics graphics) {
                Graphics2D graphics2D = (Graphics2D) graphics.create();
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics2D.setColor(new Color(90, 62, 41));
                graphics2D.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 14, 14);
                graphics2D.dispose();
            }
        };
        button.setFont(getBodyFont(14f).deriveFont(Font.BOLD, 14f));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(118, 81, 53));
        button.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        button.setRolloverEnabled(true);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        return button;
    }

    private JPanel createCardPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        panel.setBackground(COLOR_PANEL);
        panel.setOpaque(true);
        return panel;
    }

    private Font getHeadingFont(float size) {
        if (headingFont != null) {
            return headingFont.deriveFont(Font.BOLD, size);
        }
        return new Font("Serif", Font.BOLD, Math.round(size));
    }

    private Font getBodyFont(float size) {
        if (bodyFont != null) {
            return bodyFont.deriveFont(Font.PLAIN, size);
        }
        return new Font("SansSerif", Font.PLAIN, Math.round(size));
    }

    private String styleHtml(String htmlBody) {
        String family = bodyFont != null ? bodyFont.getFamily() : "SansSerif";
        int size = Math.round(bodyFontSize);
        String cleaned = htmlBody;
        if (cleaned.startsWith("<html>")) {
            cleaned = cleaned.substring(6);
        }
        if (cleaned.endsWith("</html>")) {
            cleaned = cleaned.substring(0, cleaned.length() - 7);
        }
        return "<html><div style='font-family: " + family + "; font-size: " + size + "pt;'>" + cleaned
                + "</div></html>";
    }

    private JLabel createHeading(String text) {
        JLabel label = new JLabel(text);
        label.putClientProperty("fontRole", "heading");
        label.setFont(getHeadingFont(headingFontSize));
        label.setForeground(COLOR_TEXT_DARK);
        return label;
    }

    private String styleRaw(String text) {
        String family = bodyFont != null ? bodyFont.getFamily() : "SansSerif";
        int size = Math.round(bodyFontSize);
        return "<html><div style='font-family: " + family + "; font-size: " + size + "pt;'>" + text + "</div></html>";
    }

    private JLabel createBody(String text) {
        JLabel label = new JLabel(styleRaw(text));
        label.putClientProperty("fontRole", "body");
        label.setFont(getBodyFont(bodyFontSize));
        label.setForeground(COLOR_TEXT_MUTED);
        return label;
    }

    private JButton createPrimaryButton(String text) {
        return createStyledButton(
                text,
                primaryButtonSkin,
                new Color(118, 81, 53),
                Color.WHITE,
                getBodyFont(14f).deriveFont(Font.BOLD, 14f),
                42);
    }

    private JButton createSecondaryButton(String text) {
        return createStyledButton(
                text,
                secondaryButtonSkin,
                new Color(118, 81, 53),
                Color.WHITE,
                getBodyFont(13f).deriveFont(Font.BOLD, 13f),
                40);
    }

    private JButton createBattleButton(String text) {
        return createStyledButton(
                text,
                null,
                COLOR_BATTLE_SURFACE,
                Color.WHITE,
                getBodyFont(15f).deriveFont(Font.BOLD, 15f),
                58);
    }

    private JButton createStyledButton(String text, ButtonSkin skin, Color fallbackColor, Color textColor, Font font,
            int height) {
        PixelButton button = new PixelButton(text, skin, fallbackColor);
        button.setForeground(textColor);
        button.setFont(font);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        button.setMinimumSize(new Dimension(160, height));
        button.setPreferredSize(new Dimension(220, height));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        return button;
    }

    private ButtonSkin loadButtonSkin(String baseName) {
        // Button skins are optional. If only the normal PNG exists, Swing still uses it
        // and falls back to the default state colors for anything missing.
        BufferedImage normal = loadImageAsset(UI_IMAGE_DIRECTORY + baseName + ".png");
        BufferedImage hover = loadImageAsset(UI_IMAGE_DIRECTORY + baseName + "-hover.png");
        BufferedImage pressed = loadImageAsset(UI_IMAGE_DIRECTORY + baseName + "-pressed.png");
        BufferedImage disabled = loadImageAsset(UI_IMAGE_DIRECTORY + baseName + "-disabled.png");
        return new ButtonSkin(normal, hover, pressed, disabled);
    }

    private BufferedImage loadImageAsset(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (Exception ignored) {
            return null;
        }
    }

    private BufferedImage loadLandingBackground() {
        // Prefer classpath resource so the image works no matter the run directory.
        try (InputStream classpathStream = getClass()
                .getResourceAsStream("/com/ror/models/assets/images/landing-background-source.jpg")) {
            if (classpathStream != null) {
                BufferedImage image = ImageIO.read(classpathStream);
                if (image != null) {
                    return image;
                }
            }
        } catch (Exception ignored) {
        }
        try (InputStream classpathStream = getClass().getResourceAsStream("/com/ror/models/assets/images/title.png")) {
            if (classpathStream != null) {
                BufferedImage image = ImageIO.read(classpathStream);
                if (image != null) {
                    return image;
                }
            }
        } catch (Exception ignored) {
        }

        // Fallbacks for file-based runs in IDEs with different working directories.
        String[] candidates = {
                "src/com/ror/models/assets/images/landing-background-source.jpg",
                "assets/images/landing-background-source.jpg",
                "src/com/ror/models/assets/images/title.png",
                "assets/images/title.png"
        };

        for (String candidate : candidates) {
            BufferedImage image = loadImageAsset(candidate);
            if (image != null) {
                return image;
            }
        }

        return null;
    }

    // Font loader for custom fonts
    private void loadFonts() {
        try (InputStream cinzelStream = getClass()
                .getResourceAsStream("/com/ror/models/assets/fonts/Cinzel-VariableFont_wght.ttf");
                InputStream garamondStream = getClass()
                        .getResourceAsStream("/com/ror/models/assets/fonts/EBGaramond-VariableFont_wght.ttf")) {

            // Loading and registration for heading font.
            if (cinzelStream != null) {
                Font loadedHeading = Font.createFont(Font.TRUETYPE_FONT, cinzelStream).deriveFont(Font.BOLD,
                        headingFontSize);
                headingFont = loadedHeading;
                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(loadedHeading);
            }

            // Loading and registration for body font.
            if (garamondStream != null) {
                Font loadedBody = Font.createFont(Font.TRUETYPE_FONT, garamondStream).deriveFont(Font.PLAIN,
                        bodyFontSize);
                bodyFont = loadedBody;
                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(loadedBody);
            }

            // Apply as global font(didn't work for all components rip).
            applyGlobalUIFont();

        } catch (Exception e) {
            // Fallback if wala ang font.
            headingFont = new Font("Serif", Font.BOLD, Math.round(headingFontSize));
            bodyFont = new Font("SansSerif", Font.PLAIN, Math.round(bodyFontSize));
            applyGlobalUIFont();
        }
    }

    public void setGameFonts(Font heading, Font body) {
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

    public void setGameFontSizes(float headingSize, float bodySize) {
        headingFontSize = headingSize;
        bodyFontSize = bodySize;
        if (headingFont != null) {
            headingFont = headingFont.deriveFont(Font.BOLD, headingSize);
        }
        if (bodyFont != null) {
            bodyFont = bodyFont.deriveFont(Font.PLAIN, bodySize);
        }
        applyGlobalUIFont();
        applyFontsToAllComponents(rootPanel);
    }

    // Brute force method for font applications (ngano di consistent ang UIManager
    // font application sa components???)
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
                if ("heading".equals(role)) {
                    label.setFont(getHeadingFont(headingFontSize));
                } else {
                    label.setFont(getBodyFont(bodyFontSize));
                }

                String text = label.getText();
                if (text != null && text.startsWith("<html>")) {
                    String inner = text.substring(6, text.length() - 7);
                    label.setText(styleHtml(inner));
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

    private void updateEnemySprite(Entity enemy) {
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

    private BufferedImage loadEnemySprite(Entity enemy) {
        if (enemy == null) {
            return null;
        }

        if (enemy instanceof Goblin) {
            return loadImageAsset(ENEMY_IMAGE_DIRECTORY + "goblin.png");
        }

        return null;
    }

    private javax.swing.Icon createScaledSpriteIcon(BufferedImage sprite, int targetWidth, int targetHeight) {
        BufferedImage scaled = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = scaled.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        // STARE O_O balik nasad ta ani dim math

        int drawWidth = sprite.getWidth();
        int drawHeight = sprite.getHeight();
        double scale = Math.min((double) targetWidth / Math.max(1, drawWidth),
                (double) targetHeight / Math.max(1, drawHeight));
        int scaledWidth = Math.max(1, (int) Math.round(drawWidth * scale));
        int scaledHeight = Math.max(1, (int) Math.round(drawHeight * scale));
        int x = (targetWidth - scaledWidth) / 2;
        int y = (targetHeight - scaledHeight) / 2;

        graphics2D.drawImage(sprite, x, y, scaledWidth, scaledHeight, null);
        graphics2D.dispose();
        return new javax.swing.ImageIcon(scaled);
    }

    // for title screen only
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
            if (!model.isEnabled()) {
                return disabled != null ? disabled : normal;
            }
            if (model.isPressed()) {
                if (pressed != null) {
                    return pressed;
                }
                if (hover != null) {
                    return hover;
                }
            }
            if (rollover && hover != null) {
                return hover;
            }
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
            Graphics2D graphics2D = (Graphics2D) graphics.create();

            if (skin != null && skin.hasAnyImage()) {
                BufferedImage stateImage = skin.resolve(getModel(), rollover);
                if (stateImage != null) {
                    // Pixel art should stay crisp, so we scale with nearest-neighbor
                    // instead of the default smoothing used for photos.
                    graphics2D.setRenderingHint(
                            RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
                    graphics2D.drawImage(stateImage, 0, 0, getWidth(), getHeight(), null);
                }
            } else {
                graphics2D.setColor(resolveFallbackColor());
                graphics2D.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            }

            graphics2D.dispose();
            super.paintComponent(graphics);
        }

        private Color resolveFallbackColor() {
            if (!isEnabled()) {
                return fallbackColor.darker();
            }
            if (getModel().isPressed()) {
                return fallbackColor.darker();
            }
            if (rollover) {
                return fallbackColor.brighter();
            }
            return fallbackColor;
        }
    }

    private JPanel buildLandingScreen() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);

                Graphics2D graphics2D = (Graphics2D) graphics.create();
                // Temporarily use a plain backdrop with no image.
                graphics2D.setColor(new Color(18, 12, 10));
                graphics2D.fillRect(0, 0, getWidth(), getHeight());
                graphics2D.dispose();
            }
        };

        panel.setLayout(new GridBagLayout());

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setMaximumSize(new Dimension(760, Integer.MAX_VALUE));

        JLabel eyebrow = new JLabel("REALMS OF RIFTBORNE");
        eyebrow.setFont(getBodyFont(18f).deriveFont(Font.BOLD, 18f));
        eyebrow.setForeground(new Color(234, 190, 137));
        eyebrow.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JLabel title = new JLabel("Mystvale Academy");
        title.setFont(getHeadingFont(56f));
        title.setForeground(new Color(255, 238, 216));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JLabel body = new JLabel(
                "<html><div style='text-align:center;'>Defeat Kim Morvain and escape Mystvale Academy.</div></html>");
        body.setFont(getBodyFont(18f));
        body.setForeground(new Color(232, 219, 208));
        body.setHorizontalAlignment(SwingConstants.CENTER);
        body.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        body.setMaximumSize(new Dimension(760, 50));

        JButton startButton = createPrimaryButton("Play");
        startButton.setPreferredSize(new Dimension(220, 46));
        startButton.setMaximumSize(new Dimension(220, 46));
        startButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        startButton.addActionListener(event -> openCharacterSelectFromLanding());

        JButton exitButton = createSecondaryButton("Exit");
        exitButton.setPreferredSize(new Dimension(220, 46));
        exitButton.setMaximumSize(new Dimension(220, 46));
        exitButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        exitButton.addActionListener(event -> frame.dispose());

        content.add(Box.createVerticalGlue());
        content.add(eyebrow);
        content.add(Box.createVerticalStrut(10));
        content.add(title);
        content.add(Box.createVerticalStrut(14));
        content.add(body);
        content.add(Box.createVerticalStrut(26));
        JPanel landingButtons = new JPanel(new GridLayout(1, 2, 26, 0));
        landingButtons.setOpaque(false);
        landingButtons.setMaximumSize(new Dimension(470, 46));
        landingButtons.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        landingButtons.add(startButton);
        landingButtons.add(exitButton);

        content.add(landingButtons);
        content.add(Box.createVerticalGlue());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(content, constraints);
        return panel;
    }

    private void wireConsoleStreams() {
        System.setIn(gameInputStream);

        PrintStream guiOut = new PrintStream(
                new BufferedOutputStream(
                        new TextAreaOutputStream(outputArea, this::filterLegacyOutput, this::observeLegacyOutput), 1),
                true,
                StandardCharsets.UTF_8);

        System.setOut(guiOut);
        System.setErr(guiOut);
    }

    private void selectHero(Hero selectedHero) {
        hero = selectedHero;
        inventoryNarrationShown = false;
        hero.setHpCap(hero.getHp());
        hero.setManaCap(hero.getMana());
        refreshHeroDashboard();
        refreshInventoryPanel();
        refreshProfilePanel();
        appendLog("\nHero selected: " + hero.getName() + " the " + hero.getCharClass() + ".\n");
        beginStoryForHero(true);
    }

    private void refreshHeroDashboard() {
        if (hero == null) {
            heroNameValue.setText("-");
            heroClassValue.setText("-");
            heroLevelValue.setText("-");
            heroGoldValue.setText("-");
            hpBar.setMaximum(1);
            hpBar.setValue(0);
            hpBar.setString("0 / 0");
            manaBar.setMaximum(1);
            manaBar.setValue(0);
            manaBar.setString("0 / 0");
            refreshJourneyStatus();
            return;
        }

        heroNameValue.setText(hero.getName());
        heroClassValue.setText("Class: " + hero.getCharClass());
        heroLevelValue.setText("Level: " + hero.getLevel());
        heroGoldValue.setText("Gold: " + statFormat.format(hero.getGold()));

        hpBar.setMaximum(Math.max(1, hero.getHpCap()));
        hpBar.setValue(Math.max(0, hero.getHp()));
        hpBar.setString(statFormat.format(hero.getHp()) + " / " + statFormat.format(hero.getHpCap()));

        manaBar.setMaximum(Math.max(1, hero.getManaCap()));
        manaBar.setValue(Math.max(0, hero.getMana()));
        manaBar.setString(statFormat.format(hero.getMana()) + " / " + statFormat.format(hero.getManaCap()));

        refreshAreaButtons();
        refreshJourneyStatus();
    }

    private void beginStoryForHero(boolean firstTimeSelection) {
        if (hero == null) {
            return;
        }

        currentStorySequence = buildIntroStoryForHero(hero);
        currentStoryIndex = 0;
        storyTitleLabel.setText(firstTimeSelection ? "Opening Story" : "Storyline Recap");
        subtitleLabel.setText(firstTimeSelection ? "Story begins." : "Review your hero's storyline.");
        showCurrentStoryBeat();
        showScreen(SCREEN_STORY);
    }

    private void showCurrentStoryBeat() {
        if (currentStorySequence.length == 0) {
            finishStorySequence("No story scenes are available for this hero yet.");
            return;
        }

        int safeIndex = Math.max(0, Math.min(currentStoryIndex, currentStorySequence.length - 1));
        storyProgressLabel.setText("Scene " + (safeIndex + 1) + " / " + currentStorySequence.length);
        startStoryTypewriter(currentStorySequence[safeIndex]);

        boolean isLastScene = safeIndex >= currentStorySequence.length - 1;
        storyNextButton.setVisible(!isLastScene);
        storyStartButton.setVisible(isLastScene);
    }

    private void advanceStoryBeat() {
        if (storyTypewriterTimer != null && storyTypewriterTimer.isRunning()) {
            completeStoryTypewriter();
            return;
        }

        if (currentStoryIndex < currentStorySequence.length - 1) {
            currentStoryIndex++;
            showCurrentStoryBeat();
        } else {
            finishStorySequence("Story complete. Your journey continues.");
        }
    }

    private void finishStorySequence(String logMessage) {
        if (storyTypewriterTimer != null) {
            storyTypewriterTimer.stop();
            storyTypewriterTimer = null;
        }
        subtitleLabel.setText("Adventure overview.");
        refreshHeroDashboard();
        showScreen(SCREEN_MAIN);
        appendLog("\n" + logMessage + "\n");
    }

    private void startStoryTypewriter(String fullText) {
        if (storyTypewriterTimer != null) {
            storyTypewriterTimer.stop();
        }

        currentStoryLine = fullText == null ? "" : fullText;
        storyTextArea.setText("");
        storyTextArea.setCaretPosition(0);

        if (currentStoryLine.isEmpty()) {
            return;
        }

        final int[] index = { 0 };
        storyTypewriterTimer = new Timer(STORY_TYPEWRITER_DELAY_MS, event -> {
            if (index[0] >= currentStoryLine.length()) {
                ((Timer) event.getSource()).stop();
                return;
            }

            index[0]++;
            storyTextArea.setText(currentStoryLine.substring(0, index[0]));
            storyTextArea.setCaretPosition(storyTextArea.getDocument().getLength());
        });
        storyTypewriterTimer.start();
    }

    private void completeStoryTypewriter() {
        if (storyTypewriterTimer != null) {
            storyTypewriterTimer.stop();
        }
        storyTextArea.setText(currentStoryLine);
        storyTextArea.setCaretPosition(storyTextArea.getDocument().getLength());
    }

    private String[] buildIntroStoryForHero(Hero selectedHero) {
        String prologue = "Your days as a student have been a blur of half-hearted effort and quiet frustration. " +
                "One restless night, sleep gives way to something stranger. You wake beneath a pale moon, " +
                "facing a vast academy beyond the trees. A wandering spirit named Void Aetherlight " +
                "greets you and warns that Mystvale Academy is both sanctuary and trial. To survive, " +
                "you must grow stronger, uncover the truth behind Kim Morvain, and decide what kind of hero you will become.";

        String academy = "Mystvale Academy stands at the edge of danger. Within its halls lie the Library, the Training Ground, "
                +
                "the Principal's Office, and the paths leading toward the Forest of Reverie, Reverie's Edge, and the Forsaken Lands. "
                +
                "Every step forward reveals more than monsters. The academy hides wounds, secrets, and a fate tied to your chosen hero.";

        if (selectedHero instanceof Swordsman) {
            return new String[] {
                    prologue,
                    academy,
                    "Kael Solmere carries the burden of a bloodline stained by betrayal. The Solmere family once guarded a forbidden power tied to Kim Morvain, "
                            +
                            "and the curse left behind still follows their name. Kael enters Mystvale to become stronger, protect the people he loves, and uncover the truth hidden in his family's shadow.",
                    "For Kael, this journey is not just about defeating enemies. It is about confronting legacy, loyalty, and the darkness woven into his own blood. "
                            +
                            "Every battle at Mystvale brings him closer to either breaking the curse or becoming part of it."
            };
        }

        if (selectedHero instanceof Gunner) {
            return new String[] {
                    prologue,
                    academy,
                    "Aria Caelith was stolen from home and forced into Project LUCENT, a cruel experiment designed by Kim Morvain. "
                            +
                            "The trials gave Aria terrifying precision and Aether-fueled power, but they also carved pain deep into body and memory.",
                    "Aria arrives in Mystvale carrying anger, survival instinct, and unfinished vengeance. Each step through the academy is a step toward the truth behind Project LUCENT "
                            +
                            "and a reckoning with the man who tried to turn Aria into a weapon."
            };
        }

        return new String[] {
                prologue,
                academy,
                "Selene Arclight was born with brilliance in cosmic magic, but her gift drew her into Kim Morvain's schemes. "
                        +
                        "She helped shape dangerous magic before discovering the horror it would unleash. By the time she tried to escape, a curse had already marked her.",
                "Selene now walks Mystvale with guilt, pride, and a fierce need to set things right. The road ahead is filled with spells, sacrifice, and buried truths. "
                        +
                        "To end Morvain's reign, she must face both the enemy before her and the regret still burning inside."
        };
    }

    private void refreshAreaButtons() {
        if (forestButton == null || swampButton == null || forsakenButton == null) {
            return;
        }

        boolean hasHero = hero != null;
        forestButton.setEnabled(hasHero && hero.hasUnlockedArea1());
        swampButton.setEnabled(hasHero && hero.hasUnlockedArea2());
        forsakenButton.setEnabled(hasHero && hero.hasUnlockedArea3());

        forestButton.setToolTipText(
                hasHero && !hero.hasUnlockedArea1() ? "Unlock through the Principal Office flow." : null);
        swampButton.setToolTipText(
                hasHero && !hero.hasUnlockedArea2() ? "Unlock through the Principal Office flow." : null);
        forsakenButton.setToolTipText(
                hasHero && !hero.hasUnlockedArea3() ? "Unlock through the Principal Office flow." : null);
    }

    private void refreshInventoryPanel() {
        if (hero == null) {
            inventorySummary.setText("No hero selected.");
            smallHealthCount.setText("0");
            mediumHealthCount.setText("0");
            largeHealthCount.setText("0");
            smallManaCount.setText("0");
            mediumManaCount.setText("0");
            largeManaCount.setText("0");
            shopGoldLabel.setText("Gold: -");
            shopStatusLabel.setText("Choose a hero first.");
            return;
        }

        Inventory inventory = hero.getInventory();
        inventorySummary.setText(
                "HP " + statFormat.format(hero.getHp()) + "/" + statFormat.format(hero.getHpCap()) +
                        "  |  Mana " + statFormat.format(hero.getMana()) + "/" + statFormat.format(hero.getManaCap()));
        smallHealthCount.setText(String.valueOf(inventory.getSmallHealthPotion()));
        mediumHealthCount.setText(String.valueOf(inventory.getMediumHealthPotion()));
        largeHealthCount.setText(String.valueOf(inventory.getLargeHealthPotion()));
        smallManaCount.setText(String.valueOf(inventory.getSmallManaPotion()));
        mediumManaCount.setText(String.valueOf(inventory.getMediumManaPotion()));
        largeManaCount.setText(String.valueOf(inventory.getLargeManaPotion()));
        shopGoldLabel.setText("Gold: " + statFormat.format(hero.getGold()));
    }

    private void refreshProfilePanel() {
        if (hero == null) {
            return;
        }

        profileName.setText(hero.getName());
        profileClass.setText(hero.getCharClass());
        profileWeapon.setText(hero.getWeapon());
        profileLevel.setText(String.valueOf(hero.getLevel()));
        profileAttack.setText(statFormat.format(hero.getAttack()));
        profileDefense.setText(statFormat.format(hero.getDefense()));
        profileSpeed.setText(statFormat.format(hero.getSpeed()));
        profileSkill1.setText(hero.getSkill1());
        profileSkill2.setText(hero.getSkill2());
        profileUltimate.setText(hero.getUltimate());
    }

    private void openLibraryGui() {
        if (!requireHero()) {
            return;
        }

        boolean firstVisit = !hero.hasVisitedLibrary();
        hero.setVisitedLibrary(true);
        if (firstVisit) {
            playNarrationSequence("Library Narration", buildLibraryNarration());
        }

        boolean hasQuest1 = !hero.isLibraryQuest1Done();
        boolean hasQuest2 = !hero.isLibraryQuest2Done();

        if (!hasQuest1 && !hasQuest2) {
            showInfoSync("Library", "All library quests are already completed.");
            return;
        }

        int questChoice;
        if (hasQuest1 && hasQuest2) {
            Object[] options = { "Lost Book Quest", "Riddle Quest", "Back" };
            questChoice = showOptionSync(
                    "Library",
                    "Choose a library quest.",
                    options,
                    options[0]);
        } else if (hasQuest1) {
            questChoice = 0;
        } else {
            questChoice = 1;
        }

        if (questChoice == 0 && hasQuest1) {
            int accept = showConfirmSync(
                    "Library Quest",
                    "Accept 'Find the Lost Book' quest?");
            if (accept == 0) {
                hero.setLibraryQuest1Done(true);
                grantRewards(800, 120, "Lost Book quest complete!");
            }
            return;
        }

        if (questChoice == 1 && hasQuest2) {
            int accept = showConfirmSync(
                    "Library Quest",
                    "Accept 'Riddles of the Library' quest?");
            if (accept != 0) {
                return;
            }

            boolean r1 = askRiddle(
                    "Riddle 1: I have pages, but I am not a tree. What am I?",
                    new Object[] { "Book", "Sword", "Lantern" },
                    0);
            hero.setRiddle1Done(r1);

            boolean r2 = askRiddle(
                    "Riddle 2: I am spent when used to light the way. What am I?",
                    new Object[] { "Shadow", "Candle", "Stone" },
                    1);
            hero.setRiddle2Done(r2);

            boolean r3 = askRiddle(
                    "Riddle 3: What grows stronger the more you train?",
                    new Object[] { "Luck", "Discipline", "Silence" },
                    1);
            hero.setRiddle3Done(r3);

            if (hero.isAllRiddlesDone()) {
                hero.setLibraryQuest2Done(true);
                grantRewards(1200, 220, "Riddle quest complete!");
            } else {
                showInfoSync(
                        "Library",
                        "Not all riddles were correct. Try the quest again.");
            }
        }
    }

    private void openTrainingGui() {
        if (!requireHero()) {
            return;
        }

        boolean firstVisit = !hero.hasVisitedGym();
        hero.setVisitedGym(true);
        if (firstVisit) {
            playNarrationSequence("Training Ground", buildTrainingNarration());
        }

        if (hero.hasFinishedAllTraining()) {
            showInfoSync("Training Ground", "Training is already complete.");
            return;
        }

        while (true) {
            Object[] options = {
                    hero.hasFinishedEndurance() ? "Endurance (Done)" : "Endurance",
                    hero.hasFinishedStrength() ? "Strength (Done)" : "Strength",
                    hero.hasFinishedDurability() ? "Durability (Done)" : "Durability",
                    hero.hasFinishedManaRefinement() ? "Mana Refinement (Done)" : "Mana Refinement",
                    "Quit Training"
            };

            int choice = showOptionSync(
                    "Training Ground",
                    "Complete all 4 trainings to unlock eligibility for Forest of Reverie.",
                    options,
                    options[0]);

            if (choice == 4 || choice == -1) {
                if (hero.getNumberOfTrainingFinished() > 0 && hero.getNumberOfTrainingFinished() < 4) {
                    int confirm = showConfirmSync(
                            "Training Ground",
                            "Quit now? Incomplete training progress will reset.");

                    if (confirm == 0) {
                        resetTrainingProgress();
                        showInfoSync("Training Ground", "Training progress reset.");
                        refreshHeroDashboard();
                        refreshProfilePanel();
                        return;
                    }
                    continue;
                }
                return;
            }

            if (choice == 0 && !hero.hasFinishedEndurance()) {
                resolveTrainingTask("Endurance", () -> hero.setFinishedEndurance(true));
            } else if (choice == 1 && !hero.hasFinishedStrength()) {
                resolveTrainingTask("Strength", () -> hero.setFinishedStrength(true));
            } else if (choice == 2 && !hero.hasFinishedDurability()) {
                resolveTrainingTask("Durability", () -> hero.setFinishedDurability(true));
            } else if (choice == 3 && !hero.hasFinishedManaRefinement()) {
                resolveTrainingTask("Mana Refinement", () -> hero.setFinishedManaRefinement(true));
            }

            if (hero.getNumberOfTrainingFinished() >= 4
                    && hero.hasFinishedEndurance()
                    && hero.hasFinishedStrength()
                    && hero.hasFinishedDurability()
                    && hero.hasFinishedManaRefinement()) {
                hero.setFinishedAllTraining(true);
                hero.unlockArea1(true);
                statProgress.endurance(hero);
                statProgress.strength(hero);
                statProgress.durability(hero);
                statProgress.mana(hero);
                grantRewards(2500, 500, "Training complete! You can now apply for Area 1 eligibility.");
                refreshHeroDashboard();
                refreshProfilePanel();
                return;
            }
        }
    }

    private void openShopGui() {
        if (!requireHero()) {
            return;
        }

        boolean firstVisit = !hero.hasVisitedShop();
        hero.setHasVisitedShop(true);
        if (firstVisit) {
            playNarrationSequence("Shop Narration", buildShopNarration());
            playNarrationSequence("Shopkeeper", buildShopConversationNarration());
        }
        subtitleLabel.setText("Browse the academy shop.");
        shopStatusLabel.setText("Choose an item and quantity.");
        refreshInventoryPanel();
        refreshHeroDashboard();
        showScreen(SCREEN_SHOP);
    }

    private void buyShopItem(int itemChoice, int requestedQuantity) {
        if (!requireHero()) {
            return;
        }

        Inventory inventory = hero.getInventory();
        int[] prices = { 450, 1350, 2750, 450, 1350, 2750 };
        String[] names = {
                "Small Health Potion",
                "Medium Health Potion",
                "Large Health Potion",
                "Small Mana Potion",
                "Medium Mana Potion",
                "Large Mana Potion"
        };

        int currentCount = getPotionCount(inventory, itemChoice);
        int availableCapacity = inventory.getCapacity() - currentCount;

        if (availableCapacity <= 0) {
            shopStatusLabel.setText(names[itemChoice] + " is already at max capacity.");
            return;
        }

        int quantity = Math.min(requestedQuantity, availableCapacity);
        int totalCost = prices[itemChoice] * quantity;

        if (hero.getGold() < totalCost) {
            shopStatusLabel.setText("Not enough gold for " + quantity + " " + names[itemChoice] + ".");
            return;
        }

        hero.setGold(hero.getGold() - totalCost);
        setPotionCount(inventory, itemChoice, currentCount + quantity);
        refreshInventoryPanel();
        refreshHeroDashboard();

        String cappedNote = quantity < requestedQuantity ? " Max capacity reached, so only " + quantity + " bought."
                : "";
        shopStatusLabel.setText(
                "Purchased " + quantity + " " + names[itemChoice] + " for " + statFormat.format(totalCost) + " gold."
                        + cappedNote);
    }

    private void openPrincipalOfficeGui() {
        if (!requireHero()) {
            return;
        }

        boolean firstVisit = !hero.hasVisitedOffice();
        hero.setVisitedOffice(true);
        if (firstVisit) {
            playNarrationSequence("Principal's Office", buildPrincipalOfficeNarration());
        }

        if (!hero.hasUnlockedArea1() && hero.canEnterArea1() && hero.hasFinishedAllTraining()) {
            playNarrationSequence("Eligibility Granted", buildArea1EligibilityNarration());
            hero.setUnlockArea1(true);
            grantRewards(2500, 500, "Eligibility granted: Forest of Reverie unlocked.");
            refreshHeroDashboard();
            return;
        }

        if (!hero.hasUnlockedArea2() && hero.canEnterArea2() && hero.getHaveDefeatedArea1Boss()) {
            playNarrationSequence("Eligibility Granted", buildArea2EligibilityNarration());
            hero.setUnlockArea2(true);
            grantRewards(2500, 500, "Eligibility granted: Reverie's Edge unlocked.");
            refreshHeroDashboard();
            return;
        }

        if (!hero.hasUnlockedArea3() && hero.canEnterArea3() && hero.getHaveDefeatedArea2Boss()) {
            playNarrationSequence("Eligibility Granted", buildArea3EligibilityNarration());
            hero.setUnlockArea3(true);
            grantRewards(2500, 500, "Eligibility granted: Forsaken Lands unlocked.");
            refreshHeroDashboard();
            return;
        }

        showInfoSync(
                "Principal's Office",
                "Not eligible yet.\nFinish training and defeat area bosses first.");
    }

    private void resolveTrainingTask(String taskName, Runnable markComplete) {
        int start = showConfirmSync(
                "Training Ground",
                "Start " + taskName + " challenge?");
        if (start != 0) {
            return;
        }

        boolean success = random.nextInt(10) <= 7;
        if (success) {
            markComplete.run();
            hero.setNumberOfTrainingFinished(hero.getNumberOfTrainingFinished() + 1);
            showInfoSync("Training Ground", taskName + " complete!");
        } else {
            showInfoSync("Training Ground", taskName + " failed. Try again.");
        }
    }

    private void resetTrainingProgress() {
        hero.setFinishedEndurance(false);
        hero.setFinishedStrength(false);
        hero.setFinishedDurability(false);
        hero.setFinishedManaRefinement(false);
        hero.setFinishedAllTraining(false);
        hero.setNumberOfTrainingFinished(0);
        hero.unlockArea1(false);
    }

    private int getPotionCount(Inventory inventory, int index) {
        return switch (index) {
            case 0 -> inventory.getSmallHealthPotion();
            case 1 -> inventory.getMediumHealthPotion();
            case 2 -> inventory.getLargeHealthPotion();
            case 3 -> inventory.getSmallManaPotion();
            case 4 -> inventory.getMediumManaPotion();
            case 5 -> inventory.getLargeManaPotion();
            default -> 0;
        };
    }

    private void setPotionCount(Inventory inventory, int index, int value) {
        switch (index) {
            case 0 -> inventory.setSmallHealthPotion(value);
            case 1 -> inventory.setMediumHealthPotion(value);
            case 2 -> inventory.setLargeHealthPotion(value);
            case 3 -> inventory.setSmallManaPotion(value);
            case 4 -> inventory.setMediumManaPotion(value);
            case 5 -> inventory.setLargeManaPotion(value);
            default -> {
            }
        }
    }

    private void grantRewards(int gold, int xp, String message) {
        hero.setGold(hero.getGold() + gold);
        hero.levelUp(xp);
        if (hero.getHp() > hero.getHpCap()) {
            hero.setHpCap(hero.getHp());
        }
        if (hero.getMana() > hero.getManaCap()) {
            hero.setManaCap(hero.getMana());
        }
        refreshHeroDashboard();
        refreshInventoryPanel();
        refreshProfilePanel();

        showInfoSync(
                "Rewards",
                message + "\nRewards: +" + statFormat.format(gold) + " gold, +" + statFormat.format(xp) + " XP.");
    }

    private boolean askRiddle(String question, Object[] options, int correctIndex) {
        int answer = showOptionSync(
                "Library Riddle",
                question,
                options,
                options[0]);

        return answer == correctIndex;
    }

    private void usePotion(String itemCode) {
        if (!requireHero()) {
            return;
        }

        Inventory inventory = hero.getInventory();

        switch (itemCode) {
            case "SH":
                if (inventory.getSmallHealthPotion() > 0) {
                    inventory.useSmallHealthPotion(hero);
                }
                break;
            case "MH":
                if (inventory.getMediumHealthPotion() > 0) {
                    inventory.useMediumHealthPotion(hero);
                }
                break;
            case "LH":
                if (inventory.getLargeHealthPotion() > 0) {
                    inventory.useLargeHealthPotion(hero);
                }
                break;
            case "SM":
                if (inventory.getSmallManaPotion() > 0) {
                    inventory.useSmallManaPotion(hero);
                }
                break;
            case "MM":
                if (inventory.getMediumManaPotion() > 0) {
                    inventory.useMediumManaPotion(hero);
                }
                break;
            case "LM":
                if (inventory.getLargeManaPotion() > 0) {
                    inventory.useLargeManaPotion(hero);
                }
                break;
            default:
                break;
        }

        refreshInventoryPanel();
        refreshHeroDashboard();
    }

    private void launchArea1() {
        if (!requireHero()) {
            return;
        }
        if (!hero.hasUnlockedArea1()) {
            showInfoSync("Forest of Reverie", "Forest of Reverie is still locked.");
            return;
        }
        startAreaAdventureAsync(this::startForestOfReverieAdventure);
    }

    private void launchArea2() {
        if (!requireHero()) {
            return;
        }
        if (!hero.hasUnlockedArea2()) {
            showInfoSync("Reverie's Edge", "Reverie's Edge is still locked.");
            return;
        }
        startAreaAdventureAsync(this::startReveriesEdgeAdventure);
    }

    private void launchArea3() {
        if (!requireHero()) {
            return;
        }
        if (!hero.hasUnlockedArea3()) {
            showInfoSync("Forsaken Lands", "Forsaken Lands is still locked.");
            return;
        }
        startAreaAdventureAsync(this::startForsakenLandsAdventure);
    }

    private void startAreaAdventureAsync(Runnable adventureTask) {
        battlePanel.setBattleButtonsEnabled(false);

        Thread worker = new Thread(() -> {
            try {
                adventureTask.run();
            } catch (Exception exception) {
                SwingUtilities.invokeLater(() -> showWarningSync(
                        "Error",
                        "Area flow error: " + exception.getMessage()));
            } finally {
                SwingUtilities.invokeLater(() -> {
                    refreshHeroDashboard();
                    refreshInventoryPanel();
                    refreshProfilePanel();
                    if (hero == null) {
                        subtitleLabel.setText("Choose a hero to begin.");
                        showScreen(SCREEN_CHARACTER);
                    } else {
                        subtitleLabel.setText("Adventure overview.");
                        showScreen(SCREEN_MAIN);
                    }
                    battlePanel.setBattleButtonsEnabled(false);
                });
            }
        }, "area-adventure");

        worker.start();
    }

    private void startForestOfReverieAdventure() {
        if (!requireHero()) {
            return;
        }

        boolean firstVisit = !hero.hasVisitedArea1();
        hero.setHasVisitedArea1(true);
        if (firstVisit) {
            playNarrationSequence("Forest of Reverie", buildArea1Narration());
        }

        Entity[] encounters = {
                new Goblin(),
                new Slime(),
                new MudLurker(),
                new Elderthorn()
        };
        int[] goldRewards = { 360, 420, 680, 1200 };
        int[] xpRewards = { 90, 110, 180, 320 };

        boolean completed = runAreaAdventure("Forest of Reverie", encounters, goldRewards, xpRewards);
        if (!completed) {
            return;
        }

        hero.setHaveDefeatedArea1Boss(true);
        hero.unlockArea2(true);
        showInfoSync("Area Cleared",
                "Forest of Reverie cleared.\nVisit Principal's Office to process Area 2 eligibility.");
    }

    private void startReveriesEdgeAdventure() {
        if (!requireHero()) {
            return;
        }

        boolean firstVisit = !hero.hasVisitedArea2();
        hero.setHasVisitedArea2(true);
        if (firstVisit) {
            playNarrationSequence("Reverie's Edge", buildArea2Narration());
        }

        Entity[] encounters = {
                new SwampRat(),
                new VeilSerpent(),
                new FadingWarden(),
                new Morgrath()
        };
        int[] goldRewards = { 520, 620, 900, 1500 };
        int[] xpRewards = { 130, 170, 240, 420 };

        boolean completed = runAreaAdventure("Reverie's Edge", encounters, goldRewards, xpRewards);
        if (!completed) {
            return;
        }

        hero.setHaveDefeatedArea2Boss(true);
        hero.unlockArea3(true);
        showInfoSync("Area Cleared",
                "Reverie's Edge cleared.\nVisit Principal's Office to process Area 3 eligibility.");
    }

    private void startForsakenLandsAdventure() {
        if (!requireHero()) {
            return;
        }

        boolean firstVisit = !hero.hasVisitedArea3();
        hero.setHasVisitedArea3(true);
        if (firstVisit) {
            playNarrationSequence("Forsaken Lands", buildArea3Narration());
        }

        Entity[] encounters = {
                new ShadowAbyss(),
                new VoidBeast(),
                new HollowKing(),
                new Azrael(),
                new Kim()
        };
        int[] goldRewards = { 760, 880, 1300, 2000, 4000 };
        int[] xpRewards = { 210, 260, 360, 650, 1500 };

        boolean completed = runAreaAdventure("Forsaken Lands", encounters, goldRewards, xpRewards);
        if (!completed) {
            return;
        }

        hero.setHaveDefeatedArea3Boss(true);

        Object[] options = { "Sacrifice and Return", "Refuse and Repeat Cycle" };
        int endingChoice = showOptionSync(
                "Final Choice",
                "Kim Morvain has fallen.\nChoose your fate:",
                options,
                options[0]);

        if (endingChoice == 0) {
            playNarrationSequence("Final Ending", buildSacrificeEndingNarration());
            showInfoSync("Ending",
                    "You sacrificed your character and returned to the real world.\nYou cleared the game.");
        } else {
            playNarrationSequence("Loop Ending", buildLoopEndingNarration());
            hero.resetAllProgress();
            hero = null;
            runOnEdtSync(() -> {
                refreshHeroDashboard();
                refreshInventoryPanel();
                refreshProfilePanel();
                subtitleLabel.setText("Choose a hero to begin.");
                showScreen(SCREEN_CHARACTER);
            });
            showInfoSync("Loop Ending", "The cycle repeats. Progress has been reset.");
        }
    }

    private boolean runAreaAdventure(String areaName, Entity[] encounters, int[] goldRewards, int[] xpRewards) {
        showInfoSync(areaName, "Entering " + areaName + "...\nPrepare for battle!");

        for (int i = 0; i < encounters.length; i++) {
            Entity enemy = encounters[i];
            String header = enemy.getName();

            int proceed = showConfirmSync(areaName, header + "\nStart battle?");
            if (proceed != 0) {
                showInfoSync(areaName, "Adventure cancelled.");
                return false;
            }

            BattleOutcome outcome = runButtonBattle(enemy, areaName);
            if (outcome == BattleOutcome.RAN) {
                showInfoSync(areaName, "You retreated from battle.");
                return false;
            }

            if (outcome == BattleOutcome.LOST) {
                showWarningSync("Defeat",
                        "You were defeated and brought back outside the academy.\nArea progress was not completed.");
                return false;
            }

            grantRewards(goldRewards[i], xpRewards[i], enemy.getName() + " defeated!");
        }

        return true;
    }

    private BattleOutcome runButtonBattle(Entity enemy, String battleTitle) {
        if (!requireHero()) {
            return BattleOutcome.LOST;
        }

        int originalHp = hero.getHp();
        int originalMana = hero.getMana();
        int originalCooldown1 = hero.getCooldown1();
        int originalCooldown2 = hero.getCooldown2();
        int originalCooldownU = hero.getCooldownU();
        int originalStun = hero.getStunned();
        int originalPoison = hero.getPoison();

        enemy.setHpCap(enemy.getHp());
        enemy.setManaCap(enemy.getMana());
        int round = 1;
        String battleLog = "Battle started against " + enemy.getName() + ".";

        while (hero.getHp() > 0 && enemy.getHp() > 0) {
            refreshHeroDashboard();
            refreshInventoryPanel();
            refreshProfilePanel();

            if (hero.getStunned() > 0) {
                battleLog = appendBattleLog(battleLog, hero.getName() + " is stunned and loses this turn.");
            } else {
                int choice = chooseBattleAction(enemy, round, battleTitle, battleLog);
                if (choice == -1) {
                    choice = 5;
                }

                if (choice == 4) {
                    usePotionInBattle();
                    continue;
                }

                if (choice == 5) {
                    if (attemptRunAway(enemy)) {
                        restoreHeroBattleState(
                                originalHp,
                                originalMana,
                                originalCooldown1,
                                originalCooldown2,
                                originalCooldownU,
                                originalStun,
                                originalPoison);
                        return BattleOutcome.RAN;
                    }

                    battleLog = appendBattleLog(battleLog, "Retreat failed.");
                } else {
                    String playerActionResult = performPlayerAction(choice, enemy);
                    if (playerActionResult.startsWith("INVALID:")) {
                        showInfoSync("Battle", playerActionResult.replace("INVALID:", "").trim());
                        continue;
                    }
                    battleLog = appendBattleLog(battleLog, playerActionResult);
                }
            }

            applyPlayerAfterTurnEffects();
            reducePlayerCooldowns();

            if (enemy.getHp() <= 0) {
                enemy.setHp(0);
                battleLog = appendBattleLog(battleLog, enemy.getName() + " was defeated.");
                showInfoSync(battleTitle, battleLog);
                restoreHeroBattleState(
                        originalHp,
                        originalMana,
                        originalCooldown1,
                        originalCooldown2,
                        originalCooldownU,
                        originalStun,
                        originalPoison);
                return BattleOutcome.WON;
            }

            if (enemy.getStunned() > 0) {
                battleLog = appendBattleLog(battleLog, enemy.getName() + " is stunned and cannot act.");
            } else if (enemy.getDisabled() > 0) {
                battleLog = appendBattleLog(battleLog, enemy.getName() + " is disabled and cannot act.");
            } else {
                battleLog = appendBattleLog(battleLog, performEnemyAction(enemy));
            }

            applyEnemyAfterTurnEffects(enemy);
            reduceEnemyCooldowns(enemy);

            if (hero.getHp() <= 0) {
                hero.setHp(0);
                showWarningSync(battleTitle, battleLog);
                restoreHeroBattleState(
                        originalHp,
                        originalMana,
                        originalCooldown1,
                        originalCooldown2,
                        originalCooldownU,
                        originalStun,
                        originalPoison);
                return BattleOutcome.LOST;
            }

            round++;
        }

        restoreHeroBattleState(
                originalHp,
                originalMana,
                originalCooldown1,
                originalCooldown2,
                originalCooldownU,
                originalStun,
                originalPoison);
        return hero.getHp() > 0 ? BattleOutcome.WON : BattleOutcome.LOST;
    }

    private int chooseBattleAction(Entity enemy, int round, String battleTitle, String battleLog) {
        runOnEdtSync(() -> {
            battlePanel.restoreBattleButtons();
            battlePanel.getBattleTitleValue().setText(battleTitle);
            battlePanel.getBattleRoundValue().setText("Round " + round);
            battlePanel.getBattleHeroNameValue().setText(hero.getName());
            battlePanel.getBattleEnemyNameValue().setText(enemy.getName());
            updateEnemySprite(enemy);
            updateBattleBars(enemy);
            battlePanel.getBattleLogArea().setText(truncateBattleLog(battleLog));
            battlePanel.getBattleLogArea().setCaretPosition(battlePanel.getBattleLogArea().getDocument().getLength());
            subtitleLabel.setText(battleTitle);
            showScreen(SCREEN_BATTLE);
            battlePanel.setBattleButtonsEnabled(true);
        });

        return waitForBattleAction();
    }

    private void updateBattleBars(Entity enemy) {
        battlePanel.getBattleHeroHpBar().setMaximum(Math.max(1, hero.getHpCap()));
        battlePanel.getBattleHeroHpBar().setValue(Math.max(0, hero.getHp()));
        battlePanel.getBattleHeroHpBar()
                .setString(statFormat.format(Math.max(0, hero.getHp())) + " / " + statFormat.format(hero.getHpCap()));
        battlePanel.getBattleHeroLevelValue().setText("Level " + hero.getLevel());

        battlePanel.getBattleHeroManaBar().setMaximum(Math.max(1, hero.getManaCap()));
        battlePanel.getBattleHeroManaBar().setValue(Math.max(0, hero.getMana()));
        battlePanel.getBattleHeroManaBar().setString(
                statFormat.format(Math.max(0, hero.getMana())) + " / " + statFormat.format(hero.getManaCap()));

        battlePanel.getBattleEnemyHpBar().setMaximum(Math.max(1, enemy.getHpCap()));
        battlePanel.getBattleEnemyHpBar().setValue(Math.max(0, enemy.getHp()));
        battlePanel.getBattleEnemyHpBar()
                .setString(statFormat.format(Math.max(0, enemy.getHp())) + " / " + statFormat.format(enemy.getHpCap()));

        battlePanel.getBattleEnemyManaBar().setMaximum(Math.max(1, enemy.getManaCap()));
        battlePanel.getBattleEnemyManaBar().setValue(Math.max(0, enemy.getMana()));
        battlePanel.getBattleEnemyManaBar().setString(
                statFormat.format(Math.max(0, enemy.getMana())) + " / " + statFormat.format(enemy.getManaCap()));
    }

    private void submitBattleAction(int action) {
        synchronized (battleActionLock) {
            if (pendingBattleAction != null) {
                return;
            }
            pendingBattleAction = action;
            runOnEdtSync(() -> battlePanel.setBattleButtonsEnabled(false));
            battleActionLock.notifyAll();
        }
    }

    private int waitForBattleAction() {
        synchronized (battleActionLock) {
            pendingBattleAction = null;
            while (pendingBattleAction == null) {
                try {
                    battleActionLock.wait();
                } catch (InterruptedException interruptedException) {
                    Thread.currentThread().interrupt();
                    return 5;
                }
            }
            int action = pendingBattleAction;
            pendingBattleAction = null;
            return action;
        }
    }

    private void runOnEdtSync(Runnable task) {
        if (SwingUtilities.isEventDispatchThread()) {
            task.run();
            return;
        }

        try {
            SwingUtilities.invokeAndWait(task);
        } catch (Exception ignored) {
        }
    }

    private void showInfoSync(String title, String message) {
        runOnEdtSync(() -> {
            if (overlay != null) {
                overlay.showMessage(title, message);
            }
        });
    }

    private void showNarrationSync(String title, String message, boolean fadeIn, boolean fadeOut) {
        runOnEdtSync(() -> {
            if (overlay != null) {
                overlay.showMessageSequence(title, message, fadeIn, fadeOut);
            }
        });
    }

    private void showWarningSync(String title, String message) {
        runOnEdtSync(() -> {
            if (overlay != null) {
                overlay.showMessage(title, message);
            }
        });
    }

    private int showConfirmSync(String title, String message) {
        final int[] choice = { 1 };
        runOnEdtSync(() -> {
            if (overlay != null) {
                choice[0] = overlay.showConfirm(title, message);
            }
        });
        return choice[0];
    }

    private int showOptionSync(String title, String message, Object[] options, Object initial) {
        final int[] choice = { -1 };
        runOnEdtSync(() -> {
            if (overlay != null) {
                choice[0] = overlay.showOptions(title, message, options, initial);
            }
        });
        return choice[0];
    }

    private String performPlayerAction(int choice, Entity enemy) {
        int enemyHpBefore = enemy.getHp();
        int heroManaBefore = hero.getMana();
        int enemyStunBefore = enemy.getStunned();

        switch (choice) {
            case 0:
                hero.basicAttack(hero, enemy);
                break;
            case 1:
                if (hero.getMana() < hero.scaledCost(hero.getManaCostSkill1())) {
                    return "INVALID:Not enough mana for Skill 1.";
                }
                if (hero.getCooldown1() > 0) {
                    return "INVALID:Skill 1 is on cooldown (" + hero.getCooldown1() + ").";
                }
                hero.skill1(hero, enemy);
                break;
            case 2:
                if (hero.getMana() < hero.scaledCost(hero.getManaCostSkill2())) {
                    return "INVALID:Not enough mana for Skill 2.";
                }
                if (hero.getCooldown2() > 0) {
                    return "INVALID:Skill 2 is on cooldown (" + hero.getCooldown2() + ").";
                }
                hero.skill2(hero, enemy);
                break;
            case 3:
                if (hero.getMana() < hero.scaledCost(hero.getManaCostUltimate())) {
                    return "INVALID:Not enough mana for Ultimate.";
                }
                if (hero.getCooldownU() > 0) {
                    return "INVALID:Ultimate is on cooldown (" + hero.getCooldownU() + ").";
                }
                hero.ultimate(hero, enemy);
                break;
            default:
                return "INVALID:Invalid action.";
        }

        int dealt = Math.max(0, enemyHpBefore - enemy.getHp());
        int manaSpent = Math.max(0, heroManaBefore - hero.getMana());
        boolean stunned = enemy.getStunned() > enemyStunBefore;

        String text = hero.getName() + " dealt " + statFormat.format(dealt) + " to " + enemy.getName() + ".";
        if (manaSpent > 0) {
            text += " Mana -" + statFormat.format(manaSpent) + ".";
        }
        if (stunned) {
            text += " Enemy stunned.";
        }

        return text;
    }

    private String performEnemyAction(Entity enemy) {
        int heroHpBefore = hero.getHp();
        int enemyManaBefore = enemy.getMana();
        int heroStunBefore = hero.getStunned();
        int heroPoisonBefore = hero.getPoison();

        int choice = selectEnemyAction(enemy);

        switch (choice) {
            case 1 -> enemy.basicAttack(enemy, hero);
            case 2 -> enemy.skill1(enemy, hero);
            case 3 -> enemy.skill2(enemy, hero);
            case 4 -> enemy.skill3(enemy, hero);
            case 5 -> enemy.ultimate(enemy, hero);
            default -> enemy.basicAttack(enemy, hero);
        }

        int damage = Math.max(0, heroHpBefore - hero.getHp());
        int manaSpent = Math.max(0, enemyManaBefore - enemy.getMana());
        boolean stunned = hero.getStunned() > heroStunBefore;
        boolean poisoned = hero.getPoison() > heroPoisonBefore;

        String text = enemy.getName() + " dealt " + statFormat.format(damage) + " to " + hero.getName() + ".";
        if (manaSpent > 0) {
            text += " Enemy mana -" + statFormat.format(manaSpent) + ".";
        }
        if (stunned) {
            text += " You are stunned.";
        }
        if (poisoned) {
            text += " You are poisoned.";
        }

        return text;
    }

    private int selectEnemyAction(Entity enemy) {
        int[] pool = new int[5];
        int count = 0;

        pool[count++] = 1;

        if (enemy.getMana() >= enemy.getManaCostSkill1() && enemy.getCooldown1() == 0) {
            pool[count++] = 2;
        }
        if (enemy.getMana() >= enemy.getManaCostSkill2() && enemy.getCooldown2() == 0) {
            pool[count++] = 3;
        }

        boolean hasSkill3 = enemy.getSkill3() != null
                && !enemy.getSkill3().isBlank()
                && !"Unknown".equalsIgnoreCase(enemy.getSkill3());
        if (hasSkill3 && enemy.getMana() >= enemy.getManaCostSkill3() && enemy.getCooldown3() == 0) {
            pool[count++] = 4;
        }

        boolean hasUltimate = enemy.getUltimate() != null
                && !enemy.getUltimate().isBlank()
                && !"Unknown".equalsIgnoreCase(enemy.getUltimate());
        if (hasUltimate && enemy.getMana() >= enemy.getManaCostUltimate() && enemy.getCooldownU() == 0) {
            pool[count++] = 5;
        }

        return pool[random.nextInt(count)];
    }

    private void applyPlayerAfterTurnEffects() {
        if (hero.getPoison() > 0) {
            int poisonDamage = Math.max(1, (int) Math.round(hero.getHpCap() * 0.05));
            hero.setHp(Math.max(0, hero.getHp() - poisonDamage));
            hero.setPoison(hero.getPoison() - 1);
        }

        if (hero.getStunned() >= 0) {
            hero.setStun(hero.getStunned() - 1);
        }

        if (hero.getStunned() < -1) {
            hero.setStun(-1);
        }
        if (hero.getPoison() < 0) {
            hero.setPoison(0);
        }
    }

    private void applyEnemyAfterTurnEffects(Entity enemy) {
        if (enemy.getStunned() >= 0) {
            enemy.setStun(enemy.getStunned() - 1);
        }
        if (enemy.getDisabled() >= 0) {
            enemy.setDisabled(enemy.getDisabled() - 1);
        }
        if (enemy.getStunned() < -1) {
            enemy.setStun(-1);
        }
        if (enemy.getDisabled() < -1) {
            enemy.setDisabled(-1);
        }
    }

    private void reducePlayerCooldowns() {
        if (hero.getCooldown1() > 0) {
            hero.setCooldown1(hero.getCooldown1() - 1);
        }
        if (hero.getCooldown2() > 0) {
            hero.setCooldown2(hero.getCooldown2() - 1);
        }
        if (hero.getCooldownU() > 0) {
            hero.setCooldownU(hero.getCooldownU() - 1);
        }
    }

    private void reduceEnemyCooldowns(Entity enemy) {
        if (enemy.getCooldown1() > 0) {
            enemy.setCooldown1(enemy.getCooldown1() - 1);
        }
        if (enemy.getCooldown2() > 0) {
            enemy.setCooldown2(enemy.getCooldown2() - 1);
        }
        if (enemy.getCooldown3() > 0) {
            enemy.setCooldown3(enemy.getCooldown3() - 1);
        }
        if (enemy.getCooldownU() > 0) {
            enemy.setCooldownU(enemy.getCooldownU() - 1);
        }
    }

    private boolean attemptRunAway(Entity enemy) {
        double baseChance = 30.0;
        double speedFactor = (hero.getSpeed() - enemy.getSpeed()) * 0.5;
        double successChance = Math.max(10.0, Math.min(90.0, baseChance + speedFactor));
        double roll = random.nextDouble() * 100.0;
        return roll <= successChance;
    }

    private void usePotionInBattle() {
        Inventory inventory = hero.getInventory();
        String[] options = {
                "Small HP (" + inventory.getSmallHealthPotion() + ")",
                "Medium HP (" + inventory.getMediumHealthPotion() + ")",
                "Large HP (" + inventory.getLargeHealthPotion() + ")",
                "Small Mana (" + inventory.getSmallManaPotion() + ")",
                "Medium Mana (" + inventory.getMediumManaPotion() + ")",
                "Large Mana (" + inventory.getLargeManaPotion() + ")",
                "Back"
        };

        int choice = showOptionSync("Inventory", "Choose a potion.", options, options[0]);

        if (choice == 6 || choice == -1) {
            return;
        }

        int hpBefore = hero.getHp();
        int manaBefore = hero.getMana();

        switch (choice) {
            case 0 -> {
                if (inventory.getSmallHealthPotion() > 0) {
                    inventory.useSmallHealthPotion(hero);
                } else {
                    showInfoSync("Inventory", "No Small Health Potion left.");
                    return;
                }
            }
            case 1 -> {
                if (inventory.getMediumHealthPotion() > 0) {
                    inventory.useMediumHealthPotion(hero);
                } else {
                    showInfoSync("Inventory", "No Medium Health Potion left.");
                    return;
                }
            }
            case 2 -> {
                if (inventory.getLargeHealthPotion() > 0) {
                    inventory.useLargeHealthPotion(hero);
                } else {
                    showInfoSync("Inventory", "No Large Health Potion left.");
                    return;
                }
            }
            case 3 -> {
                if (inventory.getSmallManaPotion() > 0) {
                    inventory.useSmallManaPotion(hero);
                } else {
                    showInfoSync("Inventory", "No Small Mana Potion left.");
                    return;
                }
            }
            case 4 -> {
                if (inventory.getMediumManaPotion() > 0) {
                    inventory.useMediumManaPotion(hero);
                } else {
                    showInfoSync("Inventory", "No Medium Mana Potion left.");
                    return;
                }
            }
            case 5 -> {
                if (inventory.getLargeManaPotion() > 0) {
                    inventory.useLargeManaPotion(hero);
                } else {
                    showInfoSync("Inventory", "No Large Mana Potion left.");
                    return;
                }
            }
            default -> {
                return;
            }
        }

        int hpGain = Math.max(0, hero.getHp() - hpBefore);
        int manaGain = Math.max(0, hero.getMana() - manaBefore);
        showInfoSync("Inventory",
                "Potion used.\nHP +" + statFormat.format(hpGain) + " | Mana +" + statFormat.format(manaGain));
        refreshInventoryPanel();
        refreshHeroDashboard();
    }

    private void restoreHeroBattleState(
            int hp,
            int mana,
            int cooldown1,
            int cooldown2,
            int cooldownU,
            int stun,
            int poison) {
        hero.setHp(hp);
        hero.setMana(mana);
        hero.setCooldown1(cooldown1);
        hero.setCooldown2(cooldown2);
        hero.setCooldownU(cooldownU);
        hero.setStun(stun);
        hero.setPoison(poison);
        refreshHeroDashboard();
        refreshInventoryPanel();
        refreshProfilePanel();
    }

    private String appendBattleLog(String current, String line) {
        if (current == null || current.isBlank()) {
            return line;
        }
        return current + "\n" + line;
    }

    private String truncateBattleLog(String log) {
        if (log == null) {
            return "";
        }
        String[] lines = log.split("\\R");
        int start = Math.max(0, lines.length - 6);
        StringBuilder builder = new StringBuilder();
        for (int i = start; i < lines.length; i++) {
            builder.append(lines[i]).append("\n");
        }
        return builder.toString().trim();
    }

    private enum BattleOutcome {
        WON,
        LOST,
        RAN
    }

    private void launchLegacyAction(String title, Runnable action) {
        if (!requireHero()) {
            return;
        }

        appendLog("\n[" + title + "] opened from GUI buttons.\n");

        Thread worker = new Thread(() -> {
            try {
                action.run();
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                SwingUtilities.invokeLater(() -> {
                    refreshHeroDashboard();
                    refreshInventoryPanel();
                    refreshProfilePanel();
                });
            }
        }, "legacy-" + title.toLowerCase().replace(' ', '-'));

        worker.start();
    }

    private boolean requireHero() {
        if (hero != null) {
            return true;
        }

        showInfoSync("Action Required", "Choose a character first.");
        return false;
    }

    private void submitLegacyInput() {
        String rawInput = inputField.getText();

        if (rawInput == null) {
            return;
        }

        String submittedInput = rawInput.strip();

        submitLegacyInputValue(submittedInput);
    }

    private void submitLegacyShortcut(String submittedInput) {
        submitLegacyInputValue(submittedInput);
    }

    private void submitLegacyInputValue(String submittedInput) {
        try {
            gameInputWriter.write((submittedInput + System.lineSeparator()).getBytes(StandardCharsets.UTF_8));
            gameInputWriter.flush();
            appendLog("\n> " + submittedInput + "\n");
            inputField.setText("");
            setQuickChoiceVisible(false);
        } catch (Exception exception) {
            appendLog("\n[Input error] " + exception.getMessage() + "\n");
        }
    }

    private void observeLegacyOutput(String text) {
        if (text == null || text.isEmpty()) {
            return;
        }

        if (!containsYesNoPrompt(text) && quickChoicePanel != null && quickChoicePanel.isVisible()) {
            setQuickChoiceVisible(false);
        }
    }

    private String filterLegacyOutput(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        pendingLegacyOutput.append(text);
        StringBuilder visibleOutput = new StringBuilder();

        while (pendingLegacyOutput.length() > 0) {
            MatchResult promptMatch = findPromptMatch(pendingLegacyOutput.toString());

            if (promptMatch == null) {
                int safeLength = Math.max(0, pendingLegacyOutput.length() - LEGACY_OUTPUT_TAIL_LIMIT);

                if (safeLength == 0) {
                    break;
                }

                visibleOutput.append(pendingLegacyOutput, 0, safeLength);
                pendingLegacyOutput.delete(0, safeLength);
                continue;
            }

            if (promptMatch.start > 0) {
                visibleOutput.append(pendingLegacyOutput, 0, promptMatch.start);
                pendingLegacyOutput.delete(0, promptMatch.start);
            }

            String promptBlock = pendingLegacyOutput.substring(0, promptMatch.end - promptMatch.start);
            String promptText = extractPromptText(promptBlock);

            if (promptText != null && !promptText.isBlank()) {
                quickChoicePromptLabel.setText(promptText);
                setQuickChoiceVisible(true);
            }

            pendingLegacyOutput.delete(0, promptBlock.length());
        }

        if (pendingLegacyOutput.length() > 0 && !looksLikePromptPrefix(pendingLegacyOutput.toString())) {
            visibleOutput.append(pendingLegacyOutput);
            pendingLegacyOutput.setLength(0);
        }

        return visibleOutput.toString();
    }

    private void setQuickChoiceVisible(boolean visible) {
        if (quickChoicePanel == null || quickChoicePromptLabel == null) {
            return;
        }

        if (quickChoicePanel.isVisible() == visible && quickChoicePromptLabel.isVisible() == visible) {
            return;
        }

        quickChoicePromptLabel.setVisible(visible);
        quickChoicePanel.setVisible(visible);
        quickChoicePanel.revalidate();
        quickChoicePanel.repaint();
    }

    private MatchResult findPromptMatch(String text) {
        Matcher boxedMatcher = BOXED_YES_NO_PROMPT.matcher(text);

        while (boxedMatcher.find()) {
            String candidate = boxedMatcher.group();

            if (containsYesNoPrompt(candidate)) {
                return new MatchResult(boxedMatcher.start(), boxedMatcher.end());
            }
        }

        Matcher inlineMatcher = INLINE_YES_NO_PROMPT.matcher(text);

        if (inlineMatcher.find() && containsYesNoPrompt(inlineMatcher.group())) {
            return new MatchResult(inlineMatcher.start(), inlineMatcher.end());
        }

        return null;
    }

    private boolean containsYesNoPrompt(String text) {
        return text != null && text.contains("(y/n)");
    }

    private boolean looksLikePromptPrefix(String text) {
        return text.contains("┌") || text.contains("(y/n)") || text.endsWith(": ") || text.endsWith("-->| ");
    }

    private String extractPromptText(String promptBlock) {
        String[] lines = promptBlock.split("\\R");

        for (String line : lines) {
            if (!line.contains("(y/n)")) {
                continue;
            }

            String cleaned = line
                    .replace("│", "")
                    .replace("(y/n)", "")
                    .replace(":", "")
                    .replace("-->|", "")
                    .trim();

            if (!cleaned.isEmpty()) {
                return cleaned;
            }
        }

        return "Choose:";
    }

    private static final class MatchResult {
        private final int start;
        private final int end;

        private MatchResult(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private void appendLog(String text) {
        outputArea.append(text);
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    private void playNarrationSequence(String title, String[] lines) {
        if (lines == null || lines.length == 0) {
            return;
        }

        int visibleCount = 0;
        for (String line : lines) {
            if (line != null && !line.isBlank()) {
                visibleCount++;
            }
        }
        if (visibleCount == 0) {
            return;
        }

        int shown = 0;
        for (String line : lines) {
            if (line == null || line.isBlank()) {
                continue;
            }
            boolean fadeIn = shown == 0;
            boolean fadeOut = shown == visibleCount - 1;
            showNarrationSync(title, line.trim(), fadeIn, fadeOut);
            shown++;
        }
    }

    private String[] buildAcademyNarration() {
        return new String[] {
                "The tall gates of Mystvale Academy open with a low groan as you step inside.",
                "A familiar chill brushes your shoulder. Void appears, flickering softly in the light.",
                "\"Mystvale is vast,\" the spirit says. \"The Library offers knowledge and quests. The Training Ground forges your strength.\"",
                "\"The Principal's Office decides your eligibility, and every path beyond the academy comes with danger.\"",
                "\"Choose your road carefully. Every place here will shape the hero you become.\""
        };
    }

    private String[] buildLibraryNarration() {
        return new String[] {
                "As you step into the library, the air grows still and heavy with old paper and quiet thought.",
                "Void flickers into view beside the shelves. \"This is Mystvale's Library. May knowledge guide you.\"",
                "The spirit fades, leaving only the rustle of unseen pages and the promise of secrets waiting to be uncovered."
        };
    }

    private String[] buildTrainingNarration() {
        return new String[] {
                "The Training Ground bursts with life: sparring steel, shouted instructions, and the rhythm of practiced movement.",
                "A tall coach studies your stance with sharp eyes. \"Untaught, but solid. If you want to grow stronger, earn it.\"",
                "\"Training here isn't just power,\" the coach warns. \"It's control, discipline, and the will to keep going.\""
        };
    }

    private String[] buildPrincipalOfficeNarration() {
        return new String[] {
                "The doors of the Principal's Office stand tall and unyielding as you approach.",
                "Golden light spills across the marble floor while the academy crest gleams overhead.",
                "A secretary stops you before the inner chamber. \"Before seeing Principal Nemeesha, your progress must be verified.\"",
                "The room falls quiet, as if even the walls are waiting to judge whether you are ready for what comes next."
        };
    }

    private String[] buildArea1EligibilityNarration() {
        return new String[] {
                "Principal Nemeesha Brightwell nods as you step forward.",
                "\"You have shown promise,\" she says. \"The Forest of Reverie will now open to you.\"",
                "\"Do not underestimate what waits there. Even the gentlest woods may hide fangs.\""
        };
    }

    private String[] buildArea2EligibilityNarration() {
        return new String[] {
                "Principal Nemeesha studies you with a steadier, more serious gaze than before.",
                "\"Impressive progress. You have earned passage into Reverie's Edge.\"",
                "\"It is a place that tests patience as much as strength. Keep your focus, or it will swallow you.\""
        };
    }

    private String[] buildArea3EligibilityNarration() {
        return new String[] {
                "The principal's expression turns solemn as you approach her desk.",
                "\"Few reach this point,\" she says quietly. \"The Forsaken Lands now await you.\"",
                "\"Beyond those gates lie trials unlike any you have faced. Walk forward with courage and wisdom.\""
        };
    }

    private String[] buildShopNarration() {
        return new String[] {
                "Void appears beside the shop door, calm against the academy's hush.",
                "\"This is the supply shop,\" the spirit says. \"Weapons, potions, and the tools students depend on to survive.\"",
                "\"Spend with intention. Not everything you need will be offered twice.\""
        };
    }

    private String[] buildShopConversationNarration() {
        return new String[] {
                "You push open the creaking door, and the scent of herbs and aged wood fills the air.",
                "A small bell jingles. Behind the counter, the shopkeeper peers over his spectacles.",
                "\"Welcome to Mystic Curiosities,\" he says. \"I'm Kabang Cobbleton. Handle the items wisely. They all carry stories.\""
        };
    }

    private String[] buildInventoryNarration() {
        return new String[] {
                "Void emerges without a sound, its form quiet and steady beside you.",
                "\"All that you carry tells a story,\" it says. \"Weapons, potions, relics, and the choices you've made so far.\"",
                "\"Your space is not endless. What you keep reflects what you value. Choose wisely.\""
        };
    }

    private String[] buildArea1Narration() {
        return new String[] {
                "The trees of the Forest of Reverie rise around you, ancient and watchful.",
                "Void drifts into view. \"This is the inner forest, where your first true trials begin.\"",
                "The spirit fades, leaving you alone with the rustle of leaves and the uneasy sense that the forest is already studying you."
        };
    }

    private String[] buildArea2Narration() {
        return new String[] {
                "The air thickens as you step into Reverie's Edge, where the ground sours and the silence feels wrong.",
                "Void flickers beside you. \"This region is harsher, crueler, and home to stronger entities. Stay sharp.\"",
                "When the spirit vanishes, only the mist remains, curling around your path like a warning."
        };
    }

    private String[] buildArea3Narration() {
        return new String[] {
                "Stone ruins and jagged towers stretch across the Forsaken Lands, vast and unnervingly alive.",
                "Void appears with an unreadable expression. \"This is the outer region, where the strongest entities gather.\"",
                "\"At the end of this land waits the heart of your trial. Never let your guard down.\""
        };
    }

    private String[] buildSacrificeEndingNarration() {
        return new String[] {
                "The silence after Kim Morvain's fall feels almost impossible, as if the academy itself has forgotten how to breathe.",
                "You make your choice knowing the cost. The path home will open only if something of you is left behind.",
                "Light gathers, the curse loosens, and Mystvale fades at the edges. Your journey ends with sacrifice, but also with freedom."
        };
    }

    private String[] buildLoopEndingNarration() {
        return new String[] {
                "For a heartbeat, everything seems still. Then the world bends.",
                "Corridors shift, shadows stretch, and the academy reforms around you like a memory refusing to end.",
                "The cycle begins again. Mystvale remains, waiting for you to walk its halls once more."
        };
    }

    private void showLandingScreen() {
        titleLabel.setText("Mystvale Academy");
        subtitleLabel.setText("GUI RPG");
        rootLayout.show(rootPanel, ROOT_LANDING);
    }

    private void openCharacterSelectFromLanding() {
        titleLabel.setText("Mystvale Academy");
        subtitleLabel.setText("Choose a hero to begin.");
        rootLayout.show(rootPanel, ROOT_GAME);
        showScreen(SCREEN_CHARACTER);
    }

    private void showScreen(String screenName) {
        screenLayout.show(screenPanel, screenName);

        boolean isBattleScreen = SCREEN_BATTLE.equals(screenName);
        if (headerPanel != null) {
            headerPanel.setVisible(!isBattleScreen);
        }
        if (headerExitButton != null) {
            boolean hideExit = SCREEN_CHARACTER.equals(screenName) || SCREEN_STORY.equals(screenName);
            headerExitButton.setVisible(!hideExit);
        }
        if (leftPane != null) {
            leftPane.setBackground(isBattleScreen ? COLOR_BATTLE_PANEL : COLOR_BACKGROUND);
            leftPane.setBorder(isBattleScreen ? BorderFactory.createEmptyBorder() : defaultLeftPaneBorder);
        }
        if (screenPanel != null) {
            screenPanel.setOpaque(isBattleScreen);
            screenPanel.setBackground(isBattleScreen ? COLOR_BATTLE_PANEL : COLOR_BACKGROUND);
        }
    }

    @Override
    public void onBattleAction(int action) {
        submitBattleAction(action);
    }

    @Override
    public void onShowAttackPanel() {
        battlePanel.showAttackPanel();
    }
}
