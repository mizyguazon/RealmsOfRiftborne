package Narration;

//import DesignRelated.*;

import java.util.Scanner;

public class SwordsmanPlot {

    private Scanner scanner = new Scanner(System.in);

    public void swordsmanAfterArea1() { // implemented

        String[] afterArea1Narration1 = {
            "Narration: You are summoned to the Principal's Office.\nThe corridors feel heavier than usual, each step echoing against the marble floor.",
            "Narration: Whispers follow your name - Kael Solmere. The door is engraved with runes.\nWhen it opens, the scent of old parchment and burning incense fills the air.",
            "Principal: \"Kael… I know this summons may seem sudden. Sit down. There's something your family kept hidden for too long.\"",
            "Narration: The principal Nemeesha Brightwell avoids your gaze, her tone heavy with reluctance.",
            "Kael: \"What are you talking about? Is this about the curse or my family's past?\"",
            "Principal: \"Yes... and more. Your parents weren't victims. They were hunted - slain - by Kim Morvain.\"",
            "Narration: The name pierces through you. The room seems to tremble; for a heartbeat, you hear a whisper - soft and familiar.",
            "Kael: \"No... that can't be. They tried to stop him, didn't they?\"",
            "Principal: \"They defied him, Kael. Your parents once served Kim Morvain,\nbut when they learned his true power, they turned against him.\nTheir betrayal saved lives - but doomed your bloodline.\"",
            "Narration: Silence wraps around you. The curse that plagues your family - the whispers, the visions—now pulse stronger in your chest.",
            "Kael: \"Then the curse... it started because of them?\"",
            "Principal: \"Because of their choice. Kim Morvain's vengeance is eternal. Every Solmere heir bears it.\"",
            "Narration: The principal rests a hand on your shoulder, voice soft.",
            "Principal: \"You must learn your family's truth... but remember - truth cuts deeper than any blade.\"",
            "Narration: As you leave, the air feels colder. A faint echo follows you - your name, spoken by a voice you no longer recognize.",
            "Narration: With this revelation, a new path unfolds. The seals guarding The Reverie's Edge now recognize your strength and lineage.\nYou are eligible to enter Area 2. Beyond its gates, greater challenges and truths await."
        };

        swordsmanPlotNarration(afterArea1Narration1);

    }


    public void swordsmanAfterArea2() { // implemented

        String[] afterArea2Narration1 = {
            "Narration: The sky above the academy is gray when the summons comes again.\nYou stand before the Principal's Office, the air heavy with dread.\nInside, the principal Nemeesha Brightwell watches the fog roll past the window, his voice quiet when he finally speaks.",
            "Principal: \"Kael... you've come so far. It's time you knew the truth.\nYour family - the Solmeres - weren't protectors of light. They once served Kim Morvain.\"",
            "Kael: \"That's impossible. My parents died fighting him!\"",
            "Principal: \"They did. But the curse didn't come from Morvain alone. It came from your own blood.\nYour ancestors turned on them for breaking the oath that bound your lineage to darkness.\"",
            "Narration: The words hit like thunder. Shadows twist along the walls, faces flickering within them - faces you almost recognize.",
            "Unknown Whispers: \"Traitors... betrayers of the Oath...\"",
            "Kael: \"Then the ones haunting me - \"",
            "Principal: \"- are your kin. Bound by rage, unable to forgive. The curse is their punishment, passed down through you.\"",
            "Narration: You leave the office in silence. The wind outside howls like laughter.\nIn your shadow, faint figures move beside you - reminders that the Solmere blood remembers... and never forgets betrayal.",
            "Narration: The revelation weighs on you, yet a path now opens. The gates to the Forsaken Lands recognize your lineage and the trials you've endured.\nYou are now eligible to enter, to face the darkness that awaits beyond and uncover the secrets your blood has carried for generations."
        };

        swordsmanPlotNarration(afterArea2Narration1);

    }

    // ending plot for Swordsman
    // dapat ma discover niya na the very first Solmere was Kim Morvain
    // kun i utilize gihapon an idea ni ray na papatayon an sadiri, then pwede na inin iya after area 3 tapos dapat mangyari ini sa area 3

    /*public void swordsmanAfterArea3() { // already implemented

        String[] afterArea3Narration1 = {
            
            "Narration: The Louraine hovers beside you, its faint glow illuminating the dim library.",
            "Narration: On the ancient pedestal rests a manuscript, bound in cracked leather and etched with symbols that tighten your chest.",
            "Narration: The spirit gestures silently, and the pages flip open to a passage that burns into your eyes.",
            "Kael: \"This… this can\'t be... it says... 'Kim Morvain was one of them… the first Solmere...'\"",
            "Narration: Your hands tremble as the words sink in.\nThe manuscript tells of a mage obsessed with immortality, who tore his soul apart and bound it to his descendants.",
            "Kael: \"So... the curse, the visions, the whispers... it\'s not just his doing... it\'s me. I... I\'m him.\"",
            "Narration: Shadows in the library lean closer, the air heavy with the weight of history and blood.\nThe power surging inside you isn\'t yours - it\'s Morvain awakening through your veins.",
            "Kael: \"No... this can\'t be real. I can\'t... I won\'t let him live again... not through me!\"",
            "Narration: The Louraine hovers silently, offering no comfort, only the manuscript and the truth it carries.",
            "Kael: \"You cannot kill what you are made of... but maybe... maybe I can end it all here.\"",
            "Narration: Your reflection in the polished floor wavers, a ghostly echo of both Kael and Kim Morvain.\nThe power inside threatens to consume reason.",
            "Kael: \"If I live... he lives. If I die... maybe... maybe it ends.\"",
            "Narration: For a heartbeat, the library is silent. The wandering spirit fades,\nleaving you alone with the manuscript, the revelation, and the terrible clarity of what must be decided."
        };

        swordsmanPlotNarration(afterArea3Narration1);
    }
        */

