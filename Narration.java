import java.util.Scanner;

public class Narration{
    static Scanner scanner = new Scanner(System.in);

    // Narration for Prologue
    public static void prologueNarration() {
        String[] prologue = {
            "Your days as a student have been a blur of half-hearted effort and quiet frustration. \nLessons slip past you, faces blur together, and the weight of expectations presses down heavier with each passing week. \nTonight feels no different—you collapse into bed, hoping that sleep might offer an escape, if only for a few hours.",
            
            "But this sleep is unlike any other.",
            
            "You wake with a start, not in your room, but in a place you\'ve never seen before. \nThe air is sharp and crisp, carrying the faint scent of pine and old stone. A pale moon hangs above an endless forest, \nits silver light spilling over a towering structure in the distance - an academy, its spires reaching for the sky.",
            
            "As you take a cautious step forward, a presence brushes against you. Weightless, yet undeniable. \nA wandering spirit materializes from the air itself, its form shifting like smoke caught in a breeze. \nIts voice is calm, distant, but warm enough to ease the unease clawing at your chest.",
            
            "\"Welcome, lost one,\" it says. \"This is Mystvale Academy… a place where magic is studied, honed, \nand wielded against the forces beyond these walls. You now stand on the threshold of its trials.\"",
            
            "\"The academy rests at the edge of a vast forest, shielding its students from the dangers beyond.\"",
            
            "\"The inner region, just past the academy\'s borders, is alive with mystical woods and wandering lesser entities.\"",
            
            "\"The middle region stretches into treacherous swamplands, where stronger beings test the skill of even seasoned students.\"",
            
            "\"And far beyond lies the outer region—the dominion of entities. Few dare tread there, \nfor it is said the most fearsome of them all awaits in its shadowed heart.\"",
            
            "The spirit\'s gaze lingers on you, sharp and knowing.",
            
            "\"Why you were brought here, only time will reveal. But your path begins now. \nLearn, survive, and perhaps... you may one day stand against what lies beyond the forest.\"",
            
            "The spirit fades, leaving only silence and the academy\'s looming silhouette in the distance.",
            
            "Your story has begun."
        };

        // Array Iteration 
        playSection(prologue);
    }

    // Academy Narration
    public static void academyNarration() {
        String[] academy = {
            "The tall gates of Mystvale Academy open with a low groan...",
            
            "You pause, taking it all in, when a sudden chill brushes your shoulder...",
            
            "You nearly stumble back in shock.",
            
            "\"I didn't expect you again,\" you mutter under your breath.",
            
            "The spirit's form flickers faintly, its voice calm and patient.",
            
            "\"Do not be alarmed. Mystvale Academy is vast...\"",
            
            "It gestures toward the campus...",
            
            "\"The Library, a hall of ancient knowledge...\"",
            
            "\"Canteen, though filled with chatter...\"",
            
            "\"The Gym, where strength is forged...\"",
            
            "\"And finally\" the spirit's form stills...",
            
            "Its glow dims slightly as the explanations end.",
            
            "\"Here, you are free to choose your path...\"",
            
            "The spirit fades as quickly as it appeared..."
        };

        // Array Iteration
        playSection(academy);
    }

    // Library Narration
    public static void libraryNarration() {
        String[] library = {
            "As you step into the library, the air grows still...",
            
            "The wandering spirit flickers into view...",
            
            "\"This is Mystvale's Library...\"",
            
            "The spirit fades, leaving only the quiet rustle of unseen pages."
        };

        // Array Iteration
        playSection(library);
    }

    // Canteen Narration
    public static void canteenNarration() {
        String[] canteen = {
            "The canteen hums with the chatter of students...",
            
            "The wandering spirit appears at your side.",
            
            "\"This is no ordinary canteen...\"",
            
            "The spirit fades, and the scent of food and metal lingers in the air."
        };

        // Array Iteration
        playSection(canteen);
    }

    // Gym Narration
    public static void gymNarration() {
        String[] gym = {
            "The gym resounds with the clash of practice weapons...",
            
            "The wandering spirit flickers into view.",
            
            "\"This is where strength is forged...\"",
            
            "The spirit fades, leaving the echoes of steel and determination behind."
        };

        // Array Iteration
        playSection(gym);
    }

