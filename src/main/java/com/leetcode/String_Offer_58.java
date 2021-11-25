package com.leetcode;

public class String_Offer_58 {
    public static String reverseLeftWords(String s, int n) {
        char[] array = s.toCharArray();
        reverseLeftRight(array, 0, n-1);
        reverseLeftRight(array, n, array.length - 1);
        reverseLeftRight(array, 0, array.length - 1);
        return new String(array);
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
        String s = "abcdef";
        int n = 2;
        System.out.println(reverseLeftWords(s, n));
    }
}
