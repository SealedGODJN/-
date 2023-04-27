package com.leetcode.Greedy;

import java.util.Arrays;
import java.util.Comparator;

public class Greedy_455 {
    /**
     * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。<br>
     * 如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。<br>
     * 你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     *
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        int result = 0;
        Arrays.sort(g);
        Arrays.sort(s);

        int index = s.length - 1; // 饼干数组的下标
        for (int i = g.length - 1; i >= 0; i--) {
            if (index >= 0 && s[index] >= g[i]) {
                result++;
                index--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] g = {1, 2};
        int[] s = {1, 2, 3};
        Greedy_455 greedy455 = new Greedy_455();
        System.out.println(greedy455.findContentChildren(g, s));
    }
}
