package swd_ahp;

import ahp.Algorithm;
import ahp.Matrix;
import ahp.Result;
import java.util.ArrayList;
import java.util.Collections;
import swd_ahp.data.Katalog;
import swd_ahp.data.Mieszkanie;
import swd_ahp.data.Ocena;
import swd_ahp.data.Preferencja;
import swd_ahp.data.UporzadkowaneMieszkanie;

/**
 *
 * @author Wojciech Gaweł
 */
public class Preparation {
    
    private Katalog katalogPoOgraniczeniach;
    private ArrayList<UporzadkowaneMieszkanie> listaUporzadkowana;
    private Algorithm alg;
    private Result algResult;
    
    public Preparation() {
        katalogPoOgraniczeniach = new Katalog();
        for (int i=0; i<Swd_ahp.katalog.getMieszkania().size(); i++) {
            Mieszkanie m = Swd_ahp.katalog.mieszkanieNaPozycji(i);
            if (Swd_ahp.uzytkownik.getOdlegloscOdCentrum().getRatingOfValue(m.getOdlegloscOdCentrumMiasta()) != null
                    && Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().getRatingOfValue(m.getLiczbaLiniiKomunikacjiMiejskiej()) != null
                    && Swd_ahp.uzytkownik.getOdlegloscOdPracy().getRatingOfValue(m.getOdlegloscOdPracy()) != null
                    && Swd_ahp.uzytkownik.getPietro().getRatingOfValue(m.getNumerPietra()) != null
                    && Swd_ahp.uzytkownik.getWielkosc().getRatingOfValue(m.getWielkoscMieszkania()) != null
                    && Swd_ahp.uzytkownik.getCena().getRatingOfValue(m.getCena()) != null) {
                katalogPoOgraniczeniach.dodajMieszkanie(Swd_ahp.katalog.mieszkanieNaPozycji(i));
            }
        }
        String info = "Liczba mieszkań odrzuconych wg ograniczeń: "+(Swd_ahp.katalog.getMieszkania().size()-katalogPoOgraniczeniach.getMieszkania().size());
        System.out.println(info);
        Swd_ahp.addLog(info);
        int liczba_mieszkan = katalogPoOgraniczeniach.getMieszkania().size();
        
        ArrayList<Matrix> macierzePreferencji = new ArrayList<>();
        
        // 1
        Matrix matrixOdlegloscOdCentrum = new Matrix(liczba_mieszkan);
        for (int m1=0; m1<liczba_mieszkan; m1++) {
            for (int m2=0; m2<liczba_mieszkan; m2++) {
                Mieszkanie mieszk1 = katalogPoOgraniczeniach.mieszkanieNaPozycji(m1);
                Mieszkanie mieszk2 = katalogPoOgraniczeniach.mieszkanieNaPozycji(m2);
                Ocena mieszk1_ocena = Swd_ahp.uzytkownik.getOdlegloscOdCentrum().getRatingOfValue(mieszk1.getOdlegloscOdCentrumMiasta());
                Ocena mieszk2_ocena = Swd_ahp.uzytkownik.getOdlegloscOdCentrum().getRatingOfValue(mieszk2.getOdlegloscOdCentrumMiasta());
                double ocena_ahp = Ocena.getAHPCompare(mieszk1_ocena, mieszk2_ocena);
                matrixOdlegloscOdCentrum.set(m1, m2, ocena_ahp);
            }
        }
        macierzePreferencji.add(matrixOdlegloscOdCentrum);
        System.out.println("Macierz odległości od centrum: \n"+matrixOdlegloscOdCentrum);
        
        // 2
        Matrix matrixOdlegloscOdPracy = new Matrix(liczba_mieszkan);
        for (int m1=0; m1<liczba_mieszkan; m1++) {
            for (int m2=0; m2<liczba_mieszkan; m2++) {
                Mieszkanie mieszk1 = katalogPoOgraniczeniach.mieszkanieNaPozycji(m1);
                Mieszkanie mieszk2 = katalogPoOgraniczeniach.mieszkanieNaPozycji(m2);
                Ocena mieszk1_ocena = Swd_ahp.uzytkownik.getOdlegloscOdPracy().getRatingOfValue(mieszk1.getOdlegloscOdPracy());
                Ocena mieszk2_ocena = Swd_ahp.uzytkownik.getOdlegloscOdPracy().getRatingOfValue(mieszk2.getOdlegloscOdPracy());
                double ocena_ahp = Ocena.getAHPCompare(mieszk1_ocena, mieszk2_ocena);
                matrixOdlegloscOdPracy.set(m1, m2, ocena_ahp);
            }
        }
        macierzePreferencji.add(matrixOdlegloscOdPracy);
        System.out.println("Macierz odległości od pracy: \n"+matrixOdlegloscOdPracy);
        
        // 3
        Matrix matrixWielkosc = new Matrix(liczba_mieszkan);
        for (int m1=0; m1<liczba_mieszkan; m1++) {
            for (int m2=0; m2<liczba_mieszkan; m2++) {
                Mieszkanie mieszk1 = katalogPoOgraniczeniach.mieszkanieNaPozycji(m1);
                Mieszkanie mieszk2 = katalogPoOgraniczeniach.mieszkanieNaPozycji(m2);
                Ocena mieszk1_ocena = Swd_ahp.uzytkownik.getWielkosc().getRatingOfValue(mieszk1.getWielkoscMieszkania());
                Ocena mieszk2_ocena = Swd_ahp.uzytkownik.getWielkosc().getRatingOfValue(mieszk2.getWielkoscMieszkania());
                double ocena_ahp = Ocena.getAHPCompare(mieszk1_ocena, mieszk2_ocena);
                matrixWielkosc.set(m1, m2, ocena_ahp);
            }
        }
        macierzePreferencji.add(matrixWielkosc);
        
        // 4
        Matrix matrixPietro = new Matrix(liczba_mieszkan);
        for (int m1=0; m1<liczba_mieszkan; m1++) {
            for (int m2=0; m2<liczba_mieszkan; m2++) {
                Mieszkanie mieszk1 = katalogPoOgraniczeniach.mieszkanieNaPozycji(m1);
                Mieszkanie mieszk2 = katalogPoOgraniczeniach.mieszkanieNaPozycji(m2);
                Ocena mieszk1_ocena = Swd_ahp.uzytkownik.getPietro().getRatingOfValue(mieszk1.getNumerPietra());
                Ocena mieszk2_ocena = Swd_ahp.uzytkownik.getPietro().getRatingOfValue(mieszk2.getNumerPietra());
                double ocena_ahp = Ocena.getAHPCompare(mieszk1_ocena, mieszk2_ocena);
                matrixPietro.set(m1, m2, ocena_ahp);
            }
        }
        macierzePreferencji.add(matrixPietro);
        
        // 5
        Matrix matrixLiczbaLiniiKomunikacjiMiejskiej = new Matrix(liczba_mieszkan);
        for (int m1=0; m1<liczba_mieszkan; m1++) {
            for (int m2=0; m2<liczba_mieszkan; m2++) {
                Mieszkanie mieszk1 = katalogPoOgraniczeniach.mieszkanieNaPozycji(m1);
                Mieszkanie mieszk2 = katalogPoOgraniczeniach.mieszkanieNaPozycji(m2);
                Ocena mieszk1_ocena = Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().getRatingOfValue(mieszk1.getLiczbaLiniiKomunikacjiMiejskiej());
                Ocena mieszk2_ocena = Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().getRatingOfValue(mieszk2.getLiczbaLiniiKomunikacjiMiejskiej());
                double ocena_ahp = Ocena.getAHPCompare(mieszk1_ocena, mieszk2_ocena);
                matrixLiczbaLiniiKomunikacjiMiejskiej.set(m1, m2, ocena_ahp);
            }
        }
        macierzePreferencji.add(matrixLiczbaLiniiKomunikacjiMiejskiej);
        
        // 6
        Matrix matrixCena = new Matrix(liczba_mieszkan);
        for (int m1=0; m1<liczba_mieszkan; m1++) {
            for (int m2=0; m2<liczba_mieszkan; m2++) {
                Mieszkanie mieszk1 = katalogPoOgraniczeniach.mieszkanieNaPozycji(m1);
                Mieszkanie mieszk2 = katalogPoOgraniczeniach.mieszkanieNaPozycji(m2);
                Ocena mieszk1_ocena = Swd_ahp.uzytkownik.getCena().getRatingOfValue(mieszk1.getCena());
                Ocena mieszk2_ocena = Swd_ahp.uzytkownik.getCena().getRatingOfValue(mieszk2.getCena());
                double ocena_ahp = Ocena.getAHPCompare(mieszk1_ocena, mieszk2_ocena);
                matrixCena.set(m1, m2, ocena_ahp);
            }
        }
        macierzePreferencji.add(matrixCena);
        
        // 7
        Matrix matrixOcena = new Matrix(liczba_mieszkan);
        for (int m1=0; m1<liczba_mieszkan; m1++) {
            for (int m2=0; m2<liczba_mieszkan; m2++) {
                Mieszkanie mieszk1 = katalogPoOgraniczeniach.mieszkanieNaPozycji(m1);
                Mieszkanie mieszk2 = katalogPoOgraniczeniach.mieszkanieNaPozycji(m2);
                Ocena mieszk1_ocena = mieszk1.getSubiektywnaOcenaUzytkownika();
                Ocena mieszk2_ocena = mieszk2.getSubiektywnaOcenaUzytkownika();
                double ocena_ahp = Ocena.getAHPCompare(mieszk1_ocena, mieszk2_ocena);
                matrixOcena.set(m1, m2, ocena_ahp);
            }
        }
        macierzePreferencji.add(matrixOcena);
        System.out.println("Macierz subiektywnej oceny: \n"+matrixOcena);
        
        int liczba_kryteriow = 7;
        Matrix matrixPorownanKryteriow = new Matrix(liczba_kryteriow);
        Ocena[] ocenyKryteriow = {
            Swd_ahp.uzytkownik.getWaznoscOdlegloscOdCentrum(),
            Swd_ahp.uzytkownik.getWaznoscOdlegloscOdPracy(),
            Swd_ahp.uzytkownik.getWaznoscWielkosc(),
            Swd_ahp.uzytkownik.getWaznoscPietro(),
            Swd_ahp.uzytkownik.getWaznoscLiczbaLiniiKomunikacjiMiejskiej(),
            Swd_ahp.uzytkownik.getWaznoscCena(),
            Swd_ahp.uzytkownik.getWaznoscSubiektywnaOcena()
        };
        for (int k1=0; k1<liczba_kryteriow; k1++) {
            for (int k2=0; k2<liczba_kryteriow; k2++) {
                double ocena_ahp = Ocena.getAHPCompare(ocenyKryteriow[k1], ocenyKryteriow[k2]);
                matrixPorownanKryteriow.set(k1, k2, ocena_ahp);
            }
        }
        System.out.println("Macierz porównań kryteriów: \n"+matrixPorownanKryteriow);
        
        alg = new Algorithm(macierzePreferencji, matrixPorownanKryteriow);
    }
    
    public ArrayList<UporzadkowaneMieszkanie> getList() {
        if (algResult == null) {
            algResult = alg.getResult();
        }
        
        listaUporzadkowana = new ArrayList<>(katalogPoOgraniczeniach.getMieszkania().size());
        for (int i=0; i<katalogPoOgraniczeniach.getMieszkania().size(); i++) {
            UporzadkowaneMieszkanie um = new UporzadkowaneMieszkanie();
            um.updateValues(katalogPoOgraniczeniach.mieszkanieNaPozycji(i));
            um.setWartoscPreferencji(algResult.getValueForIndex(i));
            listaUporzadkowana.add(um);
        }
        Collections.sort(listaUporzadkowana);
        return listaUporzadkowana;
    }
    
    public Result getResult() {
        if (algResult == null) {
            algResult = alg.getResult();
        }
        return algResult;
    }
    
}
