package Library;

import Hero.*;
import Narration.*;
import DesignRelated.*;

import java.util.Scanner;
import java.util.Random;

public class LibraryAcademy {

    private static Scanner scanner = new Scanner(System.in);
    Random random = new Random(System.nanoTime());
    private LibraryQuest questHandler = new LibraryQuest();
    private LibraryNarration narrationHandler = new LibraryNarration();
    IntroTitle loadGameHandler = new IntroTitle();
    Narration separatorHandler = new Narration();

    public void libraryAcademy(Hero hero) {
        boolean isRoam = true;

        while (isRoam) {
            try {
                System.out.println();
                System.out.println("┌───────────────────────────────────────┐");
                System.out.println("│   Do you want to roam around? (y/n)   │");
                System.out.println("└───────────────────────────────────────┘");
                System.out.print("-->| ");
                String roam;
                
                roam = scanner.nextLine().trim();

                if(roam.isEmpty()) {
                    continue;
                }

                if (roam.equalsIgnoreCase("y")) {
                    System.out.println();

                    /*System.out.println();
                    System.out.println("┌─────────────────────────────┐");
                    System.out.println("│   Press Enter to continue   │");
                    System.out.println("└─────────────────────────────┘");

                    scanner.nextLine();
                    */

                    loadGameHandler.loadGame();

                    /*String[] timeDelay = {
                        "-- >>> You are now roaming around... <<< --", 
                        "-- >>> You are now roaming around... <<< --", 
                        "-- >>> You are now roaming around... <<< --", 
                        "-- >>> You are now roaming around... <<< --", 
                        "-- >>> You are now roaming around... <<< --"
                    };

                    for (int i = 0; i < timeDelay.length; i++) {
                        scanner.nextLine();
                        System.out.println(timeDelay[i]);
                    }
                        */

                    libraryQuests(hero);

                } else if (roam.equalsIgnoreCase("n")) {
                    System.out.println();
                    System.out.println("┌───────────────────────────────────┐");
                    System.out.println("│   >>> Departing the library. <<<  │");
                    System.out.println("└───────────────────────────────────┘");
                    separatorHandler.promptSeparatorResized();
                    return;
                } else {
                    System.out.println();
                    System.out.println("┌────────────────────────────────────────┐");
                    System.out.println("│   Choice unclear! Enter 'y' or 'n'.    │");
                    System.out.println("└────────────────────────────────────────┘");
                    separatorHandler.promptSeparatorResized();
                }
            } catch (Exception e) {
                System.out.println();
                System.out.println("┌─────────────────────────────────────────────┐");
                System.out.println("│   Error occurred during input. Try again.   │");
                System.out.println("└─────────────────────────────────────────────┘");
                scanner.nextLine(); 
                separatorHandler.promptSeparatorResized();
            }
        }
    }


    public void libraryQuests(Hero hero){

        boolean hasAvailableQuest = !hero.isLibraryQuest1Done() || !hero.isAllRiddlesDone();

        separatorHandler.promptSeparatorResized();

        if (!hasAvailableQuest) {
            System.out.println();
            System.out.println("┌──────────────────────────────────────────────────────────────────┐");
            System.out.println("│   + Congratrulations! You\'ve completed every library quest. +   │");
            System.out.println("└──────────────────────────────────────────────────────────────────┘");
            return;
        }

        int quests;

        do {
            quests = random.nextInt(2) + 1;
        } while ((quests == 1 && hero.isLibraryQuest1Done()) || (quests == 2 && hero.isLibraryQuest2Done()));

        switch (quests) {
            case 1:
                separatorHandler.promptSeparatorResized();
                narrationHandler.findTheLostBookNarration();
                acceptQuest(hero, 1);
                break;

            case 2:
                separatorHandler.promptSeparatorResized();
                //riddleDesign.riddles();
                narrationHandler.riddlesNarration();
                acceptQuest(hero, 2);
                break;
        }

    }

    private void acceptQuest(Hero hero, int questType) {
        boolean validInput = false;

        while (!validInput) {
            try {
                separatorHandler.promptSeparatorResized();
                System.out.println("┌────────────────────────────────────────────┐");
                System.out.println("│   Do you dare to accept the quest? (y/n)   │");
                System.out.println("└────────────────────────────────────────────┘");
                System.out.print("-->| ");

                String isAccept;
                
                isAccept = scanner.nextLine().trim();

                if(isAccept.isEmpty()) {
                    continue;
                }

                if (isAccept.equalsIgnoreCase("y")) {
                    switch (questType) {
                        case 1:
                            separatorHandler.promptSeparatorResized();
                            questHandler.findTheLostBook(hero);
                            hero.setLibraryQuest1Done(true);
                            break;
                        case 2:
                            separatorHandler.promptSeparatorResized();
                            questHandler.riddles(hero);

                            if (hero.isAllRiddlesDone()) {
                                hero.setLibraryQuest2Done(true);
                            }

                            break;
                    }
                    validInput = true;

                    //displayStats(hero);

                } else if (isAccept.equalsIgnoreCase("n")) {
                    System.out.println();
                    System.out.println("┌─────────────────────────────────────────────┐");
                    System.out.println("│   >>> Quest skipped. Maybe next time! <<<   │");
                    System.out.println("└─────────────────────────────────────────────┘");
                    separatorHandler.promptSeparatorResized();
                    validInput = true;

                } else {
                    System.out.println();
                    System.out.println("┌────────────────────────────────────────┐");
                    System.out.println("│   Choice unclear! Enter 'y' or 'n'.    │");
                    System.out.println("└────────────────────────────────────────┘");
                    separatorHandler.promptSeparatorResized();
                }
            } catch (Exception e) {
                System.out.println();
                System.out.println("┌─────────────────────────────────────────────┐");
                System.out.println("│   Error occurred during input. Try again.   │");
                System.out.println("└─────────────────────────────────────────────┘");
                scanner.nextLine(); 
                separatorHandler.promptSeparatorResized();
            }
        }
    }

}
