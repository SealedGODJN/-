package com.leetcode;

import java.util.HashSet;

/**
 * 该问题可以转换为检查链表是否有环？
 * 这个链表由所有sum构成，当sum构成的链表出现环，且环中没有1，则可以判定，sum不可能为1
 *
 * 判断链表是否有环，可以使用双指针算法
 */
public class HashMap_202 {
    /**
     * 使用哈希表检测是否有环
     * @param n
     * @return
     */
//    public static boolean isHappy(int n) {
//        String nToString = String.valueOf(n);
//        char[] nToCharArray = nToString.toCharArray();
//        int sum = 0;
//
//        HashSet<Integer> record = new HashSet<>(); // 记录 一轮循环 是否会有重复的sum
//
//        while (sum != 1) {
//            sum = 0;
//            for (char c : nToCharArray) {
//                int temp = c - '0';
//                sum += temp * temp;
//            }
//            if(record.contains(sum)) return false;
//            else record.add(sum);
//            nToString = String.valueOf(sum);
//            nToCharArray = nToString.toCharArray();
//        }
//        return true;
//    }

    /**
     * 使用双指针检测是否有环
     * @param n
     * @return
     */
    public static boolean isHappy(int n) {
//        String nToString = String.valueOf(n);
//        char[] nToCharArray = nToString.toCharArray();
        int slow = n;
        int fast = calculateSum(n);
//        while (slow != 1) {

        do {
            slow = calculateSum(slow);
            fast = calculateSum(fast);
            fast = calculateSum(fast);
//            if(fast != 1 && fast == slow) return false;
        } while (fast != 1 && fast != slow);

        return fast == 1;
    }

    public static int calculateSum(int sum) {

        String nToString = String.valueOf(sum);
        char[] nToCharArray = nToString.toCharArray();

        sum = 0;
        for (char c : nToCharArray) {
            int temp = c - '0';
            sum += temp * temp;
        }
        return sum;
    }


    public static void main(String[] args) {
        for(int i=0; i < 1000; i++) {
            boolean isHappy = isHappy(i);
            // 输出1~1000以内的快乐数
            if (isHappy) {
                System.out.println(i);
            }
        }
    }
}
