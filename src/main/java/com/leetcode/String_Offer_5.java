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
        String[] sArray = s.split("\\s");
        int len = s.length();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<sArray.length; i++) {
            stringBuilder.append(sArray[i]);

            if (i != sArray.length - 1)
                stringBuilder.append("%20");
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String s = "We are happy.";
        String result = replaceSpace(s);
        System.out.println(result);
    }
}
