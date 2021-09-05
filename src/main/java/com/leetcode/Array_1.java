package com.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Array_1 {
//    /**
//     * 暴力解法：O(n^2)
//     * @param nums
//     * @param target
//     * @return
//     */
//    public static int[] twoSum(int[] nums, int target) {
//        int length = nums.length;
//
//        for(int i=0; i<length; i++) {
//            for(int j=0; j<length; j++) {
//                if(nums[i] + nums[j] == target) {
//                    if(i != j) {
//                        int[] result = {i, j};
//                        return  result;
//                    }
//                }
//            }
//        }
//        return null;
//    }

    /**
     * 使用哈希表搜索另一个结果：O(n)
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hash = new HashMap<Integer, Integer>();
        int length = nums.length;
        int[] index = new int[2];
        for(int i=0; i<length; i++) {
            if(hash.containsKey(nums[i])) {
                index[0] = i;
                index[1] = hash.get(nums[i]);
                return index;
            }
            hash.put(target-nums[i], i);
        }
        return null;
    }


    public static void main(String[] args) {
//        int[] nums = {2,7,11,15};
        int[] nums = {3,2,4};
        int target = 6;
        int[] result = twoSum(nums, target);
        if(result == null) return;
        for (int i :
                result) {
            System.out.println(i);
        }
    }
}
