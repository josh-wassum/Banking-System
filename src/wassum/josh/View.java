package wassum.josh;

public abstract class View {
    private String menu;

    public View(String menu) {
        this.menu = menu;
    }

    public String getMenu() {
        return menu;
    }


}
