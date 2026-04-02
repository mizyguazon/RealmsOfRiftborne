package com.ror;

import com.ror.engine.GameWindow;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            GameWindow window = new GameWindow();
            window.launchGame();
        });
    }
}
