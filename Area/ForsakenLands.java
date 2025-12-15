package Area;
import BattleMechanics.*;
import Boss.*;
import DesignRelated.*;
import Hero.*;
import Mobs.*;
import Narration.*;

import java.text.DecimalFormat;
import java.util.*;

public class ForsakenLands {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
    DecimalFormat df = new DecimalFormat("#,##0");
    MobBattleMechanic mobBattle = new MobBattleMechanic();
    EliteBattleMechanic eliteBattle = new EliteBattleMechanic();
    
    Stats reward = new Stats();
    boolean retreat = false;
    boolean explore = true;
    boolean exit = false;
    int goldGained, expGained;
    int currentArea = 0;

    Narration narrationPrinter = new Narration();
    Narration separatorHandler =  new Narration();
    EndingPlot endingplotHandler = new EndingPlot();

    public void enter(Hero hero) {
        // hero.setLevel(59); // for testing
        // hero.levelUp(101); // for testing
        System.out.println();
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│     You have entered the Forsaken Lands.   │");
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
        char choice;

        while(true){
            try {
                System.out.print("\nDo you wish to explore the Forsaken Lands? (y/n): ");
                String input = scan.next().trim().toLowerCase();

                if (input.isEmpty()) {
                    System.out.println("No input detected. Please enter 'y' or 'n'.");
                    continue;
                }

                choice = input.charAt(0);
                System.out.println();

                if (choice == 'y') {

                    System.out.println("Press ENTER to continue...");
                    scan.nextLine();
                    scan.nextLine();

                    System.out.println();
                    System.out.println();
                    System.out.println();

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
                }

            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scan.nextLine(); // clears broken input
            }
        }
    }

