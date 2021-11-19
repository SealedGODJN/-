package com.leetcode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HashMap_18 {
    /**
     * 计算四数之和
     *
     * @param nums 数组
     * @param target 目标值
     * @return 所有四个元素之和为target的4元组
     */
//    public static List<List<Integer>> fourSum(int[] nums, int target) {
////        Arrays.sort(nums);
////
////        List<List<Integer>> result = new ArrayList<>();
////
////        if (nums.length < 4) {
////            return null;
////        }
////
////        Set<Integer> first = new HashSet<>();
////        Set<Integer> second = new HashSet<>();
////        Set<Integer> third = new HashSet<>();
////
////        for (int i = 0; i < nums.length - 3; i++) {
////            if(!first.contains(nums[i]))
////                first.add(nums[i]);
////            for (int j = i + 1; j < nums.length - 2; j++) {
////                if(!second.contains(nums[j]))
////                    second.add(nums[j]);
////                for (int k = j + 1; k < nums.length - 1; k++) {
////                    if(!third.contains(nums[k]))
////                        third.add(nums[k]);
////                    int sum = nums[i]+nums[j];
////                    for (int l = k + 1; l < nums.length; l++) {
////                        if (sum == target - nums[l] - nums[k]) {
////                            List<Integer> combine = new ArrayList<>(4);
////                            combine.add(nums[i]);
////                            combine.add(nums[j]);
////                            combine.add(nums[k]);
////                            combine.add(nums[l]);
////                            result.add(combine);
////                        }
////                    }
////
////                }
////                third.clear();
////            }
////            second.clear();
////        }
////        return result;
//
//        Arrays.sort(nums);
//        List<List<Integer>> result = new ArrayList<>();
//        Set<Integer> first = new HashSet<>();
//        Set<Integer> second = new HashSet<>();
//        Set<Integer> third = new HashSet<>();
//
//        for(int a = 0; a < nums.length-3; a++){
//            if(!first.contains(nums[a])){
//                first.add(nums[a]);
//                for(int b = a+1; b < nums.length-2; b++){
//                    if(!second.contains(nums[b])){
//                        second.add(nums[b]);
//                        for(int c = b+1; c < nums.length-1; c++){
//                            if(!third.contains(nums[c])){
//                                third.add(nums[c]);
//                                int sum = nums[a]+nums[b]+nums[c];
//                                for(int d = c+1; d < nums.length; d++){
//                                    if(sum+nums[d] > target){
//                                        break;
//                                    }else if(sum+nums[d] == target){
//                                        List<Integer> combine = new ArrayList<>(4);
//                                        combine.add(nums[a]);
//                                        combine.add(nums[b]);
//                                        combine.add(nums[c]);
//                                        combine.add(nums[d]);
//                                        result.add(combine);
//                                        break;
//                                    }
//                                }
//                            }
//                        }
//                        third.clear(); // 在second开始add的时候，清空third
//                    }
//                }
//                second.clear(); // 在first开始add的时候，清空first
//            }
//        }
//        return result;
//    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
//        List<List<Integer>> result = new ArrayList<List<Integer>>();
//
//        Arrays.sort(nums);
//
//        int n = nums.length;
//
//        for (int i = 0; i < n - 3; i++) { // 要判断后三位
//            if (i>0 && nums[i] == nums[i-1]) continue; // 剪枝操作
//            if (nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target) continue; // 剪枝操作
//            if (nums[i] + nums[n-3] + nums[n-2] + nums[n-1] < target) continue; // 剪枝操作
//
//            for (int j = i + 1; j < n - 2; j++) { // 要判断后两位
//                if (nums[j] == nums[j-1]) continue; // 剪枝操作
//
//                if (nums[i] + nums[j] > target - nums[j + 1] - nums[j + 2]) break; // 剪枝操作
//
//                if (nums[i] + nums[j] < target - nums[n - 1] - nums[n - 2]) break; // 剪枝操作
//
//                int left = j + 1;
//                int right = n - 1;
//
//                while (left < right) {
//                    int sum1 = nums[i] + nums[j];
//                    int sum2 = target - nums[left] - nums[right];
//                    if (sum1 == sum2) {
//                        List<Integer> temp = Stream.of(nums[i], nums[j], nums[left], nums[right]).collect(Collectors.toList());
//                        result.add(temp);
//
//                        /**
//                         * 这一部分放里面和放外面有什么区别呢？
//                         */
//                        while (nums[left] == nums[left+1]) left++;
//                        left++;
//
//                        while (nums[right] == nums[right-1]) right--;
//                        right--;
//
//                    } else if (sum1 > sum2) right--;
//                    else left++;
//
//                    /**
//                     * 这一部分放里面和放外面有什么区别呢？
//                     * 区别在于
//                     * else if (sum1 > sum2) right--;
//                     * else left++;
//                     *
//                     * 这两句还可以再动态调整left和right的范围，让其不损失某一解
//                     */
////                    while (nums[left] == nums[left+1]) left++;
////                    left++;
////
////                    while (nums[right] == nums[right-1]) right--;
////                    right--;
//                }
//
//            }
//        }
//        return result;


        List<List<Integer>> quadruplets = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) {
            return quadruplets;
        }
        Arrays.sort(nums); // 从小到大排序
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 这种剪枝是错误的，这道题目target 是任意值
            // if (nums[k] > target) {
            //     return result;
            // }
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                // 这种剪枝是对的，最小的四个数相加>target，过大
                break;
            }
            if (nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                // 最大三个数相加都<target，过小
                continue;
            }
            for (int j = i + 1; j < length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) { // 剪枝（去重）
                    continue;
                }
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    // 已经确定了两个数，再拿最小的两个数相加>target，过大
                    break;
                }
                if (nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    // 已经确定了两个数，再拿最大的两个数相加<target，过小
                    continue;
                }
                int left = j + 1, right = length - 1;
                while (left < right) { // 开始使用双指针，根据target和sum的大小关系【控制】，调整两个指针的位置
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum < target) { // sum < target ，则需要数组中更大的数来参与四数之和
                        left++;
                    } else { // sum > target ，则需要数组中更小的数来参与四数之和
                        right--;
                    }
                }
            }
        }
        return quadruplets;
    }

    public static void main(String[] args) {
//        int[] nums = {1,0,-1,0,-2,2};
        int[] nums = {2,2,2,2,2,2};
        int target = 0;
        List<List<Integer>> result = fourSum(nums, target);

        result.forEach(integers -> integers.forEach(System.out::print));
    }
}
