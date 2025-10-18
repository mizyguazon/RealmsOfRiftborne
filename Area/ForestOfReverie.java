package Area;
import BattleMechanics.BattleMechanic;
import Hero.*;
import Boss.*;
import java.util.*;

public class ForestOfReverie {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
    boolean retreat = false;
    boolean explore = true;

    public void enter(Hero hero) {
        System.out.println();
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│   You have entered the Forest of Reverie   │");
        System.out.println("└────────────────────────────────────────────┘");

        exploreEntry(hero);
    }

    public void exploreEntry(Hero hero) {
        while(true){
            System.out.print("\nDo you want to explore the Forest of Reverie? (y/n): ");
            char choice = scan.next().toLowerCase().charAt(0);
            System.out.println();

            if (choice == 'y') {
                System.out.println("Exploring the mystical Forest of Reverie...");
                retreat = false;
                explore = true;
                exploreOutsideArea(hero);
                break;
            } else if (choice == 'n'){
                System.out.println("You chose not to explore the Forest of Reverie.");
                exit();
                return;
            } else {
                System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                System.out.println();
            }
        }
    }

    public void exploreOutsideArea(Hero hero) {
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
                
            } else {
                System.out.println("The forest feels calm and serene as you wander among the tall trees.");
            }
        }

        if(retreat){
            if(rand.nextBoolean()){
                System.out.println("You see the forest entrance ahead, but an angry mob blocks your way!");
            } else {
                System.out.println("You reach the forest entrance without any problems.");
            }
        }

        System.out.println();

        while(true){
            System.out.print("Do you want to continue exploring? (y/n): ");
            char choice = scan.next().toLowerCase().charAt(0);

            if (choice == 'y') {
                explore = true;
                retreat = false;
                exploreMiddleArea(hero);
                break;
            } else if (choice == 'n'){
                System.out.println("\nYou chose to head back towards the academy entrance.");
                retreat = true;
                explore = false;
                exploreEntry(hero);
                break;
            } else {
                System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                System.out.println();
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
            } else {
                System.out.println("You walk deeper into the forest, admiring its mystical beauty.");
            }
        }

        if(retreat){
            if(rand.nextBoolean()){
                System.out.println("As you head back, a lurking creature jumps from the shadows!");
            } else {
                System.out.println("You return safely from the inner forest area.");
            }
        }

        System.out.println();

        while(true){
            System.out.print("Do you want to continue exploring? (y/n): ");
            char choice = scan.next().toLowerCase().charAt(0);

            if (choice == 'y') {
                explore = true;
                retreat = false;
                exploreInnerArea(hero);
                break;
            } else if (choice == 'n'){
                System.out.println("\nYou chose to head back to the previous area.");
                retreat = true;
                explore = false;
                exploreOutsideArea(hero);
                break;
            } else {
                System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                System.out.println();
            }
        }
    }

    public void exploreInnerArea(Hero hero) {
        BattleMechanic battle = new BattleMechanic();

        System.out.println();
        if(explore){
            if(rand.nextBoolean()){
                System.out.println("You venture into the innermost part of the forest... \n\nSuddenly, a strong creature appears to attack!");
            } else {
                System.out.println("You are peacefully exploring the innermost part of the forest \n\nand noticed that the forest has clawed trees and dark atmosphere.");
            }
        }
        System.out.println();
        
        String[] elderthorn = {
            "\nYou found something unusual in this area.",
            
            "As you approach, you notice a tree glowing with a faint, eerie light.",
            
            "As you slowly approach the tree, you feel a surge of dark energy coursing through your body.",
            
            "Before you can react, the tree transforms into a menacing creature!",
            
        };

        // Array Iteration
        playSection(elderthorn);

        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│      It's the Forest Guardian, Elderthorn!      │");
        System.out.println("└─────────────────────────────────────────────────┘");
        System.out.println();

        while(true){
            System.out.print("Do you want to fight the Forest Guardian, Elderthorn? (y/n): ");
            char choice = scan.next().toLowerCase().charAt(0);

            if (choice == 'y') {
                System.out.println();
                System.out.println("┌────────────────────┐");
                System.out.println("│       BATTLE       │");
                System.out.println("└────────────────────┘");
                explore = true;
                retreat = false;
                if(battle.fight(hero, new Elderthorn())){
                    System.out.println("You have slain the enemy that guards the forest, every living being within the forest now fears you.");
                    System.out.println();
                    System.out.println("You peacefully exit the Forest Of Reverie...");
                    System.out.println();
                } else {
                    System.out.println("You have sustained serious injuries, but fortunately, a group of students arrived in time to rescue you.");
                }
                break;
            } else if (choice == 'n'){
                System.out.println("\nYou chose to avoid the Forest Guardian, Elderthorn and head back to the previous area.");
                retreat = true;
                explore = false;
                exploreMiddleArea(hero);
                break;
            } else {
                System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                System.out.println();
            }
        }
    }
    
    public static void playSection(String[] section) {
        scan.nextLine();
        System.out.println("Press ENTER to continue...");
        
        for (int i = 0; i < section.length; i++) {
            scan.nextLine();
            System.out.println(section[i]);
            
        }
        
        System.out.println(); 
    }

    public void exit() {
        System.out.println();
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│           <<< Location Exited >>>          │");
        System.out.println("│   You have exited the Forest of Reverie.   │");
        System.out.println("└────────────────────────────────────────────┘");
        System.out.println();

    }
}

