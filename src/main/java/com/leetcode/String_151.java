package com.leetcode;

import java.util.StringTokenizer;

public class String_151 {
    /**
     * 空间复杂度为O(1)？
     * 1、removeExtraSpaces 移除多余的空格
     * 2、反转数组 reverseString
     * 3、对每个单词再反转一遍
     *
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        char[] sArray = s.toCharArray();
        int newLen = removeExtraSpaces(sArray);
        reverseLeftRight(sArray, 0, newLen); // 设定的范围为去除了空格的数组的长度
        int start = 0;
        int end;
        for (int i = 0; i < newLen; i++) {
            if(sArray[i] == ' ') {
                end = i - 1;
                reverseLeftRight(sArray, start, end);
                start = i + 1;
            }
        }
        return new String(sArray).substring(0, newLen + 1);
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

    /**
     * 返回新的数组长度
     * @param s
     * @return
     */
    private static int removeExtraSpaces(char[] s) {
        int slow = 0;
        int fast;
        boolean firstMeetSpace = true;
        for (fast = 0; fast < s.length; fast++) {
            if (s[fast] != ' ') { // 如果不是空格，则将后面的元素往前移动
                s[slow++] = s[fast];
                firstMeetSpace = true;
            } else {
                if (firstMeetSpace) { // 双指针法很容易弄错 指针的移动条件
                    if (fast == 0) {
                        s[slow] = s[++fast];
                        firstMeetSpace = false;
                    } else {
                        s[slow++] = s[fast];
                        firstMeetSpace = false;
                    }
                }
            }
        }
        return s.length - (fast - slow) - 1;
    }

    public static void main(String[] args) {
//        String s = "the sky   is blue";
        String s = "  hello world  ";
        String result = reverseWords(s);
        System.out.println(result);
    }
}
