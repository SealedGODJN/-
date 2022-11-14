package com.leetcode.dp;

public class dp_1049 {
    public static int lastStoneWeightII(int[] stones) {
        if (stones.length == 1) return stones[0];
        if (stones.length == 2) return Math.abs(stones[0] - stones[1]);
        int bagWeight = 0;
        for (int i = 0; i < stones.length; i++) {
            bagWeight += stones[i];
        }
        int sum = bagWeight;
        bagWeight = bagWeight / 2;

        int[] dp = new int[bagWeight + 1];
        for (int i = 0; i < stones.length; i++) {
            for (int j = bagWeight; j >= stones[i]; j--) {
                if (j >= stones[i]) {
                    dp[j] = Math.max(dp[j - stones[i]] + stones[i], dp[j]);
                }
            }
        }
        int min = dp[bagWeight];
        for (int j : dp) {
            if (Math.abs(sum - j * 2) <= min) min = Math.abs(sum - j * 2);
        }
        return min;
    }

    public static void main(String[] args) {
//        int[] stones = { 2,7,4,1,8,1 };
//        int[] stones = {31,26,33,21,40};
//        int[] stones = {1,2};
        int[] stones = {2, 6};

        System.out.println(lastStoneWeightII(stones));
    }
}
