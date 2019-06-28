package org.saxing.a.algorithm2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * leet code 1
 *
 */
public class TwoSum {

    static int[] getArray(){
        return  new int[]{2, 7, 11, 15};
    }

    public static void main(String[] args) {
        int[] ints = twoSum(getArray(), 17);
        System.out.println(Arrays.stream(ints).boxed().collect(Collectors.toList()));
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff) && map.get(diff) != i){
                return new int[]{i, map.get(diff)};
            }
        }
        return new int[0];
    }

}
