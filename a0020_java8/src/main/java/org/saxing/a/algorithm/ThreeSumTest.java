package org.saxing.a.algorithm;

import java.util.*;

public class ThreeSumTest {

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum(nums, 0);
        System.out.println(lists);
    }

    public static List<List<Integer>> threeSum(int[] num, int target) {
        Arrays.sort(num);
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        for(int i = 0; i < num.length-2; ++i) {
            if(i > 0 && num[i] == num[i-1])
                continue;

            int j = i + 1, k = num.length-1;

            while(j < k) {
                int candidate = num[i] + num[j] + num[k];
                if(candidate <= 0) {
                    if(candidate == 0)
                        result.add(Arrays.asList(num[i],num[j], num[k]));
                    ++j;
                    while (j < k && num[j] == num[j-1])
                        ++j;
                }
                else if(candidate > 0) {
                    --k;
                    while (k > j && num[k] == num[k+1])
                        --k;
                }
            }
        }

        return result;
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i){
                return new int[]{i, map.get(complement)};
            }
        }
        return new int[0];
    }

}
