package com.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Stack_239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 存储的是编号
        Deque<Integer> Q = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; ++i) {
            // 去尾
            // 队头一直是最大值
            while (!Q.isEmpty() && nums[Q.getLast()] < nums[i]) {
                Q.removeLast();
            }
            Q.addLast(i);

            // 队列内的元素达到滑动窗口的大小，才开始取解
            if (i>= k - 1) {
                // 删头
                while (!Q.isEmpty() &&  Q.getFirst() < i-k+1) {
                    Q.removeFirst();
                }

                // 取解
                result[i - k + 1] = nums[Q.getFirst()];
            }

//            if (Q.isEmpty()) Q.addLast(V[i]);
//            else if (Q.getLast() > V[i]) {
//                Q.addLast(V[i]);
//            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
//        int[] nums = {9,11};
        int k = 2;
//        int[] result = new int[nums.length - k + 1];

        Stack_239 stack_239 = new Stack_239();
        int[] result = stack_239.maxSlidingWindow(nums, k);

        for (int i : result) {
            System.out.print(i + " ");
        }

    }
}

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int max = Integer.MIN_VALUE;
        int[] arr = new int[len - k + 1];
        int left = 0, right = k - 1, prex = -1; // left 和 right 维护滑动窗口的大小
        while (right < len) {
            //最大值仍在滑动窗口中
            if (left <= prex) {
                //比较新滑入的值和目前的最大值哪个大，如果新滑入的大，则更新最大值与prex坐标，反之结束if语句
                if (nums[right] >= max) {
                    max = nums[right];
                    prex = right;
                }
            }
            //最大值不在滑动窗口了，但是新滑入的值大于等于最大值（减一是避免数组刚开始时就直接错误得到的滑动窗口最右端的值作为最大值）
            else if (nums[right] >= max - 1) {
                max = nums[right];
                prex = right;
            }
            //最大值不在滑动窗口，但是滑动窗口最左端的值大于等于最大值（减一是避免数组刚开始时就直接错误得到的滑动窗口最左端的值作为最大值）
            else if (nums[left] >= max - 1) {
                max = nums[left];
                prex = left;
            } else { // 寻找滑动窗口中的最大值
                max = Integer.MIN_VALUE;
                for (int i = left; i <= right; i++) {
                    if (nums[i] >= max) {
                        max = nums[i];
                        prex = i;
                    }
                }
            }
            arr[left] = max;
            left++;
            right++;
        }
        return arr;
    }
}
