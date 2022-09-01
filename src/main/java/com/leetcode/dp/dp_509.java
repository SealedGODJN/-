package com.leetcode.dp;

public class dp_509 {
    public static int fib(int n) {
        if (n <= 1) return n;
        int sum = 0;
        int f0 = 0;
        int f1 = 1;
        for (int i = 2; i <= n; i++) {
            sum = f0 + f1;
            f0 = f1;
            f1 = sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(fib(0));
    }
}
