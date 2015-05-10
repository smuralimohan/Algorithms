package org.hackerrank;

import java.util.Scanner;

/**
 * Created by murali on 10-05-2015.
 */
public class Handshake {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        while(testCaseCount-- > 0) {
            long n = scanner.nextInt();
            long n1 = n-1;
            if (n%2 == 0) n/=2; else n1 /=2;

            System.out.println(n * n1);
        }
    }
}
