import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * This class handles saving objects into the data file.
 **/
public class Saver {

    private ArrayList<BankAccount> accountList = new ArrayList<BankAccount>();

    public Saver(){
        this.objectReader();
    }

    /**
     * This method actually handles the saving of objects.
     * It accepts an ArrayList and loops through them and
     * uses XML encoder to write to the XML data file.
     **/
    public void objectSaver(){
        try{
            FileOutputStream fos = new FileOutputStream(new File("./data.xml"));
            XMLEncoder encoder = new XMLEncoder(fos);
            for (Object value : this.accountList) {
                encoder.writeObject(value);
            }
            encoder.close();
            fos.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    /**
     * This method creates an ArrayList from
     * the projects saved within Data.xml.
     * It uses XMLDecoder to do this.
     */
    public void objectReader(){

        // This Try Catch handles the creation of my Array of bank accounts.
        try{
            FileInputStream fis = new FileInputStream(new File("./data.xml")); // Starting a new file input stream to decode.
            XMLDecoder decoder = new XMLDecoder(fis); // Starting Decoder and pass the file input stream.

            // This block iterates through the file using Document Builder.
            // The purpose of this is to create an iterable list of objects.
            // This list allows me to decode each object from the XML file Using XMLDecoder.
            File file = new File("./data.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("object");

            // For loop to create my array of objects.
            for (var i =0; i < nodeList.getLength(); i++){
                BankAccount account = (BankAccount) decoder.readObject();
                this.accountList.add(account);
            }
            decoder.close();
            fis.close();
        } catch(IOException | ParserConfigurationException | SAXException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Add an account to the ArrayList.
     */
    public void addAccount(BankAccount account) {
        this.accountList.add(account);
    }

    /**
     * Attempts to change a record within the ArrayList
     * with a user defined account. Returns true if
     * successful and false otherwise.
     */
    public boolean editAccount(BankAccount account){
        for (var i = 0; i < accountList.size(); i++){
            if (account.getAccountNumber() == accountList.get(i).getAccountNumber()){
                accountList.set(i, account);
                return true;
            }
        }
        return false;
    }

    /**
     * The method attempts to remove an account from
     * the ArrayList. It accepts an account as a parameter.
     * @param account
     * @return
     */
    public boolean removeAccount(BankAccount account){
        for (var i = 0; i < accountList.size(); i++){
            if (account.getAccountNumber() == accountList.get(i).getAccountNumber()){
                accountList.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Setters
     */
    public void setAccountList(ArrayList<BankAccount> accountList) {
        this.accountList = accountList;
    }

    /**
     * Getters
     */
    public ArrayList<BankAccount> getAccountList() {
        return accountList;
    }
}
