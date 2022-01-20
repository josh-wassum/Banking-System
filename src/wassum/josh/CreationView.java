package wassum.josh;

import java.util.Scanner;

public class CreationView extends View{
    public CreationView(){
        super("Welcome to the Creation view.");
    }

    public BankAccount createAccount(Scanner scanner){

        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Enter a password: ");
        String password = scanner.nextLine();
        System.out.println("Enter a starting balance: ");
        boolean isInt = scanner.hasNextInt();

        if (isInt) {
            int balance = scanner.nextInt();
            if(balance > 0){
                double accountNumber = Math.random();
                BankAccount user = new BankAccount(accountNumber, balance, name, email, password);
                System.out.println(user.getPassword());
                return user ;
            } else {
                System.out.println("Could not create your account, incorrect balance added!");
            }
        } else {
            System.out.println("Could not create your account, incorrect balance added!");
            scanner.nextLine();
        }
        return null;
    }
}
