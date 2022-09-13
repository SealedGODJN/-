package com.dataStructure.sort;

public class FastSort {

	public static void main(String[] args) {
		int[] a = { 5,-1,10,3,30};
		QuickSort(a,0,a.length-1);
		for(int i:a) {
			System.out.print(i + " ");
		}
	}
	
	public static void QuickSort(int[] a,int L,int R) {
		if(L<R) {
			int S = Partition(a,L,R);
			QuickSort(a,L,S);
			QuickSort(a,S+1,R);
		}
	}
	
	public static int Partition(int[] a,int L,int R) {
		int i = L+1, j = R;
		while(true) {
			while(++i<=R && a[i]<a[L]);
			while(--j>=L && a[j]>a[L]);
			if(i>=j)break;
			/**
			 * swap(a[i],a[j])左右扫描未交叉
			 */
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
		// swap(a[L],a[j])
		int temp = a[L];
		a[L] = a[j];
		a[j] = temp;
		return j;
	}
}
