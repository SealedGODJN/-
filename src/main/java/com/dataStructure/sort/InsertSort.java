package com.dataStructure.sort;

public class InsertSort {
    /**
     * 直接插入排序
     * <p>
     * 每次从待排序序列中按顺序选取一个待排序记录，将其插入
     *
     * @param A 待排序的数组
     */
    public static void insertSort(int[] A) {
        for (int i = 1; i < A.length; i++) {
            int temp = A[i];
            for (int j = i - 1; j >= 0; j--) {
                if (A[j] > temp) {
                    // A[j]往前移动一个位置
                    A[j + 1] = A[j];
                    A[j] = temp;
                } else {
                    // 不用排，直接放到最后一个位置
                    break;
                }
            }
        }
        for (int a : A) {
            System.out.print(a + " ");
        }
    }

    /**
     * 王道上的数组A的第0个位置不存储元素，用于临时记录要插入的元素
     *
     * @param A 待排序的数组
     */
    public static void insertSort_New(int[] A) {
        int i, j;
        for (i = 1; i < A.length; i++) {
            // 0 ~ i-1 的元素已经排好
            if (A[i] < A[i - 1]) {
                A[0] = A[i];
                // 从后往前查找待插入的位置
                for (j = i - 1; A[0] < A[j]; j--) {
                    // 大于A[i]的元素都要往后移动一格
                    A[j + 1] = A[j];
                }
                // 复制到插入的位置
                A[j + 1] = A[0];
            }

        }
        for (int a : A) {
            System.out.print(a + " ");
        }
    }

    public static void main(String[] args) {
        int[] array = {0, 3, -1, 0, -8, 2, 1}; // 不考虑数组长度为1的情况
//        insertSort(array);
//        System.out.println();
        insertSort_New(array);
    }
}
