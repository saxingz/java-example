package org.saxing.a.algorithm;

/**
 * leet code  198
 *
 */
public class HouseRobber {


    // solution 1
    //f(0) = nums[0]
    //f(1) = max(num[0], num[1])
    //f(k) = max( f(k-2) + nums[k], f(k-1) )

    // solution 2
    //Include this house:
    //i = num[k] + e (money of this house + money robbed excluding the previous house)
    //
    //Exclude this house:
    //e = max(i, e) (max of money robbed including the previous house or money robbed excluding the previous house)
    //(note that i and e of the previous step, that's why we use tmp here to store the previous i when calculating e, to make O(1) space)



    public static void main(String[] args) {
        int nums[] = new int[]{1,2,3,1};

        int res = new HouseRobber().rob(nums);
        System.out.println(res);
    }

    public int rob(int[] nums) {
        int i = 0;
        int e = 0;
        for (int k = 0; k < nums.length; k++) {
            int temp = i;
            i = nums[k] + e;
            e = Math.max(temp, e);
        }
        return Math.max(i, e);
    }

}
