import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class handles the creation of a users account.
 * It extends the View class.
 */
public class RegisterView extends View{

    /**
     * This is the class constructor and takes the
     * Saver object as a parameter.
     */
    public RegisterView() {
        super("Create an Account", "Please create an account!");
    }

    /**
     * This method handles delivering the view,
     * accepting and using user input, and
     * guiding to the next view based upon user input.
     */
    public void serveView() {

        // Defining our top level JFrame elements.
        JFrame f = new JFrame(this.getTitle());
        JLabel welcomeMessage = new JLabel(this.getMessage());
        welcomeMessage.setBounds(225, 75, 400, 100);

        // Creating the user name GUI elements.
        JLabel name = new JLabel("Name:");
        name.setBounds(100, 175, 100, 25);
        JTextField nameInput = new JTextField();
        nameInput.setBounds(200, 175, 150, 25);

        // Creating the email GUI elements.
        JLabel emailName = new JLabel("Email Address:");
        emailName.setBounds(100, 225, 100, 25);
        JTextField email = new JTextField();
        email.setBounds(200, 225, 150, 25);

        // Creating the password GUI elements.
        JLabel passwordName = new JLabel("Password:");
        passwordName.setBounds(100, 275, 100, 25);
        JPasswordField password = new JPasswordField();
        password.setBounds(200, 275, 150, 25);

        // Defining the user call to action elements.
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(100, 325, 75, 25);
        JButton createBtn = new JButton("Create");
        createBtn.setBounds(200, 325, 75, 25);

        // Add all GUI elements to the screen.
        f.add(welcomeMessage);
        f.add(name);
        f.add(nameInput);
        f.add(emailName);
        f.add(email);
        f.add(passwordName);
        f.add(password);
        f.add(cancelBtn);
        f.add(createBtn);

        // Handles the user creating an account.
        createBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                accountSaver.addAccount(newAccount);
//                accountSaver.objectSaver();
                Connector.createUser(nameInput.getText(), email.getText(), password.getText());
                ResultSet user = Connector.findUserByLogin(email.getText(), password.getText());
                try {
                    Connector.createAccount(0.00, user.getInt(1));
                    user.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                f.setVisible(false);
                StartMenu startMenu = new StartMenu();
                startMenu.serveView();
            }
        });

        // Handles the user canceling their account creation.
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                StartMenu startMenu = new StartMenu();
                startMenu.serveView();
            }
        });

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        // Defining the Screens parameters.
        f.setSize(600, 800);
        f.setLayout(null);
        f.setVisible(true);
    }
}
