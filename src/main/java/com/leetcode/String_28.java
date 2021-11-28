package com.leetcode;

public class String_28 {
    /**
     * 返回第一次出现的位置
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr(String haystack, String needle) {
        if (needle.equals("")) return 0;
        if (needle.length() > haystack.length()) return -1;

        char[] haystackArray = haystack.toCharArray();
        char[] needleArray = needle.toCharArray();

        int[] next = genNext(needleArray); // 构造next数组

        int j = 0; // needle从头开始匹配
        for (int i = 0; i < haystackArray.length; i++) {
//            int iTemp = i;
//            if (needleArray[j] == haystackArray[i]) {
//                int jTemp = j;
//                for (; j < needleArray.length; j++) {
//                    if (needleArray[j] != haystackArray[iTemp]) {
//                        j = next[j-1]; // 保留j的状态
//                        i = i + iTemp - 1; // 保留i的状态
//                        break;
//                    }
//
//                    iTemp++;
//
//                    int temp = j;
//                    if (++temp == needleArray.length) return i - jTemp; // 匹配完了
//                    if (iTemp == haystackArray.length) { // 超出 待匹配的字符串的长度
//                        return -1;
//                    }
//                }
//                if (j == needleArray.length) { // 有可能超出数组长度
//                    j = next[j-1];
//                }
//            } else j = 0;
            while (j>0 && haystackArray[i] != needleArray[j]) {
                j = next[j-1];
            }
            if (haystackArray[i] == needleArray[j]) j++;
            if (j == needle.length()) return i - needle.length() + 1;
        }
        return -1; // 不存在则返回-1
    }

    private static int[] genNext(char[] needle) {
        int j = 0;
        int[] next = new int[needle.length];
        for(int i = 1; i < next.length; i++) {
            while ( j > 0 && needle[j] != needle[i]) j = next[j-1]; // 找到最初始的j
            if (needle[j] == needle[i]) j++;
            next[i] = j;
        }
        return next;
    }


    public static void main(String[] args) {
//        String haystack = "hlello";
//        String needle = "ll";
//        String haystack = "aaaaaa";
//        String needle = "bba";

//        String haystack = "bbababbba";
//        String needle = "bbb";

//        String haystack = "mississippi";
//        String needle = "issip";

//        String haystack = "aabaaabaaac";
//        String needle = "aabaaac";

        String haystack = "abbabaaaabbbaabaabaabbbaaabaaaaaabbbabbaabbabaabbabaaaaababbabbaaaaabbbbaaabbaaabbbbabbbbaaabbaaaaababbaababbabaaabaabbbbbbbaabaabaabbbbababbbababbaaababbbabaabbaaabbbba";
        String needle = "bbbbbbaa";

        System.out.println(strStr(haystack, needle));
    }

}
