package com.ror.engine.narration;

import java.util.Scanner;

public class SwordsmanPlot {

    private Scanner scanner = new Scanner(System.in);
    private Narration printDelayHandler = new Narration();
    private Narration promptSeparatorHandler = new Narration();

    public void swordsmanAfterArea1() { 

        String[] afterArea1Narration1 = {
            "Narration: You are summoned to the Principal's Office.\nThe corridors feel heavier than usual, each step echoing against the marble floor.",
            "Narration: Whispers follow your name - Neo Solmere. The door is engraved with runes.\nWhen it opens, the scent of old parchment and burning incense fills the air.",
            "Principal Nemeesha: \"Neo... I know this summons may seem sudden. Sit down. There's something your family kept hidden for too long.\"",
            "Narration: The principal Nemeesha Brightwell avoids your gaze, her tone heavy with reluctance.",
            "Neo: \"What are you talking about? Is this about the curse or my family's past?\"",
            "Principal Nemeesha: \"Yes... and more. Your parents weren't victims. They were hunted - slain - by Kim Morvain.\"",
            "Narration: The name pierces through you. The room seems to tremble; for a heartbeat, you hear a whisper - soft and familiar.",
            "Neo: \"No... that can't be. They tried to stop him, didn't they?\"",
            "Principal Nemeesha: \"They defied him, Neo. Your parents once served Kim Morvain,\nbut when they learned his true power, they turned against him.\nTheir betrayal saved lives - but doomed your bloodline.\"",
            "Narration: Silence wraps around you. The curse that plagues your family - the whispers, the visions - now pulse stronger in your chest.",
            "Neo: \"Then the curse... it started because of them?\"",
            "Principal Nemeesha: \"Because of their choice. Kim Morvain's vengeance is eternal. Every Solmere heir bears it.\"",
            "Narration: The principal rests a hand on your shoulder, voice soft.",
            "Principal Nemeesha: \"You must learn your family's truth... but remember - truth cuts deeper than any blade.\"",
            "Narration: As you leave, the air feels colder. A faint echo follows you - your name, spoken by a voice you no longer recognize.",
            "Narration: With this revelation, a new path unfolds. The seals guarding The Reverie's Edge now recognize your strength and lineage.\nYou are eligible to enter Reveries Edge. Beyond its gates, greater challenges and truths await."
        };

        swordsmanPlotNarration(afterArea1Narration1);

    }


    public void swordsmanAfterArea2() { 

        String[] afterArea2Narration1 = {
            "Narration: The sky above the academy is gray when the summons comes again.\nYou stand before the Principal's Office, the air heavy with dread.\nInside, the principal Nemeesha Brightwell watches the fog roll past the window, his voice quiet when he finally speaks.",
            "Principal Nemeesha: \"Neo... you've come so far. It's time you knew the truth.\nYour family - the Solmeres - weren't protectors of light. They once served Kim Morvain.\"",
            "Neo: \"That's impossible. My parents died fighting him!\"",
            "Principal Nemeesha: \"They did. But the curse didn't come from Morvain alone. It came from your own blood.\nYour ancestors turned on them for breaking the oath that bound your lineage to darkness.\"",
            "Narration: The words hit like thunder. Shadows twist along the walls, faces flickering within them - faces you almost recognize.",
            "Unknown Whispers: \"Traitors... betrayers of the Oath...\"",
            "Neo: \"Then the ones haunting me - \"",
            "Principal Nemeesha: \"- are your kin. Bound by rage, unable to forgive. The curse is their punishment, passed down through you.\"",
            "Narration: You leave the office in silence. The wind outside howls like laughter.\nIn your shadow, faint figures move beside you - reminders that the Solmere blood remembers... and never forgets betrayal.",
            "Narration: The revelation weighs on you, yet a path now opens. The gates to the Forsaken Lands recognize your lineage and the trials you've endured.\nYou are now eligible to enter, to face the darkness that awaits beyond and uncover the secrets your blood has carried for generations."
        };

        swordsmanPlotNarration(afterArea2Narration1);

    }

    public void swordsmanAfterArea3() {
        String[] afterArea3Narration1 = {
            "Narration: The remnants of Kim Morvain\'s shadow dissolve around you, but the air still feels thick, heavy with whispers.\nFaces of your ancestors flicker in the corners of your vision, their eyes full of rage and sorrow.",
            "Neo: \"It\'s not over... not yet. The curse... it\'s still here. It never truly left.\"",
            "Narration: The wind inside the chamber rises, carrying voices from the past.\nThey are your family, bound in eternal torment, their betrayal echoing through generations.",
            "Whispering Voices: \"Traitor... Solmere... blood... forever...\"",
            "Neo: \"I understand now. I\'ve fought, I\'ve bled... but the only way to end this... is me.\"",
            "Narration: The shadows swirl around you, tugging at your very being.\nThe academy trembles, as if sensing the magnitude of the choice."
        };

        swordsmanPlotNarration(afterArea3Narration1);

    }

