package Area;
import BattleMechanics.*;
import Boss.*;
import DesignRelated.*;
import Hero.*;
import Mobs.*;
import Narration.*; // Javines | Plot 
import java.text.DecimalFormat;
import java.util.*;

public class ReveriesEdge {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
    DecimalFormat df = new DecimalFormat("#,##0");
    MobBattleMechanic mobBattle = new MobBattleMechanic();
    EliteBattleMechanic eliteBattle = new EliteBattleMechanic();
    GunnerPlot gunnerPlotHandler = new GunnerPlot(); // Javines | Plot
    Stats reward = new Stats();
    boolean retreat = false;
    boolean explore = true;
    boolean exit = false;
    int goldGained, expGained;
    int currentArea = 0;
    
    public void enter(Hero hero) {
        // hero.setLevel(45); // for testing
        // hero.levelUp(101); // for testing
        System.out.println();
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│       You have entered Reverie's Edge.     │");
        System.out.println("└────────────────────────────────────────────┘");

        while(!exit){    
            switch(currentArea){
                case 0:
                    exploreEntry(hero);
                    break;
                
                case 1:
                    exploreOuterArea(hero);
                    break;
                
                case 2:
                    exploreMiddleArea(hero);
                    break;
                
                case 3:
                    exploreInnerArea(hero);
                    break;

                default:
                    System.out.println("Unknown area! Retreating to safety...");
                    exit = true;
            }
        }
        exit = false;
    }

