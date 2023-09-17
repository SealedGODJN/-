package com.interview.侯蓓.unknown0909.main2;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    /*
6 2
1 1 4 5 1 4
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        scanner.close();

        Arrays.sort(array); // 将数组按升序排序

        int score = 0;
//        int start = 0;
        int end = n - 1;
        while (0 < end) {
            // 从大的开始 相乘
            // 而不是首尾开始
            int start = end - 1;
            int diff = array[end] - array[start];
            if (diff <= k) {
                score += array[start] * array[end];
//                start++;
//                end--;
                // 这两个数用完之后，用另一个数
                end = start - 1;
            } else {
                end--;
            }
        }
        System.out.println(score);
    }
}