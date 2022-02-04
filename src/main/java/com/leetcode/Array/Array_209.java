package com.leetcode.Array;

/**
 * @author HJN
 * @date 2021.5.10
 * @modify 2021.5.10
 */
public class Array_209 {
    /**
     * 递归实现，出问题，无法解决
     *
     * @modify 2021.5.10 20:38
     * 发现问题：题目要求的是“连续子数组”
     * 之前写的递归解法是：“非连续子数组”
     */
//    public static int minSubArrayLen(int target, int[] nums) {
//        int total = 0;
//        for(int i=0; i<nums.length; i++) {
//            if(nums[i]>=target) return 1;
//            total += nums[i];
//        }
//        if(total<target) return 0;
//        int level = 0;
//        int start = 0;
//        int min = nums.length;
//        return find(target, nums, start, level);
//    }
//
//    // 递归查找>=符合target的元素
//    public static int find(int target, int[] nums, int start, int level) {
//        int min = nums.length;
//        for(int i=start; i<nums.length; i++) {
//            int target_temp = target-nums[i];
//            for(int j=1; j<nums.length; j++) {
//                if(hasBigger(target_temp, nums, j)) return level;
//            }
//            level = find(target_temp, nums, start+1, level+1);
//            if(level<min) min = level;
//        }
//        return min;
//    }
//
//    // 将数组中的所有元素与target比较，得出最小的一个
//    public static boolean hasBigger(int target, int[] nums, int start) {
//        for(int i=start+1; i<nums.length; i++) {
//            if(nums[i]>target) return true;
//        }
//        return false;
//    }

//    // 暴力
//    public static int minSubArrayLen(int target, int[] nums) {
//        int total = 0;
//        int size = nums.length;
//        for(int i=0; i< size; i++) {
//            if(nums[i]>=target) return 1;
//            total += nums[i];
//        }
//        if(total<target) return 0;
//
//        int result = nums.length;
//        int length = nums.length;
//        for(int i=0; i< size; i++) {
//            int sum=0;
//            sum += nums[i];
//            for(int j=i+1; j<size; j++) {
//                sum += nums[j];
//                if( sum >= target ) {
//                    length = j-i+1;
//                    result = Math.min(result, length);
//                    break;
//                }
//            }
//        }
//        return result;
//    }

    public static int minSubArrayLen(int target, int[] nums) {
        int subSize = 0;
        int totalNum = 0;
        int curIndex = 0;
        int lastIndex = 0;
        do {
            if (totalNum >= target) {
                if (subSize == 0 || subSize > (lastIndex - curIndex)) {
                    subSize = lastIndex - curIndex;
                }
                totalNum = totalNum - nums[curIndex];
                curIndex++;
            } else {
                if (lastIndex < nums.length) {
                    totalNum += nums[lastIndex];
                    lastIndex++;
                }

            }
        } while ((lastIndex < nums.length || totalNum >= target));
        return subSize;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,2,4,3};
        int target = 7;
        System.out.println(minSubArrayLen(target, nums));
    }
}
