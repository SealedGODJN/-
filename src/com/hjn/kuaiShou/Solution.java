package com.hjn.kuaiShou;

import java.util.*;

public class Solution {
    /**
     * 返回无重复幂因子的 N进制完美数之和的所有幂因子
     * @param R int整型 
     * @param N int整型 N进制
     * @return int整型一维数组
     */
    public int[] GetPowerFactor (int R, int N) {
        // write code here
    	
    	/**
    	 * version 1.0
    	 */
//    	int temp = 1;
//    	int length = 0;
//    	int sum = 0;
//    	ArrayList<Integer> record = new ArrayList<Integer>();
//    	for(;;length++) {
//    		sum+=temp;
//    		record.add(temp);
//    		temp *= N;
//    		if(sum == R) {
//    			break;
//    		}
//    		if(sum > R) {
//    			return null;
//    		}
//    	}
//    	int[] result = new int[length];
//    	for(int i = 0; i<length; i++) {
//    		result[i] = record.get(i);
//    	}
//    	return result;
    	
    	/**
    	 * version1.2
    	 */
//    	if(R == 1) {
//    		int[] result = {0};
//    		for(int i=0; i<result.length; i++) {
////        		result[i]=record.get(i);
//        		System.out.println(result[i]);
//        	}
//    		return result;
//    	}
//    	int temp1 = 1, temp2 = R;
//    	int length = 0;
//    	int l = 0; // 记录幂数的个数
//    	ArrayList<Integer> record = new ArrayList<Integer>();
//    	for(;; length++) {
//    		if(temp1 > R) {
//    			temp1 /= N;
//    			length--;
//    			break;
//    		} if(temp1 == R) {
//    			int[] result = {length};
//        		for(int j=0; j<result.length; j++) {
////            		result[i]=record.get(i);
//            		System.out.println(result[j]);
//            	}
//        		return result;
//    		}
//    		temp1 *= N;
//    	}
//    	for(; length>=0; length--) {
//    		temp2 = R - temp1;
//    		temp1 /= N;
//    		if(temp2%N == 0) {
//    			R=temp2;
//    			record.add(length);
//    			l++;
//    		}
//    		if(temp2 == 0) {
//    			break;
//    		}
//    	}
//    	if(temp2 != 0) return null;
//    	int[] result = new int[l];
//    	for(int i=0; i< result.length; i++) {
//    		result[i] = record.get(l-i-1);
//    		System.out.println(result[i]);
//    	}
//    	return result;

    	/**
    	 * version 1.1
    	 */
    	if(R == 1) {
    		int[] result = {0};
    		for(int i=0; i<result.length; i++) {
    			System.out.println(result[i]);
    		}
    		return result;
    	}
    	
    	/**
    	 * 错误：未考虑到N^M直接等于R
    	 */
    	int temp3 = 1;
    	int length1 = 0;
    	for(;; length1++) {
    		if(temp3 > R) {
    			temp3 /= N;
    			length1--;
    			break;
    		} if(temp3 == R) {
    			int[] result = {length1};
        		for(int j=0; j<result.length; j++) {
//            		result[i]=record.get(i);
            		System.out.println(result[j]);
            	}
        		return result;
    		}
    		temp3 *= N;
    	}
    	
    	int temp = 1 , temp2 = R;
    	int length = 0;
    	int l = 0;
    	ArrayList<Integer> record = new ArrayList<Integer>();
    	for(;;length++) {
    		temp2 = R - temp;
    		temp *= N;
    		if(temp2%N == 0) {
    			R = temp2;
    			record.add(length);
    			l++;
    		}
    		if(temp2 == 0) {
    			break;
    		}
    		if(temp2 < 0) {
    			return null;
    		}
    	}
    	int[] result = new int[l];
    	for(int i=0; i<result.length; i++) {
    		result[i]=record.get(i);
    	}
        return result;
    }
	
    /**
     * 根据顾客属性计算出顾客排队顺序
     * @param a int整型一维数组 顾客a属性
     * @param b int整型一维数组 顾客b属性
     * @return int整型一维数组
     */
//    public int[] WaitInLine (int[] a, int[] b) {
//        // write code here
//    	int[] record = new int[a.length];
//    	for(int i=0; i<a.length; i++) {
//    		
//    	}
//    }
	
    public static void main(String[] args) {
    	int R = 27;
    	int N = 3;
//    	int[] a= {8,9,7};
//    	int[] b= {5,8,3};
    	Solution s = new Solution();
    	s.GetPowerFactor(R, N);
//    	s.WaitInLine(a, b);
    }
}