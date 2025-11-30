package Narration;

import java.util.Scanner;

public class Narration{
    static Scanner scanner = new Scanner(System.in);

    // Narration for Prologue
    public void prologueNarration() {
        String[] prologue = {
            "Your days as a student have been a blur of half-hearted effort and quiet frustration. \nLessons slip past you, faces blur together, and the weight of expectations presses down heavier with each passing week. \nTonight feels no different-you collapse into bed, hoping that sleep might offer an escape, if only for a few hours.",
            
            "But this sleep is unlike any other.",
            
            "You wake with a start, not in your room, but in a place you\'ve never seen before. \nThe air is sharp and crisp, carrying the faint scent of pine and old stone. A pale moon hangs above an endless forest, \nits silver light spilling over a towering structure in the distance - a fortress, its spires reaching for the sky.",
            
            "As you take a cautious step forward, a presence brushes against you. Weightless, yet undeniable. \nA wandering spirit materializes from the air itself, its form shifting like smoke caught in a breeze. \nIts voice is calm, distant, but warm enough to ease the unease clawing at your chest.",
            
            "\"Welcome, lost one. Please to meet you, I am Louraine Aetherlight, the wandering spirit it says.\" it says \"This is Mystvale Academy… a place where magic is studied, honed, \nand wielded against the forces beyond these walls. You now stand on the threshold of its trials.\"",
            
            "\"The academy rests at the edge of a vast forest, shielding its students from the dangers beyond.\"",
            
            "\"The inner region, just past the academy\'s borders, is alive with mystical woods and wandering lesser entities.\"",
            
            "\"The middle region stretches into treacherous swamplands, where stronger beings test the skill of even seasoned students.\"",
            
            "\"And far beyond lies the outer region - the dominion of entities. Few dare tread there, \nfor it is said the most fearsome of them all awaits in its shadowed heart.\"",
            
            "Louraine's gaze lingers on you, sharp and knowing.",
            
            "\"Why you were brought here, only time will reveal. But your path begins now. \nLearn, survive, and perhaps... you may one day stand against what lies beyond the forest.\"",
            
            "Louraine, the wandering spirit, fades, leaving only silence and the academy\'s looming silhouette in the distance.",
            
            "Your story has begun."
        };

        // Array Iteration 
        playSection(prologue);
    }

    // Academy Narration
    public void academyNarration() {
        String[] academy = {
            "The tall gates of Mystvale Academy open with a low groan...",
            
            "You pause, taking it all in, when a sudden chill brushes your shoulder...",
            
            "You nearly stumble back in shock. It's Louraine again!",
            
            "\"I didn't expect you again,\" you mutter under your breath.",
            
            "The Louraine's form flickers faintly, its voice calm and patient.",
            
            "\"Do not be alarmed. Mystvale Academy is vast...\"",
            
            "She gestures toward the campus...",
            
            "\"The Library, a hall of ancient knowledge...\nYou can increase your stats by accepting quests. Be aware that those quests are limited.\"",
            
            "\"The Training Ground, where strength is forged...\nOnce you finish training, you will be granted an eligibility pass through the Principal's Office.\"",

            "\"In every area outside the academy, you need an eligibility pass. So, you must go through the Principal's office first.\"",
            
            "\"And finally\" the Louraine's form stills...",
            
            "Its glow dims slightly as the explanations end.",
            
            "\"Here, you are free to choose your path...\"",
            
            "The spirit fades as quickly as it appeared..."
        };

        // Array Iteration
        playSection(academy);
    }

    // Library Narration
    public void libraryNarration() {
        String[] library = {
            "As you step into the library, the air grows still...",
            
            "The Louraine, the wandering spirit, flickers into view...",
            
            "\"This is Mystvale's Library. May knowledge guide you...\"",
            
            "Then she fades out of your sight, leaving only the quiet rustle of unseen pages."
        };

        // Array Iteration
        playSection(library);
    }

    // Gym Narration
    public void gymNarration() {
        String[] gym = {
            "The Training Ground resounds with the clash of practice weapons...",
            "Louraine, as expected, flickers into view.",
            "\"This is where strength is forged. Accept the challenge,",
            "and receive the gift of strength, precision, and courage...\"",
            "The spirit fades, leaving the echoes of steel and determination behind."
        };

        // Array Iteration
        playSection(gym);
    }

