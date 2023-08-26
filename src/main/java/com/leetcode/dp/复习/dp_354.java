package com.leetcode.dp.复习;

import java.util.Arrays;
import java.util.Comparator;

public class dp_354 {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (o1, o2) -> o1[0] == o2[0]? o2[1] - o1[1] : o1[0] - o2[0]);
        int[] dp = new int[envelopes.length];
        // 长度为1的数组，最长递增序列最小为1
        Arrays.fill(dp, 1);
        for (int i = 0; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 0;
        for (int j : dp) {
            max = Math.max(max, j);
        }
        return max;
    }
}
