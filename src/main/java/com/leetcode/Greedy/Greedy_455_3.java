package com.leetcode.Greedy;

import java.util.Arrays;

public class Greedy_455_3 {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int result = 0;
        int gIndex = 0;
        for (int j : s) {
            if (gIndex < g.length && g[gIndex] <= j) {
                result++;
                gIndex++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] g = {10, 9, 8, 7};
        int[] s = {5, 6, 7, 8};
        Greedy_455_3 test = new Greedy_455_3();
        System.out.println(test.findContentChildren(g, s));
    }
}