    // Principal's Office Narration
    public void principalOfficeNarration() {
        String[] principalOffice = {
            "The doors of the Principal\'s Office stand tall and unyielding...",
            
            "Louraine appears at your side...",
            
            "\"This office is the gateway through end of this vast land. May the odds be in your favor...\"",
            
            "Louraine fades, and the silence around the doorway feels heavier..."
        };

        //Array Iteration
        playSection(principalOffice);
    }

    // Area 1 Narration
    public void area1Narration() {
        String[] area1 = {
            "The trees of the inner forest rise around you...",
            "Louraine, then drifts into view...",
            "\"This is the inner forest, where you\'d be able to meet the lowest of lows entities.\"",
            "Before fading away, she looked at you with a meaningful look before vanishing out of your sight,\nleaving you with the quiet but uneasy rhythm..."
        };

        playSection(area1);
    }

    // Area 2 Narration
    public void area2Narration() {
        String[] area2 = {
            "The air thickens as you step into the swamp area. Muddy, stinky, and lacks of life. You felt a sudden chill down your spines.",
            "Louraine, as expected, flickers beside you.",
            "\"This is the middle region, the Swamp, where much stronger entities lives. Be careful, they may seem not strong, but they are more blood thirsty\"",
            "Before fading away, she touched your shoulder, giving you the look of encouragement.\n Before you could even say a thing, she then vanished into the mist..."
        };

        playSection(area2);
    }

    // Area 3 Narration
    public void area3Narration() {
        String[] area3 = {
            "Stone ruins and jagged towers stretch across the horizon. Suprisingly, it has more life than the previous areas you have been.\nBut you cannot seem to shake off the eerie feeling, your test tighten as you feel like suffocated with the how heavy the feeling is.",
            "Louraine then appears, her face radiates something you cannot pinpoint.",
            "\"This is the outer region, the Forsaken Lands where all strong entities lives. But at the end of this land, there lies the strongest of them all.\nMy only advice to you is to never let your guard down, and may the odds be in your favor...\"",
            "Louraine lingers for a moment longer before fading away from your sight.\nBut as her presence left the area, you felt the wind lingers onto your skin, feeling like she is still there, not quite present, but watching over you."
        };

        playSection(area3);
    }

    // Shop Narration
    public void shopNarration() {
        String[] shop = {
            "Louraine appears beside you, its form calm against the quiet sway of trees.",
            
            "\"This is the academy\'s supply shop,\" it says. \"Here, students exchange earned coin for tools of survival \n- weapons, potions, and the occasional rarity.\"",
            
            "\"The blades offered within are not forged for vanity. They are made to defend, to endure, and when necessary - to end.\"",
            
            "\"Potions line the shelves too. Restoratives, enhancers, mixtures \ndrawn from the deeper knowledge of the academy\'s alchemists. Each serves a purpose - if used wisely.\"",
            
            "\"Your currency is merit earned - through trials, tasks, and time. \nSpend it with intention. Not everything you need will be here twice.\"",
            
            "Louraine's gaze lingers on the door before fading again into the still air.",
            
            "You step forward. The scent of old leather, steel, \nand something faintly herbal greets you as the shop door creaks open."
        };
    
        playSection(shop);
    }

    public void shopConversationNarration() { // narration not used | to be followed
        String[] shopOwner = {
            "You push open the creaking door, and the scent of herbs and aged wood fills the air. Sunlight glints off shelves stacked with mysterious trinkets and glowing vials.",

            "A small bell jingles. Behind the counter, the shopkeeper peers over his spectacles.",

            "\"Ah! A new face,\" he says. \"Welcome to Mystic Curiosities! I\'m Kabang Cobbleton. Handle the items wisely—they have stories… and secrets.\"",

            "You nod, heart racing with curiosity. Your adventure in the shop has begun."
        };

        playSection(shopOwner);
    }

    // Inventory Narration | Not Used
    public void inventoryNarration() {
        String[] inventory = {
            "Louraine emerges without a sound, its form quiet and steady beside you.",

            "\"All that you carry tells a story,\" it begins. \"Not just of what you\'ve gathered - but of what you\'ve chosen to keep.\"",
            
            "\"Your inventory is more than a sack of tools. It holds your weapons, potions, relics - the fragments of your journey so far.\"",
            
            "\"Some items will aid you in battle. Others may offer insight, or protection, or... choices you do not yet understand.\"",
            
            "\"Know this — your space is not endless. What you carry reflects what you value. Choose wisely, discard carefully.\"",
            
            "The spirit lingers a moment longer, then drifts away — leaving you with your pack, your thoughts, and the quiet shifting of leaves overhead."
        };

        playSection(inventory);
    }