    /*public void swordsmanAfterDeath() { // polish the narration markers

        String[] afterDeath1 = { // have implemented

            "Narration: Darkness. Cold and endless.\nYou float weightless, as if the world itself has stopped breathing.\nThen a faint voice breaks through - your own.",
            "You: \"...Kael?\"",
            "Narration: Light flickers where Kael once stood.\nThe floor beneath you is glass - and beneath it, his body lies still.",
            "You: \"What... why am I in my room? Why are you here?\"",
            "Louraine: \"You saw what he chose. His end... was also yours.\" Louraine, the wandering spirit, suddenly appeared.",
            "You: \"No... I\'m not him. I was only playing his story. I\'m me!\"",
            "Narration: The reflection stirs - Kael\s eyes open, and they are yours.",
            "Kael: \"You guided me here. Every choice, every breath. We were never separate.\"",
            "You: \"No... I didn\'t make you-\"",
            "Kael: \"You can\'t kill what you are made of.\"",
            "Narration: Whispers rise all around you - cold, layered, unending.",
            "Kael: \"Blood remembers... one ends, another wakes...\"",
            "You: \"If he\'s gone... why am I still here?\"",
            "Kael: \"Because something had to survive. And you are what remains.\" - Wandering Spirit",
            "Narration: A dim light blooms in your chest - the same that once surrounded Kael.",
            "Kael: You realize his death didn\'t end the curse... it passed to you.",
            "You:\"...He gave it to me.\"",
            "Louraine: \"He gave it back.\"",
            "Narration: The glass cracks. The reflection fades.\nYou stand between two worlds - the player and the played.\nAnd in the dark, Kael\'s fading voice whispers:",
            "Kael: \"Finish what I couldn\'t...\""

        };

        swordsmanPlotNarration(afterDeath1);
    }

    public void swordsmanAcceptingFate() { // have implemented

        String[] acceptingfate = {

            "Narration:You stand alone in the dim library, the weight of your bloodline pressing down on you.\nThe whispers swirl around your mind, harsh and unyielding, yet a strange calm settles over you.",
            "Kael: \"So... this is who I am. Not just Kael Solmere... but the next Kim Morvain. The blood, the power... it is mine to bear.\"",
            "Narration: You close your eyes, feeling the pulse of ancient magic surge through your veins.\nThe temptation, the power, the curse - all converge within you.",
            "Kael: \"I cannot escape. I am him. And perhaps... I must be.\"",
            "Narration: The shadows twist around the library, then vanish. Silence stretches through the empty hall.Suddenly, light.\nCold morning air brushes your face. You open your eyes.\nThe academy is distant, its gates standing before you, unchanged.",
            "Kael: \"...Again? It starts over...\"",
            "Narration: The cycle resets. The whispers of your bloodline linger faintly on the breeze, reminding you:\nthe story, the curse, the power - it never ends.And your fate waits… again."

        };

        swordsmanPlotNarration(acceptingfate);
    }

    public void swordsmanEndingPlot() {
        IntroTitle outroHandler = new IntroTitle();

        swordsmanAfterArea3();

        boolean validChoice = false;

        while (!validChoice) {
            System.out.println("┌───────────────────────────────────────────────────────────────────┐");
            System.out.println("│   Will you bear the weight of your bloodline and end the curse,   │");
            System.out.println("│   or preserve yourself and let the shadows persist? (y/n)         │");
            System.out.println("└───────────────────────────────────────────────────────────────────┘");

            try {
                String willingChoice = scanner.nextLine().trim();

                if (willingChoice.equalsIgnoreCase("y")) {
                    System.out.println("┌───────────────────────────────────────────────────────┐");
                    System.out.println("│   You have accepted the fate of being the next heir.  │");
                    System.out.println("└───────────────────────────────────────────────────────┘");
                    System.out.println();

                    swordsmanAcceptingFate();
                    validChoice = true;
                } else if (willingChoice.equalsIgnoreCase("n")) {
                    System.out.println("┌───────────────────────────────────────────────────────────────┐");
                    System.out.println("│   You have chosen to preserve yourself, letting the shadows   │");
                    System.out.println("│   continue. Your bloodline\'s fate remains uncertain.         │");
                    System.out.println("└───────────────────────────────────────────────────────────────┘");
                    System.out.println();

                    swordsmanAfterDeath();
                    validChoice = true;
                } else {
                    System.out.println("┌────────────────────────────────────────┐");
                    System.out.println("│   Choice unclear! Enter 'y' or 'n'.    │");
                    System.out.println("└────────────────────────────────────────┘");
                }

            } catch (Exception e) {
                System.out.println("┌──────────────────────────────────────────────┐");
                System.out.println("│   An unexpected error occurred. Try again.   │");
                System.out.println("└──────────────────────────────────────────────┘");
                scanner.nextLine();
            }
        }

        outroHandler.outroGameDoneTemporary();

        System.out.println("\nThank you for playing Mystvale Academy.");
        System.exit(0); 
    }

    */

    public void swordsmanPlotNarration(String[] array) {

        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ Narration Ahead! Not Skippable │ Press ENTER to continue... │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");


        for (int i = 0; i < array.length; i++) {
            scanner.nextLine();
            System.out.println(array[i]);
        }

        System.out.println();
    }
        
}
