package com.leetcode;

import java.util.Arrays;

public class String_541 {
    /**
     * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
     *
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。
     * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-string-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *
     * 问题！！！！！
     * 字符串函数的substring使用出错
     *
     * @param s
     * @param k
     * @return
     */
//    public static String reverseStr(String s, int k) {
//        int totallen = s.length();
//        int len = s.length(); // 对String对长度进行记录
//        int start = 0; // 从0开始对s的子串进行反转
//
//        do {
//            if (len >= 2 * k) {
//                String subString = s.substring(start, start + k);
//                char[] temp = subString.toCharArray();
//                reverseString(temp);
//                if (start > 1)
//                    s = s.substring(0, start) + getStringOfCharArray(temp) + s.substring(start + k, totallen);
//                else
//                    s = getStringOfCharArray(temp) + s.substring(start + k, totallen);
//            } else if (len < 2 * k && len >= k) {
//                String subString = s.substring(start, start + k);
//                char[] temp = subString.toCharArray();
//                reverseString(temp);
//
//                if (start > 1)
//                    s = s.substring(0, start) + getStringOfCharArray(temp) + s.substring(start + k, totallen);
//                else
//                    s = getStringOfCharArray(temp) + s.substring(start + k, totallen);
//
//                return s;
//            } else { // 0 < len < k
//                String subString = s.substring(start, totallen);
//                char[] temp = subString.toCharArray();
//                reverseString(temp);
//                s = s.substring(0, start) + getStringOfCharArray(temp);
//                return s;
//            }
//
//            len -= 2 * k;
//            start += 2 * k;
//
//        } while (true);
//    }
//
//    private static String getStringOfCharArray(char[] temp) {
//        StringBuilder s = new StringBuilder();
//        for (char c :
//                temp) {
//            s.append(c);
//        }
//        return s.toString();
//    }

    /**
     * 循环一遍s即可
     * @param s
     * @param k
     * @return
     */
    public static String reverseStr(String s, int k) {
        char[] newStr = s.toCharArray();
        for(int i = 0; i < newStr.length; i += 2 * k) {
            reverseLeftRight(newStr, i, i + Math.min(newStr.length - i, k) - 1);
        }
        return new String(newStr);
    }

    private static void reverseLeftRight(char[] newStr, int left, int right) {
        while (left < right) {
            char leftChar = newStr[left];
            newStr[left] = newStr[right];
            newStr[right] = leftChar;

            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        String s = "abcdefg";
//        String s = "abcd";

        int k = 2;
        System.out.println(reverseStr(s, k));

    }

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

}
