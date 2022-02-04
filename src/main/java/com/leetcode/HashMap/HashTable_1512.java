package com.leetcode.HashMap;

import java.util.HashMap;
import java.util.Map;

public class HashTable_1512 {
    public static int numIdenticalPairs(int[] nums) {
        Map<Integer,Integer> record = new HashMap<Integer, Integer>();
        for(int i:nums) {
            record.put(i, record.containsKey(i)?(record.get(i)+1):1);
//            另一种写法
//            record.put(i, record.getOrDefault(i, 0)+1);
        }
        int sum = 0;
        for(Integer i:record.keySet()) {
            int v = record.get(i);
            sum += v*(v-1)/2;
        }
        return sum;
    }

    public static void main(String[] args) {
//        int[] nums = {1,2,3,1,1,3};
        int[] nums = {1,1,1,1};
        System.out.println(numIdenticalPairs(nums));
    }
}
