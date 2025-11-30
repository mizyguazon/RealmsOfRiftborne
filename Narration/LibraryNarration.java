package Narration;

import java.util.Scanner;
import DesignRelated.*;

public class LibraryNarration {

    private static Scanner scanner = new Scanner(System.in);

    Narration printDelayHandler = new Narration();
    Narration separatorHandler = new Narration();
    Quests questHandler = new Quests();

    public void findTheLostBookNarration(){
        String[] quest1 = {
            "The library hums with ancient energy.",
            "Stacks of tomes rise like towers.",
            "The librarian names Lourdes Inkwood looks up and says,",
            "\"Student of Mystvale, an important book - The Art of Silent Spells - has gone missing.\"",
            "\"Can you help find it on one of the five nearby shelves?\""
        };

        for (int i = 0; i < quest1.length; i++) {
            //scanner.nextLine(); 
            printDelayHandler.printWithDelay(quest1[i], 15); 
            System.out.println("\n");
        }

        //playDialogueNarration(quest1);

    }

    public void ifQuest1Success(){
        String[] success1 = {
            "You pull a dusty tome from the shelf - its cover glimmers faintly.",  
            "\"The Art of Silent Spells,\" you whisper.",
            "\"Marvelous! You have a keen eye, young scholar. Mystvale could use more minds like yours.\""
        };

        for (int i = 0; i < success1.length; i++) {
            //scanner.nextLine(); 
            printDelayHandler.printWithDelay(success1[i], 15); 
            System.out.println("\n");
        }

        //playSuccessOrFailNarration(success1);

    }

    public void ifQuest1Fail(){
        String[] fail1 = {
            "You search carefully, but the shelf holds nothing but cobwebs and old parchment.",
            "\"Hmm… no luck? Don\'t worry. Even the books in this place like to hide.\"",  
            "\"Perhaps next time, they\'ll reveal themselves to you.\""
        };

        for (int i = 0; i < fail1.length; i++) {
            //scanner.nextLine(); 
            printDelayHandler.printWithDelay(fail1[i], 15); 
            System.out.println("\n");
        }

        //playSuccessOrFailNarration(fail1);

    }

    public void riddlesNarration(){
        String[] quest3 = {
            "As you approach the Restricted Section, you see the librarian Lourdes by a flickering candle.",
            "An ancient tome lies open, letters moving across its pages.",
            "She looks up and says, \"Ah, curious student! This tome opens only to those who answer its riddles.\"",
            "\"Will you try your wit?\""
        };

        for (int i = 0; i < quest3.length; i++) {
            //scanner.nextLine(); 
            printDelayHandler.printWithDelay(quest3[i], 15); 
            System.out.println("\n");
        }

        //playDialogueNarration(quest3);

    }

