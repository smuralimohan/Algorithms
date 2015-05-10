package org.hackerrank;

import java.util.Scanner;

/**
 * Created by murali on 10-05-2015.
 */
public class StrangeGrid {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long row = scanner.nextInt();
        int column = scanner.nextInt();
        long[] values = new long[5];

        if (row%2 == 1) {
            values[0] = (row - 1) * 5;
            values[1] = values[0] + 2;
            values[2] = values[0] + 4;
            values[3] = values[0] + 6;
            values[4] = values[0] + 8;
        } else {
            values[4] = row * 5 - 1;
            values[3] = values[4] - 2;
            values[2] = values[4] - 4;
            values[1] = values[4] - 6;
            values[0] = values[4] - 8;
        }
        System.out.println(values[column - 1]);
    }
}
