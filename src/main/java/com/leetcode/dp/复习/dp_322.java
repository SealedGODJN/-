package com.leetcode.dp.复习;

public class dp_322 {
    /**
     * 1. 确定dp[i]的含义
     * i代表钱
     * dp[i]代表i元钱需要的硬币
     *
     * 2.递推公式
     *  dp[i] = min(dp[i], dp[i - coins[i]] + 1)
     *
     * 3.dp数组的初始化
     *
     * 4.确定遍历顺序
     *
     *
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        int[] dp = new int[amount + 1];
        for (int i = 0; i < amount + 1; i++) {
            dp[i] = amount + 1;
        }
        dp[0] = 0;
        for (int i = 0; i < amount + 1; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        dp_322 s = new dp_322();
        int[] coins = {1,2,5};
        int amount = 11;
        System.out.println(s.coinChange(coins, amount));
    }
}
