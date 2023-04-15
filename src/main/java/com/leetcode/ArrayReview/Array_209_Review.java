package com.leetcode.ArrayReview;

public class Array_209_Review {
    public static int minSubArrayLen(int target, int[] nums) {
        int result = Integer.MAX_VALUE;
        int start = 0; // 滑动窗口的起始
        int sum = 0; // 滑动窗口长度之和
        int sublength = 0; // 滑动窗口的长度
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= target) {
//                if (sum - nums[i+1-size] >= target) {
//                    sum -= nums[i+1-size];
//                    size--;
//                } else {
//                    break;
//                }
                // 这里面的操作是直接去缩小滑动窗口的长度
                sublength = i - start + 1;
                result = Math.min(result, sublength);
                sum -= nums[start++]; // start++ 缩小子数组的长度
            }
        }
        return result < Integer.MAX_VALUE ? result : 0;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        int target = 7;
        System.out.println(minSubArrayLen(target, nums));
    }
}
