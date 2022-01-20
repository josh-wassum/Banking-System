package wassum.josh;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginView extends View{

    public LoginView(){
        super("Please Sign in!");
    }


    public String getEmail(Scanner scanner) {
        System.out.println("Please enter your email: ");
        return (scanner.nextLine());
    }

    public String getPassword(Scanner scanner) {
        System.out.println("Please enter your password: ");
        return (scanner.nextLine());
    }

    public BankAccount login(Scanner scanner, ArrayList<BankAccount> accountList){
        String email = this.getEmail(scanner);
        String password = this.getPassword(scanner);
        for (BankAccount bankAccount : accountList) {
            if (bankAccount.getEmail().equalsIgnoreCase(email)) {
                if (bankAccount.getPassword().equals(password)) {
                    return bankAccount;
                }
                break;
            }
        }
        return null;
    }


}
