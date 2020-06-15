package com.leetcode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class FindBestValue_1300 {
    public static void main(String[] args) {
        int[] arr = {1547,83230,57084,93444,70879};
        int target = 71237;
        System.out.println(findBestValue(arr, target));
//        double d1 = 113.4;
//        double d2 = 113.5;
//        double d3 = 113.56;
//        System.out.println(Math.round(d1));
//        System.out.println(Math.round(d2));
//        System.out.println(Math.round(d3)); // double转int类型按4舍5入取整
//        System.out.println(formatDouble(d1));
//        System.out.println(formatDouble(d2));
//        System.out.println(formatDouble(d3));
    }

    public static int findBestValue(int[] arr, int target) {
        int arr_size = arr.length;
        Arrays.sort(arr);
        double m = 0;
        for(int i=0; i< arr_size; i++) {
            m = (double)target/(arr_size - i);
            int m_up = (int)m;
            if(m-m_up > 0.5) {
                m_up++;
            }
            if(arr[i] > m_up) {
                return m_up;
            } else {
                target -= arr[i];
            }
        }
        return arr[arr_size-1];
    }
//    public static double formatDouble(double d) {
//        BigDecimal bd = new BigDecimal(d).setScale(0, RoundingMode.UP);
//        return bd.doubleValue();
//    }
}
