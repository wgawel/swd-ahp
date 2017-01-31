package swd_ahp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import swd_ahp.data.Katalog;
import swd_ahp.data.Mieszkanie;
import swd_ahp.data.Uzytkownik;

/**
 *
 * @author Wojciech Gaweł
 */
public class DataManager {
    
    private static String file_path_flats = "./../implementacja/katalog_mieszkan.xml";
    private static String file_path_user = "./../implementacja/ustawienia.xml";
    
    public static void writeDataFlats(List<Mieszkanie> list) {
        Katalog katalog = new Katalog(list); 
        writeDataFlats(katalog);
    }
    
    public static void writeDataFlats(Katalog katalog) {
        final File katalogXml = new File(file_path_flats); // plik, gdzie zostanie zapisany stan modelu
        try {
            JAXBContext context = JAXBContext.newInstance("swd_ahp.data"); // tutaj nastąpi wczytanie pliku jaxb.index
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(katalog, new FileOutputStream(katalogXml)); // to jest moment zapisania stanu do pliku
            System.out.println("Pomyślnie zapisano plik z katalogiem mieszkań. Liczba mieszkań: "+katalog.getMieszkania().size());
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
            try {
                System.out.println("Błąd zapisu danych ('"+katalogXml.getCanonicalPath()+"'). \n"+ex.toString());
            } catch (IOException ex1) {
                Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex1);
                System.out.println("Błąd zapisu danych. \n"+ex.toString());
            }
        }
    }
    
    public static Katalog readDataFlats() {
        final File katalogXml = new File(file_path_flats); // plik, z którego wczytujemy dane do modelu
        if (!katalogXml.exists()) {
            writeDataFlats(new Katalog());
        }
        try {
            JAXBContext context = JAXBContext.newInstance("swd_ahp.data");
            Unmarshaller um = context.createUnmarshaller();
            Katalog katalog = (Katalog) um.unmarshal(katalogXml); // wczytanie danych do modelu - począwszy od Katalog
            System.out.println("Pomyślnie odczytano plik z katalogiem mieszkań. Liczba mieszkań: "+katalog.getMieszkania().size());
            return katalog;
        } catch (JAXBException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
            try {
                System.out.println("Błąd odczytu danych ('"+katalogXml.getCanonicalPath()+"'). "+ex.toString());
            } catch (IOException ex1) {
                Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex1);
                System.out.println("Błąd odczytu danych. "+ex.toString());
            }
        }
        return null;
    }
    
    public static void writeDataUser(Uzytkownik uzytkownik) {
        final File userXml = new File(file_path_user); // plik, gdzie zostanie zapisany stan modelu
        try {
            JAXBContext context = JAXBContext.newInstance("swd_ahp.data"); // tutaj nastąpi wczytanie pliku jaxb.index
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(uzytkownik, new FileOutputStream(userXml)); // to jest moment zapisania stanu do pliku
            System.out.println("Pomyślnie zapisano plik z ustawieniami preferencji.");
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
            try {
                System.out.println("Błąd zapisu danych ('"+userXml.getCanonicalPath()+"'). \n"+ex.toString());
            } catch (IOException ex1) {
                Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex1);
                System.out.println("Błąd zapisu danych. \n"+ex.toString());
            }
        }
    }
    
    public static Uzytkownik readDataUser() {
        final File userXml = new File(file_path_user); // plik, z którego wczytujemy dane do modelu
        if (!userXml.exists()) {
            writeDataUser(new Uzytkownik());
        }
        try {
            JAXBContext context = JAXBContext.newInstance("swd_ahp.data");
            Unmarshaller um = context.createUnmarshaller();
            Uzytkownik user = (Uzytkownik) um.unmarshal(userXml); // wczytanie danych do modelu - począwszy od user
            System.out.println("Pomyślnie odczytano plik z ustawieniami preferencji");
            return user;
        } catch (JAXBException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
            try {
                System.out.println("Błąd odczytu danych ('"+userXml.getCanonicalPath()+"'). "+ex.toString());
            } catch (IOException ex1) {
                Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex1);
                System.out.println("Błąd odczytu danych. "+ex.toString());
            }
        }
        return null;
    }
}
