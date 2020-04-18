package com.hjn.kuaiShou;

import java.util.List;
import java.util.Scanner;

public class Main {
	/**
	 * A-a+b
	 */
//	public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int[] input = new int[2];
//        while(in.hasNext()) {
//            for(int i = 0; i<=1; i++) {
//                input[i]=in.nextInt();
//            }
//            System.out.println(input[0]+input[1]);
//        }
//    }
	
	/**
	 * B-a+b
	 */
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		char[] input_char = input.toCharArray();
		int[] left_record = new int[input_char.length];
		int[] right_record = new int[input_char.length];
		int leftLength=0,rightLength=0;
		int a=0,b=0,c=0;
		for(int i=0; i<input_char.length; i++) {
			if(input_char[i] == '(') {
				left_record[leftLength] = i;
				leftLength++;
			}
			if(input_char[i] == ')') {
				right_record[rightLength] = i;
				rightLength++;
			}
		}
		int[] record = new int[leftLength]; // 记录用过哪些左括号
		int recordLength = 0, flag;
		for(int i=0; i<rightLength; i++) {
			flag = 0;
			for(int j=leftLength-1; j>=0; j--) {
				for(int k=0; k<recordLength; k++) {
					if(j == record[k]) {
						flag = 1;
					}
				}
				if(right_record[i]<left_record[j] && flag ==0) { // 左括号没用过 构成一对
					a++;
					record[i]=j;
					recordLength++;
					break;
				}
			}
		}
		b=leftLength-a;
		c=rightLength-a;
		System.out.println(a+" "+b+" "+c);
	}
}
