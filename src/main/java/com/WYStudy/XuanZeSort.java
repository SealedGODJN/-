package com.WYStudy;

public class XuanZeSort {
    public static void main(String[] args) {
        int[] a = {13, 22, -1, 43, 12};
        int temp;
//        int k = 0;
//        boolean isChange = false;
        // 选择法排序
//        for (int i = 0; i < 5; i++) {
//            isChange = false;
//            temp = a[i];
//            for (int j = i + 1; j < 5; j++) {
//                if (a[j] < temp) {
//                    temp = a[j];
//                    k = j;
//                    isChange = true;
//                }
//            }
//            if (isChange) {
//                a[k] = a[i];
//                a[i] = temp;
//            }
//        }
        for (int i = 0; i < 5; i++) {
            temp = a[i];
            for (int j = i + 1; j < 5; j++) {
                if (a[j] < temp) {
                    temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }
            }
        }
        for (int i : a) {
            System.out.print(i + " ");
        }
    }
}
