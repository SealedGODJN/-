package com.leetcode.dp;

public class dp_完全背包 {
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
     * 遍历背包容量时，从后往前遍历
     * 为什么从后往前？
     * 1、每一遍放置一个物品，当背包容量大于物品时，物品可以直接放下去（没有考虑到后面放其他物品，而是将整个背包的容量都用来放该物品）
     * 2、后面放新的物品时，是考虑之前放的物品，必须覆盖放上一个物品的方案
     *
     * @param weight
     * @param value
     * @param bagWeight
     */
    public static void testWeightBagProblem(int[] weight, int[] value, int bagWeight) {
        int[] dp = new int[bagWeight + 1];
        for (int i = 0; i < value.length; i++) {
            // 对于完全背包，
//            二维dp遍历的时候，背包容量是从小到大，而一维dp遍历的时候，背包既可以是从大到小，背包也可以是从大到小。
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

            // 对于完全背包来说，由于物品可以多次使用，因此，无需考虑物品是否用过，则可以从前往后遍历背包容量
            for (int j = weight[i]; j < bagWeight + 1; j++) {
//            for (int j = bagWeight; j >= weight[i]; j--) {
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
