package wassum.josh;

// Utility imports
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        // Creating variable needed for program.
        Scanner scanner = new Scanner(System.in);
        StartView startView = new StartView();
        CreationView creationView = new CreationView();
        LoginView loginView = new LoginView();
        ArrayList<BankAccount> accountList = new ArrayList<BankAccount>();

        // This Try Catch handles the creation of my Array of bank accounts.
        try{
            FileInputStream fis = new FileInputStream(new File("./data.xml")); // Starting a new file input stream to decode.
            XMLDecoder decoder = new XMLDecoder(fis); // Starting Decoder and pass the file input stream.

            // This block iterates through the file using Document Builder.
            // The purpose of this is to create an iterable list of objects.
            // This list allows me to decode each object from the XML file Using XMLDecoder.
            File file = new File("./data.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("object");

            // For loop to create my array of objects.
            for (var i =0; i < nodeList.getLength(); i++){
                BankAccount account = (BankAccount) decoder.readObject();
                accountList.add(account);
            }
            decoder.close();
            fis.close();
        } catch(IOException ex){
            ex.printStackTrace();
        }

        // Starting a loop that will end when the user exits the system.
        boolean isRunning = true;
        while (isRunning) {

            // Gathering user input and passing it to various classes.
            System.out.println(startView.getMenu());
            int selection = startView.collectUserInput(scanner);

            switch (selection) {

                case 1: // Logging into the system.
                    System.out.println(loginView.getMenu());
                    boolean notLoggedIn = true;
                    BankAccount account = loginView.login(scanner, accountList);

                    // Checks to see if the user logged in by checking the value of account.
                    if (account == null) {
                        System.out.println("Incorrect Email or Password, please try again");
                    } else {
                        boolean loggedIn = true;
                        AccountView userAccount = new AccountView(account);
                        System.out.println(userAccount.getMenu());

                        // Runs the account view as long as the user is logged in.
                        while (loggedIn) {
                            System.out.println(userAccount.getMenuItems());
                            int userSelection = userAccount.getSelection(scanner);
                            switch (userSelection) {

                                case 1: // User balance inquiry.
                                    System.out.println("Your current Balance is " + account.getBalance());
                                    break;

                                case 2: // User withdrawals.
                                    BankAccount updatedWithdrawalAccount = userAccount.getWithdrawal(scanner);
                                    if (updatedWithdrawalAccount != null) {
                                        for (var i = 0; i < accountList.size(); i++) {
                                            if (accountList.get(i).getAccountNumber() == updatedWithdrawalAccount.getAccountNumber()) {
                                                accountList.set(i, updatedWithdrawalAccount);
                                                System.out.println("Your updated balance is: " + account.getBalance());
                                                break;
                                            }
                                        }
                                    }
                                    break;

                                case 3: // User deposits.
                                    BankAccount updatedDepositAccount = userAccount.getDeposit(scanner);
                                    if (updatedDepositAccount != null) {
                                        for (var i = 0; i < accountList.size(); i++) {
                                            if (accountList.get(i).getAccountNumber() == updatedDepositAccount.getAccountNumber()) {
                                                accountList.set(i, updatedDepositAccount);
                                                System.out.println("Your updated balance is: " + account.getBalance());
                                                break;
                                            }
                                        }
                                    }
                                    break;

                                case 4: // User account closure.
                                    boolean shouldClose = userAccount.shouldClose(scanner);
                                    if (shouldClose) {
                                        for (var i = 0; i < accountList.size(); i++) {
                                            if (accountList.get(i).getAccountNumber() == userAccount.getAccount().getAccountNumber()) {
                                                accountList.remove(i);
                                                System.out.println("Your updated balance is: " + account.getBalance());
                                                break;
                                            }
                                        }
                                    }
                                    break;

                                case 5: // User log out.
                                    loggedIn = false;
                                    System.out.println("you have successfully logged out!");
                                    break;

                                default: // Everything else
                                    System.out.println("Could not understand your input. Please try again.");
                            }
                        }
                    }
                    break;


                case 2: // Creating a new account.
                    System.out.println(creationView.getMenu());
                    BankAccount newAccount = creationView.createAccount(scanner);
                    if (newAccount != null) {
                        System.out.println("Account Created Successfully, please login.");
                        accountList.add(newAccount);
                    } else {
                        System.out.println("Error when creating account, please try again.");
                    }
                    break;


                case 3: // Handles exiting the system.
                    System.out.println("Thanks for visiting!");
                    Saver.objectSaver(accountList); // Saves arraylist into our XML file.
                    isRunning = false;
                    break;

                default: // All other input.
                    System.out.println("Could not understand your input.");
                    scanner.nextLine();
                    break;
            }
        }
        scanner.close();
    }
}

