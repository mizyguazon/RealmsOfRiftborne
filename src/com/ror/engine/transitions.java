package com.ror.engine;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import javax.swing.*;

final class transitions {
    private boolean running = false;
    private static final int STEPS = 14;
    private static final int STEP_DELAY_MS = 18;

    void runTransition(JFrame frame, Runnable onBlack, Runnable afterFade) {
        if (frame == null) {
            if (onBlack != null) {
                onBlack.run();
            }
            if (afterFade != null) {
                afterFade.run();
            }
            return;
        }

        if (running) {
            return;
        }
        running = true;

        Component previousGlassPane = frame.getGlassPane();
        FadeTransitionPane fadePane = new FadeTransitionPane();
        frame.setGlassPane(fadePane);
        fadePane.setVisible(true);
        fadePane.requestFocusInWindow();

        final int[] outStep = { 0 };
        Timer fadeOut = new Timer(STEP_DELAY_MS, null);
        fadeOut.addActionListener(event -> {
            outStep[0]++;
            fadePane.setAlpha(Math.min(1f, outStep[0] / (float) STEPS));

            if (outStep[0] < STEPS) {
                return;
            }

            ((Timer) event.getSource()).stop();

            if (onBlack != null) {
                onBlack.run();
            }

            final int[] inStep = { STEPS };
            Timer fadeIn = new Timer(STEP_DELAY_MS, null);
            fadeIn.addActionListener(fadeEvent -> {
                inStep[0]--;
                fadePane.setAlpha(Math.max(0f, inStep[0] / (float) STEPS));

                if (inStep[0] > 0) {
                    return;
                }

                ((Timer) fadeEvent.getSource()).stop();
                fadePane.setVisible(false);
                frame.setGlassPane(previousGlassPane);
                if (previousGlassPane != null) {
                    previousGlassPane.setVisible(false);
                }
                running = false;

                if (afterFade != null) {
                    afterFade.run();
                }
            });
            fadeIn.start();
        });
        fadeOut.start();
    }

    private static final class FadeTransitionPane extends JComponent {
        private float alpha = 0f;

        private FadeTransitionPane() {
            setOpaque(false);
            addMouseListener(new MouseAdapter() {
            });
            addMouseMotionListener(new MouseMotionAdapter() {
            });
            addKeyListener(new KeyAdapter() {
            });
            setFocusable(true);
        }

        private void setAlpha(float value) {
            alpha = Math.max(0f, Math.min(1f, value));
            repaint();
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D graphics2D = (Graphics2D) graphics.create();
            graphics2D.setColor(new Color(0f, 0f, 0f, alpha));
            graphics2D.fillRect(0, 0, getWidth(), getHeight());
            graphics2D.dispose();
        }
    }
}
