package Menu;

import Hero.*;
import Library.LibraryAcademy;
import TrainingGround.*;
import DesignRelated.*;
import Office.*;

public class AcademyMenu extends Menu{

    public void academyMapMenu(Hero hero) {
        boolean academyMapMenu = true;
        Training trainingGroundHandler = new Training();
        LibraryAcademy libraryHandler = new LibraryAcademy();
        PrincipalOffice officeHandler = new PrincipalOffice();
        AcademyMap mapHandler = new AcademyMap();
        IntroTitle introHandler = new IntroTitle();
        MenuRelated menuRelatedHanlder = new MenuRelated();
        ShopRelated shopPromptHandler = new ShopRelated();

        while (academyMapMenu) {
            
            menuRelatedHanlder.academyMapMenu();
            System.out.print("-->| ");

            try {
                int academyMapMenuChoice = scanner.nextInt();

                switch (academyMapMenuChoice) {
                    case 1:

                        mapHandler.characterInsideLibrary();

                        System.out.println();
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - -  - - - - <<<<<");
                        System.out.println("      ┌───────────────────────────────────────┐");
                        System.out.println("      │   + You are now inside the Library +  │");
                        System.out.println("      └───────────────────────────────────────┘");
                        System.out.println("     ┌──────────────────────────────────────────┐");
                        System.out.println("     │   Shh! Be mindful, others are studying   │");
                        System.out.println("     └──────────────────────────────────────────┘");
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - <<<<<");

                        if(!hero.hasVisitedLibrary()){
                            libraryNarration();
                            hero.setVisitedLibrary(true);
                        }

                        libraryHandler.libraryAcademy(hero);

                        break;

                    case 2:

                        mapHandler.characterInsideTrainingGround();

                        System.out.println();
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");
                        System.out.println("        ┌────────────────────────────────────────────────┐");
                        System.out.println("        │   + You are now inside the Training Ground +   │");
                        System.out.println("        └────────────────────────────────────────────────┘");
                        System.out.println("     ┌───────────────────────────────────────────────────────┐");
                        System.out.println("     │   The air grows tense. It's time to prove your skill  │");
                        System.out.println("     └───────────────────────────────────────────────────────┘");
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");

                        if (!hero.hasVisitedGym()) {
                            gymNarration();
                            hero.setVisitedGym(true);
                        }

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
                        mapHandler.characterInsidePrincipalOffice();

                        System.out.println();
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - <<<<<");
                        System.out.println("     ┌───────────────────────────────────────┐");
                        System.out.println("     │   + You are now inside the Office +   │");
                        System.out.println("     └───────────────────────────────────────┘");
                        System.out.println("       ┌───────────────────────────────────┐");
                        System.out.println("       │   May the odds be in your favor   │");
                        System.out.println("       └───────────────────────────────────┘");
                        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - <<<<<");

                        if (!hero.hasVisitedOffice()) {
                            principalOfficeNarration();
                            hero.setVisitedOffice(true);
                        }
                        System.out.println();

                        officeHandler.principalOffice(hero);

                        break;

                    case 4:
                        shopPromptHandler.shopPrompt();

                        if (!hero.hasVisitedShop()) {
                            shopNarration();
                            hero.setHasVisitedShop(true);
                        }

                        if(!hero.getConversedWithShop()){
                            shopConversationNarration();
                            hero.setConversedWithShop(true);
                        }

                        //shopFunction();

                        break;
                        

                    case 5:
                       
                        introHandler.exitAcademy();

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