    // Principal's Office Narration
    public static void principalOfficeNarration() {
        String[] principalOffice = {
            "The doors of the Principal\'s Office stand tall and unyielding...",
            
            "The wandering spirit appears at your side...",
            
            "\"This office is the gateway...\"",
            
            "The spirit fades, and the silence around the doorway feels heavier..."
        };

        //Array Iteration
        playSection(principalOffice);
    }

    // Area 1 Narration
    public static void area1Narration() {
        String[] area1 = {
            "The trees of the inner forest rise around you...",
            "The wandering spirit drifts into view...",
            "\"This is the inner forest...\"",
            "The spirit fades, leaving you with the quiet but uneasy rhythm..."
        };

        playSection(area1);
    }

    // Area 2 Narration
    public static void area2Narration() {
        String[] area2 = {
            "The air thickens as you step into the swamp...",
            "The wandering spirit flickers beside you.",
            "\"This is the middle region...\"",
            "The spirit fades into the mist..."
        };

        playSection(area2);
    }

    // Area 3 Narration
    public static void area3Narration() {
        String[] area3 = {
            "Stone ruins and jagged towers stretch across the horizon...",
            "The wandering spirit appears one final time...",
            "\"This is the outer region...\"",
            "The spirit lingers for a moment longer..."
        };

        playSection(area3);
    }

    // Array Iteration in order to avoid for loop usage repitition
    public static void playSection(String[] section) {
        System.out.println("\nPress ENTER to continue...");
        
        for (int i = 0; i < section.length; i++) {
            scanner.nextLine();
            System.out.println(section[i]);
            
        }
        
        System.out.println(); 
    }

    // Shop Narration
    public static void shopNarration() {
        String[] shop = {
            "The spirit appears beside you, its form calm against the quiet sway of trees.",
            
            "\"This is the academy\'s supply shop,\" it says. \"Here, students exchange earned coin for tools of survival \n- weapons, potions, and the occasional rarity.\"",
            
            "\"The blades offered within are not forged for vanity. They are made to defend, to endure, and when necessary - to end.\"",
            
            "\"Potions line the shelves too. Restoratives, enhancers, mixtures \ndrawn from the deeper knowledge of the academy\'s alchemists. Each serves a purpose - if used wisely.\"",
            
            "\"Your currency is merit earned - through trials, tasks, and time. \nSpend it with intention. Not everything you need will be here twice.\"",
            
            "The spirit\'s gaze lingers on the door before fading again into the still air.",
            
            "You step forward. The scent of old leather, steel, \nand something faintly herbal greets you as the shop door creaks open."
        };
    
    playSection(shop);
    }

    // Inventory Narration
    public static void inventoryNarration() {
        String[] inventory = {
            "The spirit emerges without a sound, its form quiet and steady beside you.",

            "\"All that you carry tells a story,\" it begins. \"Not just of what you\'ve gathered - but of what you\'ve chosen to keep.\"",
            
            "\"Your inventory is more than a sack of tools. It holds your weapons, potions, relics - the fragments of your journey so far.\"",
            
            "\"Some items will aid you in battle. Others may offer insight, or protection, or... choices you do not yet understand.\"",
            
            "\"Know this — your space is not endless. What you carry reflects what you value. Choose wisely, discard carefully.\"",
            
            "The spirit lingers a moment longer, then drifts away — leaving you with your pack, your thoughts, and the quiet shifting of leaves overhead."
        };

        playSection(inventory);
    }

    public static void choiceSwordsman(){
        System.out.println();
        System.out.println("┌────────────────────┐");
        System.out.println("│  Player Character  │");
        System.out.println("└────────────────────┘");
        System.out.println("Kael Solmere");
        System.out.println("Age: 15");
        System.out.println("Personality: Intense, Brooding, Fiercely Loyal");
        System.out.println("Grade Level: First Year Student");
        System.out.println("Fun Fact: Siblings with Aria Caelith");
        System.out.println("The siblings aim to master their respective skills, uncover the truth about their family\'s past, \nand bring the “Kim Morvain” to justice. They rely on each other\'s strengths to overcome challenges \nand support each other in their quest for revenge and redemption.");
        System.out.println();
                
        System.out.println("┌───────────────────────────────────────────┐");
        System.out.println("│  Swordsman (MAX LVL: 60)                  │");
        System.out.println("│  Basic Attack -                           │");
        System.out.println("│  Skill 1 -                                │");
        System.out.println("│  Skill 2 -                                │");
        System.out.println("│  Ultimate -                               │");
        System.out.println("│                                           │");
        System.out.println("│  HP (Health Points): 4000                 │");
        System.out.println("│  MP (Mana Points):    500                 │");
        System.out.println("│  ATK (Attack):        400                 │");
        System.out.println("│  DEF (Defence):       250                 │");
        System.out.println("│  SPD (Speed):         180                 │");
        System.out.println("└───────────────────────────────────────────┘");
        System.out.println();
        
        System.out.println("┌──────────────┐");
        System.out.println("│  Backstory   │");
        System.out.println("└──────────────┘");
        
        System.out.println("Driven by a desire for revenge against the Kim Morvain, \nThe Dark Sorcerer” who destroyed their family\'s reputation and killed their parents.");
    }
    
