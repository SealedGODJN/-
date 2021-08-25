package com.dataStructure.sort;

public class InsertSort {
	public static void main(String[] args) {
		int[] array = {3,-1,0,-8,2,1}; // 不考虑数组长度为1的情况
		for(int index=1;index<array.length;index++) {
			int temp = array[index];
			for(int j=index-1; j>=0; j--) {
				if(array[j]>temp) {
					array[j+1]=array[j];
					array[j]=temp;
				} else {
					break;
				}
			}
		}
		for(int a:array) {
			System.out.println(a);
		}
	}
}
