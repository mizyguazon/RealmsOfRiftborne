package Menu;

import Narration.Narration;
import Area.ForestOfReverie;
import java.util.Scanner;

public class Menu extends Narration {
    static Scanner scanner = new Scanner(System.in);

    //Change these variables to private once encasulation is implemented (i think)
    private boolean hasVisitedShop = false;
    private boolean hasOpenedInventory = false;
    private boolean hasVisitedAcademy = false;
    private boolean hasVisitedArea1 = false;
    private boolean hasVisitedArea2 = false;
    private boolean hasVisitedArea3 = false;

    public void defaultMainMenu(){
        boolean runMainMenu = true;
        AcademyMenu handler = new AcademyMenu();
        ForestOfReverie forest = new ForestOfReverie();
        
        while(runMainMenu){
            System.out.println("+------------------------------------------+");
            System.out.println("|  --- MYSTVALE ACADEMY MAIN MENU ---      |");
            System.out.println("+------------------------------------------+");
            System.out.println("| 1. Go to Academy                         |");
            System.out.println("| 2. Shop                                  |");
            System.out.println("| 3. Inventory                             |");
            System.out.println("| 4. The Forest of Reverie                 |");
            System.out.println("| 5. The Reverie Edge                      |");
            System.out.println("| 6. The Forsaken Lands                    |");
            System.out.println("| 7. Exit Game                             |");
            System.out.println("+------------------------------------------+");
            System.out.println("      ┌──────────────────────────────┐");
            System.out.println("      │   Where do you want to go?   │");
            System.out.println("      └──────────────────────────────┘");
            System.out.print("      >>> ");

            
            String mainMenuChoice = scanner.nextLine();
            
            System.out.println();
            
            switch (mainMenuChoice){
                case "1":
                    if (!hasVisitedAcademy) {
                        academyNarration();
                        hasVisitedAcademy = true;
                    } 

                    System.out.println();
                    System.out.println("┌──────────────────────────────────────────────┐");
                    System.out.println("│        You are now inside the Academy        │");
                    System.out.println("└──────────────────────────────────────────────┘");

                    handler.academyMapMenu();
                    break;

                case "2":
                    if (!hasVisitedShop) {
                        shopNarration();
                        hasVisitedShop = true;
                    }
                        
                    System.out.println();
                    System.out.println("┌──────────────────────────┐");
                    System.out.println("│    Welcome to the shop   │");
                    System.out.println("└──────────────────────────┘");
                    
                    //shopFunction();
                    break;

                case "3":
                    boolean isInventoryEmpty = false;

                    if (!hasOpenedInventory) {
                        inventoryNarration();
                        hasOpenedInventory = true;
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
                    
                    // add restriction

                    if (!hasVisitedArea1) {
                        area1Narration();
                        hasVisitedArea1 = true;
                    } 
                    
                    System.out.println();
                    System.out.println("┌────────────────────────────────────┐");
                    System.out.println("│      Beware of forest entities     │");
                    System.out.println("└────────────────────────────────────┘");

                    forest.enter();
                    break;
                    
                case "5":
                    
                    // add restriction

                    if (!hasVisitedArea2) {
                        area2Narration();
                        hasVisitedArea2 = true;
                    } 
                    
                    System.out.println();
                    System.out.println("┌────────────────────────────────┐");
                    System.out.println("│    Beware of swamp entities    │");
                    System.out.println("└────────────────────────────────┘");
                    break;
                    
                case "6":
                    
                    // add restriction
                    
                    if (!hasVisitedArea3) {
                        area3Narration();
                        hasVisitedArea3 = true;
                    } 
                        
                    System.out.println();
                    System.out.println("┌──────────────────────────────────────────────────┐");
                    System.out.println("│    Warning! You may or may not come out alive    │");
                    System.out.println("└──────────────────────────────────────────────────┘");
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