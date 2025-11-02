package Menu;

import TrainingGround.*;
import Hero.*;
import DesignRelated.*;

import java.util.Scanner;

public class TrainingMenu {

    static Scanner scanner = new Scanner(System.in);
    
    private Training trainingHandler;

    public TrainingMenu(Training trainingHandler) {
        this.trainingHandler = trainingHandler;
    }
    
    public void trainingMenu(Hero hero) {
        MenuRelated menuRelatedHandler = new MenuRelated();
        boolean training = true; 

        while (training) {
            
            menuRelatedHandler.trainingMenu();

            try {
                // Convert input to integer
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println();
                        System.out.println("┌────────────────────────────┐");
                        System.out.println("│   + Endurance Training +   │");
                        System.out.println("└────────────────────────────┘");

                        if (hero.hasFinishedEndurance()) {
                            System.out.println();
                            System.out.println("┌─────────────────────────────────────────────┐");
                            System.out.println("│     You have already mastered Endurance     │");
                            System.out.println("│     Please choose another training type     │");
                            System.out.println("└─────────────────────────────────────────────┘");
                        } else {
                            trainingHandler.generalTrainingPrompt(hero, "endurance");
                            training = false;
                        }
                        break;

                    case 2:
                        System.out.println();
                        System.out.println("┌───────────────────────────┐");
                        System.out.println("│   + Strength Training +   │");
                        System.out.println("└───────────────────────────┘");

                        if (hero.hasFinishedStrength()) {
                            System.out.println();
                            System.out.println("┌─────────────────────────────────────────────┐");
                            System.out.println("│     You have already mastered Strength!     │");
                            System.out.println("│     Please choose another training type     │");
                            System.out.println("└─────────────────────────────────────────────┘");
                        } else {
                            trainingHandler.generalTrainingPrompt(hero, "strength");
                            training = false;
                        }
                        break;

                    case 3:
                        System.out.println();
                        System.out.println("┌───────────────────────────────┐");
                        System.out.println("│    + Durability Training +    │");
                        System.out.println("└───────────────────────────────┘");

                        if (hero.hasFinishedDurability()) {
                            System.out.println();
                            System.out.println("┌─────────────────────────────────────────────┐");
                            System.out.println("│     You have already mastered Durability    │");
                            System.out.println("│     Please choose another training type     │");
                            System.out.println("└─────────────────────────────────────────────┘");
                        } else {
                            trainingHandler.generalTrainingPrompt(hero, "durability");
                            training = false;
                        }
                        break;

                    case 4:
                        System.out.println();
                        System.out.println("┌──────────────────────────────────┐");
                        System.out.println("│   + Mana Refinement Training +   │");
                        System.out.println("└──────────────────────────────────┘");

                        if (hero.hasFinishedManaRefinement()) {
                            System.out.println();
                            System.out.println("┌─────────────────────────────────────────────┐");
                            System.out.println("│  You have already mastered Mana Refinement! │");
                            System.out.println("│     Please choose another training type     │");
                            System.out.println("└─────────────────────────────────────────────┘");
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
                        continue;
                }
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("┌──────────────────────────────────────────┐");
                System.out.println("│   Invalid input! Please enter a number.  │");
                System.out.println("└──────────────────────────────────────────┘");
            } catch (Exception e) {
                System.out.println();
                System.out.println("┌──────────────────────────────────────────────┐");
                System.out.println("│   An unexpected error occurred. Try again.   │");
                System.out.println("└──────────────────────────────────────────────┘");
                scanner.nextLine();
            }
        }
    }


}
