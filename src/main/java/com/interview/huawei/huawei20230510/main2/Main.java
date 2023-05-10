package com.interview.huawei.huawei20230510.main2;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    // 6833023887793076998810418710

    // 68846787793076946788418710
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String M = sc.nextLine();
        String N = sc.nextLine();
        String op = sc.nextLine();
        BigInteger n = new BigInteger(N);
        BigInteger max = new BigInteger("-1");
        int l = -1, r = -1;
        for (int i = 0; i < M.length(); i++) {
            if (M.charAt(i) == '0') continue;
            for (int j = i + 2; j < M.length(); j++) {
                BigInteger tmp = new BigInteger(M.substring(i, j + 1));
                BigInteger cur;
                if (op.equals("*")) {
                    cur = tmp.multiply(n);
                } else if (op.equals("+")) {
                    cur = tmp.add(n);
                } else {
                    cur = tmp.subtract(n);
                }
                if (check(cur) && cur.compareTo(max) > 0) {
                    max = cur;
                    l = i;
                    r = j;
                }
            }
        }
        if (l == -1) {
            System.out.println(-1);
        } else System.out.println(M.substring(l, r + 1));
    }

    static Boolean check(BigInteger num) {
        String str = num.toString();
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i - 1) != str.charAt(i)) return false;
        }
        return true;
    }
}
