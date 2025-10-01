package Menu;

import Narration.Narration;
import java.util.Scanner;

public class CharacterMenu extends Narration{
    public void chooseCharacterMenu(){
        Scanner scanner = new Scanner(System.in);
        Menu menuHandler = new Menu();

        System.out.println("+------------------------------+");
        System.out.println("|      Choose a character      |");
        System.out.println("|------------------------------|");
        System.out.println("|  1. Swordsman                |");
        System.out.println("|  2. Gunner                   |");
        System.out.println("|  3. Mage                     |");
        System.out.println("+------------------------------+");
        System.out.print(">>> ");

        char characterChoice = scanner.next().charAt(0);

        switch(characterChoice){
            case '1':            
                choiceSwordsman();
                menuHandler.defaultMainMenu();
                break;

            case '2':
                choiceGunner();
                menuHandler.defaultMainMenu();
                break;

            case '3':    
                choiceMage();
                menuHandler.defaultMainMenu();
                break;

            default:
                System.out.println();
                System.out.println("┌─────────────────────────────┐");
                System.out.println("│  Invalid choice. Try again! │");
                System.out.println("└─────────────────────────────┘");
                chooseCharacterMenu();
        }
        scanner.close();    
    }
}
