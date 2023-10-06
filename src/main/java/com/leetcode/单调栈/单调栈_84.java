package com.leetcode.单调栈;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class 单调栈_84 {
    public int largestRectangleArea(int[] heights) {
        int size = heights.length;

        // 记录左边（第一个小于i的高度）的index
        int[] minLeftIndex = new int[size];
        // 记录右边（第一个小于i的高度）的index
        int[] minRightIndex = new int[size];

        minLeftIndex[0] = -1; // 注意这里初始化，防止下面while死循环
        for (int i = 0; i < size; i++) {
            int minLIndex = i - 1;
            while (minLIndex >= 0 && heights[minLIndex] >= heights[i]) minLIndex = minLeftIndex[minLIndex];
            minLeftIndex[i] = minLIndex;
        }

        minRightIndex[size - 1] = size; // 注意这里初始化，防止下面while死循环
        for (int i = size - 2; i >= 0; i--) {
            int minRIndex = i + 1;
            while (minRIndex < size && heights[minRIndex] >= heights[i]) minRIndex = minRightIndex[minRIndex];
            minRightIndex[i] = minRIndex;
        }

        int result = 0;
        for (int i = 0; i < size; i++) {
            int w = minRightIndex[i] - minLeftIndex[i] - 1;
            int h = heights[i];
            result = Math.max(result, w * h);
        }

        return result;
    }

    public static void main(String[] args) {
        单调栈_84 test = new 单调栈_84();

        int[] height = new int[67419];
        Arrays.fill(height, 8035);

        System.out.println(test.largestRectangleArea(height));
    }
}
