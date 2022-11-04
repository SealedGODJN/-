package com.interview.nowcoder.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HJ11 {
    public static void test1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            char temp = chars[i];
            chars[i] = chars[chars.length - i - 1];
            chars[chars.length - i - 1] = temp;
        }
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]);
        }
    }

    public static void test2() throws IOException {
        InputStream inputStream = System.in;
        int available = inputStream.available() - 1;
        char[] result = new char[available];
        while (available-- > 0) {
            result[available] = (char) inputStream.read();
        }
        System.out.println(String.valueOf(result));
    }

    public static void main(String[] args) throws IOException {
//        test1();
        test2();
    }
}
