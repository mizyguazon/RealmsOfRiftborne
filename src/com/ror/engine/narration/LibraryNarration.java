package com.ror.engine.narration;

import java.util.Scanner;
import com.ror.engine.design.*;
import com.ror.models.*;

public class LibraryNarration {

    private static Scanner scanner = new Scanner(System.in);
    private Narration printDelayHandler = new Narration();
    private Narration separatorHandler = new Narration();
    private Quests questHandler = new Quests();

    public void findTheLostBookNarration(Hero hero){
        String[] quest1 = {
            "The library hums with ancient energy.",
            "Stacks of tomes rise like towers.",
            "The librarian names Lourdes Inkwood looks up and says,",
            "\"Student of Mystvale, an important book - The Art of Silent Spells - has gone missing.\"",
            "\"Can you help find it on one of the five nearby shelves?\""
        };

        for (int i = 0; i < quest1.length; i++) {
            printDelayHandler.printWithDelay(quest1[i], 15); 
            System.out.println("\n");
        }

        hero.setVisitedRiddles(true);

    }

    public void ifQuest1Success(){
        String[] success1 = {
            "You pull a dusty tome from the shelf - its cover glimmers faintly.",  
            "\"The Art of Silent Spells,\" you whisper.",
            "\"Marvelous! You have a keen eye, young scholar. Mystvale could use more minds like yours.\""
        };

        for (int i = 0; i < success1.length; i++) {
            printDelayHandler.printWithDelay(success1[i], 15); 
            System.out.println("\n");
        }

    }

    public void ifQuest1Fail(){
        String[] fail1 = {
            "You search carefully, but the shelf holds nothing but cobwebs and old parchment.",
            "\"Hmm... no luck? Don\'t worry. Even the books in this place like to hide.\"",  
            "\"Perhaps next time, they\'ll reveal themselves to you.\""
        };

        for (int i = 0; i < fail1.length; i++) {
            printDelayHandler.printWithDelay(fail1[i], 15); 
            System.out.println("\n");
        }

    }

    public void riddlesNarration(Hero hero){
        String[] quest3 = {
            "As you approach the Restricted Section, you see the librarian Lourdes by a flickering candle.",
            "An ancient tome lies open, letters moving across its pages.",
            "She looks up and says, \"Ah, curious student! This tome opens only to those who answer its riddles.\"",
            "\"Will you try your wit?\""
        };

        for (int i = 0; i < quest3.length; i++) {
            printDelayHandler.printWithDelay(quest3[i], 15); 
            System.out.println("\n");
        }

        hero.setVisitedRiddles(true);

    }

    public boolean riddle1(Hero hero) {
        boolean continueInput = true;
        boolean isCorrect = false;

        questHandler.riddles();

        String[] riddle1Swordsman = {
            "┌──────────────────────────────────┐",
            "│  I pass from parent to child,    │",
            "│  a weight both proud and wild.   │",
            "│  I bind the past to the now,     │",
            "│  What am I that all must endow?  │",
            "└──────────────────────────────────┘"
        };


        String[] riddle1Gunner = {
            "┌──────────────────────────────────────────────────┐",
            "│  I see what's moving though my eyes are still,   │",
            "│  I trace the angle, speed, and will.             │",
            "│  Not magic, not radar, a gift and a blight -     │",
            "│  What lets me read motion in the dark of night?  │",
            "└──────────────────────────────────────────────────┘",
        };

        String[] riddle1Mage = {
            "┌───────────────────────────────────┐",
            "│  I give strength but cause pain,  │",
            "│  a gift wrapped in fear.          │",
            "│  Use me too freely,               │",
            "│  and suffering draws near.        │",
            "└───────────────────────────────────┘"
        };

        if(hero.getSwordmanCharacterChosen()) {
            for (int i = 0; i < riddle1Swordsman.length; i++) {  
                printDelayHandler.printLineWithDelay(riddle1Swordsman[i], 25); 
            }
        } else if(hero.getGunnerCharacterChosen()) {
            for (int i = 0; i < riddle1Gunner.length; i++) {  
                printDelayHandler.printLineWithDelay(riddle1Gunner[i], 25); 
            }
        } else if(hero.getMageCharacterChosen()) {
            for (int i = 0; i < riddle1Mage.length; i++) {  
                printDelayHandler.printLineWithDelay(riddle1Mage[i], 25); 
            }
        }

        while (continueInput) {
            try {
                System.out.print("-->| ");
                String answer1 = scanner.nextLine();

                if ((answer1.equalsIgnoreCase("Vision") && hero.getGunnerCharacterChosen()) || (answer1.equalsIgnoreCase("Curse") && hero.getMageCharacterChosen()) || (answer1.equalsIgnoreCase("Legacy") && hero.getSwordmanCharacterChosen())) {
                    System.out.println();
                    System.out.println("┌───────────────────────┐");
                    System.out.println("│   Nice! You got it!   │");
                    System.out.println("└───────────────────────┘");
                    separatorHandler.promptSeparatorResized();

                    isCorrect = true;
                    continueInput = false;
                } else if (answer1.isEmpty()) {
                    System.out.println("┌──────────────────────────────────────────────────┐");
                    System.out.println("│   Input unclear! Answer must be a character\\s    │");
                    System.out.println("└──────────────────────────────────────────────────┘");
                    separatorHandler.promptSeparatorResized();
                } else {
                    System.out.println();
                    System.out.println("┌──────────────────────────────────────────┐");
                    System.out.println("│   Whoops! Better luck on the next try!   │");
                    System.out.println("└──────────────────────────────────────────┘");
                    separatorHandler.promptSeparatorResized();
                    continueInput = false;
                }
            } 
            catch (Exception e) {
                System.out.println();
                System.out.println("┌──────────────────────────────────────┐");
                System.out.println("│   Error occured in input statement   │");
                System.out.println("└──────────────────────────────────────┘");
                scanner.nextLine(); 
                separatorHandler.promptSeparatorResized();
            }
        }

        return isCorrect;
    }


