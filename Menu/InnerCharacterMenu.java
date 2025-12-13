package Menu;

import DesignRelated.*;
import Hero.*;
import Narration.*;

import java.util.Scanner;

public class InnerCharacterMenu extends Narration{

    static Scanner scanner = new Scanner(System.in);

    MenuRelated menuRelatedHandler = new MenuRelated();
    Characters characterStatsHandler = new Characters();
    Narration separatorHandler = new Narration();
    IntroTitle loadReset = new IntroTitle();
    

    public Hero playerSwordsman(Hero hero) {

        boolean isValid = false; 

        separatorHandler.promptSeparator();

        while(!isValid) {
            menuRelatedHandler.swordsmanCharacterMenu();

            try {
                int choice = scanner.nextInt();

                switch(choice) {
                    case 1: 
                        separatorHandler.promptSeparatorResized();
                        swordsmanDetails();
                        separatorHandler.promptSeparator();
                        break;

                    case 2:
                        separatorHandler.promptSeparatorResized();
                        characterStatsHandler.swordsmanCharacterStats(hero);
                        separatorHandler.promptSeparator();
                        break;

                    case 3:
                        separatorHandler.promptSeparatorResized();
                        swordsmanBackstory();
                        separatorHandler.promptSeparator();
                        break;

                    case 4:
                        boolean willChange = true;
                        scanner.nextLine(); 

                        while (willChange) {
                            System.out.println("┌──────────────────────────────────────────────┐");
                            System.out.println("│  Change character? Progress won't be saved.  │");
                            System.out.println("│      Enter 'y' to confirm, 'n' to cancel     │");
                            System.out.println("└──────────────────────────────────────────────┘");
                            System.out.print("--> ");

                            try {
                                String willChangePlayer = scanner.nextLine().trim();

                                if (willChangePlayer.equalsIgnoreCase("y")) {

                                    loadReset.resetGame();

                                    hero.resetAllProgress();

                                    CharacterMenu characterMenuHandler = new CharacterMenu();
                                
                                    hero = characterMenuHandler.chooseCharacterMenu();
                                    hero.setSwordmanCharacterChosen(hero instanceof Swordsman);
                                    hero.setMageCharacterChosen(false);
                                    hero.setGunnerCharacterChosen(false);


                                } else if (willChangePlayer.equalsIgnoreCase("n")) {
                                    System.out.println();
                                    System.out.println("┌──────────────────────────────────────────┐");
                                    System.out.println("│   Continuing with current character...   │");
                                    System.out.println("└──────────────────────────────────────────┘");
                                    separatorHandler.promptSeparatorResized();
                                    willChange = false;
                                } else {
                                    System.out.println();
                                    System.out.println("┌─────────────────────────────────────┐");
                                    System.out.println("│  Invalid input. Please enter y/n.   │");
                                    System.out.println("└─────────────────────────────────────┘");
                                    separatorHandler.promptSeparatorResized();
                                }

                            } catch (Exception e) {
                                System.out.println();
                                System.out.println("┌──────────────────────────────────────────────┐");
                                System.out.println("│   Invalid input detected. Please try again.  │");
                                System.out.println("└──────────────────────────────────────────────┘");
                                scanner.nextLine(); 
                            }
                        }
                        break;

                    
                    case 5:
                        separatorHandler.promptSeparatorResized();
                        isValid = true;
                        return null;

                    default:
                        System.out.println();
                        System.out.println("┌─────────────────────────────────────┐");
                        System.out.println("│  Oops! Invalid choice. Try again.   │");
                        System.out.println("└─────────────────────────────────────┘");
                        separatorHandler.promptSeparatorResized();
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

    public Hero playerGunner(Hero hero) {
        MenuRelated menuRelatedHandler = new MenuRelated();
        Characters characterStatsHandler = new Characters();
        boolean isValid = false;

        while(!isValid) {
            menuRelatedHandler.gunnerCharacterMenu();

            try {
                int choice = scanner.nextInt();

                switch(choice) {
                    case 1: 
                        separatorHandler.promptSeparatorResized();
                        gunnerDetails();
                        separatorHandler.promptSeparator();
                        break;

                    case 2:
                        separatorHandler.promptSeparatorResized();
                        characterStatsHandler.gunnerCharacterStats(hero);
                        separatorHandler.promptSeparator();
                        break;

                    case 3:
                        separatorHandler.promptSeparatorResized();
                        gunnerBackstory();
                        separatorHandler.promptSeparator();
                        break;

                    case 4:
                        boolean willChange = true;
                        scanner.nextLine(); 

                        while (willChange) {
                            System.out.println("┌──────────────────────────────────────────────┐");
                            System.out.println("│  Change character? Progress won't be saved.  │");
                            System.out.println("│      Enter 'y' to confirm, 'n' to cancel     │");
                            System.out.println("└──────────────────────────────────────────────┘");
                            System.out.print("--> ");

                            try {
                                String willChangePlayer = scanner.nextLine().trim();

                                if (willChangePlayer.equalsIgnoreCase("y")) {

                                    loadReset.resetGame();

                                    hero.resetAllProgress();

                                    CharacterMenu characterMenuHandler = new CharacterMenu();
                                
                                    hero = characterMenuHandler.chooseCharacterMenu();
                                    hero.setSwordmanCharacterChosen(false);
                                    hero.setMageCharacterChosen(false);
                                    hero.setGunnerCharacterChosen(hero instanceof Gunner);

                                } else if (willChangePlayer.equalsIgnoreCase("n")) {
                                    System.out.println();
                                    System.out.println("┌──────────────────────────────────────────┐");
                                    System.out.println("│   Continuing with current character...   │");
                                    System.out.println("└──────────────────────────────────────────┘");
                                    separatorHandler.promptSeparatorResized();
                                    willChange = false;
                                } else {
                                    System.out.println();
                                    System.out.println("┌─────────────────────────────────────┐");
                                    System.out.println("│  Invalid input. Please enter y/n.   │");
                                    System.out.println("└─────────────────────────────────────┘");
                                    separatorHandler.promptSeparatorResized();
                                }

                            } catch (Exception e) {
                                System.out.println();
                                System.out.println("┌──────────────────────────────────────────────┐");
                                System.out.println("│   Invalid input detected. Please try again.  │");
                                System.out.println("└──────────────────────────────────────────────┘");
                                scanner.nextLine(); 
                            }
                        }
                        break;

                    
                    case 5: 
                        isValid = true;
                        return null;

                    default:
                        System.out.println();
                        System.out.println("┌─────────────────────────────────────┐");
                        System.out.println("│  Oops! Invalid choice. Try again.   │");
                        System.out.println("└─────────────────────────────────────┘");
                        separatorHandler.promptSeparatorResized();
                }


            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("┌──────────────────────────────────────────┐");
                System.out.println("│   Invalid input! Please enter a number.  │");
                System.out.println("└──────────────────────────────────────────┘");
                separatorHandler.promptSeparatorResized();
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

    public Hero playerMage(Hero hero) {
        MenuRelated menuRelatedHandler = new MenuRelated();
        Characters characterStatsHandler = new Characters();
        boolean isValid = false;
        //hero = null;

        while(!isValid) {
            menuRelatedHandler.mageCharacterMenu();

            try {
                int choice = scanner.nextInt();

                switch(choice) {
                    case 1: 
                        separatorHandler.promptSeparatorResized();
                        mageDetails();
                        separatorHandler.promptSeparator();
                        break;

                    case 2:
                        separatorHandler.promptSeparatorResized();
                        characterStatsHandler.mageCharacterStats(hero);
                        separatorHandler.promptSeparator();
                        break;

                    case 3:
                        separatorHandler.promptSeparatorResized();
                        mageBackstory();
                        separatorHandler.promptSeparator();
                        break;

                    case 4:
                        boolean willChange = true;
                        scanner.nextLine(); 

                        while (willChange) {
                            System.out.println("┌──────────────────────────────────────────────┐");
                            System.out.println("│  Change character? Progress won't be saved.  │");
                            System.out.println("│      Enter 'y' to confirm, 'n' to cancel     │");
                            System.out.println("└──────────────────────────────────────────────┘");
                            System.out.print("-->| ");

                            try {
                                String willChangePlayer = scanner.nextLine().trim();

                                if (willChangePlayer.equalsIgnoreCase("y")) {

                                    loadReset.resetGame();
                                    hero.resetAllProgress();
                                   
                                    CharacterMenu characterMenuHandler = new CharacterMenu();

                                    hero = characterMenuHandler.chooseCharacterMenu();
                                    hero.setSwordmanCharacterChosen(false);
                                    hero.setMageCharacterChosen(hero instanceof Mage);
                                    hero.setGunnerCharacterChosen(false);
                                } 
                                else if (willChangePlayer.equalsIgnoreCase("n")) {
                                    System.out.println();
                                    System.out.println("┌──────────────────────────────────────────┐");
                                    System.out.println("│   Continuing with current character...   │");
                                    System.out.println("└──────────────────────────────────────────┘");
                                    separatorHandler.promptSeparatorResized();
                                    willChange = false;
                                } 
                                else {
                                    System.out.println();
                                    System.out.println("┌─────────────────────────────────────┐");
                                    System.out.println("│  Invalid input. Please enter y/n.   │");
                                    System.out.println("└─────────────────────────────────────┘");
                                    separatorHandler.promptSeparatorResized();
                                }

                            } catch (Exception e) {
                                System.out.println();
                                System.out.println("┌──────────────────────────────────────────────┐");
                                System.out.println("│   Invalid input detected. Please try again.  │");
                                System.out.println("└──────────────────────────────────────────────┘");
                                scanner.nextLine(); 
                            }
                        }
                        break;

                         
                    
                    case 5:
                        isValid = true;
                        return null;

                    default:
                        System.out.println();
                        System.out.println("┌─────────────────────────────────────┐");
                        System.out.println("│  Oops! Invalid choice. Try again.   │");
                        System.out.println("└─────────────────────────────────────┘");
                        separatorHandler.promptSeparatorResized();
                }


            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("┌──────────────────────────────────────────┐");
                System.out.println("│   Invalid input! Please enter a number.  │");
                System.out.println("└──────────────────────────────────────────┘");
                separatorHandler.promptSeparatorResized();
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
    
