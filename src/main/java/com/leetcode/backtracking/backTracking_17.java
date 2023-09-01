package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class backTracking_17 {

    static String[] letterMap = {
            "", // 0
            "", // 1
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl", // 5
            "mno", // 6
            "pqrs", // 7
            "tuv", // 8
            "wxyz", // 9
    };

    List<String> result;

    StringBuilder path;

    public List<String> letterCombinations(String digits) {

        result = new ArrayList<>();
        path = new StringBuilder();
        if (digits.isEmpty()) return result;

        backTracking(digits, 0);
        return result;
    }

    /**
     * 先选取数字对应的字母 再
     * @param digits
     */
    public void backTracking(String digits, int num) {
        if (digits.length() == num) {
            result.add(path.toString());
            return;
        }
        int number = digits.charAt(num) - 48;

        String letter = letterMap[number];
        int length = letter.length();

        for (int i = 0; i < length; i++) {
            path.append(letter.charAt(i));
            // 递归
            backTracking(digits, num + 1);
            // 回溯
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        String digits = "234";

        backTracking_17 backTracking17 = new backTracking_17();
        List<String> result = backTracking17.letterCombinations(digits);

        result.forEach(item -> System.out.print(item + " "));
    }
}
