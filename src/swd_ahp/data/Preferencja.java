/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swd_ahp.data;

/**
 *
 * @author Wojciech Gaweł
 */
public class Preferencja {
    private int rating_1_minValue = 0;
    private int rating_1_maxValue = 1000000;
    private int rating_2_minValue = 0;
    private int rating_2_maxValue = 1000000;
    private int rating_3_minValue = 0;
    private int rating_3_maxValue = 1000000;
    private int rating_4_minValue = 0;
    private int rating_4_maxValue = 1000000;
    private int rating_5_minValue = 0;
    private int rating_5_maxValue = 1000000;

    public Preferencja() {
    }

    public int getRating_1_minValue() {
        return rating_1_minValue;
    }

    public void setRating_1_minValue(int rating_1_minValue) {
        this.rating_1_minValue = rating_1_minValue;
        if (rating_1_maxValue < rating_1_minValue) {
            setRating_1_maxValue(rating_1_minValue);
        }
        if (rating_1_minValue > rating_2_minValue) {
            setRating_2_minValue(rating_1_minValue);
        }
        if (rating_1_maxValue < rating_2_maxValue) {
            setRating_2_maxValue(rating_1_maxValue);
        }
        
    }

    public int getRating_1_maxValue() {
        return rating_1_maxValue;
    }

    public void setRating_1_maxValue(int rating_1_maxValue) {
        this.rating_1_maxValue = rating_1_maxValue;
        if (rating_1_maxValue < rating_1_minValue) {
            setRating_1_minValue(rating_1_maxValue);
        }
        if (rating_1_minValue > rating_2_minValue) {
            setRating_2_minValue(rating_1_minValue);
        }
        if (rating_1_maxValue < rating_2_maxValue) {
            setRating_2_maxValue(rating_1_maxValue);
        }
    }

    public int getRating_2_minValue() {
        return rating_2_minValue;
    }

    public void setRating_2_minValue(int rating_2_minValue) {
        this.rating_2_minValue = rating_2_minValue;
        if (rating_2_maxValue < rating_2_minValue) {
            setRating_2_maxValue(rating_2_minValue);
        }
        if (rating_2_minValue > rating_3_minValue) {
            setRating_3_minValue(rating_2_minValue);
        }
        if (rating_2_maxValue < rating_3_maxValue) {
            setRating_3_maxValue(rating_2_maxValue);
        }
    }

    public int getRating_2_maxValue() {
        return rating_2_maxValue;
    }

    public void setRating_2_maxValue(int rating_2_maxValue) {
        this.rating_2_maxValue = rating_2_maxValue;
        if (rating_2_maxValue < rating_2_minValue) {
            setRating_2_minValue(rating_2_maxValue);
        }
        if (rating_2_minValue > rating_3_minValue) {
            setRating_3_minValue(rating_2_minValue);
        }
        if (rating_2_maxValue < rating_3_maxValue) {
            setRating_3_maxValue(rating_2_maxValue);
        }
    }

    public int getRating_3_minValue() {
        return rating_3_minValue;
    }

    public void setRating_3_minValue(int rating_3_minValue) {
        this.rating_3_minValue = rating_3_minValue;
        if (rating_3_maxValue < rating_3_minValue) {
            setRating_3_maxValue(rating_3_minValue);
        }
        if (rating_3_minValue > rating_4_minValue) {
            setRating_4_minValue(rating_3_minValue);
        }
        if (rating_3_maxValue < rating_4_maxValue) {
            setRating_4_maxValue(rating_3_maxValue);
        }
    }

    public int getRating_3_maxValue() {
        return rating_3_maxValue;
    }

