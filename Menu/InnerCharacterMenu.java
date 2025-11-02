package Menu;

import DesignRelated.*;
import Hero.*;
import Narration.Narration;

import java.util.Scanner;

public class InnerCharacterMenu extends Narration{

    static Scanner scanner = new Scanner(System.in);
    

    public Hero playerSwordsman() {
        MenuRelated menuRelatedHandler = new MenuRelated();
        boolean isValid = false;
        Hero hero = null;

        while(!isValid) {
            menuRelatedHandler.swordsmanCharacterMenu();

            try {
                int choice = scanner.nextInt();

                switch(choice) {
                    case 1: 
                        swordsmanDetails();
                        break;

                    case 2:
                        swordsmanStats();
                        break;

                    case 3:
                        swordsmanBackstory();
                        break;

                    case 4:
                        System.out.println();
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");
                        System.out.println("      ┌───────────────────────────────────────────────────────────────────────────────────┐");
                        System.out.println("      │   The winds whisper your choice... The path of the Swordsman is yours to walk.    │");
                        System.out.println("      └───────────────────────────────────────────────────────────────────────────────────┘");
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");
                        

                        hero =  new Swordsman();
                        hero.setSwordmanCharacterChosen(true);
                        isValid = true;
                        break;

                    case 5: 
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");
                        System.out.println("      ┌────────────────────────────────────────────────────────────────────┐");
                        System.out.println("      │   The fates allow you to choose again. Another destiny awaits...   │");
                        System.out.println("      └────────────────────────────────────────────────────────────────────┘");
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");
                        return null;

                    default:
                        System.out.println();
                        System.out.println("┌─────────────────────────────────────┐");
                        System.out.println("│  Oops! Invalid choice. Try again.   │");
                        System.out.println("└─────────────────────────────────────┘");
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

        return hero;
        
    }

    public Hero playerGunner() {
        MenuRelated menuRelatedHandler = new MenuRelated();
        boolean isValid = false;
        Hero hero = null;

        while(!isValid) {
            menuRelatedHandler.gunnerCharacterMenu();

            try {
                int choice = scanner.nextInt();

                switch(choice) {
                    case 1: 
                        gunnerDetails();
                        break;

                    case 2:
                        gunnerStats();
                        break;

                    case 3:
                        gunnerBackstory();
                        break;

                    case 4:
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");
                        System.out.println("      ┌────────────────────────────────────────────────────────────────────────────────┐");
                        System.out.println("      │   The echo of gunfire resounds through the void - you are the chosen Gunner.   │");
                        System.out.println("      └────────────────────────────────────────────────────────────────────────────────┘");
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");

                        hero = new Gunner();
                        hero.setGunnerCharacterChosen(true);
                        isValid = true;
                        break;

                    case 5: 
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");
                        System.out.println("      ┌────────────────────────────────────────────────────────────────┐");
                        System.out.println("      │   The visions shift... perhaps another path calls your name.   │");
                        System.out.println("      └────────────────────────────────────────────────────────────────┘");
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");

                        return null;

                    default:
                        System.out.println();
                        System.out.println("┌─────────────────────────────────────┐");
                        System.out.println("│  Oops! Invalid choice. Try again.   │");
                        System.out.println("└─────────────────────────────────────┘");
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

        return hero;
        
    }

    public Hero playerMage() {
        MenuRelated menuRelatedHandler = new MenuRelated();
        boolean isValid = false;
        Hero hero = null;

        while(!isValid) {
            menuRelatedHandler.mageCharacterMenu();

            try {
                int choice = scanner.nextInt();

                switch(choice) {
                    case 1: 
                        mageDetails();
                        break;

                    case 2:
                        mageStats();
                        break;

                    case 3:
                        mageBackstory();
                        break;

                    case 4:
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");
                        System.out.println("      ┌──────────────────────────────────────────────────────────────────────────────────┐");
                        System.out.println("      │   The stars align as the arcane accepts you. You are now the Mage of destiny.    │");
                        System.out.println("      └──────────────────────────────────────────────────────────────────────────────────┘");
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");

                        hero = new Mage();
                        hero.setMageCharacterChosen(true);
                        isValid = true;
                        break;

                    case 5: 
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");
                        System.out.println("      ┌───────────────────────────────────────────────────────────────────────────┐");
                        System.out.println("      │   The scrolls fade from your grasp — you seek another legend to become.   │");
                        System.out.println("      └───────────────────────────────────────────────────────────────────────────┘");
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");

                        return null;

                    default:
                        System.out.println();
                        System.out.println("┌─────────────────────────────────────┐");
                        System.out.println("│  Oops! Invalid choice. Try again.   │");
                        System.out.println("└─────────────────────────────────────┘");
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

        return hero;
        
    }

    

}
