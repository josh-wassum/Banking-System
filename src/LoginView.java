import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class allows a user to log in to their account.
 * This view extends the View class.
 */
public class LoginView extends View{

    /**
     * Class constructor. Accepts the Saver object
     * as a parameter.
     */
    public LoginView() {
        super("Login", "Please login!");

    }

    /**
     * This method handles delivering the view,
     * accepting and using user input, and
     * guiding to the next view based upon user input.
     */
    public void serveView(){

        // Creating top level GUI elements.
        JFrame f=new JFrame(this.getTitle());
        JLabel welcomeMessage = new JLabel(this.getMessage());
        welcomeMessage.setBounds(250,75, 400,100);

        // Creating email entry GUI elements.
        JLabel emailName = new JLabel("Email Address:");
        emailName.setBounds(100, 175, 100, 25);
        JTextField email = new JTextField();
        email.setBounds(200, 175, 150, 25);

        // Creating Password entry GUI elements.
        JLabel passwordName = new JLabel("Password:");
        passwordName.setBounds(100, 225, 100, 25);
        JPasswordField password = new JPasswordField();
        password.setBounds(200, 225, 150, 25);

        // Creating user call to action GUI elements.
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(100, 275, 75, 25);
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(200, 275, 75, 25);

        // Setting up error messaging in the case the user enters incorrect information.
        JLabel errorMessage = new JLabel("Problems with logging in. Please try again or create a new account.");
        errorMessage.setBounds(100,300, 400,100);
        errorMessage.setVisible(false);

        // Adding our GUI elements to the screen.
        f.add(welcomeMessage);
        f.add(emailName);
        f.add(email);
        f.add(passwordName);
        f.add(password);
        f.add(cancelBtn);
        f.add(loginBtn);
        f.add(errorMessage);

        // Handles the user selecting the cancel button.
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                StartMenu startMenu = new StartMenu();
                startMenu.serveView();
            }
        });

        // Handles the user selecting the login button.
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ResultSet user = Connector.findAccountByLogin(email.getText(), password.getText());
                if (user != null) {
                    f.setVisible(false);
                    try {
                        BankAccount loggedInUser = new BankAccount(
                                user.getInt(1),
                                user.getString(2),
                                user.getString(3),
                                user.getString(4),
                                user.getInt(5),
                                user.getDouble(6));
                        user.close();
                        AccountView accountView = new AccountView(loggedInUser);
                        accountView.serveView();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
               errorMessage.setVisible(true);
            }
        });

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        // Defining view Parameters.
        f.setSize(600, 800);
        f.setLayout(null);
        f.setVisible(true);
    }

}
