package org.saxing.a.algorithm2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leet code 18
 */
public class Sum4 {

    static int[] getArray(){
        return new int[]{1, 0, -1, 0, -2, 2};
    }

    public static void main(String[] args) {

        List<List<Integer>> lists1 = fourSum1(getArray(), 0);
        System.out.println(lists1);


        List<List<Integer>> lists2 = fourSum2(getArray(), 0);
        System.out.println(lists2);

    }

    public static List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > 0 && nums[j] == nums[j - 1]){
                    continue;
                }
                int t = target - nums[i] - nums[j];
                int m = j + 1, n = nums.length - 1;
                while (m < n){
                    if (nums[m] + nums[n] == t){
                        result.add(Arrays.asList(nums[i], nums[j], nums[m], nums[n]));
                        m++;
                        n--;
                        while (m > n && nums[m] == nums[m - 1]){
                            m++;
                        }
                        while (m > n && nums[n] == nums[n - 1]){
                            n--;
                        }
                    }else if (nums[m] + nums[n] < t){
                        m++;
                    }else{
                        n--;
                    }
                }
            }
        }
        return result;
    }













    public static List<List<Integer>> fourSum1(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1]){
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j != 0 && nums[j] == nums[j - 1]){
                    continue;
                }
                int t = target - nums[i] - nums[j];
                int m = j + 1, n = nums.length - 1;
                while (m < n){
                    if (nums[m] + nums[n] == t){
                        res.add(Arrays.asList(nums[i], nums[j], nums[m], nums[n]));
                        m++;
                        n--;
                        while (m < n && nums[m] == nums[m - 1]){
                            m++;
                        }
                        while (m < n && nums[n] == nums[n - 1]){
                            n--;
                        }
                    }else if (nums[m] + nums[n] < t){
                        m++;
                    }else{
                        n--;
                    }
                }
            }
        }
        return res;
    }

}
