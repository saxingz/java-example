package org.saxing.a.algorithm;

/**
 * leet code 152
 */
public class MaximumProductSubarrayTest {

    public static void main(String[] args) {
//        int[] nums = new int[]{2,3,-2,4};
        int[] nums = new int[]{-2,3,-4};

        int result = new MaximumProductSubarrayTest().maxProduct(nums);
        System.out.println(result);
    }

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }

        int[][] dp = new int[2][2];
        int res = nums[0];
        dp[0][1] = nums[0];
        dp[0][0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int x = i % 2;
            int y = (i - 1) % 2;

            dp[x][0] = Math.max(Math.max(dp[y][0] * nums[i], dp[y][1] * nums[i]), nums[i]) ;
            dp[x][1] = Math.min(Math.min(dp[y][0] * nums[i], dp[y][1] * nums[i]), nums[i]) ;
            res = Math.max(res, dp[x][0]);
        }
        return res;
    }

}
