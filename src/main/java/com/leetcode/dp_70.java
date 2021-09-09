package com.leetcode;

import java.util.Scanner;

/**
 * @author hjn
 * @time 2020.6.13
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 */
public class dp_70 {
    public static int climbStairs(int n) {
        int f0 = 0;
        int f1 = 1;
        int f2 = 0;
        for(int i=1; i<=n; i++) {
            f2 = f0 + f1;
            f0 = f1;
            f1 = f2;
        }
        return f2;
//        int temp = 0;
//        int epoch = n/2;
//        for(int i=0; i <= epoch; i++) {
//            if(i*2 != n) {
//                sum += A(i,(n-i*2)+1);
//            } else {
//                sum += 1;
//            }
//        }
//        return sum;
    }
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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(climbStairs(n));
    }
}