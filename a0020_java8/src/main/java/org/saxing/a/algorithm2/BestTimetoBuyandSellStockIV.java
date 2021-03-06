package org.saxing.a.algorithm2;

/**
 * leetcode 188
 */
public class BestTimetoBuyandSellStockIV {

    public static void main(String[] args) {
        int[] prices = new int[]{2,4,1};
        int k = 2;

        System.out.println(new BestTimetoBuyandSellStockIV().maxProfit(k, prices));

        prices = new int[]{3,2,6,5,0,3};
        k = 2;
        System.out.println(new BestTimetoBuyandSellStockIV().maxProfit(k, prices));
    }

    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (k >= len / 2) return quickSolve(prices);

        int[][]t = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            int tempMax = -prices[0];
            for (int j = 1; j < len; j++) {
                t[i][j] = Math.max(t[i][j - 1], prices[j] + tempMax);
                tempMax = Math.max(tempMax, t[i - 1][j - 1] - prices[j]);
            }
        }

        return t[k][len -1];
    }

    private int quickSolve(int[] prices){
        int len = prices.length, profit = 0;

        for (int i = 1; i < len; i++) {
            if (prices[i] > prices[i - 1]){
                profit += prices[i] - prices[i - 1];
            }
        }

        return profit;
    }

}
