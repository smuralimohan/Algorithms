package org.hackerrank;

import java.util.Scanner;

/**
 * Created by murali on 07-05-2015.
 */
public class MaxSubArray {

    static Scanner in = new Scanner(System.in);

    private static void printMaxSubArrayValues() {
        int numCount = in.nextInt();
        int thisNum = 0;
        long onlyPositives = 0;
        long globalOptimum = Long.MIN_VALUE, localOptimum = 0;
        boolean foundPositive = false;
        long absMax = Long.MAX_VALUE;

        for (int i = 0; i < numCount; i++) {
            thisNum = in.nextInt();

            if (thisNum > 0) {
                foundPositive = true;
                onlyPositives += thisNum;
            }
            if (absMax > Math.abs(thisNum))
                absMax = Math.abs(thisNum);

            localOptimum += thisNum;
            if (localOptimum < 0) {
                localOptimum = 0;
            } else if (localOptimum > globalOptimum) {
                globalOptimum = localOptimum;
            }
        }
        if (numCount > 0) {
            if (foundPositive)
                System.out.println(globalOptimum + " " + onlyPositives);
            else
                System.out.println(absMax * -1 + " " + absMax * -1);
        }
    }

    public static void main(String[] args) {
        int testCaseCount = in.nextInt();
        for (int i = 0; i < testCaseCount; i++) {
            printMaxSubArrayValues();
        }
    }
}
