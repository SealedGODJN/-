package com.leetcode;

public class String_Offer_5 {
    /**
     * ⚠️
     * 不要使用split函数
     * 空间复杂度最好为1
     *
     * @param s
     * @return
     */
    public static String replaceSpace(String s) {
        char[] sArray = s.toCharArray();
        int len = s.length();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i < len; i++) {
            if (sArray[i] == ' ') {
                stringBuilder.append("%20");
            }
            else stringBuilder.append(sArray[i]);
        }

        return stringBuilder.toString();
    }

    /*
输入：
"     "
输出：
""
预期结果：
"%20%20%20%20%20"
     */

    public static void main(String[] args) {
//        String s = "We are happy.";
        String s = "   ";
        String result = replaceSpace(s);
        System.out.println(result);
    }
}
