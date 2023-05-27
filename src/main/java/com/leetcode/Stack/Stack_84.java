package com.leetcode.Stack;

/**
 * 柱状图中的最大矩形
 */
public class Stack_84 {
    public int largestRectangleArea(int[] heights) {
        if (heights == null) return 0;
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = -1;
        right[n - 1] = n;
        for (int i = 1; i < n; i++) {
            int tmp = i - 1;
            while (tmp >= 0 && heights[tmp] >= heights[i]) {
                tmp = left[tmp];
            }
            // 最大的放最右边
            // left[i] = -1，代表左侧所有柱子的高度都大于height[i]
            left[i] = tmp;
            // left[i]中的值代表（选择当前i的柱子heights[i]作为高，左边会有几个比heights[i]小的柱子？）
        }
        for (int i = n - 2; i >= 0; i--) {
            int tmp = i + 1;
            while (tmp < n && heights[tmp] >= heights[i]) {
                tmp = right[tmp];
            }
            // 最大的放最右边
            right[i] = tmp;
            // right[i] = 6 代表其他柱子都比heights[i]柱子高
            // right[i] = 6 - 比它高的柱子的数量
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            // right[i] - left[i] - 1 其中 -1 是因为left[i]初始化为-1（计算面积时，要消去-1）
            result = Math.max(result, (right[i] - left[i] - 1) * heights[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        Stack_84 stack84 = new Stack_84();
        System.out.println(stack84.largestRectangleArea(heights));
    }
}
