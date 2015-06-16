package org.hackerrank;

import java.util.Scanner;

/**
 * Created by murali on 10-06-2015.
 */
public class DiwaliLights {
    private static int mod = 41824;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tc = scanner.nextInt();
        while (tc-- > 0) {
            int i = scanner.nextInt();
            System.out.println(findVal(i));
        }
    }

    static long findVal(int exp) {
        long val = 1;

        while (exp > 30) {
            val = (val * mod) % 100000;
            exp -= 30;
        }

        val = ((val * (int) Math.pow(2, exp)) % 100000) - 1;
        return val;
    }
}
