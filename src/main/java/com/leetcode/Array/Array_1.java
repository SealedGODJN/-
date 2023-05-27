package com.leetcode.Array;

import java.util.*;

/**
 * @author HJN
 * @date 2021.9.5
 * @modify 2021.9.5
 */
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

//    /**
//     * 使用哈希表搜索另一个结果：O(n)
//     * @param nums
//     * @param target
//     * @return
//     */
//    public static int[] twoSum(int[] nums, int target) {
//        Map<Integer, Integer> hash = new HashMap<Integer, Integer>();
//        int length = nums.length;
//        int[] index = new int[2];
//        for(int i=0; i<length; i++) {
//            if(hash.containsKey(nums[i])) {
//                index[0] = i;
//                index[1] = hash.get(nums[i]);
//                return index;
//            }
//            hash.put(target-nums[i], i);
//        }
//        return null;
//    }

    public static int[] twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < target) {
                left++;
                while (nums[left] == nums[left + 1]) left++;
            } else if (sum > target) {
                right--;
                while (nums[right] == nums[right - 1]) right--;
            } else {
                break;
            }
        }
        return new int[]{left, right};
    }

    // 泛化题目
    //  返回多对结果
    public static List<List<Integer>> twoSum_More(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        List<List<Integer>> result = new ArrayList<>();
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < target) {
                //left < right && 这个条件要满足！
                while (left < right && nums[left] == nums[left + 1]) left++;
            } else if (sum > target) {
                while (left < right && nums[right] == nums[right - 1]) right--;
            } else {
                List<Integer> oneResult = new ArrayList<>();
                oneResult.add(left);
                oneResult.add(right);
                result.add(oneResult);
                while (left < right && nums[left] == nums[left + 1]) left++;
                while (left < right && nums[right] == nums[right - 1]) right--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] nums = {2,7,11,15};
        int[] nums = {3, 2, 4};
        int target = 6;
        int[] result = twoSum(nums, target);
        for (int i : result) {
            System.out.println(i);
        }
    }
}
