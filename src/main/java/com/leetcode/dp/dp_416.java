package com.leetcode.dp;

public class dp_416 {
    /**
     * 只有确定了如下四点，才能把01背包问题套到本题上来。
     * <p>
     * 背包的体积为sum / 2
     * 背包要放入的商品（集合里的元素）重量为 元素的数值，价值也为元素的数值
     * 背包如果正好装满，说明找到了总和为 sum / 2 的子集。
     * 背包中每一个元素是不可重复放入。
     */
    public static boolean canParition(int[] nums) {
        if (nums.length <= 1) return false;
        int bagWeight = 0;
        for (int i = 0; i < nums.length; i++) {
            bagWeight += nums[i];
        }
        int sum = bagWeight;
        bagWeight = bagWeight / 2;
        if (sum / bagWeight != 2 || sum % bagWeight != 0) return false;

        int[] dp = new int[bagWeight + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = bagWeight; j >= nums[i]; j--) {
                if (j >= nums[i]) {
                    dp[j] = Math.max(dp[j - nums[i]] + nums[i], dp[j]);
                }
            }
        }
        for (int j : dp) {
            if (j == bagWeight) return true;
        }
        return false;
    }

    public static void main(String[] args) {
//        int[] nums = { 1, 18, 11, 5 };
//        int[] nums = { 1, 2 };
        int[] nums = {1, 2, 3, 5};
        System.out.println(canParition(nums));
    }
}
