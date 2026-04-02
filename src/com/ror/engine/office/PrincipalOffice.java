package com.ror.engine.office;

import com.ror.models.*;
import com.ror.engine.narration.*;
import com.ror.engine.design.*;
import java.util.Scanner;
import com.ror.models.training.StatProgress;

public class PrincipalOffice extends StatProgress {
    private OfficeNarration narrationHandler = new OfficeNarration();
    private SwordsmanPlot swordsmanPlotHandlder = new SwordsmanPlot();
    private GunnerPlot gunnerPlotHandler = new GunnerPlot();
    private MagePlot magePlotHandler = new MagePlot();
    private IntroTitle loadHandler = new IntroTitle();
    private Narration separatorHandler = new Narration();
    private Scanner scanner = new Scanner(System.in);
    boolean enter = true;

    public void principalOffice(Hero hero) {

        if (!hero.haveEntered()) {
            narrationHandler.officeNarration();
            hero.setHaveEntered(true);
        }

        System.out.println("┌────────────────────────────────────────┐");
        System.out.println("│  Press ENTER to determine eligibility  │");
        System.out.println("└────────────────────────────────────────┘");
        scanner.nextLine();

        separatorHandler.promptSeparator();
        loadHandler.elibility();
        separatorHandler.promptSeparator();

        boolean eligible = false;

        // ===== AREA 1 =====
        if (!hero.hasUnlockedArea1()
                && hero.canEnterArea1()
                && hero.hasFinishedAllTraining()) {

            narrationHandler.area1Eligible();
            hero.setUnlockArea1(true);
            
            System.out.println("┌──────────────────────────────────────────────────┐");
            System.out.println("│   + You may now enter The Forest of Reverie +    │");
            System.out.println("└──────────────────────────────────────────────────┘");
            System.out.println("│  Press ENTER to claim rewards...  │");
            System.out.println("└───────────────────────────────────┘");
            scanner.nextLine();

            separatorHandler.promptSeparatorResized();
            currencyProgress(hero);

            eligible = true;
        }

        // ===== AREA 2 =====
        else if (!hero.hasUnlockedArea2()
                && hero.canEnterArea2()
                && hero.getHaveDefeatedArea1Boss()) {

            hero.setUnlockArea2(true);

            if (hero.getSwordmanCharacterChosen()) {
                swordsmanPlotHandlder.swordsmanAfterArea1();
            } else if (hero.getGunnerCharacterChosen()) {
                gunnerPlotHandler.gunnerAfterArea1();
            } else if (hero.getMageCharacterChosen()) {
                magePlotHandler.mageAfterArea1();
            }

            System.out.println("┌───────────────────────────────────────────┐");
            System.out.println("│   + You may now enter The Reverie Edge +  │");
            System.out.println("└───────────────────────────────────────────┘");
            System.out.println("│  Press ENTER to claim rewards...  │");
            System.out.println("└───────────────────────────────────┘");
            scanner.nextLine();

            separatorHandler.promptSeparatorResized();
            currencyProgress(hero);
            eligible = true;
        }

        // ===== AREA 3 =====
        else if (!hero.hasUnlockedArea3()
                && hero.canEnterArea3()
                && hero.getHaveDefeatedArea2Boss()) {

            hero.setUnlockArea3(true);

            if (hero.getSwordmanCharacterChosen()) {
                swordsmanPlotHandlder.swordsmanAfterArea2();
            } else if (hero.getMageCharacterChosen()) {
                magePlotHandler.mageAfterArea2();
            } else if (hero.getGunnerCharacterChosen()) {
                gunnerPlotHandler.gunnerAfterArea2();
            }

            System.out.println("┌───────────────────────────────────────────────┐");
            System.out.println("│   + You may now enter The Forsaken Lands +    │");
            System.out.println("└───────────────────────────────────────────────┘");
            System.out.println("│  Press ENTER to claim rewards...  │");
            System.out.println("└───────────────────────────────────┘");
            scanner.nextLine();

            separatorHandler.promptSeparatorResized();
            currencyProgress(hero);

            eligible = true;
        }

        // ===== NOT ELIGIBLE =====
        if (!eligible) {

            System.out.println("┌──────────────────────────────────────────────────────────────────┐");
            System.out.println("│         You are not eligible for any outside premises            │");
            System.out.println("│   Finish your training or defeat more boss to gain elibility     │");
            System.out.println("└──────────────────────────────────────────────────────────────────┘");
            System.out.println("│  Press ENTER to continue  │");
            System.out.println("└───────────────────────────┘");
            scanner.nextLine();
        }

        separatorHandler.promptSeparatorResized();

        System.out.println("┌───────────────────────────────────────────────────┐");
        System.out.println("│   >>> Exiting from the Principal's Office <<<     │");
        System.out.println("└───────────────────────────────────────────────────┘");

        loadHandler.exitGame();
    }
}
