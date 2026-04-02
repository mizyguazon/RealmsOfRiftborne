package com.ror.engine.narration;

import java.util.Scanner;

public class OfficeNarration {

    private Narration printDelayHandler = new Narration();
    private Narration separatorHandler = new Narration();
    private static Scanner scanner = new Scanner(System.in);

    public void officeNarration(){
        String[] principalOfficeNarration = {
           "As you enter the tall arched doors, the air shifts - calm yet commanding.",
            "Golden light glows across the marble floor and the crest of Mystvale Academy.",
            "A voice stops you.",
            "\"Good day, student,\" the secretary names Niña Montel says, her quill hovering over a glowing parchment.",
            "\"Before seeing Principal Nemeesha, I must verify your experience.\"",
            "She gestures, and a soft light surrounds you as numbers and symbols float in the air.",
            "\"Let\'s see... your experience will decide if you may proceed.\""
        };
        
        playEligibleNarration(principalOfficeNarration);

    }

    public void area1Eligible(){
        String[] area1Principal = {
            "Principal Nemeesha Brightwell nods approvingly as you approach.",
            "\"You\'ve shown promise, student,\" she says. \"You are now eligible to explore the Forest of Reverie.\"",
            "\"Beware with the entities that lies within the forest, they may not what they seem.\""
        };

        playEligibleNarration(area1Principal);

    }

    public void area2Eligible(){
        String[] area2Principal = {
            "Principal Nemeesha Brightwell folds her hands behind her back, studying you closely.",
            "\"Impressive progress. You\'ve earned the right to enter the Reveries Edge,\" she declares.",
            "\"It\'s a place that tests patience as much as strength, as the entities there are much stronger - do not lose your focus.\""
        };

        playEligibleNarration(area2Principal);

    }

    public void area3Eligible(){
        String[] area3Principal = {
            "Principal Nemeesha's tone grows solemn as she regards you.",
            "\"You\'ve reached a rare level of mastery,\" she says quietly. \"The Forsaken Lands now awaits you.\"",
            "\"Beyond its gates lie trials unlike any other. Step forward with courage - and wisdom.\""
        };

        playEligibleNarration(area3Principal);

    }

    public void playEligibleNarration(String[] arr){

        System.out.println();
        System.out.println("┌─────────────────────────────┐");
        System.out.println("│   Press Enter to continue   │");
        System.out.println("└─────────────────────────────┘");
        scanner.nextLine();

        separatorHandler.promptSeparatorResized();

        for (int i = 0; i < arr.length; i++) {
            printDelayHandler.printWithDelay(arr[i], 15); 
            System.out.println("\n");
        }

        separatorHandler.promptSeparatorResized();

        System.out.println();
        return; 
        
    }
    
}
