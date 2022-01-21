package wassum.josh;


public class BankAccount {
    private double accountNumber;
    private double balance;
    private String customerName;
    private String email;
    private String password;


    /**
     * This is my empty Class constructor.
     * This constructor allows me to use my Saver object.
     */
    public BankAccount(){

    }

    /**
     * This is my Class filled class constructor.
     * This constructor allows me to pass the relevant information needed
     * to create a new object.
     */
    public BankAccount(double accountNumber, double balance, String customerName, String email, String password) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerName = customerName;
        this.email = email;
        this.password = password;
    }

    /***********************************************************
    *
    * Methods
    *
     ***********************************************************/

    /**
     * This method allows for a user to make a deposit.
     * This receives an amount and sets the balance plus equal to it.
     */
    public void deposit(double depositAmount) {
        this.balance += depositAmount;
        System.out.println("Deposit of " + depositAmount + " made. New balance is " + this.balance);
    }

    /**
     * This method allows for a user to make a Withdrawal.
     * This receives an amount and sets the balance -= equal to it.
     */
    public void withdraw(double withdrawAmount) {
        this.balance -= withdrawAmount;
        System.out.println("Withdrawal of " + withdrawAmount + " processed. Remaining balance: " + this.balance);
    }

    /**
     * This method allows for a user to view their account balance.
     */
    public void viewBalance(){
        System.out.println("You have a current balance of " + this.balance + ".");
    }

    /***********************************************************
    *
    * Setters
     *
    *************************************************************/

    public void setAccountNumber(double accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*******************************************************
    *
    * Getters
    *
    * ******************************************************/

    public double getBalance() {
        return balance;
    }

    public double getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
