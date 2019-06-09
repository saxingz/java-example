package org.saxing.a.algorithm;

/**
 * leet code
 * 309
 */
public class BestTimetoBuyandSellStockwithCooldown {

    //buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
    //sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);

//    Let b2, b1, b0 represent buy[i - 2], buy[i - 1], buy[i]
//    Let s2, s1, s0 represent sell[i - 2], sell[i - 1], sell[i]

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        int b0 = -prices[0], b1 = b0;
        int s0 = 0, s1 = 0, s2 = 0;

        for (int i = 0; i < prices.length; i++) {
            b0 = Math.max(b1, s2 - prices[i]);
            s0 = Math.max(s1, b1 + prices[i]);
            b1 = b0; s2 = s1; s1 = s0;
        }
        return s0;
    }

}
