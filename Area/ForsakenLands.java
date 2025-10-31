package Area;
import BattleMechanics.*;
import Boss.*;
import Hero.*;
import Mobs.*;
import java.text.DecimalFormat;
import java.util.*;

public class ForsakenLands {
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
        System.out.println("│     You have entered the Forsaken Lands    │");
        System.out.println("└────────────────────────────────────────────┘");

        while(!exit){    
            switch(currentArea){
                case 0:
                    exploreEntry(hero);
                    break;
                
                case 1:
                    exploreOutsideArea(hero);
                    break;
                
                case 2:
                    exploreMiddleArea(hero);
                    break;
                
                case 3:
                    exploreInnerArea(hero);
                    break;

                default:
                    System.out.println("Unknown area detected... retreating immediately!");
                    exit = true;
            }
        }
        exit = false;
    }

    public void exploreEntry(Hero hero) {
        while(true){
            System.out.print("\nDo you wish to explore the Forsaken Lands? (y/n): ");
            char choice = scan.next().toLowerCase().charAt(0);
            System.out.println();

            if (choice == 'y') {
                System.out.println("You step into a realm where hope seems to have perished...");
                retreat = false;
                explore = true;
                currentArea = 1;  
                break;
            } else if (choice == 'n'){
                System.out.println("You chose not to wander into the Forsaken Lands.");
                exit();
                exit = true;
                break;
            } else {
                System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                System.out.println();
            }
        }
    }

    public void exploreOutsideArea(Hero hero) {
        System.out.println("┌────────────────────────────────────────────────────┐");
        System.out.println("│|||||              FORSAKEN LANDS                  │");
        System.out.println("│/////      The ground is cracked and lifeless.     │");
        System.out.println("│////     Echoes of sorrow fill the cold air...     │");
        System.out.println("│|||\\\\   - - O - - - - - - O - - - - - - O          │");
        System.out.println("│||| \\\\           You                               │");
        System.out.println("└────────────────────────────────────────────────────┘");

        System.out.println();

        if(explore){
            if(rand.nextBoolean()){
                System.out.println("A wandering husk of a fallen warrior approaches!");
                mobBattle.fight(hero, randomMob());
            } else {
                System.out.println("You walk among desolate plains, feeling unseen eyes upon you...");
            }
        }

        if(retreat){
            if(rand.nextBoolean()){
                System.out.println("Something crawls from the ashes to block your retreat!");
                mobBattle.fight(hero, randomMob());
            } else {
                System.out.println("You turn back and leave the lifeless wasteland behind.");
            }
        }

        System.out.println();

        while(true){
            System.out.print("Do you want to venture deeper? (y/n): ");
            char choice = scan.next().toLowerCase().charAt(0);

            if (choice == 'y') {
                explore = true;
                retreat = false;
                currentArea = 2; 
                break;
            } else if (choice == 'n'){
                System.out.println("\nYou decide to return to safer grounds.");
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
        System.out.println("┌────────────────────────────────────────────────────────┐");
        System.out.println("│       FORSAKEN LANDS - The Ruined City                 │");
        System.out.println("│   Collapsed towers and broken monuments surround you.  │");
        System.out.println("│  - - O - - - - - - O - - - - - - O                    │");
        System.out.println("│                 You                                   │");
        System.out.println("└────────────────────────────────────────────────────────┘");

        System.out.println();
        
        if(explore){
            if(rand.nextBoolean()){
                System.out.println("From the ruins, a twisted wraith rises and attacks!");
                mobBattle.fight(hero, randomMob());
            } else {
                System.out.println("You step through the remains of a forgotten kingdom...");
            }
        }

        if(retreat){
            if(rand.nextBoolean()){
                System.out.println("A cursed spirit follows you as you retreat!");
                mobBattle.fight(hero, randomMob());
            } else {
                System.out.println("You quietly leave the ruined city behind.");
            }
        }

        System.out.println();

        while(true){
            System.out.print("Do you wish to proceed further into the darkness? (y/n): ");
            char choice = scan.next().toLowerCase().charAt(0);

            if (choice == 'y') {
                explore = true;
                retreat = false;
                currentArea = 3; 
                break;
            } else if (choice == 'n'){
                System.out.println("\nYou decide to return to the wastelands.");
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
                System.out.println("The air grows thick... the ground trembles beneath your feet...");
                eliteBattle.fight(hero, new HollowKing());
            } else {
                System.out.println("You reach the heart of desolation, where no life stirs...");
            }
        }
        System.out.println();

        String[] azraelIntro = {
            "You step into a vast crater filled with bones and ash...",
            "The wind whispers — 'All souls end here.'",
            "A towering figure descends from the crimson sky, its wings tattered yet divine...",
            "A deep, echoing voice speaks: 'I am Azrael... the Harbinger of Ruin.'",
            "Darkness itself bends before his presence."
        };

        playSection(azraelIntro);

        System.out.println("┌────────────────────────────────────────────────┐");
        System.out.println("│          Boss Encounter: Azrael                │");
        System.out.println("└────────────────────────────────────────────────┘");
        System.out.println();

        while(true){
            System.out.print("Do you dare challenge Azrael, the Harbinger of Ruin? (y/n): ");
            char choice = scan.next().toLowerCase().charAt(0);

            if (choice == 'y') {
        
                explore = true;
                retreat = false;
                boolean heroWon = battle.fight(hero, new Azrael());

                if(heroWon){
                    goldGained = (int) Math.round(3200 * rand.nextDouble(1.50, 1.60));
                    expGained = (int) Math.round(600 * rand.nextDouble(1.20, 1.30));

                    System.out.println("┌────────────────────────────────────────────────┐");
                    System.out.println("│                CONGRATULATIONS!!!              │");
                    System.out.println("│                                                │");
                    System.out.println("│    You have obtained:                          │");
                    System.out.println("│               Gold: " + df.format(goldGained) + "                      │");
                    System.out.println("│               Exp : " + df.format(expGained) + "                        │");
                    System.out.println("│                                                │");
                    System.out.println("└────────────────────────────────────────────────┘");

                    hero.levelUp(expGained);
                    System.out.println("Azrael falls... his wings crumble to dust, and the land grows silent once more.");
                    System.out.println("You have conquered the Forsaken Lands.");
                    System.out.println();
                } else {
                    System.out.println("Azrael's divine scythe strikes true — darkness consumes you...");
                    System.out.println("You are rescued by fellow adventurers and taken back to the academy.");
                }
                currentArea = 0;
                exit = true;
                break;
            } else if (choice == 'n'){
                System.out.println("\nYou feel the overwhelming dread and choose to flee before Azrael notices you.");
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
            return new ShadowAbyss();
        } else  {
            return new VoidBeast();
        } 
    }

    public void exit() {
        System.out.println();
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│           <<< Location Exited >>>          │");
        System.out.println("│      You have left the Forsaken Lands.     │");
        System.out.println("└────────────────────────────────────────────┘");
        System.out.println();
    }

    
}
