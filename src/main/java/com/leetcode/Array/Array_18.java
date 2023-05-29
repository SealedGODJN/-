package com.leetcode.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Array_18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> oneResult = threeSumTarget(nums, i + 1, target - nums[i]);
            for (List<Integer> one : oneResult) {
                one.add(nums[i]);
                result.add(one);
            }
            // 跳过第一个数字重复的情况，否则会出现重复结果
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) i++;
        }
        return result;
    }

    public static List<List<Integer>> twoSumTarget(int[] nums, int start, long target) {
        // sum < 0 则 lastStatus为0
        int lastStatus = 0;
        int left = start;
        int right = nums.length - 1;
        List<List<Integer>> result = new ArrayList<>();
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
        return result;
    }

    public static List<List<Integer>> threeSumTarget(int[] nums, int start, long target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        // 从start开始三数之和
        for (int i = start; i < nums.length; i++) {
            List<List<Integer>> oneResult = twoSumTarget(nums, i + 1, target - nums[i]);
            for (List<Integer> one : oneResult) {
                one.add(nums[i]);
                result.add(one);
            }
            // 跳过第一个数字重复的情况，否则会出现重复结果
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) i++;
        }
        return result;
    }

    public static void main(String[] args) {
//        int target = 0;
//        int[] nums = {1,0,-1,0,-2,2};

//        int target = 8;
//        int[] nums = {2,2,2,2,2};

        int target = -294967296;
        int[] nums = {1000000000, 1000000000, 1000000000, 1000000000};

        Array_18 array18 = new Array_18();
        List<List<Integer>> result = array18.fourSum(nums, target);
        result.stream().forEach(System.out::println);
    }
}
