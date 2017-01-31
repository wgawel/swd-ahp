package ahp;

import java.text.DecimalFormat;

/**
 *
 * @author Wojciech Gaweł
 */
public class Matrix {
    private double[][] values;
    private double[] srednieWartosciWierszy;
    private double l_max;

    public Matrix(int size) {
        this.values = new double[size][size];
        this.srednieWartosciWierszy = new double[size];
    }
    
    public int getSize() {
        return values.length;
    }
    
    public void set(int x, int y, double value) {
        values[x][y] = value;
        this.srednieWartosciWierszy = new double[values.length];
    }
    
    public double get(int x, int y) {
        return values[x][y];
    }
    
    public void normalizujKolumny() {
        double[] sumyKolumn = new double[values.length];
        for (int x=0; x<values.length; x++) {
            sumyKolumn[x] = 0;        
            for (int y=0; y<values[x].length; y++) {
                sumyKolumn[x] += values[x][y];
            }
        }
        
        for (int x=0; x<values.length; x++) {     
            for (int y=0; y<values[x].length; y++) {
                values[x][y] = values[x][y]/sumyKolumn[x];
            }
        }
            
        this.srednieWartosciWierszy = new double[values.length];
        
        l_max = 0;
        for (int x=0; x<values.length; x++) {
            l_max += sumyKolumn[x]*getSredniaWartoscWiersza(x);
        }
    }

    public double getL_max() {
        return l_max;
    }
    
    
    public double getSredniaWartoscWiersza(int y) {
        if (srednieWartosciWierszy[y] == 0) {     
            double sum = 0;
            for (int x=0; x<values.length; x++) {
                sum += values[x][y];
            }
            srednieWartosciWierszy[y] = sum/values.length;
        }
        
        return srednieWartosciWierszy[y];
    }

    @Override
    public String toString() {
        DecimalFormat myFormat = new DecimalFormat("0.000");
        StringBuffer result = new StringBuffer();
        for (int y=0; y<values.length; y++) {
            for (int x=0; x<values.length; x++) {
                result.append(myFormat.format(values[x][y])).append("\t");
            }
            result.append("\n");
        }
        
        return result.toString();
    }

    public String toStringWithAverageInRow() {
        DecimalFormat myFormat = new DecimalFormat("0.000");
        StringBuffer result = new StringBuffer();
        for (int y=0; y<values.length; y++) {
            for (int x=0; x<values.length; x++) {
                result.append(myFormat.format(values[x][y])).append("\t");
            }
            result.append("(avg.: ").append(getSredniaWartoscWiersza(y)).append(")").append("\n");
        }
        
        return result.toString();
    }
    
}
