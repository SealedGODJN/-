package com.interview.nowcoder.huawei;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * HJ7 取近似值
 * 描述
 * 写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。如果小数点后数值大于等于 0.5 ,向上取整；小于 0.5 ，则向下取整。
 * <p>
 * 数据范围：保证输入的数字在 32 位浮点数范围内
 * 输入描述：
 * 输入一个正浮点数值
 * <p>
 * 输出描述：
 * 输出该数值的近似整数值
 */
public class HJ7 {
    /**
     * 第一版
     * 核心思想：判断小数部分是否大于0.5
     * <p>
     * 不妥之处：使用java Math库函数，效率并不高
     */
    public static void test1() {
        Scanner sc = new Scanner(System.in);
        double num = sc.nextFloat();
        int element = (int) Math.floor(num);
        if (element == 0) element = 1;
        double last = num % element; // 取其小数部分
        if (last >= 0.5) {
            System.out.println((int) Math.ceil(num));
        } else {
            System.out.println((int) Math.floor(num));
        }
    }

    /**
     * 直接使用库函数
     */
    public static void test2() {
        Scanner sc = new Scanner(System.in);
        double num = sc.nextFloat();
        System.out.println(Math.round(num));
    }

    /**
     * 核心思想：将数字拆分成整数和小数部分，遇到小数点，即可判断输出的内容
     */
    public static void test3() throws IOException {
        InputStream inputStream = System.in;
        int c = inputStream.read();
        int result = 0;
        int isXiaoShu = 0;
        while (c != '\n') {
            if (isXiaoShu == 1) {
                if (c > 52) {
                    result += 1;
                }
                System.out.println(result);
                break;
            } else {
                if (c == '.') {
                    isXiaoShu = 1;
                } else {
                    result = result * 10 + c - 48;
                }
                c = inputStream.read();
            }
        }
        if (isXiaoShu == 0) { // 另外一种情况：没有小数点
            System.out.println(result);
        }
    }

    public static void main(String[] args) throws IOException {
//        test1();
//        test2();
        test3();
    }
}
