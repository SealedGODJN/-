package com.leetcode;

/**
 * @author HJN
 * @date 2021.5.8
 * @modify 2021.5.9
 */
public class Array_35 {

    /**
     * 暴力解法
     * @param nums
     * @param target
     * @return 如果target在nums中出现，则返回nums中元素大小=target的index；如果没有出现，则返回target应该插入到nums中的位置的index
     *
     * 找出所有可能的情况：
     * 目标值在数组所有元素之前 特例1
     * 目标值等于数组中某一个元素
     * 目标值插入数组中的位置
     * 目标值在数组所有元素之后 特例2
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
            if(nums[i] == target) {
                index = i;
                break;
            }
            else {
                if(nums[last] < target) last_check = true; // 检查target是否比上一个元素大
                if(next < nums.length){
                    if(nums[next] > target) next_check = true; // 检查target是否比下一个元素小
                }
                else {
                    index = nums.length;
                    break;
                } // 特例2

                if(last_check && next_check) { // 两个条件都符合，则可以确定target的位置
                    index = next;
                    break;
                }
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
            // 防止溢出 等同于(left + right)/2

            if(nums[mid] == target) return mid; // 数组中找到目标值的情况，直接返回下标
            else if(nums[mid] < target) low = mid +1; // target 在右区间，所以[middle + 1, right]
            else if(nums[mid] > target) high = mid-1; // target 在左区间，所以[left, middle - 1]
        }
        return high+1;
    }

    /**
     * 二分法
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert_binarySearch_2(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n; // 定义target在左闭右开的区间里，[left, right)  target
        while (left < right) { // 因为left == right的时候，在[left, right)是无效的空间
            int middle = left + ((right - left) >> 1);
            if (nums[middle] > target) {
                right = middle; // target 在左区间，在[left, middle)中
            } else if (nums[middle] < target) {
                left = middle + 1; // target 在右区间，在 [middle+1, right)中
            } else { // nums[middle] == target
                return middle; // 数组中找到目标值的情况，直接返回下标
            }
        }
        // 分别处理如下四种情况
        // 目标值在数组所有元素之前 [0,0)
        // 目标值等于数组中某一个元素 return middle
        // 目标值插入数组中的位置 [left, right) ，return right 即可
        // 目标值在数组所有元素之后的情况 [left, right)，return right 即可
        return right;
    }

    // 最简洁的写法
    // 将四种情况结合起来考虑
    public static int searchInsert_simple(int[] nums, int target) {
        for(int i = 0; i < nums.length;i++){
            if(nums[i] >= target){
                return i;
            }
        }
        return nums.length;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6};
        int target = 4;
        System.out.println(searchInsert(nums, target));
        System.out.println(searchInsert_binarySearch(nums, target));
    }
}
