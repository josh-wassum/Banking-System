import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class allows a user to log in to their account.
 * This view extends the View class.
 */
public class LoginView extends View{
    private Saver accountSaver;

    /**
     * Class constructor. Accepts the Saver object
     * as a parameter.
     * @param accountSaver
     */
    public LoginView(Saver accountSaver) {
        super("Login", "Please login!");
        this.accountSaver = accountSaver;
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
                StartMenu startMenu = new StartMenu(accountSaver);
                startMenu.serveView();
            }
        });

        // Handles the user selecting the login button.
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (var i = 0; i < accountSaver.getAccountList().size(); i++){
                    if (accountSaver.getAccountList().get(i).getEmail().equalsIgnoreCase(email.getText()) &&
                    accountSaver.getAccountList().get(i).getPassword().equals(password.getText())){
                        f.setVisible(false);
                        AccountView accountView = new AccountView(accountSaver, accountSaver.getAccountList().get(i));
                        accountView.serveView();
                    }
                }
               errorMessage.setVisible(true);
            }
        });

        // Defining view Parameters.
        f.setSize(600, 800);
        f.setLayout(null);
        f.setVisible(true);
    }

}
