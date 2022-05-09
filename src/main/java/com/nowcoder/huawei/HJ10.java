package com.nowcoder.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 编写一个函数，计算字符串中含有的不同字符的个数。字符在 ASCII 码范围内( 0~127 ，包括 0 和 127 )，换行表示结束符，不算在字符里。不在范围内的不作统计。多个相同的字符只计算一次
 * 例如，对于字符串 abaca 而言，有 a、b、c 三种不同的字符，因此输出 3
 */
public class HJ10 {
    public static void test1() throws IOException {
        boolean[] result = new boolean[128];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            result[chars[i]] = true;
        }
        int res = 0;
        for (int i = 0; i < result.length; i++) {
            if (result[i]) res++;
        }
        System.out.println(res);
    }

    public static void test2() throws IOException {
        boolean[] result = new boolean[128];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            char temp = str.charAt(i);
            if (result[temp] == false) {
                result[temp] = true;
                res++;
            }
        }

        //for (int i = 0; i < result.length; i++) {
        //    if (result[i]) res++;
        //}
        System.out.println(res);
    }

    public static void main(String[] args) throws IOException {
        test1();
        test2();
    }
}
