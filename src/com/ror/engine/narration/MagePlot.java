package com.ror.engine.narration;

import java.util.Scanner;
//import DesignRelated.IntroTitle;

public class MagePlot {

    Scanner scanner = new Scanner(System.in);
    private Narration printDelayHandler = new Narration();
    private Narration promptSeparatorHandler = new Narration();
    
    public void mageAfterArea1() { 

        String[] afterArea1Narration3 = {
            "Narration: You step into the principal\'s office, your robes singed and your hands still humming\nwith residual energy from the Forest of Reverie. Every muscle aches, but your mind is alive, alert to the faintest traces of magic.",
            "Narration: Principal Nemeesha stands silently, watching you with the weight of something unspoken.\nYou expected a routine debrief... but the tension in the room tells you otherwise.",
            "Principal Nemeesha: \"You\'ve returned,\" he says quietly, voice calm but heavy. \"And yet, something has changed in you.\"",
            "Selene: Your fingers itch for a spell, instinct and caution warring inside you.",
            "Selene: \"...I survived the forest. That\'s what matters, isn\'t it?\"",
            "Principal Nemeesha: \"Survival is only part of it. Your curse… it is more than pain or torment.\"",
            "Selene: \"...What do you mean?\"",
            "Principal Nemeesha: \"Morvain tied your magic to his own influence. Every spell you cast, every surge of your power...\nhe can sense it, manipulate it, even punish you from afar. He\'s always been watching.\"",
            "Narration: A cold weight settles in your chest.",
            "Selene: \"...So every incantation I used to survive, every burst of power... it could have been a beacon to him?\"",
            "Principal Nemeesha: \"Yes. The curse was never just a torment - it\'s a leash. And you have walked it blindly until now.\"",
            "Narration: Your jaw tightens. Anger, guilt, and determination mingle in a sudden, hot surge.",
            "Selene: \"...Then it ends. I will turn my own magic into the weapon he can\'t control.\nEvery spell, every ounce of power - it will be mine to wield. And he will pay.\"",
            "Narration: The principal studies you quietly, as if seeing the full weight of your resolve for the first time. Outside,\nthe wind whispers through the academy, carrying the echoes of a mage determined to confront the architect of her suffering."
        };

        magePlotNarration(afterArea1Narration3);

    }

    public void mageAfterArea2() { 

        String[] afterArea2Narration3 = {
            "Narration: You step into the principal\'s office, every nerve still thrumming from the battle in the Reverie\'s Edge.\nThe lingering scent of burnt Aether hangs in the air, and your hands are scorched from channeling your magic.",
            "Narration: Principal Nemeesha stands behind his desk, his gaze steady and unsettlingly calm. You expected praise -\nor at least a routine debrief - but the weight in his eyes immediately tells you this is something far heavier.",
            "Principal Nemeesha: \"Selene,\" he begins, voice low, \"there\'s something you need to know about Project LUCENT.. and those you care about.\"",
            "Narration: Your stomach tightens, the memories of the experiments flashing unbidden in your mind.",
            "Selene: \"...What is it? Speak plainly.\"",
            "Principal Nemeesha: \"During LUCENT, Morvain eliminated or captured many of your family and peers -\nthose who could have stood against him, those who opposed his will.\"",
            "Narration: A cold ache spreads through you, mingling with the familiar sting of guilt.",
            "Selene: \"...All of them? My family... my friends...?\"",
            "Principal Nemeesha: \"Yes. And some… were used in experiments similar to the ones you endured.\nTheir lives, their pain… it was all part of his plan to ensure no one could defy him.\"",
            "Narration: Your hands tremble, not from weakness, but fury. The weight of your survival hits harder now: you lived while they suffered.",
            "Selene: \"...He... he turned everything I created into a weapon against them. And I... I didn\'t even know.\"",
            "Principal Nemeesha: \"You did what you could to survive. But now... knowing the truth, you have a choice.\nThe Forsaken Lands are where Morvain consolidates his power. If you go there,\nit will not just be for vengeance - it will be for those who cannot fight themselves.\"",
            "Narration: A rush of heat surges through your veins, anger and grief intertwining.",
            "Selene: \"...Then that is where I will end him. For them... and for every life he has stolen. Morvain will pay.\"",
            "Narration: The principal nods solemnly. Outside the office, the wind whispers through the academy,\ncarrying the echoes of a mage whose guilt has transformed into burning resolve,\nand whose vengeance will not rest until the Forsaken Lands witness justice."
        };


        magePlotNarration(afterArea2Narration3);
    }

