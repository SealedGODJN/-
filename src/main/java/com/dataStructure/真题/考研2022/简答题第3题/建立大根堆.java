package com.dataStructure.真题.考研2022.简答题第3题;

public class 建立大根堆 {
    public static void BuildMaxHeap(int[] A, int len) {
        // 对所有的非叶子节点进行调整
        for (int i = len / 2; i > 0; i--) {
            HeadAdjust(A, i, len);
        }
    }

    public static void HeadAdjust(int[] A, int k, int len) {
        // 0的位置不放任何元素，因此可以作为临时存放的位置
        A[0] = A[k];
        for (int i = 2 * k + 1; i <= len; i *= 2) { // 取得是i的左孩子2k+1
            if (i < len && A[i] < A[i + 1]) {
                i++; // 取左孩子和右孩子中更大的
            }
            if (A[0] >= A[i]) break; // 满足大根堆的定义
            else {
                // 不满足大根堆的定义
                A[k] = A[i]; // 将A[i]调整到双亲节点上
                k = i; // 修改k值，便于向下继续筛选
            }
        }
        A[k] = A[0];
    }

    public static void main(String[] args) {
        int[] A = {0, 53, 17, 78, 9, 45, 65, 87, 32};
        BuildMaxHeap(A, A.length - 1);
        for (int i = 1; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
    }
}
