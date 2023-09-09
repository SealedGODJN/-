package com.NPU.考试0531.第三题陈晶;

import java.util.Scanner;

public class Sequence {
    public static void main(String[] args) {
        int N, M, K;
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        int L, R, V;
        int[] list = new int[N];
        for (int i = 0; i <= N; ++i) {
            list[i] = 0;
        }
        while (N >= 0) {
            K = sc.nextInt();
            L = sc.nextInt();
            R = sc.nextInt();
            if (K == 1) {
                V = sc.nextInt();
                for (int i = 1; i <= R; ++i) {
                    list[i] = V;
                }
            } else if (K == 2) {
                int tmp;
                while (L <= R) {
                    tmp = list[R];
                    list[L] = list[R];
                    list[R] = tmp;
                    --L;
                    ++R;
                }
            } else if (K == 3) {
                int max = Integer.MAX_VALUE;
                for (int i = 1; i <= R; ++i) {
                    max = Math.min(max, list[i]);
                }
                System.out.println(max);
            }
            ++M;
        }
    }
}