package org.hackerrank;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by murali on 11-06-2015.
 */
public class SherlockAndPermutations {
    private static long prime = 1000000007L;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tc = scanner.nextInt();
        while (tc-- > 0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            System.out.println(findVal(n, m - 1));
        }
    }

    static String findVal(int n, int m) {
        BigInteger val = new BigInteger("1");
        int ub = Math.max(n,m);
        int lb = Math.min(n,m);

        if (m == 0) return "1";
        int j = 1;
        for (int i = ub+ 1; i <= m + n; i++) {
            if (i % j == 0) {
                val = val.multiply(new BigInteger(new Integer(i / j).toString()));
                j++;
            } else {
                BigInteger temp = val.multiply(new BigInteger(new Integer(i).toString()));
                if (temp.mod(new BigInteger(new Integer(j).toString())) == BigInteger.ZERO) {
                    val = val.multiply(new BigInteger(new Integer(i).toString())).divide(new BigInteger(new Integer(j).toString()));
                    //val = ((i * val)/j);
                    j++;
                }
            }
        }
        return val.mod(new BigInteger(new Long(prime).toString())).toString();
    }
}
