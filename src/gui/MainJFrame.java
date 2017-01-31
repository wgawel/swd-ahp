/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import swd_ahp.Preparation;
import swd_ahp.data.Ocena;
import swd_ahp.Swd_ahp;
import swd_ahp.data.Mieszkanie;

/**
 *
 * @author Wojciech Gaweł
 */
public class MainJFrame extends javax.swing.JFrame {
    
    private boolean blokujZapisywaniePreferencji = false;

    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {
        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        initComponents();
        jComboBoxDaneMieszkaniaOcenaUzytkownika.setModel(new DefaultComboBoxModel(Ocena.values()));
        jComboBoxDaneMieszkaniaOcenaUzytkownika.setSelectedIndex(2);
        this.odswiezListeMieszkan();
        this.odswiezPreferencje();
        
        jTextFieldDaneMieszkaniaNazwa.setText("");
        SpinnerNumberModel model1 = new SpinnerNumberModel(0, 0, 1000000, 1);  
        jSpinnerDaneMieszkaniaOdlegloscOdCentrum.setModel(model1);
        SpinnerNumberModel model2 = new SpinnerNumberModel(0, 0, 1000000, 1);  
        jSpinnerDaneMieszkaniaWielkosc.setModel(model2);
        SpinnerNumberModel model3 = new SpinnerNumberModel(0, 0, 1000000, 1);  
        jSpinnerDaneMieszkaniaPietro.setModel(model3);
        SpinnerNumberModel model4 = new SpinnerNumberModel(0, 0, 1000000, 1);  
        jSpinnerDaneMieszkaniaLiczbaLiniiKomunikacjiMiejskiej.setModel(model4);
        SpinnerNumberModel model5 = new SpinnerNumberModel(0, 0, 1000000, 1);  
        jSpinnerDaneMieszkaniaCena.setModel(model5);
        SpinnerNumberModel model6 = new SpinnerNumberModel(0, 0, 1000000, 1);  
        jSpinnerDaneMieszkaniaOdlegloscOdPracy.setModel(model6);
    }
    
    public void addLog(String s) {
        jTextAreaLog.append(s+"\n");
        jTextAreaLog.setCaretPosition(jTextAreaLog.getDocument().getLength());
    }
    
    public void clearLog() {
        jTextAreaLog.setText("");
    }
    
    private void makeCalculations() {
       clearLog();
       addLog("---\nRozpoczęto obliczanie preferencji...");
       Preparation p = new Preparation();
       jListUporzadkowaneMieszkania.setListData(p.getList().toArray());
       
       StringBuffer infotext = new StringBuffer();
       
       DecimalFormat df = new DecimalFormat("0.000");
       
       infotext.append("\nMacierz preferencji ze względu na odległość do centrum miasta:\n");
       if (p.getResult().getIsCRCalculated(0)) {
           infotext.append("Spójność = ").append(df.format(p.getResult().getCR(0))).append("\n");
           if (p.getResult().getCR(0) <= 0.1) {
               infotext.append("macierz jest spójna");
           } else {
               infotext.append("brak spójności macierzy - spróbuj poprawić preferencje");
           }
       } else {
           infotext.append("brak możliwości obliczenia spójności (zbyt duża liczba mieszkań)");
       }
       
       infotext.append("\nMacierz preferencji względu na odległość do miejsca pracy:\n");
       if (p.getResult().getIsCRCalculated(1)) {
           infotext.append("Spójność = ").append(df.format(p.getResult().getCR(1))).append("\n");
           if (p.getResult().getCR(1) <= 0.1) {
               infotext.append("macierz jest spójna");
           } else {
               infotext.append("brak spójności macierzy - spróbuj poprawić preferencje");
           }
       } else {
           infotext.append("brak możliwości obliczenia spójności (zbyt duża liczba mieszkań)");
       }
       
       infotext.append("\nMacierz preferencji ze względu na wielkość mieszkania:\n");
       if (p.getResult().getIsCRCalculated(2)) {
           infotext.append("Spójność = ").append(df.format(p.getResult().getCR(2))).append("\n");
           if (p.getResult().getCR(2) <= 0.1) {
               infotext.append("macierz jest spójna");
           } else {
               infotext.append("brak spójności macierzy - spróbuj poprawić preferencje");
           }
       } else {
           infotext.append("brak możliwości obliczenia spójności (zbyt duża liczba mieszkań)");
       }
       
       infotext.append("\nMacierz preferencji ze wwzględu na numer piętra na jakim się znajduje:\n");
       if (p.getResult().getIsCRCalculated(3)) {
           infotext.append("Spójność = ").append(df.format(p.getResult().getCR(3))).append("\n");
           if (p.getResult().getCR(3) <= 0.1) {
               infotext.append("macierz jest spójna");
           } else {
               infotext.append("brak spójności macierzy - spróbuj poprawić preferencje");
           }
       } else {
           infotext.append("brak możliwości obliczenia spójności (zbyt duża liczba mieszkań)");
       }
       
       infotext.append("\nMacierz względu na liczbę odjeżdżających linii komunikacji miejskiej :\n");
       if (p.getResult().getIsCRCalculated(4)) {
           infotext.append("Spójność = ").append(df.format(p.getResult().getCR(4))).append("\n");
           if (p.getResult().getCR(4) <= 0.1) {
               infotext.append("macierz jest spójna");
           } else {
               infotext.append("brak spójności macierzy - spróbuj poprawić preferencje");
           }
       } else {
           infotext.append("brak możliwości obliczenia spójności (zbyt duża liczba mieszkań)");
       }
       
       infotext.append("\nMacierz preferencji ze względu na cenę:\n");
       if (p.getResult().getIsCRCalculated(5)) {
           infotext.append("Spójność = ").append(df.format(p.getResult().getCR(5))).append("\n");
           if (p.getResult().getCR(5) <= 0.1) {
               infotext.append("macierz jest spójna");
           } else {
               infotext.append("brak spójności macierzy - spróbuj poprawić preferencje");
           }
       } else {
           infotext.append("brak możliwości obliczenia spójności (zbyt duża liczba mieszkań)");
       }
       
       infotext.append("\nMacierz preferencji ze względu na subiektywną ocenę użytkownika:\n");
       if (p.getResult().getIsCRCalculated(6)) {
           infotext.append("Spójność = ").append(df.format(p.getResult().getCR(6))).append("\n");
           if (p.getResult().getCR(6) <= 0.1) {
               infotext.append("macierz jest spójna");
           } else {
               infotext.append("brak spójności macierzy - spróbuj poprawić preferencje");
           }
       } else {
           infotext.append("brak możliwości obliczenia spójności (zbyt duża liczba mieszkań)");
       }
       
       infotext.append("\nMacierz porównań kryteriów:\n");
       if (p.getResult().getIsCRCalculated(6)) {
           infotext.append("Spójność = ").append(df.format(p.getResult().getCR(6))).append("\n");
           if (p.getResult().getCR(6) <= 0.1) {
               infotext.append("macierz jest spójna");
           } else {
               infotext.append("brak spójności macierzy - spróbuj poprawić preferencje");
           }
       } else {
           infotext.append("brak możliwości obliczenia spójności (zbyt duża liczba mieszkań)");
       }
       infotext.append("\n");
       addLog(infotext.toString());
       addLog("Zakończono obliczanie preferencji.");
       System.out.print("Zakończono obliczanie preferencji.");
       
    }
    
    public void odswiezListeMieszkan() {
        jListMieszkania.setListData(Swd_ahp.katalog.getMieszkania().toArray());
    }
    
    public void odswiezListeMieszkanBezZmianyZaznaczenia() {
        int selectedIndex = jListMieszkania.getSelectedIndex();
        jListMieszkania.setListData(Swd_ahp.katalog.getMieszkania().toArray());
        jListMieszkania.setSelectedIndex(selectedIndex);
    }
    
    public void wczytajDaneMieszkania(Mieszkanie m) { 
        jTextFieldDaneMieszkaniaNazwa.setText(m.getNazwaMieszkania());
        jSpinnerDaneMieszkaniaOdlegloscOdCentrum.setValue(m.getOdlegloscOdCentrumMiasta());
        jSpinnerDaneMieszkaniaWielkosc.setValue(m.getWielkoscMieszkania());
        jSpinnerDaneMieszkaniaPietro.setValue(m.getNumerPietra());
        jSpinnerDaneMieszkaniaLiczbaLiniiKomunikacjiMiejskiej.setValue(m.getLiczbaLiniiKomunikacjiMiejskiej());
        jSpinnerDaneMieszkaniaCena.setValue(m.getCena());
        jSpinnerDaneMieszkaniaOdlegloscOdPracy.setValue(m.getOdlegloscOdPracy());
        jComboBoxDaneMieszkaniaOcenaUzytkownika.setSelectedItem(m.getSubiektywnaOcenaUzytkownika());
    }
    
    public Mieszkanie utworzMieszkanieWgDanychMieszkania() {
        String nazwaMieszkania = jTextFieldDaneMieszkaniaNazwa.getText().trim();
        int odlegloscOdCentrumMiasta = (int) jSpinnerDaneMieszkaniaOdlegloscOdCentrum.getValue();
        int wielkoscMieszkania = (int) jSpinnerDaneMieszkaniaWielkosc.getValue();
        int numerPietra = (int) jSpinnerDaneMieszkaniaPietro.getValue();
        int liczbaLiniiKomunikacjiMiejskiej = (int) jSpinnerDaneMieszkaniaLiczbaLiniiKomunikacjiMiejskiej.getValue();
        int cena = (int) jSpinnerDaneMieszkaniaCena.getValue();
        int odlegloscOdPracy = (int) jSpinnerDaneMieszkaniaOdlegloscOdPracy.getValue();
        Ocena subiektywnaOcenaUzytkownika = (Ocena) jComboBoxDaneMieszkaniaOcenaUzytkownika.getSelectedItem();
        
        return new Mieszkanie(nazwaMieszkania, odlegloscOdCentrumMiasta, wielkoscMieszkania, numerPietra, liczbaLiniiKomunikacjiMiejskiej, cena, odlegloscOdPracy, subiektywnaOcenaUzytkownika);
    }
    