    public void exploreEntry(Hero hero) {
        char choice;

        while(true){
            try {
                System.out.print("\nDo you want to explore Reverie's Edge? (y/n): ");
                String input = scan.next().trim().toLowerCase();

                if (input.isEmpty()) {
                    System.out.println("No input detected. Please enter 'y' or 'n'.");
                    continue;
                }

                choice = input.charAt(0);
                System.out.println();

                if (choice == 'y') {
                    System.out.println("You step toward the dark borderlands of Reverie's Edge...");
                    retreat = false;
                    explore = true;
                    currentArea = 1;  
                    break;
                } else if (choice == 'n'){
                    System.out.println("You chose not to venture into Reverie's Edge.");
                    exit();
                    exit = true;
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scan.nextLine(); // clears broken input
            }
        }
    }

    public void exploreOuterArea(Hero hero) {
        System.out.println("┌────────────────────────────────────────────────────────────┐");
        System.out.println("│             *             REVERIES EDGE      *             │");
        System.out.println("│        *                                                   │");
        System.out.println("│  _______________                                  DANGER!  │");
        System.out.println("│  \\             /    - - O - - - - - - O - - - - - - O      │");
        System.out.println("│   \\           /        You               *                 │");
        System.out.println("│    \\         /                                             │");
        System.out.println("│     \\       /        *             +          *            │");
        System.out.println("│      \\     /                 +                             │");
        System.out.println("│       \\   /        *                       *               │");
        System.out.println("└────────────────────────────────────────────────────────────┘");

        if(explore){
            if(rand.nextBoolean()){
                System.out.println("A corrupted beast emerges from the shadows!");
                boolean heroWon = mobBattle.fight(hero, randomMob());
                if(MobBattleMechanic.run) {
                    MobBattleMechanic.run = false;
                } else if (heroWon) {
                    // reward here
                    goldGained = rand.nextInt(450, 500); // random gold between 450 and 500
                    expGained = rand.nextInt(95, 145); // random exp between 95 and 145
                    reward.rewards(hero, goldGained, expGained);
                } else {
                    System.out.println("You collapse, but are rescued and taken back to safety.");
                    currentArea = 0;
                    exit = true;
                    return;
                }
            } else {
                System.out.println("You hear whispers carried by the wind, but nothing appears.");
            }
        }

        if(retreat){
            if(rand.nextBoolean()){
                System.out.println("You try to leave, but a lurking shade attacks!");
                boolean heroWon = mobBattle.fight(hero, randomMob());
                if(MobBattleMechanic.run) {
                    MobBattleMechanic.run = false;
                } else if (heroWon) {
                    goldGained = rand.nextInt(450, 500); // random gold between 450 and 500
                    expGained = rand.nextInt(95, 145); // random exp between 95 and 145
                    reward.rewards(hero, goldGained, expGained);
                } else {
                    System.out.println("You collapse, but are rescued and taken back to safety.");
                    currentArea = 0;
                    exit = true;
                    return;
                }
            } else {
                System.out.println("You safely return to the forest boundary.");
            }
        }

        while(true){
            char choice;

            try {
                System.out.print("\n\nDo you want to continue exploring deeper? (y/n): ");
                String input = scan.next().trim().toLowerCase();

                if (input.isEmpty()) {
                    System.out.println("\nNo input detected. Please enter 'y' or 'n'.");
                    continue;
                }

                choice = input.charAt(0);
                System.out.println();

                if (choice == 'y') {
                    explore = true;
                    retreat = false;
                    currentArea = 2; 
                    break;

                } else if (choice == 'n'){
                    System.out.println("\nYou head back toward the previous safe area.");
                    retreat = true;
                    explore = false;
                    currentArea = 0;
                    break;

                } else {
                    System.out.println("\nInvalid choice. Please enter 'y' or 'n'.");
                }

            } catch (Exception e) {
                System.out.println("\nInvalid input. Please try again.");
                scan.nextLine(); // clears broken input
            }
        }
    }

    public void exploreMiddleArea(Hero hero) {
        System.out.println("┌───────────────────────────────────────────────────────────────────┐");
        System.out.println("│                          REVERIES EDGE            *          *    │");
        System.out.println("│                   *                                               │");
        System.out.println("│  _______________                                          DANGER! │");
        System.out.println("│  \\             /     *      - - O - - - - - - O - - - - - - O     │");
        System.out.println("│   \\           /                              You                  │");
        System.out.println("│    \\         /__________                                 *        │");
        System.out.println("│     \\       / \\        /                                          │");
        System.out.println("│      \\     /   \\      /        *        *           *             │");
        System.out.println("│       \\   /     \\    /                        *                   │");
        System.out.println("└───────────────────────────────────────────────────────────────────┘");
        
        if(explore){
            if(rand.nextBoolean()){
                System.out.println("A shadowy creature blocks your path!");
                boolean heroWon = mobBattle.fight(hero, randomMob());
                if(MobBattleMechanic.run) {
                    MobBattleMechanic.run = false;
                } else if (heroWon) {
                    goldGained = rand.nextInt(625, 700); // random gold between 625 and 700
                    expGained = rand.nextInt(120, 170); // random exp between 120 and 170
                    reward.rewards(hero, goldGained, expGained);
                } else {
                    System.out.println("You collapse, but are rescued and taken back to safety.");
                    currentArea = 0;
                    exit = true;
                    return;
                }
            } else {
                System.out.println("You walk through the dim mist, hearing distant growls...");
            }
        }

        if(retreat){
            if(rand.nextBoolean()){
                System.out.println("Something follows you as you retreat, preparing to strike!");
                boolean heroWon = mobBattle.fight(hero, randomMob());
                if(MobBattleMechanic.run) {
                    MobBattleMechanic.run = false;
                } else if (heroWon) {
                    goldGained = rand.nextInt(625, 700); // random gold between 625 and 700
                    expGained = rand.nextInt(120, 170); // random exp between 120 and 170
                    reward.rewards(hero, goldGained, expGained);
                } else {
                    System.out.println("You collapse, but are rescued and taken back to safety.");
                    currentArea = 0;
                    exit = true;
                    return;
                }
            } else {
                System.out.println("You carefully retrace your steps to the outer edge.");
            }
        }

        while(true){
            char choice;

            try {
                System.out.print("\n\nDo you want to go deeper into Reverie's Edge? (y/n): ");
                String input = scan.next().trim().toLowerCase();

                if (input.isEmpty()) {
                    System.out.println("\nNo input detected. Please enter 'y' or 'n'.");
                    continue;
                }

                choice = input.charAt(0);
                System.out.println();

                if (choice == 'y') {
                    explore = true;
                    retreat = false;
                    currentArea = 3; 
                    break;

                } else if (choice == 'n'){
                    System.out.println("\nYou decide to move back to the outer edge.");
                    retreat = true;
                    explore = false;
                    currentArea = 1; 
                    break;

                } else {
                    System.out.println("\nInvalid choice. Please enter 'y' or 'n'.");
                }

            } catch (Exception e) {
                System.out.println("\nInvalid input. Please try again.");
                scan.nextLine(); // clears broken input
            }
        }
    }

    public void exploreInnerArea(Hero hero) {
        BattleMechanic battle = new BattleMechanic();
        System.out.println("┌───────────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│                                    REVERIES EDGE                *                 │");
        System.out.println("│                      *                                                *       +   │");
        System.out.println("│  _______________                                    DANGER!     _______________   │");
        System.out.println("│  \\             /      - - O - - - - - - O - - - - - - O         \\             /   │");
        System.out.println("│   \\           /                                      You         \\           /    │");
        System.out.println("│    \\         /__________                         *                \\         /     │");
        System.out.println("│     \\       / \\        /        *                                  \\       /      │");
        System.out.println("│      \\     /   \\      /                          +        *         \\     /       │");
        System.out.println("│       \\   /     \\    /                 +                             \\   /        │");
        System.out.println("└───────────────────────────────────────────────────────────────────────────────────┘");

        if(explore){
            if(rand.nextBoolean()){
                System.out.println("You walk through an eerie clearing... and the ground trembles!");
                boolean heroWon = eliteBattle.fight(hero, new FadingWarden());
                if(EliteBattleMechanic.run) {
                    EliteBattleMechanic.run = false;
                } else if (heroWon) {
                    goldGained = rand.nextInt(650, 725); // random gold between 650 and 725
                    expGained = rand.nextInt(145, 180); // random exp between 145 and 180
                    reward.rewards(hero, goldGained, expGained);    
                } else {
                    System.out.println("You collapse, but are rescued and taken back to safety.");
                    currentArea = 0;
                    exit = true;
                    return;
                }
            } else {
                System.out.println("The place feels corrupted — twisted roots and ash cover the ground.");
            }
        }
        System.out.println();

        String[] morgrath = {
            "The mist thickens as an oppressive aura fills the air...",
            "Dark whispers echo around you, calling your name...",
            "A towering shadow emerges from the darkness, holding a blackened scythe.",
            "'So, another lost soul dares enter my dominion'",
            "Morgrath, the Swamp Warden, stands before you!"
        };

        playSection(morgrath);
        System.out.println();
        System.out.println();
        System.out.println("┌────────────────────────────────────────────────┐");
        System.out.println("│        It's the Swamp Warden, Morgrath!        │");
        System.out.println("└────────────────────────────────────────────────┘");
        System.out.println();
        System.out.println();

        while(true){
            char choice;

            try {
                System.out.print("\nDo you wish to challenge Morgrath, the Swamp Warden? (y/n): ");
                String input = scan.next().trim().toLowerCase();

                if (input.isEmpty()) {
                    System.out.println("No input detected. Please enter 'y' or 'n'.");
                    continue;
                }

                choice = input.charAt(0);
                System.out.println();

                if (choice == 'y') {
                    explore = true;
                    retreat = false;
                    boolean heroWon = battle.fight(hero, new Morgrath());
                    if(BattleMechanic.run) {
                        BattleMechanic.run = false;
                    } else if (heroWon) {
                        goldGained = (int) Math.round(4000 * rand.nextDouble(1.50, 1.60)); // random multiplier 1.50 - 1.59
                        expGained = (int) Math.round(600 * rand.nextDouble(1.20, 1.30)); // random multiplier 1.20 - 1.29
                        reward.rewards(hero, goldGained, expGained);
                        
                        System.out.println("The shadow fades... Reverie's Edge grows silent once again.");
                        System.out.println();
                        System.out.println("You leave the cursed borderlands behind.");
                        System.out.println();

                        hero.setHaveDefeatedArea2Boss(true);
                        hero.setUnlockArea2(true);

                        exit();
                        currentArea = 0; // automatically exits the REVERIE EDGE
                        exit = true;
                        break;
                    } else {
                        System.out.println("Morgrath's power overwhelms you — darkness consumes your vision.");
                        System.out.println("You collapse, but are rescued and taken back to safety.");
                        exit();
                        currentArea = 0; // automatically exits the REVERIE EDGE
                        exit = true;
                        break;
                    }
                } else if (choice == 'n'){
                    System.out.println("\nYou turn away from the dark presence and retreat cautiously.");
                    retreat = true;
                    explore = false;
                    currentArea = 2; 
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scan.nextLine(); // clears broken input
            }
        }
    }

    public static void playSection(String[] section) {
        System.out.println("\nPress ENTER to continue...");
        scan.nextLine();
        
        for (int i = 0; i < section.length; i++) {
            scan.nextLine();
            System.out.println(section[i]);
        }
        System.out.println(); 
    }
    
    public Entity randomMob(){
        boolean choice = rand.nextBoolean();

        if(choice){
            return new SwampRat();
        } else {
            return new VeilSerpent();
        } 
    }

    public void exit() {
        System.out.println();
        System.out.println();
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│           <<< Location Exited >>>          │");
        System.out.println("│         You have left Reveries Edge.       │");
        System.out.println("└────────────────────────────────────────────┘");
        System.out.println();
        System.out.println();
    }

    
}
