package Office;

import Hero.*;
import Narration.*;
import DesignRelated.*;
import java.util.Scanner;
import TrainingGround.StatProgress;

public class PrincipalOffice extends StatProgress {

    private OfficeNarration narrationHandler = new OfficeNarration();
    private SwordsmanPlot swordsmanPlotHandlder = new SwordsmanPlot();
    private GunnerPlot gunnerPlotHandler = new GunnerPlot();
    private MagePlot magePlotHandler = new MagePlot();
    private IntroTitle loadHandler = new IntroTitle();
    private Narration separatorHandler = new Narration();
    private Scanner scanner = new Scanner(System.in);

    public void principalOffice(Hero hero){
        
        if(!hero.haveEntered()){
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

        if (!hero.hasUnlockedArea1() && hero.canEnterArea1() && hero.hasFinishedAllTraining()) {
            
                narrationHandler.area1Eligible();
                hero.unlockArea1(true);

                System.out.println("┌──────────────────────────────────────────────────┐");
                System.out.println("│   + You may now enter The Forest of Reverie +    │");
                System.out.println("└──────────────────────────────────────────────────┘");
                System.out.println("│  Press ENTER to claim rewards...  │");
                System.out.println("└───────────────────────────────────┘");
                scanner.nextLine();

                separatorHandler.promptSeparatorResized();

                currencyProgress(hero);
                scanner.nextLine();

                eligible = true;
            } else if (!hero.hasUnlockedArea2() && hero.canEnterArea1() && hero.getHaveDefeatedArea1Boss()) { 

                //narrationHandler.area2Eligible();

                hero.unlockArea2(true);

                if(hero.getSwordmanCharacterChosen()) {
                    swordsmanPlotHandlder.swordsmanAfterArea1();
                } else if(hero.getGunnerCharacterChosen()) {
                    gunnerPlotHandler.gunnerAfterArea1();
                } else if(hero.getMageCharacterChosen()) {
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
                scanner.nextLine();

                eligible = true;

            }  else if (!hero.hasUnlockedArea3() && hero.canEnterArea2() && hero.getHaveDefeatedArea2Boss()) {

                //narrationHandler.area3Eligible();

                hero.unlockArea3(true);

                if(hero.getSwordmanCharacterChosen()) {
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
                scanner.nextLine();

                eligible = true;
            
        }

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