    public void swordsmanSacrifice() {
        String[] sacrifice = {
            "Neo: \"I am Neo Solmere. I am your heir. And I will take this burden upon myself. The curse ends here.\"",
            "Narration: With deliberate steps, you move to the center of the chamber.\nThe whispers grow louder, pleading, accusing, mourning - yet you feel no fear. Only purpose.",
            "Neo: \"May this sacrifice free every Solmere, every soul chained by my bloodline... including yours, Morvain.\"",
            "Narration: You raise your hand, channeling every ounce of your life force, every spark of your existence,\ninto the spell that will sever the curse. The shadows writhe, resisting, clawing at your mind, but you hold steady.",
            "Narration: A blinding light erupts from your chest. The whispers shatter, the shadows dissipate.\nSilence follows - profound, complete, freeing. And in that silence, you feel it: the curse has ended.\nThe Solmere bloodline, burdened for generations, is finally at peace."
        };

        swordsmanPlotNarration(sacrifice);

    }

    public void swordsmanLoop() {
        String[] loop = {
            "Neo: \"No... I won\'t give in. I can fight this... I\'ll end it without sacrificing myself.\"",
            "Narration: You step away from the center of the chamber.\nThe whispers rise into a scream, twisting into a chorus of rage, sorrow, and warning.\nThe shadows writhe violently, curling around the walls, the ceiling, the floor... yet, somehow, you survive.",
            "Neo: \"It\'s over... right? I did it...\"",
            "Narration: For a heartbeat, the academy seems still, almost normal.\nThe sunlight filters through cracked windows, dust floating lazily in the golden rays. Relief warms your chest.\nThen, a subtle tremor runs through the walls.The floor beneath you shifts imperceptibly.\nThe portraits along the corridors blink, not with eyes, but with mouths, whispering your name.",
            "Narration: You step outside. The streets seem familiar, yet the air hums with a strange, low vibration.\nThe world is quiet... too quiet. Every shadow stretches just a little too long, every reflection lingers too long in puddles, waiting.",
            "Neo: \"This... isn\'t right. Something\'s... wrong...\"",
            "Narration: You walk, but the scenery subtly shifts. Buildings twist,\ntrees curve unnaturally, the sky pulses faintly as if breathing. Somewhere in the distance,\na door opens where there should be none. The wind carries whispers, soft, patient... mocking:",
            "Whispering Voices: \"Neo... Solmere... you cannot leave... your blood binds you... forever...\"",
            "Narration: And then, you see it: your own body, walking past you, perfectly still, eyes empty,\nmoving on some invisible path you cannot touch. Behind it, the academy reforms, its towers curling into impossible angles,\ncorridors stretching beyond comprehension, doors opening into rooms you never entered.",
            "Neo: \"No... no... this... again... I\'m trapped… it\'s... endless..\"",
            "Narration: The cycle begins anew. Every choice, every defiance, every attempt to escape folds back on itself.\nThe curse is patient. It waits. It watches. And it will never release you.",
            "Whispering Voices: \"Forever... Neo... forever... Solmere...\"",
            "Narration: The streets twist once more. You are outside... and inside... and nowhere.\nThe academy breathes, alive, eternal. And you... are still walking, still running, still trapped."
        };

        swordsmanPlotNarration(loop);

    }

    public void swordsManEndingPlot() {

        promptSeparatorHandler.promptSeparatorResized();
        swordsmanAfterArea3();

        String choice;

        while(true) {

            System.out.println("┌────────────────────────────────────────────────┐");
            System.out.println("│  The chamber trembles with ancestral whispers  │");
            System.out.println("│     Will you offer your life to break the      │");
            System.out.println("│                     (y/n)                      │");
            System.out.println("└────────────────────────────────────────────────┘");
            System.out.print("-->|");

            try {
                choice = scanner.nextLine();

                if(choice.equalsIgnoreCase("y")) {
                    promptSeparatorHandler.promptSeparatorResized();
                    swordsmanSacrifice();
                } else if (choice.equalsIgnoreCase("n")){
                    promptSeparatorHandler.promptSeparatorResized();
                    swordsmanLoop();
                } else  {
                    System.out.println();
                    System.out.println("┌────────────────────────────────────────┐");
                    System.out.println("│   Choice unclear! Enter 'y' or 'n'.    │");
                    System.out.println("└────────────────────────────────────────┘");
                    promptSeparatorHandler.promptSeparatorResized();
                }

            } catch(Exception e) {

                System.out.println();
                System.out.println("┌──────────────────────────────────────────────┐");
                System.out.println("│   An unexpected error occurred. Try again.   │");
                System.out.println("└──────────────────────────────────────────────┘");
                promptSeparatorHandler.promptSeparatorResized();
                scanner.nextLine();

            }

            
        }

    }

    public void swordsmanPlotNarration(String[] array) {

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
