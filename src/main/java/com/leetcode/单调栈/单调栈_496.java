package com.leetcode.单调栈;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class 单调栈_496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> s = new LinkedList<>();
        s.push(0);

        int[] result = new int[nums1.length];

        // nums1 的 数 对应的下标
        Map<Integer, Integer> indexs = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            indexs.put(nums1[i], i);

            // 初始result
            result[i] = -1;
        }
        for (int i = 0; i < nums2.length; i++) {
            if (nums2[i] < nums2[s.peek()]) {
                s.push(i);
            } else if (nums2[i] == nums2[s.peek()]) {
                s.push(i);
            } else {
                while (!s.isEmpty() && nums2[i] > nums2[s.peek()]) {
                    // 弹出栈的元素 小于 当前即将进栈的元素（即，找到了右边 离他最近 且 比他大 的元素）
                    if (indexs.containsKey(nums2[s.peek()])) {
                        result[indexs.get(nums2[s.peek()])] = nums2[i];
                    }
                    s.pop();
                }
                s.push(i);
            }
        }
        return result;
    }
}
