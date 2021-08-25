package com.interview.ShenXinFu;

public class Solution {
    /**
     * 判断N是否可由2、5、7相乘得到，如果可以，返回1，否则返回0
     * @param N int整型 乘积
     * @return int整型
     */
    public static int is_product (int N) {
        // write code here
        if(N%2 == 0) {
            return 1;
        } else if(N%5 == 0) {
            return 1;
        } else if(N%7 == 0) {
            return 1;
        }
        return 0;
    }
    public static void main(String[] args) {
        System.out.println(is_product(21));
    }
}
