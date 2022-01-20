package wassum.josh;

import java.util.Scanner;


// This class extends the View class
public class StartView extends View {

    // Inheriting the view classes menu attribute.
    public StartView(){
        super("Welcome to J' bank. Type in a corresponding number to continue: " +
                "\n1. Sign into account" +
                "\n2. Create account" +
                "\n3. Quit system");

    }

    public int collectUserInput (Scanner scanner) {
        boolean isInt = scanner.hasNextInt();
        if (isInt) {
            int selection = scanner.nextInt();
            scanner.nextLine();

            // While loop to make sure number is in range
            while (selection < 1 || selection >= 4) {
                System.out.println("Number out of range, please try again");
                System.out.println(this.getMenu());
                isInt = scanner.hasNextInt();
                if (isInt) {
                    selection = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    System.out.println("Incorrect value received");
                    selection = -1;
                    scanner.nextLine();
                }
            }
            return selection;
        } else {
            return -1;
        }
    }
}
