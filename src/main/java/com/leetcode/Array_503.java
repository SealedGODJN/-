package com.leetcode;

public class Array_503 {
    /**
     * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
     *
     * 示例 1:
     *
     * 输入: [1,2,1]
     * 输出: [2,-1,2]
     * 解释: 第一个 1 的下一个更大的数是 2；
     * 数字 2 找不到下一个更大的数；
     * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
     * 注意: 输入数组的长度不会超过 10000。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/next-greater-element-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
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