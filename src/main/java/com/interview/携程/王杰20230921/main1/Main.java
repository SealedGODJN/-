package com.interview.携程.王杰20230921.main1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取输入的排列a的长度n
        int n = scanner.nextInt();

        // 读取输入的排列a
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < n; i++) {
            if (a[i] < min) {
                minIndex = i;
                min = a[i];
            }
        }

        if (minIndex == 0) {
            System.out.print(a[1] + " ");
            System.out.print(a[0] + " ");
            for (int i = 2; i < n; i++) {
                System.out.print(a[i] + " ");
            }
        } else if (minIndex == n - 1) {
            // minIndex >= 1
            for (int i = 0; i < n; ) {
                if (i != minIndex - 1) {
                    System.out.print(a[i] + " ");
                    i++;
                } else {
                    // if (i == minIndex - 1)
                    System.out.print(a[minIndex] + " ");
                    System.out.print(a[minIndex - 1] + " ");
                    i = i + 2;
                }
            }
        } else {
            // 一直要替换
//            min = Integer.MAX_VALUE;
//            minIndex = -1;
            for (int i = 0; i < n; i++) {
                if (a[i] < min) {
                    minIndex = i;
                    min = a[i];
                }
                // TODO 连续对比，如果元素相等，交换元素
            }
        }
    }
}