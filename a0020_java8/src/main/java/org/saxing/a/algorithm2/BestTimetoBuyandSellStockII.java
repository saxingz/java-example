package org.saxing.a.algorithm2;

/**
 * leet code 122
 */
public class BestTimetoBuyandSellStockII {

    static int[] getArray(){
        return new int[]{7,1,5,3,6,4};
    }

    public static void main(String[] args) {

        System.out.println(maxProfit1(getArray()));


    }


    public static int maxProfit1(int[] prices) {
        if (prices.length <= 1){
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
