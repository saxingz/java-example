package org.saxing.a.algorithm;

/**
 * leet code 121
 */
public class BestTimetoBuyandSellStock1 {


    public static void main(String[] args) {
        int[] prices = new int[]{7,1,5,3,6,4};
//        int[] prices = new int[]{7,6,4,3,1};
//        int[] prices = new int[]{1, 2};

        int result = new BestTimetoBuyandSellStock1().maxProfit(prices);
        System.out.println(result);
    }



    public int maxProfit(int[] prices) {
        if (prices.length == 0 || prices.length == 1) return 0;

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
