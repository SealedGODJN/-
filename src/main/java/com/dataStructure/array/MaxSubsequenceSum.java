package com.dataStructure.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Scanner;

/**
 * @author HJN
 *
 */
public class MaxSubsequenceSum {
	public static void main(String[] args) throws NumberFormatException, IOException {
//		Scanner in = new Scanner(System.in);
//		int K = in.nextInt();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		String s[] = br.readLine().split(" ");
		int[] N = new int[K];
		int judge1 = 1, judge2 = 1;
		for(int i=0; i<K; i++) {
//			N[i]=in.nextInt();
			N[i]=Integer.parseInt(s[i]);
			if(N[i]>0) judge1 = 0; // 考虑全是非正数情况
			if(N[i] == 0) judge2 = 0; // 考虑有0
		}
		if(judge1 == 1 && judge2 == 0) { // 有 0  且 剩下的为负数 
			System.out.println("0 0 0");
			return;
		}
		
		int thisSum = 0, maxSum = 0;
		int first=0,end=K-1;
		for(int i=0; i<K; i++) {
			thisSum+=N[i];
			if(thisSum>maxSum) {
				maxSum=thisSum;
				end=i;
			}
			if(thisSum<0) {
				thisSum=0;
			}
		}
//		int temp = maxSum;
//		for(int i=end; i>=0; i--) {
//			temp -= N[i];
//			if(temp==0) { // 如果减到小于0，说明
//				first = i;
//				break;
//			}
//		}
		if(maxSum > 0) {
			thisSum = 0;
	        for(int j = end;j>=0;j--){
	            thisSum += N[j];
	            if(thisSum==maxSum){
	                first = j;
	                break;
	            }
	        }
		}
        System.out.println(maxSum+" "+N[first]+" "+N[end]);
	}
}


/**
 * 待解决问题：
 * 
 * 最大和前面有一段是0——答案错误
 * 分析：是否要从0开始计？
 * 网上回答：要把0包括在其中
 * 参考：https://www.cnblogs.com/Learn-Excel/p/12640708.html
 * 
 * 最大N——运行超时
 * 分析：算法复杂度过高？
 * 网上回答：Scanner 输入效率太低，改成BuffferedReader 最后一个测试点就能过了。
 */