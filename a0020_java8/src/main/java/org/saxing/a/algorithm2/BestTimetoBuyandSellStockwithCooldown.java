package org.saxing.a.algorithm2;

/**
 * leetcode 309
 */
public class BestTimetoBuyandSellStockwithCooldown {

    public static void main(String[] args) {

    }

    public int maxProfit(int[] prices) {
        // Let b2, b1, b0 represent buy[i - 2], buy[i - 1], buy[i]
        // Let s2, s1, s0 represent sell[i - 2], sell[i - 1], sell[i]
        if (prices == null || prices.length <= 1){
            return 0;
        }

        int buy0 = -prices[0], buy1 = buy0;
        int sell0 = 0, sell1 = 0, sell2 = 0;

        for (int i = 0; i < prices.length; i++) {
            buy0 = Math.max(buy1, sell2 - prices[i]);
            sell0 = Math.max(sell1, buy1 + prices[i]);
            buy1 = buy0; sell2 = sell1; sell1 = sell0;
        }
        return sell0;
    }

}
