package com.leetcode.Greedy;

import java.util.Arrays;

public class Greedy_455_2 {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int result = 0;
        int sIndex = 0;
        for (int i = 0; i < g.length; i++) {
            while (sIndex < s.length && g[i] > s[sIndex]) sIndex++;
            if (sIndex == s.length) break;
            if (g[i] <= s[sIndex]) {
                result++;
                if (sIndex == s.length - 1) break;
                sIndex++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] g = {10, 9, 8, 7};
        int[] s = {10, 9, 8, 7};
        Greedy_455_2 test = new Greedy_455_2();
        System.out.println(test.findContentChildren(g, s));
    }
}
