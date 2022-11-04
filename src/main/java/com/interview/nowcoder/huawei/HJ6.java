package com.interview.nowcoder.huawei;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;

public class HJ6 {
    public static void test1() throws IOException {
        int i = 2;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> result = new ArrayList<>();
        while (N > 1) {
//        while (N > i) {
            if (N % i == 0) { // 存在问题 i=4
                result.add(i);
                N = N / i;
                // i--是为了多试探一次
                i--;
            }
            i++;
        }
//        if (N <= i) {
//            i = 2;
//        }
//        while (N > 1) {
//            if (N % i == 0) {
//                result.add(i);
//                N = N / i;
//            }
//            i++;
//        }
        // 有i-- 就不需要再排序了
        // Collections.sort(result);
        for (Integer element : result) {
            System.out.print(element);
            System.out.print(" ");
        }
    }

    public static void test2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                sb.append(i).append(" ");
                num = num / i;
                i--;
            }
        }
        sb.append(num).append(" ");
        System.out.println(sb);
    }

    public static void test3() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            int num = Integer.parseInt(str);
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    sb.append(i).append(" ");
                    num = num / i;
                    i--;
                }
            }
            sb.append(num).append(" ");
            System.out.println(sb);
        }
    }

    public static void main(String[] args) throws IOException {
//        test1();
//        test2();
        test3();
    }
}
