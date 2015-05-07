package org.hackerrank;

import java.util.Scanner;

/**
 * Created by murali on 07-05-2015.
 */
public class CoinChangeProblem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int value = scanner.nextInt();
        int denomCount = scanner.nextInt();
        int[] denoms = new int[denomCount];
        for (int i = 0; i < denomCount; i++)
            denoms[i] = scanner.nextInt();

        long[][] dpTable = new long[value + 1][denomCount + 1];
        long usingThis = 0, inclThis = 0, exclThis = 0;

        for (int i = 1; i <= value; i++) {
            for (int j = 1; j <= denomCount; j++) {
                int denom = denoms[j-1];

                if (i%denom == 0)
                    usingThis = 1;
                else
                    usingThis = 0;

                exclThis = 0;
                inclThis = 0;

                if (j > 1) {
                    exclThis = dpTable[i][j - 1];
                    int rem = i - denom;

                    while (rem > 0) {
                        inclThis += dpTable[rem][j - 1];
                        rem -= denom;
                    }
                }
                dpTable[i][j] = exclThis + inclThis + usingThis;
            }
        }
        System.out.println(dpTable[value][denomCount]);
    }
}
