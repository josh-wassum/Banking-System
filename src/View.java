/**
* This class is my base view class.
 * It sets up the basic elements and functions
 * needed by the other views.
 **/

public abstract class View {
    private String title;
    private String message;

    public View(String title, String message){
        this.title = title;
        this.message = message;
    }

    /**
     * Public Methods
     */
    public void serveView(){

    }

    /**
     * Getters
     */

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }


}
