/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swd_ahp.data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wojciech Gaweł
 */
@XmlRootElement
public class Uzytkownik {
    private Preferencja odlegloscOdCentrum = new Preferencja();
    private Preferencja odlegloscOdPracy = new Preferencja();
    private Preferencja wielkosc = new Preferencja();
    private Preferencja pietro = new Preferencja();
    private Preferencja liczbaLiniiKomunikacjiMiejskiej = new Preferencja();
    private Preferencja cena = new Preferencja();
    private Ocena waznoscOdlegloscOdCentrum = Ocena.getDefault();
    private Ocena waznoscOdlegloscOdPracy = Ocena.getDefault();
    private Ocena waznoscWielkosc = Ocena.getDefault();
    private Ocena waznoscPietro = Ocena.getDefault();
    private Ocena waznoscLiczbaLiniiKomunikacjiMiejskiej = Ocena.getDefault();
    private Ocena waznoscCena = Ocena.getDefault();
    private Ocena waznoscSubiektywnaOcena = Ocena.getDefault();

    public Uzytkownik() {
    }

    public Preferencja getOdlegloscOdCentrum() {
        return odlegloscOdCentrum;
    }

    public void setOdlegloscOdCentrum(Preferencja odlegloscOdCentrum) {
        this.odlegloscOdCentrum = odlegloscOdCentrum;
    }

    public Preferencja getOdlegloscOdPracy() {
        return odlegloscOdPracy;
    }

    public void setOdlegloscOdPracy(Preferencja odlegloscOdPracy) {
        this.odlegloscOdPracy = odlegloscOdPracy;
    }

    public Preferencja getWielkosc() {
        return wielkosc;
    }

    public void setWielkosc(Preferencja wielkosc) {
        this.wielkosc = wielkosc;
    }

    public Preferencja getPietro() {
        return pietro;
    }

    public void setPietro(Preferencja pietro) {
        this.pietro = pietro;
    }

    public Preferencja getLiczbaLiniiKomunikacjiMiejskiej() {
        return liczbaLiniiKomunikacjiMiejskiej;
    }

    public void setLiczbaLiniiKomunikacjiMiejskiej(Preferencja liczbaLiniiKomunikacjiMiejskiej) {
        this.liczbaLiniiKomunikacjiMiejskiej = liczbaLiniiKomunikacjiMiejskiej;
    }

    public Preferencja getCena() {
        return cena;
    }

    public void setCena(Preferencja cena) {
        this.cena = cena;
    }

    public Ocena getWaznoscOdlegloscOdCentrum() {
        return waznoscOdlegloscOdCentrum;
    }

    public void setWaznoscOdlegloscOdCentrum(Ocena waznoscOdlegloscOdCentrum) {
        this.waznoscOdlegloscOdCentrum = waznoscOdlegloscOdCentrum;
    }

    public Ocena getWaznoscOdlegloscOdPracy() {
        return waznoscOdlegloscOdPracy;
    }

    public void setWaznoscOdlegloscOdPracy(Ocena waznoscOdlegloscOdPracy) {
        this.waznoscOdlegloscOdPracy = waznoscOdlegloscOdPracy;
    }

    public Ocena getWaznoscWielkosc() {
        return waznoscWielkosc;
    }

    public void setWaznoscWielkosc(Ocena waznoscWielkosc) {
        this.waznoscWielkosc = waznoscWielkosc;
    }

    public Ocena getWaznoscPietro() {
        return waznoscPietro;
    }

    public void setWaznoscPietro(Ocena waznoscPietro) {
        this.waznoscPietro = waznoscPietro;
    }

    public Ocena getWaznoscLiczbaLiniiKomunikacjiMiejskiej() {
        return waznoscLiczbaLiniiKomunikacjiMiejskiej;
    }

    public void setWaznoscLiczbaLiniiKomunikacjiMiejskiej(Ocena waznoscLiczbaLiniiKomunikacjiMiejskiej) {
        this.waznoscLiczbaLiniiKomunikacjiMiejskiej = waznoscLiczbaLiniiKomunikacjiMiejskiej;
    }

    public Ocena getWaznoscCena() {
        return waznoscCena;
    }

    public void setWaznoscCena(Ocena waznoscCena) {
        this.waznoscCena = waznoscCena;
    }

    public Ocena getWaznoscSubiektywnaOcena() {
        return waznoscSubiektywnaOcena;
    }

    public void setWaznoscSubiektywnaOcena(Ocena waznoscSubiektywnaOcena) {
        this.waznoscSubiektywnaOcena = waznoscSubiektywnaOcena;
    }

    @Override
    public String toString() {
        return "Uzytkownik{" + "\n\todlegloscOdCentrum=" + odlegloscOdCentrum + ", \n\todlegloscOdPracy=" + odlegloscOdPracy + ", \n\twielkosc=" + wielkosc + ", \n\tpietro=" + pietro + ", \n\tliczbaLiniiKomunikacjiMiejskiej=" + liczbaLiniiKomunikacjiMiejskiej + ", \n\tcena=" + cena + ", \n\twaznoscOdlegloscOdCentrum=" + waznoscOdlegloscOdCentrum + ", \n\twaznoscOdlegloscOdPracy=" + waznoscOdlegloscOdPracy + ", waznoscWielkosc=" + waznoscWielkosc + ", \n\twaznoscPietro=" + waznoscPietro + ", \n\twaznoscLiczbaLiniiKomunikacjiMiejskiej=" + waznoscLiczbaLiniiKomunikacjiMiejskiej + ", \n\twaznoscCena=" + waznoscCena + ", \n\twaznoscSubiektywnaOcena=" + waznoscSubiektywnaOcena + '}';
    }
    
    
}
