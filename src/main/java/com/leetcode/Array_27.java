package com.leetcode;

public class Array_27 {
    public static int removeElement(int[] nums, int val) {
        int index = 0; // 记录数组中元素等于val的个数
        int times = 0; // 记录循环的次数（i不能作为循环控制变量，times作为循环控制变量）
        for(int i = 0; i<nums.length; i++) { // 可以优化的点：nums.length变为可变长度的length，即nums.length-index

            int diff = 0;
            if(nums[i] == val) {
                index++;
                diff++;
                for(int j=i; j+1<nums.length; j++) {
                    nums[j] = nums[j+1];
                }
            }
            i-=diff;

            times++; // 控制循环，防止死循环，退出不了循环
            if(times==nums.length) break;
        }
        return nums.length-index;
    }

    /**
     * 双指针法
     * 双指针法（快慢指针法）：「通过一个快指针和慢指针在一个for循环下完成两个for循环的工作。」
     */
    public static int removeElement_two_pointers(int[] nums, int val) {
        int slow = 0; // 慢指针（对应于==val的情况，当nums[fast]==val时，slow不加加）
        int fast = 0; // 快指针（对应于普通的数组元素）
        for(; fast<nums.length; fast++) {
            if(nums[fast]!=val) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,2,2,3,0,4,2};
        int val = 2;
        System.out.println(removeElement(nums, val));
        System.out.println(removeElement_two_pointers(nums, val));
    }
}
