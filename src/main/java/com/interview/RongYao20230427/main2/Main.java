package com.interview.RongYao20230427.main2;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] nums = line.split(",");
        nums[0] = nums[0].substring(1);
        nums[2] = nums[2].substring(0, nums[2].length() - 1);
//        int[] frequencies = new int[3];
        BigInteger[] frequencies = new BigInteger[3];
        for (int i = 0; i < 3; i++) {
            frequencies[i] = new BigInteger(nums[i]);
//            frequencies[i] = Integer.parseInt(nums[i]);
        }
        sc.close();

        int minCombination = 2540;
        int maxCombination = 2630;
        int minOrder = -5;
        int maxOrder = 5;

        for (int i = minOrder; i <= maxOrder; i++) {
            for (int j = minOrder; j <= maxOrder; j++) {
                for (int k = minOrder; k <= maxOrder; k++) {

                    BigInteger combination = frequencies[0].multiply(new BigInteger(String.valueOf(i))).add(frequencies[1].multiply(new BigInteger(String.valueOf(j)))).add(frequencies[2].multiply(new BigInteger(String.valueOf(k))));
                    if (combination.compareTo(new BigInteger(String.valueOf(minCombination))) == 1 && combination.compareTo(new BigInteger(String.valueOf(maxCombination))) == -1) {
                        System.out.println(combination + " " + i + " " + j + " " + k);
                    }
                }
            }
        }
    }
}