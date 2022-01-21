import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This view acts as the entry view into our banking system.
 * It uses Java Swing to deliver a GUI.
 * Extends the View class.
 */
public class StartMenu extends View{
    private Saver accountSaver;

    /**
     * This is our class constructor.
     * It takes in the Saver object as a
     * parameter.
     * @param saver
     */
    public StartMenu(Saver saver) {
        super("Start Menu", "Welcome to J's bank! How may we assist you?");
        this.accountSaver = saver;
    }

    /**
     * This method handles delivering the view,
     * accepting and using user input, and
     * guiding to the next view based upon user input.
     */
    public void serveView(){

        // Defining our top level JFrame elements.
        JFrame f=new JFrame(this.getTitle());
        JLabel welcomeMessage = new JLabel(this.getMessage());
        welcomeMessage.setBounds(175,75, 400,100);

        // Initializes the GUI elements for user action on the screen.
        JButton login = new JButton("Login");
        login.setBounds(225, 200, 150, 25);
        JButton register = new JButton("Register");
        register.setBounds(225, 250, 150, 25);
        JButton exit = new JButton("Exit");
        exit.setBounds(225, 300, 150, 25);

        // Adds GUI elements to the screen.
        f.add(welcomeMessage);
        f.add(login);
        f.add(register);
        f.add(exit);

        // Handles the user selecting the login button.
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                LoginView loginView = new LoginView(accountSaver);
                loginView.serveView();
            }
        });

        // Handles the user selecting the register button.
        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               f.setVisible(false);
               RegisterView registerView = new RegisterView(accountSaver);
               registerView.serveView();
            }
        });

        // Handles the user selecting the exit button.
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Sets screen parameters.
        f.setSize(600, 800);
        f.setLayout(null);
        f.setVisible(true);
    }

}
