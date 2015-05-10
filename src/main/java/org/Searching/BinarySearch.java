package org.Searching;

import java.util.Scanner;

/**
 * Created by murali on 10-05-2015.
 */
public class BinarySearch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int val = scanner.nextInt();
        int size = scanner.nextInt();
        int[] input = new int[size];
        for (int i = 0; i < size; i++)
            input[i] = scanner.nextInt();

        System.out.println(binarySearch(input, 0, size-1, val));
    }


    public static int binarySearch(int[] input, int lo, int hi, int val) {
        int absent = -1;

        if (lo > hi) {
            return absent;
        } else if (lo == hi) {
            if (val == input[lo]) {
                return lo;
            } else {
                return absent;
            }
        } else {
            int mid = (lo + hi)/2;
            if (val == input[mid]) {
                return mid;
            } else if (val > input[mid]) {
                return binarySearch(input, mid + 1, hi, val);
            } else {
                return binarySearch(input, lo, mid - 1, val);
            }
        }
    }
}
