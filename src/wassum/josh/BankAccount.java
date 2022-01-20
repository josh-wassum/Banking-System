package wassum.josh;

public class BankAccount {
    private double accountNumber;
    private double balance;
    private String customerName;
    private String email;
    private String password;

    public BankAccount(){

    }

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

    public void deposit(double depositAmount) {
        this.balance += depositAmount;
        System.out.println("Deposit of " + depositAmount + " made. New balance is " + this.balance);
    }

    public void withdraw(double withdrawAmount) {
        if (this.balance - withdrawAmount < 0){
            System.out.println("Only " + this.balance + " available.");
        } else{
            this.balance -= withdrawAmount;
            System.out.println("Withdrawal of " + withdrawAmount + " processed. Remaining balance: " + this.balance);
        }
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
