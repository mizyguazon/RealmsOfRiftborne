import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        StartMenu startMenu = new StartMenu();
        CharacterMenu characterMenuHandler = new CharacterMenu();
        
        startMenu.displayStartMenu();
        characterMenuHandler.chooseCharacterMenu();

        scan.close();
        return;
    }
}