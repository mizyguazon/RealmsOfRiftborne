package TrainingGround;

import Hero.*;
import Menu.*;
import Narration.*;
import TrainingGround.Training;
import DesignRelated.*;

import java.util.Scanner;
import java.util.Random;

public class Training {

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    private TrainingMenu trainingMenuHandler = new TrainingMenu(this);
    private TrainingNarration narrationHandler = new TrainingNarration();
    private StatProgress statProgressHandler = new StatProgress();
    private Narration separatorHandler = new Narration();
    private IntroTitle loadHandler = new IntroTitle();

    public void trainingGround(Hero hero){

        if(!hero.haveExploredButExited()){ 
            narrationHandler.exploreNarration();
            hero.setHaveExploredButExited(true);
        }

        if(hero.hasFinishedAllTraining()){

                separatorHandler.promptSeparatorResized();
                System.out.println("┌─────────────────────────────────────────────┐");
                System.out.println("│    You have completed your training quest   │");
                System.out.println("│       Exiting from Training Ground...       │");
                System.out.println("└─────────────────────────────────────────────┘");
                return;
        }

        boolean isValid = true;

        while (isValid) {
            try {
                System.out.println();
                System.out.println("┌────────────────────────────────────────────────┐");
                System.out.println("│    Will you accept the training offer? (y/n)   │");
                System.out.println("└────────────────────────────────────────────────┘");
                System.out.print("-->| ");

                String wouldAccept;
                
                wouldAccept = scanner.nextLine().trim();

                //if (scanner.hasNextLine()) scanner.nextLine();

                if(wouldAccept.isEmpty()) {
                    continue;
                }

                if (wouldAccept.equalsIgnoreCase("y")) {

                    if(!hero.haveAcceptedButExited()){
                        narrationHandler.acceptedTraining();
                        hero.setHaveAcceptedButExited(true);
                    }

                    startTrainingLoop(hero);
                    isValid = false; 

                } else if (wouldAccept.equalsIgnoreCase("n")) {
                    System.out.println();
                    System.out.println("┌───────────────────────────────────────┐");
                    System.out.println("│        Training offer declined        │");
                    System.out.println("│   Exiting from the Training Ground    │");
                    System.out.println("└───────────────────────────────────────┘");
                    separatorHandler.promptSeparatorResized();

                    isValid = false;
                    return;
                } else {
                    System.out.println();
                    System.out.println("┌────────────────────────────────────────┐");
                    System.out.println("│   Choice unclear! Enter 'y' or 'n'.    │");
                    System.out.println("└────────────────────────────────────────┘");
                    separatorHandler.promptSeparatorResized();
                }
            } catch (Exception e) {
                System.out.println();
                System.out.println("┌──────────────────────────────────────────────┐");
                System.out.println("│   An unexpected error occurred. Try again.   │");
                System.out.println("└──────────────────────────────────────────────┘");
                scanner.nextLine(); 
                separatorHandler.promptSeparatorResized();
            }
        }

        return;
    }


