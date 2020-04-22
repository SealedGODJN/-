package com.hjn.huawei;

public class Solution {
	static int n = 4;
	static int[] bestx = new int[n];
	static int[] x = new int[] {0,1,2,3};
	static int[][] F = new int[][]{{0,2,3,2},{0,1,1,3}};;
	static int f1 = 0;
	static int f2 = 0;
	static int temp_f2 = 0;
	static int currentT = 0;
	static int bestT = 100;
	public static void swap(int t,int i) {
		int temp = x[t];
		x[t] = x[i];
		x[i] = temp;
	}
	
	public static void solve(int t) {
		if(t>=n) { // t控制层数
			if(currentT < bestT) {
				for(int i = 1; i <n; i++) {
					bestx[i] = x[i];
				}
				bestT = currentT;
			}
		}
		else {
			for(int i = t; i<n; i++) {
				f1 += F[0][x[i]]; // f1当前的处理时间
				temp_f2 = f2;
				f2 = Math.max(f2, f1) + F[1][x[i]];
				currentT += f2; // 不清楚总时间的计算方法
				if(currentT < bestT) {
					swap(t,i);
					solve(t+1);
					swap(t,i);
				}
				currentT -= f2; // 当前的处理时间 回溯
				f1 -= F[0][x[i]]; // f1回溯
				f2 = temp_f2; // f2回溯
			}
		}
	}
	
	public static void main(String[] args) {
		int t = 1;
		solve(t);
		for(int result:bestx) {
			System.out.println(result);
		}
		System.out.println(bestT);
	}
}
