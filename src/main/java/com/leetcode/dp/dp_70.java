package com.leetcode.dp;

import java.util.Scanner;

/**
 * @author hjn
 * @time 2020.6.13
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 */
public class dp_70 {
//    public static int climbStairs(int n) {
//        int f0 = 0;
//        int f1 = 1;
//        int f2 = 0;
//        for(int i=1; i<=n; i++) {
//            f2 = f0 + f1;
//            f0 = f1;
//            f1 = f2;
//        }
//        return f2;
////        int temp = 0;
////        int epoch = n/2;
////        for(int i=0; i <= epoch; i++) {
////            if(i*2 != n) {
////                sum += A(i,(n-i*2)+1);
////            } else {
////                sum += 1;
////            }
////        }
////        return sum;
//    }
//    public static int A(int i, int n) {
//        if(i >= n) {
//            int temp = i;
//            i = n - 1;
//            n = temp + 1;
//        }
//        int sum = 1;
//        int j=i, k=n;
//        for(; j>0; j--) {
//            sum *= k;
//            k=k-1;
//        }
//        return sum;
//    }

    /**
     * 2022.9.2
     * <p>
     * 1）确定dp数组和i的含义：dp[i]： 爬到第i层楼梯，有dp[i]种方法
     * 2）确定递推公式：dp[i]=dp[i-1]+dp[i-2]
     * 3）初始化dp数组：dp[0]=0 dp[1]=1 dp[2]=2
     * 4）确定遍历顺序
     * 5）举例
     *
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if (n <= 2) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * @param n
     * @param m 表示最多可以爬m个台阶
     * @return
     */
    public static int climbStairs(int n, int m) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) { // 把m换成2，就可以AC爬楼梯这道题
                if (i - j >= 0) dp[i] += dp[i - j];
            }
        }
        return dp[n];
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(climbStairs(n));
        System.out.println(climbStairs(n, 2));
    }
}