package com.leetcode.Graph.DFS;

/**
 * 与1020相反
 */
public class graph_130 {
    public void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            dfs(board, i, 0);
        }
        for (int i = 0; i < board[0].length; i++) {
            dfs(board, 0, i);
        }
        for (int i = 0; i < board.length; i++) {
            dfs(board, i, board[0].length - 1);
        }
        for (int i = 0; i < board[0].length; i++) {
            dfs(board, board.length - 1, i);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '2') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private boolean inArea(char[][] board, int i, int j) {
        return i >= 0 && i <= board.length - 1 && j >= 0 && j <= board[0].length - 1;
    }

    private void dfs(char[][] board, int i, int j) {
        // 判断搜索的位置是否合法
        if (!inArea(board, i, j)) return;

        if (board[i][j] != 'O') return;
        // '2'代表已经遍历过的岛屿
        board[i][j] = '2';

        dfs(board, i - 1, j);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
        dfs(board, i, j + 1);
    }
}
