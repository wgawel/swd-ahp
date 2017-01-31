/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swd_ahp.data;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wojciech Gaweł
 */
@XmlRootElement
public class Katalog {
    private List<Mieszkanie> mieszkania = new ArrayList<>();

    public Katalog() {
    }

    public Katalog(List<Mieszkanie> mieszkania) {
        this.mieszkania = mieszkania;
    }
    
    public List<Mieszkanie> getMieszkania() {
        return mieszkania;
    }

    public void setMieszkania(List<Mieszkanie> mieszkania) {
        this.mieszkania = mieszkania;
    }
    
    public void dodajMieszkanie(Mieszkanie mieszkanie) {
        this.mieszkania.add(mieszkanie);
    }
    
    public void usunNaPozycji(int index) {
        this.mieszkania.remove(index);
    }
    
    public Mieszkanie mieszkanieNaPozycji(int index) {
        return this.mieszkania.get(index);
    }
}
