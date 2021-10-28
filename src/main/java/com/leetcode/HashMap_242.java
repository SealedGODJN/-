package com.leetcode;

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
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;

        HashMap<Character, Integer> sCount = new HashMap<>();
        HashMap<Character, Integer> tCount = new HashMap<>();

        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();

        for (char c : sCharArray) {
            int tempTimes = sCount.getOrDefault(c, 0); // 如果存在该key，则设置key,value++
            tempTimes++;
            if (tempTimes == 1) { // tempTimes++之前是0，说明sCount之前不存在该key
                sCount.put(c, tempTimes);
            } else sCount.replace(c, tempTimes);
        }

        for (char c : tCharArray) {
            int tempTimes = tCount.getOrDefault(c, 0); // 如果存在该key，则设置key,value++
            tempTimes++;
            if (tempTimes == 1) { // tempTimes++之前是0，说明sCount之前不存在该key
                tCount.put(c, tempTimes);
            } else tCount.replace(c, tempTimes);
        }

        for (char c :
                sCount.keySet()) {
            int tTimes = tCount.getOrDefault(c, 0);
            int sTimes = sCount.get(c);
            if (tTimes == 0) return false;
            else if (tTimes != sTimes) return false;
        }

        return true;
    }

    /*
    「数组其实就是一个简单哈希表」，而且这道题目中字符串只有小写字符，那么就可以定义一个数组，来记录字符串s里字符出现的次数。

需要定义一个多大的数组呢，定一个数组叫做record，大小为26 就可以了，初始化为0，因为字符a到字符z的ASCII也是26个连续的数值。
     */

    public static void main(String[] args) {
        String s = "aacc";
        String t = "ccac";

        System.out.println(isAnagram(s, t));
    }
}
