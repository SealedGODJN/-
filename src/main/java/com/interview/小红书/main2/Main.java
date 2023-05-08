package com.interview.小红书.main2;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        System.out.println(maxMin(a, n, k));
    }

    public static int maxMin(int[] a, int n, int k) {
        int[] b = Arrays.copyOf(a, n);
        Arrays.sort(b);
        int ans = 0;
        for (int i = n - 1; i > 0; i--) {
            int mid = b[i];
            if (check(mid, a, n, k)) {
                ans = mid;
            }
        }
        return ans;
    }

    public static boolean check(int mid, int[] a, int n, int k) {
        int count = 0;
        int i = 0;
        boolean flag = false;
        while (i < n) {
            if (a[i] >= mid) {
                while (i < n) {
                    if (a[i] >= mid) {
                        flag = true;
                    } else {
                        flag = false;
                        count++;
                        if (count > k) break;
                    }
                    i++;
                }
            } else {
                i++;
            }
        }
        return count <= k && flag;
    }
}