    public void odswiezPreferencjeOdlegloscOdCentrum() {
        blokujZapisywaniePreferencji = true;
        
        jSpinnerOdlegloscOdCentrumMin.setValue(Swd_ahp.uzytkownik.getOdlegloscOdCentrum().getRating_1_minValue());
        jSpinnerOdlegloscOdCentrumMaks.setValue(Swd_ahp.uzytkownik.getOdlegloscOdCentrum().getRating_1_maxValue());
        
        rangeSliderOdlegloscOdCentrum2.setMinimum(Swd_ahp.uzytkownik.getOdlegloscOdCentrum().getRating_1_minValue());
        rangeSliderOdlegloscOdCentrum2.setMaximum(Swd_ahp.uzytkownik.getOdlegloscOdCentrum().getRating_1_maxValue());
        rangeSliderOdlegloscOdCentrum2.setValue(Swd_ahp.uzytkownik.getOdlegloscOdCentrum().getRating_2_minValue());
        rangeSliderOdlegloscOdCentrum2.setUpperValue(Swd_ahp.uzytkownik.getOdlegloscOdCentrum().getRating_2_maxValue());
        
        rangeSliderOdlegloscOdCentrum3.setMinimum(Swd_ahp.uzytkownik.getOdlegloscOdCentrum().getRating_2_minValue());
        rangeSliderOdlegloscOdCentrum3.setMaximum(Swd_ahp.uzytkownik.getOdlegloscOdCentrum().getRating_2_maxValue());
        rangeSliderOdlegloscOdCentrum3.setValue(Swd_ahp.uzytkownik.getOdlegloscOdCentrum().getRating_3_minValue());
        rangeSliderOdlegloscOdCentrum3.setUpperValue(Swd_ahp.uzytkownik.getOdlegloscOdCentrum().getRating_3_maxValue());
        
        rangeSliderOdlegloscOdCentrum4.setMinimum(Swd_ahp.uzytkownik.getOdlegloscOdCentrum().getRating_3_minValue());
        rangeSliderOdlegloscOdCentrum4.setMaximum(Swd_ahp.uzytkownik.getOdlegloscOdCentrum().getRating_3_maxValue());
        rangeSliderOdlegloscOdCentrum4.setValue(Swd_ahp.uzytkownik.getOdlegloscOdCentrum().getRating_4_minValue());
        rangeSliderOdlegloscOdCentrum4.setUpperValue(Swd_ahp.uzytkownik.getOdlegloscOdCentrum().getRating_4_maxValue());
        
        rangeSliderOdlegloscOdCentrum5.setMinimum(Swd_ahp.uzytkownik.getOdlegloscOdCentrum().getRating_4_minValue());
        rangeSliderOdlegloscOdCentrum5.setMaximum(Swd_ahp.uzytkownik.getOdlegloscOdCentrum().getRating_4_maxValue());
        rangeSliderOdlegloscOdCentrum5.setValue(Swd_ahp.uzytkownik.getOdlegloscOdCentrum().getRating_5_minValue());
        rangeSliderOdlegloscOdCentrum5.setUpperValue(Swd_ahp.uzytkownik.getOdlegloscOdCentrum().getRating_5_maxValue());
        
        jLabelOdlegloscOdCentrum2.setText(Swd_ahp.uzytkownik.getOdlegloscOdCentrum().rating_2_toString());
        jLabelOdlegloscOdCentrum3.setText(Swd_ahp.uzytkownik.getOdlegloscOdCentrum().rating_3_toString());
        jLabelOdlegloscOdCentrum4.setText(Swd_ahp.uzytkownik.getOdlegloscOdCentrum().rating_4_toString());
        jLabelOdlegloscOdCentrum5.setText(Swd_ahp.uzytkownik.getOdlegloscOdCentrum().rating_5_toString());
        
        blokujZapisywaniePreferencji = false;
    }
    
    public void odswiezPreferencjeOdlegloscOdPracy() {
        blokujZapisywaniePreferencji = true;
        
        jSpinnerOdlegloscOdPracyMin.setValue(Swd_ahp.uzytkownik.getOdlegloscOdPracy().getRating_1_minValue());
        jSpinnerOdlegloscOdPracyMaks.setValue(Swd_ahp.uzytkownik.getOdlegloscOdPracy().getRating_1_maxValue());
        
        rangeSliderOdlegloscOdPracy2.setMinimum(Swd_ahp.uzytkownik.getOdlegloscOdPracy().getRating_1_minValue());
        rangeSliderOdlegloscOdPracy2.setMaximum(Swd_ahp.uzytkownik.getOdlegloscOdPracy().getRating_1_maxValue());
        rangeSliderOdlegloscOdPracy2.setValue(Swd_ahp.uzytkownik.getOdlegloscOdPracy().getRating_2_minValue());
        rangeSliderOdlegloscOdPracy2.setUpperValue(Swd_ahp.uzytkownik.getOdlegloscOdPracy().getRating_2_maxValue());
        
        rangeSliderOdlegloscOdPracy3.setMinimum(Swd_ahp.uzytkownik.getOdlegloscOdPracy().getRating_2_minValue());
        rangeSliderOdlegloscOdPracy3.setMaximum(Swd_ahp.uzytkownik.getOdlegloscOdPracy().getRating_2_maxValue());
        rangeSliderOdlegloscOdPracy3.setValue(Swd_ahp.uzytkownik.getOdlegloscOdPracy().getRating_3_minValue());
        rangeSliderOdlegloscOdPracy3.setUpperValue(Swd_ahp.uzytkownik.getOdlegloscOdPracy().getRating_3_maxValue());
        
        rangeSliderOdlegloscOdPracy4.setMinimum(Swd_ahp.uzytkownik.getOdlegloscOdPracy().getRating_3_minValue());
        rangeSliderOdlegloscOdPracy4.setMaximum(Swd_ahp.uzytkownik.getOdlegloscOdPracy().getRating_3_maxValue());
        rangeSliderOdlegloscOdPracy4.setValue(Swd_ahp.uzytkownik.getOdlegloscOdPracy().getRating_4_minValue());
        rangeSliderOdlegloscOdPracy4.setUpperValue(Swd_ahp.uzytkownik.getOdlegloscOdPracy().getRating_4_maxValue());
        
        rangeSliderOdlegloscOdPracy5.setMinimum(Swd_ahp.uzytkownik.getOdlegloscOdPracy().getRating_4_minValue());
        rangeSliderOdlegloscOdPracy5.setMaximum(Swd_ahp.uzytkownik.getOdlegloscOdPracy().getRating_4_maxValue());
        rangeSliderOdlegloscOdPracy5.setValue(Swd_ahp.uzytkownik.getOdlegloscOdPracy().getRating_5_minValue());
        rangeSliderOdlegloscOdPracy5.setUpperValue(Swd_ahp.uzytkownik.getOdlegloscOdPracy().getRating_5_maxValue());
        
        jLabelOdlegloscOdPracy2.setText(Swd_ahp.uzytkownik.getOdlegloscOdPracy().rating_2_toString());
        jLabelOdlegloscOdPracy3.setText(Swd_ahp.uzytkownik.getOdlegloscOdPracy().rating_3_toString());
        jLabelOdlegloscOdPracy4.setText(Swd_ahp.uzytkownik.getOdlegloscOdPracy().rating_4_toString());
        jLabelOdlegloscOdPracy5.setText(Swd_ahp.uzytkownik.getOdlegloscOdPracy().rating_5_toString());
        
        blokujZapisywaniePreferencji = false;
    }
    
    public void odswiezPreferencjeWielkosc() {
        blokujZapisywaniePreferencji = true;
        
        jSpinnerWielkoscMin.setValue(Swd_ahp.uzytkownik.getWielkosc().getRating_1_minValue());
        jSpinnerWielkoscMaks.setValue(Swd_ahp.uzytkownik.getWielkosc().getRating_1_maxValue());
        
        rangeSliderWielkosc2.setMinimum(Swd_ahp.uzytkownik.getWielkosc().getRating_1_minValue());
        rangeSliderWielkosc2.setMaximum(Swd_ahp.uzytkownik.getWielkosc().getRating_1_maxValue());
        rangeSliderWielkosc2.setValue(Swd_ahp.uzytkownik.getWielkosc().getRating_2_minValue());
        rangeSliderWielkosc2.setUpperValue(Swd_ahp.uzytkownik.getWielkosc().getRating_2_maxValue());
        
        rangeSliderWielkosc3.setMinimum(Swd_ahp.uzytkownik.getWielkosc().getRating_2_minValue());
        rangeSliderWielkosc3.setMaximum(Swd_ahp.uzytkownik.getWielkosc().getRating_2_maxValue());
        rangeSliderWielkosc3.setValue(Swd_ahp.uzytkownik.getWielkosc().getRating_3_minValue());
        rangeSliderWielkosc3.setUpperValue(Swd_ahp.uzytkownik.getWielkosc().getRating_3_maxValue());
        
        rangeSliderWielkosc4.setMinimum(Swd_ahp.uzytkownik.getWielkosc().getRating_3_minValue());
        rangeSliderWielkosc4.setMaximum(Swd_ahp.uzytkownik.getWielkosc().getRating_3_maxValue());
        rangeSliderWielkosc4.setValue(Swd_ahp.uzytkownik.getWielkosc().getRating_4_minValue());
        rangeSliderWielkosc4.setUpperValue(Swd_ahp.uzytkownik.getWielkosc().getRating_4_maxValue());
        
        rangeSliderWielkosc5.setMinimum(Swd_ahp.uzytkownik.getWielkosc().getRating_4_minValue());
        rangeSliderWielkosc5.setMaximum(Swd_ahp.uzytkownik.getWielkosc().getRating_4_maxValue());
        rangeSliderWielkosc5.setValue(Swd_ahp.uzytkownik.getWielkosc().getRating_5_minValue());
        rangeSliderWielkosc5.setUpperValue(Swd_ahp.uzytkownik.getWielkosc().getRating_5_maxValue());
        
        jLabelWielkosc2.setText(Swd_ahp.uzytkownik.getWielkosc().rating_2_toString());
        jLabelWielkosc3.setText(Swd_ahp.uzytkownik.getWielkosc().rating_3_toString());
        jLabelWielkosc4.setText(Swd_ahp.uzytkownik.getWielkosc().rating_4_toString());
        jLabelWielkosc5.setText(Swd_ahp.uzytkownik.getWielkosc().rating_5_toString());
        
        blokujZapisywaniePreferencji = false;
    }
    
    public void odswiezPreferencjePietro() {
        blokujZapisywaniePreferencji = true;
        
        jSpinnerPietroMin.setValue(Swd_ahp.uzytkownik.getPietro().getRating_1_minValue());
        jSpinnerPietroMaks.setValue(Swd_ahp.uzytkownik.getPietro().getRating_1_maxValue());
        
        rangeSliderPietro2.setMinimum(Swd_ahp.uzytkownik.getPietro().getRating_1_minValue());
        rangeSliderPietro2.setMaximum(Swd_ahp.uzytkownik.getPietro().getRating_1_maxValue());
        rangeSliderPietro2.setValue(Swd_ahp.uzytkownik.getPietro().getRating_2_minValue());
        rangeSliderPietro2.setUpperValue(Swd_ahp.uzytkownik.getPietro().getRating_2_maxValue());
        
        rangeSliderPietro3.setMinimum(Swd_ahp.uzytkownik.getPietro().getRating_2_minValue());
        rangeSliderPietro3.setMaximum(Swd_ahp.uzytkownik.getPietro().getRating_2_maxValue());
        rangeSliderPietro3.setValue(Swd_ahp.uzytkownik.getPietro().getRating_3_minValue());
        rangeSliderPietro3.setUpperValue(Swd_ahp.uzytkownik.getPietro().getRating_3_maxValue());
        
        rangeSliderPietro4.setMinimum(Swd_ahp.uzytkownik.getPietro().getRating_3_minValue());
        rangeSliderPietro4.setMaximum(Swd_ahp.uzytkownik.getPietro().getRating_3_maxValue());
        rangeSliderPietro4.setValue(Swd_ahp.uzytkownik.getPietro().getRating_4_minValue());
        rangeSliderPietro4.setUpperValue(Swd_ahp.uzytkownik.getPietro().getRating_4_maxValue());
        
        rangeSliderPietro5.setMinimum(Swd_ahp.uzytkownik.getPietro().getRating_4_minValue());
        rangeSliderPietro5.setMaximum(Swd_ahp.uzytkownik.getPietro().getRating_4_maxValue());
        rangeSliderPietro5.setValue(Swd_ahp.uzytkownik.getPietro().getRating_5_minValue());
        rangeSliderPietro5.setUpperValue(Swd_ahp.uzytkownik.getPietro().getRating_5_maxValue());
        
        jLabelPietro2.setText(Swd_ahp.uzytkownik.getPietro().rating_2_toString());
        jLabelPietro3.setText(Swd_ahp.uzytkownik.getPietro().rating_3_toString());
        jLabelPietro4.setText(Swd_ahp.uzytkownik.getPietro().rating_4_toString());
        jLabelPietro5.setText(Swd_ahp.uzytkownik.getPietro().rating_5_toString());
        
        blokujZapisywaniePreferencji = false;
    }
    
