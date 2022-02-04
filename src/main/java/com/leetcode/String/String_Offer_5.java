package com.leetcode.String;

public class String_Offer_5 {
    /**
     * ⚠️
     * 不要使用split函数
     * 空间复杂度最好为1
     *
     * 此方法的时间复杂度为
     *
     * @param s
     * @return
     */
//    public static String replaceSpace(String s) {
//        char[] sArray = s.toCharArray();
//        int len = s.length();
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i=0; i < len; i++) {
//            if (sArray[i] == ' ') {
//                stringBuilder.append("%20");
//            }
//            else stringBuilder.append(sArray[i]);
//        }
//
//        return stringBuilder.toString();
//    }

    /*
输入：
"     "
输出：
""
预期结果：
"%20%20%20%20%20"
     */

    /**
     *
     * @param s
     * @return
     */
//    public static String replaceSpace(String s) {
//        char[] sArray = s.toCharArray();
//        int len = s.length();
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i=0; i < len; i++) {
//            if (sArray[i] == ' ') {
//                stringBuilder.append("%20");
//            }
//            else stringBuilder.append(sArray[i]);
//        }
//
//        return stringBuilder.toString();
//    }

    /**
     * 完成空间复杂度为O（1）的算法
     * @param s
     */
    public static String replaceSpace(String s) {
        char[] sArray = s.toCharArray();
        // 先判断数组中有几个空格？
        int sum = 0;
        for (int i = 0; i < sArray.length; i++) {
            if(sArray[i] == ' ') sum++;
        }
        char[] result = new char[sArray.length + sum*2]; // 创建新的且长度适合的字符数组存储result
        int j = result.length - 1;
        for (int i = sArray.length - 1; i >= 0 ; i--) {
            if (sArray[i] == ' ') {
                result[j--] = '0';
                result[j--] = '2';
                result[j--] = '%';
            } else {
                result[j--] = sArray[i];
            }
        }
        return new String(result);
    }

    public static void main(String[] args) {
        String s = "We are happy. ";
//        String s = "   ";
        String result = replaceSpace(s);
        System.out.println(result);
    }
}
