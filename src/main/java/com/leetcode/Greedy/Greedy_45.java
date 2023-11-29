package com.leetcode.Greedy;

public class Greedy_45 {
    public int jump(int[] nums) {
        if (nums.length == 1) return 0;
        int curDistance = 0;    // 当前覆盖最远距离下标
        int ans = 0;            // 记录走的最大步数
        int nextDistance = 0;   // 下一步覆盖最远距离下标
        for (int i = 0; i < nums.length; i++) {
            nextDistance = Math.max(nums[i] + i, nextDistance);  // 更新下一步覆盖最远距离下标

            // i走到最远覆盖距离时，再更新ans（不能走一步就更新一次ans）
            if (i == curDistance) {
                ans++;
                curDistance = nextDistance;
                // 当前覆盖最远距到达集合终点，不用做ans++操作了，直接结束
                if (nextDistance >= nums.length - 1) break;
            }
        }
        return ans;
    }
}
