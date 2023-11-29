package com.leetcode.Greedy;

public class Greedy_55 {
    public boolean canJump(int[] nums) {
        if (nums.length == 1) return true; // 只有一个元素，就是能达到
        int maxJump = 0;
        for (int i = 0; i <= maxJump; i++) {
            maxJump = Math.max(maxJump, nums[i] + i);
            if (maxJump >= nums.length - 1) return true; // 说明可以覆盖到终点了
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 0};

        Greedy_55 test = new Greedy_55();
        System.out.println(test.canJump(nums));
    }
}
