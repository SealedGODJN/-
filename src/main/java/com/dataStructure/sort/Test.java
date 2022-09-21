package com.dataStructure.sort;

public class Test {
    private static int Partition(int[] A, int i, int j) {
        int key = A[i];
        while (i < j) {
            // key右边的元素，如果大于key，则留在右边
            // 如果小于key，则退出循环，与start位置的元素进行交换（因为start位置的元素是key）
            while (A[j] >= key && j > i) j--;
//            A[i] = A[j];
            if (j > i) {
                A[i] = A[j];
                i++;
            }

            // key左边的元素，如果小于key，则留在左边
            // 如果大于key，则与end位置的元素进行交换（因为如果start<j，还会继续一轮循环）
            while (A[i] <= key && j > i) i++;
//            A[j] = A[i];
            if (j > i) {
                A[j] = A[i];
                j--;
            }
        }
        // start位置的元素，左侧小于key，右侧都大于key（虽然左侧的元素不一定有序，右侧的元素也不一定有序）
        A[i] = key;
        return i;
    }

    public static void quickSort(int[] A, int start, int end) {
        if (start < end) {
            // 第i次排好的
            int index = Partition(A, start, end);
            quickSort(A, start, index - 1);
            quickSort(A, index + 1, end);
        }
    }

    public static void main(String[] args) {
        int[] A = new int[]{1, 3, 2, 9, 7, 4, 6, 8, 20, 17, 25, 21};
        quickSort(A, 0, A.length - 1);
        for (int temp : A) {
            System.out.print(temp + " ");
        }
    }
}
