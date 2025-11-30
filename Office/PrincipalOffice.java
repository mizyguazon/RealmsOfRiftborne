package Office;

import Hero.*;
import Narration.*;
import java.util.Scanner;
import TrainingGround.StatProgress;

public class PrincipalOffice extends StatProgress {

    private OfficeNarration narrationHandler = new OfficeNarration();
    private SwordsmanPlot swordsmanPlotHandlder = new SwordsmanPlot();
    private GunnerPlot gunnerPlotHandler = new GunnerPlot();
    private MagePlot magePlotHandler = new MagePlot();
    private Scanner scanner = new Scanner(System.in);
    //private Narration separatorHandler = new Narration();

    public void principalOffice(Hero hero){
        
        if(!hero.haveEntered()){
            narrationHandler.officeNarration(); 
            hero.setHaveEntered(true);
        }

        System.out.println();
        System.out.println("┌──────────────────────────────────────────────┐");
        System.out.println("│   Determining your elegibility. Standby~~~   │");
        System.out.println("└──────────────────────────────────────────────┘");
        //separatorHandler.promptSeparatorResized();


        boolean eligible = false;

        if (!hero.canEnterArea1() && hero.hasFinishedAllTraining()) {
            
                narrationHandler.area1Eligible();
                hero.unlockArea1();

                System.out.println("┌──────────────────────────────────────────────────┐");
                System.out.println("│   + You may now enter The Forest of Reverie +    │");
                System.out.println("└──────────────────────────────────────────────────┘");
                System.out.println("Press ENTER to claim rewards...");
                scanner.nextLine();

                currencyProgress(hero);

                eligible = true;
            } else if (!hero.canEnterArea2() && hero.canEnterArea1() && hero.getHaveDefeatedArea1Boss()) { // else if. set as if for testing
                //if(hero.getHaveDefeatedArea1Boss()) {

                //narrationHandler.area2Eligible();

                /*if(hero.getSwordmanCharacterChosen()) {
                    swordsmanPlotHandlder.swordsmanAfterArea1(); // currently establishing
                } if(hero instanceof Gunner) {
                    gunnerPlotHandler.gunnerAfterArea1();
                } if(hero.getMageCharacterChosen()) {
                    // to be implemented
                }
                */

                /*if(hero instanceof Swordsman) {
                    swordsmanPlotHandlder.swordsmanAfterArea1();
                } 
                if(hero instanceof Gunner) {
                    gunnerPlotHandler.gunnerAfterArea1();
                } 
                if(hero instanceof Mage) {
                    magePlotHandler.mageAfterArea1();
                }
                    */

                if(hero.getSwordmanCharacterChosen()) {
                    swordsmanPlotHandlder.swordsmanAfterArea1();
                } else if(hero.getGunnerCharacterChosen()) {
                    gunnerPlotHandler.gunnerAfterArea1();
                } else if(hero.getMageCharacterChosen()) {
                    narrationHandler.area2Eligible();
                }

                hero.unlockArea2();

                System.out.println("┌───────────────────────────────────────────┐");
                System.out.println("│   + You may now enter The Reverie Edge +  │");
                System.out.println("└───────────────────────────────────────────┘");
                System.out.println("Press ENTER to claim rewards...");
                scanner.nextLine();

                currencyProgress(hero);

                eligible = true;

            }  else if (!hero.canEnterArea3() && hero.canEnterArea2() && hero.getHaveDefeatedArea2Boss()) {

                if(hero.getSwordmanCharacterChosen()) {
                    swordsmanPlotHandlder.swordsmanAfterArea2(); 
                } else if (hero.getMageCharacterChosen()) {
                    magePlotHandler.mageAfterArea2();
                } else if (hero.getGunnerCharacterChosen()) {
                    narrationHandler.area3Eligible();
                }
                
                hero.unlockArea3();
            
                System.out.println("┌───────────────────────────────────────────────┐");
                System.out.println("│   + You may now enter The Forsaken Lands +    │");
                System.out.println("└───────────────────────────────────────────────┘");
                System.out.println("Press ENTER to claim rewards...");
                scanner.nextLine();

                currencyProgress(hero);

                eligible = true;
            
        }
        

        if(hero.getGunnerCharacterChosen() && hero.getHaveDefeatedArea3Boss()) {
            scanner.nextLine();
            gunnerPlotHandler.gunnerEndingPlot();

        }


        if (!eligible) {

            System.out.println("┌──────────────────────────────────────────────────────────────────┐");
            System.out.println("│         You are not eligible for any outside premises            │");
            System.out.println("│   Finish your training or defeat more boss to gain elibility     │");
            System.out.println("└──────────────────────────────────────────────────────────────────┘");
            System.out.println("┌───────────────────────────────────────────────────┐");
            System.out.println("│   >>> Exiting from the Principal\'s Office <<<    │");
            System.out.println("└───────────────────────────────────────────────────┘");

        }
    }

}
