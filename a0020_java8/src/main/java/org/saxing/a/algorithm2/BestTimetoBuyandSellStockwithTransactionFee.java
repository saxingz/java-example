package org.saxing.a.algorithm2;

/**
 * leetcode 714
 *
 */
public class BestTimetoBuyandSellStockwithTransactionFee {

    public static void main(String[] args) {
        int[] prices = new int[]{1, 3, 2, 8, 4, 9};
        int fee = 2;
        System.out.println(new BestTimetoBuyandSellStockwithTransactionFee().maxProfit(prices, fee));
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