    public boolean riddle1() {
        boolean continueInput = true;
        boolean isCorrect = false;

        questHandler.riddles();

        String[] riddle = {
            "┌───────────────────────────────────────────────────┐",
            "│   Nang maliit ay mestiso, nang lumaki'y negro    │",
            "└───────────────────────────────────────────────────┘",
            "┌─────────────────────────────────────────────────────────┐",
            "│   When young was fair-skinned, when grown became dark.  │",
            "└─────────────────────────────────────────────────────────┘"
        };

        for (int i = 0; i < riddle.length; i++) {  
            printDelayHandler.printLineWithDelay(riddle[i], 25); 
        }

        /*System.out.println("┌───────────────────────────────────────────────────┐");
        System.out.println("│   Nang maliit ay mestiso, nang lumaki'y negro    │");
        System.out.println("└───────────────────────────────────────────────────┘");
        System.out.println("┌─────────────────────────────────────────────────────────┐");
        System.out.println("│   When young was fair-skinned, when grown became dark.  │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
        */

        while (continueInput) {
            try {
                System.out.print("-->| ");
                String answer1 = scanner.nextLine();

                if (answer1.equalsIgnoreCase("Abo ng sigarilyo") || answer1.equalsIgnoreCase("Cigarette ash")) {
                    System.out.println();
                    System.out.println("┌───────────────────────┐");
                    System.out.println("│   Nice! You got it!   │");
                    System.out.println("└───────────────────────┘");
                    separatorHandler.promptSeparatorResized();

                    isCorrect = true;
                    continueInput = false;
                } else if (answer1.isEmpty()) {
                    System.out.println("┌──────────────────────────────────────────────────┐");
                    System.out.println("│   Input unclear! Answer must be a character\\s   │");
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


    public boolean riddle2() {
        boolean continueInput = true;
        boolean isCorrect = false;

        questHandler.riddles();

        String[] riddle = {
            "┌────────────────────────────────────────────────────────────┐",
            "│   Walang ulo, walang mata, may bibig na laging umaariba,   │",
            "│               At isang tenga na bubuka-buka                │",
            "└────────────────────────────────────────────────────────────┘",
            "  ┌───────────────────────────────────────────────────────┐",
            "  │   No head, no eyes, a mouth that's always roaring,   │",
            "  │           And an ear that opens and closes.           │",
            "  └───────────────────────────────────────────────────────┘"
        };

        for (int i = 0; i < riddle.length; i++) {  
            printDelayHandler.printLineWithDelay(riddle[i], 25); 
        }

        /*System.out.println("┌────────────────────────────────────────────────────────────┐");
        System.out.println("│   Walang ulo, walang mata, may bibig na laging umaariba,   │");
        System.out.println("│               At isang tenga na bubuka-buka                │");
        System.out.println("└────────────────────────────────────────────────────────────┘");
        System.out.println("  ┌───────────────────────────────────────────────────────┐");
        System.out.println("  │   No head, no eyes, a mouth that's always roaring,   │");
        System.out.println("  │           And an ear that opens and closes.           │");
        System.out.println("  └───────────────────────────────────────────────────────┘");
        */

        while (continueInput) {
            try {
                System.out.print("-->| ");
                String answer2 = scanner.nextLine();

                if (answer2.equalsIgnoreCase("Kawali") || answer2.equalsIgnoreCase("Frying pan")) {
                    System.out.println();
                    System.out.println("┌────────────────────────┐");
                    System.out.println("│   Correct! Good job!   │");
                    System.out.println("└────────────────────────┘");
                    separatorHandler.promptSeparatorResized();

                    isCorrect = true;
                    continueInput = false;
                } else if (answer2.isEmpty()) {
                    System.out.println("┌──────────────────────────────────────────────────┐");
                    System.out.println("│   Input unclear! Answer must be a character\\s   │");
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

    public boolean riddle3() {
        boolean continueInput = true;
        boolean isCorrect = false;

        questHandler.riddles();

        String[] riddle = {
            "┌──────────────────────────────────────────────────────────────┐",
            "│   Duguang buhok ni Letecia, sinipsip ng kanyang mga bisita   │",
            "└──────────────────────────────────────────────────────────────┘",
            "  ┌────────────────────────────────────────────────────────┐",
            "  │   Letecia's bloody hair was sucked by her visitors.   │",
            "  └────────────────────────────────────────────────────────┘"
        };

        for (int i = 0; i < riddle.length; i++) {  
            printDelayHandler.printLineWithDelay(riddle[i], 25); 
        }


        /*System.out.println("┌──────────────────────────────────────────────────────────────┐");
        System.out.println("│   Duguang buhok ni Letecia, sinipsip ng kanyang mga bisita   │");
        System.out.println("└──────────────────────────────────────────────────────────────┘");
        System.out.println("  ┌────────────────────────────────────────────────────────┐");
        System.out.println("  │   Letecia's bloody hair was sucked by her visitors.   │");
        System.out.println("  └────────────────────────────────────────────────────────┘");
        */

        while (continueInput) {
            try {
                System.out.print("-->| ");

                String answer3 = scanner.nextLine();

                if (answer3.equalsIgnoreCase("Spaghetti")) {
                    separatorHandler.promptSeparatorResized();
                    System.out.println("┌─────────────────────────┐");
                    System.out.println("│   Correct! Well done!   │");
                    System.out.println("└─────────────────────────┘");
                    separatorHandler.promptSeparatorResized();

                    isCorrect = true;
                    continueInput = false;
                } else if (answer3.isEmpty()) {
                    System.out.println("┌──────────────────────────────────────────────────┐");
                    System.out.println("│   Input unclear! Answer must be a character\\s   │");
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
