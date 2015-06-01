package org.hackerrank;

import java.util.Scanner;

/**
 * Created by murali on 31-05-2015.
 */
public class StockMaximize {

    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        StockMaximize stockMaximize = new StockMaximize();
        stockMaximize.solveAll();
    }

    public void solveAll() {

        int tcc = scanner.nextInt();
        while (tcc-- > 0) solve();
    }

    public void solve() {

        int dayCount = scanner.nextInt();
        long profit = 0;
        int[] prices = new int[dayCount];

        for (int i = 0; i < dayCount; i++) {
            prices[i] = scanner.nextInt();
        }

        long lastMaxValue = prices[dayCount - 1];
        for (int j = dayCount - 2; j >=0; j--) {
            if (prices[j] > lastMaxValue) {
                lastMaxValue = prices[j];
            } else {
                profit += lastMaxValue - prices[j];
            }
        }
        System.out.println(profit);
    }
}
