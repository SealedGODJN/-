package com.interview.huawei.huawei0524.main3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] costs = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            costs[i] = scanner.nextInt();
        }
        int maxCost = dp(costs, 1);
        System.out.println(maxCost);
    }
    public static int dp(int[] nums, int index) {
        if (index >= nums.length) {
            return 0;
        }
        // 删除当前节点
        int res_1 = dp(nums,  4 *index)
                + dp(nums,  4 * index + 1)
                + dp(nums,  4 * index + 2)
                + dp(nums,  4 * index + 3) + nums[index];
        // 不删除
        int res_2 = dp(nums, 2 * index) + dp(nums,  2 * index + 1);
        return Math.max(res_1, res_2);
    }

//    private static int computeMaxCost(int[] costs) {
//        int n = costs.length;
//        int[] tree = new int[n + 1];
//        int[][] dp = new int[n + 1][2];
//
//        for (int i = 1; i <= n; i++) {
//            tree[i] = costs[i - 1];
//        }
//        // 从叶子节点
//        for (int i = n; i > 0; i--) {
//            if (2 * i > n) {
//                // 初始化根节点
//                dp[i][0] = tree[i];
//                dp[i][1] = 0;
//            } else {
////                dp[i][0]表示选择节点i时的最大维护成本。
//                // 不能选择左右孩子了
//                dp[i][0] = tree[i] + dp[2 * i][1] + dp[2 * i + 1][1];
////                dp[i][1]表示不选择节点i时的最大维护成本。
//                // 选树的左右孩子
//                dp[i][1] = Math.max(dp[2 * i][0] + dp[2 * i + 1][0], Math.max(dp[2 * i][0] + dp[2 * i + 1][1],
//                        Math.max(dp[2 * i][1] + dp[2 * i + 1][0], dp[2 * i][1] + dp[2 * i + 1][1])));
//            }
//        }
//        // 返回根的选择
//        return Math.max(dp[1][0], dp[1][1]);
//    }
}
