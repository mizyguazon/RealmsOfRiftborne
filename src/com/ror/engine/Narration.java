package com.ror.engine;

public class Narration {

    private Narration() {} 

    // -------------------------------------------------------------------------
    // Narration sequences
    // -------------------------------------------------------------------------

    public static String[] buildAcademyNarration() {
        return new String[] {
                "The tall gates of Mystvale Academy open with a low groan as you step inside.",
                "A familiar chill brushes your shoulder. Void appears, flickering softly in the light.",
                "\"Mystvale is vast,\" the spirit says. \"The Library offers knowledge and quests. The Training Ground forges your strength.\"",
                "\"The Principal's Office decides your eligibility, and every path beyond the academy comes with danger.\"",
                "\"Choose your road carefully. Every place here will shape the hero you become.\""
        };
    }

    public static String[] buildLibraryNarration() {
        return new String[] {
                "As you step into the library, the air grows still and heavy with old paper and quiet thought.",
                "Void flickers into view beside the shelves. \"This is Mystvale's Library. May knowledge guide you.\"",
                "The spirit fades, leaving only the rustle of unseen pages and the promise of secrets waiting to be uncovered."
        };
    }

    public static String[] buildTrainingNarration() {
        return new String[] {
                "The Training Ground bursts with life: sparring steel, shouted instructions, and the rhythm of practiced movement.",
                "A tall coach studies your stance with sharp eyes. \"Untaught, but solid. If you want to grow stronger, earn it.\"",
                "\"Training here isn't just power,\" the coach warns. \"It's control, discipline, and the will to keep going.\""
        };
    }

    public static String[] buildPrincipalOfficeNarration() {
        return new String[] {
                "The doors of the Principal's Office stand tall and unyielding as you approach.",
                "Golden light spills across the marble floor while the academy crest gleams overhead.",
                "A secretary stops you before the inner chamber. \"Before seeing Principal Nemeesha, your progress must be verified.\"",
                "The room falls quiet, as if even the walls are waiting to judge whether you are ready for what comes next."
        };
    }

    public static String[] buildArea1EligibilityNarration() {
        return new String[] {
                "Principal Nemeesha Brightwell nods as you step forward.",
                "\"You have shown promise,\" she says. \"The Forest of Reverie will now open to you.\"",
                "\"Do not underestimate what waits there. Even the gentlest woods may hide fangs.\""
        };
    }

    public static String[] buildArea2EligibilityNarration() {
        return new String[] {
                "Principal Nemeesha studies you with a steadier, more serious gaze than before.",
                "\"Impressive progress. You have earned passage into Reverie's Edge.\"",
                "\"It is a place that tests patience as much as strength. Keep your focus, or it will swallow you.\""
        };
    }

    public static String[] buildArea3EligibilityNarration() {
        return new String[] {
                "The principal's expression turns solemn as you approach her desk.",
                "\"Few reach this point,\" she says quietly. \"The Forsaken Lands now await you.\"",
                "\"Beyond those gates lie trials unlike any you have faced. Walk forward with courage and wisdom.\""
        };
    }

    public static String[] buildShopNarration() {
        return new String[] {
                "Void appears beside the shop door, calm against the academy's hush.",
                "\"This is the supply shop,\" the spirit says. \"Weapons, potions, and the tools students depend on to survive.\"",
                "\"Spend with intention. Not everything you need will be offered twice.\""
        };
    }

    public static String[] buildShopConversationNarration() {
        return new String[] {
                "You push open the creaking door, and the scent of herbs and aged wood fills the air.",
                "A small bell jingles. Behind the counter, the shopkeeper peers over his spectacles.",
                "\"Welcome to Mystic Curiosities,\" he says. \"I'm Kabang Cobbleton. Handle the items wisely. They all carry stories.\""
        };
    }

    public static String[] buildInventoryNarration() {
        return new String[] {
                "Void emerges without a sound, its form quiet and steady beside you.",
                "\"All that you carry tells a story,\" it says. \"Weapons, potions, relics, and the choices you've made so far.\"",
                "\"Your space is not endless. What you keep reflects what you value. Choose wisely.\""
        };
    }

    public static String[] buildArea1Narration() {
        return new String[] {
                "The trees of the Forest of Reverie rise around you, ancient and watchful.",
                "Void drifts into view. \"This is the inner forest, where your first true trials begin.\"",
                "The spirit fades, leaving you alone with the rustle of leaves and the uneasy sense that the forest is already studying you."
        };
    }

    public static String[] buildArea2Narration() {
        return new String[] {
                "The air thickens as you step into Reverie's Edge, where the ground sours and the silence feels wrong.",
                "Void flickers beside you. \"This region is harsher, crueler, and home to stronger entities. Stay sharp.\"",
                "When the spirit vanishes, only the mist remains, curling around your path like a warning."
        };
    }

    public static String[] buildArea3Narration() {
        return new String[] {
                "Stone ruins and jagged towers stretch across the Forsaken Lands, vast and unnervingly alive.",
                "Void appears with an unreadable expression. \"This is the outer region, where the strongest entities gather.\"",
                "\"At the end of this land waits the heart of your trial. Never let your guard down.\""
        };
    }

    public static String[] buildSacrificeEndingNarration() {
        return new String[] {
                "The silence after Kim Morvain's fall feels almost impossible, as if the academy itself has forgotten how to breathe.",
                "You make your choice knowing the cost. The path home will open only if something of you is left behind.",
                "Light gathers, the curse loosens, and Mystvale fades at the edges. Your journey ends with sacrifice, but also with freedom."
        };
    }

    public static String[] buildLoopEndingNarration() {
        return new String[] {
                "For a heartbeat, everything seems still. Then the world bends.",
                "Corridors shift, shadows stretch, and the academy reforms around you like a memory refusing to end.",
                "The cycle begins again. Mystvale remains, waiting for you to walk its halls once more."
        };
    }

}
