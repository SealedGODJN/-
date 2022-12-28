package com.leetcode.dp;

public class dp_322 {
    /**
     * 1.dp[j]数组及下标含义
     * 用硬币凑成j需要的最少硬币数——dp[j]
     * <p>
     * 2.递推公式
     * dp[i] = min(dp[i], dp[i - coins] + 1)
     * <p>
     * 3.dp数组初始化
     * dp[0] = 0
     * <p>
     * 4.遍历顺序
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        int[] dp = new int[amount + 1];

        int max = Integer.MAX_VALUE;

        // 所有下标非0的元素都应是最大值
        for (int i = 1; i <= amount; i++) {
            dp[i] = max;
        }
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                if (dp[j - coins[i]] != Integer.MAX_VALUE)
                    dp[j] = Math.min(dp[j - coins[i]] + 1, dp[j]);
            }
        }
        if (dp[amount] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        System.out.println(coinChange(coins, 1));
    }
}
