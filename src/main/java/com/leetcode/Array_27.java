package com.leetcode;

public class Array_27 {
    public static int removeElement(int[] nums, int val) {
        int diff = 0;
        for(int i = 0; i<nums.length; i++) {
            if(nums[i] == val) {
                diff++;
                for(int j=i; j+1<nums.length; j++) {
                    nums[j] = nums[j+1];
                }
            }
        }
        return nums.length-diff;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 3, 2, 2};
        int val = 3;
        System.out.println(removeElement(nums, val));
    }
}
