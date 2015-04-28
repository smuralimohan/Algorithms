package org.leetcode;

import java.math.BigInteger;

/**
 * Created by Murali M on 28/4/15.
 */
public class MultiplyStrings {

    int[] result;
    int[] n1;
    int[] n2;
    int[][] cache;

    public static void main(String[] args) {

        MultiplyStrings multiplyStrings = new MultiplyStrings();
        System.out.println(multiplyStrings.multiply("99", "99"));
    }

    private void populateCache(int d) {
        int carry = 0;

        for (int i = n1.length - 1; i >= 0; i--) {
            carry += n1[i] * d;
            cache[d-1][i + 1] = carry % 10;
            carry /= 10;
        }
        //if (carry > 0)
        {
            cache[d-1][0] = carry;
        }
    }

    private void add(int d, int pos) {
        if (d > 0) {
            int[] num = cache[d-1];
            if (pos == 0) {
                for (int i = 0; i < num.length; i++)
                    result[result.length - 1 - i] = num[num.length - 1 -i];
            } else {
                int carry = 0;
                for (int i = 0; i < num.length; i++) {
                    carry = carry + result[result.length - 1 - i - pos] + num[num.length - 1 -i];
                    result[result.length - 1 - i - pos] = carry % 10;
                    carry /= 10;
                }
                //result[result.length - 1 - num.length - pos] = carry;
                carry = 0;
            }
        }
    }

    private void multiply() {

        int j = 0;
        result = new int[n1.length + n2.length];
        for (int i = n2.length - 1; i >= 0; i--) {
            add(n2[i], j);
            j++;
        }
    }

    public String multiply(String num1, String num2) {

        if (num1.length() > num2.length()) {
            String temp;
            temp = num1;
            num1 = num2;
            num2 = temp;
        }

        n1 = new int[num1.length()];
        for (int i = 0; i < num1.length(); i++)
            n1[i] = Character.getNumericValue(num1.charAt(i));

        boolean[] digitsPresent = new boolean[10];
        n2 = new int[num2.length()];
        for (int i = 0; i < num2.length(); i++) {
            int digit = Character.getNumericValue(num2.charAt(i));
            n2[i] = digit;
            digitsPresent[digit] = true;
        }

        cache = new int[9][n1.length + 1];
        for (int i = 1; i <= 9; i++) {
            if (digitsPresent[i])
                populateCache(i);
        }
        multiply();
        StringBuilder sb = new StringBuilder(result.length);
        boolean nonZeroFound = false;

        for (int i : result) {
            if (i == 0) {
                if (nonZeroFound) {
                    sb.append(i);
                }
            } else {
                sb.append(i);
                nonZeroFound = true;
            }
        }
        if (!nonZeroFound) return "0";
        return sb.toString();
    }
}
