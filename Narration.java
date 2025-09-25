import java.util.Scanner;

class Prologue {

    static Scanner scanner = new Scanner(System.in);
    
    public static void openingPrologue() {
        String[] prologue = {
            "Your days as a student have been a blur of half-hearted effort and quiet frustration. Lessons slip past you, faces blur together, and the weight of expectations presses down heavier with each passing week. Tonight feels no different—you collapse into bed, hoping that sleep might offer an escape, if only for a few hours.",
            "But this sleep is unlike any other.",
            "You wake with a start, not in your room, but in a place you have never seen before. The air is sharp and crisp, carrying the faint scent of pine and old stone. A pale moon hangs above an endless forest, its silver light spilling over a towering structure in the distance—an academy, its spires reaching for the sky.",
            "As you take a cautious step forward, a presence brushes against you—weightless, yet undeniable. A wandering spirit materializes from the air itself, its form shifting like smoke caught in a breeze. Its voice is calm, distant, but warm enough to ease the unease clawing at your chest.",
            "\"Welcome, lost one,\" it says. \"This is Mystvale Academy… a place where magic is studied, honed, and wielded against the forces beyond these walls. You now stand on the threshold of its trials.\"",
 
            "\"The academy rests at the edge of a vast forest, shielding its students from the dangers beyond.\"",
            "\"The inner region, just past the academy`s borders, is alive with mystical woods and wandering lesser entities.\"",
            "\"The middle region stretches into treacherous swamplands, where stronger beings test the skill of even seasoned students.\"",
            "\"And far beyond lies the outer region—the dominion of entities. Few dare tread there, for it is said the most fearsome of them all awaits in its shadowed heart.\"",
            "The spirit`s gaze lingers on you, sharp and knowing.",
            "\"Why you were brought here, only time will reveal. But your path begins now. Learn, survive, and perhaps… you may one day stand against what lies beyond the forest.\"",
            "The spirit fades, leaving only silence and the academy`s looming silhouette in the distance.",
            "Your story has begun."
        };
 
        for (int i = 0; i < prologue.length; i++) {
            scanner.nextLine();
            System.out.println();
            System.out.println(prologue[i]);
        }
        scanner.close();
    }

    public static void playSection(String[] section) {
        System.out.println("\nPress ENTER to continue...");
        
        for (int i = 0; i < section.length; i++) {
            scanner.nextLine();
            System.out.println(section[i]);
            
        }
        
        System.out.println(); 
        
    }

}
