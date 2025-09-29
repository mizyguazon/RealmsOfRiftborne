import java.util.Random;
import java.util.Scanner;

public class ForestOfReverie {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);

    public void enter() {
        System.out.println();
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│   You have entered the Forest of Reverie   │");
        System.out.println("└────────────────────────────────────────────┘");
        System.out.println();

        exploreEntry();
    }

    public void exploreEntry() {
        System.out.print("\nDo you want to explore the Forest of Reverie? (y/n): ");
        char choice = scan.next().toLowerCase().charAt(0);
        System.out.println();

        if (choice == 'y') {
            System.out.println("Exploring the mystical Forest of Reverie...");
            exploreOutsideArea();
        } else if (choice == 'n'){
            System.out.println("You chose not to explore the Forest of Reverie.");
            exit();
        } else {
            System.out.println("Invalid choice. Please enter 'y' or 'n'.");
            System.out.println();
            exploreEntry();
        }
    }

    public void exploreOutsideArea() {
        boolean valid = true;
        boolean explore = true;

        System.out.println();
        if(explore){
            if(rand.nextBoolean()){
                System.out.println("You are exploring the forest then suddenly a wild creature appears to attack!");
            } else {
                System.out.println("You are peacefully exploring the forest and noticed that the forest is calm for now.");
            }
        }
        System.out.println();

        while(valid){
            System.out.print("Do you want to continue exploring? (y/n): ");
            char choice = scan.next().toLowerCase().charAt(0);

            if (choice == 'y') {
                exploreMiddleArea();
                valid = false;
            } else if (choice == 'n'){
                System.out.println("\nYou chose to head back and return to the forest entrance.");
                valid = false;
                exploreEntry();
            } else {
                System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                System.out.println();
            }
        }
    }

    public void exploreMiddleArea() {
        boolean valid = true;
        boolean explore = true;

        System.out.println();
        if(explore){
            if(rand.nextBoolean()){
                System.out.println("You are exploring deeper into the forest when suddenly a wild creature appears to attack!");
            } else {
                System.out.println("You are peacefully exploring deeper into the forest and noticed that the forest is calm for now.");
            }
        }
        System.out.println();

        while(valid){
            System.out.print("Do you want to continue exploring? (y/n): ");
            char choice = scan.next().toLowerCase().charAt(0);

            if (choice == 'y') {
                exploreInnerArea();
                valid = false;
            } else if (choice == 'n'){
                System.out.println("\nYou chose to head back and return to the previous area.");
                valid = false;
                exploreOutsideArea();
            } else {
                System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                System.out.println();
            }
        }
    }

    public void exploreInnerArea() {
        boolean valid = true;
        boolean explore = true;

        System.out.println();
        if(explore){
            if(rand.nextBoolean()){
                System.out.println("You are exploring the innermost part of the forest \n\nwhen suddenly a wild ELITE type creature appears to attack!");
            } else {
                System.out.println("You are peacefully exploring the innermost part of the forest \n\nand noticed that the forest has clawed trees and dark atmosphere.");
            }
        }
        System.out.println();
        
        String[] elderthorn = {
            "You found something unusual in this area.",
            
            "You slowly approach it and found a tree with an eerie glow.",
            
            "As you touch the tree, you feel a surge of dark energy coursing through your body.",
            
            "Before you could react, the tree infront of you transforms into a menacing creature!",
            
            "It's the Forest Guardian, Elderthorn!",
        };

        // Array Iteration
        playSection(elderthorn);

        while(valid){
            System.out.print("Do you want to fight the Forest Guardian, Elderthorn? (y/n): ");
            char choice = scan.next().toLowerCase().charAt(0);

            if (choice == 'y') {
                System.out.println("You chose to fight the Forest Guardian, Elderthorn!");
                // Initiate battle sequence here
                valid = false; // Exit the loop after handling the choice
            } else if (choice == 'n'){
                System.out.println("\nYou chose to avoid the Forest Guardian, Elderthorn and head back to the previous area.");
                valid = false;
                exploreMiddleArea();
            } else {
                System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                System.out.println();
            }
        }
    }
    
    public static void playSection(String[] section) {
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
