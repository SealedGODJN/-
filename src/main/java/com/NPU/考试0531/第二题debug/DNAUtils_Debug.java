package com.NPU.考试0531.第二题debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DNAUtils {

    int times_A = 0;

    int times_G = 0;

    int times_C = 0;

    int times_T = 0;

    /**
     * 检查序列是否合法
     *
     * @param DNASeq
     * @return
     */
    public boolean check(byte[] DNASeq) {
        boolean flag = true;
        for (byte b : DNASeq) {
            if (b == 'A') times_A++;
            else if (b == 'G') times_G++;
            else if (b == 'C') times_C++;
            else if (b == 'T') times_T++;
            else flag = false;
        }
        return flag;
    }

    /**
     * 计算序列中GC出现的比例（G和C总出现次数/总字母数）
     *
     * @return
     */
    public double GC_rate() {
        return (times_G + times_C) / (times_C + times_G + times_A + times_T);
    }

    /**
     * 找到所有 相同字符是连续的子串的开始下标
     *
     * @param DNASeq
     * @return
     */
    public List<Integer> findSubSequenceLocation(byte[] DNASeq) {
        List<Integer> result = new ArrayList<>();
        int head = 0;
        int tail = 0;
        for (int i = 0; i < DNASeq.length; i++) {
            head = i;
            tail = head;
            while (DNASeq[tail] == DNASeq[head]) {
                tail++;
            }
            if (tail - head > 1) {
                result.add(head);
            }
        }
        return result;
    }

    /**
     * 在DNA分子序列中出现不止一次的长度为L的序列，其中L是入参。如L=10，则计算DNA序列中出现多次的长度为10DNA子序列。输出所有的结果。
     *
     * @param L
     * @return
     */
    public List<String> find_L_LengthSubSeq(String DNASeq, int L) {
        List<String> result = new ArrayList<>();
        // key : 长度为L的子串 value : 出现次数
        Map<String, Integer> all_L_LengthSubSeq = new HashMap<>();
        for (int i = 0; i <= DNASeq.length() - L; i++) {
            String seq = DNASeq.substring(i, i + L);
            if (all_L_LengthSubSeq.containsKey(seq)) {
                all_L_LengthSubSeq.put(seq, 1);
            } else {
                all_L_LengthSubSeq.put(seq, all_L_LengthSubSeq.get(seq) + 1);
            }
        }
        for (String seq : all_L_LengthSubSeq.keySet()) {
            if (all_L_LengthSubSeq.get(seq) > 1) {
                result.add(seq);
            }
        }
        return result;
    }

    /**
     * 求某个DNA序列是否存在，如果存在返回开始下标，不存在则返回-1<br>
     * KMP算法
     *
     * @return
     */
    public int findSeqIsExisted(String DNA, String seq) {
        if (seq.length() == 0) {
            return 0;
        }
        int[] next = new int[seq.length()];
        getNext(next, seq);
        int j = -1; // // 因为next数组里记录的起始位置为-1
        for (int i = 0; i < DNA.length(); i++) { // 注意i就从0开始
            while (j >= 0 && DNA.charAt(i) != seq.charAt(j + 1)) { // 不匹配
                j = next[j]; // j 寻找之前匹配的位置
            }
            if (DNA.charAt(i) == seq.charAt(j + 1)) { // 匹配，j和i同时向后移动
                j++; // i的增加在for循环里
            }
            if (j == (seq.length() - 1)) { // 文本串s里出现了模式串t
                return (i - seq.length() + 1);
            }
        }
        return 0;
    }

    public void getNext(int[] next, String s) {
        int j = -1;
        next[0] = j;
        for (int i = 1; i < s.length(); i++) { // 注意i从1开始
            while (j >= 0 && s.charAt(i) != s.charAt(j + 1)) { // 前后缀不相同了
                j = next[j]; // 向前回退
            }
            if (s.charAt(i) == s.charAt(j + 1)) { // 找到相同的前后缀
                j++;
            }
            next[i] = j; // 将j（前缀的长度）赋给next[i]
        }
    }

    /**
     * 返回DNA序列的互补序列
     */
    public byte[] getReverseSeq(byte[] DNA) {
        byte[] newSeq = new byte[DNA.length];
        for (int i = 0; i < DNA.length; i++) {
            if (DNA[i] == 'A') newSeq[i] = 'T';
            else if (DNA[i] == 'T') newSeq[i] = 'A';
            else if (DNA[i] == 'C') newSeq[i] = 'G';
            else if (DNA[i] == 'G') newSeq[i] = 'C';
        }
        return newSeq;
    }

    /**
     * 将DNA序列转换为RNA序列
     *
     * @param DNA
     * @return
     */
    public byte[] transformToRNA(byte[] DNA) {
        byte[] RNA = new byte[DNA.length];
        for (int i = 0; i < DNA.length; i++) {
            if (DNA[i] == 'A') RNA[i] = 'T';
            else if (DNA[i] == 'T') RNA[i] = 'A';
            else if (DNA[i] == 'C') RNA[i] = 'G';
            else if (DNA[i] == 'G') RNA[i] = 'C';
        }
        return RNA;
    }

    /**
     * n：原始DNA序列的长度
     * m：突变序列的长度
     * dp[i][j]：表示将原序列的前i个字符变异到突变序列的前j个字符所需的最少突变次数
     */
    public int minTimesToChange(byte[] origin, byte[] change) {
        int n = origin.length;
        int m = change.length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (origin[i] == change[j]) {
                    if (i > 1 && j > 1) {
                        // 继承之前的最少突变次数
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    if (i > 1 && j > 1) {
                        // 三种情况
                        // 插入 丢失 替换
                        dp[i][j] = Math.max(dp[i][j - 1] + 1, Math.max(dp[i - 1][j] + 1, dp[i - 1][j - 1] + 1));
                    }
                    // 不需要这么做
//                    else {
//                        dp[i][j] += 1;
//                    }
                }
            }
        }
        return dp[n - 1][m - 1];
    }

    public static void main(String[] args) {
        DNAUtils dnaUtils = new DNAUtils();

        byte[] s1 = {'A', 'T', 'C', 'G'};
        byte[] s2 = {'T', 'C', 'T'};
        System.out.println(dnaUtils.minTimesToChange(s1, s2));
    }
}
