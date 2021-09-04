package com.leetcode;

import java.util.*;

public class Array_15 {

    public static List<List<Integer>> threeSum(int[] nums) {
        if(nums == null || nums.length < 3) {
            return null;
        }

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int length = nums.length;

        for(int i=1; i<length; i++) {
            int leftindex = i-1;
            int rightindex = i+1;
            while(leftindex != -1 && rightindex != length) {
                int sum = nums[leftindex] + nums[i] + nums[rightindex];
                if ( sum == 0) {
                    List<Integer> tempResult = new ArrayList<>();
                    tempResult.add(leftindex);
                    tempResult.add(i);
                    tempResult.add((rightindex));
                    result.add(tempResult);
//                    break;
                } else if(sum < 0) {
                    rightindex++;
                } else  if(sum > 0) {
                    leftindex--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
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