    public static void choiceGunner() {
        System.out.println();
        System.out.println("┌────────────────────┐");
        System.out.println("│  Player Character  │");
        System.out.println("└────────────────────┘");
            
        System.out.println("Aria Caelith (Mother\'s Surname)");
        System.out.println("Age: 15");
        System.out.println("Personality: Quick-Witted, Resourceful, Fiercely Protective of Kael");
        System.out.println("Grade Level: First Year Student");
        System.out.println("Fun Fact: Siblings with Kael Solmere");
        System.out.println("The siblings aim to master their respective skills, uncover the truth about their family's past, \nand bring the “Kim Morvain” to justice. They rely on each other's strengths to overcome challenges \nand support each other in their quest for revenge and redemption.");
        System.out.println();
                
        System.out.println("┌───────────────────────────────────────────┐");
        System.out.println("│  Gunner (MAX LVL: 60)                     │");
        System.out.println("│  Basic Attack                             │");
        System.out.println("│  Skill 1 -                                │");
        System.out.println("│  Skill 2 -                                │");
        System.out.println("│  Ultimate -                               │");
        System.out.println("│                                           │");
        System.out.println("│  HP (Health Points): 2500                 │");
        System.out.println("│  MP (Mana Points):   1000                 │");
        System.out.println("│  ATK (Attack):        500                 │");
        System.out.println("│  DEF (Defence):       125                 │");
        System.out.println("│  SPD (Speed):         220                 │");
        System.out.println("└───────────────────────────────────────────┘");
        System.out.println();
            
        System.out.println("┌──────────────┐");
        System.out.println("│  Backstory   │");
        System.out.println("└──────────────┘");
        System.out.println("A skilled gunner with a talent for inventing and modifying firearms.");
        System.out.println();   
    }
    
    public static void choiceMage() {
        System.out.println();
        System.out.println("┌────────────────────┐");
        System.out.println("│  Player Character  │");
        System.out.println("└────────────────────┘");

        System.out.println("Selene Arclight");
        System.out.println("Age: 15");
        System.out.println("Personality: Prideful");
        System.out.println("Grade Level: First Year Student");
        System.out.println("Fun Fact: To be followed...");
        System.out.println();
            
        System.out.println("┌───────────────────────────────────────────┐");
        System.out.println("│  Mage (MAX LVL: 60)                       │");
        System.out.println("│  Basic Attack                             │");
        System.out.println("│  Skill 1 - Stellar Shard                  │");
        System.out.println("│  Skill 2- Chains of Starlight             │");
        System.out.println("│  Ultimate - Astral Cataclysm              │");
        System.out.println("│                                           │");
        System.out.println("│  HP (Health Points): 3200                 │");
        System.out.println("│  MP (Mana Points):    600                 │");
        System.out.println("│  ATK (Attack):        350                 │");
        System.out.println("│  DEF (Defence):       180                 │");
        System.out.println("│  SPD (Speed):         300                 │");
        System.out.println("└───────────────────────────────────────────┘");
        System.out.println();
            
        System.out.println("┌──────────────┐");
        System.out.println("│  Backstory   │");
        System.out.println("└──────────────┘");
        System.out.println("Born into the prestigious mage family, Arclight, as the legitimate eldest child, \nSelene was expected to inherit the family head’s mantle. Until her father brought \nhome a young boy claiming that he is her brother, and that she should support him \nin becoming the next head of the family.");
        System.out.println("Determined to reclaim her status as heir, she enrolled in the academy. Her goal? \nTo learn everything the academy has to offer and achieve something so great \nthat even her father can’t deny her worth. \nAnd killing the “Kim Morvain” seems just right for that.");
        System.out.println();
    }
}