    public void swordsmanDetails() { // implemented with print delay
        String[] lines = {
            "┌──────────────────────────────────────────────────────────────────────────────┐",
            "│                         Kael Solmere - The Blade of Dawn                     │",
            "│                            Player Character Details                          │",
            "├──────────────────────────────────────────────────────────────────────────────┤",
            "│ Name: Kael Solmere                                                           │",
            "│ Age: 15                                                                      │",
            "│ Personality: Intense, Brooding, Fiercely Loyal                               │",
            "│ Grade Level: First Year                                                      │",
            "│ Fun Facts:                                                                   │",
            "│   - Talks to his shadow when thinking                                        │",
            "│   - Collects old maps and mysterious relics                                  │",
            "│   - Can't resist chocolate during stressful situations                       │",
            "│   - Has a soft spot for stray animals                                        │",
            "│                                                                              │",
            "│ Skill Details:                                                               │",
            "│   Basic Attack                                                               │",
            "│    - A precise sword strike that restores mana                               │",
            "│                                                                              │",
            "│   Blade Dance                                                                │",
            "│    - A swift combo of slashes that deals increased damage                    │",
            "│                                                                              │",
            "│   Blinding Silhouette                                                        │",
            "│    - A fast strike that stuns the enemy for 1 turn                           │",
            "│      or refreshes the stun                                                   │",
            "│                                                                              │",
            "│   Shattered Sun                                                              │",
            "│    - A powerful finishing blow that stuns the enemy                          │",
            "│      for 2 turns or refreshes the stun                                       │",
            "└──────────────────────────────────────────────────────────────────────────────┘",
            "┌─────────────────────────────────┐",
            "│     Press ENTER to continue     │",
            "└─────────────────────────────────┘"
        };


        for (int i = 0; i < lines.length; i++) {  
            printLineWithDelay(lines[i], 15);
        }

        scanner.nextLine(); 
        System.out.println();
    }


    public void swordsmanStats() { // implemented through revision
        System.out.println();
        System.out.println("┌───────────────────────────────────────────┐");
        System.out.println("│      Kael Solmere - The Blade of Dawn     │");
        System.out.println("│               Combat Overview             │");
        System.out.println("│-------------------------------------------│");
        System.out.println("│  Swordsman (Max Level: 60)                │");
        System.out.println("│  Basic Attack                             │");
        System.out.println("│  Skill 1 - Blade Dance                    │");
        System.out.println("│  Skill 2 - Blinding Silhouette            │");
        System.out.println("│  Ultimate - Shattered Sun                 │");
        System.out.println("│                                           │");
        System.out.println("│  HP (Health Points): 4000                 │");
        System.out.println("│  MP (Mana Points):    700                 │");
        System.out.println("│  ATK (Attack):        500                 │");
        System.out.println("│  DEF (Defence):       350                 │");
        System.out.println("│  SPD (Speed):         200                 │");
        System.out.println("└───────────────────────────────────────────┘");

        System.out.println("┌──────────────────────────────┐");
        System.out.println("│   Press ENTER to continue    │");
        System.out.println("└──────────────────────────────┘");
        scanner.nextLine();
        System.out.println();

    }

    public void swordsmanBackstory() { // implemented with print delay
        String[] lines = {
            "┌───────────────────────────────────────────────────────────────────────────────┐",
            "│                       Kael Solmere - The Blade of Dawn                        │",
            "│                                  Backstory                                    │",
            "├───────────────────────────────────────────────────────────────────────────────┤",
            "│ Kael Solmere was born into a family burdened with a dark legacy. The Solmeres │",
            "│ were once the guardians of the Kim Morvain, a forbidden artifact of immense   │",
            "│ power. Centuries ago, a treacherous betrayal shattered the family, scattering │",
            "│ its members and leaving a lingering curse upon their bloodline.               │",
            "│                                                                               │",
            "│ Now, Kael and his sister Aria are the last heirs. Their path is not only about│",
            "│ mastering their extraordinary abilities but also unraveling the secrets       │",
            "│ haunting their family. Bound by loyalty and shared struggle, the siblings     │",
            "│ rely on each other to survive trials, uncover hidden truths, and confront the │",
            "│ forces behind the Kim Morvain. Every step they take brings them closer to     │",
            "│ breaking the curse—if they can withstand the shadows of their bloodline.      │",
            "└───────────────────────────────────────────────────────────────────────────────┘",
            "┌──────────────────────────────┐",
            "│   Press ENTER to continue    │",
            "└──────────────────────────────┘"
        };

        for (int i = 0; i < lines.length; i++) {
            printLineWithDelay(lines[i], 25);    
        }

        scanner.nextLine(); 
        System.out.println();
    }


