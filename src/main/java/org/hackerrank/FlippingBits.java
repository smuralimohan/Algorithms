package org.hackerrank;

import java.util.Scanner;

/**
 * Created by murali on 16-06-2015.
 */
public class FlippingBits {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long tc = scanner.nextLong();
        while(tc-- > 0) {
            long num = scanner.nextLong();
            long out = flipBits(num);
            System.out.println(out);
        }
    }

    static long flipBits(long num) {
        long out = 0;
        int mask = 1 << 31;
        while (mask != 0) {
            out = out << 1;
            if ((num & mask) == 0)
                out = out | 1;
            mask = mask >>> 1;
        }
        return out;
    }
}
