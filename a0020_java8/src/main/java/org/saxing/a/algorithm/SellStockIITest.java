package org.saxing.a.algorithm;

public class SellStockIITest {

    public static void main(String[] args) {
        int[] nums = new int[]{7,1,5,3,6,4};
        System.out.println(maxProfit(nums));
    }

    public static int maxProfit(int[] prices) {
        if (prices.length == 0){
            return 0;
        }

        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i + 1] - prices[i];
            profit += diff > 0 ? diff : 0;
        }
        return profit;
    }

}
