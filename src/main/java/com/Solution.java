package com;

public class Solution {
    public static void main(String[] args) {
        int[] A = { 3 , 2, 11 , 33, 22 , 5, -1};
        int n = A.length;
        for(int i=0; i<n; i++)
            System.out.println(A[i]);
//        for(int i = 0; i<n; i++) {
//            for(int j = i; j<n; j++) {
//                if(A[i]>A[j]) { // 这一步不能叫做“比较相邻两个记录之间的比较和交换”
//                    int temp = A[i];
//                    A[i] = A[j];
//                    A[j] = temp;
//                }
//            }
//        }
        for(int i = 0; i<n-1; i++) {
            for(int j=0; j<n-1-i; j++) {
                if(A[j]>A[j+1]) { // 相邻的两个记录进行比较
                    int temp = A[j];
                    A[j] = A[j+1];
                    A[j+1] = temp;
                }
            }
        }
        for(int i=0; i<n; i++)
            System.out.println(A[i]);
    }
}
