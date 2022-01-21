import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class handles actions users can take
 * on their accounts. It uses Java Swing to deliver
 * a GUI. Extends the View class.
 */
public class AccountView extends View{
    private Saver accountSaver;
    private BankAccount account;

    /**
     * This is the constructor. It needs to receive the Saver object as well
     * as the account logged in.
     * @param saver
     * @param account
     */
    public AccountView(Saver saver, BankAccount account) {
        super("Account View", "Welcome " + account.getCustomerName() + ".");
        this.accountSaver = saver;
        this.account = account;
    }

    /**
     * This method handles delivering the view,
     * accepting and using user input, and
     * guiding to the next view based upon user input.
     */
    public void serveView() {

        // Defining our top level JFrame elements.
        JFrame f = new JFrame(this.getTitle());
        JPopupMenu popupmenu = new JPopupMenu("Confirm");
        JLabel welcomeMessage = new JLabel(this.getMessage());
        welcomeMessage.setBounds(50, 75, 400, 100);

        // Displaying the users balance.
        JLabel currentBalance = new JLabel("Your current balance is: $" + account.getBalance());
        currentBalance.setBounds(50, 150, 400, 25);

        // Setting up the deposit GUI elements.
        JLabel depositName = new JLabel("Deposit Amount:");
        depositName.setBounds(50,200,125,25);
        JTextField depositAmount = new JTextField("0.0");
        depositAmount.setBounds(175, 200, 150, 25);
        JButton deposit = new JButton("Deposit");
        deposit.setBounds(325, 200, 100,25);

        // Setting up the withdrawal GUI elements.
        JLabel withdrawName = new JLabel("Withdraw Amount:");
        withdrawName.setBounds(50,250,125,25);
        JTextField withdrawAmount = new JTextField("0.0");
        withdrawAmount.setBounds(175, 250, 150, 25);
        JButton withdraw = new JButton("Deposit");
        withdraw.setBounds(325, 250, 100,25);

        // Setting up the logging out and account closure GUI elements.
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(100, 300, 150, 25);
        JButton closeBtn = new JButton("Close Account");
        closeBtn.setBounds(260, 300, 150, 25);

        // Creating a popup menu for a user to confirm account closure.
        JLabel confirmMessage = new JLabel("Are you sure you want \nto close you account?");
        JButton confirm = new JButton("Yes");
        popupmenu.add(confirmMessage); popupmenu.add(confirm);

        // Adding all GUI elements to the screen.
        f.add(welcomeMessage);
        f.add(currentBalance);
        f.add(depositName);
        f.add(depositAmount);
        f.add(deposit);
        f.add(withdrawName);
        f.add(withdrawAmount);
        f.add(withdraw);
        f.add(logoutBtn);
        f.add(closeBtn);

        // Handles the deposit on click functionality.
        deposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double amount = Integer.parseInt(depositAmount.getText());
               account.deposit(amount);
               accountSaver.editAccount(account);
               depositAmount.setText("0.0");
               currentBalance.setText("Your current balance is: $" + account.getBalance());
            }
        });

        // Handles the withdrawal on click functionality.
        withdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double amount = Integer.parseInt(withdrawAmount.getText());
                account.withdraw(amount);
                accountSaver.editAccount(account);
                withdrawAmount.setText("0.0");
                currentBalance.setText("Your current balance is: $" + account.getBalance());
            }
        });

        //Handles logging out and redirecting to a new screen.
        logoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                LoginView loginView = new LoginView(accountSaver);
                loginView.serveView();
                accountSaver.objectSaver();
            }
        });

        //Handles opening of to delete confirmation popup menu.
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                popupmenu.show(f, 250, 350);
            }
        });

        // Handles the user closing their account.
        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                LoginView loginView = new LoginView(accountSaver);
                loginView.serveView();
                accountSaver.removeAccount(account);
                accountSaver.objectSaver();
            }
        });

        // Defining screen parameters.
        f.setSize(600, 800);
        f.setLayout(null);
        f.setVisible(true);
    }
}
