package Menu;

import Hero.*;
import Narration.*;
import Area.*;

import java.util.Scanner;

public class Menu extends Narration {
    static Scanner scanner = new Scanner(System.in);

    public void mainMenu(Hero hero){
        AcademyMenu handler = new AcademyMenu();
        ForestOfReverie forest = new ForestOfReverie();
        ReveriesEdge reverieEdge = new ReveriesEdge();
        ForsakenLands forsakenLands = new ForsakenLands();
        
        while(true){
            System.out.println("+------------------------------------------+");
            System.out.println("|    --- MYSTVALE ACADEMY MAIN MENU ---    |");
            System.out.println("+------------------------------------------+");
            System.out.println("| [1] Go to Academy                        |");
            System.out.println("| [2] Shop                                 |");
            System.out.println("| [3] Inventory                            |");
            System.out.println("| [4] The Forest of Reverie                |");
            System.out.println("| [5] The Reverie Edge                     |");
            System.out.println("| [6] The Forsaken Lands                   |");
            System.out.println("| [7] Exit Game                            |");
            System.out.println("+------------------------------------------+");
            System.out.println("┌──────────────────────────────┐");
            System.out.println("│   Where do you want to go?   │");
            System.out.println("└──────────────────────────────┘");
            System.out.print(">>> ");

            
            String mainMenuChoice = scanner.nextLine();
            
            System.out.println();
            
            switch (mainMenuChoice){
                case "1":
                    if (!hero.hasVisitedAcademy()) {
                        academyNarration();
                        hero.setHasVisitedAcademy(true);
                    } 

                    System.out.println();
                    System.out.println("┌──────────────────────────────────────────────┐");
                    System.out.println("│        You are now inside the Academy        │");
                    System.out.println("└──────────────────────────────────────────────┘");

                    handler.academyMapMenu(hero);
                    break;

                case "2":
                    if (!hero.hasVisitedShop()) {
                        shopNarration();
                        hero.setHasVisitedShop(true);
                    }
                        
                    System.out.println();
                    System.out.println("┌──────────────────────────┐");
                    System.out.println("│    Welcome to the shop   │");
                    System.out.println("└──────────────────────────┘");
                    
                    //shopFunction();
                    break;

                case "3":
                    boolean isInventoryEmpty = false; // default case since wala pay inventory

                    if (!hero.hasOpenedInventory()) {
                        inventoryNarration();
                        hero.setHasOpenedInventory(true);
                    }
                        System.out.println();
                        System.out.println("┌────────────────────────────────────┐");
                        System.out.println("│       This is your invetory        │");
                        System.out.println("└────────────────────────────────────┘");
                    
                    if(!isInventoryEmpty){
                        System.out.println("┌───────────────────────────────────────┐");
                        System.out.println("│      Hmmm. Nothing to see here.       │");
                        System.out.println("│   Go shop if you want to own items.   │");
                        System.out.println("└───────────────────────────────────────┘");
                    } else{
                        //inventoryFunction();
                    }
                    
                    break;

                case "4":
                    
                    if (hero.canEnterArea1()) {
                        System.out.println("┌───────────────────────────────────────────────┐");
                        System.out.println("│    You may now enter The Forest of Reverie    │");
                        System.out.println("└───────────────────────────────────────────────┘");


                        if (!hero.hasVisitedArea1()) {
                            area1Narration();
                            hero.setHasVisitedArea1(true);
                        }

                        System.out.println();
                        System.out.println("┌────────────────────────────────────┐");
                        System.out.println("│      Beware of forest entities     │");
                        System.out.println("└────────────────────────────────────┘");

                        forest.enter(hero);

                    } else {
                        System.out.println();
                        System.out.println("┌──────────────────────────────────────────────────────────────┐");
                        System.out.println("│     You are not eligible to enter The Forest of Reverie      │");
                        System.out.println("│      Visit the Principal's Office to unlock this area        │");
                        System.out.println("└──────────────────────────────────────────────────────────────┘");
                    }

                    break;
                    
                case "5":
                    
                    if (hero.canEnterArea2()) {
                        System.out.println("┌──────────────────────────────────────────────┐");
                        System.out.println("│      You may now enter The Reverie Edge      │");
                        System.out.println("└──────────────────────────────────────────────┘");

                        
                        if (!hero.hasVisitedArea2()) {
                            area2Narration();
                            hero.setHasVisitedArea2(true);
                        }

                        System.out.println();
                        System.out.println("┌────────────────────────────────┐");
                        System.out.println("│    Beware of swamp entities    │");
                        System.out.println("└────────────────────────────────┘");

                        reverieEdge.enter(hero);

                    } else {
                        System.out.println();
                        System.out.println("┌──────────────────────────────────────────────────────────────┐");
                        System.out.println("│        You are not eligible to enter The Reverie Edge        │");
                        System.out.println("│       Visit the Principal's Office to unlock this area       │");
                        System.out.println("└──────────────────────────────────────────────────────────────┘");
                    }

                    break;
                    
                case "6":
                    
                   if (hero.canEnterArea3()) {
                        System.out.println("┌──────────────────────────────────────────────┐");
                        System.out.println("│     You may now enter The Forsaken Lands     │");
                        System.out.println("└──────────────────────────────────────────────┘");

                        
                        if (!hero.hasVisitedArea3()) {
                            area3Narration();
                            hero.setHasVisitedArea3(true);
                        }

                        System.out.println();
                        System.out.println("┌──────────────────────────────────────────────────┐");
                        System.out.println("│    Warning! You may or may not come out alive    │");
                        System.out.println("└──────────────────────────────────────────────────┘");

                        forsakenLands.enter(hero);

                    } else {
                        System.out.println();
                        System.out.println("┌──────────────────────────────────────────────────────────────┐");
                        System.out.println("│       You are not eligible to enter The Forsaken Lands       │");
                        System.out.println("│       Visit the Principal's Office to unlock this area       │");
                        System.out.println("└──────────────────────────────────────────────────────────────┘");
                    }

                    break;

                case "7":
                    System.out.println("┌──────────────────────────────────────────────────────────┐");
                    System.out.println("│       Are you sure you want to quit playing? (y/n)       │");
                    System.out.println("└──────────────────────────────────────────────────────────┘");
                    System.out.print(">>> ");
                    
                    String ifWantToQuit = scanner.nextLine();

                    switch (ifWantToQuit.toLowerCase()) {
                        case "y":
                            System.out.println();
                            System.out.println("┌────────────┐");
                            System.out.println("│ Good Game! │");
                            System.out.println("└────────────┘");
                            System.exit(0);
                            break;

                        case "n":
                            System.out.println();
                            System.out.println("┌────────────────────────────┐");
                            System.out.println("│  Returning to Main Menu... │");
                            System.out.println("└────────────────────────────┘");
                            break;

                        default:
                            System.out.println();
                            System.out.println("┌────────────────────────────┐");
                            System.out.println("│  Invalid input. Try again! │");
                            System.out.println("└────────────────────────────┘");
                            break;
                    }
                    break;

                default:
                    System.out.println();
                    System.out.println("┌─────────────────────────────┐");
                    System.out.println("│  Invalid choice. Try again! │");
                    System.out.println("└─────────────────────────────┘");
            }
        }

    }
    
}