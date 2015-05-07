package org.leetcode;

import java.util.ArrayList;

/**
 * Created by murali on 07-05-2015.
 */
public class FractionToRecurringDecimal {
    public static void main(String[] args) {
        FractionToRecurringDecimal fraction2RecurringDecimal = new FractionToRecurringDecimal();
        //System.out.println(fraction2RecurringDecimal.fractionToDecimal(1, 2));
        //System.out.println(fraction2RecurringDecimal.fractionToDecimal(2, 1));
        //System.out.println(fraction2RecurringDecimal.fractionToDecimal(2, 3));
//        System.out.println(fraction2RecurringDecimal.fractionToDecimal(1, 6));
//        System.out.println(fraction2RecurringDecimal.fractionToDecimal(1, 90));
//        System.out.println(fraction2RecurringDecimal.fractionToDecimal(1, 99));
//        System.out.println(fraction2RecurringDecimal.fractionToDecimal(1, 333));
//        System.out.println(fraction2RecurringDecimal.fractionToDecimal(1, 7));
        //System.out.println(fraction2RecurringDecimal.fractionToDecimal(1, 19));
        //System.out.println(fraction2RecurringDecimal.fractionToDecimal(1, 17));
        //System.out.println(fraction2RecurringDecimal.fractionToDecimal(-50, 8));
        //System.out.println(fraction2RecurringDecimal.fractionToDecimal(7, -12));
        //System.out.println(fraction2RecurringDecimal.fractionToDecimal(1, 214748364));
        //System.out.println(fraction2RecurringDecimal.fractionToDecimal(-1, -2147483648));
        System.out.println(fraction2RecurringDecimal.fractionToDecimal(-2147483648, -1999));
    }

    public String fractionToDecimal(int n, int d) {

        if (n == 0) return "0";
        long numerator = n, denominator = d;
        ArrayList<Long> quotients = new ArrayList<>(), remainders = new ArrayList<>();
        long q, r, maxLimit = 5000;
        int rIndex = -1, sign = 1;

        if (numerator < 0) sign *= -1;
        if (denominator < 0) sign *= -1;

        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);
        q = numerator / denominator;
        r = numerator % denominator;

        String p = (sign == -1 ? "-" : "") + q;
        if (r == 0) return (sign == -1 ? "-" : "") + q + "";
        remainders.add(r);

        while(r > 0 && remainders.size() <= maxLimit) {
            numerator = r * 10;
            q = numerator / denominator;
            r = numerator % denominator;

            quotients.add(q);
            rIndex = remainders.indexOf(r);
            if (rIndex != -1) {
                break;
            }
            remainders.add(r);
        }

        StringBuilder sb = new StringBuilder();
        if (rIndex != -1) {
            for (int l = 0; l < rIndex; l++) {
                sb.append(quotients.get(l));
            }
            sb.append('(');
            for (int l = rIndex; l < quotients.size(); l++) {
                sb.append(quotients.get(l));
            }
            sb.append(')');
        } else {
            for (Long num : quotients) {
                sb.append(num);
            }
        }
        return  p + "." + sb.toString();
    }
}
