package org.saxing.a.algorithm2;

/**
 * leetcode 322
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        int amount = 11;
        System.out.println(new CoinChange().coinChange(coins, amount));
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 0; i < dp.length; i++) {
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