    public boolean riddle2(Hero hero) {
        boolean continueInput = true;
        boolean isCorrect = false;

        questHandler.riddles();

        String[] riddle2Swordsman = {
            "┌───────────────────────────────────────┐",
            "│  I lie in wait, unseen, unheard,      │",
            "│  Revealed to those who seek my word.  │",
            "│  I guide the path if you are wise,    │",
            "│  What am I that opens eyes?           │",
            "└───────────────────────────────────────┘"
        };


        String[] riddle2Gunner = {
            "┌──────────────────────────────────────────────────────┐",
            "│  I leave the barrel in a single line,                │",
            "│  but I'll curve and twist if the user will refine.   │",
            "│  Through steel or air I find my way -                │",
            "│  What am I that strikes where foes don't stay?       │",
            "└──────────────────────────────────────────────────────┘"
        };

        String[] riddle2Mage = {
            "┌─────────────────────────────┐",
            "│  I am woven, not stitched,  │",
            "│  cast, not thrown.          │",
            "│  Shaped by intention,       │",
            "│  from thought I am grown.   │",
            "└─────────────────────────────┘"
        };

        if(hero.getSwordmanCharacterChosen()) {
            for (int i = 0; i < riddle2Swordsman.length; i++) {  
                printDelayHandler.printLineWithDelay(riddle2Swordsman[i], 25); 
            }
        } else if(hero.getGunnerCharacterChosen()) {
            for (int i = 0; i < riddle2Gunner.length; i++) {  
                printDelayHandler.printLineWithDelay(riddle2Gunner[i], 25); 
            }
        } else if(hero.getMageCharacterChosen()) {
            for (int i = 0; i < riddle2Mage.length; i++) {  
                printDelayHandler.printLineWithDelay(riddle2Mage[i], 25); 
            }
        }

        

        while (continueInput) {
            try {
                System.out.print("-->| ");
                String answer2 = scanner.nextLine();

                if ((answer2.equalsIgnoreCase("Bullet") && hero.getGunnerCharacterChosen()) || (answer2.equalsIgnoreCase("Spell") && hero.getMageCharacterChosen()) || (answer2.equalsIgnoreCase("Secret") && hero.getSwordmanCharacterChosen())) {
                    System.out.println();
                    System.out.println("┌────────────────────────┐");
                    System.out.println("│   Correct! Good job!   │");
                    System.out.println("└────────────────────────┘");
                    separatorHandler.promptSeparatorResized();

                    isCorrect = true;
                    continueInput = false;
                } else if (answer2.isEmpty()) {
                    System.out.println("┌──────────────────────────────────────────────────┐");
                    System.out.println("│   Input unclear! Answer must be a character\\s    │");
                    System.out.println("└──────────────────────────────────────────────────┘");
                    separatorHandler.promptSeparatorResized();
                } else {
                    System.out.println();
                    System.out.println("┌──────────────────────────────┐");
                    System.out.println("│   Bzzzt! That's not right!   │");
                    System.out.println("└──────────────────────────────┘");
                    separatorHandler.promptSeparatorResized();

                    isCorrect = false;
                    continueInput = false;
                }
            } 
            catch (Exception e) {
                System.out.println();
                System.out.println("┌──────────────────────────────────────┐");
                System.out.println("│   Error occured in input statement   │");
                System.out.println("└──────────────────────────────────────┘");
                scanner.nextLine(); 
                separatorHandler.promptSeparatorResized();
            }
        }

        return isCorrect;
    }

