package com.leetcode.Graph.DFS;

import java.util.Objects;
import java.util.Scanner;

/*
Description

Order is an important concept in mathematics and in computer science. For example, Zorn's Lemma states: ``a partially ordered set in which every chain has an upper bound contains a maximal element.'' Order is also important in reasoning about the fix-point semantics of programs.


This problem involves neither Zorn's Lemma nor fix-point semantics, but does involve order.
Given a list of variable constraints of the form x < y, you are to write a program that prints all orderings of the variables that are consistent with the constraints.


For example, given the constraints x < y and x < z there are two orderings of the variables x, y, and z that are consistent with these constraints: x y z and x z y.
Input

The input consists of a sequence of constraint specifications. A specification consists of two lines: a list of variables on one line followed by a list of contraints on the next line. A constraint is given by a pair of variables, where x y indicates that x < y.


All variables are single character, lower-case letters. There will be at least two variables, and no more than 20 variables in a specification. There will be at least one constraint, and no more than 50 constraints in a specification. There will be at least one, and no more than 300 orderings consistent with the contraints in a specification.


Input is terminated by end-of-file.
Output

For each constraint specification, all orderings consistent with the constraints should be printed. Orderings are printed in lexicographical (alphabetical) order, one per line.


Output for different constraint specifications is separated by a blank line.
Sample Input

a b f g
a b b f
v w x y z
v y x v z v w v
Sample Output

abfg
abgf
agbf
gabf

wxzvy
wzxvy
xwzvy
xzwvy
zwxvy
zxwvy
 */
public class poj_1270 {

    static int maxN = 26;
    static int n; //有效字母数
    static int[][] graph = new int[maxN][maxN];
    static boolean[] vis = new boolean[maxN];
    static int[] ans = new int[maxN];
    static boolean[] mark = new boolean[maxN]; //标记当前字母出现在变量中

    public static boolean ok(int i, int count) {
        for (int j = 0; j < count; j++) {
            // 如果在ans[0]至ans[cnt-1]中，出现了一个本应在i后面才出现的字母,那么返回false
            if (graph[i][ans[j]] == 1)
                return false;
        }
        return true;
    }

    public static void dfs(int cnt) {

        if (cnt == n && ok(ans[n - 1], cnt)) {
            for (int i = 0; i < n; i++) {
                char c = (char) (ans[i] + 'a');
                System.out.print(c + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < 26; i++) {
            // 已标记 未访问
            if (mark[i] && !vis[i] && ok(i, cnt)) {
                // 回溯开始
                vis[i] = true;
                ans[cnt] = i;
                dfs(cnt + 1);
                // 回溯结束
                vis[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line;
        while (!Objects.equals(line = sc.nextLine(), "")) {
            // 第一行是节点
            for (int i = 0; i < line.length(); i++) {
                char letter = line.charAt(i);
                if (letter != ' ') {
                    mark[letter - 'a'] = true;
                    n++;
                }
            }

            // 第二行是 依赖关系
            line = sc.nextLine();
            for (int i = 0; i < line.length(); ) {
                char letter = line.charAt(i++);
                if (letter != ' ') {
                    int a, b;
                    a = letter - 'a';
                    while (i < line.length() && line.charAt(i) == ' ') {
                        i++;
                    }
                    b = letter - 'a';
                    graph[a][b] = 1;
                }
            }
            dfs(0);//表示当前正在构造第0个位置
        }
    }
}
