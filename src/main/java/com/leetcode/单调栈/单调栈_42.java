package com.leetcode.单调栈;

import java.util.Deque;
import java.util.LinkedList;

public class 单调栈_42 {
    public int trap(int[] height) {
        if (height.length <= 2) return 0; // 可以不加

        Deque<Integer> s = new LinkedList<>();
        s.push(0);

        int result = 0;

        for (int i = 1; i < height.length; i++) {
            if (height[i] < height[s.peek()]) {
                s.push(i);
            } else if (height[i] == height[s.peek()]) {
                s.pop();
                s.push(i);
            } else {
                while (!s.isEmpty() && height[i] > height[s.peek()]) {
                    // 弹出栈的元素 小于 当前即将进栈的元素（即，找到了右边 离他最近 且 比他大 的元素）
                    int mid = s.pop();
                    if (!s.isEmpty()) {
                        int left = s.peek();
                        int h = Math.min(height[left], height[i]) - height[mid];
                        int w = i - left - 1; // 注意减一，只求中间宽度（求数轴上点到另一个点的距离）
                        result += h * w;
                    }
                }
                s.push(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        单调栈_42 test = new 单调栈_42();

        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        System.out.println(test.trap(height));
    }
}
