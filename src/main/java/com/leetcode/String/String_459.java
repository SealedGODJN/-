package com.leetcode.String;

public class String_459 {

    /**
     * 解法一：找规律
     *
     * @param s
     * @return
     */
//    public static boolean repeatedSubstringPattern(String s) {
//        if (s.length() <= 1) return false;
//        char[] needle = s.toCharArray();
//        int[] next = genNext(needle);
////        for (int i = 0; i < next.length; i++) {
////            if (next[i] == 0) cycle++; // 0 不代表这是循环，只有next[next.length - 1]能代表这个循环的长度
////            else if (next[i] != 0) {
////                if ((i - cycle + 1) != next[i]) return false;
////            }
////        }
//        return next[next.length-1] >= 1 && (s.length() % (s.length() - next[next.length-1]) == 0);
//    }

    /**
     * str = s + s
     *
     * 如果您的字符串 S 包含一个重复的子字符串，那么这意味着您可以多次 “移位和换行”`您的字符串，并使其与原始字符串匹配。
     *
     * 例如：abcabc
     *
     * 移位一次：cabcab
     * 移位两次：bcabca
     * 移位三次：abcabc
     *
     * 现在字符串和原字符串匹配了，所以可以得出结论存在重复的子串。
     *
     * 基于这个思想，可以每次移动k个字符，直到匹配移动 length - 1 次。但是这样对于重复字符串很长的字符串，效率会非常低。在 LeetCode 中执行时间超时了。
     *
     * 为了避免这种无用的环绕，可以创建一个新的字符串 str，它等于原来的字符串 S 再加上 S 自身，这样其实就包含了所有移动的字符串。
     *
     * 比如字符串：S = acd，那么 str = S + S = acdacd
     *
     * acd 移动的可能：dac、cda。其实都包含在了 str 中了。就像一个滑动窗口
     *
     * 一开始 acd (acd) ，移动一次 ac(dac)d，移动两次 a(cda)cd。循环结束
     *
     * 所以可以直接判断 str 中去除首尾元素之后，是否包含自身元素。如果包含。则表明存在重复子串。
     *
     * 作者：13217319563
     * 链接：https://leetcode-cn.com/problems/repeated-substring-pattern/solution/jian-dan-ming-liao-guan-yu-javaliang-xing-dai-ma-s/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public static boolean repeatedSubstringPattern(String s) {
        String str = s + s; // 列出了所有s的圆排列
        return str.substring(1, str.length() - 1).contains(s);
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
//        String s = "abcabcabcabc";
//        String s = "abab";
//        String s = "abcd,abcd,abcd,";
//        String s = "aba";
//        String s = "a";
        String s = "aabaabaabaab";
//        String s = "abac";
//        String s = "aa";
        int[] next = genNext(s.toCharArray());
        for (int i : next) {
            System.out.print(i + " ");
        }
        System.out.println(repeatedSubstringPattern(s));
    }
}
