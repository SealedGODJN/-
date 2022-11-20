package com.leetcode.dp;

public class dp_518 {
    /**
     * 但本题和纯完全背包不一样，纯完全背包是能否凑成总金额，而本题是要求凑成总金额的个数！
     * <p>
     * 注意题目描述中是凑成总金额的硬币组合数，为什么强调是组合数呢？
     * <p>
     * 组合不强调元素之间的顺序，排列强调元素之间的顺序。 其实这一点我们在讲解回溯算法专题的时候就讲过了哈。
     * 那我为什么要介绍这些呢，因为这和下文讲解遍历顺序息息相关!
     * 回归本题，动规五步曲来分析如下：
     * <p>
     * 1.确定dp数组以及下标的含义
     * dp[j]：凑成总金额j的货币组合数为dp[j]
     * <p>
     * 2.确定递推公式
     * dp[j] （考虑coins[i]的组合总和） 就是所有的dp[j - coins[i]]（不考虑coins[i]）相加。
     * <p>
     * 所以递推公式：dp[j] += dp[j - coins[i]];
     * <p>
     * 3.dp数组如何初始化
     * 首先dp[0]一定要为1，dp[0] = 1是 递归公式的基础。
     * <p>
     * 4.确定遍历顺序
     * 假设：coins[0] = 1，coins[1] = 5。
     * <p>
     * 外层for循环遍历物品（钱币），内层for遍历背包（金钱总额）---> 组合数（只有[1,5]）
     * 外层for循环遍历背包（金钱总额），内层for遍历物品（钱币）---> 排列数（[1,5]和[5,1]）
     *
     * @param amount 凑成的总金额数
     * @param coins  提供的零钱
     */
    public static int change(int amount, int[] coins) {
        if (coins.length == 1 && amount % coins[0] != 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];

        // 初始化dp数组
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j >= coins[i]) {
                    dp[j] += dp[j - coins[i]];
                }
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int amount = 5;
        int[] coins = {1, 2, 3};
        System.out.println(change(amount, coins));
    }
}
