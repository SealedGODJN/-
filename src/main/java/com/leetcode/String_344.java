package com.leetcode;

public class String_344 {
    /**
     * 双指针法
     * @param s 待反转的字符串
     */
    public static void reverseString(char[] s) {
        int head = 0;
        int tail = s.length - 1;
        while (head < tail) {
            // 简单的值交换
            char temp = s[head];
            s[head] = s[tail];
            s[tail] = temp;

            /*
            《深入理解计算机系统》
            位运算：异或
            a ^= b;
            b ^= a;
            a ^= b;

            异或的性质：
            a^a = 0
            0^a = a
            a^b = b^a

            a=(a ^ b);
            b=(a ^ b) ^ b = a ^ (b ^ b) = a ^ 0 = a;
            a=(a ^ b) ^ a = (a ^ a) ^ b = 0 ^ b =b;
             */

            head++;
            tail--;
        }
    }

    public static void main(String[] args) {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        reverseString(s);
        System.out.println(s);
    }
}