    public void gunnerDetails() { // implemented with print delay
        String[] lines = {
            "┌────────────────────────────────────────────────────────────────────────────────┐",
            "│                          Aria Caelith - The Void's Eye                         │",
            "│                            Player Character Details                            │",
            "├────────────────────────────────────────────────────────────────────────────────┤",
            "│ Age: 15                                                                        │",
            "│ Personality: Quick-Witted, Resourceful, Fiercely Protective of Kael            │",
            "│ Grade Level: First Year Student                                                │",
            "│ Fun Facts:                                                                     │",
            "│   - Skilled gunner and inventor                                                │",
            "│   - Always carries a small notebook for invention ideas                        │",
            "│   - Loves collecting rare crystals and gemstones                               │",
            "│   - Has a habit of whistling when nervous                                      │",
            "│   - Can memorize complex mechanisms after seeing them once                     │",
            "│   - Prefers fast-paced games over slow strategy games                          │",
            "│                                                                                │",
            "│ Skill Details:                                                                 │",
            "│   Basic Attack                                                                 │",
            "│    - A quick pistol shot that restores mana                                    │",
            "│                                                                                │",
            "│   Crimson Barrage                                                              │",
            "│    - A rapid burst of bullets that deals increased damage                      │",
            "│                                                                                │",
            "│   Frostwind Bullet                                                             │",
            "│    - A chilling shot that stuns the enemy for 2 turns or refreshes the stun    │",
            "│                                                                                │",
            "│   Judgement Phantom                                                            │",
            "│    - A high-impact phantom round that delivers massive damage                  │",
            "│      for 2 turns or refreshes the stun                                         │",
            "└────────────────────────────────────────────────────────────────────────────────┘",
            "┌──────────────────────────────┐",
            "│   Press ENTER to continue    │",
            "└──────────────────────────────┘"
        };

        for (int i = 0; i < lines.length; i++) {  
            printLineWithDelay(lines[i], 25);   
        }

        scanner.nextLine(); 
        System.out.println();
    }


    public void gunnerStats() { // implemented through revision
        System.out.println();
        System.out.println("┌───────────────────────────────────────────┐");
        System.out.println("│       Aria Caelith - The Void's Eye       │");
        System.out.println("│       Sharpshooter's Combat Overview      │");
        System.out.println("│-------------------------------------------│");
        System.out.println("│  Gunner (Max LeveL: 60)                   │");
        System.out.println("│  Basic Attack                             │");
        System.out.println("│  Skill 1 - Flurry Shots                   │");
        System.out.println("│  Skill 2 - Frostwind Bullet               │");
        System.out.println("│  Ultimate - Judgement Phantom             │");
        System.out.println("│                                           │");
        System.out.println("│  HP (Health Points): 4000                 │");
        System.out.println("│  MP (Mana Points):    600                 │");
        System.out.println("│  ATK (Attack):        450                 │");
        System.out.println("│  DEF (Defence):       300                 │");
        System.out.println("│  SPD (Speed):         200                 │");
        System.out.println("└───────────────────────────────────────────┘");

        System.out.println("┌──────────────────────────────┐");
        System.out.println("│   Press ENTER to continue    │");
        System.out.println("└──────────────────────────────┘");
        scanner.nextLine();
        System.out.println();

    }

