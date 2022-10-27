package com.dataStructure.真题.考研2022;

public class 斐波那契数列_1_4 {
    public static int F(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return F(n - 1) + F(n - 2);
    }

//    public static int F(int n) {
//        if (n == 0 || n == 1) {
//            return n;
//        }
//        int f0 = 0;
//        int f1 = 1;
//        int sum = 0;
//        for (int i = 2; i <= n; i++) {
//            sum = f1 + f0;
//            f0= f1;
//            f1 = sum;
//        }
//        return sum;
//    }

    public static void main(String[] args) {
        System.out.println(F(5));
    }
}
