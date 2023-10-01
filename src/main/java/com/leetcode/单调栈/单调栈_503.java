package com.leetcode.单调栈;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class 单调栈_503 {
    public int[] nextGreaterElements(int[] nums) {
        Deque<Integer> s = new LinkedList<>();
        s.push(0);

        int[] result = new int[nums.length];
        Arrays.fill(result, -1);

        for (int i = 1; i < nums.length * 2; i++) {
            if (nums[i % nums.length] < nums[s.peek()]) {
                s.push(i % nums.length);
            } else if (nums[i % nums.length] == nums[s.peek()]) {
                s.push(i % nums.length);
            } else {
                while (!s.isEmpty() && nums[i % nums.length] > nums[s.peek()]) {
                    // 弹出栈的元素 小于 当前即将进栈的元素（即，找到了右边 离他最近 且 比他大 的元素）
                    result[s.peek()] = nums[i % nums.length];
                    s.pop();
                }
                s.push(i % nums.length);
            }
        }
        return result;
    }
}
