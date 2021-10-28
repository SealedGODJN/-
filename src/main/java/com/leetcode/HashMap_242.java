package com.leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author hjn
 * @time 2021-10-28
 */
public class HashMap_242 {
    /**
     * 1 <= s.length, t.length <= 5 * 104
     * s 和 t 仅包含小写字母
     *
     * @param s 待比较的第一个字符串
     * @param t 待比较的第二个字符串
     * @return
     */
//    public static boolean isAnagram(String s, String t) {
//        if(s.length() != t.length()) return false;
//
//        HashMap<Character, Integer> sCount = new HashMap<>();
//        HashMap<Character, Integer> tCount = new HashMap<>();
//
//        char[] sCharArray = s.toCharArray();
//        char[] tCharArray = t.toCharArray();
//
//        for (char c : sCharArray) {
//            int tempTimes = sCount.getOrDefault(c, 0); // 如果存在该key，则设置key,value++
//            tempTimes++;
//            if (tempTimes == 1) { // tempTimes++之前是0，说明sCount之前不存在该key
//                sCount.put(c, tempTimes);
//            } else sCount.replace(c, tempTimes);
//        }
//
//        for (char c : tCharArray) {
//            int tempTimes = tCount.getOrDefault(c, 0); // 如果存在该key，则设置key,value++
//            tempTimes++;
//            if (tempTimes == 1) { // tempTimes++之前是0，说明sCount之前不存在该key
//                tCount.put(c, tempTimes);
//            } else tCount.replace(c, tempTimes);
//        }
//
//        for (char c :
//                sCount.keySet()) {
//            int tTimes = tCount.getOrDefault(c, 0);
//            int sTimes = sCount.get(c);
//            if (tTimes == 0) return false;
//            else if (tTimes != sTimes) return false;
//        }
//
//        return true;
//    }

    /*
    想法一：
    「数组其实就是一个简单哈希表」，而且这道题目中字符串只有小写字符，那么就可以定义一个数组，来记录字符串s里字符出现的次数。
    需要定义一个多大的数组呢，定一个数组叫做record，大小为26 就可以了，初始化为0，因为字符a到字符z的ASCII也是26个连续的数值。
    需要把字符映射到数组也就是哈希表的索引下表上，「因为字符a到字符z的ASCII是26个连续的数值，所以字符a映射为下表0，相应的字符z映射为下表25。」
    再遍历字符串s的时候，「只需要将 s[i] - ‘a’ 所在的元素做+1 操作即可，并不需要记住字符a的ASCII，只要求出一个相对数值就可以了。」
    这样就将字符串s中字符出现的次数，统计出来了。
    那看一下如何检查字符串t中是否出现了这些字符，同样在遍历字符串t的时候，对t中出现的字符映射哈希表索引上的数值再做-1的操作。
    那么最后检查一下，「record数组如果有的元素不为零0，说明字符串s和t一定是谁多了字符或者谁少了字符，return false。」
    最后如果record数组所有元素都为零0，说明字符串s和t是字母异位词，return true。

    时间复杂度为O(n)
    空间上因为定义是的一个常量大小的辅助数组，所以空间复杂度为O(1)。

    想法二：
    不去遍历 hash 表，而是分别遍历两个字符串，遍历 s 加一，遍历 t 时减一并当 hash 表中某个字符的个数小于 0 时直接返回 false
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        char[] count = new char[26]; // 26个英文小写字母
        Arrays.fill(count, (char) 0);

        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            int sIndex = sCharArray[i] - 'a';
            int tIndex = tCharArray[i] - 'a';
            count[sIndex]++;
            count[tIndex]--;
        }

        for (char c : count) {
            if (c != 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "aacc";
        String t = "ccac";

        System.out.println(isAnagram(s, t));
    }
}
