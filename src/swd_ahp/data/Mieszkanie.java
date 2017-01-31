/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swd_ahp.data;

/**
 *
 * @author Wojciech Gaweł
 */
public class Mieszkanie {
    private String nazwaMieszkania;
    private int odlegloscOdCentrumMiasta;
    private int wielkoscMieszkania;
    private int numerPietra;
    private int liczbaLiniiKomunikacjiMiejskiej;
    private int cena;
    private int odlegloscOdPracy;
    private Ocena subiektywnaOcenaUzytkownika;

    public Mieszkanie() {
    }

    
    public Mieszkanie(String nazwaMieszkania, int odlegloscOdCentrumMiasta, int wielkoscMieszkania, int numerPietra, int liczbaLiniiKomunikacjiMiejskiej, int cena) {
        this.nazwaMieszkania = nazwaMieszkania;
        this.odlegloscOdCentrumMiasta = odlegloscOdCentrumMiasta;
        this.wielkoscMieszkania = wielkoscMieszkania;
        this.numerPietra = numerPietra;
        this.liczbaLiniiKomunikacjiMiejskiej = liczbaLiniiKomunikacjiMiejskiej;
        this.cena = cena;
        this.odlegloscOdPracy = 0;
        this.subiektywnaOcenaUzytkownika = Ocena.SREDNIO_PREFEROWANE;
    }

    public Mieszkanie(String nazwaMieszkania, int odlegloscOdCentrumMiasta, int wielkoscMieszkania, int numerPietra, int liczbaLiniiKomunikacjiMiejskiej, int cena, int odlegloscOdPracy, Ocena subiektywnaOcenaUzytkownika) {
        this.nazwaMieszkania = nazwaMieszkania;
        this.odlegloscOdCentrumMiasta = odlegloscOdCentrumMiasta;
        this.wielkoscMieszkania = wielkoscMieszkania;
        this.numerPietra = numerPietra;
        this.liczbaLiniiKomunikacjiMiejskiej = liczbaLiniiKomunikacjiMiejskiej;
        this.cena = cena;
        this.odlegloscOdPracy = odlegloscOdPracy;
        this.subiektywnaOcenaUzytkownika = subiektywnaOcenaUzytkownika;
    }

    public String getNazwaMieszkania() {
        return nazwaMieszkania;
    }

    public void setNazwaMieszkania(String nazwaMieszkania) {
        this.nazwaMieszkania = nazwaMieszkania;
    }

    public int getOdlegloscOdCentrumMiasta() {
        return odlegloscOdCentrumMiasta;
    }

    public void setOdlegloscOdCentrumMiasta(int odlegloscOdCentrumMiasta) {
        this.odlegloscOdCentrumMiasta = odlegloscOdCentrumMiasta;
    }

    public int getWielkoscMieszkania() {
        return wielkoscMieszkania;
    }

    public void setWielkoscMieszkania(int wielkoscMieszkania) {
        this.wielkoscMieszkania = wielkoscMieszkania;
    }

    public int getNumerPietra() {
        return numerPietra;
    }

    public void setNumerPietra(int numerPietra) {
        this.numerPietra = numerPietra;
    }

    public int getLiczbaLiniiKomunikacjiMiejskiej() {
        return liczbaLiniiKomunikacjiMiejskiej;
    }

    public void setLiczbaLiniiKomunikacjiMiejskiej(int liczbaLiniiKomunikacjiMiejskiej) {
        this.liczbaLiniiKomunikacjiMiejskiej = liczbaLiniiKomunikacjiMiejskiej;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getOdlegloscOdPracy() {
        return odlegloscOdPracy;
    }

    public void setOdlegloscOdPracy(int odlegloscOdPracy) {
        this.odlegloscOdPracy = odlegloscOdPracy;
    }

    public Ocena getSubiektywnaOcenaUzytkownika() {
        return subiektywnaOcenaUzytkownika;
    }

    public void setSubiektywnaOcenaUzytkownika(Ocena subiektywnaOcenaUzytkownika) {
        this.subiektywnaOcenaUzytkownika = subiektywnaOcenaUzytkownika;
    }
    
    public void updateValues(Mieszkanie m) {
        this.nazwaMieszkania = m.nazwaMieszkania;
        this.odlegloscOdCentrumMiasta = m.odlegloscOdCentrumMiasta;
        this.wielkoscMieszkania = m.wielkoscMieszkania;
        this.numerPietra = m.numerPietra;
        this.liczbaLiniiKomunikacjiMiejskiej = m.liczbaLiniiKomunikacjiMiejskiej;
        this.cena = m.cena;
        this.odlegloscOdPracy = m.odlegloscOdPracy;
        this.subiektywnaOcenaUzytkownika = m.subiektywnaOcenaUzytkownika;
    }

    @Override
    public String toString() {
        return nazwaMieszkania+ ", " + wielkoscMieszkania + "m2, od centrum: " + odlegloscOdCentrumMiasta + "m, od pracy: " + odlegloscOdPracy + "m, piętro: " + numerPietra + ", linie komun. miej.: " + liczbaLiniiKomunikacjiMiejskiej + ", " + cena + "zł/msc, sub. ocena: " + subiektywnaOcenaUzytkownika;
    }
    
}
