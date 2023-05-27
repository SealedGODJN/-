package com.leetcode.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NSumTarget {
    /**
     * 调用nSumTarget之前，应该
     *
     * @param nums
     * @param n
     * @param start
     * @param target
     * @return
     */
    public List<List<Integer>> nSumTarget(int[] nums, int n, int start, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (n < 2 || nums.length < n) return result;
        if (n == 2) {
            int lastStatus = 0;
            int left = start;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum < target) {
                    lastStatus = 0;
                    left++;
                } else if (sum > target) {
                    lastStatus = 1;
                    right--;
                } else {
                    List<Integer> oneResult = new ArrayList<>();
                    oneResult.add(nums[left]);
                    oneResult.add(nums[right]);
                    result.add(oneResult);

                    while (right > left && nums[right] == nums[right - 1]) right--;
                    while (right > left && nums[left] == nums[left + 1]) left++;

                    // 找到答案时，双指针同时收缩
                    if (lastStatus == 0) {
                        left++;
                    } else right--;
                }
            }
        } else {
            for (int i = start; i < nums.length; i++) {
                List<List<Integer>> oneResult = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> one : oneResult) {
                    one.add(nums[i]);
                    result.add(one);
                }
                // 跳过第一个数字重复的情况，否则会出现重复结果
                while (i < nums.length - 1 && nums[i] == nums[i + 1]) i++;
            }
        }
        return result;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return nSumTarget(nums, 4, 0, target);
    }
}
