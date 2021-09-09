package com.leetcode;

import java.util.*;

public class Array_15 {

    /**
     * 如果是暴力解法，三个数则是O(n^3)
     * 使用排序+双指针解法，时间达到了O(n^2)
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        Set<List<Integer>> resultTemp = new HashSet<>();
        if(nums == null || nums.length < 3) {
            return result;
        }


        Arrays.sort(nums);
        int length = nums.length;

        for(int i=1; i<length; i++) {
            int laststatus = 0;
            int leftindex = i-1;
            int rightindex = i+1;
            while(leftindex != -1 && rightindex != length) {
                int sum = nums[leftindex] + nums[i] + nums[rightindex];
                if ( sum == 0) {
                    List<Integer> tempResult = new ArrayList<>();
                    tempResult.add(nums[leftindex]);
                    tempResult.add(nums[i]);
                    tempResult.add((nums[rightindex]));
                    resultTemp.add(tempResult);

                    if(laststatus == 0) {
                        rightindex++;
                    } else if(laststatus ==1) {
                        leftindex--;
                    }

//                    break;
                } else if(sum < 0) {
                    laststatus = 0;
                    rightindex++;
                } else  if(sum > 0) {
                    laststatus = 1;
                    leftindex--;
                }
            }
        }
        result = new ArrayList<>(resultTemp);
        return result;
    }

    // 错误1
    //输入：
    //[0,0,0,0]
    //输出：
    //[[0,0,0],[0,0,0],[0,0,0]]
    //预期结果：
    //[[0,0,0]]

    public static void main(String[] args) {
//        int[] nums = {-1,0,1,2,-1,-4};
        int[] nums = {0,0,0,0};
        List<List<Integer>> result = threeSum(nums);
        for (List<Integer> line :
                result) {
            System.out.println();
            for (Integer element :
                    line) {
                System.out.print(element);
            }
        }
    }
}