    public boolean riddle3(Hero hero) {
        boolean continueInput = true;
        boolean isCorrect = false;

        questHandler.riddles();

        String[] riddle3Swordsman = {
            "┌──────────────────────────────────────────┐",
            "│  I test the brave, I test the wise,      │",
            "│  I challenge strength and sharpen ties.  │",
            "│  Step through me and you may grow,       │",
            "│  What am I that heroes know?             │",
            "└──────────────────────────────────────────┘"
        };

        String[] riddle3Gunner = {
            "┌───────────────────────────────────────────┐",
            "│  They gave me power but took my lease,    │",
            "│  my days burn quick, my nights decrease.  │",
            "│  A borrowed spark that costs the prime -  │",
            "│  What is this price measured as in time?  │",
            "└───────────────────────────────────────────┘"
        };

        String[] riddle3Mage  = {
            "┌──────────────────────────────┐",
            "│  Silent yet heavy,           │",
            "│  I follow her path.          │",
            "│  Not a shadow, not a curse,  │",
            "│  but born of her past.       │",
            "└──────────────────────────────┘"
        };

        if(hero.getSwordmanCharacterChosen()) {
            for (int i = 0; i < riddle3Swordsman.length; i++) {  
                printDelayHandler.printLineWithDelay(riddle3Swordsman[i], 25); 
            }
        } else if(hero.getGunnerCharacterChosen()) {
            for (int i = 0; i < riddle3Gunner.length; i++) {  
                printDelayHandler.printLineWithDelay(riddle3Gunner[i], 25); 
            }
        } else if(hero.getMageCharacterChosen()) {
            for (int i = 0; i < riddle3Mage.length; i++) {  
                printDelayHandler.printLineWithDelay(riddle3Mage[i], 25); 
            }
        }

        while (continueInput) {
            try {
                System.out.print("-->| ");

                String answer3 = scanner.nextLine();

                if ((answer3.equalsIgnoreCase("Time") && hero.getGunnerCharacterChosen()) || (answer3.equalsIgnoreCase("Guilt") && hero.getMageCharacterChosen()) || ((answer3.equalsIgnoreCase("Trials") || answer3.equals("Trial") || (answer3.equalsIgnoreCase("Challenges")) || answer3.equalsIgnoreCase("Challenge")) && hero.getSwordmanCharacterChosen())) {
                    separatorHandler.promptSeparatorResized();
                    System.out.println("┌─────────────────────────┐");
                    System.out.println("│   Correct! Well done!   │");
                    System.out.println("└─────────────────────────┘");
                    separatorHandler.promptSeparatorResized();

                    isCorrect = true;
                    continueInput = false;
                } else if (answer3.isEmpty()) {
                    System.out.println("┌──────────────────────────────────────────────────┐");
                    System.out.println("│   Input unclear! Answer must be a character\\s    │");
                    System.out.println("└──────────────────────────────────────────────────┘");
                    separatorHandler.promptSeparatorResized();
                } else {
                    System.out.println();
                    System.out.println("┌───────────────────────────────────────┐");
                    System.out.println("│   Incorrect! Better luck next time!   │");
                    System.out.println("└───────────────────────────────────────┘");
                    separatorHandler.promptSeparatorResized();

                    isCorrect = false;
                    continueInput = false;
                }
            } 
            catch (Exception e) {
                System.out.println();
                System.out.println("┌──────────────────────────────────────┐");
                System.out.println("│   Error occured in input statement   │");
                System.out.println("└──────────────────────────────────────┘");
                scanner.nextLine();
                separatorHandler.promptSeparatorResized();
            }
        }

        return isCorrect;
    }


    public void playSuccessOrFailNarration(String[] arr){
        for (String line : arr) {
            scanner.nextLine();
            System.out.println(line);
            }
        System.out.println();
    }

    public void playDialogueNarration(String[] arr){

        System.out.println();
        System.out.println("┌─────────────────────────────┐");
        System.out.println("│   Press Enter to continue   │");
        System.out.println("└─────────────────────────────┘");

        for (String line : arr) {
            scanner.nextLine();
            System.out.println(line);
        }
        
        System.out.println();
        return; 
        
    }
    

}
