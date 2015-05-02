package org.leetcode;

/**
 * Created by murali on 02-05-2015.
 */
public class NumberOf1Bits {
    public static void main(String[] args) {
        NumberOf1Bits numberOf1Bits = new NumberOf1Bits();
        System.out.println(numberOf1Bits.hammingWeight(11));
    }

    int hammingWeight(int n) {
        int counter = 0;

        while(n > 0) {
            n &= n - 1;
            counter++;
        }
        return counter;
    }
}
