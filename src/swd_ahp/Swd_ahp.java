package swd_ahp;

import gui.MainJFrame;
import javax.swing.JFrame;
import swd_ahp.data.Katalog;
import swd_ahp.data.Uzytkownik;

/**
 *
 * @author Wojciech Gaweł
 */
public class Swd_ahp {

    public static Katalog katalog;
    public static Uzytkownik uzytkownik;
    
    public static JFrame mainJFrame;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        katalog = DataManager.readDataFlats();
        uzytkownik = DataManager.readDataUser();
        
        mainJFrame = new MainJFrame();
        mainJFrame.setVisible(true);
    }
    
    public static void saveChangesFlats() {
        DataManager.writeDataFlats(katalog);
    }
    
    public static void saveChangesUser() {
        DataManager.writeDataUser(uzytkownik);
    }
    
    public static void reloadUser() {
        uzytkownik = DataManager.readDataUser();
    }
    
    public static void addLog(String s) {
        if (mainJFrame != null) {
            ((MainJFrame) mainJFrame).addLog(s);
        }
    }
}
