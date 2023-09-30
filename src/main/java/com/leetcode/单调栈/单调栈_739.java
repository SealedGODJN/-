package com.leetcode.单调栈;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class 单调栈_739 {
    public int[] dailyTemperatures(int[] temperatures) {
//        Stack<Integer> s = new Stack<Integer>();
        Deque<Integer> s = new LinkedList<>();
        s.push(0);

        int[] result = new int[temperatures.length];

        for (int i = 1; i < temperatures.length; i++) {
            if (temperatures[i] < temperatures[s.peek()]) {
                s.push(i);
            } else if (temperatures[i] == temperatures[s.peek()]) {
                s.push(i);
            } else {
                while (!s.isEmpty() && temperatures[i] > temperatures[s.peek()]) {
                    // 弹出栈的元素 小于 当前即将进栈的元素（即，找到了右边 离他最近 且 比他大 的元素）
                    result[s.peek()] = i - s.peek();
                    s.pop();
                }
                s.push(i);
            }
        }
        return result;
    }
}
