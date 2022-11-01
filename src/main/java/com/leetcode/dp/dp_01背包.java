package com.leetcode.dp;

import java.util.Map;

public class dp_01背包 {
//    /**
//     * 1.确定dp数组以及下标的含义<br>
//     * 对于背包问题，有一种写法，是使用二维数组，即dp[i][j] 表示从下标为[0-i]的物品里任意取，放进容量为j的背包，价值总和最大是多少。<br>
//     * <br>
//     * 2.确定递推公式<br>
//     * （1）不放物品 i ：如果背包不放i，则dp[i][j] = dp[i-1][j]<br>
//     * （2）放物品 i ：如果背包放i，则取dp[i-1][j - weight[i]] + value[i]和dp[i-1][j]中的最大值，即：
//     * dp[i][j] = max ( dp[i-1][j - weight[i]] + value[i] , dp[i-1][j] )<br><br>
//     *
//     * 3.dp数组如何初始化<br>
//     *
//     * dp[i][0]，即：j为0，不存放物品的时候，各个容量的背包所能存放的最大价值。<br>
//     * dp[i][0] = 0<br>
//     *
//     * 看状态转移方程，dp[i]是从dp[i-1]推导出来，因此，dp[0]也需要初始化：<br>
//     *
//     * dp[0][j]，即：i为0，存放编号0的物品的时候，各个容量的背包所能存放的最大价值。<br>
//     *  j < weight[0]的时候，dp[0][j] 应该是 0<br>
//     *  dp[o][j] = 0<br>
//     *  j >= weight[0]的时候，dp[0][j] 应该是 value[0]<br>
//     *  dp[o][j] = value[0]<br><br>
//     *
//     * 4.dp数组遍历的顺序<br>
//     * 两个遍历的维度：物品与背包重量<br>
//     * 优先遍历物品，将背包重量作为限制<br>
//     *
//     * @param weight 物品重量
//     * @param value 物品价值
//     */
//    public static void testWeightBagProblem(int[] weight, int[] value, int bagsize) {
//
//        int[][] dp = new int[weight.length + 1][bagsize + 1];
//        for (int i = 0; i < weight.length; i++) {
//            dp[i][0] = 0;
//        }
//        for (int j = 0; j < weight[0]; j++) {
//            dp[0][j] = 0;
//        }
//        for (int j = weight[0]; j < bagsize + 1; j++) {
//            dp[0][j] = value[0];
//        }
//
//        for (int i = 1; i < weight.length; i++) { // 遍历物品
//            for (int j = 0; j < bagsize + 1; j++) { // 遍历背包
//                if (j < weight[i]) { // 只能选择不放
//                    dp[i][j] = dp[i-1][j];
//                } else { // 有两种选择，放or不放
////                    dp[i][j] = dp[i-1][j-weight[i]];
//                    dp[i][j] = Math.max(dp[i-1][j-weight[i]] + value[i], dp[i-1][j]);
//                }
//            }
//        }
//
//        //打印dp数组
//        for (int i = 0; i <= weight.length; i++) {
//            for (int j = 0; j <= bagsize; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.print("\n");
//        }
//
//        System.out.println("dp[2][4]=" + dp[2][4]);
//    }

    /**
     * 1.确定dp数组的定义<br>
     * 在一维dp数组中，dp[j]表示：容量为j的背包，所背的物品价值可以最大为dp[j]。<br>
     * <p>
     * 2.一维dp数组的递推公式<br>
     * 不放物品i：<br>
     * 如果背包不放i，则dp[j] = dp[j]
     * <p>
     * 放物品i：
     * 如果背包放i，则dp[j] = max( dp[j - weight[i]] + value[i], dp[j])
     * <p>
     * 3.dp数组的初始化
     * dp[0] = 0
     * 由递推公式可知，后面的dp[j]都是由dp[0]推导而来
     * <p>
     * 4.dp数组的遍历顺序
     * 先遍历物品
     * 后遍历背包体积
     *
     * @param weight
     * @param value
     * @param bagWeight
     */
    public static void testWeightBagProblem(int[] weight, int[] value, int bagWeight) {
        int[] dp = new int[bagWeight + 1];
        for (int i = 0; i < value.length; i++) {
//            二维dp遍历的时候，背包容量是从小到大，而一维dp遍历的时候，背包是从大到小。
//            为什么呢？
//
//            倒序遍历是为了保证物品i只被放入一次！。但如果一旦正序遍历了，那么物品0就会被重复加入多次！
//            所以从后往前循环，每次取得状态不会和之前取得状态重合，这样每种物品就只取一次了。
//            如果正序遍历
//
//            dp[1] = dp[1 - weight[0]] + value[0] = 15
//
//            dp[2] = dp[2 - weight[0]] + value[0] = 30
//
//            此时dp[2]就已经是30了，意味着物品0，被放入了两次，所以不能正序遍历。
//            解释：【dp[2]把dp[1]的状态覆盖了，导致0被放入了两次】
//
//            那么问题又来了，为什么二维dp数组历的时候不用倒序呢？
//
//            因为对于二维dp，dp[i][j]都是通过上一层即dp[i - 1][j]计算而来，本层的dp[i][j]并不会被覆盖！
//            for (int j = weight[i]; j < bagWeight + 1; j++) {
            for (int j = bagWeight; j >= weight[i]; j--) {
                if (j >= weight[i]) {
                    dp[j] = Math.max(dp[j - weight[i]] + value[i], dp[j]);
                }
            }
        }
        for (int j = 0; j <= bagWeight; j++) {
            System.out.print(dp[j] + " ");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagsize = 4;
        testWeightBagProblem(weight, value, bagsize);
    }
}
