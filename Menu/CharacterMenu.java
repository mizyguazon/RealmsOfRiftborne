package Menu;

import Hero.*;
import Narration.*;
import DesignRelated.*;

import java.util.Scanner;

public class CharacterMenu extends Narration{
    Scanner scanner = new Scanner(System.in);

    public Hero chooseCharacterMenu(){
        MenuRelated designRelatedMenu = new MenuRelated();
        Characters characterPrompts = new Characters();
        Menu mainMenuHandler = new Menu();
        Hero hero = null;

        Narration separatorHandler = new Narration();

        separatorHandler.promptSeparator();

        while(true){

            designRelatedMenu.characterChoiceMenu();

            try {
                int characterChoice = scanner.nextInt(); 
                hero = null;

                switch(characterChoice){
                    case 1:  
                        characterPrompts.swordsmanCharacter();
                        hero =  new Swordsman();
                        hero.setSwordmanCharacterChosen(true);
                        break;

                    case 2:
                        characterPrompts.gunnerCharacter();
                        hero = new Gunner();
                        hero.setGunnerCharacterChosen(true);
                        break;

                    case 3: 
                        characterPrompts.mageCharacter();
                        hero = new Mage();
                        hero.setMageCharacterChosen(true);
                        break;

                    case 4:
                        hero = new Test();
                        break;
                        
                    default:
                        System.out.println();
                        System.out.println("┌─────────────────────────────────────┐");
                        System.out.println("│  Oops! Invalid choice. Try again.   │");
                        System.out.println("└─────────────────────────────────────┘");
                        continue;
                }

                if (hero != null) {
                    mainMenuHandler.mainMenu(hero);
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
                scanner.nextLine(); 
            }
        }

        return hero;
    }
}
    