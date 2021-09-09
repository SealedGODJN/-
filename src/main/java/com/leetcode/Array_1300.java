package com.leetcode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 * @author HJN13
 * @date 2020.6.15
 */
public class Array_1300 {
    /**
     * 给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组中所有大于 value 的值变成 value 后，数组的和最接近  target （最接近表示两者之差的绝对值最小）。
     *
     * 如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。
     *
     * 请注意，答案不一定是 arr 中的数字。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：arr = [4,9,3], target = 10
     * 输出：3
     * 解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
     * 示例 2：
     *
     * 输入：arr = [2,3,5], target = 10
     * 输出：5
     * 示例 3：
     *
     * 输入：arr = [60864,25176,27249,21296,20204], target = 56803
     * 输出：11361
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sum-of-mutated-array-closest-to-target
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
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
