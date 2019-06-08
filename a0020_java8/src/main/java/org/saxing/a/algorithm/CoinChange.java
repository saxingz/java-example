package org.saxing.a.algorithm;

/**
 * leet code 322
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 5};
        int amount = 8;

        int res = new CoinChange().coinChange(nums, amount);
        System.out.println(res);
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = amount + 1;
        }
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i){
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

}
