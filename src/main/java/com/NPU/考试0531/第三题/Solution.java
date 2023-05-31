package com.NPU.考试0531.第三题;

import java.util.Scanner;

/**
 * 给定一个长度为N的序列，每个序列的元素是一个整数。要支持以下三种操作：
 * 1.将[L,R]这个区间内的所有数加上V。
 * 2.将[L,R]这个区间翻转，比如1 2 3 4变成4 3 2 1。
 * 3.求[L,R]这个区间中的最大值。
 * 最开始所有元素都是0。
 */
public class Solution {
    /**
     * 将[L,R]这个区间内的所有数加上V。
     * @param seq
     * @param V
     * @param L 左区间
     * @param R 右区间
     */
    public static int[] addV(int[] seq, int V, int L, int R) {
        for (int i = L; i < R; i++) {
            seq[i] += V;
        }
        return seq;
    }

    public static int[] reverse(int[] seq, int L, int R) {
        int length = (R - L) / 2;
        for (int i = L; i <= length; i++) {
            seq[i] = seq[R - i - L];
        }
        return seq;
    }

    public static int maxValue(int[] seq, int L, int R) {
        int max = Integer.MIN_VALUE;
        for (int i = L; i <= R; i++) {
            if (seq[i] > max) max = seq[i];
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] seq = new int[N];
        for (int i = 0; i < M; i++) {
            int K = sc.nextInt();
            int L,R,V;
            switch (K) {
                case 1:
                    L = sc.nextInt() - 1;
                    R = sc.nextInt() - 1;
                    V = sc.nextInt();
                    seq = addV(seq, L, R, V);
                    break;
                case 2:
                    L = sc.nextInt() - 1;
                    R = sc.nextInt() - 1;
                    seq = reverse(seq, L, R);
                    break;
                case 3:
                    L = sc.nextInt() - 1;
                    R = sc.nextInt() - 1;
                    System.out.println(maxValue(seq, L, R));
                    break;
                default:
                    break;
            }
        }
    }
}
