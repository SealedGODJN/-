package com.leetcode.dp;

import java.util.Arrays;

public class Sword_Offer_2_103 {

//    int j = 1;
//    public int coinChange(int[] coins, int amount) {
//        if (amount == 0) return 0;
//        Arrays.sort(coins);
//        int i = amount;
//        int num = 0;
//        while (true) {
//            while (i < coins[coins.length - j]) {
//                if (j + 1 <= coins.length) {
//                    j++;
//                } else break;
//            }
//            i -= coins[coins.length - j];
//            num++;
//            if (i == 0) {
//                break;
//            } else if (i < 0) {
//                return -1;
//            }
//        }
//        return num;
//    }

    public int coinChange(int[] coins, int amount) {
        int[] coinNums = new int[amount + 1];
//        int[] remainder = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            coinNums[i] = amount + 1;
        }
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
//                    remainder[i] = remainder[i - coins[j]];
                    coinNums[i] = Math.min(coinNums[i - coins[j]] + 1, coinNums[i]);
                }
            }
        }
//        if (remainder[amount] != 0) return -1;
        return coinNums[amount] > amount ? -1 : coinNums[amount];
    }

    public static void main(String[] args) {
        int[] coins = {186, 419, 83, 408};
        int amount = 6249;

//        int[] coins = {1, 2, 5};
//        int amount = 11;

//        int[] coins = {2};
//        int amount = 3;

//        int[] coins = {1};
//        int amount = 1;

        Sword_Offer_2_103 main = new Sword_Offer_2_103();
        System.out.println(main.coinChange(coins, amount));
    }
}
