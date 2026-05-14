package com.ror.engine.menu;

import com.ror.engine.narration.*;
import com.ror.engine.design.*;

import java.util.Scanner;

public class StartMenu extends Narration { 
    
    static Scanner scan = new Scanner(System.in);
    static boolean hasVisitedPrologue = false;

    public boolean displayStartMenu() {
        IntroTitle introDisplayHander = new IntroTitle();
        MenuRelated startMenuHandler = new MenuRelated();
        Narration separatorHandler = new Narration();
        IntroTitle loadGameHandler = new IntroTitle();
        
        introDisplayHander.mystvaleIntroTitle();

        loadGameHandler.loadGame();

        separatorHandler.promptSeparator();

        while (true) {

            startMenuHandler.startMenuRevised();
            System.out.print("-->| ");

            try {
                int startMenuChoice = Integer.parseInt(scan.nextLine());

                switch (startMenuChoice) {
                    case 1:

                        if (!hasVisitedPrologue) {
                            prologueNarration();
                            hasVisitedPrologue = true;
                        }

                        introDisplayHander.startGame();
                        
                        return true;

                    case 2:
                        
                        loadGameHandler.exitGame();
                        separatorHandler.promptSeparator();
                        loadGameHandler.mystvaleOutroTitle();
                        
                        return false;

                    default:
                        System.out.println();
                        System.out.println("┌─────────────────────────────────────┐");
                        System.out.println("│   Oops! Invalid choice. Try again.  │");
                        System.out.println("└─────────────────────────────────────┘");
                        separatorHandler.promptSeparatorResized();
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("┌──────────────────────────────────────────┐");
                System.out.println("│   Invalid input! Please enter a number.  │");
                System.out.println("└──────────────────────────────────────────┘");
                separatorHandler.promptSeparatorResized();
            } catch (Exception e) {
                System.out.println();
                System.out.println("┌──────────────────────────────────────────────┐");
                System.out.println("│   An unexpected error occurred. Try again.   │");
                System.out.println("└──────────────────────────────────────────────┘");
                separatorHandler.promptSeparatorResized();
                scan.nextLine(); 
            }
        }
    }
}

