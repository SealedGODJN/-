package com.interview.nowcoder.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

public class HJ9 {
    public static void test1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num = br.readLine();
        HashSet<Integer> check = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = num.length() - 1; i >= 0; i--) {
            int temp = num.charAt(i) - 48;
            if (!check.contains(temp)) {
                sb.append(temp);
                check.add(temp);
            }
        }
        System.out.println(sb);
    }

    public static void test2() throws IOException {
        InputStream in = System.in;
        int available = in.available();
        char[] chars = new char[available];
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            // 查找是否有重复的值
            if (result.lastIndexOf(String.valueOf(chars[i])) != -1) {
                continue;
            }
            result.append(chars[i]);
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
//        test1();
        test2();
    }
}
