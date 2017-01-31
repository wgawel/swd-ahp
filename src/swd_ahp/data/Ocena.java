/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swd_ahp.data;

import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Wojciech Gaweł
 */
@XmlType 
public enum Ocena {

    BARDZO_MALO_PREFEROWANE(1, "Bardzo mało preferowane"),
    MALO_PREFEROWANE(2, "Mało preferowane"),
    SREDNIO_PREFEROWANE(3, "Średnio preferowane"),
    MOCNO_PREFEROWANE(4, "Mocno preferowane"),
    BARDZO_MOCNO_PREFEROWANE(5, "Bardzo mocno preferowane");
    private final int liczba;
    private final String nazwa;

    Ocena(int liczba, String nazwa) {
        this.liczba = liczba;
        this.nazwa = nazwa;
    }

    public static int wartosc(Ocena ocena) {
        return ocena.liczba;
    }
    
    public static Ocena getForValue(int value) {
        if (value <= 1) {
            return Ocena.BARDZO_MALO_PREFEROWANE;
        } else if (value <= 2) {
            return Ocena.MALO_PREFEROWANE;
        } else if (value <= 3) {
            return Ocena.SREDNIO_PREFEROWANE;
        } else if (value <= 4) {
            return Ocena.MOCNO_PREFEROWANE;
        } else {
            return Ocena.BARDZO_MOCNO_PREFEROWANE;
        }
    }
    
    public static double getAHPCompare(Ocena o2, Ocena o1) {
        double ocenaAHP;
        switch (o1.liczba-o2.liczba) {
            case -4 :
                ocenaAHP = 1/9.0;
                break;
            case -3 :
                ocenaAHP = 1/7.0;
                break;
            case -2 :
                ocenaAHP = 1/5.0;
                break;
            case -1 :
                ocenaAHP = 1/3.0;
                break;
            case 0 :
                ocenaAHP = 1;
                break;
            case 1 :
                ocenaAHP = 3;
                break;
            case 2 :
                ocenaAHP = 5;
                break;
            case 3 :
                ocenaAHP = 7;
                break;
            case 4 :
                ocenaAHP = 9;
                break;
            default :
                throw new RuntimeException("Niedozwolone oceny ("+o2.liczba+" vs. "+o1.liczba+").");
        }
        return ocenaAHP;
    }
    
    public static Ocena getDefault() {
        return Ocena.SREDNIO_PREFEROWANE;
    }

    @Override
    public String toString() {
        return this.nazwa;
    }
}
