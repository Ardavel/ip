/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip.ui;

import java.text.NumberFormat;

/**
 *
 * @author PiotrGrzelak
 */
public class FormatUtils {
    
    private static final int MAX_FRAC_DIGITS = 6;
    
    public static String doubleArrayToString(double[] array) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        NumberFormat formatter = NumberFormat.getInstance();
        formatter.setMaximumFractionDigits(MAX_FRAC_DIGITS);
        
        for (int i = 0; i < array.length; ++i) {
            builder.append(formatter.format(array[i]));
            if (i != array.length - 1) {
                builder.append("; ");
            }
        }
        builder.append("]");
        
        return builder.toString();
    }
}
