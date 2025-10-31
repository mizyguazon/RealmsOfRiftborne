package Area;
import BattleMechanics.*;
import Boss.*;
import Hero.*;
import Mobs.*;
import java.text.DecimalFormat;
import java.util.*;

public class ReveriesEdge {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
    DecimalFormat df = new DecimalFormat("#,##0");
    MobBattleMechanic mobBattle = new MobBattleMechanic();
    EliteBattleMechanic eliteBattle = new EliteBattleMechanic();
    boolean retreat = false;
    boolean explore = true;
    boolean exit = false;
    int currentArea = 0;
    
    public void enter(Hero hero) {
        hero.setLevel(35); // for testing
        hero.levelUp(101); // for testing
        System.out.println();
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│        You have entered Reverie's Edge     │");
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
        while(true){
            System.out.print("\nDo you want to explore Reverie's Edge? (y/n): ");
            char choice = scan.next().toLowerCase().charAt(0);
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
        }
    }

    public void exploreOuterArea(Hero hero) {
        System.out.println("┌───────────────────────────────────────────────────┐");
        System.out.println("│|||||              REVERIE'S EDGE                 │");
        System.out.println("│/////                                               │");
        System.out.println("│////        The trees grow darker here...          │");
        System.out.println("│|||\\\\   - - O - - - - - - O - - - - - - O          │");
        System.out.println("│||| \\\\         You                                 │");
        System.out.println("└───────────────────────────────────────────────────┘");

        System.out.println();

        if(explore){
            if(rand.nextBoolean()){
                System.out.println("A corrupted beast emerges from the shadows!");
                mobBattle.fight(hero, randomMob());
            } else {
                System.out.println("You hear whispers carried by the wind, but nothing appears.");
            }
        }

        if(retreat){
            if(rand.nextBoolean()){
                System.out.println("You try to leave, but a lurking shade attacks!");
                mobBattle.fight(hero, randomMob());
            } else {
                System.out.println("You safely return to the forest boundary.");
            }
        }

        System.out.println();

        while(true){
            System.out.print("Do you want to continue exploring deeper? (y/n): ");
            char choice = scan.next().toLowerCase().charAt(0);

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
                System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                System.out.println();
            }
        }
    }

    public void exploreMiddleArea(Hero hero) {
        System.out.println("┌──────────────────────────────────────────────────────────┐");
        System.out.println("│      REVERIE'S EDGE - The Borderlands                    │");
        System.out.println("│   The air feels heavy; faint lights flicker in the fog.  │");
        System.out.println("│  - - O - - - - - - O - - - - - - O                      │");
        System.out.println("│                   You                                   │");
        System.out.println("└──────────────────────────────────────────────────────────┘");

        System.out.println();
        
        if(explore){
            if(rand.nextBoolean()){
                System.out.println("A shadowy creature blocks your path!");
                mobBattle.fight(hero, randomMob());
            } else {
                System.out.println("You walk through the dim mist, hearing distant growls...");
            }
        }

        if(retreat){
            if(rand.nextBoolean()){
                System.out.println("Something follows you as you retreat, preparing to strike!");
                mobBattle.fight(hero, randomMob());
            } else {
                System.out.println("You carefully retrace your steps to the outer edge.");
            }
        }

        System.out.println();

        while(true){
            System.out.print("Do you want to go deeper into Reverie's Edge? (y/n): ");
            char choice = scan.next().toLowerCase().charAt(0);

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
                System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                System.out.println();
            }
        }
    }

    public void exploreInnerArea(Hero hero) {
        int goldGained, expGained;
        BattleMechanic battle = new BattleMechanic();

        System.out.println();
        if(explore){
            if(rand.nextBoolean()){
                System.out.println("You walk through an eerie clearing... and the ground trembles!");
                eliteBattle.fight(hero, new FadingWarden());
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
            "Morgrath, the Shadow Warden, stands before you!"
        };

        playSection(morgrath);

        System.out.println("┌────────────────────────────────────────────────┐");
        System.out.println("│          Boss Encounter: Morgrath              │");
        System.out.println("└────────────────────────────────────────────────┘");
        System.out.println();

        while(true){
            System.out.print("Do you wish to challenge Morgrath, the Shadow Warden? (y/n): ");
            char choice = scan.next().toLowerCase().charAt(0);

            if (choice == 'y') {
                System.out.println();
                System.out.println("┌────────────────────┐");
                System.out.println("│       BATTLE       │");
                System.out.println("└────────────────────┘");

                explore = true;
                retreat = false;
                boolean heroWon = battle.fight(hero, new Morgrath());

                if(heroWon){
                    goldGained = (int) Math.round(2800 * rand.nextDouble(1.50, 1.60));
                    expGained = (int) Math.round(500 * rand.nextDouble(1.20, 1.30));

                    System.out.println("┌────────────────────────────────────────────────┐");
                    System.out.println("│                CONGRATULATIONS!!!              │");
                    System.out.println("│                                                │");
                    System.out.println("│    You have obtained:                          │");
                    System.out.println("│               Gold: " + df.format(goldGained) + "                      │");
                    System.out.println("│               Exp : " + df.format(expGained) + "                        │");
                    System.out.println("│                                                │");
                    System.out.println("└────────────────────────────────────────────────┘");

                    hero.levelUp(expGained);
                    System.out.println("The shadow fades... Reverie's Edge grows silent once again.");
                    System.out.println("You leave the cursed borderlands behind.");
                    System.out.println();
                } else {
                    System.out.println("Morgrath's power overwhelms you — darkness consumes your vision.");
                    System.out.println("You collapse, but are rescued and taken back to safety.");
                }
                currentArea = 0;
                exit = true;
                break;
            } else if (choice == 'n'){
                System.out.println("\nYou turn away from the dark presence and retreat cautiously.");
                retreat = true;
                explore = false;
                currentArea = 2; 
                break;
            } else {
                System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                System.out.println();
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
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│           <<< Location Exited >>>          │");
        System.out.println("│       You have left Reverie's Edge.        │");
        System.out.println("└────────────────────────────────────────────┘");
        System.out.println();
    }

    
}
