package com.interview.侯蓓.unknown0909.main3;

import java.util.Scanner;

public class Main {
    /*
7
0 2 3 5 6 0 0

7
0 2 3 1 6 8 0
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] value = new int[n];
        for (int i = 0; i < n; i++) {
            value[i] = sc.nextInt();
        }
        int[] result = new int[n];
        result[1] = value[0];
        for (int i = 2; i < n; i++) {
            result[i] = Math.min(result[i - 1] + value[i - 1], result[i - 2] + value[i - 2]);
        }
        System.out.println(result[n - 1]);
    }
}
