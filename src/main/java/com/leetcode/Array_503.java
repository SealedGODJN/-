package com.leetcode;

public class Array_503 {
    public static void main(String[] args) {
//        int[] nums = {1,2,1};
        int[] nums = {1,2,3,4,3};
        int[] result = nextGreaterElements(nums);
        for(int i:result) {
            System.out.println(i);
        }
    }

    public static int[] nextGreaterElements(int[] nums) {
        int max = -Integer.MAX_VALUE;
        for(int i=0; i<nums.length; i++) {
            if(nums[i]>max) max = nums[i];
        }

        int[] result = new int[nums.length];

        for(int i=0; i<nums.length; i++) {
            int temp = nums[i];
            int i_max = -1;
            if(temp<max) {
                i_max = cal(nums,temp,i_max,i);
            }
            result[i] = i_max;
        }

        return result;
    }
    public static int cal(int[] nums, int temp, int i_max, int i){
        for(int j=i+1; j<nums.length; j++) {
            if(nums[j]>temp) {
                i_max = nums[j];
                temp = nums[j];
                return i_max;
            }
        }

        for(int j=0; j<i; j++) {
            if(nums[j]>temp) {
                i_max = nums[j];
                temp = nums[j];
                return  i_max;
            }
        }
        return i_max;
    }
}

//class Solution {
//    public int[] nextGreaterElements(int[] nums) {
//        int n = nums.length;
//        int[] ans = new int[n];
//        Arrays.fill(ans, -1);
//        Deque<Integer> d = new ArrayDeque<>();
//        for (int i = 0; i < n * 2; i++) {
//            while (!d.isEmpty() && nums[i % n] > nums[d.peekLast()]) {
//                int u = d.pollLast();
//                ans[u] = nums[i % n];
//            }
//            d.addLast(i % n);
//        }
//        return ans;
//    }
//}

// 问题：第59行的代码这么复杂，不便于维护，工程实践中是否需要写这种类型的代码呢？
// 我认为，不需要。代码题只是用来筛选人，在工程中，代码应该是越简单越好