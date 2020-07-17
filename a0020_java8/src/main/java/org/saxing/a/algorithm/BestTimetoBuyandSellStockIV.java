package org.saxing.a.algorithm;

/**
 * leet code 188
 *
 * @author saxing 2020/7/17 23:56
 */
public class BestTimetoBuyandSellStockIV {


    public static void main(String[] args) {
        int k = 2;
        int[] prices = new int[]{2, 4, 1};

        int res = new BestTimetoBuyandSellStockIV().maxProfit(k, prices);
        System.out.println(res);
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
        return t[k][len - 1];
    }

    private int quickSolve(int[] prices){
        int len = prices.length, profit = 0;

        for (int i = 1; i < len; i++) {
            if (prices[i] > prices[i - 1])
                profit += prices[i] - prices[i - 1];
        }
        return profit;
    }

}
