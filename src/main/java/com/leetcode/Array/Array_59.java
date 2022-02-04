package com.leetcode.Array;

import java.util.ArrayList;
import java.util.List;

public class Array_59 {
//    /**
//     * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
//     * @param n 1 <= n <= 20
//     * @return matrix二维数组
//     * 解题方法：
//     * 参照54
//     */
//    public static int[][] generateMatrix(int n) {
//        int[][] matrix = new int[n][n];
//
////        List<Integer> result = new ArrayList<>();
//
//        int b = matrix[0].length;
//        int a = matrix.length;
//
//        if(n == 1) { // 特殊情况，matrix数组只有一个元素
//            matrix[0][0] = 1;
//            return matrix;
////            result.add(matrix[0][0]);
////            return result;
//        }
//
//        int[][] check = new int[a][b];
//        for(int i=0; i<a; i++) {
//            for(int j=0; j<b; j++) {
//                check[i][j] = 0;
//            }
//        }
//
//        int up = 0, down = a, left = 0, right =b;
//
//        int count = 1;
//
//        while(true) {
//            for(int i=left; i<right-1; i++) {
//                if(check[up][i] != 1) {
////                    result.add(matrix[up][i]); // 从最上面一层开始螺旋，从左到右
//                    matrix[up][i] = count;
//                    count++;
//                    check[up][i] = 1;
//                }
////                else return result;
//            }
//            for(int i=up; i<down-1; i++) {
//                if(check[i][right-1] != 1) {
////                    result.add(matrix[i][right - 1]); // 拐了一次，绕到右边了，从上到下
//                    matrix[i][right - 1] = count;
//                    count++;
//                    check[i][right-1] = 1;
//                }
////                else return result;
//            }
//            for(int i=right-1; i>left; i--) {
//                if(check[down-1][i] != 1) {
////                    result.add(matrix[down - 1][i]); // 拐了第二次，在底部，从右到左
//                    matrix[down - 1][i] = count;
//                    count++;
//                    check[down-1][i] = 1;
//                }
////                else return result;
//            }
//            for(int i=down-1; i>up; i--) {
//                if(check[i][left] != 1) {
////                    result.add(matrix[i][left]); // 拐了第三次，从底部往上，从下到上
//                    matrix[i][left] = count;
//                    count++;
//                    check[i][left] = 1;
//                }
////                else return result;
//            }
//
//            left++; // 经过一次旋转之后，缩小旋转范围
//            right--;
//            up++;
//            down--;
////            if(result.size() == a*b)
//            if(count == a*b + 1)
//                break; // 获取了全部的元素之后，退出该程序
////            if(result.size() == a*b -1) { // 特殊情况，如果matrix是正方形二维数组（a==b），则会出现只剩一个的情况
//            if(count == a*b) { // 特殊情况，如果matrix是正方形二维数组（a==b），则会出现只剩一个的情况
////                result.add(matrix[up][left]);
//                matrix[up][left] = count;
//                break;
//            }
//        }
//
//        return matrix;
//    }

    /**
     * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
     * @param n 1 <= n <= 20
     * @return matrix二维数组
     * 解题方法：
     * 动态边界法（每转一个反向，收缩边界）
     */
    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int left = 0, right = n-1, up = 0, down = n-1;
        int count = 1; // 从1开始，到n^2结束
        while(left<=right && up<=down) {
            for(int i=left; i<=right;i++){
                matrix[up][i] = count;
                count++;
            }
            up++;
            for(int i=up; i<=down; i++) {
                matrix[i][right] = count;
                count++;
            }
            right--;
            for(int i=right; i>=left; i--) {
                matrix[down][i] = count;
                count++;
            }
            down--;
            for(int i=down; i>=up; i--) {
                matrix[i][left] = count;
                count++;
            }
            left++;
            if(count == (n*n+1)) break;
        }
        return matrix;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] matrix = generateMatrix(n);
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}
