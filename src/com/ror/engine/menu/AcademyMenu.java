package com.ror.engine.menu;

import com.ror.models.*;
import com.ror.utils.LibraryAcademy;
import com.ror.engine.narration.Narration;
import com.ror.models.training.*;
import com.ror.engine.design.*;
import com.ror.engine.office.*;
import com.ror.engine.shop.Shop;

public class AcademyMenu extends Menu{

    public void academyMapMenu(Hero hero) {
        boolean academyMapMenu = true;
        Training trainingGroundHandler = new Training();
        LibraryAcademy libraryHandler = new LibraryAcademy();
        PrincipalOffice officeHandler = new PrincipalOffice();
        AcademyMap mapHandler = new AcademyMap();
        MenuRelated menuRelatedHanlder = new MenuRelated();
        ShopRelated shopPromptHandler = new ShopRelated();
        Narration separatorHandler = new Narration();
        IntroTitle loadHandler = new IntroTitle();
        Shop shop = new Shop();
        
        while (academyMapMenu) {
            
            separatorHandler.promptSeparator();
            menuRelatedHanlder.academyMapMenu();
            System.out.print("-->| ");

            try {
                int academyMapMenuChoice = scanner.nextInt();
                scanner.nextLine();

                switch (academyMapMenuChoice) {
                    case 1:

                        if(!hero.hasVisitedLibrary()){
                            libraryNarration();
                            hero.setVisitedLibrary(true);
                        }

                        separatorHandler.promptSeparator();
                        mapHandler.characterInsideLibrary();

                        libraryHandler.libraryAcademy(hero);

                        break;

                    case 2:

                        if (!hero.hasVisitedGym()) {
                            gymNarration();
                            hero.setVisitedGym(true);
                        }

                        separatorHandler.promptSeparator();
                        mapHandler.characterInsideTrainingGround();

                        boolean validInput = false;

                        while (!validInput) {
                            try {
                                System.out.println();
                                System.out.println("┌──────────────────────────────────────────────┐");
                                System.out.println("│   Explore Mystvale's training ground? (y/n)  │");
                                System.out.println("└──────────────────────────────────────────────┘");
                                System.out.print(">>> ");
                                String exploreInput = scanner.nextLine();

                                if (exploreInput.equalsIgnoreCase("y")) {
                                    trainingGroundHandler.trainingGround(hero);
                                    validInput = true;
                                } else if (exploreInput.equalsIgnoreCase("n")) {
                                    System.out.println("┌──────────────────────────────────────────────┐");
                                    System.out.println("│   >>> Exiting from the Training Ground <<<   │");
                                    System.out.println("└──────────────────────────────────────────────┘");
                                    loadHandler.exitGame();
                                    separatorHandler.promptSeparator();
                                
                                    validInput = true;
                                } else {
                                    System.out.println();
                                    System.out.println("┌────────────────────────────────────────┐");
                                    System.out.println("│   Choice unclear! Enter 'y' or 'n'.    │");
                                    System.out.println("└────────────────────────────────────────┘");
                                }
                            } catch (Exception e) {
                                System.out.println();
                                System.out.println("┌──────────────────────────────────────────────┐");
                                System.out.println("│   Error occurred during input. Try again.    │");
                                System.out.println("└──────────────────────────────────────────────┘");
                                scanner.nextLine(); 
                            }
                        }

                        break;


                    case 3:

                        if (!hero.hasVisitedOffice()) {
                            principalOfficeNarration();
                            hero.setVisitedOffice(true);
                        }
                        System.out.println();

                        separatorHandler.promptSeparator();
                        mapHandler.characterInsidePrincipalOffice();

                        officeHandler.principalOffice(hero);

                        break;

                    case 4:

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
                       

                    case 5:

                        loadHandler.exitGame();
                        separatorHandler.promptSeparator();

                        academyMapMenu = false;

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
