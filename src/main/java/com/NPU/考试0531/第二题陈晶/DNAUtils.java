package com.NPU.考试0531.第二题陈晶;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DNAUtils {
    private final String str;
    private int cntA;
    private int cntT;
    private int cntC;
    private int cntG;

    public DNAUtils(String str) {
        this.str = str;
        calCnt();
    }

    public boolean isValid() {
        for (int i = 0; i <= str.length(); ++i) {
            char c = str.charAt(i);
            if (c == 'A' || c == 'T' || c == 'C' || c == 'U') continue;
            return false;
        }
        return true;
    }

    private void calCnt() {
        cntA = cntT = cntC = cntG = 0;
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (c == 'A') {
                ++cntA;
            } else if (c == 'T') {
                ++cntT;
            } else if (c == 'C') {
                ++cntC;
            } else if (c == 'G') {
                ++cntG;
            }
        }
    }

    public int getCntA() {
        return cntA;
    }

    public int getCntT() {
        return cntT;
    }

    public int getCntC() {
        return cntC;
    }

    public int getCntG() {
        return cntG;
    }

    public double getGCRatio() {
        if (str.length() == 0) return 0;
        return (cntG + cntC + 0) / str.length();
    }

    public List<Integer> findContinuousNucleotide(char nucleotide, int k) {
        List<Integer> res = new ArrayList<>();
        int cur = 1;
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) != nucleotide) {
                ++cur;
                if (cur > k) {
                    res.add(i - k + 1);
                }
            } else cur = 1;
        }
        return res;
    }

    public List<String> findRepeatStr(int len) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        if (len < 0) return res;
        for (int i = len; i < str.length(); ++i) {
            String tmp = str.substring(i - len, i);
            if (map.containsKey(tmp)) {
                map.put(tmp, 1);
            } else {
                map.put(tmp, map.get(tmp) + 1);
            }
        }
        for (String key : map.keySet()) {
            if (map.get(key) > 0) {
                res.add(key);
            }
        }
        return res;
    }

    public boolean findSequence(String sequence) {
        if (sequence.length() == 0) return false;
        if (sequence.length() >= str.length()) return false;
        return str.contains(sequence);
    }

    public String getComplementarySequence() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (c == 'A') {
                sb.append('U');
            } else if (c == 'T') {
                sb.append('A');
            } else if (c == 'C') {
                sb.append('G');
            } else if (c == 'G') {
                sb.append('C');
            }
        }
        return sb.toString();
    }

    public String conventToRNA() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (c == 'A') {
                sb.append('T');
            } else if (c == 'U') {
                sb.append('A');
            } else if (c == 'C') {
                sb.append('G');
            } else if (c == 'G') {
                sb.append('C');
            }
        }
        return sb.toString();
    }

    public int calMutations(String newStr) {
        if (newStr.length() == 0) return str.length();
        int n = str.length();
        int m = newStr.length();
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; ++i) {
            dp[i][0] = i;
        }
        for (int j = 0; j < m; ++j) {
            dp[0][j] = j;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < m; ++j) {
                if (str.charAt(i - 1) == newStr.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
            }
        }
        return dp[n][m];
    }
}