    public void odswiezPreferencjeLiczbaLiniiKomunikacjiMiejskiej() {
        blokujZapisywaniePreferencji = true;
        
        jSpinnerLiczbaLiniiKomunikacjiMiejskiejMin.setValue(Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().getRating_1_minValue());
        jSpinnerLiczbaLiniiKomunikacjiMiejskiejMaks.setValue(Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().getRating_1_maxValue());
        
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej2.setMinimum(Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().getRating_1_minValue());
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej2.setMaximum(Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().getRating_1_maxValue());
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej2.setValue(Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().getRating_2_minValue());
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej2.setUpperValue(Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().getRating_2_maxValue());
        
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej3.setMinimum(Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().getRating_2_minValue());
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej3.setMaximum(Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().getRating_2_maxValue());
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej3.setValue(Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().getRating_3_minValue());
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej3.setUpperValue(Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().getRating_3_maxValue());
        
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej4.setMinimum(Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().getRating_3_minValue());
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej4.setMaximum(Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().getRating_3_maxValue());
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej4.setValue(Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().getRating_4_minValue());
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej4.setUpperValue(Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().getRating_4_maxValue());
        
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej5.setMinimum(Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().getRating_4_minValue());
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej5.setMaximum(Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().getRating_4_maxValue());
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej5.setValue(Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().getRating_5_minValue());
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej5.setUpperValue(Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().getRating_5_maxValue());
        
        jLabelLiczbaLiniiKomunikacjiMiejskiej2.setText(Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().rating_2_toString());
        jLabelLiczbaLiniiKomunikacjiMiejskiej3.setText(Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().rating_3_toString());
        jLabelLiczbaLiniiKomunikacjiMiejskiej4.setText(Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().rating_4_toString());
        jLabelLiczbaLiniiKomunikacjiMiejskiej5.setText(Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().rating_5_toString());
        
        blokujZapisywaniePreferencji = false;
    }
    
    public void odswiezPreferencjeCena() {
        blokujZapisywaniePreferencji = true;
        
        jSpinnerCenaMin.setValue(Swd_ahp.uzytkownik.getCena().getRating_1_minValue());
        jSpinnerCenaMaks.setValue(Swd_ahp.uzytkownik.getCena().getRating_1_maxValue());
        
        rangeSliderCena2.setMinimum(Swd_ahp.uzytkownik.getCena().getRating_1_minValue());
        rangeSliderCena2.setMaximum(Swd_ahp.uzytkownik.getCena().getRating_1_maxValue());
        rangeSliderCena2.setValue(Swd_ahp.uzytkownik.getCena().getRating_2_minValue());
        rangeSliderCena2.setUpperValue(Swd_ahp.uzytkownik.getCena().getRating_2_maxValue());
        
        rangeSliderCena3.setMinimum(Swd_ahp.uzytkownik.getCena().getRating_2_minValue());
        rangeSliderCena3.setMaximum(Swd_ahp.uzytkownik.getCena().getRating_2_maxValue());
        rangeSliderCena3.setValue(Swd_ahp.uzytkownik.getCena().getRating_3_minValue());
        rangeSliderCena3.setUpperValue(Swd_ahp.uzytkownik.getCena().getRating_3_maxValue());
        
        rangeSliderCena4.setMinimum(Swd_ahp.uzytkownik.getCena().getRating_3_minValue());
        rangeSliderCena4.setMaximum(Swd_ahp.uzytkownik.getCena().getRating_3_maxValue());
        rangeSliderCena4.setValue(Swd_ahp.uzytkownik.getCena().getRating_4_minValue());
        rangeSliderCena4.setUpperValue(Swd_ahp.uzytkownik.getCena().getRating_4_maxValue());
        
        rangeSliderCena5.setMinimum(Swd_ahp.uzytkownik.getCena().getRating_4_minValue());
        rangeSliderCena5.setMaximum(Swd_ahp.uzytkownik.getCena().getRating_4_maxValue());
        rangeSliderCena5.setValue(Swd_ahp.uzytkownik.getCena().getRating_5_minValue());
        rangeSliderCena5.setUpperValue(Swd_ahp.uzytkownik.getCena().getRating_5_maxValue());
        
        jLabelCena2.setText(Swd_ahp.uzytkownik.getCena().rating_2_toString());
        jLabelCena3.setText(Swd_ahp.uzytkownik.getCena().rating_3_toString());
        jLabelCena4.setText(Swd_ahp.uzytkownik.getCena().rating_4_toString());
        jLabelCena5.setText(Swd_ahp.uzytkownik.getCena().rating_5_toString());
        
        blokujZapisywaniePreferencji = false;
    }
    
    public void odswiezPreferencjeWaznoscKryteriow() {
        blokujZapisywaniePreferencji = true;
        
        jComboBoxWaznoscCena.setSelectedItem(Swd_ahp.uzytkownik.getWaznoscCena());
        jComboBoxWaznoscLiczbaLiniiKomunikacjiMiejskiej.setSelectedItem(Swd_ahp.uzytkownik.getWaznoscLiczbaLiniiKomunikacjiMiejskiej());
        jComboBoxWaznoscOcena.setSelectedItem(Swd_ahp.uzytkownik.getWaznoscSubiektywnaOcena());
        jComboBoxWaznoscOdlegloscOdCentrum.setSelectedItem(Swd_ahp.uzytkownik.getOdlegloscOdCentrum());
        jComboBoxWaznoscOdlegloscOdPracy.setSelectedItem(Swd_ahp.uzytkownik.getWaznoscOdlegloscOdPracy());
        jComboBoxWaznoscPietro.setSelectedItem(Swd_ahp.uzytkownik.getWaznoscPietro());
        jComboBoxWaznoscWielkosc.setSelectedItem(Swd_ahp.uzytkownik.getWaznoscWielkosc());
        
        blokujZapisywaniePreferencji = false;
    }
    
    public void odswiezPreferencje() {
        odswiezPreferencjeCena();
        odswiezPreferencjeLiczbaLiniiKomunikacjiMiejskiej();
        odswiezPreferencjeOdlegloscOdCentrum();
        odswiezPreferencjeOdlegloscOdPracy();
        odswiezPreferencjePietro();
        odswiezPreferencjeWielkosc();
        odswiezPreferencjeWaznoscKryteriow();
    }
    
