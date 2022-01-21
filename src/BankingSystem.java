public class BankingSystem {

    // Main method, acts as entry point into the program.
    public static void main(String[] args) {
        Saver saver = new Saver();
        StartMenu startMenu = new StartMenu(saver);
        startMenu.serveView();
    }
}
