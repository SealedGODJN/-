package com.NPU.面向对象设计.chapter6;

public class Comparison {
    public static <K extends Comparable> K min(K k1, K k2) {

        if (k1.compareTo(k2) > 0) {
            return k2;
        } else {
            return k1;
        }
    }

    public static void main(String[] args) {
        int m = 10, n = 100;
        int i = min(n, m);
        float j = 12.6f, k = 21.9f;
        float f = min(j, k);
        System.out.println("the min value of m and n is " + i + "\nthe" +
                " min value of j and k is " + f);
    }
}