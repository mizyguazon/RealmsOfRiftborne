import java.util.Scanner;

// Things to polish: 
// Mechanism of the menu in terms of hopping from one menu to another
// Restriction on some functions
// Correct the repetition in narration restrictions DONE
// Other function needed:
// Shops
// Inventory
// Select Character Prompt

class StartMenu extends Narration { 
    static Scanner scan = new Scanner(System.in);
    static boolean hasVisitedPrologue = false;

    public void displayStartMenu(){
        //Menu mainMenuHandler = new Menu();

        System.out.println("+------------------------------+");
        System.out.println("|        Mystvale Academy      |");
        System.out.println("+------------------------------+");
        System.out.println("|    A Text-Based Adventure    |");
        System.out.println("|  in a World of Sorcery and   |");
        System.out.println("|         Shadowed Halls       |");
        System.out.println("+------------------------------+");
        System.out.println("|          1. Start Game       |");
        System.out.println("|          2. Exit Game        |");
        System.out.println("+------------------------------+");
        System.out.print("Enter your choice (1-2): ");

        String startMenuChoice = scan.nextLine();

        switch (startMenuChoice) {
            case "1":
                System.out.println("┌─────────────────────────┐");
                System.out.println("│   Starting the game...  │");
                System.out.println("└─────────────────────────┘");
                System.out.println();

                if(!hasVisitedPrologue){
                    prologueNarration();
                    //handler.defaultMainMenu();;
                    hasVisitedPrologue = true;
                }

                //mainMenuHandler.defaultMainMenu();
                break;

            case "2":
                System.out.println("┌───────────┐");
                System.out.println("│  Goodbye! │");
                System.out.println("└───────────┘");
                System.out.println();
                break;

            default:
                System.out.println("┌─────────────────────────────┐");
                System.out.println("│  Invalid choice. Try again! │");
                System.out.println("└─────────────────────────────┘");
                displayStartMenu();
        }
    }
}

class Menu extends Narration {
    static Scanner scanner = new Scanner(System.in);

    //Change these variables to private once encasulation is implemented (i think)
    static boolean hasVisitedShop = false;
    static boolean hasOpenedInventory = false;
    //static boolean hasVisitedPrologue = false;
    static boolean hasVisitedAcademy = false;
    static boolean hasVisitedLibrary = false;
    static boolean hasVisitedCanteen = false;
    static boolean hasVisitedGym = false;
    static boolean hasVisitedOffice = false;
    static boolean hasVisitedArea1 = false;
    static boolean hasVisitedArea2 = false;
    static boolean hasVisitedArea3 = false;

    public void defaultMainMenu(){
        boolean runMainMenu = true;
        AcademyMenu handler = new AcademyMenu();
        
        while(runMainMenu){
            System.out.println("+------------------------------------------+");
            System.out.println("|  --- MYSTVALE ACADEMY MAIN MENU ---      |");
            System.out.println("+------------------------------------------+");
            System.out.println("| 1. Go to Academy                         |");
            System.out.println("| 2. Shop                                  |");
            System.out.println("| 3. Inventory                             |");
            System.out.println("| 4. Area 1                                |");
            System.out.println("| 5. Area 2                                |");
            System.out.println("| 6. Area 3                                |");
            System.out.println("| 7. Exit Game                             |");
            System.out.println("+------------------------------------------+");
            System.out.print("    Enter your choice (1-7): ");

            
            String mainMenuChoice = scanner.nextLine();
            
            System.out.println();
            
            switch (mainMenuChoice){
                case "1":
                    System.out.println("┌──────────────────────────────┐");
                    System.out.println("│   Where do you want to go?   │");
                    System.out.println("└──────────────────────────────┘");
                    System.out.println();

                    if(!hasVisitedAcademy){
                        academyNarration();
                        hasVisitedAcademy = true;
                    } 
                    
                    handler.academyMapMenu();
                    
                    break;

                case "2":
                    System.out.println("┌──────────────────────────┐");
                    System.out.println("│    Welcome to the shop   │");
                    System.out.println("└──────────────────────────┘");
                    System.out.println();

                    if(!hasVisitedShop){
                        shopNarration();
                        hasVisitedShop = true;
                    } 
                    
                    //shopFunction();
                    break;

                case "3":
                    boolean isInventoryEmpty = false;
                    
                    if(!isInventoryEmpty){
                        System.out.println("┌───────────────────────────────────────┐");
                        System.out.println("│      Hmmm. Nothing to see here.       │");
                        System.out.println("│   Go shop if you want to own items.   │");
                        System.out.println("└───────────────────────────────────────┘");
                        System.out.println();

                    } else{
                        //inventoryFunction();
                    }

                    if(!hasOpenedInventory){
                        inventoryNarration();
                        hasOpenedInventory = true;
                    } 
                    
                    break;

                case "4":
                    
                    // add restriction

                    System.out.println("┌────────────────────────────────────┐");
                    System.out.println("│      Beware of forest entities     │");
                    System.out.println("└────────────────────────────────────┘");
                    System.out.println();
                    
                    if(!hasVisitedArea1){
                        area1Narration();
                        hasVisitedArea1 = true;
                    }
                    
                    break;
                    
                case "5":
                    
                    // add restriction

                    System.out.println("┌────────────────────────────────┐");
                    System.out.println("│    Beware of swamp entities    │");
                    System.out.println("└────────────────────────────────┘");
                    System.out.println();
                    
                    if(!hasVisitedArea2){
                        area2Narration();
                        hasVisitedArea2 = true;
                    }

                    break;
                    
                case "6":
                    
                    // add restriction

                    System.out.println("┌──────────────────────────────────────────────────┐");
                    System.out.println("│    Warning! You may or may not come out alive    │");
                    System.out.println("└──────────────────────────────────────────────────┘");
                    System.out.println();
                    
                    if(!hasVisitedArea3){
                        area3Narration();
                        hasVisitedArea3 = true;
                    }
                    
                    break;

                case "7":
                    System.out.println("\nAre you sure you want to quit playing? (y/n)");
                    
                    char ifWantToQuit = scanner.next().charAt(0);
    
                    if(ifWantToQuit == 'y' || ifWantToQuit == 'Y'){
                            System.out.println("┌────────────┐");
                            System.out.println("│ Good Game! │");
                            System.out.println("└────────────┘");
                            System.out.println();
                            runMainMenu = false;
                            break;
                        
                    } else if(ifWantToQuit == 'n' || ifWantToQuit == 'N'){
                            System.out.println("┌────────────────────────────┐");
                            System.out.println("│  Returning to Main Menu... │");
                            System.out.println("└────────────────────────────┘");
                            System.out.println();
                            defaultMainMenu();
                            break;
                        
                    } else {
                        System.out.println("┌────────────────────────────┐");
                        System.out.println("│  Invalid input. Try again! │");
                        System.out.println("└────────────────────────────┘");
                        System.out.println();

                    }
                    
                    break;

                default:
                    System.out.println("┌─────────────────────────────┐");
                    System.out.println("│  Invalid choice. Try again! │");
                    System.out.println("└─────────────────────────────┘");
                    System.out.println();

            }
        }
        
    }
    
}

