package wassum.josh;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Saver {

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
