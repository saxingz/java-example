package org.saxing.a.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MajorityElementTest {

    public static void main(String[] args) {
        int[] nums = new int[]{2,2,1,1,1,2,2};
        System.out.println(majorityElement(nums));
    }

    public static int majorityElement(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i : nums){
            Integer count = map.get(i);
            if (count == null){
                map.put(i, 0);
            } else{
                map.put(i, count + 1);
            }
        }

        Set<Integer> keys = map.keySet();
        for (Integer i : keys){
            if (map.get(i) >= nums.length / 2){
                return i;
            }
        }
        throw new RuntimeException("no result");
    }

}
