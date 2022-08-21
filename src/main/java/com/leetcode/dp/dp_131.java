package com.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class dp_131 {
    boolean[][] f;
    List<List<String>> ret= new ArrayList<List<String>>(); // 二维数组的初始化
    List<String> ans = new ArrayList<String>();
    int n;
    public static void main(String[] args) {
        dp_131 example = new dp_131();
        String s = "aaba";
        List<List<String>> ret= example.partition(s);
        for(List<String> i:ret) {
            System.out.print("[");
            for(String j:i){
                System.out.print(j);
                System.out.print(",");
            }
            System.out.print("]");
        }
    }

    public List<List<String>> partition(String s) {
        n = s.length();
        f = new boolean[n][n];
        for(int i=0; i<n; i++) {
            Arrays.fill(f[i], true); // 初始化f
        }
//        for(int i=n-1; i>0; i--) {
        for(int i=n-1; i>=0; i--) {
            for(int j=i+1; j<n; j++) {
//                if(i<j) {
                    f[i][j] = f[i+1][j-1] && (s.charAt(i)==s.charAt(j)); // 判断s[i...j]是否是回文串
//                }
            }
        }
        dfs(s,0);
        return ret;
    }

    public void dfs(String s, int i){
        if(i==n){
//            ret.add(ans);
            ret.add(new ArrayList<String>(ans));
            return;
        }
//        for(int i=0; i<n; i++) {
//            for(int j=i+1; j<n; j++) {
//                if(f[i][j]) {
//
//                }
//            }
//        }
        for(int j=i; j<n; j++) {
            if(f[i][j]) {
//                ans.add(s.substring(i,j));
                ans.add(s.substring(i,j+1));
                dfs(s,j+1); // 深度优先搜索
                ans.remove(ans.size()-1); // 回溯
            }
        }
    }
}
