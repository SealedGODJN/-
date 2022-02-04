package com.leetcode.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * 2021-8-24
 * 重新开始刷题，每天一道，每天一小时
 * 54. 螺旋矩阵 I
 */
public class Array_54 {


    /**
     *
     * @param matrix
     * @return
     * 解题方法：
     * 按层处理，从最外层开始，依次螺旋遍历
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        int n = matrix[0].length;
        int m = matrix.length;

        if(m == 1 && n==1) { // 特殊情况，matrix数组只有一个
            result.add(matrix[0][0]);
            return result;
        }

        int[][] check = new int[m][n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                check[i][j] = 0;
            }
        }

        int up = 0, down = m, left = 0, right =n;

        while(true) {
            for(int i=left; i<right-1; i++) {
                if(check[up][i] != 1) {
                    result.add(matrix[up][i]); // 从最上面一层开始螺旋，从左到右
                    check[up][i] = 1;
                } else return result;
            }
            for(int i=up; i<down-1; i++) {
                if(check[i][right-1] != 1) {
                    result.add(matrix[i][right - 1]); // 拐了一次，绕到右边了，从上到下
                    check[i][right-1] = 1;
                } else return result;
            }
            for(int i=right-1; i>left; i--) {
                if(check[down-1][i] != 1) {
                    result.add(matrix[down - 1][i]); // 拐了第二次，在底部，从右到左
                    check[down-1][i] = 1;
                } else return result;
            }
            for(int i=down-1; i>up; i--) {
                if(check[i][left] != 1) {
                    result.add(matrix[i][left]); // 拐了第三次，从底部往上，从下到上
                    check[i][left] = 1;
                } else return result;
            }

            left++; // 经过一次旋转之后，缩小旋转范围
            right--;
            up++;
            down--;
            if(result.size() == m*n) break; // 获取了全部的元素之后，退出该程序
            if(result.size() == m*n -1) { // 特殊情况，如果matrix是正方形二维数组（m==n），则会出现只剩一个的情况
                result.add(matrix[up][left]);
                break;
            }
        }

        return result;
    }

    // 有趣的代码
//    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
//    res = []
//            while matrix:
//            # 削头（第一层）
//    res += matrix.pop(0)
//            # 将剩下的逆时针转九十度，等待下次被削
//            matrix = list(zip(*matrix))[::-1]
//            return res

    public static void main(String[] args) {
//        int[][] matrix = {{1,2,3,4},{4,5,6,5},{7,8,9,6},{10,11,12,9}};
        int[][] matrix = {{1}};
        List<Integer> result = spiralOrder(matrix);
        for(Integer i:result){
            System.out.println(i);
        }
    }
}
