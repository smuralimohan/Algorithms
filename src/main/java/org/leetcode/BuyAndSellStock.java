package org.leetcode;

/**
 * Created by murali on 02-05-2015.
 */
public class BuyAndSellStock {

    public static void main(String[] args) {

        BuyAndSellStock  buyAndSellStock = new BuyAndSellStock();
        System.out.println(buyAndSellStock.maxProfit(new int[] {4,9,2,8,1,3}));
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
            if (minIndex < maxIndex) {
                profit = max - min;
            } else if (minIndex == end - 1 || maxIndex == start) {

                int i1 = start + 1, i2 = end - 2, newStart = start, newEnd = end;

                if (maxIndex == start) {
                    for (; i1 < end; i1++) {
                        newStart = i1;
                        if (i1 + 1 < end && prices[i1] < prices[i1 + 1]) break;
                    }
                }
                if (i1 == end) return 0;

                if (minIndex == end -1) {
                    for (; i2 >= start; i2--) {
                        newEnd = i2 + 1;
                        if (i2 - 1 >= start && prices[i2-1] < prices[i2]) break;
                    }
                }
                if (i2 == start) return 0;

                return maxProfitHelper(prices, newStart, newEnd);
            }
            else {
                int localMax = Integer.MIN_VALUE;
                int localMin = Integer.MAX_VALUE;

                for (int i = minIndex; i < end; i++) {
                    if (prices[i] > localMax) {
                        localMax = prices[i];
                    }
                }

                for (int i = start; i < maxIndex; i++) {
                    if (prices[i] < localMin) {
                        localMin = prices[i];
                    }
                }
                profit = Math.max(localMax - min, max - localMin);
            }
        }
        return profit;
    }

    public int maxProfit(int[] prices) {
        return  maxProfitHelper(prices, 0, prices.length);
    }
}
