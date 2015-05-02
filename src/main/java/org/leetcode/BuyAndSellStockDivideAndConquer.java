package org.leetcode;

/**
 * Created by murali on 02-05-2015.
 */
public class BuyAndSellStockDivideAndConquer {
    public static void main(String[] args) {

        BuyAndSellStockDivideAndConquer  buyAndSellStockDivideAndConquer = new BuyAndSellStockDivideAndConquer();
        System.out.println(buyAndSellStockDivideAndConquer.maxProfit(new int[] {10000,9999,9998,9997,9996,9995,9994}));
    }

    public int maxProfitHelper(int[] prices, int start, int end) {
        int profit = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int minIndex = -1, maxIndex = -1;

        if (end > start) {
            for (int i = start; i < end; i++) {
                if (prices[i] > max) {
                    max = prices[i];
                    maxIndex = i;
                }
                if (prices[i] < min) {
                    min = prices[i];
                    minIndex = i;
                }
            }
            if (minIndex <= maxIndex) {
                profit = max - min;
            } else {

                int i1 = maxIndex + 1, i2 = minIndex - 1, newStart = start, newEnd = end;

                for (; i1 < end; i1++) {
                    newStart = i1;
                    if (i1 + 1 < end && prices[i1] < prices[i1 + 1]) break;
                }

                for (; i2 >= start; i2--) {
                    newEnd = i2 + 1;
                    if (i2 - 1 >= start && prices[i2-1] < prices[i2] || newEnd <= newStart) break;
                }

                int result1 = maxProfitHelper(prices, start, maxIndex + 1);
                int result2 = maxProfitHelper(prices, newStart, newEnd);
                int result3 = maxProfitHelper(prices, minIndex, end);
                return Math.max(result1, Math.max(result2, result3));
            }
        }
        return profit;
    }

    public int maxProfit(int[] prices) {
        return  maxProfitHelper(prices, 0, prices.length);
    }
}
