package org.Sorting;

import java.util.Scanner;

/**
 * Created by murali on 10-05-2015.
 */
public class InsertionSort {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] input = new int[size];
        for (int i = 0; i < size; i++)
            input[i] = scanner.nextInt();

        insertIntoSorted(input);

    }

    public static void insertIntoSorted(int[] input) {
        int i = input.length - 1;
        int last = input[i];
        while ((i - 1) >= 0 && input[i-1] > last) {
            input[i] = input[i-1];
            for (int j=0; j < input.length; j++)
                System.out.print(input[j] + " ");
            System.out.println();
            i--;
        }
        input[i] = last;
        for (int j=0; j < input.length; j++)
            System.out.print(input[j] + " ");
        System.out.println();
    }
}
