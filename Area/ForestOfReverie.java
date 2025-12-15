package Area;
import BattleMechanics.*;
import Boss.*;
import DesignRelated.Stats;
import Hero.*;
import Mobs.*;
import Narration.*; 
import java.text.DecimalFormat;
import java.util.*;

public class ForestOfReverie {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
    DecimalFormat df = new DecimalFormat("#,##0");
    MobBattleMechanic mobBattle = new MobBattleMechanic();
    EliteBattleMechanic eliteBattle = new EliteBattleMechanic();   
    Narration narrationPrinter = new Narration();
    Stats reward = new Stats();
    boolean retreat = false;
    boolean explore = true;
    boolean exit = false;
    int goldGained, expGained;
    int currentArea = 0;

    public void enter(Hero hero) {
        
        System.out.println();
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│   You have entered the Forest of Reverie.  │");
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
                System.out.println("Unknown area! Exiting forest for safety...");
                exit = true;
            }
        }
        exit = false;
    }

    public void exploreEntry(Hero hero) {
        char choice;

        while (true) {
            try {
                System.out.print("\nDo you want to explore the Forest of Reverie? (y/n): ");
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

                    System.out.println("Exploring the mystical Forest of Reverie...");
                    retreat = false;
                    explore = true;
                    currentArea = 1;  // explore outside
                    break;

                } else if (choice == 'n') {
                    System.out.println("You chose not to explore the Forest of Reverie.");
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
        // This is my current currentArea = 1 
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│||||||            FOREST OF REVERIE              │");
        System.out.println("│/////                                            │");
        System.out.println("│////                                 DANGER!     │");
        System.out.println("│|||\\\\   - - O - - - - - - O - - - - - - O        │");
        System.out.println("│||| \\\\     You                                   │");
        System.out.println("│|||  \\\\                                          │");
        System.out.println("└─────────────────────────────────────────────────┘");

        System.out.println();

        if(explore){
            if(rand.nextBoolean()){
                System.out.println("You are exploring the forest then suddenly a wild creature appears to attack!");

                System.out.println();
                System.out.println("Press ENTER to continue...");
                scan.nextLine();
                //scan.nextLine();

                System.out.println();
                System.out.println();
                System.out.println();

                boolean heroWon = mobBattle.fight(hero, randomMob());
                if(MobBattleMechanic.run) {
                    MobBattleMechanic.run = false;
                } else if (heroWon) {
                    // reward here
                    goldGained = rand.nextInt(300, 400); // random gold between 300 and 400
                    expGained = rand.nextInt(75, 100); // random exp between 75 and 100
                    reward.rewards(hero, goldGained, expGained);
                } else {
                    System.out.println("You have sustained serious injuries, but fortunately, a group of students arrived in time to rescue you.");
                    currentArea = 0;
                    exit = true;
                    return;
                }
            } else {
                System.out.println("The forest feels calm and serene as you wander among the tall trees.");
            }
        }

        if(retreat){
            if(rand.nextBoolean()){
                System.out.println("You see the forest entrance ahead, but an angry mob blocks your way!");

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
                    goldGained = rand.nextInt(300, 400); // random gold between 300 and 400
                    expGained = rand.nextInt(75, 100); // random exp between 75 and 100
                    reward.rewards(hero, goldGained, expGained);
                } else {
                    System.out.println("You have sustained serious injuries, but fortunately, a group of students arrived in time to rescue you.");
                    currentArea = 0;
                    exit = true;
                    return;
                }
            } else {
                System.out.println("You reach the forest entrance without any problems.");
            }
        }

        while(true){
            char choice;

            try {
                System.out.print("\n\nDo you want to continue exploring? (y/n): ");
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
                    currentArea = 2; // explore middle area
                    break;

                } else if (choice == 'n'){
                    System.out.println("\nYou chose to head back towards the academy entrance.");
                    retreat = true;
                    explore = false;
                    currentArea = 0; // go back to explore entry
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
        System.out.println("┌───────────────────────────────────────────────────────────┐");
        System.out.println("│    /\\  /\\  /\\              FOREST OF REVERIE              │");
        System.out.println("│   /  \\/  \\/  \\                                            │");
        System.out.println("│  /_  _\\  /_  _\\                               DANGER!     │");
        System.out.println("│  / __ \\__/ __ \\  - - O - - - - - - O - - - - - - O        │");
        System.out.println("│  /_  _\\  /_  _\\                   You                     │");
        System.out.println("│    ||  ||  ||                                             │");
        System.out.println("└───────────────────────────────────────────────────────────┘");

        System.out.println();
        
        if(explore){
            if(rand.nextBoolean()){
                System.out.println("You are exploring deeper into the forest when suddenly a wild creature appears to attack!");

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
                    goldGained = rand.nextInt(450, 500); // random gold between 450 and 500
                    expGained = rand.nextInt(85, 115); // random exp between 85 and 115
                    reward.rewards(hero, goldGained, expGained);
                } else {
                    System.out.println("You have sustained serious injuries, but fortunately, a group of students arrived in time to rescue you.");
                    currentArea = 0;
                    exit = true;
                    return;
                }
            } else {
                System.out.println("You walk deeper into the forest, admiring its mystical beauty.");
            }
        }

        if(retreat){
            if(rand.nextBoolean()){
                System.out.println("As you head back, a lurking creature jumps from the shadows!");

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
                    goldGained = rand.nextInt(450, 500); // random gold between 450 and 500
                    expGained = rand.nextInt(85, 115); // random exp between 85 and 115
                    reward.rewards(hero, goldGained, expGained);
                } else {
                    System.out.println("You have sustained serious injuries, but fortunately, a group of students arrived in time to rescue you.");
                    currentArea = 0;
                    exit = true;
                    return;
                }
            } else {
                System.out.println("You return safely from the inner forest area.");
            }
        }

        while(true){
            char choice;

            try {
                System.out.print("\n\nDo you want to continue exploring? (y/n): ");
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
                    currentArea = 3; // explore inner area
                    break;

                } else if (choice == 'n'){
                    System.out.println("\nYou chose to head back to the previous area.");
                    retreat = true;
                    explore = false;
                    currentArea = 1; // go back to outside area
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
        // This is my current currentArea = 3
        BattleMechanic battle = new BattleMechanic();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("┌────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│    /\\  /\\  /\\              FOREST OF REVERIE   *  .  *   /\\* /\\ ./\\* . │");
        System.out.println("│   /  \\/  \\/  \\                                   * .  * /  \\/  \\/  \\  *│");
        System.out.println("│  /_  _\\  /_  _\\                               DANGER! */_  _\\  /_  _\\. │");
        System.out.println("│  / __ \\__/ __ \\  - - O - - - - - - O - - - - - - O  .  / __ \\__/ __ \\ *│");
        System.out.println("│  /_  _\\  /_  _\\                                 You   ./_  _\\  /_  _\\ .│");
        System.out.println("│    ||  ||  ||                                     .   *  ||* || .||  * │");
        System.out.println("└────────────────────────────────────────────────────────────────────────┘");
        System.out.println();
        System.out.println();
        System.out.println();

        if(explore){
            if(rand.nextBoolean()){
                System.out.println("You venture into the innermost part of the forest... \nSuddenly, a strong creature appears to attack!");

                System.out.println();
                System.out.println("Press ENTER to continue...");
                scan.nextLine();
                scan.nextLine();

                System.out.println();
                System.out.println();
                System.out.println();


                boolean heroWon = eliteBattle.fight(hero, new MudLurker());
                if(EliteBattleMechanic.run) {
                    EliteBattleMechanic.run = false;
                } else if (heroWon) {
                    // reward here
                    goldGained = rand.nextInt(700, 750); // random gold between 700 and 750
                    expGained = rand.nextInt(125, 150); // random exp between 125 and 150
                    reward.rewards(hero, goldGained, expGained);
                } else {
                    System.out.println("You have sustained serious injuries, but fortunately, a group of students arrived in time to rescue you.");
                    currentArea = 0; // automatically exits the FOREST OF REVERIE
                    exit = true;
                    return;
                }
            } else {
                System.out.println("You are peacefully exploring the innermost part of the forest \n\nand noticed that the forest has clawed trees and dark atmosphere.\n\n\n");
            }
        }
        
        String[] elderthorn = {
            "You found something unusual in this area.",
            "As you approach, you notice a tree glowing with a faint, eerie light.",
            "As you slowly approach the tree, you feel a surge of dark energy coursing through your body.",
            "Before you can react, the tree transforms into a menacing creature!",
        };

        // Array Iteration
        playSection(elderthorn);
    
        System.out.println();
        System.out.println();
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│      It's the Forest Guardian, Elderthorn!      │");
        System.out.println("└─────────────────────────────────────────────────┘");
        System.out.println();
        System.out.println();

        while(true){
            char choice;

            try {    
                System.out.print("\n\nDo you want to fight the Forest Guardian, Elderthorn? (y/n): ");
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
                    boolean heroWon = battle.fight(hero, new Elderthorn());
                    if(BattleMechanic.run) {
                        BattleMechanic.run = false;
                    } else if (heroWon) {
                        goldGained = (int) Math.round(2500 * rand.nextDouble(1.50, 1.60)); // random multiplier 1.50 - 1.59
                        expGained = (int) Math.round(450 * rand.nextDouble(1.20, 1.30)); // random multiplier 1.20 - 1.29
                        reward.rewards(hero, goldGained, expGained);
                        System.out.println("You have slain the enemy that guards the forest, every living being within the forest now fears you.");
                        System.out.println();

                        hero.setHaveDefeatedArea1Boss(true);
                        hero.unlockArea2(true);

                        System.out.println("You peacefully exit the Forest Of Reverie...");
                        System.out.println();
                        exit();

                        currentArea = 0; // automatically exits the FOREST OF REVERIE
                        exit = true;
                        break;

                    } else {
                        System.out.println("\n\nYou have sustained serious injuries, but fortunately, a group of students arrived in time to rescue you.");
                        exit();
                        currentArea = 0; // automatically exits the FOREST OF REVERIE
                        exit = true;
                        System.out.println();
                        break;

                    }
                } else if (choice == 'n'){
                    System.out.println("\n\nYou chose to avoid the Forest Guardian, Elderthorn and head back to the previous area.");
                    retreat = true;
                    explore = false;
                    currentArea = 2; // go back to middle area
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
        System.out.println("\n\nPress ENTER to continue...");
        scan.nextLine();
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
            return new Goblin();
        } else {
            return new Slime();
        } 
    }

    public void exit() {
        System.out.println();
        System.out.println();
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│           <<< Location Exited >>>          │");
        System.out.println("│       You have left Forest of Reverie.     │");
        System.out.println("└────────────────────────────────────────────┘");
        System.out.println();
        System.out.println();
        
    }
}