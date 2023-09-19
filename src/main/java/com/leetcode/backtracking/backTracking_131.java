package com.leetcode.backtracking;

import java.util.*;

public class backTracking_131 {

    List<List<String>> result = new ArrayList<>();

    List<String> path = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backTracking(s, 0);
        return result;
    }

    /**
     *
     * @param s 字符串
     * @param startIndex 是切割线
     */
    public void backTracking(String s, int startIndex) {
        // 终止条件
        if (startIndex >= s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {

            // 每一层切割的子串 s.substring(startIndex, i)
            if(isPalindrome(s, startIndex, i)) {
                // 如果是回文
                path.add(s.substring(startIndex, i + 1));
            } else {
                continue;
            }
            // 切割过的地方不需要重复切割
            backTracking(s, i + 1);

            // 移除最后一项
            path.remove(path.size() - 1);
        }
    }

    /**
     * 判断字符串是否是回文
     *
     * @return
     */
    private boolean isPalindrome(String s, int start, int end) {
        // 切割出来，只剩一个字母，一定是回文串
//        if (start == end) return false;
        // 双指针
        for (int i = start, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
