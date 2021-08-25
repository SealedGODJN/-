package com.dataStructure.array;

import java.util.Scanner;

/**
 * @author HJN
 *
 */
public class MaxSubsequence {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int K = in.nextInt();
		int[] N = new int[K];
		for(int i=0; i<K; i++) {
			N[i]=in.nextInt();
		}
		
		int thisSum = 0, maxSum = 0;
		for(int i=0; i<K; i++) {
			thisSum+=N[i];
			if(thisSum>maxSum) maxSum=thisSum;
			if(thisSum<0) thisSum=0;
		}
		System.out.println(maxSum);
	}
}
