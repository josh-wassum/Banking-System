package wassum.josh;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
* This class handles saving objects into the data file.
 **/
public class Saver {

    /**
     * This method actually handles the saving of objects.
     * It accepts an ArrayList and loops through them and
     * uses XML encoder to write to the XML data file.
     **/
    public static void objectSaver(ArrayList o){
        try{
            FileOutputStream fos = new FileOutputStream(new File("./data.xml"));
            XMLEncoder encoder = new XMLEncoder(fos);
            for (Object value : o) {
                encoder.writeObject(value);
            }
            encoder.close();
            fos.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
