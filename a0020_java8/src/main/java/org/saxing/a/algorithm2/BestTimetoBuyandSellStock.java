package org.saxing.a.algorithm2;

/**
 * leet code 121
 */
public class BestTimetoBuyandSellStock {

    public static void main(String[] args) {
        int[] prices = new int[]{7,1,5,3,6,4};
        System.out.println(new BestTimetoBuyandSellStock().maxProfit(prices));
    }

    public int maxProfit(int[] prices) {
        if (prices.length == 0 || prices.length == 1){
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int profit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min){
                min = prices[i];
            }else if (prices[i] - min > profit){
                profit = prices[i] - min;
            }
        }
        return profit;
    }

}
