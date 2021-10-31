package com.leetcode;

import java.math.BigInteger;
import java.sql.DriverManager;
import java.util.Collections;
import java.util.HashSet;

public class HashMap_202 {
    public static boolean isHappy(int n) {
        String nToString = String.valueOf(n);
        char[] nToCharArray = nToString.toCharArray();
        int sum = 0;

        HashSet<Integer> record = new HashSet<>(); // 记录 一轮循环 是否会有重复的sum

        while (sum != 1) {
            sum = 0;
            for (char c : nToCharArray) {
                int temp = c - '0';
                sum += temp * temp;
            }
            if(record.contains(sum)) return false;
            else record.add(sum);
            nToString = String.valueOf(sum);
            nToCharArray = nToString.toCharArray();
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 2;
        System.out.println(isHappy(n));
    }
}
