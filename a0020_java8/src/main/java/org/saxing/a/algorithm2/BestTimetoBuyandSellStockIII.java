package org.saxing.a.algorithm2;

/**
 * leetcode 123
 */
public class BestTimetoBuyandSellStockIII {

    public static void main(String[] args) {
        int[] prices = new int[]{3,3,5,0,0,3,1,4};
        System.out.println(new BestTimetoBuyandSellStockIII().maxProfit(prices));
    }

    public int maxProfit(int[] prices) {
        int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;
        int release1 = 0, release2 = 0;

        for (int i : prices){
            release2 = Math.max(release2, hold2 + i);
            hold2 = Math.max(hold2, release1 - i);
            release1 = Math.max(release1, hold1 + i);
            hold1 = Math.max(hold1, -i);
        }
        return release2;
    }

}