    public void mageAfterArea3() {

        String[] afterArea3Narration3 = {
            "Narration: The chamber falls silent. Kim Morvain\'s body collapses into collapsing threads of Aether, fading like a dying echo.",
            "Narration: As the final spark leaves him, a violent surge erupts in your mind - images, memories, voices you buried long ago.",
            "Narration: Needles. Restraints. Screams echoing through sterile halls.\nYour own hands trembling as Morvain forced you to cast spells you didn\'t understand.",
            "Narration: And then-your family. Your peers. Their faces distorted by fear and pain.",
            "Selene: \"N-No... stop-!\"",
            "Narration: The memories crash through you all at once. Not fragments.\nNot illusions. Truths. Truths you had forgotten-or were forced to forget.",
            "Narration: Shame curls in your throat like smoke. You see yourself standing beside Morvain, broken, obedient, complicit.",
            "Selene: \"I was part of this... I was part of *him.*\"",
            "Narration: Your knees weaken. Every breath tastes of guilt.",
            "Narration: But underneath the guilt, something else stirs- a cold, metallic bite along your spine.",
            "Selene: \"...The curse. It\'s still there.\""
        };

        magePlotNarration(afterArea3Narration3);

    }

    public void mageSacrifice() {

        String[] sacrifice = {
            "Narration: Your pulse pounds. The ache along your ribs intensifies-burning, tightening, reshaping itself into a familiar pattern.",
            "Narration: The Solmere rune. Morvain\'s brand. It glows faintly beneath your skin, pulsing like a second heartbeat.",
            "Selene: \"No... no, this shouldn\'t be possible. He\'s dead. He\'s gone.\"",
            "Narration: But the curse hums louder, as if mocking you.",
            "Selene: \"Why is it still here? What did he do to me?\"",
            "Narration: A memory surfaces—Morvain whispering as he carved the rune into you: \"Your soul will answer mine. Even beyond death.\"",
            "Narration: And then you understand. Horribly. Completely.",
            "Selene: \"It\'s not tied to his life… it\'s tied to his *bloodline.*\"",
            "Narration: Fear and clarity collide in your chest.",
            "Selene: \"If the curse is bound to the Solmere tether... then the only way to sever it is...\"",
            "Narration: Your breath catches. The realization feels like ice.",
            "Selene: \"...to destroy the soul it\'s attached to. My soul. My life.\"",
            "Narration: The room feels colder. The world feels smaller. A choice looms before you-unforgiving, absolute."
        };

        magePlotNarration(sacrifice);

    }

    public void mageLoop() {

        String[] loop = {
            "Narration: You take a shaky breath. The weight of the truth hangs heavy... but still, you step back from it.",
            "Selene: \"No. Not yet. I can\'t just throw my life away. I\'ll endure the curse. I\'ve endured worse.\"",
            "Narration: You turn away from the forsaken battlefield, choosing guilt over oblivion, survival over sacrifice.",
            "Narration: The air distorts. A ripple of Aether curls around your ankles.",
            "Selene: \"...What-?\"",
            "Narration: In a blink, the world fractures into pale light.",
            "Narration: When your vision clears, you are standing before the academy gates-exactly where your journey began.",
            "Narration: The wind is still. The sky unchanged. The loop complete.",
            "Selene: \"No... no, this can\'t- I left this place. I \"escaped.\"",
            "Narration: But your feet move on their own, carrying you through the halls.",
            "Narration: Again. Again. Again.",
            "Narration: Until you stand before the principal\'s door, your hand trembling over the handle.",
            "Principal Nemeesha: \"Enter.\"",
            "Narration: Her voice is exactly as before. Calm. Heavy. Expecting you.",
            "Selene: \"Principal Nemeesha... something\'s wrong. I was outside- I killed Morvain- and now I\'m back here?\"",
            "Principal Nemeesha: \"Of course you are. Morvain\'s death changed nothing.\"",
            "Selene: \"...What are you saying? I saw his body fade.\"",
            "Principal Nemeesha: \"Kim Morvain was only one shadow of a darker truth.\"",
            "Narration: She steps closer, expression unreadable.",
            "Principal Nemeesha: \"There is another. Another Solmere. One whose existence ensures the curse continues.\"",
            "Narration: The air thickens. Your heartbeat stutters.",
            "Selene: \"...Another? Who? Who could possibly-\"",
            "Principal Nemeesha: \"Kael Solmere. The last of the bloodline. And now... the new source of your curse.\"",
            "Narration: Your world tilts. The loop, the curse, the return-it all makes sense now.",
            "Narration: You are not free. Not yet. Not until Kael Solmere falls."
        };

        magePlotNarration(loop);

    }

    public void mageEndingPlot() {

        promptSeparatorHandler.promptSeparatorResized();
        mageAfterArea3();

        String choice;

        while(true) {

            System.out.println("┌─────────────────────────────────────────────────┐");
            System.out.println("│      A life-binding choice awaits you...        │");
            System.out.println("│  Will you offer your soul to sever the  curse?  │");
            System.out.println("│                      (y/n)                      │");
            System.out.println("└─────────────────────────────────────────────────┘");
            System.out.print("-->| ");

            try {
                choice = scanner.nextLine();

                if(choice.equalsIgnoreCase("y")) {
                    promptSeparatorHandler.promptSeparatorResized();
                    mageSacrifice();
                } else if (choice.equalsIgnoreCase("n")) {
                    promptSeparatorHandler.promptSeparatorResized();
                    mageLoop();
                } else {
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
    

    public void magePlotNarration(String[] array) {
        
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
