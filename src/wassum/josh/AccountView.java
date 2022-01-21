package wassum.josh;

import java.util.Scanner;

public class AccountView extends View{

    final BankAccount account;

    // Inheriting the view classes menu attribute.
    public AccountView(BankAccount account){
        super("Thank you for signing in " + account.getCustomerName() + ". Type in a corresponding number to continue: ");

        this.account = account;
    }

    public String getMenuItems() {
        return "\n1. View Balance" +
                "\n2. Make Withdrawal" +
                "\n3. Make Deposit" +
                "\n4. Close Account" +
                "\n5. Log out";
    }
    public int getSelection(Scanner scanner){
        boolean isInt = scanner.hasNextInt();
            if(isInt){
                int selection = scanner.nextInt();
                scanner.nextLine();
                return selection;
            }
        return -1;
    }

    public BankAccount getWithdrawal(Scanner scanner){
        System.out.println("Please enter the amount you wish to withdraw: ");
        boolean isInt = scanner.hasNextInt();
        if (isInt){
            double amount = scanner.nextInt();
            if (amount > this.getAccount().getBalance() || amount < 0){
                System.out.println("Invalid amount, current available balance: " + this.getAccount().getBalance());
                scanner.nextLine();
                return null;
            } else{
                this.account.withdraw(amount);
                return this.getAccount();
            }
        } else {
            System.out.println("Invalid entry!");
            scanner.nextLine();
            return null;
        }
    }

    public BankAccount getDeposit(Scanner scanner) {
        System.out.println("Please enter the amount you wish to deposit: ");
        boolean isInt = scanner.hasNextInt();
        if (isInt){
            double amount = scanner.nextInt();
            this.account.deposit(amount);
            return this.getAccount();
        } else {
            System.out.println("Invalid entry!");
            scanner.nextLine();
            return null;
        }
    }

    public boolean shouldClose(Scanner scanner){
        System.out.println("Are you sure you want to close your account? (Y/N)");
        String userSelection = scanner.nextLine();
        if (userSelection.equalsIgnoreCase("y")){
            System.out.println("Your account will be removed from the system.");
            return true;
        } else if (userSelection.equalsIgnoreCase("n")){
            System.out.println("Your account was not closed.");
            return false;
        } else{
            System.out.println("Sorry, I did not understand your input.");
            return false;
        }
    }

    public BankAccount getAccount() {
        return account;
    }
}