    public void gunnerBackstory() { // implemented with time delay

        String[] lines = {
            "┌────────────────────────────────────────────────────────┐",
            "│               Aria Caelith - The Void's Eye            │",
            "│                       Backstory                        │",
            "├────────────────────────────────────────────────────────┤",
            "│                                                        │",
            "│ Aria Caelith was taken from his home as a child,       │",
            "│ abducted during a covert raid led by Kim Morvain.      │",
            "│ Selected for Project LUCENT, he was subjected to       │",
            "│ brutal experiments that fused Aether Pulse energy      │",
            "│ into his nervous system, granting him deadly precision │",
            "│ at the cost of his own lifespan.                       │",
            "│                                                        │",
            "│ In the cold metal halls where failure meant death,     │",
            "│ Aria developed Aether Sight -an ability to read motion,│",
            "│ pressure, and angles with impossible clarity. His      │",
            "│ bullets could bend through steel and air, but using    │",
            "│ this power burned his body from within.                │",
            "│                                                        │",
            "│ After years of torment, he escaped the facility,       │",
            "│ vowing to destroy Morvain and end Project LUCENT       │",
            "│ before it creates an army of controlled soldiers made  │",
            "│ from his stolen power.                                 │",
            "│                                                        │",
            "│ In the shadows, his name is both feared and whispered  │",
            "│  -Aria Caelith, the last experiment who never misses.  │",
            "│                                                        │",
            "└────────────────────────────────────────────────────────┘",
            "┌──────────────────────────────┐",
            "│   Press ENTER to continue    │",
            "└──────────────────────────────┘"
        };


        /*String[] lines = {
            "┌───────────────────────────────────────────────────────┐",
            "│              Aria Caelith - The Void's Eye            |",
            "│                       Backstory                       │",
            "├───────────────────────────────────────────────────────┤",
            "│                                                       │",
            "│ Aria Caelith grew up in the lawless outskirts of      │",
            "│ Luthien, where survival demanded more than courage -  │",
            "│ it demanded precision and ruthlessness. Trained from  │",
            "│ a young age in firearms and tactical combat, he       │",
            "│ quickly became a feared gunner and mercenary.         │",
            "│                                                       │",
            "│ His family was torn apart by betrayal and political   │",
            "│ schemes, leaving Aria to forge his own path in a      │",
            "│ world that respected only strength and cunning.       │",
            "│                                                       │",
            "│ Obsessed with the Kim Morvain, an artifact of immense │",
            "│ destructive potential, he walks the line between      │",
            "│ vigilante and outlaw, using every shot and strategy   │",
            "│ to ensure his survival and enforce his own brand of   │",
            "│ justice. In the shadows, his name is both feared and  │",
            "│ whispered -Aria Caelith, the gunner who never misses. │",
            "│                                                       │",
            "└───────────────────────────────────────────────────────┘",
            "┌──────────────────────────────┐",
            "│   Press ENTER to continue    │",
            "└──────────────────────────────┘"
        };
        */

        System.out.println(); 

        for (int i = 0; i < lines.length; i++) {
            printLineWithDelay(lines[i], 25);
        }

        scanner.nextLine();
        System.out.println();
    }

    public void mageDetails() { // implemented with print delay

        String[] lines = {
            "┌───────────────────────────────────────────────────────────────────────────────┐",
            "│                  Selene Arclight - Weaver of Arcane Flames                    │",
            "│                           Player Character Details                            │",
            "├───────────────────────────────────────────────────────────────────────────────┤",
            "│ Age: 15                                                                       │",
            "│ Personality: Prideful                                                         │",
            "│ Grade Level: First Year Student                                               │",
            "│ Fun Facts:                                                                    │",
            "│   - Always practices spells under moonlight                                   │",
            "│   - Can memorize complex incantations after hearing them once                 │",
            "│   - Has a secret love for herbal teas                                         │",
            "│   - Often talks to magical familiars when thinking                            │",
            "│   - Collects rare magical crystals                                            │",
            "│                                                                               │",
            "│ Skill Details:                                                                │",
            "│   Basic Attack                                                                │",
            "│    - A focused blast of starlight that restores mana                          │",
            "│                                                                               │",
            "│   Stellar Shard                                                               │",
            "│    - A condensed star fragment that strikes the enemy                         │",
            "│      with amplified magic damage                                              │",
            "│                                                                               │",
            "│   Chains of Starlight                                                         │",
            "│    - Celestial bindings that deal damage and immobilize the enemy for         │",
            "│      2 turns or refresh the stun                                              │",
            "│                                                                               │",
            "│   Astral Cataclysm                                                            │",
            "│    - A devastating surge of astral magic that unleashes                       │",
            "│      massive destructive power                                                │",
            "└───────────────────────────────────────────────────────────────────────────────┘",
            "┌──────────────────────────────┐",
            "│   Press ENTER to continue    │",
            "└──────────────────────────────┘"
        };

        System.out.println(); 

        for (int i = 0; i < lines.length; i++) {
            printLineWithDelay(lines[i], 25); 
        }

        scanner.nextLine(); 
        System.out.println();
    }

