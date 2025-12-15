package Narration;

import java.util.Scanner;

public class TrainingNarration {

    private Narration printDelayNarration = new Narration();
    private Narration separatorHandler = new Narration();
    private Scanner scanner = new Scanner(System.in);

    public void exploreNarration(){

        String[] exploreGym = {
            "As you enter the Training Ground\'s left wing, the Training Ground bursts with activity -\nstudents duel with wands, spar hand-to-hand, and clash swords at the center.",
            "Thunderous shots echo from a warded enclosure where firearms meet magic.",
            "A tall coach steps forward, eyes sharp. \"You\'re not part of the current training batch, are you?\"",
            "They study your stance. \"Untaught, but solid. Are you here to watch or to learn?\"",
            "Folding their arms, they add, \"If you want to grow stronger, I can offer a proper session - only if you\'re ready to earn it.",
            "\"I am Ray Grayson, top coach of the Academy.\""
        };

        playGymNarration(exploreGym);

    }

    public void acceptedTraining(){
        String[] accept = {
            "You meet coach Ray's gaze and nod.",
            "\"Good. Let\'s see what you can do,\" they say, gesturing to an open section of the arena.",
            "Noise fades as your focus narrows. \"Training here isn\'t about power - it\'s about control,\" coach Ray warns.",
            "\"Show me your stance. Let\'s start with the basics.\"",
            "You steady yourself. Coach Ray smiles faintly. \"That\'s more like it. Welcome to the Training Ground.\""
        };

        playGymNarration(accept);

    }

    public void playGymNarration(String[] arr){

        /*System.out.println();
        System.out.println("┌─────────────────────────────┐");
        System.out.println("│   Press Enter to continue   │");
        System.out.println("└─────────────────────────────┘");
        scanner.nextLine();
        */
       
        separatorHandler.promptSeparatorResized();

        for (int i = 0; i < arr.length; i++) {
            printDelayNarration.printWithDelay(arr[i], 15); 
            System.out.println("\n");
        }
        
        System.out.println();
        return; 
        
    }

}

