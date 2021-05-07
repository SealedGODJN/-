package com.leetcode;

public class Array_35 {
    public static int searchInsert(int[] nums, int target) {
        int last;
        int next;
        boolean last_check = false;
        boolean next_check = false;
        int index = 0; // 0 则是排在最前面

        // 特例1
        if(nums[0] > target) return 0;

        for(int i=0; i<nums.length; i++) {
            last = i;
            next = last+1;
            if(nums[i] == target) {index = i; break;}
            else {
                if(nums[last] < target) last_check = true;
                if(next < nums.length){
                    if(nums[next] > target) next_check = true;
                }
                else {index = nums.length; break;} // 特例2

                if(last_check && next_check) {index = next; break;}
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6};
        int target = 8;
        System.out.println(searchInsert(nums, target));
    }
}
