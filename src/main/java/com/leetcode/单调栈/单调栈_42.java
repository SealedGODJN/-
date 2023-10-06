package com.leetcode.单调栈;

import java.util.Deque;
import java.util.LinkedList;

public class 单调栈_42 {

    /**
     * 双指针？
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        // 第i个柱子 左边最高的柱子的高度
        int[] maxLeft = new int[height.length];
        // 第i个柱子 右边最高的柱子的高度
        int[] maxRight = new int[height.length];

        int maxL = 0;
        int maxR = 0;

        for (int i = 0; i < height.length; i++) {
            maxL = Math.max(height[i], maxL);
            maxLeft[i] = maxL;
        }

        for (int i = height.length - 1; i >= 0; i--) {
            maxR = Math.max(height[i], maxR);
            maxRight[i] = maxR;
        }

        int result = 0;
        for (int i = 0; i < height.length; i++) {
            int h = Math.min(maxLeft[i], maxRight[i]) - height[i];
            if (h > 0) result += h;
        }
        return result;
    }

//    /**
//     * 暴力解法
//     * @param height
//     * @return
//     */
//    public int trap(int[] height) {
//        int result = 0;
//
//        for (int i = 0; i < height.length; i++) {
//            int maxLeft = 0;
//            int maxRight = 0;
//            // 第一个柱子和最后一个柱子不接雨水
//            if (i == 0 || i == height.length - 1) continue;
//            // 计算左边最高
//            for (int j = i - 1; j >= 0; j--) {
//                maxLeft = Math.max(height[j], maxLeft);
//            }
//            // 计算右边最高
//            for (int j = i + 1; j < height.length; j++) {
//                maxRight = Math.max(height[j], maxRight);
//            }
//            int h = Math.min(maxLeft,maxRight) - height[i];
//            if (h > 0) result += h;
//        }
//        return result;
//    }

//    /**
//     * 单调栈解法
//     *
//     * @param height
//     */
//    public int trap(int[] height) {
//        if (height.length <= 2) return 0; // 可以不加
//
//        Deque<Integer> s = new LinkedList<>();
//        s.push(0);
//
//        int result = 0;
//
//        for (int i = 1; i < height.length; i++) {
//            if (height[i] < height[s.peek()]) {
//                s.push(i);
//            } else if (height[i] == height[s.peek()]) {
//                s.pop();
//                s.push(i);
//            } else {
//                while (!s.isEmpty() && height[i] > height[s.peek()]) {
//                    // 弹出栈的元素 小于 当前即将进栈的元素（即，找到了右边 离他最近 且 比他大 的元素）
//                    int mid = s.pop();
//                    if (!s.isEmpty()) {
//                        int left = s.peek();
//                        int h = Math.min(height[left], height[i]) - height[mid];
//                        int w = i - left - 1; // 注意减一，只求中间宽度（求数轴上点到另一个点的距离）
//                        result += h * w;
//                    }
//                }
//                s.push(i);
//            }
//        }
//        return result;
//    }

    public static void main(String[] args) {
        单调栈_42 test = new 单调栈_42();

        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        System.out.println(test.trap(height));
    }
}
