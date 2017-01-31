package ahp;

import java.util.ArrayList;

/**
 *
 * @author Wojciech Gaweł
 */
public class Algorithm {
    private ArrayList<Matrix> macierzePreferencji;
    private Matrix macierzePorownanKryteriow;
    private boolean[] jestObliczonaSpojnosc;
    private double[] CRs;

    public Algorithm(ArrayList<Matrix> macierzePreferencji, Matrix macierzePorownanKryteriow) {
        this.macierzePreferencji = macierzePreferencji;
        this.macierzePorownanKryteriow = macierzePorownanKryteriow;
        
        jestObliczonaSpojnosc = new boolean[macierzePreferencji.size()+1];
        CRs = new double[macierzePreferencji.size()+1];
    }
    
    private void normujMacierze() {
        for (int i=0; i<macierzePreferencji.size(); i++) {
            macierzePreferencji.get(i).normalizujKolumny();
//            System.out.println("Macierz "+i+":\n");
//            System.out.println(macierzePreferencji.get(i).toStringWithAverageInRow());
        }
        macierzePorownanKryteriow.normalizujKolumny();
    }
    
    private double getRI(int n) {
        double ri = 0;
        switch (n) {
            case 1 :
                ri = 0;
                break;
            case 2 :
                ri = 0;
                break;
            case 3 :
                ri = 0.52;
                break;
            case 4 :
                ri =0.89;
                break;
            case 5 :
                ri = 1.11;
                break;
            case 6 :
                ri = 1.25;
                break;
            case 7 :
                ri = 1.35;
                break;
            case 8 :
                ri = 1.40;
                break;
            case 9 :
                ri = 1.45;
                break;
            case 10 :
                ri = 1.49;
                break;
            case 11 :
                ri = 1.51;
                break;
            case 12 :
                ri = 1.54;
                break;
            case 13 :
                ri = 1.56;
                break;
            case 14 :
                ri = 1.57;
                break;
            case 15 :
                ri = 1.58;
                break;
        }
        return ri;
    }

    private void sprawdzSpojnosc() {
        for (int i=0; i<macierzePreferencji.size(); i++) {
            int n = macierzePreferencji.get(i).getSize();
            if (n <=2 ) {
                jestObliczonaSpojnosc[i] = true;
                CRs[i] = 0;
            } else {
                double ri = getRI(n);
                if (ri == 0) {
                    jestObliczonaSpojnosc[i] = false;
                } else {
                    jestObliczonaSpojnosc[i] = true;
                    CRs[i] = ((macierzePreferencji.get(i).getL_max()-n)/(n-1))/ri;
                }
            }
        }
        int n = macierzePorownanKryteriow.getSize();
        if (n <=2 ) {
            jestObliczonaSpojnosc[macierzePreferencji.size()] = true;
            CRs[macierzePreferencji.size()] = 0;
        } else {
                double ri = getRI(n);
                if (ri == 0) {
                    jestObliczonaSpojnosc[macierzePreferencji.size()] = false;
                } else {
                    jestObliczonaSpojnosc[macierzePreferencji.size()] = true;
                    CRs[macierzePreferencji.size()] = ((macierzePorownanKryteriow.getL_max()-n)/(n-1))/ri;
                }
        }
    }
    
    private void wyznaczWspolczynniki(Result result) {
        for (int i=0; i<(macierzePreferencji.get(0).getSize()); i++) {
            double r = 0;
            System.out.println("Mieszkanie "+i);
            for (int k=0; k<macierzePorownanKryteriow.getSize(); k++) {
                System.out.print("r += "+macierzePreferencji.get(k).getSredniaWartoscWiersza(i)+" * "+macierzePorownanKryteriow.getSredniaWartoscWiersza(k)+" = "+macierzePreferencji.get(k).getSredniaWartoscWiersza(i)*macierzePorownanKryteriow.getSredniaWartoscWiersza(k));
                r += macierzePreferencji.get(k).getSredniaWartoscWiersza(i)*macierzePorownanKryteriow.getSredniaWartoscWiersza(k);
                System.out.println(" ("+r+")");
            }
            result.setValueForIndex(i, r);
        }
    }
    
    public boolean jestObliczonaSpojnosc(int index) {
        return jestObliczonaSpojnosc[index];
    }
    
    public Result getResult() {
        Result result = new Result(macierzePreferencji.get(0).getSize());
        
        normujMacierze();
        sprawdzSpojnosc();
        wyznaczWspolczynniki(result);
        
        result.setCRs(this.CRs);
        result.setIsCRCalculated(this.jestObliczonaSpojnosc);
        
        return result;
    }
    
}
