package com.leetcode.dp.复习;

import java.util.Arrays;

public class dp_300 {
    /**
     * 1. 确定dp[i]的含义
     * i代表数组的长度
     * dp[i]代表当数组长度为i时，最长的递增子序列长度
     *
     * 2.递推公式
     *  dp[i] = min(dp[i], dp[i - coins[i]] + 1)
     *
     * 3.dp数组的初始化
     *
     * 4.确定遍历顺序
     *
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        // 长度为1的数组，最长递增序列最小为1
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 0;
        for (int j : dp) {
            max = Math.max(max, j);
        }
        return max;
    }
}
