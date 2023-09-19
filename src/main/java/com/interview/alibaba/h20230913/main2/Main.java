package com.interview.alibaba.h20230913.main2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        double isPrime = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
            if (isPrime(nums[i])) {
                isPrime++;
            }
        }
        double target = isPrime / n;
        int res = 0;
        while (target < 0.4) {
            res++;
            n++;
            target = (isPrime + res) / n;
        }
        System.out.println(res);
    }

    private static boolean isPrime(int num) {
        if (num <= 3) {
            return num > 1;
        }
        // num % 6 == 2 || num % 6 == 3 || num % 6 == 4 || num % 6 == 0 都是非质数
        if (num % 6 != 1 && num % 6 != 5) {
            return false;
        }

        // 判断从2到sqrt(n)是否存在其约数
        int sqrt = (int) Math.sqrt(num);

        // 质数分布的规律：大于等于5的质数一定和6的倍数相邻
        // 例如5和7，11和13,17和19等等
        for (int i = 5; i <= sqrt; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) return false;
        }
        return true;
    }
}
