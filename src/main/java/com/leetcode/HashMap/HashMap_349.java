package com.leetcode.HashMap;

import java.util.HashSet;

public class HashMap_349 {
    public static int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        HashSet<Integer> nums = new HashSet<>();
        HashSet<Integer> result = new HashSet<>();
        for (int i : nums1) {
            nums.add(i);
        }
        for (int i : nums2) {
            if(nums.contains(i)) result.add(i);
        }
        int[] resultFinal = new int[result.size()];
        Integer[] temp = result.toArray(new Integer[0]);
        for (int i = 0; i < result.size(); i++) {
            resultFinal[i] = temp[i];
        }

        return resultFinal;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3};
        int[] nums2 = {2,2,2,4,5,1};
        int[] result = intersection(nums1, nums2);
        for (int i : result) {
            System.out.println(i);
        }
    }
}
