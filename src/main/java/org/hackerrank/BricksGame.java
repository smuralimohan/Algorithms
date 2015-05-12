package org.hackerrank;

import java.util.Scanner;

/**
 * Created by murali on 09-05-2015.
 */
public class BricksGame {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int testCaseCount = scanner.nextInt();
        while(testCaseCount-- > 0) {
            findSolution();
        }
    }

    public static void findSolution() {
        int numCount = scanner.nextInt();
        int[] numbers = new int[numCount];
        long[] dpTable = new long[numCount];
        int[] choice = new int[numCount];
        int nextOptIndex;

        for (int i = 0; i < numCount; i++)
            numbers[i] = scanner.nextInt();

        if (numCount <= 3) {
            System.out.println(numbers[0] + numbers[1] + numbers[2]);
        }
        else {

            dpTable[numCount - 1] = numbers[numCount - 1];
            dpTable[numCount - 2] = numbers[numCount - 2];
            dpTable[numCount - 3] = numbers[numCount - 3];

            dpTable[numCount - 2] += dpTable[numCount - 1];
            dpTable[numCount - 3] += dpTable[numCount - 2];

            choice[numCount - 1] = 1;
            choice[numCount - 2] = 2;
            choice[numCount - 3] = 3;

            for (int j = numCount - 4; j >= 0; j--) {

                long max, opt1 = numbers[j],
                        opt2 = numbers[j] + numbers[j + 1],
                        opt3 = numbers[j] + numbers[j + 1] + numbers[j + 2];

                nextOptIndex = j + 1 + choice[j + 1];
                if (nextOptIndex < numCount)
                    opt1 += dpTable[nextOptIndex];

                nextOptIndex = j + 2 + choice[j + 2];
                if (nextOptIndex < numCount)
                    opt2 += dpTable[nextOptIndex];

                nextOptIndex = j + 3 + choice[j + 3];
                if (nextOptIndex < numCount)
                    opt3 += dpTable[nextOptIndex];

                if (opt1 > opt2) {
                    max = opt1;
                    nextOptIndex = 1;
                } else {
                    max = opt2;
                    nextOptIndex = 2;
                }

                if (opt3 > max) {
                    max = opt3;
                    nextOptIndex = 3;
                }

                dpTable[j] = max;
                choice[j] = nextOptIndex;
            }
            System.out.println(dpTable[0]);
        }
    }
}
