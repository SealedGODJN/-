package com.dataStructure.sort;


public class BubbleSort {
    /**
     * 交换排序
     * 1）根据比较结果，来对换两个记录在序列中的位置<br>
     * <p>
     * 1、冒泡排序
     * <p>
     * 2、快速排序
     */
    public static void BubbleSort(int[] A) {
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] > A[j]) {
                    swap(A, i, j);
                }
            }
        }

        for (int a : A) {
            System.out.print(a + " ");
        }
    }

    public static void swap(int[] A, int i, int j) {
        int temp = A[j];
        A[j] = A[i];
        A[i] = temp;
    }

    public static void main(String[] args) {
        int[] array = {3, -1, 0, -8, 2, 1}; // 不考虑数组长度为1的情况
        BubbleSort(array);
    }

}
