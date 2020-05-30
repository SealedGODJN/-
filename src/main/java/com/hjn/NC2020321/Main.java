package com.hjn.NC2020321;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		// 获取输入
		String firstLine = in.nextLine();
		int N = firstLine.charAt(0) - 48; // int被存为char 以ASCII值存入 要减去48
		int M = firstLine.charAt(2) - 48; // int被存为char 以ASCII值存入 要减去48
		int[] scores = new int[N];
		int[][] action = new int[M][3];
		
		// 获取初始成绩
		String secondLine = in.nextLine();
		String[] score = secondLine.split(" ");
		for (int i = 0; i < N; i++) {
			scores[i] = Integer.parseInt(score[i]);
		}
		// 获取U或者Q操作
//		for (int i = 0; i < M; i++) {
//			for (int j = 0; j <= 2; j++) {
//				action[i][j] = in.nextInt();
//			}
//		}
		int x = 0;
		while(in.hasNextLine()) {
			String s = in.nextLine();
			char[] chars = s.toCharArray();
			for(int j=0; j<=2; j++) {
				if(j == 0) {
					action[x][j] = chars[j*2];
				} else action[x][j] = chars[j*2] - 48;
			}
			
//			for (int j = 0; j <= 2; j++) {
//				action[x][j] = in.nextInt();
//			}
			x++;
			if(x==M) break;
		}
		in.close();
		
		// 处理输入
		for (int i = 0; i < M; i++) {
			int[] scores_temp = new int[N];
			// 复制一份scores，action对备份操作，不改变scores的对应关系
			for (int j = 0; j < N; j++) {
				scores_temp[j] = scores[j];
			}
			if (action[i][0] == 81) { // 处理 U
				int temp = scores_temp[action[i][1] - 1];
				// 通过比较 将最大值放在action[i][1]-1的位置处
				for (int l = action[i][1]; l < action[i][2]; l++) {
					if (scores_temp[l] > temp) {
						scores_temp[action[i][1] - 1] = scores_temp[l];
						scores_temp[l] = temp;
						temp = scores_temp[action[i][1] - 1];
					}
				}
				System.out.println(scores_temp[action[i][1] - 1]); // 输出当中的最大值
			} else if (action[i][0] == 85) { // 处理 Q
				scores[action[i][1] - 1] = action[i][2];
			}
		}
	}
}