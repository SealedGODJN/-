package com.leetcode;

public class Array_35 {

    /**
     * 暴力解法
     * @param nums
     * @param target
     * @return
     */
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

    /**
     * 二分法
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert_binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;

        // 特例1
        if(nums[0] > target) return 0;
        // 特例2
        if(nums[nums.length-1] < target) return nums.length;

        while(low<=high) {
            int mid = low+(high-low) / 2; // 一直困扰我的：偶数长度的数组和奇数长度的数组，mid的计算公式是否相同？

            if(nums[mid] == target) return mid;
            else if(nums[mid] < target) low = mid +1;
            else if(nums[mid] > target) high = mid-1;
        }
        return high+1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6};
        int target = 4;
        System.out.println(searchInsert(nums, target));
        System.out.println(searchInsert_binarySearch(nums, target));
    }
}
