package com.ror.engine.design;

import com.ror.models.*;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Stats {
    Scanner scanner = new Scanner(System.in);
    DecimalFormat df = new DecimalFormat("#,##0");

    public void sackOfGoldAfterTraining(Hero hero, int gold, int xp) {

        int totalGold = hero.getGold() + gold;
        hero.setGold(totalGold);
        
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
        System.out.printf("           >>> %-15s : +%6s%n", "Gold Earned", df.format(gold));
        System.out.printf("           >>> %-15s : %6s%n", "Total Gold", df.format(totalGold));
        System.out.printf("           >>> %-15s : +%6s%n", "Experience Earned", df.format(xp));
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
        System.out.println("                    ░░░░ | Press ENTER to continue... |");
        scanner.nextLine();
        hero.levelUp(xp);
        scanner.nextLine();
        

    }
    

    public void rewards(Hero hero, int gold, int xp) {

        int totalGold = hero.getGold() + gold;
        hero.setGold(totalGold);
       
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
        System.out.printf("           >>> %-15s : +%6s%n", "Gold Earned", df.format(gold));
        System.out.printf("           >>> %-15s : %6s%n", "Total Gold", df.format(totalGold));
        System.out.printf("           >>> %-15s : +%6s%n", "Experience Earned", df.format(xp));
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
        
        scanner.nextLine();

        hero.levelUp(xp);

    }

}
