import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int number = scan.nextInt();
        System.out.println("You entered: " + number);
        
        scan.close();

        return;
    }
}