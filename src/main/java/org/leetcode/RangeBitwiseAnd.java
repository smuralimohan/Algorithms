package org.leetcode;

/**
 * Created by Murali M on 27/4/15.
 */
public class RangeBitwiseAnd {

    public static void main(String[] args) {
        System.out.println(rangeBitwiseAnd(5,7));
    }

    public static int rangeBitwiseAnd(int m, int n) {
        int i = 30;
        while( 1<< i > n) i--;
       return rangeBitwiseAndHelper(m,n,i);

    }

    private static int rangeBitwiseAndHelper(int m, int n, int i) {

        if (i < 0)
            return 0;

        int ret = 0;
        int pow2 = 1 << i;

        if (n == pow2) {
            if (m == n) {
                return n;
            } else if (m < n) {
                return 0;
            }
        } else if (n > pow2) {
            if (m < pow2 ) {
                return 0;
            } else if (m == pow2) {
                return pow2;
            } else {
               return pow2 | rangeBitwiseAndHelper(m-pow2, n-pow2, --i);
            }
        } else {
            return rangeBitwiseAndHelper(m, n, --i);
        }
        return ret;
    }
}
