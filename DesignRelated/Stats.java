package DesignRelated;

import Hero.*;

public class Stats {

    public void sackOfGoldAfterTraining(Hero hero, int gold, int xp) {

        int gold = 2500; // for testing
       
        System.out.println("░░░░░█    ░░░░░░  ░░      ░█    ░░░░    ░░░░░█    ░░░░░█      ░░░░░█");
        System.out.println("░░    ░░  ░█      ░░  ░░  ░█  ░░    █░  ░░    ░░  ░░    ░░  ░█");
        System.out.println("░░░░░░    ░░░░    ░█  ░░  ░░  ░░░░░░░░  ░░░░░░    ░░    ░░    ░░░░");
        System.out.println("░░    ░█  ░█      ░█  ░░  ░░  ░█    ░░  ░█    ░░  ░░    ░█        ░░");
        System.out.println("░░    ░█  ░░░░░░    ░░  ░░    ░░    ░░  ░█    ░░  ░░░░░█    ░░░░░█") ;
        System.out.println();
        System.out.println("          ┌────────────────────────────────────────┐");
        System.out.println("          │        Here's a chest with gold        |");
        System.out.println("          │  May it help you conquer your battles  │");
        System.out.println("          └────────────────────────────────────────┘");
        System.out.printf("           >>> %-15s : +%6d%n", "Gold Earned", gold);
        System.out.printf("           >>> %-15s : %6d%n", "Total Gold", hero.getGold() + gold);
        System.out.println("                                                                   ");
        System.out.println("                            ░░░░░░░░  ");
        System.out.println("                        ░░░░▓▓▓▓▓▓▒▒░░░░");
        System.out.println("                    ░░░░▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓░░░░");
        System.out.println("                ░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▓▓░░");
        System.out.println("            ░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▓▓░░");
        System.out.println("        ░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓░░");
        System.out.println("    ░░░░▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▓▓░░");
        System.out.println("  ░░▓▓▓▓▓▓▓▓▓▓▓▓▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▓▓▒▒▒▒▒▒▓▓▓▓▓▓▓▓▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▒▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▒▒░░");
        System.out.println("░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▓▓▓▓░░");
        System.out.println("░░▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▒▒▒▒▒▒▒▒░░░░▒▒▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▓▓▓▓▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▒▒▒▒░░░██░░░▓▓▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▓▓▓▓▒▒▒▒▓▓▓▓▒▒▒▒▓▓▓▓▒▒▓▓▓▓▓▓░░░██░░░▒▒▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▓▓▓▓▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒░░░░▒▒▒▒▒▒▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▓▓▒▒▓▓▓▓▒▒▒▒▓▓▓▓▒▒▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▓▓▓▓▒▒▓▓▓▓▓▓▒▒▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▒▒▒▒▓▓▓▓▒▒▒▒▓▓▓▓▒▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓░░░░");
        System.out.println("  ░░▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▒▒▒▒▒▒▒▒▓▓▓▓░░░░");
        System.out.println("    ░░░░▓▓▓▓▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▒▒▒▒▓▓▓▓░░░░");
        System.out.println("        ░░░░▓▓▓▓▒▒▒▒▓▓▓▓▒▒▓▓▓▓▓▓░░░░");
        System.out.println("            ░░░░▓▓▓▓▓▓▓▓▓▓▓▓░░░░");
        System.out.println("                ░░░░▓▓▓▓░░░░");
        System.out.println("                    ░░░░");
        
        

    }
    

    public void rewards(Hero hero, int gold, int xp) {

        int gold = 5000; // for testing
        int xp = 5000; // for testing
       
        System.out.println("░░░░░█    ░░░░░░  ░░      ░█    ░░░░    ░░░░░█    ░░░░░█      ░░░░░█");
        System.out.println("░░    ░░  ░█      ░░  ░░  ░█  ░░    █░  ░░    ░░  ░░    ░░  ░█");
        System.out.println("░░░░░░    ░░░░    ░█  ░░  ░░  ░░░░░░░░  ░░░░░░    ░░    ░░    ░░░░");
        System.out.println("░░    ░█  ░█      ░█  ░░  ░░  ░█    ░░  ░█    ░░  ░░    ░█        ░░");
        System.out.println("░░    ░█  ░░░░░░    ░░  ░░    ░░    ░░  ░█    ░░  ░░░░░█    ░░░░░█") ;
        System.out.println();
        System.out.println("          ┌────────────────────────────────────────┐");
        System.out.println("          │        Here's a chest with gold        |");
        System.out.println("          │  May it help you conquer your battles  │");
        System.out.println("          └────────────────────────────────────────┘");
        System.out.printf("           >>> %-15s : +%6d%n", "Gold Earned", gold);
        System.out.printf("           >>> %-15s : %6d%n", "Total Gold", hero.getGold() + gold);
        System.out.printf("           >>> %-15s : +%6d%n", "Experience Earned", xp);
        System.out.println("                                                                   ");
        System.out.println("                            ░░░░░░░░  ");
        System.out.println("                        ░░░░▓▓▓▓▓▓▒▒░░░░");
        System.out.println("                    ░░░░▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓░░░░");
        System.out.println("                ░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▓▓░░");
        System.out.println("            ░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▓▓░░");
        System.out.println("        ░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓░░");
        System.out.println("    ░░░░▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▓▓░░");
        System.out.println("  ░░▓▓▓▓▓▓▓▓▓▓▓▓▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▓▓▒▒▒▒▒▒▓▓▓▓▓▓▓▓▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▒▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▒▒░░");
        System.out.println("░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▓▓▓▓░░");
        System.out.println("░░▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▒▒▒▒▒▒▒▒░░░░▒▒▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▓▓▓▓▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▒▒▒▒░░░██░░░▓▓▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▓▓▓▓▒▒▒▒▓▓▓▓▒▒▒▒▓▓▓▓▒▒▓▓▓▓▓▓░░░██░░░▒▒▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▓▓▓▓▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒░░░░▒▒▒▒▒▒▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▓▓▒▒▓▓▓▓▒▒▒▒▓▓▓▓▒▒▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▓▓▒▒▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▓▓▓▓▒▒▓▓▓▓▓▓▒▒▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓░░");
        System.out.println("░░▓▓▒▒▒▒▒▒▒▒▓▓▓▓▒▒▒▒▓▓▓▓▒▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓░░░░");
        System.out.println("  ░░▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▒▒▒▒▒▒▒▒▓▓▓▓░░░░");
        System.out.println("    ░░░░▓▓▓▓▒▒▒▒▒▒▒▒▓▓▓▓▒▒▓▓▒▒▒▒▓▓▓▓░░░░");
        System.out.println("        ░░░░▓▓▓▓▒▒▒▒▓▓▓▓▒▒▓▓▓▓▓▓░░░░");
        System.out.println("            ░░░░▓▓▓▓▓▓▓▓▓▓▓▓░░░░");
        System.out.println("                ░░░░▓▓▓▓░░░░");
        System.out.println("                    ░░░░");
        
        

    }

}
