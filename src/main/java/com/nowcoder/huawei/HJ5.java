package com.nowcoder.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HJ5 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        str = str.substring(2);
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            String cur = str.substring(i, i + 1);
            int temp = 0;
            switch (cur) {
                case "A":
                    temp = 10;
                    break;
                case "B":
                    temp = 11;
                    break;
                case "C":
                    temp = 12;
                    break;
                case "D":
                    temp = 13;
                    break;
                case "E":
                    temp = 14;
                    break;
                case "F":
                    temp = 15;
                    break;
                default:
                    temp = Integer.parseInt(cur);
                    break;
            }
            result += temp * Math.pow(16, str.length() - i - 1);
        }
        System.out.println(result);
    }
}
