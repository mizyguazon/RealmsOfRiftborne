package com.ror.engine.menu;

import com.ror.models.*;
import com.ror.engine.narration.*;
import com.ror.engine.area.*;
import com.ror.engine.design.*;


import java.util.Scanner;

public class Menu extends Narration {

    static Scanner scanner = new Scanner(System.in);

    public void mainMenu(Hero hero){
        AcademyMenu handler = new AcademyMenu();
        ForestOfReverie forest = new ForestOfReverie();
        ReveriesEdge reverieEdge = new ReveriesEdge();
        ForsakenLands forsakenLands = new ForsakenLands();
        ShopRelated shopPromptHandler = new ShopRelated();
        MenuRelated menuRelatedHandler = new MenuRelated();
        AreaRelated areaHandler = new AreaRelated();
        InnerCharacterMenu innerCharacterMenuHandler = new InnerCharacterMenu();
        IntroTitle loadHandler = new IntroTitle();
        Narration separatorHandler = new Narration();

        while(true){

            separatorHandler.promptSeparator();
            menuRelatedHandler.mainMenu();
            System.out.print("-->| ");

            try {

                int mainMenuChoice = scanner.nextInt();
                scanner.nextLine();
                
                switch (mainMenuChoice){
                    case 1:

                        if (!hero.hasVisitedAcademy()) {
                            academyNarration();
                            hero.setHasVisitedAcademy(true);
                        }  
                        
                        handler.academyMapMenu(hero);
                        break;

                    /*case 2:

                        separatorHandler.promptSeparator();
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

                        shop.shop(hero);
                        break;
                    */

                    case 2:
                
                        separatorHandler.promptSeparator();
                        shopPromptHandler.inventoryPrompt();

                        if (!hero.hasOpenedInventory()) {
                            inventoryNarration();
                            hero.setHasOpenedInventory(true);
                        }

                            hero.getInventory().inventory();

                        break;

                    case 3:

                        loadHandler.loadGame();

                        if (hero.hasUnlockedArea1()) {

                            separatorHandler.promptSeparator();
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

                            separatorHandler.promptSeparator();
                            areaHandler.forestOfReverieNOtEligible();
                            
                        }

                        //loadHandler.exitGame();

                        break;

                    case 4:

                        loadHandler.loadGame();

                        if (hero.hasUnlockedArea2()) {

                            separatorHandler.promptSeparator();
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

                            separatorHandler.promptSeparator();
                            areaHandler.reverieEdgeNotEligible();

                        }

                        //loadHandler.exitGame();

                        break;

                    case 5:

                        loadHandler.loadGame();

                        if (hero.hasUnlockedArea3()) {

                            separatorHandler.promptSeparator();
                            areaHandler.forsakenLandsEligible();

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

                            separatorHandler.promptSeparator();
                            areaHandler.forsakenLandsNotEligible();
            
                        }

                        //loadHandler.exitGame();

                        break;

                    case 6:

                        separatorHandler.promptSeparator();

                        if (hero.getSwordmanCharacterChosen()) {
                            Hero result = innerCharacterMenuHandler.playerSwordsman(hero);

                            if (result == null) {
                                break;
                            }

                        } else if (hero.getGunnerCharacterChosen()) {
                            Hero result = innerCharacterMenuHandler.playerGunner(hero);

                            if (result == null) {
                                break;
                            } 

                        } else if (hero.getMageCharacterChosen()) {
                            Hero result = innerCharacterMenuHandler.playerMage(hero);

                            if (result == null) {
                                break;
                            } 
                        }

                        break;
                        

                    case 7:
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

                                        loadHandler.exitGame();
                                        separatorHandler.promptSeparator();
                                        loadHandler.mystvaleOutroTitle();

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

                                        loadHandler.loadGame();
                                        separatorHandler.promptSeparator();
                                        confirmExit = false; 

                                        break;

                                    default:
                                        System.out.println();
                                        System.out.println("┌────────────────────────────────────────┐");
                                        System.out.println("│   Choice unclear! Enter 'y' or 'n'.    │");
                                        System.out.println("└────────────────────────────────────────┘");
                                        separatorHandler.promptSeparatorResized();
                                        break;
                                }
                            } catch (Exception e) {
                                System.out.println();
                                System.out.println("┌──────────────────────────────────────────────┐");
                                System.out.println("│   An unexpected error occurred. Try again.   │");
                                System.out.println("└──────────────────────────────────────────────┘");
                                separatorHandler.promptSeparatorResized();
                                scanner.nextLine(); 
                            }
                        }

                        break;

                    default:
                        System.out.println();
                        System.out.println("┌─────────────────────────────────────┐");
                        System.out.println("│  Oops! Invalid choice. Try again.   │");
                        System.out.println("└─────────────────────────────────────┘");
                        separatorHandler.promptSeparator();
                }

            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("┌──────────────────────────────────────────┐");
                System.out.println("│   Invalid input! Please enter a number.  │");
                System.out.println("└──────────────────────────────────────────┘");
                separatorHandler.promptSeparator();
            } catch (Exception e) {
                System.out.println();
                System.out.println("┌──────────────────────────────────────────────┐");
                System.out.println("│   An unexpected error occurred. Try again.   │");
                System.out.println("└──────────────────────────────────────────────┘");
                scanner.nextLine(); 

                separatorHandler.promptSeparator();
            }
        }
    }
}
