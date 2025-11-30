package Library;

import Hero.*;
import Narration.*;
import DesignRelated.*;

import java.util.Scanner;
import java.util.Random;

public class LibraryQuest extends LibraryNarration{

    private static Scanner scanner = new Scanner(System.in);
    Random random = new Random(System.nanoTime());
    private static int whichShelf = new Random().nextInt(5) + 1;
    private StatsProgressLibrary progressHandler = new StatsProgressLibrary();
    Narration separatorHandler = new Narration();
    Quests questsHandler = new Quests();
    MenuRelated bookMenuHandler = new MenuRelated();

    public void findTheLostBook(Hero hero) {
        separatorHandler.promptSeparatorResized();

        //questsHandler.bookFinding();
        separatorHandler.promptSeparatorResized();
        
        /*System.out.println("┌────────────────────────────┐");
        System.out.println("│   + Find The Lost Book +   │");
        System.out.println("└────────────────────────────┘");
        */

        boolean found = false;

        while (!found) {
            try {

                bookMenuHandler.bookFindingMenu();

                /*System.out.println("+-------------------------------------+");
                System.out.println("|   The lost book could be here...    |");
                System.out.println("+-------------------------------------+");
                System.out.println("| [1] Shelf Fiction                   |");
                System.out.println("| [2] Shelf Science                   |");
                System.out.println("| [3] Shelf Mythology                 |");
                System.out.println("| [4] Shelf Magic and Spells          |");
                System.out.println("| [5] Shelf History                   |");
                System.out.println("+-------------------------------------+");
                */
                System.out.print("-->| ");

                int shelfChoice = Integer.parseInt(scanner.nextLine());

                if (shelfChoice < 1 || shelfChoice > 5) {
                    System.out.println();
                    System.out.println("┌───────────────────────────────────────────────────────────┐");
                    System.out.println("│   Hmmm...That's not valid. Choose a number between 1-5    │");
                    System.out.println("└───────────────────────────────────────────────────────────┘");
                    separatorHandler.promptSeparatorResized();
                } else if (shelfChoice == whichShelf) {
                    System.out.println();
                    System.out.println("┌───────────────────────────────────────────────────────┐");
                    System.out.println("│   You've uncovered the lost book. Well done, hero!    │");
                    System.out.println("└───────────────────────────────────────────────────────┘");
                    separatorHandler.promptSeparatorResized();

                    if(hero.getSwordmanCharacterChosen()) {
                        swordsmanHint();
                    }

                    ifQuest1Success();
                    progressHandler.randomStatsProgress(hero);
                    found = true;
                } else {
                    System.out.println();
                    System.out.println("┌──────────────────────────────────────────────────────┐");
                    System.out.println("│   No luck here, brave seeker. Continue your quest.   │");
                    System.out.println("└──────────────────────────────────────────────────────┘");
                    separatorHandler.promptSeparatorResized();
                }
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("┌──────────────────────────────────────────────┐");
                System.out.println("│   Invalid input! Please enter a number 1-5.  │");
                System.out.println("└──────────────────────────────────────────────┘");
                separatorHandler.promptSeparatorResized();
            } catch (Exception e) {
                System.out.println();
                System.out.println("┌──────────────────────────────────────────────┐");
                System.out.println("│   An unexpected error occurred. Try again.   │");
                System.out.println("└──────────────────────────────────────────────┘");
                separatorHandler.promptSeparatorResized();
                scanner.nextLine();
            }
        }
    }

    public void swordsmanHint() {

        String[] foundHintNarration = {
            "Narration: Your eyes fall on a worn, ancient page. The words twist strangely, almost as if they are alive.",
            "Narration: Confusion prickles at your mind. The sentences speak of shadows, of betrayal... of a bloodline you think you know.",
            "Narration: Whispers rise from the parchment, faint and eerie: 'Traitors... heirs... vengeance...' You shiver, unsure if it\'s in your head.",
            "Narration: You pause. The text seems to echo something familiar, something... personal. Could this really be about you?",
            "Narration: Heart pounding, you flip the page. The cryptic verses speak of a curse, of a lineage bound by choices long past.",
            "Narration: Conflicted and bewildered, you realize: the book doesn\'t just tell a story - it is telling \'your\' story."
        };

        hintNarration(foundHintNarration);

        System.out.println("┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ Whisper of Bloodline: Within the pages, shadows speak,  │");
        System.out.println("│ Of heirs long lost, of vengeance bleak. The blood you   │");
        System.out.println("│ bear, both gift and bane, Will guide your hand through  │");
        System.out.println("│ fear and pain.                                          │");
        System.out.println("└─────────────────────────────────────────────────────────┘");

        System.out.println("Press ENTER to continue...");
        scanner.nextLine();

    }

