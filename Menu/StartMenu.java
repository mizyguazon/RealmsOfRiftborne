package Menu;

import Narration.*;
import DesignRelated.*;

import java.util.Scanner;

public class StartMenu extends Narration { 
    
    static Scanner scan = new Scanner(System.in);
    static boolean hasVisitedPrologue = false;

    public boolean displayStartMenu() {
        IntroTitle introDisplayHander = new IntroTitle();
        //MainMenu menuHandler = new MainMenu();
        MenuRelated startMenuHandler = new MenuRelated();
        

        introDisplayHander.mystvaleIntroTitle();

        while (true) {

            startMenuHandler.startMenuRevised();
            //startMenuHandler.trainingMenu();
            System.out.print("-->| ");
            */

            startMenuHandler.startMenu();
            System.out.print("-->| ");

            try {
                int startMenuChoice = Integer.parseInt(scan.nextLine());

                switch (startMenuChoice) {
                    case 1:
                        introDisplayHander.startingTheGame();

                        if (!hasVisitedPrologue) {
                            prologueNarration();
                            hasVisitedPrologue = true;
                        }

                        return true;

                    case 2:
                        
                        introDisplayHander.exitingTheGame();
                        
                        return false;

                    default:
                        System.out.println();
                        System.out.println("┌─────────────────────────────────────┐");
                        System.out.println("│   Oops! Invalid choice. Try again.  │");
                        System.out.println("└─────────────────────────────────────┘");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("┌──────────────────────────────────────────┐");
                System.out.println("│   Invalid input! Please enter a number.  │");
                System.out.println("└──────────────────────────────────────────┘");
            } catch (Exception e) {
                System.out.println();
                System.out.println("┌──────────────────────────────────────────────┐");
                System.out.println("│   An unexpected error occurred. Try again.   │");
                System.out.println("└──────────────────────────────────────────────┘");
                scan.nextLine(); 
            }
        }
    }
}