class AcademyMenu extends Menu{
    public void academyMapMenu() {
        boolean academyMapMenu = true;

        while (academyMapMenu) {
            System.out.println("+-------------------------------------+");
            System.out.println("|  --- MYSTVALE ACADEMY MAP MENU ---  |");
            System.out.println("+-------------------------------------+");
            System.out.println("| 1. Library                          |");
            System.out.println("| 2. Canteen                          |");
            System.out.println("| 3. Gym                              |");
            System.out.println("| 4. Principal's Office               |");
            System.out.println("| 5. Exit Current Location            |");
            System.out.println("| 6. Main Menu                        |");
            System.out.println("+-------------------------------------+");
            System.out.print("Enter your choice (1-6): ");


            String academyMapMenuChoice = scanner.nextLine();

            switch (academyMapMenuChoice) {
                case "1":
                    System.out.println("┌──────────────────────────────────────────────┐");
                    System.out.println("│     Shh! Be mindful, others are studying     │");
                    System.out.println("└──────────────────────────────────────────────┘");
                    System.out.println();

                    if(!hasVisitedLibrary){
                        libraryNarration();
                        hasVisitedLibrary = true;
                    }

                    break;

                case "2":
                    System.out.println("┌────────────────────────────┐");
                    System.out.println("│    Buy at your own risk    │");
                    System.out.println("└────────────────────────────┘");
                    System.out.println();
                    if(!hasVisitedCanteen){
                        canteenNarration();
                        hasVisitedCanteen = true;
                    }

                    break;

                case "3":
                    System.out.println("┌────────────────────────────────────────────┐");
                    System.out.println("│     Are you ready to be physically fit?    │");
                    System.out.println("└────────────────────────────────────────────┘");
                    System.out.println();
                    if(!hasVisitedGym){
                        gymNarration();
                        hasVisitedGym = true;
                    }

                    break;

                case "4":
                    
                    // add restrictions

                    System.out.println("┌────────────────────────────────────────────┐");
                    System.out.println("│       May the odds be in your favor        │");
                    System.out.println("└────────────────────────────────────────────┘");
                    System.out.println();
                    
                    if(!hasVisitedOffice){
                        principalOfficeNarration();
                        hasVisitedOffice = true;
                    }
                    
                    break;

                case "5":
                    System.out.println("┌───────────────────────────────────────────┐");
                    System.out.println("│    You have left your current location    │");
                    System.out.println("│    For now                                │");
                    System.out.println("└───────────────────────────────────────────┘");
                    System.out.println();

                    academyMapMenu = false;
                    
                    // Polish this part
                    //defaultMainMenu();
                    //academyMapMenu();
                    break;
                    
                case "6":
                    defaultMainMenu();
                    break;

                default:
                    System.out.println("┌─────────────────────────────┐");
                    System.out.println("│  Invalid choice. Try again! │");
                    System.out.println("└─────────────────────────────┘");
                    System.out.println();
            }
        }    
    }
}

class CharacterMenu extends Narration{
    public void chooseCharacterMenu(){
        System.out.println("+------------------------------+");
        System.out.println("|      Choose a character      |");
        System.out.println("|------------------------------|");
        System.out.println("|  1. Swordsman                |");
        System.out.println("|  2. Gunner                   |");
        System.out.println("|  3. Mage                     |");
        System.out.println("+------------------------------+");
        System.out.print("  Enter your choice: ");

        char characterChoice = scanner.next().charAt(0);

        switch(characterChoice){
            case '1':            
                choiceSwordsman();
                break;

            case '2':
                choiceGunner();
                break;

            case '3':    
                choiceMage();
                break;

            default:
                System.out.println("┌─────────────────────────────┐");
                System.out.println("│  Invalid choice. Try again! │");
                System.out.println("└─────────────────────────────┘");
                chooseCharacterMenu();
        }    
    }
}