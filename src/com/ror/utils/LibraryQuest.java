package com.ror.utils;

import com.ror.models.*;
import com.ror.engine.narration.*;
import com.ror.engine.design.*;

import java.util.Scanner;
import java.util.Random;

public class LibraryQuest extends LibraryNarration{

    private static Scanner scanner = new Scanner(System.in);
    private Random random = new Random(System.nanoTime());
    private static int whichShelf = new Random().nextInt(5) + 1;
    private StatsProgressLibrary progressHandler = new StatsProgressLibrary();
    private Narration separatorHandler = new Narration();
    private Narration printDelayHandler = new Narration();
    private MenuRelated bookMenuHandler = new MenuRelated();

    public void findTheLostBook(Hero hero) {
        separatorHandler.promptSeparatorResized();
        separatorHandler.promptSeparatorResized();
        
        boolean found = false;

        while (!found) {
            try {

                bookMenuHandler.bookFindingMenu();
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

                    ifQuest1Success();

                    if(hero.getSwordmanCharacterChosen()) {
                        swordsmanHint();
                    }
                    
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
        System.out.println("│   Press ENTER to continue    │");
        System.out.println("└──────────────────────────────┘");
        scanner.nextLine();

    }

    public void mageHint() {

        String[] foundHintNarration = {
            "Narration: Your hand hovers over a faintly glowing page. Strange symbols ripple across it, as if alive with ancient magic.",
            "Narration: A shiver runs down your spine. The text speaks of power, curses, and a path intertwined with your own fate.",
            "Narration: Soft murmurs echo from the tome: 'Awaken... chosen... the path of the Arclight...' You pause, unsure if the whispers are real or conjured by your mind.",
            "Narration: The words resonate deeply, stirring memories and visions of your training and the burdens you bear. Could this truly be meant for you?",
            "Narration: Heart pounding, you turn the page. Cryptic passages hint at the Forsaken Lands and a power that could end Morvain's schemes.",
            "Narration: Bewildered yet compelled, you realize this tome does more than tell history—it reaches out to guide your steps, testing your courage and wisdom."
        };

        hintNarration(foundHintNarration);

        System.out.println("┌────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│ Flickering Glyphs: A hidden spark whispers your path, Secrets entwined │");
        System.out.println("│ with power and curse. Solve the riddle, unveil the lines,              │");
        System.out.println("│ and let the ancient magic guide your hand.                             │");
        System.out.println("└────────────────────────────────────────────────────────────────────────┘");
        System.out.println("│   Press ENTER to continue    │");
        System.out.println("└──────────────────────────────┘");
        scanner.nextLine();
    }

    public void gunnerHint() {

        String[] foundHintNarration = {
            "Narration: Your fingers graze a cold, metallic page, faint Aether energy crackling beneath your touch.",
            "Narration: Unease prickles at your senses. The text speaks of experiments, stolen power, and a destiny tied to precision and sacrifice.",
            "Narration: Whispered syllables rise from the page: 'Aether... marksman... vengeance...' You shiver, unsure if it is warning or fate.",
            "Narration: You pause, heart racing. The words strike a chord within you, echoing the trials and torment only you have endured.",
            "Narration: Each line hints at hidden threats and untold abilities, powers you might wield or must confront.",
            "Narration: Conflicted yet driven, you realize: this is not mere record-keeping - the book calls to you, guiding your path to end Project LUCENT."
        };

        hintNarration(foundHintNarration);

        separatorHandler.promptSeparatorResized();
        
        System.out.println("┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│ Aethered Echoes: Shadows shift where silence should reign, Precision    │");
        System.out.println("│ bends fate and hidden hands move. Each step measured, each strike told, │");
        System.out.println("│ The unseen marks the path you must hold.                                │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘");
        System.out.println("│   Press ENTER to continue    │");
        System.out.println("└──────────────────────────────┘");
        scanner.nextLine();
    }


    public void riddles(Hero hero){

        separatorHandler.promptSeparatorResized();

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
                isCorrect = riddle1(hero);
                
                if(isCorrect){
                    hero.setRiddle1Done(true);
                }
                break;

            case 2: 
                isCorrect = riddle2(hero);
                
                if(isCorrect){
                    hero.setRiddle2Done(true);
                }

                break;

            case 3:
                isCorrect = riddle3(hero);
                
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

        System.out.println("┌────────────────────────────────────┐");
        System.out.println("│   Narration Ahead! Not Skippable   │");
        System.out.println("└────────────────────────────────────┘");
        System.out.println("│  Press ENTER to continue  │");
        System.out.println("└───────────────────────────┘");
        scanner.nextLine();

        System.out.println();

        for (int i = 0; i < array.length; i++) {
            printDelayHandler.printWithDelay(array[i], 10); 
            System.out.println("\n");
        }

        System.out.println();
    }
    
}
