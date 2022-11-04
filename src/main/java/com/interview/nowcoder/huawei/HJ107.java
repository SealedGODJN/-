package com.interview.nowcoder.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HJ107 {
    /**
     * 牛顿迭代法
     *
     * @param x
     * @return
     */
    public static double fun(double x) {
        if (Math.abs(x * x * x - val) < 0.001) {
            return x;
        } else {
            return fun((x * 2 / 3) + (val / (3 * x * x)));
        }
    }

    public static void test1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        val = Double.parseDouble(br.readLine());
        double result = fun(val / 2);
        System.out.printf("%.1f", result);
    }

    static double val;

    public static void test2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double val = Double.parseDouble(br.readLine());
        boolean flag = false;
        if (val < 0) {
            val = 0 - val;
            flag = true;
        }
        double x = val / 8;
        while (x * x * x <= val) {
            x += 0.01;
        }
        if (flag) {
            System.out.printf("%.1f", -x);
        } else {
            System.out.printf("%.1f", x);
        }

    }

    public static void main(String[] args) throws IOException {
//        test1();
        test2();
    }


}