    public void exploreOutsideArea(Hero hero) {
        System.out.println("┌────────────────────────────────────────────────────────────┐");
        System.out.println("│ ===============               FORSAKEN LANDS               │");
        System.out.println("│ ||||||   ||||||                                            │");
        System.out.println("│ ||||||   ||||||                                DANGER!     │");
        System.out.println("│ ||||||===||||||   - - O - - - - - - O - - - - - - O        │");
        System.out.println("│ ||||||   ||||||      You                                   │");
        System.out.println("│ ||||||   ||||||                                            │");
        System.out.println("│ ===============                                            │");
        System.out.println("└────────────────────────────────────────────────────────────┘");


        if(explore){
            if(rand.nextBoolean()){
                System.out.println("A wandering husk of a fallen warrior approaches!");
                boolean heroWon = mobBattle.fight(hero, randomMob());
                if(MobBattleMechanic.run) {
                    MobBattleMechanic.run = false;
                } else if (heroWon) {
                    // reward here
                    goldGained = rand.nextInt(700, 750); // random gold between 700 and 750
                    expGained = rand.nextInt(165, 210); // random exp between 165 and 210
                    reward.rewards(hero, goldGained, expGained);
                } else {
                    System.out.println("You are rescued by fellow adventurers and taken back to the academy.");
                    currentArea = 0;
                    exit = true;
                    return;
                }
            } else {
                System.out.println("You walk among desolate plains, feeling unseen eyes upon you...");
            }
        }

        if(retreat){
            if(rand.nextBoolean()){
                System.out.println("Something crawls from the ashes to block your retreat!");

                System.out.println();
                System.out.println("Press ENTER to continue...");
                scan.nextLine();
                scan.nextLine();

                System.out.println();
                System.out.println();
                System.out.println();

                boolean heroWon = mobBattle.fight(hero, randomMob());
                if(MobBattleMechanic.run) {
                    MobBattleMechanic.run = false;
                } else if (heroWon) {
                    goldGained = rand.nextInt(700, 750); // random gold between 700 and 750
                    expGained = rand.nextInt(165, 210); // random exp between 165 and 210
                    reward.rewards(hero, goldGained, expGained);
                } else {
                    System.out.println("You are rescued by fellow adventurers and taken back to the academy.");
                    currentArea = 0;
                    exit = true;
                    return;
                }
            } else {
                System.out.println("You turn back and leave the lifeless wasteland behind.");
            }
        }

        while(true){
            char choice;

            try {
                System.out.print("\n\nDo you want to venture deeper? (y/n): ");
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
                    System.out.println("\nYou decide to return to safer grounds.");
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
        System.out.println("┌────────────────────────────────────────────────────────────┐");
        System.out.println("│ =========  =========             FORSAKEN LANDS            │");
        System.out.println("│ |||   |||  |||   |||                                       │");
        System.out.println("│ |||   |||  |||   |||                              DANGER!  │");
        System.out.println("│ |||===|||  |||===||| - - O - - - - - - O - - - - - - O     │");
        System.out.println("│ |||   |||  |||   |||                  You                  │");
        System.out.println("│ |||   |||  |||   |||                                       │");
        System.out.println("│ =========  =========                                       │");
        System.out.println("└────────────────────────────────────────────────────────────┘");
        
        if(explore){
            if(rand.nextBoolean()){
                System.out.println("From the ruins, a twisted wraith rises and attacks!");

                System.out.println();
                System.out.println("Press ENTER to continue...");
                scan.nextLine();
                scan.nextLine();

                System.out.println();
                System.out.println();
                System.out.println();

                boolean heroWon = mobBattle.fight(hero, randomMob());
                if(MobBattleMechanic.run) {
                    MobBattleMechanic.run = false;
                } else if (heroWon) {
                    // reward here
                    goldGained = rand.nextInt(850, 900); // random gold between 850 and 900
                    expGained = rand.nextInt(200, 245); // random exp between 200 and 245
                    reward.rewards(hero, goldGained, expGained);
                } else {
                    System.out.println("You are rescued by fellow adventurers and taken back to the academy.");
                    currentArea = 0;
                    exit = true;
                    return;
                }
            } else {
                System.out.println("You step through the remains of a forgotten kingdom...");
            }
        }

        if(retreat){
            if(rand.nextBoolean()){
                System.out.println("A cursed spirit follows you as you retreat!");

                System.out.println();
                System.out.println("Press ENTER to continue...");
                scan.nextLine();
                scan.nextLine();

                System.out.println();
                System.out.println();
                System.out.println();

                boolean heroWon = mobBattle.fight(hero, randomMob());
                if(MobBattleMechanic.run) {
                    MobBattleMechanic.run = false;
                } else if (heroWon) {
                    // reward here
                    goldGained = rand.nextInt(850, 900); // random gold between 850 and 900
                    expGained = rand.nextInt(200, 245); // random exp between 200 and 245
                    reward.rewards(hero, goldGained, expGained);
                } else {
                    System.out.println("You are rescued by fellow adventurers and taken back to the academy.");
                    currentArea = 0;
                    exit = true;
                    return;
                }
            } else {
                System.out.println("You quietly leave the ruined city behind.");
            }
        }

        while(true){
            char choice;

            try {    
                System.out.print("\n\nDo you wish to proceed further into the darkness? (y/n): ");
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
                    System.out.println("\nYou decide to return to the wastelands.");
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
        System.out.println("┌─────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│ =========  =========                FORSAKEN LANDS            =========     │");
        System.out.println("│ |||   |||  |||   |||                                          |||   |||     │");
        System.out.println("│ |||   |||  |||   |||                                DANGER!   |||   |||     │");
        System.out.println("│ |||===|||  |||===|||   - - O - - - - - - O - - - - - - O      |||===|||     │");
        System.out.println("│ |||   |||  |||   |||                                  You     |||   |||     │");
        System.out.println("│ |||   |||  |||   |||                                          |||   |||     │");
        System.out.println("│ =========  =========                                          =========     │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────────┘");

        if(explore){
            if(rand.nextBoolean()){
                System.out.println("The air grows thick... the ground trembles beneath your feet...");

                System.out.println();
                System.out.println("Press ENTER to continue...");
                scan.nextLine();
                scan.nextLine();

                System.out.println();
                System.out.println();
                System.out.println();

                boolean heroWon = eliteBattle.fight(hero, new HollowKing());
                if(EliteBattleMechanic.run) {
                    EliteBattleMechanic.run = false;
                } else if (heroWon) {
                    // reward here
                    goldGained = rand.nextInt(1000, 1200); // random gold between 1000 and 1200
                    expGained = rand.nextInt(250, 300); // random exp between 250 and 300
                    reward.rewards(hero, goldGained, expGained);
                } else {
                    System.out.println("You are rescued by fellow adventurers and taken back to the academy.");
                    currentArea = 0;
                    exit = true;
                    return;
                }
            } else {
                System.out.println("You reach the heart of desolation, where no life stirs...\n\n\n");
            }
        }

        String[] azraelIntro = {
            "You step into a vast crater filled with bones and ash...",
            "The wind whispers - 'All souls end here.'",
            "A towering figure descends from the crimson sky, its wings tattered yet divine...",
            "A deep, echoing voice speaks: 'I am Azrael... the Harbinger of Ruin.'",
            "Darkness itself bends before his presence."
        };

        playSection(azraelIntro);
        System.out.println();
        System.out.println();
        System.out.println("┌────────────────────────────────────────────────┐");
        System.out.println("│        It's the Harbringer Ruin, Azrael!       │");
        System.out.println("└────────────────────────────────────────────────┘");
        System.out.println();
        System.out.println();

        while(true){
            char choice;

            try {
                System.out.print("\n\nDo you dare challenge Azrael, the Harbinger of Ruin? (y/n): ");
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
                    boolean heroWon = battle.fight(hero, new Azrael());
                    if(BattleMechanic.run) {
                        BattleMechanic.run = false;
                    } else if (heroWon) {
                        goldGained = (int) Math.round(5000 * rand.nextDouble(1.50, 1.60)); // random multiplier 1.50 - 1.59
                        expGained = (int) Math.round(700 * rand.nextDouble(1.20, 1.30)); // random multiplier 1.20 - 1.29
                        reward.rewards(hero, goldGained, expGained);
                        System.out.println();
                        System.out.println("Azrael falls... his wings crumble to dust, and the land grows silent once more.");
                        System.out.println();
                        System.out.println("You have conquered the Forsaken Lands.");
                        System.out.println();
                        System.out.println("");
                        boolean specialEnding = battle.fight(hero, new Kim());
                        if(BattleMechanic.run) {
                            BattleMechanic.run = false;
                        } else if (specialEnding) {
                            System.out.println();
                            System.out.println("With Kim Morvain defeated, a radiant light pierces the Forsaken Lands...");
                            System.out.println("Life begins to return, and hope is rekindled in your heart.");
                            System.out.println("You have unlocked the Special Ending: 'Rebirth of the Forsaken Lands'!");

                            separatorHandler.promptSeparator();
                            endingplotHandler.generalEndingPlot(hero);
                            // insert end game prompts here
                            System.out.println();
                        } else {
                            System.out.println();
                            System.out.println("Kim Morvain's shadow engulfs the land once more...");
                            System.out.println("Aetherion remains shrouded in darkness.");
                            System.out.println();
                            exit();
                            currentArea = 0; // automatically exits the FORSAKEN LANDS
                            exit = true;
                            break;
                        }
                        exit();
                        currentArea = 0; // automatically exits the FORSAKEN LANDS
                        exit = true;
                        break;

                    } else {
                        System.out.println("\n\nAzrael's divine scythe strikes true - darkness consumes you...");
                        System.out.println("You are rescued by fellow adventurers and taken back to the academy.");
                        exit();
                        currentArea = 0; // automatically exits the FORSAKEN LANDS
                        exit = true;
                        break;

                    }
                } else if (choice == 'n'){
                    System.out.println("\n\nYou feel the overwhelming dread and choose to flee before Azrael notices you.");
                    retreat = true;
                    explore = false;
                    currentArea = 2; 
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

    public void playSection(String[] section) {
        System.out.println("\nPress ENTER to continue...");
        scan.nextLine();

        for (int i = 0; i < section.length; i++) {
            narrationPrinter.printWithDelay(section[i], 10); 
            System.out.println("\n");
        }
        
        /*for (int i = 0; i < section.length; i++) {
            scan.nextLine();
            System.out.println(section[i]);
        }
            */
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
        System.out.println();
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│           <<< Location Exited >>>          │");
        System.out.println("│       You have left Forsaken Lands.        │");
        System.out.println("└────────────────────────────────────────────┘");
        System.out.println();
        System.out.println();
    }

    
}
