package com.leetcode.dp;

public class dp_343 {
    /**
     * 1) dp[i]含义
     * dp[i]是拆分为i个数的最大乘积[错误]
     * dp[i] i被拆分的最大乘积
     * <p>
     * 2) dp递推公式
     * 其实可以从1遍历j，然后有两种渠道得到dp[i].
     * <p>
     * 一个是j * (i - j) 直接相乘。
     * <p>
     * 一个是j * dp[i - j]，相当于是拆分(i - j)，对这个拆分不理解的话，可以回想dp数组的定义
     * <p>
     * j * (i - j) 是单纯的把整数拆分为两个数相乘，而j * dp[i - j]是拆分成两个以及两个以上的个数相乘。
     * <p>
     * 3) 初始化
     * dp[0] dp[1] 没有意义
     * <p>
     * 4) 确定遍历顺序
     * 先把dp[i-1]算好，和dp[i]的两次拆分、多次拆分进行大小比较
     * 根据递推公式，i 3->n
     * j 1->i
     * <p>
     * 5) 举例子
     *
     * @return
     */
    public static int integerBreak(int n) {
        int[] dp = new int[n + 1];
        if (n <= 1) return n;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                // 拆分两次 和 拆分多次 进行比较
                int temp = Math.max(j * (i - j), j * dp[i - j]);
                dp[i] = Math.max(dp[i], temp);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(integerBreak(10));
    }
}