    public void zapiszPreferencje() {
        if (!blokujZapisywaniePreferencji) {
            Swd_ahp.uzytkownik.getOdlegloscOdCentrum().setRating_1_minValue((int) jSpinnerOdlegloscOdCentrumMin.getValue());
            Swd_ahp.uzytkownik.getOdlegloscOdCentrum().setRating_1_maxValue((int) jSpinnerOdlegloscOdCentrumMaks.getValue());
            Swd_ahp.uzytkownik.getOdlegloscOdCentrum().setRating_2_minValue(rangeSliderOdlegloscOdCentrum2.getValue());
            Swd_ahp.uzytkownik.getOdlegloscOdCentrum().setRating_2_maxValue(rangeSliderOdlegloscOdCentrum2.getUpperValue());
            Swd_ahp.uzytkownik.getOdlegloscOdCentrum().setRating_3_minValue(rangeSliderOdlegloscOdCentrum3.getValue());
            Swd_ahp.uzytkownik.getOdlegloscOdCentrum().setRating_3_maxValue(rangeSliderOdlegloscOdCentrum3.getUpperValue());
            Swd_ahp.uzytkownik.getOdlegloscOdCentrum().setRating_4_minValue(rangeSliderOdlegloscOdCentrum4.getValue());
            Swd_ahp.uzytkownik.getOdlegloscOdCentrum().setRating_4_maxValue(rangeSliderOdlegloscOdCentrum4.getUpperValue());
            Swd_ahp.uzytkownik.getOdlegloscOdCentrum().setRating_5_minValue(rangeSliderOdlegloscOdCentrum5.getValue());
            Swd_ahp.uzytkownik.getOdlegloscOdCentrum().setRating_5_maxValue(rangeSliderOdlegloscOdCentrum5.getUpperValue());

            Swd_ahp.uzytkownik.getOdlegloscOdPracy().setRating_1_minValue((int) jSpinnerOdlegloscOdPracyMin.getValue());
            Swd_ahp.uzytkownik.getOdlegloscOdPracy().setRating_1_maxValue((int) jSpinnerOdlegloscOdPracyMaks.getValue());
            Swd_ahp.uzytkownik.getOdlegloscOdPracy().setRating_2_minValue(rangeSliderOdlegloscOdPracy2.getValue());
            Swd_ahp.uzytkownik.getOdlegloscOdPracy().setRating_2_maxValue(rangeSliderOdlegloscOdPracy2.getUpperValue());
            Swd_ahp.uzytkownik.getOdlegloscOdPracy().setRating_3_minValue(rangeSliderOdlegloscOdPracy3.getValue());
            Swd_ahp.uzytkownik.getOdlegloscOdPracy().setRating_3_maxValue(rangeSliderOdlegloscOdPracy3.getUpperValue());
            Swd_ahp.uzytkownik.getOdlegloscOdPracy().setRating_4_minValue(rangeSliderOdlegloscOdPracy4.getValue());
            Swd_ahp.uzytkownik.getOdlegloscOdPracy().setRating_4_maxValue(rangeSliderOdlegloscOdPracy4.getUpperValue());
            Swd_ahp.uzytkownik.getOdlegloscOdPracy().setRating_5_minValue(rangeSliderOdlegloscOdPracy5.getValue());
            Swd_ahp.uzytkownik.getOdlegloscOdPracy().setRating_5_maxValue(rangeSliderOdlegloscOdPracy5.getUpperValue());

            Swd_ahp.uzytkownik.getWielkosc().setRating_1_minValue((int) jSpinnerWielkoscMin.getValue());
            Swd_ahp.uzytkownik.getWielkosc().setRating_1_maxValue((int) jSpinnerWielkoscMaks.getValue());
            Swd_ahp.uzytkownik.getWielkosc().setRating_2_minValue(rangeSliderWielkosc2.getValue());
            Swd_ahp.uzytkownik.getWielkosc().setRating_2_maxValue(rangeSliderWielkosc2.getUpperValue());
            Swd_ahp.uzytkownik.getWielkosc().setRating_3_minValue(rangeSliderWielkosc3.getValue());
            Swd_ahp.uzytkownik.getWielkosc().setRating_3_maxValue(rangeSliderWielkosc3.getUpperValue());
            Swd_ahp.uzytkownik.getWielkosc().setRating_4_minValue(rangeSliderWielkosc4.getValue());
            Swd_ahp.uzytkownik.getWielkosc().setRating_4_maxValue(rangeSliderWielkosc4.getUpperValue());
            Swd_ahp.uzytkownik.getWielkosc().setRating_5_minValue(rangeSliderWielkosc5.getValue());
            Swd_ahp.uzytkownik.getWielkosc().setRating_5_maxValue(rangeSliderWielkosc5.getUpperValue());

            Swd_ahp.uzytkownik.getPietro().setRating_1_minValue((int) jSpinnerPietroMin.getValue());
            Swd_ahp.uzytkownik.getPietro().setRating_1_maxValue((int) jSpinnerPietroMaks.getValue());
            Swd_ahp.uzytkownik.getPietro().setRating_2_minValue(rangeSliderPietro2.getValue());
            Swd_ahp.uzytkownik.getPietro().setRating_2_maxValue(rangeSliderPietro2.getUpperValue());
            Swd_ahp.uzytkownik.getPietro().setRating_3_minValue(rangeSliderPietro3.getValue());
            Swd_ahp.uzytkownik.getPietro().setRating_3_maxValue(rangeSliderPietro3.getUpperValue());
            Swd_ahp.uzytkownik.getPietro().setRating_4_minValue(rangeSliderPietro4.getValue());
            Swd_ahp.uzytkownik.getPietro().setRating_4_maxValue(rangeSliderPietro4.getUpperValue());
            Swd_ahp.uzytkownik.getPietro().setRating_5_minValue(rangeSliderPietro5.getValue());
            Swd_ahp.uzytkownik.getPietro().setRating_5_maxValue(rangeSliderPietro5.getUpperValue());

            Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().setRating_1_minValue((int) jSpinnerLiczbaLiniiKomunikacjiMiejskiejMin.getValue());
            Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().setRating_1_maxValue((int) jSpinnerLiczbaLiniiKomunikacjiMiejskiejMaks.getValue());
            Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().setRating_2_minValue(rangeSliderLiczbaLiniiKomunikacjiMiejskiej2.getValue());
            Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().setRating_2_maxValue(rangeSliderLiczbaLiniiKomunikacjiMiejskiej2.getUpperValue());
            Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().setRating_3_minValue(rangeSliderLiczbaLiniiKomunikacjiMiejskiej3.getValue());
            Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().setRating_3_maxValue(rangeSliderLiczbaLiniiKomunikacjiMiejskiej3.getUpperValue());
            Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().setRating_4_minValue(rangeSliderLiczbaLiniiKomunikacjiMiejskiej4.getValue());
            Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().setRating_4_maxValue(rangeSliderLiczbaLiniiKomunikacjiMiejskiej4.getUpperValue());
            Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().setRating_5_minValue(rangeSliderLiczbaLiniiKomunikacjiMiejskiej5.getValue());
            Swd_ahp.uzytkownik.getLiczbaLiniiKomunikacjiMiejskiej().setRating_5_maxValue(rangeSliderLiczbaLiniiKomunikacjiMiejskiej5.getUpperValue());

            Swd_ahp.uzytkownik.getCena().setRating_1_minValue((int) jSpinnerCenaMin.getValue());
            Swd_ahp.uzytkownik.getCena().setRating_1_maxValue((int) jSpinnerCenaMaks.getValue());
            Swd_ahp.uzytkownik.getCena().setRating_2_minValue(rangeSliderCena2.getValue());
            Swd_ahp.uzytkownik.getCena().setRating_2_maxValue(rangeSliderCena2.getUpperValue());
            Swd_ahp.uzytkownik.getCena().setRating_3_minValue(rangeSliderCena3.getValue());
            Swd_ahp.uzytkownik.getCena().setRating_3_maxValue(rangeSliderCena3.getUpperValue());
            Swd_ahp.uzytkownik.getCena().setRating_4_minValue(rangeSliderCena4.getValue());
            Swd_ahp.uzytkownik.getCena().setRating_4_maxValue(rangeSliderCena4.getUpperValue());
            Swd_ahp.uzytkownik.getCena().setRating_5_minValue(rangeSliderCena5.getValue());
            Swd_ahp.uzytkownik.getCena().setRating_5_maxValue(rangeSliderCena5.getUpperValue());
            
            Swd_ahp.uzytkownik.setWaznoscCena((Ocena) jComboBoxWaznoscCena.getSelectedItem());
            Swd_ahp.uzytkownik.setWaznoscLiczbaLiniiKomunikacjiMiejskiej((Ocena) jComboBoxWaznoscLiczbaLiniiKomunikacjiMiejskiej.getSelectedItem());
            Swd_ahp.uzytkownik.setWaznoscOdlegloscOdCentrum((Ocena) jComboBoxWaznoscOdlegloscOdCentrum.getSelectedItem());
            Swd_ahp.uzytkownik.setWaznoscOdlegloscOdPracy((Ocena) jComboBoxWaznoscOdlegloscOdPracy.getSelectedItem());
            Swd_ahp.uzytkownik.setWaznoscPietro((Ocena) jComboBoxWaznoscPietro.getSelectedItem());
            Swd_ahp.uzytkownik.setWaznoscSubiektywnaOcena((Ocena) jComboBoxWaznoscOcena.getSelectedItem());
            Swd_ahp.uzytkownik.setWaznoscWielkosc((Ocena) jComboBoxWaznoscWielkosc.getSelectedItem());
        }
            odswiezPreferencje();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaLog = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldDaneMieszkaniaNazwa = new javax.swing.JTextField();
        jSpinnerDaneMieszkaniaOdlegloscOdCentrum = new javax.swing.JSpinner();
        jSpinnerDaneMieszkaniaWielkosc = new javax.swing.JSpinner();
        jSpinnerDaneMieszkaniaPietro = new javax.swing.JSpinner();
        jSpinnerDaneMieszkaniaLiczbaLiniiKomunikacjiMiejskiej = new javax.swing.JSpinner();
        jSpinnerDaneMieszkaniaCena = new javax.swing.JSpinner();
        jSpinnerDaneMieszkaniaOdlegloscOdPracy = new javax.swing.JSpinner();
        jComboBoxDaneMieszkaniaOcenaUzytkownika = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListMieszkania = new javax.swing.JList();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelOdlegloscOdCentrum = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jSpinnerOdlegloscOdCentrumMin = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        jSpinnerOdlegloscOdCentrumMaks = new javax.swing.JSpinner();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        rangeSliderOdlegloscOdCentrum2 = new slider.RangeSlider();
        jLabelOdlegloscOdCentrum2 = new javax.swing.JLabel();
        rangeSliderOdlegloscOdCentrum3 = new slider.RangeSlider();
        rangeSliderOdlegloscOdCentrum5 = new slider.RangeSlider();
        rangeSliderOdlegloscOdCentrum4 = new slider.RangeSlider();
        jLabelOdlegloscOdCentrum4 = new javax.swing.JLabel();
        jLabelOdlegloscOdCentrum3 = new javax.swing.JLabel();
        jLabelOdlegloscOdCentrum5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jSpinnerWielkoscMin = new javax.swing.JSpinner();
        jSpinnerWielkoscMaks = new javax.swing.JSpinner();
        jLabel31 = new javax.swing.JLabel();
        rangeSliderWielkosc2 = new slider.RangeSlider();
        rangeSliderWielkosc3 = new slider.RangeSlider();
        rangeSliderWielkosc4 = new slider.RangeSlider();
        rangeSliderWielkosc5 = new slider.RangeSlider();
        jLabelWielkosc5 = new javax.swing.JLabel();
        jLabelWielkosc4 = new javax.swing.JLabel();
        jLabelWielkosc3 = new javax.swing.JLabel();
        jLabelWielkosc2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jSpinnerPietroMin = new javax.swing.JSpinner();
        jSpinnerPietroMaks = new javax.swing.JSpinner();
        jLabel37 = new javax.swing.JLabel();
        rangeSliderPietro2 = new slider.RangeSlider();
        rangeSliderPietro3 = new slider.RangeSlider();
        rangeSliderPietro4 = new slider.RangeSlider();
        rangeSliderPietro5 = new slider.RangeSlider();
        jLabelPietro5 = new javax.swing.JLabel();
        jLabelPietro4 = new javax.swing.JLabel();
        jLabelPietro3 = new javax.swing.JLabel();
        jLabelPietro2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jSpinnerLiczbaLiniiKomunikacjiMiejskiejMin = new javax.swing.JSpinner();
        jSpinnerLiczbaLiniiKomunikacjiMiejskiejMaks = new javax.swing.JSpinner();
        jLabel43 = new javax.swing.JLabel();
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej2 = new slider.RangeSlider();
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej3 = new slider.RangeSlider();
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej4 = new slider.RangeSlider();
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej5 = new slider.RangeSlider();
        jLabelLiczbaLiniiKomunikacjiMiejskiej5 = new javax.swing.JLabel();
        jLabelLiczbaLiniiKomunikacjiMiejskiej4 = new javax.swing.JLabel();
        jLabelLiczbaLiniiKomunikacjiMiejskiej3 = new javax.swing.JLabel();
        jLabelLiczbaLiniiKomunikacjiMiejskiej2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jSpinnerCenaMin = new javax.swing.JSpinner();
        jSpinnerCenaMaks = new javax.swing.JSpinner();
        jLabel49 = new javax.swing.JLabel();
        rangeSliderCena2 = new slider.RangeSlider();
        rangeSliderCena3 = new slider.RangeSlider();
        rangeSliderCena4 = new slider.RangeSlider();
        rangeSliderCena5 = new slider.RangeSlider();
        jLabelCena5 = new javax.swing.JLabel();
        jLabelCena4 = new javax.swing.JLabel();
        jLabelCena3 = new javax.swing.JLabel();
        jLabelCena2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jSpinnerOdlegloscOdPracyMin = new javax.swing.JSpinner();
        jSpinnerOdlegloscOdPracyMaks = new javax.swing.JSpinner();
        jLabel55 = new javax.swing.JLabel();
        rangeSliderOdlegloscOdPracy2 = new slider.RangeSlider();
        rangeSliderOdlegloscOdPracy3 = new slider.RangeSlider();
        rangeSliderOdlegloscOdPracy4 = new slider.RangeSlider();
        rangeSliderOdlegloscOdPracy5 = new slider.RangeSlider();
        jLabelOdlegloscOdPracy5 = new javax.swing.JLabel();
        jLabelOdlegloscOdPracy4 = new javax.swing.JLabel();
        jLabelOdlegloscOdPracy3 = new javax.swing.JLabel();
        jLabelOdlegloscOdPracy2 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jComboBoxWaznoscOdlegloscOdCentrum = new javax.swing.JComboBox();
        jComboBoxWaznoscWielkosc = new javax.swing.JComboBox();
        jComboBoxWaznoscPietro = new javax.swing.JComboBox();
        jComboBoxWaznoscLiczbaLiniiKomunikacjiMiejskiej = new javax.swing.JComboBox();
        jComboBoxWaznoscOdlegloscOdPracy = new javax.swing.JComboBox();
        jComboBoxWaznoscOcena = new javax.swing.JComboBox();
        jLabel56 = new javax.swing.JLabel();
        jComboBoxWaznoscCena = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListUporzadkowaneMieszkania = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("System wspomagania decyzji wyboru mieszkania do wynajmu z wykorzystaniem metody AHP");

        jButton1.setText("Dodaj nowe mieszkanie");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Zapisz dane mieszkania");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Usuń mieszkanie");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Uruchom analizę preferencji");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Zapisz preferencje użytkownika");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Odczytaj ponownie preferencje użytkownika");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTextAreaLog.setEditable(false);
        jTextAreaLog.setColumns(20);
        jTextAreaLog.setRows(5);
        jScrollPane4.setViewportView(jTextAreaLog);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addGap(13, 13, 13)
                .addComponent(jButton3)
                .addGap(22, 22, 22)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setText("Nazwa mieszkania:");

        jLabel2.setText("Odległość od centrum miasta [m]:");

        jLabel3.setText("Wielkość mieszkania [m2]:");

        jLabel4.setText("Piętro:");

        jLabel5.setText("Liczba linii komun. miej. w odl. 500m:");

        jLabel6.setText("Opłata miesięczna za wynajem:");

        jLabel7.setText("Odległość od miejsca pracy:");

        jLabel8.setText("Subiektywna ocena użytkownika:");

        jTextFieldDaneMieszkaniaNazwa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDaneMieszkaniaNazwaActionPerformed(evt);
            }
        });

        jComboBoxDaneMieszkaniaOcenaUzytkownika.setName(""); // NOI18N
        jComboBoxDaneMieszkaniaOcenaUzytkownika.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDaneMieszkaniaOcenaUzytkownikaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Dane mieszkania");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxDaneMieszkaniaOcenaUzytkownika, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSpinnerDaneMieszkaniaCena, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSpinnerDaneMieszkaniaLiczbaLiniiKomunikacjiMiejskiej, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSpinnerDaneMieszkaniaOdlegloscOdPracy, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSpinnerDaneMieszkaniaPietro, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 80, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSpinnerDaneMieszkaniaOdlegloscOdCentrum, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSpinnerDaneMieszkaniaWielkosc, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextFieldDaneMieszkaniaNazwa))))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldDaneMieszkaniaNazwa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jSpinnerDaneMieszkaniaOdlegloscOdCentrum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jSpinnerDaneMieszkaniaWielkosc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jSpinnerDaneMieszkaniaPietro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jSpinnerDaneMieszkaniaLiczbaLiniiKomunikacjiMiejskiej, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jSpinnerDaneMieszkaniaCena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jSpinnerDaneMieszkaniaOdlegloscOdPracy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBoxDaneMieszkaniaOcenaUzytkownika, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jListMieszkania.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jListMieszkania.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListMieszkania.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListMieszkaniaValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jListMieszkania);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Lista wszystkich mieszkań");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Preferencje");

        jLabel13.setText("Dopuszczalny zakres [m]: od");

        jSpinnerOdlegloscOdCentrumMin.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000000, 1));
        jSpinnerOdlegloscOdCentrumMin.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerOdlegloscOdCentrumMinStateChanged(evt);
            }
        });

        jLabel14.setText("do");

        jSpinnerOdlegloscOdCentrumMaks.setModel(new javax.swing.SpinnerNumberModel(1000000, 0, 1000000, 1));
        jSpinnerOdlegloscOdCentrumMaks.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerOdlegloscOdCentrumMaksStateChanged(evt);
            }
        });

        jLabel15.setText("Wartości nieznacznie preferowane:");

        jLabel16.setText("Wartości silnie preferowane:");

        jLabel17.setText("Wartości bardzo silnie preferowane:");

        jLabel18.setText("Wartości wyjątkowo preferowane:");

        rangeSliderOdlegloscOdCentrum2.setMaximum(1000000);
        rangeSliderOdlegloscOdCentrum2.setToolTipText("");
        rangeSliderOdlegloscOdCentrum2.setValue(0);
        rangeSliderOdlegloscOdCentrum2.setUpperValue(1000000);
        rangeSliderOdlegloscOdCentrum2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderOdlegloscOdCentrum2StateChanged(evt);
            }
        });

        jLabelOdlegloscOdCentrum2.setText("0-10000");

        rangeSliderOdlegloscOdCentrum3.setMaximum(1000000);
        rangeSliderOdlegloscOdCentrum3.setValue(0);
        rangeSliderOdlegloscOdCentrum3.setUpperValue(1000000);
        rangeSliderOdlegloscOdCentrum3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderOdlegloscOdCentrum3StateChanged(evt);
            }
        });

        rangeSliderOdlegloscOdCentrum5.setMaximum(1000000);
        rangeSliderOdlegloscOdCentrum5.setValue(0);
        rangeSliderOdlegloscOdCentrum5.setUpperValue(1000000);
        rangeSliderOdlegloscOdCentrum5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderOdlegloscOdCentrum5StateChanged(evt);
            }
        });

        rangeSliderOdlegloscOdCentrum4.setMaximum(1000000);
        rangeSliderOdlegloscOdCentrum4.setValue(0);
        rangeSliderOdlegloscOdCentrum4.setUpperValue(1000000);
        rangeSliderOdlegloscOdCentrum4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderOdlegloscOdCentrum4StateChanged(evt);
            }
        });

        jLabelOdlegloscOdCentrum4.setText("0-10000");

        jLabelOdlegloscOdCentrum3.setText("0-10000");

        jLabelOdlegloscOdCentrum5.setText("0-10000");

        javax.swing.GroupLayout jPanelOdlegloscOdCentrumLayout = new javax.swing.GroupLayout(jPanelOdlegloscOdCentrum);
        jPanelOdlegloscOdCentrum.setLayout(jPanelOdlegloscOdCentrumLayout);
        jPanelOdlegloscOdCentrumLayout.setHorizontalGroup(
            jPanelOdlegloscOdCentrumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOdlegloscOdCentrumLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelOdlegloscOdCentrumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelOdlegloscOdCentrumLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerOdlegloscOdCentrumMin, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerOdlegloscOdCentrumMaks, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelOdlegloscOdCentrumLayout.createSequentialGroup()
                        .addGroup(jPanelOdlegloscOdCentrumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelOdlegloscOdCentrumLayout.createSequentialGroup()
                                .addGroup(jPanelOdlegloscOdCentrumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                                .addGap(39, 39, 39)
                                .addGroup(jPanelOdlegloscOdCentrumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rangeSliderOdlegloscOdCentrum3, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                                    .addComponent(rangeSliderOdlegloscOdCentrum2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanelOdlegloscOdCentrumLayout.createSequentialGroup()
                                .addGroup(jPanelOdlegloscOdCentrumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(jPanelOdlegloscOdCentrumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rangeSliderOdlegloscOdCentrum4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rangeSliderOdlegloscOdCentrum5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(21, 21, 21)))
                .addGroup(jPanelOdlegloscOdCentrumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelOdlegloscOdCentrum5, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(jLabelOdlegloscOdCentrum4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelOdlegloscOdCentrum3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelOdlegloscOdCentrum2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelOdlegloscOdCentrumLayout.setVerticalGroup(
            jPanelOdlegloscOdCentrumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOdlegloscOdCentrumLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelOdlegloscOdCentrumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jSpinnerOdlegloscOdCentrumMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jSpinnerOdlegloscOdCentrumMaks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelOdlegloscOdCentrumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rangeSliderOdlegloscOdCentrum3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelOdlegloscOdCentrumLayout.createSequentialGroup()
                        .addGroup(jPanelOdlegloscOdCentrumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelOdlegloscOdCentrumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(rangeSliderOdlegloscOdCentrum2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15))
                            .addComponent(jLabelOdlegloscOdCentrum2))
                        .addGroup(jPanelOdlegloscOdCentrumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelOdlegloscOdCentrumLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel16))
                            .addGroup(jPanelOdlegloscOdCentrumLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabelOdlegloscOdCentrum3)))))
                .addGap(11, 11, 11)
                .addGroup(jPanelOdlegloscOdCentrumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rangeSliderOdlegloscOdCentrum4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelOdlegloscOdCentrum4)
                    .addComponent(jLabel17))
                .addGroup(jPanelOdlegloscOdCentrumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelOdlegloscOdCentrumLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanelOdlegloscOdCentrumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rangeSliderOdlegloscOdCentrum5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelOdlegloscOdCentrum5)))
                    .addGroup(jPanelOdlegloscOdCentrumLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Odległość od centrum", jPanelOdlegloscOdCentrum);

        jLabel19.setText("Wartości wyjątkowo preferowane:");

        jLabel20.setText("Wartości bardzo silnie preferowane:");

        jLabel21.setText("Wartości silnie preferowane:");

        jLabel22.setText("Wartości nieznacznie preferowane:");

        jLabel30.setText("Dopuszczalny zakres [m2]: od");

        jSpinnerWielkoscMin.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000000, 1));
        jSpinnerWielkoscMin.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerWielkoscMinStateChanged(evt);
            }
        });

        jSpinnerWielkoscMaks.setModel(new javax.swing.SpinnerNumberModel(1000000, 0, 1000000, 1));
        jSpinnerWielkoscMaks.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerWielkoscMaksStateChanged(evt);
            }
        });

        jLabel31.setText("do");

        rangeSliderWielkosc2.setMaximum(1000000);
        rangeSliderWielkosc2.setToolTipText("");
        rangeSliderWielkosc2.setValue(0);
        rangeSliderWielkosc2.setUpperValue(1000000);
        rangeSliderWielkosc2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderWielkosc2StateChanged(evt);
            }
        });

        rangeSliderWielkosc3.setMaximum(1000000);
        rangeSliderWielkosc3.setValue(0);
        rangeSliderWielkosc3.setUpperValue(1000000);
        rangeSliderWielkosc3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderWielkosc3StateChanged(evt);
            }
        });

        rangeSliderWielkosc4.setMaximum(1000000);
        rangeSliderWielkosc4.setValue(0);
        rangeSliderWielkosc4.setUpperValue(1000000);
        rangeSliderWielkosc4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderWielkosc4StateChanged(evt);
            }
        });

        rangeSliderWielkosc5.setMaximum(1000000);
        rangeSliderWielkosc5.setValue(0);
        rangeSliderWielkosc5.setUpperValue(1000000);
        rangeSliderWielkosc5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderWielkosc5StateChanged(evt);
            }
        });

        jLabelWielkosc5.setText("0-10000");

        jLabelWielkosc4.setText("0-10000");

        jLabelWielkosc3.setText("0-10000");

        jLabelWielkosc2.setText("0-10000");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rangeSliderWielkosc3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rangeSliderWielkosc2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerWielkoscMin, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerWielkoscMaks, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 358, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rangeSliderWielkosc4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rangeSliderWielkosc5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelWielkosc5, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(jLabelWielkosc4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelWielkosc3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelWielkosc2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jSpinnerWielkoscMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(jSpinnerWielkoscMaks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rangeSliderWielkosc3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(rangeSliderWielkosc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel22))
                            .addComponent(jLabelWielkosc2))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel21))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabelWielkosc3)))))
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel20)
                        .addComponent(rangeSliderWielkosc4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelWielkosc4))
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(rangeSliderWielkosc5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelWielkosc5))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Wielkość mieszkania", jPanel5);

        jLabel32.setText("Wartości wyjątkowo preferowane:");

        jLabel33.setText("Wartości bardzo silnie preferowane:");

        jLabel34.setText("Wartości silnie preferowane:");

        jLabel35.setText("Wartości nieznacznie preferowane:");

        jLabel36.setText("Dopuszczalny zakres: od");

        jSpinnerPietroMin.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000000, 1));
        jSpinnerPietroMin.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerPietroMinStateChanged(evt);
            }
        });

        jSpinnerPietroMaks.setModel(new javax.swing.SpinnerNumberModel(1000000, 0, 1000000, 1));
        jSpinnerPietroMaks.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerPietroMaksStateChanged(evt);
            }
        });

        jLabel37.setText("do");

        rangeSliderPietro2.setMaximum(1000000);
        rangeSliderPietro2.setToolTipText("");
        rangeSliderPietro2.setValue(0);
        rangeSliderPietro2.setUpperValue(1000000);
        rangeSliderPietro2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderPietro2StateChanged(evt);
            }
        });

        rangeSliderPietro3.setMaximum(1000000);
        rangeSliderPietro3.setValue(0);
        rangeSliderPietro3.setUpperValue(1000000);
        rangeSliderPietro3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderPietro3StateChanged(evt);
            }
        });

        rangeSliderPietro4.setMaximum(1000000);
        rangeSliderPietro4.setValue(0);
        rangeSliderPietro4.setUpperValue(1000000);
        rangeSliderPietro4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderPietro4StateChanged(evt);
            }
        });

        rangeSliderPietro5.setMaximum(1000000);
        rangeSliderPietro5.setValue(0);
        rangeSliderPietro5.setUpperValue(1000000);
        rangeSliderPietro5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderPietro5StateChanged(evt);
            }
        });

        jLabelPietro5.setText("0-10000");

        jLabelPietro4.setText("0-10000");

        jLabelPietro3.setText("0-10000");

        jLabelPietro2.setText("0-10000");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rangeSliderPietro3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rangeSliderPietro2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerPietroMin, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerPietroMaks, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 383, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rangeSliderPietro4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rangeSliderPietro5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelPietro5, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(jLabelPietro4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelPietro3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelPietro2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jSpinnerPietroMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(jSpinnerPietroMaks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rangeSliderPietro3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(rangeSliderPietro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel35))
                            .addComponent(jLabelPietro2))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel34))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabelPietro3)))))
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel33)
                        .addComponent(rangeSliderPietro4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelPietro4))
                .addGap(13, 13, 13)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addComponent(rangeSliderPietro5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPietro5))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Piętro", jPanel6);

        jLabel38.setText("Wartości wyjątkowo preferowane:");

        jLabel39.setText("Wartości bardzo silnie preferowane:");

        jLabel40.setText("Wartości silnie preferowane:");

        jLabel41.setText("Wartości nieznacznie preferowane:");

        jLabel42.setText("Dopuszczalny zakres: od");

        jSpinnerLiczbaLiniiKomunikacjiMiejskiejMin.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000000, 1));
        jSpinnerLiczbaLiniiKomunikacjiMiejskiejMin.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerLiczbaLiniiKomunikacjiMiejskiejMinStateChanged(evt);
            }
        });

        jSpinnerLiczbaLiniiKomunikacjiMiejskiejMaks.setModel(new javax.swing.SpinnerNumberModel(1000000, 0, 1000000, 1));
        jSpinnerLiczbaLiniiKomunikacjiMiejskiejMaks.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerLiczbaLiniiKomunikacjiMiejskiejMaksStateChanged(evt);
            }
        });

        jLabel43.setText("do");

        rangeSliderLiczbaLiniiKomunikacjiMiejskiej2.setMaximum(1000000);
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej2.setToolTipText("");
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej2.setValue(0);
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej2.setUpperValue(1000000);
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderLiczbaLiniiKomunikacjiMiejskiej2StateChanged(evt);
            }
        });

        rangeSliderLiczbaLiniiKomunikacjiMiejskiej3.setMaximum(1000000);
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej3.setValue(0);
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej3.setUpperValue(1000000);
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderLiczbaLiniiKomunikacjiMiejskiej3StateChanged(evt);
            }
        });

        rangeSliderLiczbaLiniiKomunikacjiMiejskiej4.setMaximum(1000000);
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej4.setValue(0);
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej4.setUpperValue(1000000);
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderLiczbaLiniiKomunikacjiMiejskiej4StateChanged(evt);
            }
        });

        rangeSliderLiczbaLiniiKomunikacjiMiejskiej5.setMaximum(1000000);
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej5.setValue(0);
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej5.setUpperValue(1000000);
        rangeSliderLiczbaLiniiKomunikacjiMiejskiej5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderLiczbaLiniiKomunikacjiMiejskiej5StateChanged(evt);
            }
        });

        jLabelLiczbaLiniiKomunikacjiMiejskiej5.setText("0-10000");

        jLabelLiczbaLiniiKomunikacjiMiejskiej4.setText("0-10000");

        jLabelLiczbaLiniiKomunikacjiMiejskiej3.setText("0-10000");

        jLabelLiczbaLiniiKomunikacjiMiejskiej2.setText("0-10000");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rangeSliderLiczbaLiniiKomunikacjiMiejskiej3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rangeSliderLiczbaLiniiKomunikacjiMiejskiej2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerLiczbaLiniiKomunikacjiMiejskiejMin, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerLiczbaLiniiKomunikacjiMiejskiejMaks, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 383, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rangeSliderLiczbaLiniiKomunikacjiMiejskiej4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rangeSliderLiczbaLiniiKomunikacjiMiejskiej5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLiczbaLiniiKomunikacjiMiejskiej5, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(jLabelLiczbaLiniiKomunikacjiMiejskiej4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelLiczbaLiniiKomunikacjiMiejskiej3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelLiczbaLiniiKomunikacjiMiejskiej2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jSpinnerLiczbaLiniiKomunikacjiMiejskiejMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43)
                    .addComponent(jSpinnerLiczbaLiniiKomunikacjiMiejskiejMaks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rangeSliderLiczbaLiniiKomunikacjiMiejskiej3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(rangeSliderLiczbaLiniiKomunikacjiMiejskiej2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel41))
                            .addComponent(jLabelLiczbaLiniiKomunikacjiMiejskiej2))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel40))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabelLiczbaLiniiKomunikacjiMiejskiej3)))))
                .addGap(11, 11, 11)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel39)
                        .addComponent(rangeSliderLiczbaLiniiKomunikacjiMiejskiej4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelLiczbaLiniiKomunikacjiMiejskiej4))
                .addGap(13, 13, 13)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addComponent(rangeSliderLiczbaLiniiKomunikacjiMiejskiej5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLiczbaLiniiKomunikacjiMiejskiej5))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Liczba linii kom. miejsk.", jPanel7);

        jLabel44.setText("Wartości wyjątkowo preferowane:");

        jLabel45.setText("Wartości bardzo silnie preferowane:");

        jLabel46.setText("Wartości silnie preferowane:");

        jLabel47.setText("Wartości nieznacznie preferowane:");

        jLabel48.setText("Dopuszczalny zakres [zł]: od");

        jSpinnerCenaMin.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000000, 1));
        jSpinnerCenaMin.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerCenaMinStateChanged(evt);
            }
        });

        jSpinnerCenaMaks.setModel(new javax.swing.SpinnerNumberModel(1000000, 0, 1000000, 1));
        jSpinnerCenaMaks.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerCenaMaksStateChanged(evt);
            }
        });

        jLabel49.setText("do");

        rangeSliderCena2.setMaximum(1000000);
        rangeSliderCena2.setToolTipText("");
        rangeSliderCena2.setValue(0);
        rangeSliderCena2.setUpperValue(1000000);
        rangeSliderCena2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderCena2StateChanged(evt);
            }
        });

        rangeSliderCena3.setMaximum(1000000);
        rangeSliderCena3.setValue(0);
        rangeSliderCena3.setUpperValue(1000000);
        rangeSliderCena3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderCena3StateChanged(evt);
            }
        });

        rangeSliderCena4.setMaximum(1000000);
        rangeSliderCena4.setValue(0);
        rangeSliderCena4.setUpperValue(1000000);
        rangeSliderCena4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderCena4StateChanged(evt);
            }
        });

        rangeSliderCena5.setMaximum(1000000);
        rangeSliderCena5.setValue(0);
        rangeSliderCena5.setUpperValue(1000000);
        rangeSliderCena5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderCena5StateChanged(evt);
            }
        });

        jLabelCena5.setText("0-10000");

        jLabelCena4.setText("0-10000");

        jLabelCena3.setText("0-10000");

        jLabelCena2.setText("0-10000");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rangeSliderCena3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rangeSliderCena2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerCenaMin, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerCenaMaks, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 364, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rangeSliderCena4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rangeSliderCena5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelCena5, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(jLabelCena4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelCena3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelCena2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(jSpinnerCenaMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49)
                    .addComponent(jSpinnerCenaMaks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rangeSliderCena3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(rangeSliderCena2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel47))
                            .addComponent(jLabelCena2))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel46))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabelCena3)))))
                .addGap(11, 11, 11)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel45)
                        .addComponent(rangeSliderCena4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelCena4))
                .addGap(13, 13, 13)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44)
                    .addComponent(rangeSliderCena5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCena5))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Opłata za wynajem", jPanel8);

        jLabel50.setText("Wartości wyjątkowo preferowane:");

        jLabel51.setText("Wartości bardzo silnie preferowane:");

        jLabel52.setText("Wartości silnie preferowane:");

        jLabel53.setText("Wartości nieznacznie preferowane:");

        jLabel54.setText("Dopuszczalny zakres [m]: od");

        jSpinnerOdlegloscOdPracyMin.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000000, 1));
        jSpinnerOdlegloscOdPracyMin.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerOdlegloscOdPracyMinStateChanged(evt);
            }
        });

        jSpinnerOdlegloscOdPracyMaks.setModel(new javax.swing.SpinnerNumberModel(1000000, 0, 1000000, 1));
        jSpinnerOdlegloscOdPracyMaks.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerOdlegloscOdPracyMaksStateChanged(evt);
            }
        });

        jLabel55.setText("do");

        rangeSliderOdlegloscOdPracy2.setMaximum(1000000);
        rangeSliderOdlegloscOdPracy2.setToolTipText("");
        rangeSliderOdlegloscOdPracy2.setValue(0);
        rangeSliderOdlegloscOdPracy2.setUpperValue(1000000);
        rangeSliderOdlegloscOdPracy2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderOdlegloscOdPracy2StateChanged(evt);
            }
        });

        rangeSliderOdlegloscOdPracy3.setMaximum(1000000);
        rangeSliderOdlegloscOdPracy3.setValue(0);
        rangeSliderOdlegloscOdPracy3.setUpperValue(1000000);
        rangeSliderOdlegloscOdPracy3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderOdlegloscOdPracy3StateChanged(evt);
            }
        });

        rangeSliderOdlegloscOdPracy4.setMaximum(1000000);
        rangeSliderOdlegloscOdPracy4.setValue(0);
        rangeSliderOdlegloscOdPracy4.setUpperValue(1000000);
        rangeSliderOdlegloscOdPracy4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderOdlegloscOdPracy4StateChanged(evt);
            }
        });

        rangeSliderOdlegloscOdPracy5.setMaximum(1000000);
        rangeSliderOdlegloscOdPracy5.setValue(0);
        rangeSliderOdlegloscOdPracy5.setUpperValue(1000000);
        rangeSliderOdlegloscOdPracy5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rangeSliderOdlegloscOdPracy5StateChanged(evt);
            }
        });

        jLabelOdlegloscOdPracy5.setText("0-10000");

        jLabelOdlegloscOdPracy4.setText("0-10000");

        jLabelOdlegloscOdPracy3.setText("0-10000");

        jLabelOdlegloscOdPracy2.setText("0-10000");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rangeSliderOdlegloscOdPracy3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rangeSliderOdlegloscOdPracy2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerOdlegloscOdPracyMin, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel55)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerOdlegloscOdPracyMaks, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 364, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rangeSliderOdlegloscOdPracy4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rangeSliderOdlegloscOdPracy5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelOdlegloscOdPracy5, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(jLabelOdlegloscOdPracy4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelOdlegloscOdPracy3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelOdlegloscOdPracy2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(jSpinnerOdlegloscOdPracyMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55)
                    .addComponent(jSpinnerOdlegloscOdPracyMaks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rangeSliderOdlegloscOdPracy3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(rangeSliderOdlegloscOdPracy2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel53))
                            .addComponent(jLabelOdlegloscOdPracy2))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel52))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabelOdlegloscOdPracy3)))))
                .addGap(11, 11, 11)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel51)
                        .addComponent(rangeSliderOdlegloscOdPracy4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelOdlegloscOdPracy4))
                .addGap(13, 13, 13)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel50)
                    .addComponent(rangeSliderOdlegloscOdPracy5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelOdlegloscOdPracy5))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Odległość od miejsca pracy", jPanel9);

        jLabel24.setText("Odległość od centrum miasta:");

        jLabel25.setText("Wielkość mieszkania:");

        jLabel26.setText("Piętro:");

        jLabel27.setText("Liczba linii komun. miejskiej:");

        jLabel28.setText("Odległość od miejsca pracy:");

        jLabel29.setText("Subiektywna ocena:");

        jComboBoxWaznoscOdlegloscOdCentrum.setModel(new DefaultComboBoxModel(Ocena.values()));
        jComboBoxWaznoscOdlegloscOdCentrum.setSelectedIndex(2);
        jComboBoxWaznoscOdlegloscOdCentrum.setName(""); // NOI18N
        jComboBoxWaznoscOdlegloscOdCentrum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxWaznoscOdlegloscOdCentrumActionPerformed(evt);
            }
        });

        jComboBoxWaznoscWielkosc.setModel(new DefaultComboBoxModel(Ocena.values()));
        jComboBoxWaznoscWielkosc.setSelectedIndex(2);
        jComboBoxWaznoscWielkosc.setName(""); // NOI18N
        jComboBoxWaznoscWielkosc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxWaznoscWielkoscActionPerformed(evt);
            }
        });

        jComboBoxWaznoscPietro.setModel(new DefaultComboBoxModel(Ocena.values()));
        jComboBoxWaznoscPietro.setSelectedIndex(2);
        jComboBoxWaznoscPietro.setName(""); // NOI18N
        jComboBoxWaznoscPietro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxWaznoscPietroActionPerformed(evt);
            }
        });

        jComboBoxWaznoscLiczbaLiniiKomunikacjiMiejskiej.setModel(new DefaultComboBoxModel(Ocena.values()));
        jComboBoxWaznoscLiczbaLiniiKomunikacjiMiejskiej.setSelectedIndex(2);
        jComboBoxWaznoscLiczbaLiniiKomunikacjiMiejskiej.setName(""); // NOI18N
        jComboBoxWaznoscLiczbaLiniiKomunikacjiMiejskiej.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxWaznoscLiczbaLiniiKomunikacjiMiejskiejActionPerformed(evt);
            }
        });

        jComboBoxWaznoscOdlegloscOdPracy.setModel(new DefaultComboBoxModel(Ocena.values()));
        jComboBoxWaznoscOdlegloscOdPracy.setSelectedIndex(2);
        jComboBoxWaznoscOdlegloscOdPracy.setName(""); // NOI18N
        jComboBoxWaznoscOdlegloscOdPracy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxWaznoscOdlegloscOdPracyActionPerformed(evt);
            }
        });

        jComboBoxWaznoscOcena.setModel(new DefaultComboBoxModel(Ocena.values()));
        jComboBoxWaznoscOcena.setSelectedIndex(2);
        jComboBoxWaznoscOcena.setName(""); // NOI18N
        jComboBoxWaznoscOcena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxWaznoscOcenaActionPerformed(evt);
            }
        });

        jLabel56.setText("Opłata za wynajem:");

        jComboBoxWaznoscCena.setModel(new DefaultComboBoxModel(Ocena.values()));
        jComboBoxWaznoscCena.setSelectedIndex(2);
        jComboBoxWaznoscCena.setName(""); // NOI18N
        jComboBoxWaznoscCena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxWaznoscCenaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxWaznoscOdlegloscOdCentrum, 0, 238, Short.MAX_VALUE)
                    .addComponent(jComboBoxWaznoscWielkosc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxWaznoscPietro, 0, 238, Short.MAX_VALUE)
                    .addComponent(jComboBoxWaznoscLiczbaLiniiKomunikacjiMiejskiej, 0, 238, Short.MAX_VALUE)
                    .addComponent(jComboBoxWaznoscOdlegloscOdPracy, 0, 238, Short.MAX_VALUE)
                    .addComponent(jComboBoxWaznoscOcena, 0, 238, Short.MAX_VALUE)
                    .addComponent(jComboBoxWaznoscCena, 0, 238, Short.MAX_VALUE))
                .addGap(377, 377, 377))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxWaznoscOdlegloscOdCentrum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxWaznoscWielkosc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxWaznoscPietro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxWaznoscLiczbaLiniiKomunikacjiMiejskiej, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxWaznoscCena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxWaznoscOdlegloscOdPracy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jComboBoxWaznoscOcena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        jTabbedPane1.addTab("Ważność kryteriów", jPanel10);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Uporządkowana lista mieszkań wg preferencji");

        jListUporzadkowaneMieszkania.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListUporzadkowaneMieszkania.setEnabled(false);
        jListUporzadkowaneMieszkania.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListUporzadkowaneMieszkaniaValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jListUporzadkowaneMieszkania);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Swd_ahp.katalog.dodajMieszkanie(utworzMieszkanieWgDanychMieszkania());
        Swd_ahp.saveChangesFlats();
        odswiezListeMieszkan();
        jListMieszkania.setSelectedIndex(Swd_ahp.katalog.getMieszkania().size()-1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (jListMieszkania.getSelectedIndex() != -1) {
            Mieszkanie m = Swd_ahp.katalog.mieszkanieNaPozycji(jListMieszkania.getSelectedIndex());
            Mieszkanie m_po_zmianach = utworzMieszkanieWgDanychMieszkania();
            m.updateValues(m_po_zmianach);
            Swd_ahp.saveChangesFlats();
            odswiezListeMieszkanBezZmianyZaznaczenia();
        } else {
            JOptionPane.showMessageDialog(this, "Brak zaznaczonego mieszkania. Wybierz 'Dodaj nowe mieszkanie', jeśli chcesz dodać nowe mieszkanie.", "Zapisywanie zmian", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextFieldDaneMieszkaniaNazwaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDaneMieszkaniaNazwaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDaneMieszkaniaNazwaActionPerformed

    private void jComboBoxDaneMieszkaniaOcenaUzytkownikaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDaneMieszkaniaOcenaUzytkownikaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxDaneMieszkaniaOcenaUzytkownikaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (jListMieszkania.getSelectedIndex() != -1) {
            int selected = jListMieszkania.getSelectedIndex();
            Swd_ahp.katalog.usunNaPozycji(jListMieszkania.getSelectedIndex());
            jListMieszkania.setSelectedIndex(selected-1);
            Swd_ahp.saveChangesFlats();
            odswiezListeMieszkanBezZmianyZaznaczenia();
        } else {
            JOptionPane.showMessageDialog(this, "Brak zaznaczonego mieszkania.", "Usuwanie", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jListMieszkaniaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListMieszkaniaValueChanged
        if (jListMieszkania.getSelectedIndex() != -1) {
            wczytajDaneMieszkania(Swd_ahp.katalog.mieszkanieNaPozycji(jListMieszkania.getSelectedIndex()));
        }
    }//GEN-LAST:event_jListMieszkaniaValueChanged

    private void jListUporzadkowaneMieszkaniaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListUporzadkowaneMieszkaniaValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jListUporzadkowaneMieszkaniaValueChanged

    private void jComboBoxWaznoscOdlegloscOdCentrumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxWaznoscOdlegloscOdCentrumActionPerformed
        zapiszPreferencje();
    }//GEN-LAST:event_jComboBoxWaznoscOdlegloscOdCentrumActionPerformed

    private void jComboBoxWaznoscWielkoscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxWaznoscWielkoscActionPerformed
        zapiszPreferencje();
    }//GEN-LAST:event_jComboBoxWaznoscWielkoscActionPerformed

    private void jComboBoxWaznoscPietroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxWaznoscPietroActionPerformed
        zapiszPreferencje();
    }//GEN-LAST:event_jComboBoxWaznoscPietroActionPerformed

    private void jComboBoxWaznoscLiczbaLiniiKomunikacjiMiejskiejActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxWaznoscLiczbaLiniiKomunikacjiMiejskiejActionPerformed
        zapiszPreferencje();
    }//GEN-LAST:event_jComboBoxWaznoscLiczbaLiniiKomunikacjiMiejskiejActionPerformed

    private void jComboBoxWaznoscOdlegloscOdPracyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxWaznoscOdlegloscOdPracyActionPerformed
        zapiszPreferencje();
    }//GEN-LAST:event_jComboBoxWaznoscOdlegloscOdPracyActionPerformed

    private void jComboBoxWaznoscOcenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxWaznoscOcenaActionPerformed
        zapiszPreferencje();
    }//GEN-LAST:event_jComboBoxWaznoscOcenaActionPerformed

    private void jSpinnerOdlegloscOdCentrumMinStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerOdlegloscOdCentrumMinStateChanged
        zapiszPreferencje();
    }//GEN-LAST:event_jSpinnerOdlegloscOdCentrumMinStateChanged

    private void jSpinnerOdlegloscOdCentrumMaksStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerOdlegloscOdCentrumMaksStateChanged
        zapiszPreferencje();
    }//GEN-LAST:event_jSpinnerOdlegloscOdCentrumMaksStateChanged

    private void rangeSliderOdlegloscOdCentrum2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderOdlegloscOdCentrum2StateChanged
        zapiszPreferencje();
    }//GEN-LAST:event_rangeSliderOdlegloscOdCentrum2StateChanged

    private void rangeSliderOdlegloscOdCentrum3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderOdlegloscOdCentrum3StateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_rangeSliderOdlegloscOdCentrum3StateChanged

    private void rangeSliderOdlegloscOdCentrum4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderOdlegloscOdCentrum4StateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_rangeSliderOdlegloscOdCentrum4StateChanged

    private void rangeSliderOdlegloscOdCentrum5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderOdlegloscOdCentrum5StateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_rangeSliderOdlegloscOdCentrum5StateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Swd_ahp.saveChangesUser();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Swd_ahp.reloadUser();
        odswiezPreferencje();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jSpinnerWielkoscMinStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerWielkoscMinStateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_jSpinnerWielkoscMinStateChanged

    private void jSpinnerWielkoscMaksStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerWielkoscMaksStateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_jSpinnerWielkoscMaksStateChanged

    private void rangeSliderWielkosc2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderWielkosc2StateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_rangeSliderWielkosc2StateChanged

    private void rangeSliderWielkosc3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderWielkosc3StateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_rangeSliderWielkosc3StateChanged

    private void rangeSliderWielkosc4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderWielkosc4StateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_rangeSliderWielkosc4StateChanged

    private void rangeSliderWielkosc5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderWielkosc5StateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_rangeSliderWielkosc5StateChanged

    private void jSpinnerPietroMinStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerPietroMinStateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_jSpinnerPietroMinStateChanged

    private void jSpinnerPietroMaksStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerPietroMaksStateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_jSpinnerPietroMaksStateChanged

    private void rangeSliderPietro2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderPietro2StateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_rangeSliderPietro2StateChanged

    private void rangeSliderPietro3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderPietro3StateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_rangeSliderPietro3StateChanged

    private void rangeSliderPietro4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderPietro4StateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_rangeSliderPietro4StateChanged

    private void rangeSliderPietro5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderPietro5StateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_rangeSliderPietro5StateChanged

    private void jSpinnerLiczbaLiniiKomunikacjiMiejskiejMinStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerLiczbaLiniiKomunikacjiMiejskiejMinStateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_jSpinnerLiczbaLiniiKomunikacjiMiejskiejMinStateChanged

    private void jSpinnerLiczbaLiniiKomunikacjiMiejskiejMaksStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerLiczbaLiniiKomunikacjiMiejskiejMaksStateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_jSpinnerLiczbaLiniiKomunikacjiMiejskiejMaksStateChanged

    private void rangeSliderLiczbaLiniiKomunikacjiMiejskiej2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderLiczbaLiniiKomunikacjiMiejskiej2StateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_rangeSliderLiczbaLiniiKomunikacjiMiejskiej2StateChanged

    private void rangeSliderLiczbaLiniiKomunikacjiMiejskiej3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderLiczbaLiniiKomunikacjiMiejskiej3StateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_rangeSliderLiczbaLiniiKomunikacjiMiejskiej3StateChanged

    private void rangeSliderLiczbaLiniiKomunikacjiMiejskiej4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderLiczbaLiniiKomunikacjiMiejskiej4StateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_rangeSliderLiczbaLiniiKomunikacjiMiejskiej4StateChanged

    private void rangeSliderLiczbaLiniiKomunikacjiMiejskiej5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderLiczbaLiniiKomunikacjiMiejskiej5StateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_rangeSliderLiczbaLiniiKomunikacjiMiejskiej5StateChanged

    private void jSpinnerCenaMinStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerCenaMinStateChanged
        zapiszPreferencje();         // TODO add your handling code here:
    }//GEN-LAST:event_jSpinnerCenaMinStateChanged

    private void jSpinnerCenaMaksStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerCenaMaksStateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_jSpinnerCenaMaksStateChanged

    private void rangeSliderCena2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderCena2StateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_rangeSliderCena2StateChanged

    private void rangeSliderCena3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderCena3StateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_rangeSliderCena3StateChanged

    private void rangeSliderCena4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderCena4StateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_rangeSliderCena4StateChanged

    private void rangeSliderCena5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderCena5StateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_rangeSliderCena5StateChanged

    private void jSpinnerOdlegloscOdPracyMinStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerOdlegloscOdPracyMinStateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_jSpinnerOdlegloscOdPracyMinStateChanged

    private void jSpinnerOdlegloscOdPracyMaksStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerOdlegloscOdPracyMaksStateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_jSpinnerOdlegloscOdPracyMaksStateChanged

    private void rangeSliderOdlegloscOdPracy2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderOdlegloscOdPracy2StateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_rangeSliderOdlegloscOdPracy2StateChanged

    private void rangeSliderOdlegloscOdPracy3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderOdlegloscOdPracy3StateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_rangeSliderOdlegloscOdPracy3StateChanged

    private void rangeSliderOdlegloscOdPracy4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderOdlegloscOdPracy4StateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_rangeSliderOdlegloscOdPracy4StateChanged

    private void rangeSliderOdlegloscOdPracy5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rangeSliderOdlegloscOdPracy5StateChanged
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_rangeSliderOdlegloscOdPracy5StateChanged

    private void jComboBoxWaznoscCenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxWaznoscCenaActionPerformed
        zapiszPreferencje();        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxWaznoscCenaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        makeCalculations();
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBoxDaneMieszkaniaOcenaUzytkownika;
    private javax.swing.JComboBox jComboBoxWaznoscCena;
    private javax.swing.JComboBox jComboBoxWaznoscLiczbaLiniiKomunikacjiMiejskiej;
    private javax.swing.JComboBox jComboBoxWaznoscOcena;
    private javax.swing.JComboBox jComboBoxWaznoscOdlegloscOdCentrum;
    private javax.swing.JComboBox jComboBoxWaznoscOdlegloscOdPracy;
    private javax.swing.JComboBox jComboBoxWaznoscPietro;
    private javax.swing.JComboBox jComboBoxWaznoscWielkosc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCena2;
    private javax.swing.JLabel jLabelCena3;
    private javax.swing.JLabel jLabelCena4;
    private javax.swing.JLabel jLabelCena5;
    private javax.swing.JLabel jLabelLiczbaLiniiKomunikacjiMiejskiej2;
    private javax.swing.JLabel jLabelLiczbaLiniiKomunikacjiMiejskiej3;
    private javax.swing.JLabel jLabelLiczbaLiniiKomunikacjiMiejskiej4;
    private javax.swing.JLabel jLabelLiczbaLiniiKomunikacjiMiejskiej5;
    private javax.swing.JLabel jLabelOdlegloscOdCentrum2;
    private javax.swing.JLabel jLabelOdlegloscOdCentrum3;
    private javax.swing.JLabel jLabelOdlegloscOdCentrum4;
    private javax.swing.JLabel jLabelOdlegloscOdCentrum5;
    private javax.swing.JLabel jLabelOdlegloscOdPracy2;
    private javax.swing.JLabel jLabelOdlegloscOdPracy3;
    private javax.swing.JLabel jLabelOdlegloscOdPracy4;
    private javax.swing.JLabel jLabelOdlegloscOdPracy5;
    private javax.swing.JLabel jLabelPietro2;
    private javax.swing.JLabel jLabelPietro3;
    private javax.swing.JLabel jLabelPietro4;
    private javax.swing.JLabel jLabelPietro5;
    private javax.swing.JLabel jLabelWielkosc2;
    private javax.swing.JLabel jLabelWielkosc3;
    private javax.swing.JLabel jLabelWielkosc4;
    private javax.swing.JLabel jLabelWielkosc5;
    private javax.swing.JList jListMieszkania;
    private javax.swing.JList jListUporzadkowaneMieszkania;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelOdlegloscOdCentrum;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSpinner jSpinnerCenaMaks;
    private javax.swing.JSpinner jSpinnerCenaMin;
    private javax.swing.JSpinner jSpinnerDaneMieszkaniaCena;
    private javax.swing.JSpinner jSpinnerDaneMieszkaniaLiczbaLiniiKomunikacjiMiejskiej;
    private javax.swing.JSpinner jSpinnerDaneMieszkaniaOdlegloscOdCentrum;
    private javax.swing.JSpinner jSpinnerDaneMieszkaniaOdlegloscOdPracy;
    private javax.swing.JSpinner jSpinnerDaneMieszkaniaPietro;
    private javax.swing.JSpinner jSpinnerDaneMieszkaniaWielkosc;
    private javax.swing.JSpinner jSpinnerLiczbaLiniiKomunikacjiMiejskiejMaks;
    private javax.swing.JSpinner jSpinnerLiczbaLiniiKomunikacjiMiejskiejMin;
    private javax.swing.JSpinner jSpinnerOdlegloscOdCentrumMaks;
    private javax.swing.JSpinner jSpinnerOdlegloscOdCentrumMin;
    private javax.swing.JSpinner jSpinnerOdlegloscOdPracyMaks;
    private javax.swing.JSpinner jSpinnerOdlegloscOdPracyMin;
    private javax.swing.JSpinner jSpinnerPietroMaks;
    private javax.swing.JSpinner jSpinnerPietroMin;
    private javax.swing.JSpinner jSpinnerWielkoscMaks;
    private javax.swing.JSpinner jSpinnerWielkoscMin;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextAreaLog;
    private javax.swing.JTextField jTextFieldDaneMieszkaniaNazwa;
    private slider.RangeSlider rangeSliderCena2;
    private slider.RangeSlider rangeSliderCena3;
    private slider.RangeSlider rangeSliderCena4;
    private slider.RangeSlider rangeSliderCena5;
    private slider.RangeSlider rangeSliderLiczbaLiniiKomunikacjiMiejskiej2;
    private slider.RangeSlider rangeSliderLiczbaLiniiKomunikacjiMiejskiej3;
    private slider.RangeSlider rangeSliderLiczbaLiniiKomunikacjiMiejskiej4;
    private slider.RangeSlider rangeSliderLiczbaLiniiKomunikacjiMiejskiej5;
    private slider.RangeSlider rangeSliderOdlegloscOdCentrum2;
    private slider.RangeSlider rangeSliderOdlegloscOdCentrum3;
    private slider.RangeSlider rangeSliderOdlegloscOdCentrum4;
    private slider.RangeSlider rangeSliderOdlegloscOdCentrum5;
    private slider.RangeSlider rangeSliderOdlegloscOdPracy2;
    private slider.RangeSlider rangeSliderOdlegloscOdPracy3;
    private slider.RangeSlider rangeSliderOdlegloscOdPracy4;
    private slider.RangeSlider rangeSliderOdlegloscOdPracy5;
    private slider.RangeSlider rangeSliderPietro2;
    private slider.RangeSlider rangeSliderPietro3;
    private slider.RangeSlider rangeSliderPietro4;
    private slider.RangeSlider rangeSliderPietro5;
    private slider.RangeSlider rangeSliderWielkosc2;
    private slider.RangeSlider rangeSliderWielkosc3;
    private slider.RangeSlider rangeSliderWielkosc4;
    private slider.RangeSlider rangeSliderWielkosc5;
    // End of variables declaration//GEN-END:variables

}
