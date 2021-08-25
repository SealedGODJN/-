package com.dataStructure.sort;

public class SelectionSort {
	public static void main(String[] args) {
		int[] array = {5,2,8,4,9,1};// 不考虑数组长度为1的情况
		
		for(int index=0; index<array.length; index++) {
			int temp=index;
			for(int j=index+1; j<array.length; j++) {
				if(array[j]<array[temp]) { // array[j]<array[index]改为array[j]<array[temp]
					temp = j;
				}
			}
			int array_temp=array[index];
			array[index]=array[temp];
			array[temp]=array_temp;
		}
		for(int x:array) {
			System.out.println(x);
		}
	}
}
