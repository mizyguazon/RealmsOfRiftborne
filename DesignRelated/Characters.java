package DesignRelated;

import Hero.*;
import Narration.*;

import java.util.Scanner;

public class Characters {

    Narration printdelayHandler = new Narration();

    static Scanner scanner = new Scanner(System.in);
    
    public void swordsmanCharacter() {
     
        System.out.println();
        System.out.println("     >>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");
        System.out.println();
        System.out.println("          ┌───────────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("          │   The winds whisper your choice... The path of the Swordsman is yours to walk.    │");
        System.out.println("          └───────────────────────────────────────────────────────────────────────────────────┘");
        System.out.println();
        System.out.println("     >>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");

        String[] lines = {
            "",
            "  .         *             .                *           ░░░░░░░░░░░░",
            "      ┌────────────────────────────────────┐      .    ░░░███▓▓▓▓▓▓░░░",
            "      |   Player Character: Kael Solmere   |           ░░░▓▓▓███▓▓▓▓▓▓░░░",
            "      └────────────────────────────────────┘   *       ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░",
            "          .                    .                          ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░",
            "                      *                                      ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░",
            "                                                                ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░",
            "       .               .                  ░░░░░░░░░░░░             ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░         ░░░░░░",
            "              *                        ░░░▓▓▓▓▓▓███░░░        .       ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░   ░░░███░░░",
            "                                    ░░░▓▓▓▓▓▓███▓▓▓░░░                   ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░▓▓▓░░░",
            "                          *      ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░                      ░░░▓▓▓▓▓▓███▓▓▓░░░▓▓▓░░░",
            "    .                         ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░                    *       ░░░▓▓▓▓▓▓███░░░▓▓▓░░░",
            "               .           ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░              .                   ░░░░░░░░░███░░░",
            "                        ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░                                  ░░░▓▓▓▓▓▓▓▓▓░░░███░░░",
            "      ░░░░░░         ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░                                  ░░░███░░░░░░░░░   ░░░███░░░",
            "      ░░░███░░░   ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░           .       *                 ░░░░░░               ░░░███░░░",
            "         ░░░▓▓▓░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░                                                      .         ░░░███░░░░░░",
            "         ░░░▓▓▓░░░▓▓▓███▓▓▓▓▓▓░░░                                  .            *                *     ░░░███░░░",
            "   .     ░░░▓▓▓░░░███▓▓▓▓▓▓░░░          .               .                                              ░░░░░░░░░",
            "            ░░░███░░░░░░░░░                                                                                    ",
            "         ░░░███░░░▓▓▓▓▓▓▓▓▓░░░              .                           .              .                       ",
            "      ░░░███░░░   ░░░░░░░░░███░░░   *                       .         ┌──────────────────────────────┐    .    ",
            "░░░░░░███░░░     .      *  ░░░░░░                                     │   Press ENTER to continue    │            ",
            "░░░███░░░                                   .       *                 └──────────────────────────────┘            ",
            "░░░░░░░░░                .                                                                        .                ",
            ""
        };

        for (int i = 0; i < lines.length; i++) {  
            printdelayHandler.printLineWithDelay(lines[i], 25); 
        }

        scanner.nextLine();

        /*System.out.println();
        System.out.println("  .         *             .                *           ░░░░░░░░░░░░");
        System.out.println("      ┌────────────────────────────────────┐      .    ░░░███▓▓▓▓ ▓▓░░░");
        System.out.println("      |   Player Character: Kael Solmere   |           ░░░▓▓▓███▓▓▓▓▓▓░░░");
        System.out.println("      └────────────────────────────────────┘   *       ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░");
        System.out.println("          .                    .                          ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░");
        System.out.println("                      *                                      ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░");
        System.out.println("                                                                ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░");
        System.out.println("       .               .                  ░░░░░░░░░░░░             ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░         ░░░░░░");
        System.out.println("              *                        ░░░▓▓▓▓▓▓███░░░        .       ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░   ░░░███░░░");
        System.out.println("                                    ░░░▓▓▓▓▓▓███▓▓▓░░░                   ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░▓▓▓░░░");
        System.out.println("                          *      ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░                      ░░░▓▓▓▓▓▓███▓▓▓░░░▓▓▓░░░");
        System.out.println("    .                         ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░                    *       ░░░▓▓▓▓▓▓███░░░▓▓▓░░░");
        System.out.println("               .           ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░              .                   ░░░░░░░░░███░░░");
        System.out.println("                        ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░                                  ░░░▓▓▓▓▓▓▓▓▓░░░███░░░");
        System.out.println("      ░░░░░░         ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░                                  ░░░███░░░░░░░░░   ░░░███░░░");
        System.out.println("      ░░░███░░░   ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░           .       *                 ░░░░░░               ░░░███░░░"); 
        System.out.println("         ░░░▓▓▓░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░                                                      .         ░░░███░░░░░░");
        System.out.println("         ░░░▓▓▓░░░▓▓▓███▓▓▓▓▓▓░░░                                  .            *                *     ░░░███░░░");
        System.out.println("   .     ░░░▓▓▓░░░███▓▓▓▓▓▓░░░          .               .                                              ░░░░░░░░░");
        System.out.println("            ░░░███░░░░░░░░░                                                                                    ");
        System.out.println("         ░░░███░░░▓▓▓▓▓▓▓▓▓░░░              .                           .              .                       ");
        System.out.println("      ░░░███░░░   ░░░░░░░░░███░░░   *                       .         ┌──────────────────────────────┐    .    ");
        System.out.println("░░░░░░███░░░     .      *  ░░░░░░                                     │   Press ENTER to continue    │            ");
        System.out.println("░░░███░░░                                   .       *                 └──────────────────────────────┘            ");
        System.out.println("░░░░░░░░░                .                                                                        .                ");
        System.out.println();
        scanner.nextLine();
        */

    }


    public void gunnerCharacter() {

        System.out.println();
        System.out.println("  >>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");
        System.out.println();
        System.out.println("        ┌────────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("        │   The echo of gunfire resounds through the void - you are the chosen Gunner.   │");
        System.out.println("        └────────────────────────────────────────────────────────────────────────────────┘");
        System.out.println();
        System.out.println("  >>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");
        System.out.println();

        String[] lines = {
            "    .            *             .         *          █░░░░░███░░░███░░░░░░░░░░░░░░░░░░░░░███░░░░░░",
            "      ┌────────────────────────────────────┐        █░░▓▓▓███▓▓▓███▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓███▓▓▓▓▓▓░░█",
            "  .   |   Player Character: Aria Caelith   |   .       ░▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░████████▓▓▓▓███▓▓▓░░░",
            "      └────────────────────────────────────┘           ░░░░░░░░░░░░░░░░░█   ░░░░░░░░░░░░░░░░░░░░░",
            "                                                                            ░░░  ███ ░▓▓▓▓▓░░░",
            "               *        .             .                              *      ░░░      ░▓▓▓▓▓░░░",
            "        .                                                                █░░░░░░░░░░░░░░░▓▓▓▓▓░░░",
            "                                                *                                    ░▓▓▓▓▓▓▓▓░░░",
            "                             *                                *                      ░▓▓▓▓▓▓▓▓░░░",
            "                   .                                 .                                  ░▓▓▓▓▓▓▓▓░░░",
            "       *                         .                                       .      *       ░▓▓▓▓▓▓▓▓░░░",
            "                                             ░░░                *                       ░░░░░░░░░░░░",
            "      ██████░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░      .                                                 ",
            "   ░░████▓▓▓██████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░                                 *                      ",
            "   ░░████░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░                                                        ",
            "   ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░                    .                         .             ",
            "      ░░░▓▓▓███░░█      █░░                                                                           *         ",
            "   ░░░▓▓▓███▓▓▓░░░░░░░░░         *                     ┌──────────────────────────────┐                        ",
            "░░░▓▓▓███▓▓▓░░░                                    .   │   Press ENTER to continue    │                     ",
            "░░░███▓▓▓░░░   *                           *           └──────────────────────────────┘                             ",
            "░░░░░░░░░                     .                                         .                      *                        ",
            ""
        };

        for (int i = 0; i < lines.length; i++) {  
            printdelayHandler.printLineWithDelay(lines[i], 25); 
        }

        scanner.nextLine();

        /*System.out.println();
        System.out.println("    .            *             .         *          █░░░░░███░░░███░░░░░░░░░░░░░░░░░░░░░███░░░░░░");
        System.out.println("      ┌────────────────────────────────────┐        █░░▓▓▓███▓▓▓███▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓███▓▓▓▓▓▓░░█");
        System.out.println("  .   |   Player Character: Aria Caelith   |   .       ░▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░████████▓▓▓▓███▓▓▓░░░");
        System.out.println("      └────────────────────────────────────┘           ░░░░░░░░░░░░░░░░░█   ░░░░░░░░░░░░░░░░░░░░░");
        System.out.println("                                                                            ░░░  ███ ░▓▓▓▓▓░░░");
        System.out.println("               *        .             .                              *      ░░░      ░▓▓▓▓▓░░░");
        System.out.println("        .                                                                █░░░░░░░░░░░░░░░▓▓▓▓▓░░░");
        System.out.println("                                                *                                    ░▓▓▓▓▓▓▓▓░░░");
        System.out.println("                             *                                *                      ░▓▓▓▓▓▓▓▓░░░");
        System.out.println("                   .                                 .                                  ░▓▓▓▓▓▓▓▓░░░");
        System.out.println("       *                         .                                       .      *       ░▓▓▓▓▓▓▓▓░░░");
        System.out.println("                                             ░░░                *                       ░░░░░░░░░░░░");
        System.out.println("      ██████░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░      .                                                 ");
        System.out.println("   ░░████▓▓▓██████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░                                 *                      ");
        System.out.println("   ░░████░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░                                                        ");
        System.out.println("   ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░                    .                         .             ");
        System.out.println("      ░░░▓▓▓███░░█      █░░                                                                           *         ");
        System.out.println("   ░░░▓▓▓███▓▓▓░░░░░░░░░         *                     ┌──────────────────────────────┐                        ");
        System.out.println("░░░▓▓▓███▓▓▓░░░                                    .   │   Press ENTER to continue    │                     ");
        System.out.println("░░░███▓▓▓░░░   *                           *           └──────────────────────────────┘                             ");
        System.out.println("░░░░░░░░░                     .                                         .                      *                        ");
        System.out.println();
        scanner.nextLine();
        */
    }

    public void mageCharacter() {
        // to be implemented
        System.out.println();
        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");
        System.out.println();
        System.out.println("      ┌────────────────────────────────────────────────────────┐");
        System.out.println("      │   The stars align, You are now the Mage of destiny.    │");
        System.out.println("      └────────────────────────────────────────────────────────┘");
        System.out.println();
        System.out.println(">>>>> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <<<<<");
        System.out.println();

        String[] lines = {
            "        ░░█    ┌────────────────────────────────────┐     █░       .        ",
            "      ░░░░█    |  Player Character: Selene Arclight |     █░░░░             ",
            "      ░░░░█    └────────────────────────────────────┘     █░░░░      *      ",
            "    ░░░░░░▓           *                                   ▓░░░░░░          *  ",
            "  ░░░░░░░░░░░                                     .     ░░░░░░░░░░░     .         ",
            "░░░░▓                 .           .        *                    ▓░░░░        ",
            "░░░▓▓  ▓█      *                                            ▓█  ▓▓░░░      ",
            "░░░░▓▓▓▓▓▓█                                               █▓▓▓▓▓▓░░░░       ",
            "░░░░▓  ▓█                                                   ▓█  ▓░░░░   .  ",
            "░░▓░░         .                      *     .      *             ░░▓░░       ",
            "  ░░░░█              .                                        █░░░░        ",
            "    ░░░░█                       .                           █░░░░             ",
            "      ░░░░█                                               █░░░░              ",
            "      ░░░░█                                               █░░░░       .      ",
            "   .    ░░█                  .               .    *       █░░               ",
            "        ░░░         *                                     ░░░                ",
            "         ░░░                    *                        ░░░    .   *            ",
            "  *      ░░░                                             ░░░              .     ",
            "         ░▓░                        .           *        ░▓░                  ",
            "    .     ░░                                             ░░                 ",
            "          ░░           .                                 ░░        .     *    ",
            "          ░░                  .                          ░░                  ",
            "  *       ░░░     ┌──────────────────────────────┐      ░░░    *            ",
            "      .   ░▓░     │   Press ENTER to continue    │      ░▓░                  ",
            "           ▓░     └──────────────────────────────┘      ░▓              . ",
            ""
        };

        for (int i = 0; i < lines.length; i++) {  
            printdelayHandler.printLineWithDelay(lines[i], 25); 
        }

        scanner.nextLine();


        /*System.out.println();
        System.out.println("        ░░█    ┌────────────────────────────────────┐     █░       .        ");
        System.out.println("      ░░░░█    |  Player Character: Selene Arclight |     █░░░░             ");
        System.out.println("      ░░░░█    └────────────────────────────────────┘     █░░░░      *      ");
        System.out.println("    ░░░░░░▓           *                                   ▓░░░░░░          *  ");
        System.out.println("  ░░░░░░░░░░░                                     .     ░░░░░░░░░░░     .         ");
        System.out.println("░░░░▓                 .           .        *                    ▓░░░░        ");
        System.out.println("░░░▓▓  ▓█      *                                            ▓█  ▓▓░░░      ");
        System.out.println("░░░░▓▓▓▓▓▓█                                               █▓▓▓▓▓▓░░░░       ");
        System.out.println("░░░░▓  ▓█                                                   ▓█  ▓░░░░   .  ");
        System.out.println("░░▓░░         .                      *     .      *             ░░▓░░       ");
        System.out.println("  ░░░░█              .                                        █░░░░        ");
        System.out.println("    ░░░░█                       .                           █░░░░             ");
        System.out.println("      ░░░░█                                               █░░░░              ");
        System.out.println("      ░░░░█                                               █░░░░       .      ");
        System.out.println("   .    ░░█                  .               .    *       █░░               ");
        System.out.println("        ░░░         *                                     ░░░                ");
        System.out.println("         ░░░                    *                        ░░░    .   *            ");
        System.out.println("  *      ░░░                                             ░░░              .     ");
        System.out.println("         ░▓░                        .           *        ░▓░                  ");
        System.out.println("    .     ░░                                             ░░                 ");
        System.out.println("          ░░           .                                 ░░        .     *    ");
        System.out.println("          ░░                  .                          ░░                  ");
        System.out.println("  *       ░░░     ┌──────────────────────────────┐      ░░░    *            ");
        System.out.println("      .   ░▓░     │   Press ENTER to continue    │      ░▓░                  ");
        System.out.println("           ▓░     └──────────────────────────────┘      ░▓              . ");
        System.out.println();
        scanner.nextLine();
        */

    }

    public void swordsmanCharacterStats(Hero hero) { // implemented with print delay 
        String[] lines = {
            "",
            " .      *   ┌────────────────────────────────────────────────────┐",
            "      .     |   Player Character: Kael Solmere - Current Stats   |",
            " *          └────────────────────────────────────────────────────┘",
            "░░░░░░░░░░░░> Character Level:   .    " + hero.getLevel() + " .   *   .  .   *  ",
            "░░░███▓▓▓▓▓▓░░░> Current Experience:  " + hero.getExperience() + " .   *    .   *   ",
            "░░░▓▓▓███▓▓▓▓▓▓░░░  *     .         > Health:   " + hero.getBaseHp() + " .    . ",
            "░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░          *    > Attack:   " + hero.getAttack() + ".    .   * ",
            "  ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░    .     .  > Mana:     " + hero.getMana() + " *   .   .   *",
            "     ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░          > Defense:  " + hero.getDefense() + " .   *  .  *",
            "  .     ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░   *   > Speed:    " + hero.getSpeed(),
            "   *        ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░         ░░░░░░  .          .    ",
            "               ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░   ░░░███░░░   ",
            "      .           ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░▓▓▓░░░                     ",
            "                     ░░░▓▓▓▓▓▓███▓▓▓░░░▓▓▓░░░     .       .       ",
            "       *     .          ░░░▓▓▓▓▓▓███░░░▓▓▓░░░  *                  ",
            "                           ░░░░░░░░░███░░░    .       .           ",
            "    .             *     ░░░▓▓▓▓▓▓▓▓▓░░░███░░░              *      ",
            "                     ░░░███░░░░░░░░░   ░░░███░░░                  ",
            "      .      .       ░░░░░░               ░░░███░░░           .   ", 
            "                              .       *      ░░░███░░░░░░         ",
            "       *                                        ░░░███░░░         ",
            "                 .        *            .        ░░░░░░░░░    .    ",
            "    .                                                                                                        ",
            "┌──────────────────────────────┐",
            "│   Press ENTER to continue    │",
            "└──────────────────────────────┘"
        };

        for (int i = 0; i < lines.length; i++) {  
            printdelayHandler.printLineWithDelay(lines[i], 15); 
        }

        scanner.nextLine();
    }

    public void gunnerCharacterStats(Hero hero) { // implemented with print delay
        String[] lines = {
            "",
            "    .            *             .         *      ",
            "┌────────────────────────────────────────────────────┐    ",
            "|   Player Character: Aria Caelith - Current Stats   |   .",
            "└────────────────────────────────────────────────────┘    ",
            "  *    .       *        .             .        . ",
            "  * > Character Level:     " + hero.getLevel() + "   .       *     ",
            "  * > Current Experience:  " + hero.getExperience() + "   .      *   ",
            "       > Health:   " + hero.getHp() + "        .     *    .  ",
            "   .   > Attack:   " + hero.getAttack() + ".    .  .  *    .    *  ",
            "       > Mana:     " + hero.getMana() + " .      * .     .     *   .",
            "   .   > Defense:  " + hero.getDefense() + " .   .      .   .    *",
            "       > Speed:    " + hero.getSpeed() + "*        .        .       . ",
            "                                             ░░░",
            "      ██████░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░",
            "   ░░████▓▓▓██████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░",
            "   ░░████░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░",
            "   ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░",
            "      ░░░▓▓▓███░░█      █░░                     ",
            "   ░░░▓▓▓███▓▓▓░░░░░░░░░         *     .        ",
            "░░░▓▓▓███▓▓▓░░░              .                   ",
            "░░░███▓▓▓░░░   *                   .        *    ",
            "░░░░░░░░░          .           .             .    ",
            "",
            "┌──────────────────────────────┐",
            "│   Press ENTER to continue    │",
            "└──────────────────────────────┘"
        };

        for (int i = 0; i < lines.length; i++) {
            printdelayHandler.printLineWithDelay(lines[i], 15); 
        }

        scanner.nextLine(); 
    }

    public void mageCharacterStats(Hero hero) { // implemented with print delay
        String[] lines = {
            "",
            "",
            " .   *  ░░█    ┌────────────────────────────────────────────────────┐",
            "   .  ░░░░█    |  Player Character: Selene Arclight - Current Stats |",
            "      ░░░░█    └────────────────────────────────────────────────────┘",
            " *  ░░░░░░▓           *           .          .               .          ",
            "  ░░░░░░░░░░░                                     .                  ",
            "░░░░▓            > Character Level:     " + hero.getLevel() + "   .       *        .    ",
            "░░░▓▓  ▓█      *           .            *                  .           ",
            "░░░░▓▓▓▓▓▓█  .                    .                                    ",
            "░░░░▓  ▓█        > Current Experience:  " + hero.getExperience() + "   .       *        .   ",
            "░░▓░░         .                      *     .      *                  ",
            "  ░░░░█  .        *   .                                         .      ",
            "    ░░░░█          > Health:   " + hero.getHp() + "             *        .   ",
            "      ░░░░█           .                                    .           ",
            "      ░░░░█     .              *     .                                 ",
            "   .    ░░█        > Attack:   " + hero.getAttack() + "                   .  ",
            "        ░░░         *                                    .            ",
            "         ░░░                       .           *                    ",
            "  *      ░░░       > Mana:     " + hero.getMana() + "            *            ",
            "         ░▓░                        .           *                    ",
            "    .     ░░      .                                      .        .   ",
            "          ░░       > Defense:  " + hero.getDefense() + "            .    *   ",
            "          ░░                  .                                 *    ",
            "  *       ░░░                               .         .              ",
            "      .   ░▓░      > Speed:    " + hero.getSpeed() + "                   *  ",
            "           ▓░           *                   *                     .  ",
            "┌──────────────────────────────┐",
            "│   Press ENTER to continue    │",
            "└──────────────────────────────┘"
        };

        for (int i = 0; i < lines.length; i++) {
            printdelayHandler.printLineWithDelay(lines[i], 15); 
        }

        scanner.nextLine();
    }




    /*public void swordsmanCharacterStats(Hero hero) {
        // to be implemented
        System.out.println();
        System.out.println(" .      *   ┌────────────────────────────────────────────────────┐");
        System.out.println("      .     |   Player Character: Kael Solmere - Current Stats   |");
        System.out.println(" *          └────────────────────────────────────────────────────┘");
        System.out.println("░░░░░░░░░░░░> Character Level:   .    " + hero.getLevel() + " .   *   .  .   *  ");
        System.out.println("░░░███▓▓▓▓▓▓░░░> Current Experience:  " + hero.getExperience() + " .   *    .   *   ");
        System.out.println("░░░▓▓▓███▓▓▓▓▓▓░░░  *     .         > Health:   " + hero.getBaseHp() + " .    . ");
        System.out.println("░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░          *    > Attack:   " + hero.getAttack() + ".    .   * ");
        System.out.println("  ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░    .     .  > Mana:     " + hero.getMana() + " *   .   .   *");
        System.out.println("     ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░          > Defense:  " + hero.getDefense() + " .   *  .  *");
        System.out.println("  .     ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░   *   > Speed:    " + hero.getSpeed());
        System.out.println("   *        ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░         ░░░░░░  .          .    ");
        System.out.println("               ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░   ░░░███░░░   ");
        System.out.println("      .           ░░░▓▓▓▓▓▓███▓▓▓▓▓▓░░░▓▓▓░░░                     ");
        System.out.println("                     ░░░▓▓▓▓▓▓███▓▓▓░░░▓▓▓░░░     .       .       ");
        System.out.println("       *     .          ░░░▓▓▓▓▓▓███░░░▓▓▓░░░  *                  ");
        System.out.println("                           ░░░░░░░░░███░░░    .       .           ");
        System.out.println("    .             *     ░░░▓▓▓▓▓▓▓▓▓░░░███░░░              *      ");
        System.out.println("                     ░░░███░░░░░░░░░   ░░░███░░░                  ");
        System.out.println("      .      .       ░░░░░░               ░░░███░░░           .   "); 
        System.out.println("                              .       *      ░░░███░░░░░░         ");
        System.out.println("       *                                        ░░░███░░░         ");
        System.out.println("                 .        *            .        ░░░░░░░░░    .    ");
        System.out.println("    .                                                                                                        ");
        System.out.println("┌──────────────────────────────┐");
        System.out.println("│   Press ENTER to continue    │");
        System.out.println("└──────────────────────────────┘");
        scanner.nextLine();

    }
        */

    /*public void gunnerCharacterStats(Hero hero) {
        // to be implemented
        System.out.println();
        System.out.println("    .            *             .         *      ");
        System.out.println("┌────────────────────────────────────────────────────┐    ");
        System.out.println("|   Player Character: Aria Caelith - Current Stats   |   .");
        System.out.println("└────────────────────────────────────────────────────┘    ");
        System.out.println("  *    .       *        .             .        . ");
        System.out.println("  * > Character Level:     " + hero.getLevel() + "   .       *     ");
        System.out.println("  * > Current Experience:  " + hero.getExperience() + "   .      *   ");
        System.out.println("       > Health:   " + hero.getHp() + "        .     *    .  ");
        System.out.println("   .   > Attack:   " + hero.getAttack() + ".    .  .  *    .    *  ");
        System.out.println("       > Mana:     " + hero.getMana() + " .      * .     .     *   .");
        System.out.println("   .   > Defense:  " + hero.getDefense() + " .   .      .   .    *");
        System.out.println("       > Speed:    " + hero.getSpeed() + "*        .        .       . ");
        System.out.println("                                             ░░░");
        System.out.println("      ██████░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░");
        System.out.println("   ░░████▓▓▓██████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░");
        System.out.println("   ░░████░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░");
        System.out.println("   ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
        System.out.println("      ░░░▓▓▓███░░█      █░░                     ");
        System.out.println("   ░░░▓▓▓███▓▓▓░░░░░░░░░         *     .        ");
        System.out.println("░░░▓▓▓███▓▓▓░░░              .                   ");
        System.out.println("░░░███▓▓▓░░░   *                   .        *    ");
        System.out.println("░░░░░░░░░          .           .             .    ");
        System.out.println();
        System.out.println("┌──────────────────────────────┐");
        System.out.println("│   Press ENTER to continue    │");
        System.out.println("└──────────────────────────────┘");
        scanner.nextLine();

    }
        */

    /*public void mageCharacterStats(Hero hero) {
        // to be implemented
        System.out.println();
        System.out.println();
        System.out.println(" .   *  ░░█    ┌────────────────────────────────────────────────────┐");
        System.out.println("   .  ░░░░█    |  Player Character: Selene Arclight - Current Stats |");
        System.out.println("      ░░░░█    └────────────────────────────────────────────────────┘");
        System.out.println(" *  ░░░░░░▓           *           .          .               .          ");
        System.out.println("  ░░░░░░░░░░░                                     .                  ");
        System.out.println("░░░░▓            > Character Level:     " + hero.getLevel() + "   .       *        .    ");
        System.out.println("░░░▓▓  ▓█      *           .            *                  .           ");
        System.out.println("░░░░▓▓▓▓▓▓█  .                    .                                    ");
        System.out.println("░░░░▓  ▓█        > Current Experience:  " + hero.getExperience() + "   .       *        .   ");
        System.out.println("░░▓░░         .                      *     .      *                  ");
        System.out.println("  ░░░░█  .        *   .                                         .      ");
        System.out.println("    ░░░░█          > Health:   " + hero.getHp() + "             *        .   ");
        System.out.println("      ░░░░█           .                                    .           ");
        System.out.println("      ░░░░█     .              *     .                                 ");
        System.out.println("   .    ░░█        > Attack:   " + hero.getAttack() + "                   .  ");
        System.out.println("        ░░░         *                                    .            ");
        System.out.println("         ░░░                       .           *                    ");
        System.out.println("  *      ░░░       > Mana:     " + hero.getMana() + "            *            ");
        System.out.println("         ░▓░                        .           *                    ");
        System.out.println("    .     ░░      .                                      .        .   ");
        System.out.println("          ░░       > Defense:  " + hero.getDefense() + "            .    *   ");
        System.out.println("          ░░                  .                                 *    ");
        System.out.println("  *       ░░░                               .         .              ");
        System.out.println("      .   ░▓░      > Speed:    " + hero.getSpeed() + "                   *  ");
        System.out.println("           ▓░           *                   *                     .  ");
        System.out.println("┌──────────────────────────────┐");
        System.out.println("│   Press ENTER to continue    │");
        System.out.println("└──────────────────────────────┘");
        scanner.nextLine();


    }
        */
}