    public void setRating_3_maxValue(int rating_3_maxValue) {
        this.rating_3_maxValue = rating_3_maxValue;
        if (rating_3_maxValue < rating_3_minValue) {
            setRating_3_minValue(rating_3_maxValue);
        }
        if (rating_3_minValue > rating_4_minValue) {
            setRating_4_minValue(rating_3_minValue);
        }
        if (rating_3_maxValue < rating_4_maxValue) {
            setRating_4_maxValue(rating_3_maxValue);
        }
    }

    public int getRating_4_minValue() {
        return rating_4_minValue;
    }

    public void setRating_4_minValue(int rating_4_minValue) {
        this.rating_4_minValue = rating_4_minValue;
        if (rating_4_maxValue < rating_4_minValue) {
            setRating_4_maxValue(rating_4_minValue);
        }
        if (rating_4_minValue > rating_5_minValue) {
            setRating_5_minValue(rating_4_minValue);
        }
        if (rating_4_maxValue < rating_5_maxValue) {
            setRating_5_maxValue(rating_4_maxValue);
        }
    }

    public int getRating_4_maxValue() {
        return rating_4_maxValue;
    }

    public void setRating_4_maxValue(int rating_4_maxValue) {
        this.rating_4_maxValue = rating_4_maxValue;
        if (rating_4_maxValue < rating_4_minValue) {
            rating_4_maxValue = rating_4_minValue;
        }
        if (rating_4_minValue > rating_5_minValue) {
            setRating_5_minValue(rating_4_minValue);
        }
        if (rating_4_maxValue < rating_5_maxValue) {
            setRating_5_maxValue(rating_4_maxValue);
        }
    }

    public int getRating_5_minValue() {
        return rating_5_minValue;
    }

    public void setRating_5_minValue(int rating_5_minValue) {
        if (rating_5_maxValue < rating_5_minValue) {
            rating_5_maxValue = rating_5_minValue;
        }
        this.rating_5_minValue = rating_5_minValue;
    }

    public int getRating_5_maxValue() {
        return rating_5_maxValue;
    }

    public void setRating_5_maxValue(int rating_5_maxValue) {
        if (rating_5_maxValue < rating_5_minValue) {
            rating_5_minValue = rating_5_maxValue;
        }
        this.rating_5_maxValue = rating_5_maxValue;
    }
    
    public Ocena getRatingOfValue(int value) {
        Ocena rating = null;
        if (value >= rating_5_minValue && value <= rating_5_maxValue) {
            rating = Ocena.getForValue(5);
        } else if (value >= rating_4_minValue && value <= rating_4_maxValue) {
            rating = Ocena.getForValue(4);
        } else if (value >= rating_3_minValue && value <= rating_3_maxValue) {
            rating = Ocena.getForValue(3);
        } else if (value >= rating_2_minValue && value <= rating_2_maxValue) {
            rating = Ocena.getForValue(2);
        } else if (value >= rating_1_minValue && value <= rating_1_maxValue) {
            rating = Ocena.getForValue(1);
        }
        return rating;
    }

    @Override
    public String toString() {
        return "Preferencja{" + "ocena 1: " + rating_1_minValue + "-" + rating_1_maxValue + ", ocena 2=" + rating_2_minValue + "-" + rating_2_maxValue 
                + ", ocena 3=" + rating_3_minValue + "-" + rating_3_maxValue + ", ocena 4=" + rating_4_minValue + "-" + rating_4_maxValue + ", ocena 5=" 
                + rating_5_minValue + "-" + rating_5_maxValue + '}';
    }
    
    public String rating_1_toString() {
        return rating_1_minValue + "-" + rating_1_maxValue;
    }
    
    public String rating_2_toString() {
        return rating_2_minValue + "-" + rating_2_maxValue;
    }
    
    public String rating_3_toString() {
        return rating_3_minValue + "-" + rating_3_maxValue;
    }
    
    public String rating_4_toString() {
        return rating_4_minValue + "-" + rating_4_maxValue;
    }
    
    public String rating_5_toString() {
        return rating_5_minValue + "-" + rating_5_maxValue;
    }
    
    
}
