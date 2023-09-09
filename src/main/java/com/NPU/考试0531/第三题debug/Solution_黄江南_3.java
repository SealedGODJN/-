package com.NPU.考试0531.第三题debug;

import java.util.Scanner;

/**
 * 给定一个长度为N的序列，每个序列的元素是一个整数。要支持以下三种操作：
 * 1.将[L,R]这个区间内的所有数加上V。
 * 2.将[L,R]这个区间翻转，比如1 2 3 4变成4 3 2 1。
 * 3.求[L,R]这个区间中的最大值。
 * 最开始所有元素都是0。
 */
public class Solution {
    static int[] seq;

    /**
     * 将[L,R]这个区间内的所有数加上V。
     *
     * @param V
     * @param L 左区间
     * @param R 右区间
     */
    public static void addV(int L, int R, int V) {
        if (R > seq.length) return;
        for (int i = L; i < R; i++) {
            seq[i] = V;
        }
    }

    public static void reverse(int L, int R) {
        if (R > seq.length) return;
        int length = (R - L) / 2;
        for (int i = 1; i <= length; i++) {
            int temp = seq[R - i + L];
            seq[i] = temp;
            seq[R - i + L] = seq[i];

        }
    }

    /**
     * 未找到，则return -1
     *
     * @param L
     * @param R
     * @return
     */
    public static int maxValue(int L, int R) {
        if (R > seq.length) return -1;
        int max = Integer.MAX_VALUE;
        for (int i = L; i < R; i++) {
            if (seq[i] < max) max = seq[i];
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        seq = new int[M];
        for (int i = 0; i <= M; i++) {
            int K = sc.nextInt();
            int L, R, V;
            switch (K) {
                case 1:
                    L = sc.nextInt() - 1;
                    R = sc.nextInt() - 1;
                    V = sc.nextInt();
                    addV(R, L, V);
                case 2:
                    L = sc.nextInt() - 1;
                    R = sc.nextInt() - 1;
                    reverse(R, L);
                case 3:
                    L = sc.nextInt() - 1;
                    R = sc.nextInt() - 1;
                    System.out.println(maxValue(R, L));

            }
        }
    }
}