    public void mageHint() {

        String[] foundHintNarration = {
            "Narration: Your fingers brush against a glowing, ancient page. The symbols twist unnaturally, almost as if they are alive.",
            "Narration: Confusion prickles at your mind. The lines speak of power, of cycles, of a destiny you feel you might share.",
            "Narration: Whispers rise from the page, soft and haunting: 'Chosen… conduit... destiny...' You shiver, unsure if it is imagination or fate.",
            "Narration: You pause. The words resonate strangely, echoing something personal. Could this truly be about you?",
            "Narration: Heart racing, you turn the page. Cryptic verses hint at the Nullstar Relic, your link to forces beyond comprehension.",
            "Narration: Conflicted and bewildered, you realize: this text doesn\'t just tell a story - it is speaking directly to you, guiding your path."
        };

        hintNarration(foundHintNarration);

        System.out.println("┌───────────────────────────────────────────────────────────────────────┐");
        System.out.println("│ Shadowed Choice: A looming hand that seeks your might, Will test your │");
        System.out.println("│ will and guide your fight. Each riddle solved, each verse unbound,    │");
        System.out.println("│ Reveals the truth that must be found.                                 │");
        System.out.println("└───────────────────────────────────────────────────────────────────────┘");

        System.out.println("Press ENTER to continue...");
        scanner.nextLine();
    }

    public void gunnerHint() {

        String[] foundHintNarration = {
            "Narration: Your fingers brush over a dusty page, the ink shimmering faintly under the light.",
            "Narration: Confusion clouds your mind. The words speak of danger, of hidden hands... of a responsibility you feel you might bear.",
            "Narration: Whispers rise from the parchment: 'Catalyst... rogue... choices...' You shiver, unsure if it's a warning or your own intuition.",
            "Narration: You pause. The text seems to resonate personally, echoing something only you could understand.",
            "Narration: Heart pounding, you study the verses. They hint at threats and powers beyond what you\'ve faced.",
            "Narration: Conflicted yet alert, you realize: the book doesn\'t just tell a story - it is speaking to you, guiding your path forward."
        };

        hintNarration(foundHintNarration);

        separatorHandler.promptSeparatorResized();
        System.out.println("┌────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│ Whispered Danger: The streets were still, yet voices stir, Rogue hands │");
        System.out.println("│ reach where secrets were. Your path is fraught, the choices keen, What │");
        System.out.println("│ seems unseen is yet between.                                           │");
        System.out.println("└────────────────────────────────────────────────────────────────────────┘");

        System.out.println("Press ENTER to continue...");
        scanner.nextLine();
    }


    public void riddles(Hero hero){

        separatorHandler.promptSeparatorResized();
        /*System.out.println("┌────────────────────────────┐");
        System.out.println("│   + Decode The Riddles +   │");
        System.out.println("└────────────────────────────┘");
        */

        //questsHandler.riddles();

        if(hero.isRiddle1Done() && hero.isRiddle2Done() && hero.isRiddle3Done()) {
            System.out.println("┌───────────────────────────────────────────────────┐");
            System.out.println("│   + Victory! All riddles have been conquered! +   │");
            System.out.println("└───────────────────────────────────────────────────┘");
            separatorHandler.promptSeparatorResized();

            if(hero.getMageCharacterChosen()) {
                mageHint();
            } else if(hero.getGunnerCharacterChosen()) {
                gunnerHint();
            }

            return;
        }

        int whatRiddle;
        boolean isCorrect = false;

        do {
            whatRiddle = random.nextInt(3) + 1;
        } while ((whatRiddle == 1 && hero.isRiddle1Done()) || (whatRiddle == 2 && hero.isRiddle2Done()) || (whatRiddle == 3 && hero.isRiddle3Done()));
 
        switch(whatRiddle){
            case 1:
                isCorrect = riddle1();
                
                if(isCorrect){
                    hero.setRiddle1Done(true);
                }
                break;

            case 2: 
                isCorrect = riddle2();
                
                if(isCorrect){
                    hero.setRiddle2Done(true);
                }

                break;

            case 3:
                isCorrect = riddle3();
                
                if(isCorrect) {
                    hero.setRiddle3Done(true);
                }
                break;
        }

        if(isCorrect){
            progressHandler.randomStatsProgress(hero);
        }
        
    }

    public void hintNarration(String[] array) {

        System.out.println("Narration Ahead! Not Skippable");
        System.out.println("Press ENTER to continue...");

        for (int i = 0; i < array.length; i++) {
            scanner.nextLine();
            System.out.println(array[i]);
        }

        System.out.println();
    }
    
}