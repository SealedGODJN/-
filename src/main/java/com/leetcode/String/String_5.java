package com.leetcode.String;

public class String_5 {
    public String longestPalindrome(String s) {
        String res = "";
        // 先确定回文串的中心
        for (int i = 0; i < s.length(); i++) {
            /**
             * 分情况
             * 1、回文串的长度：奇数
             * 2、回文串的长度：偶数
             */
            // 以 s[i] 为中心的最长回文子串
            String s1 = palindrome(s, i, i);
            // 以 s[i] 和 s[i+1] 为中心的最长回文子串
            String s2 = palindrome(s, i, i + 1);
            // res = longest(res, s1, s2)
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }

    // 从中心向两端扩散
    // 确定当前s的最长回文子串
    private String palindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // left可能会到达-1
        return s.substring(++left, right);
    }

    public static void main(String[] args) {
        String s = "baotto";

        String_5 string5 = new String_5();
        String result = string5.longestPalindrome(s);
        System.out.println(result);
    }
}
