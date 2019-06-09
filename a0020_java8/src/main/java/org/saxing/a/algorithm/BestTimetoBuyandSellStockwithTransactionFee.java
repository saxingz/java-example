package org.saxing.a.algorithm;

/**
 * leet code 714
 */
public class BestTimetoBuyandSellStockwithTransactionFee {

    public static void main(String[] args) {

    }

    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length <= 1) return 0;

        int profit = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            profit = Math.max(profit, hold + prices[i] - fee);
            hold = Math.max(hold, profit - prices[i]);
        }

        return profit;
    }

}
