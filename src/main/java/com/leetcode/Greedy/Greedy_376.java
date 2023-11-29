package com.leetcode.Greedy;

public class Greedy_376 {
    /**
     * 1）画图
     * 2）发现删除“单调增/减区间”上的值，可以实现找出最长的子序列摆动数组
     * 3）统计“局部最值”即可得到最长的摆动数组
     * <p>
     * 情况三：单调坡度有平坡
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        } else if (nums.length == 2) {
            return 1 + nums[0] == nums[1] ? 0 : 1;
        }
        int result = 1;
        int pre = 0;
        int cur = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            cur = nums[i + 1] - nums[i];
            // 出现峰值
            if ((pre <= 0 && cur > 0) || (pre >= 0 && cur < 0)) {
                result++;
                pre = cur;
            }
//            // 实时更新了 prediff
//            pre = cur;
//            // 在 这个坡度 摆动变化的时候，更新 prediff 就行，这样 prediff 在 单调区间有平坡的时候 就不会发生变化，造成我们的误判。
        }
        return result;
    }
}
