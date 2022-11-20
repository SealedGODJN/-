package com.leetcode.dp;

public class dp_474 {
    /**
     * 1.确定dp数组（dp table）以及下标的含义
     * dp[i][j]：最多有i个0和j个1的strs的最大子集的大小为dp[i][j]。
     * <p>
     * 2.确定递推公式
     * dp[i][j] 可以由前一个strs里的字符串推导出来，strs里的字符串有zeroNum个0，oneNum个1。
     * <p>
     * dp[i][j] 就可以是 dp[i - zeroNum][j - oneNum] + 1。
     * <p>
     * 然后我们在遍历的过程中，取dp[i][j]的最大值。
     * <p>
     * 所以递推公式：dp[i][j] = max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
     * <p>
     * 此时大家可以回想一下01背包的递推公式：dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);
     * <p>
     * 对比一下就会发现，字符串的zeroNum和oneNum相当于物品的重量（weight[i]），字符串本身的个数相当于物品的价值（value[i]）。
     * <p>
     * 这就是一个典型的01背包！ 只不过物品的重量有了两个维度而已。
     * <p>
     * 3.初始化dp
     * dp[0][0] = 0
     * <p>
     * 4.确定递归顺序
     * 先遍历物品，再遍历背包重量【倒序】
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public static int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int oneNum = 0, zeroNum = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') zeroNum++;
                else oneNum++;
            }
            for (int i = m; i >= 0; i--) {
                for (int j = n; j >= 0; j--) {
                    if (i >= zeroNum && j >= oneNum)
                        dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
//        int m = 5, n = 3;
//        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 1, n = 1;
        String[] strs = {"10", "0", "1"};
        System.out.println(findMaxForm(strs, m, n));
    }
}
