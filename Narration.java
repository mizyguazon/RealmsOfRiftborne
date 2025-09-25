import java.util.Scanner;

class Narration{
    static Scanner scanner = new Scanner(System.in);

    // Narration for Prologue
    public static void prologueNarration() {
        String[] prologue = {
            "Your days as a student have been a blur of half-hearted effort and quiet frustration. Lessons slip past you, faces blur together, and the weight of expectations presses down heavier with each passing week. Tonight feels no different—you collapse into bed, hoping that sleep might offer an escape, if only for a few hours.",
            
            "But this sleep is unlike any other.",
            
            "You wake with a start, not in your room, but in a place you\'ve never seen before. The air is sharp and crisp, carrying the faint scent of pine and old stone. A pale moon hangs above an endless forest, its silver light spilling over a towering structure in the distance—an academy, its spires reaching for the sky.",
            
            "As you take a cautious step forward, a presence brushes against you—weightless, yet undeniable. A wandering spirit materializes from the air itself, its form shifting like smoke caught in a breeze. Its voice is calm, distant, but warm enough to ease the unease clawing at your chest.",
            
            "\"Welcome, lost one,\" it says. \"This is Mystvale Academy… a place where magic is studied, honed, and wielded against the forces beyond these walls. You now stand on the threshold of its trials.\"",
            
            "\"The academy rests at the edge of a vast forest, shielding its students from the dangers beyond.\"",
            
            "\"The inner region, just past the academy\'s borders, is alive with mystical woods and wandering lesser entities.\"",
            
            "\"The middle region stretches into treacherous swamplands, where stronger beings test the skill of even seasoned students.\"",
            
            "\"And far beyond lies the outer region—the dominion of entities. Few dare tread there, for it is said the most fearsome of them all awaits in its shadowed heart.\"",
            
            "The spirit\'s gaze lingers on you, sharp and knowing.",
            
            "\"Why you were brought here, only time will reveal. But your path begins now. Learn, survive, and perhaps… you may one day stand against what lies beyond the forest.\"",
            
            "The spirit fades, leaving only silence and the academy’s looming silhouette in the distance.",
            
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
            
            "\"This is the academy\'s supply shop,\" it says. \"Here, students exchange earned coin for tools of survival — weapons, potions, and the occasional rarity.\"",
            
            "\"The blades offered within are not forged for vanity. They are made to defend, to endure, and when necessary — to end.\"",
            
            "\"Potions line the shelves too. Restoratives, enhancers, mixtures drawn from the deeper knowledge of the academy\'s alchemists. Each serves a purpose — if used wisely.\"",
            
            "\"Your currency is merit earned — through trials, tasks, and time. Spend it with intention. Not everything you need will be here twice.\"",
            
            "The spirit\'s gaze lingers on the door before fading again into the still air.",
            
            "You step forward. The scent of old leather, steel, and something faintly herbal greets you as the shop door creaks open."
        };
    
    playSection(shop);
    
    }

    // Inventory Narration
    public static void inventoryNarration() {
        String[] inventory = {
            "The spirit emerges without a sound, its form quiet and steady beside you.",

            "\"All that you carry tells a story,\" it begins. \"Not just of what you’ve gathered — but of what you’ve chosen to keep.\"",
            
            "\"Your inventory is more than a sack of tools. It holds your weapons, potions, relics — the fragments of your journey so far.\"",
            
            "\"Some items will aid you in battle. Others may offer insight, or protection, or... choices you do not yet understand.\"",
            
            "\"Know this — your space is not endless. What you carry reflects what you value. Choose wisely, discard carefully.\"",
            
            "The spirit lingers a moment longer, then drifts away — leaving you with your pack, your thoughts, and the quiet shifting of leaves overhead."
        };
        
        playSection(inventory);

    }



    
}