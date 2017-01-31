/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swd_ahp.data;

import java.text.DecimalFormat;

/**
 *
 * @author Wojciech Gaweł
 */
public class UporzadkowaneMieszkanie extends Mieszkanie implements Comparable<UporzadkowaneMieszkanie> {
    
    private double wartoscPreferencji = 0.0;

    public double getWartoscPreferencji() {
        return wartoscPreferencji;
    }

    public void setWartoscPreferencji(double wartoscPreferencji) {
        this.wartoscPreferencji = wartoscPreferencji;
    }

    @Override
    public String toString() {
        DecimalFormat myFormat = new DecimalFormat("0.000");
        return myFormat.format(wartoscPreferencji) + " " + super.toString();
    }

    @Override
    public int compareTo(UporzadkowaneMieszkanie o) {
        return (this.wartoscPreferencji - o.wartoscPreferencji) > 0
                ? -1
                : ((this.wartoscPreferencji - o.wartoscPreferencji) == 0
                    ? 0
                    : 1);
    }
    
    
    
}
