package com.ror.engine.menu;

import com.ror.models.training.*;
import com.ror.models.*;
import com.ror.engine.narration.*;
import com.ror.engine.design.*;

import java.util.Scanner;

public class TrainingMenu {

    static Scanner scanner = new Scanner(System.in);
    private IntroTitle loadHandler = new IntroTitle();
    
    private Training trainingHandler;

    public TrainingMenu(Training trainingHandler) {
        this.trainingHandler = trainingHandler;
    }
    
    public void trainingMenu(Hero hero) {
        MenuRelated menuRelatedHandler = new MenuRelated();
        Narration separatorHandler = new Narration();
        boolean training = true; 

        while (training) {

            loadHandler.loadGame();
            separatorHandler.promptSeparator();
            menuRelatedHandler.trainingMenu();

            try {
                
                System.out.print("-->| ");
                int choice;
                
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:

                        if (hero.hasFinishedEndurance()) {
                            System.out.println();
                            System.out.println("┌─────────────────────────────────────────────┐");
                            System.out.println("│     You have already mastered Endurance     │");
                            System.out.println("│     Please choose another training type     │");
                            System.out.println("└─────────────────────────────────────────────┘");
                            System.out.println("│   Press ENTER to continue    │");
                            System.out.println("└──────────────────────────────┘");
                            scanner.nextLine();
                            scanner.nextLine();

                        } else {
                            trainingHandler.generalTrainingPrompt(hero, "endurance");
                            training = false;
                        }
                        break;

                    case 2:

                        if (hero.hasFinishedStrength()) {
                            System.out.println();
                            System.out.println("┌─────────────────────────────────────────────┐");
                            System.out.println("│     You have already mastered Strength!     │");
                            System.out.println("│     Please choose another training type     │");
                            System.out.println("└─────────────────────────────────────────────┘");
                            System.out.println("│   Press ENTER to continue    │");
                            System.out.println("└──────────────────────────────┘");
                            scanner.nextLine();
                            scanner.nextLine();

                        } else {
                            trainingHandler.generalTrainingPrompt(hero, "strength");
                            training = false;
                        }
                        break;

                    case 3:

                        if (hero.hasFinishedDurability()) {
                            System.out.println();
                            System.out.println("┌─────────────────────────────────────────────┐");
                            System.out.println("│     You have already mastered Durability    │");
                            System.out.println("│     Please choose another training type     │");
                            System.out.println("└─────────────────────────────────────────────┘");
                            System.out.println("│   Press ENTER to continue    │");
                            System.out.println("└──────────────────────────────┘");
                            scanner.nextLine();
                            scanner.nextLine();

                        } else {
                            trainingHandler.generalTrainingPrompt(hero, "durability");
                            training = false;
                        }
                        break;

                    case 4:

                        if (hero.hasFinishedManaRefinement()) {
                            System.out.println();
                            System.out.println("┌─────────────────────────────────────────────┐");
                            System.out.println("│  You have already mastered Mana Refinement! │");
                            System.out.println("│     Please choose another training type     │");
                            System.out.println("└─────────────────────────────────────────────┘");
                            System.out.println("│   Press ENTER to continue    │");
                            System.out.println("└──────────────────────────────┘");
                            scanner.nextLine();
                            scanner.nextLine();
  
                        } else {
                            trainingHandler.generalTrainingPrompt(hero, "mana refinement");
                            training = false;
                        }
                        break;

                    default:
                        System.out.println();
                        System.out.println("┌─────────────────────────────────────┐");
                        System.out.println("│   Oops! Invalid input. Try again.   │");
                        System.out.println("└─────────────────────────────────────┘");
                        separatorHandler.promptSeparatorResized();
                        continue;
                }
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("┌─────────────────────────────────────────────┐");
                System.out.println("│   Invalid input! Please enter a character.  │");
                System.out.println("└─────────────────────────────────────────────┘");
                separatorHandler.promptSeparatorResized();
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


}
