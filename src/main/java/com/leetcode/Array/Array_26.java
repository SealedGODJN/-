package com.leetcode.Array;

public class Array_26 {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        // fast 初始化的时候应该是0，而不是1
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) {
                slow++;
                // 维护 nums[0..slow] 无重复
                nums[slow] = nums[fast];
            }
            fast++;
        }
        // 维护 nums[0..slow] 无重复
        return slow + 1;
    }

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};

        Array_26 array26 = new Array_26();
        int result = array26.removeDuplicates(nums);
        for (int i = 0; i < result; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