    public void mageStats() { // implemented with print delay

        String[] lines = {
            "┌───────────────────────────────────────────┐",
            "│ Selene Arclight - Weaver of Arcane Flames │",
            "│        Mage's Combat Overview             │",
            "│-------------------------------------------│",
            "│  Mage (Max Level: 60)                     │",
            "│  Basic Attack                             │",
            "│  Skill 1 - Stellar Shard                  │",
            "│  Skill 2 - Chains of Starlight            │",
            "│  Ultimate - Astral Cataclysm              │",
            "│                                           │",
            "│  HP (Health Points): 4000                 │",
            "│  MP (Mana Points):   1000                 │",
            "│  ATK (Attack):        550                 │",
            "│  DEF (Defence):       180                 │",
            "│  SPD (Speed):         200                 │",
            "└───────────────────────────────────────────┘",
            "┌──────────────────────────────┐",
            "│   Press ENTER to continue    │",
            "└──────────────────────────────┘"
        };

        System.out.println(); 

        for (int i = 0; i < lines.length; i++) {
            printLineWithDelay(lines[i], 25);
        }

        scanner.nextLine(); 
        System.out.println();
    }


    /*public void mageStats() { // implemented through revision
        System.out.println();
        System.out.println("┌───────────────────────────────────────────┐");
        System.out.println("│ Selene Arclight - Weaver of Arcane Flames │");
        System.out.println("│        Mage's Combat Overview             │");
        System.out.println("│-------------------------------------------│");
        System.out.println("│  Mage (Max Level: 60)                     │");
        System.out.println("│  Basic Attack                             │");
        System.out.println("│  Skill 1 - Stellar Shard                  │");
        System.out.println("│  Skill 2 - Chains of Starlight            │");
        System.out.println("│  Ultimate - Astral Cataclysm              │");
        System.out.println("│                                           │");
        System.out.println("│  HP (Health Points): 4000                 │");
        System.out.println("│  MP (Mana Points):   1000                 │");
        System.out.println("│  ATK (Attack):        550                 │");
        System.out.println("│  DEF (Defence):       180                 │");
        System.out.println("│  SPD (Speed):         200                 │");
        System.out.println("└───────────────────────────────────────────┘");
        System.out.println("┌──────────────────────────────┐");
        System.out.println("│   Press ENTER to continue    │");
        System.out.println("└──────────────────────────────┘");
        scanner.nextLine();
        System.out.println();

    }
        */

    public void mageBackstory() { // implemented with print delay

        String[] lines = {
            "┌────────────────────────────────────────────────────────────────────────────────┐",
            "│                  Selene Arclight - Weaver of Arcane Flames                     |",
            "│                                  Backstory                                     │",
            "├────────────────────────────────────────────────────────────────────────────────┤",
            "│                                                                                │",
            "│ Selene Arclight was born into the prestigious Arclight lineage, her talent in  │",
            "│ cosmic and starlight magic evident from a young age. Morvain recruited her to  │",
            "│ help create the spells and enchantments that fueled Project LUCENT.            │",
            "│                                                                                │",
            "│ She soon discovered the project's true purpose: weaponizing human lives and    │",
            "│ spreading destruction. Realizing her complicity, she tried to escape—but was   │",
            "│ cursed, her magic twisted to both empower and torment her.                     │",
            "│                                                                                │",
            "│ Consumed by guilt and driven by vengeance, Selene now wields her powers to     │",
            "│ stop Morvain. Each spell carries the precision of her craft and the weight of  │",
            "│ remorse, guiding her toward the Forsaken Lands where he awaits.                │",
            "│                                                                                │",
            "│ Her name is spoken with caution and awe—Selene Arclight, the mage whose        │",
            "│ genius became a weapon, and whose vengeance burns brighter than any star.      │",
            "│                                                                                │",
            "└────────────────────────────────────────────────────────────────────────────────┘",
            "┌──────────────────────────────┐",
            "│   Press ENTER to continue    │",
            "└──────────────────────────────┘"
        };


        System.out.println(); 

        for (int i = 0; i < lines.length; i++) {
            printLineWithDelay(lines[i], 25); 
        }

        scanner.nextLine();
        System.out.println();
    }

