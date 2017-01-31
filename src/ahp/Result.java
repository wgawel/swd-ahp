package ahp;

/**
 *
 * @author Wojciech Gaweł
 */
public class Result {
    private double[] wartoscPreferencjiDlaMieszkan;
    private boolean[] isCRCalculated;
    private double[] CRs;

    public Result(int size) {
        wartoscPreferencjiDlaMieszkan = new double[size];
    }

    public double getCR(int index) {
        return CRs[index];
    }

    public void setCR(int index, double CR) {
        this.CRs[index] = CR;
    }

    public void setIsCRCalculated(boolean[] isCRCalculated) {
        this.isCRCalculated = isCRCalculated;
    }

    public void setCRs(double[] CRs) {
        this.CRs = CRs;
    }
    
    public boolean getIsCRCalculated(int index) {
        return isCRCalculated[index];
    }

    public void setIsCRCalculated(int index, boolean isCRCalculated) {
        this.isCRCalculated[index] = isCRCalculated;
    }
    
    
    public void setValueForIndex(int index, double value) {
        wartoscPreferencjiDlaMieszkan[index] = value;
    }
    
    public double getValueForIndex(int index) {
        return wartoscPreferencjiDlaMieszkan[index];
    }
    
    public double[] getWartoscPreferencjiDlaMieszkan() {
        return wartoscPreferencjiDlaMieszkan;
    }
}
