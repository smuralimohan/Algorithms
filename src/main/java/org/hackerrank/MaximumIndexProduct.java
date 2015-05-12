package org.hackerrank;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by murali on 12-05-2015.
 */
public class MaximumIndexProduct {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCount = scanner.nextInt();
        int[] numbers = new int[numCount];
        long[] left = new long[numCount];
        long[] right = new long[numCount];
        //long[] product = new long[numCount];
        long max, curr;

        if (numCount > 0) {

            max = numbers[0] = scanner.nextInt();
            for (int i = 1; i < numCount; i++) {
                int num = numbers[i] = scanner.nextInt();
                if (num >= max) {
                    max = num;
                    left[i] = 0;
                    continue;
                }
                for (int j = i - 1; j >= 0; j--) {
                    if (numbers[j] > num) {
                        left[i] = j + 1;
                        break;
                    }
                }
            }

            max = numbers[numCount - 1];
            for (int i = numCount - 2; i >= 0; i--) {
                int num = numbers[i];
                if (num >= max) {
                    max = num;
                    right[i] = 0;
                    continue;
                }
                for (int j = i + 1; j < numCount; j++) {
                    if (numbers[j] > num) {
                        right[i] = j + 1;
                        break;
                    }
                }
            }

            max = Long.MIN_VALUE;
            for (int k =0; k < numCount; k++) {
                curr = left[k] * right[k] * 1L;
                if (curr > max)
                    max = curr;
            }
            System.out.println(max);
        }
    }
}