    public void playSection(String[] section) {

        String[] blankLines = {
            "",
            "",
            "",
        };

        while (true) {
            try {
                System.out.println();
                System.out.println("┌───────────────────────────────────────────┐");
                System.out.println("│   Do you want to skip narration? (y/n):   │");
                System.out.println("└───────────────────────────────────────────┘");
                System.out.print("-->| ");

                String input = scanner.nextLine().trim();
                if (input.isEmpty()) input = " ";
                char skipChoice = input.charAt(0);

                switch (skipChoice) {
                    case 'y':
                        System.out.println("┌───────────────────────┐");
                        System.out.println("│   Narration skipped   │");
                        System.out.println("└───────────────────────┘");
                        return;

                    case 'n':
                        /*System.out.println();
                        System.out.println("┌─────────────────────────────┐");
                        System.out.println("│   Press Enter to continue   │");
                        System.out.println("└─────────────────────────────┘");
                        */

                        blankSeparators(blankLines, 50);

                        for (int i = 0; i < section.length; i++) {
                            //scanner.nextLine(); 
                            printWithDelay(section[i], 10); 
                            System.out.println("\n");
                        }

                        System.out.println();
                        return;

                    default:
                        System.out.println();
                        System.out.println("┌────────────────────────────────────────┐");
                        System.out.println("│   Choice unclear! Enter 'y' or 'n'.    │");
                        System.out.println("└────────────────────────────────────────┘");
                        break;
                }
            } catch (Exception e) {
                System.out.println();
                System.out.println("┌──────────────────────────────────────────────┐");
                System.out.println("│   An unexpected error occurred. Try again.   │");
                System.out.println("└──────────────────────────────────────────────┘");
                scanner.nextLine();
            }
        }
    }

    public void printWithDelay(String text, long delay) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void printLineWithDelay(String text, long delay) {
        System.out.println(text);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void promptSeparator() {

        String[] blankLines = {
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
        };

        blankSeparators((blankLines), 50);


    }

    public void promptSeparatorResized() {

        String[] blankLines = {
            "",
            "",
            "",
        };

        blankSeparators((blankLines), 50);

    }

    
    public void blankSeparators(String arr[], int num) {

        for (int i = 0; i < arr.length; i++) {
            //scanner.nextLine(); 
            printLineWithDelay(arr[i], num); 
            //System.out.println("\n");
        }
    }

    public void characterSeparators(String arr[], int num) {

        for (int i = 0; i < arr.length; i++) {
            //scanner.nextLine(); 
            printWithDelay(arr[i], num); 
            //System.out.println("\n");
        }
    }


    // Array Iteration in order to avoid for loop usage repitition
    /* 
    public static void playSection(String[] section) {
        while (true) {
            try {
                System.out.println();
                System.out.println("┌───────────────────────────────────────────┐");
                System.out.println("│   Do you want to skip narration? (y/n):   │");
                System.out.println("└───────────────────────────────────────────┘");
                System.out.print("-->| ");

                String input = scanner.nextLine().trim();
                if (input.isEmpty()) input = " ";
                char skipChoice = input.charAt(0);

                switch (skipChoice) {
                    case 'y':
                        System.out.println("┌───────────────────────┐");
                        System.out.println("│   Narration skipped   │");
                        System.out.println("└───────────────────────┘");
                        return;

                    case 'n':
                        System.out.println();
                        System.out.println("┌─────────────────────────────┐");
                        System.out.println("│   Press Enter to continue   │");
                        System.out.println("└─────────────────────────────┘");

                        for (String line : section) {
                            scanner.nextLine();
                            System.out.println(line);
                        }
                        System.out.println(); 
                        return; 
                    
                    default:
                        System.out.println();
                        System.out.println("┌────────────────────────────────────────┐");
                        System.out.println("│   Choice unclear! Enter 'y' or 'n'.    │");
                        System.out.println("└────────────────────────────────────────┘");
                        break;
                }
            } catch (Exception e) {
                System.out.println();
                System.out.println("┌──────────────────────────────────────────────┐");
                System.out.println("│   An unexpected error occurred. Try again.   │");
                System.out.println("└──────────────────────────────────────────────┘");
                scanner.nextLine(); 
            }
        }
    }
        */
}