package Menu;

import Hero.*;
import Narration.*;
import DesignRelated.*;

import java.util.Scanner;

public class CharacterMenu extends Narration{
    Scanner scanner = new Scanner(System.in);

    public Hero chooseCharacterMenu(){
        MenuRelated designRelatedMenu = new MenuRelated();
        InnerCharacterMenu innerCharacterMenuHandler = new InnerCharacterMenu();
        Characters characterPrompts = new Characters();

        while(true){

            designRelatedMenu.characterChoiceMenu();

            try {
                int characterChoice = scanner.nextInt(); 
                Hero hero = null;

                switch(characterChoice){
                    case 1:  
                        characterPrompts.swordsmanCharacter();
                        hero = innerCharacterMenuHandler.playerSwordsman();
                        break;

                    case 2:
                        characterPrompts.gunnerCharacter();
                        hero = innerCharacterMenuHandler.playerGunner();
                        break;

                    case 3: 
                        characterPrompts.mageCharacter();
                        hero = innerCharacterMenuHandler.playerMage();
                        break;

                    default:
                        System.out.println();
                        System.out.println("┌─────────────────────────────────────┐");
                        System.out.println("│  Oops! Invalid choice. Try again.   │");
                        System.out.println("└─────────────────────────────────────┘");
                        continue;
                }

                if(hero == null) {
                    continue;
                }

                return hero;

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
                scanner.nextLine(); 
            }
        }
    }
}