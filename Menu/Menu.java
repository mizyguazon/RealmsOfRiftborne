package Menu;

import Hero.*;
import Narration.*;
import Area.*;
import DesignRelated.*;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Menu extends Narration {

    static Scanner scanner = new Scanner(System.in);
    static DecimalFormat df = new DecimalFormat("0.00");

    public void mainMenu(Hero hero){
        AcademyMenu handler = new AcademyMenu();
        ForestOfReverie forest = new ForestOfReverie();
        ReveriesEdge reverieEdge = new ReveriesEdge();
        ForsakenLands forsakenLands = new ForsakenLands();
        //AcademyMap mapHandler = new AcademyMap();
        IntroTitle exitHandler = new IntroTitle();
        ShopRelated shopPromptHandler = new ShopRelated();
        MenuRelated menuRelatedHandler = new MenuRelated();
        AreaRelated areaHandler = new AreaRelated();
        Characters characterStatsHandler = new Characters();


        while(true){

            menuRelatedHandler.mainMenu();
            System.out.print("-->| ");

            try {

                int mainMenuChoice = scanner.nextInt();
                scanner.nextLine();
                
                switch (mainMenuChoice){
                    case 1:

                        //mapHandler.academyMap();

                        System.out.println();
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - <<<<<");
                        System.out.println("     ┌────────────────────────────────────┐");
                        System.out.println("     │   You are now inside the Academy   │");
                        System.out.println("     └────────────────────────────────────┘");
                        System.out.println("         ┌────────────────────────────┐");
                        System.out.println("         │   Let the magic guide you  │");
                        System.out.println("         └────────────────────────────┘");
                        System.out.println(">>>>> - - - - - - - - - - - - - - -  - - - <<<<<");

                        if (!hero.hasVisitedAcademy()) {
                            academyNarration();
                            hero.setHasVisitedAcademy(true);
                        } 

                        

                        handler.academyMapMenu(hero);
                        break;

                    case 2:

                        shopPromptHandler.shopPrompt();

                        if (!hero.hasVisitedShop()) {
                            shopNarration();
                            hero.setHasVisitedShop(true);
                        }
                        
                        System.out.println("┌───────────────────────────────────┐");
                        System.out.println("│      The shop owner wants to      │");
                        System.out.println("│   have a conversation with you.   │");
                        System.out.println("└───────────────────────────────────┘");

                        if(!hero.getConversedWithShop()){
                            shopConversationNarration();
                            hero.setConversedWithShop(true);
                        }

                        //shopFunction();
                        break;

                    case 3:
                        boolean isInventoryEmpty = false; // default case since wala pay inventory

                        shopPromptHandler.inventoryPrompt();

                        if (!hero.hasOpenedInventory()) {
                            inventoryNarration();
                            hero.setHasOpenedInventory(true);
                        }

                        if(!isInventoryEmpty){
                            System.out.println("┌───────────────────────────────────────┐");
                            System.out.println("│      Hmmm. Nothing to see here.       │");
                            System.out.println("│   Go shop if you want to own items.   │");
                            System.out.println("└───────────────────────────────────────┘");
                        } else{
                            //inventoryFunction();
                        }

                        break;

                    case 4:

                        if (hero.canEnterArea1()) {

                            areaHandler.forestOfReverieEligible();

                            if (!hero.hasVisitedArea1()) {
                                area1Narration();
                                hero.setHasVisitedArea1(true);
                            } 

                            System.out.println();
                            System.out.println("┌───────────────────────────────┐");
                            System.out.println("│   Beware of forest entities   │");
                            System.out.println("└───────────────────────────────┘");

                            forest.enter(hero);

                        } else {

                            areaHandler.forestOfReverieNOtEligible();
                            
                        }

                        break;

                    case 5:
                        if (hero.canEnterArea2()) {

                            areaHandler.reverieEdgeEligible();

                            if (!hero.hasVisitedArea2()) {
                                area2Narration();
                                hero.setHasVisitedArea2(true);
                            }

                            System.out.println();
                            System.out.println("┌──────────────────────────────┐");
                            System.out.println("│   Beware of swamp entities   │");
                            System.out.println("└──────────────────────────────┘");

                            reverieEdge.enter(hero);

                        } else {

                            areaHandler.reverieEdgeNotEligible();

                        }

                        break;

                    case 6:
                        if (hero.canEnterArea3()) {
                            System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");
                            System.out.println("     ┌───────────────────────────────────────────────┐");
                            System.out.println("     │   + You may now enter The Forsaken Lands +    │");
                            System.out.println("     └───────────────────────────────────────────────┘");
                            System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");

                            if (!hero.hasVisitedArea3()) {
                                area3Narration();
                                hero.setHasVisitedArea3(true);
                            }

                            System.out.println();
                            System.out.println("┌────────────────────────────────────────────────┐");
                            System.out.println("│   Warning! You may or may not come out alive   │");
                            System.out.println("└────────────────────────────────────────────────┘");

                            forsakenLands.enter(hero);

                        } else {
                            System.out.println();
                            System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");
                            System.out.println("     ┌──────────────────────────────────────────────────────────────┐");
                            System.out.println("     │       You are not eligible to enter The Forsaken Lands       │");
                            System.out.println("     │       Visit the Principal's Office to unlock this area       │");
                            System.out.println("     └──────────────────────────────────────────────────────────────┘");
                            System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");
                        }

                        break;

                    case 7:

                        if(hero.getSwordmanCharacterChosen()) {

                            characterStatsHandler.swordsmanCharacterStats(hero);
                            break;
                        } else if (hero.getGunnerCharacterChosen()) {

                            characterStatsHandler.gunnerCharacterStats(hero);
                            break;
                        } else if (hero.getMageCharacterChosen()) {

                            characterStatsHandler.mageCharacterStats(hero);
                            break;
                        }
                        
                        

                        // Part ni Ray haha

                        break;
                        

                    case 8:
                        boolean confirmExit = true;

                        while(confirmExit) {
                            System.out.println("┌───────────────────────────────────────────────────┐");
                            System.out.println("│   Are you sure you want to quit playing? (y/n)    │");
                            System.out.println("└───────────────────────────────────────────────────┘");
                            System.out.print("-->| ");

                            try {

                                 String ifWantToQuit = scanner.nextLine();

                                switch (ifWantToQuit) {
                                    case "y":
                                    case "Y":

                                        exitHandler.exitingUnfinishedGame();

                                        System.exit(0);
                                        break;

                                    case "n":
                                    case "N":
                                        System.out.println();
                                        System.out.println("┌───────────────────────────┐");
                                        System.out.println("│   >>> Exit Declined! <<<  │");
                                        System.out.println("└───────────────────────────┘");
                                        System.out.println("┌───────────────────────────┐");
                                        System.out.println("│   Returning to Main Menu  │");
                                        System.out.println("└───────────────────────────┘");
                                        confirmExit = false; 
                                        break;

                                    default:
                                        System.out.println();
                                        System.out.println("┌────────────────────────────────────────┐");
                                        System.out.println("│   Choice unclear! Enter 'y' or 'n'.    │");
                                        System.out.println("└────────────────────────────────────────┘");
                                        break;
                                }
                            } catch (Exception e) {
                                System.out.println();
                                System.out.println("┌──────────────────────────────────────────────┐");
                                System.out.println("│   An unexpected error occurred. Try again.   │");
                                System.out.println("└──────────────────────────────────────────────┘");
                                scanner.nextLine(); 
                            }
                        }

                        break;

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
    }
}