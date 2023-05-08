package com.interview.小红书.main1;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigInteger A = BigInteger.valueOf(sc.nextInt());
        BigInteger B = BigInteger.valueOf(sc.nextInt());
        BigInteger M = BigInteger.valueOf(sc.nextInt());
        sc.nextLine();
        int Q = sc.nextInt();
        sc.nextLine();
        BigInteger a0 = BigInteger.valueOf(1);
        BigInteger a1 = BigInteger.valueOf(1);

        int[] qi = new int[Q];
        int max = 0;
        for (int j = 0; j < Q; j++) {
            qi[j] = sc.nextInt();
            if (qi[j] > max) max = qi[j];
        }
        BigInteger[] dp = new BigInteger[max + 1];
        dp[0] = BigInteger.valueOf(1);
        dp[1] = BigInteger.valueOf(1);
        for (int j = 1; j < max; j++) {

            dp[j + 1] = (B.multiply(a0).add(A.multiply(a1))).mod(M);
            a0 = a1;
            a1 = dp[j + 1];
        }
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < Q; j++) {
            sb.append(dp[qi[j]]).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}