    public void startTrainingLoop(Hero hero) {
        boolean continueTraining = true;

        int originalHp = hero.getHp();
        int originalAtk = hero.getAttack();
        int originalDef = hero.getDefense(); 
        int originalMana = hero.getMana(); 
        int originalSpeed = hero.getSpeed();
        int originalExperience = hero.getExperience();
        int originalLevel = hero.getLevel();

        while (continueTraining) {

            if(hero.getNumberOfTrainingFinished() == 4){
                //separatorHandler.promptSeparatorResized();
                System.out.println("┌───────────────────────────────────────────────────────────┐");
                System.out.println("│   + You have finished your training. Congratulations! +   │");
                System.out.println("└───────────────────────────────────────────────────────────┘");

                statProgressHandler.displayXPandLevel(hero, 2500);

                hero.setFinishedAllTraining(true);
                break;
            }

            boolean isValidInput = false;

            while (!isValidInput) {
                try {
                    System.out.println();
                    System.out.println("┌─────────────────────────────────────────────┐");
                    System.out.println("│   Do you want to continue training? (y/n)   │");
                    System.out.println("└─────────────────────────────────────────────┘");
                    System.out.print("-->| ");

                    String continueChoice;
                    
                    continueChoice = scanner.nextLine().trim();

                    //if (scanner.hasNextLine()) scanner.nextLine();

                    if(continueChoice.isEmpty()) {
                        continue;
                    }

                    if (continueChoice.equalsIgnoreCase("y")) {
                        separatorHandler.promptSeparator();
                        trainingMenuHandler.trainingMenu(hero);
                        isValidInput = true; 
                    } 
                    else if (continueChoice.equalsIgnoreCase("n")) {
                        System.out.println();
                        System.out.println("┌───────────────────────────────────────────────────┐");
                        System.out.println("│        Are you sure you want to quit? (y/n)       │");
                        System.out.println("│    Any training progress will not be recorded     │");
                        System.out.println("└───────────────────────────────────────────────────┘");
                        System.out.print("-->| ");

                        try {
                            String confirmQuit = scanner.nextLine();

                            if (confirmQuit.equalsIgnoreCase("y")) {
                                System.out.println();
                                System.out.println("┌───────────────────────────────────────────────────┐");
                                System.out.println("│   + You decided to end your training session +    │");
                                System.out.println("└───────────────────────────────────────────────────┘");
                                separatorHandler.promptSeparatorResized();

                                loadHandler.resetGame();

                                if (!hero.hasFinishedAllTraining()) {
                                    hero.setHp(originalHp);
                                    hero.setAttack(originalAtk);
                                    hero.setDefense(originalDef);
                                    hero.setMana(originalMana);
                                    hero.setSpeed(originalSpeed);
                                    hero.setExperience(originalExperience); 
                                    hero.setLevel(originalLevel);

                                    hero.setFinishedEndurance(false);
                                    hero.setFinishedStrength(false);
                                    hero.setFinishedDurability(false);
                                    hero.setFinishedManaRefinement(true);
                                    hero.setNumberOfTrainingFinished(0);
                                    hero.setFinishedAllTraining(false);

                                    separatorHandler.promptSeparatorResized();
                                    /*System.out.println();
                                    System.out.println("┌──────────────────────────────────────┐");
                                    System.out.println("│   >>> Your progress was reset <<<    │");
                                    System.out.println("└──────────────────────────────────────┘");
                                    separatorHandler.promptSeparatorResized();
                                    */

                                }

                                separatorHandler.promptSeparatorResized();
                                System.out.println("┌──────────────────────────────────────────────┐");
                                System.out.println("│   >>> Exiting from the Training Ground <<<   │");
                                System.out.println("└──────────────────────────────────────────────┘");
                                separatorHandler.promptSeparatorResized();

                                return; 
                            } 
                            else if (confirmQuit.equalsIgnoreCase("n")) {
                                System.out.println();
                                System.out.println("┌─────────────────────────────────────┐");
                                System.out.println("│   >>> Your training continues <<<   │");
                                System.out.println("└─────────────────────────────────────┘");
                                separatorHandler.promptSeparatorResized();

                                isValidInput = true;
                            } 
                            else {
                                System.out.println();
                                System.out.println("┌───────────────────────────────────────────────────┐");
                                System.out.println("│   Invalid input! Continuing training by default   │");
                                System.out.println("└───────────────────────────────────────────────────┘");
                                separatorHandler.promptSeparatorResized();

                                isValidInput = true;
                            }
                        }  catch (Exception e) {
                            System.out.println();
                            System.out.println("┌──────────────────────────────────────────────┐");
                            System.out.println("│   An unexpected error occurred. Try again.   │");
                            System.out.println("└──────────────────────────────────────────────┘");
                            scanner.nextLine(); 
                            separatorHandler.promptSeparatorResized();
                        }
                    } 
                    else {
                        System.out.println();
                        System.out.println("┌────────────────────────────────────────┐");
                        System.out.println("│   Choice unclear! Enter 'y' or 'n'.    │");
                        System.out.println("└────────────────────────────────────────┘");
                        separatorHandler.promptSeparatorResized();
                    }
                } catch (Exception e) {
                    System.out.println();
                    System.out.println("┌──────────────────────────────────────────────┐");
                    System.out.println("│   An unexpected error occurred. Try again.   │");
                    System.out.println("└──────────────────────────────────────────────┘");
                    scanner.nextLine(); 
                    separatorHandler.promptSeparatorResized();
                }
            }
        }

        System.out.println("┌──────────────────────────────────────────────┐");
        System.out.println("│   >>> Exiting from the Training Ground <<<   │");
        System.out.println("└──────────────────────────────────────────────┘");
        separatorHandler.promptSeparatorResized();
    }


    public void sparringMechanism(Hero hero, String trainingType){
        int chanceofWin = random.nextInt(10);
        StatProgress statProgressHandler = new StatProgress();

        if(chanceofWin < 7){
            hero.setNumberOfTrainingFinished(hero.getNumberOfTrainingFinished() + 1);
            System.out.println();
            System.out.println("┌────────────────────────────┐");
            System.out.println("│     Success! Well done!    │");
            System.out.println("└────────────────────────────┘");

            switch(trainingType.toLowerCase()){
                case "endurance":
                    hero.setFinishedEndurance(true);
                    statProgressHandler.endurance(hero);
                    break;

                case "strength":
                    hero.setFinishedStrength(true);
                    statProgressHandler.strength(hero);
                    break;

                case "durability":
                    hero.setFinishedDurability(true);
                    statProgressHandler.durability(hero);
                    break;

                case "mana refinement":
                    hero.setFinishedManaRefinement(true);
                    statProgressHandler.mana(hero);
                    break;
            } 


        } else {
            System.out.println();
            System.out.println("┌───────────────────────────────────┐");
            System.out.println("│      You\'ve fallen this time      │");
            System.out.println("│    Ready to give it another go?   │");
            System.out.println("└───────────────────────────────────┘");
            //separatorHandler.promptSeparatorResized();

        }
    }

    public void generalTrainingPrompt(Hero hero, String trainingType){

        /*System.out.println();
        System.out.println("┌───────────────────────────────────┐");
        System.out.println("│   >>> Training is on-going <<<    │");
        System.out.println("│      Press Enter to continue      │");
        System.out.println("└───────────────────────────────────┘");

        String[] timeDelay = {
            "-- >>> Battle is on going... Please wait... <<< --", 
            "-- >>> Battle is on going... Please wait... <<< --", 
            "-- >>> Battle is on going... Please wait... <<< --", 
            "-- >>> Battle is on going... Please wait... <<< --", 
            "-- >>> Battle is on going... Please wait... <<< --"
        };
        

        for(int i = 0; i < timeDelay.length; i++){
            scanner.nextLine();
            System.out.println(timeDelay[i]);
        }
            */

        loadHandler.onGoingBattle();
            
        separatorHandler.promptSeparatorResized();

        sparringMechanism(hero, trainingType);

        return;

    }


}
