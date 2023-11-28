package com.leetcode.Graph.DFS;

import java.util.List;

public class DFS_841 {
    boolean[] visited;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        visited = new boolean[rooms.size()];
        dfs(rooms, 0);
        for (int i = 0; i < rooms.size(); i++) {
            if (!visited[i]) return false;
        }
        return true;
    }

    private void dfs(List<List<Integer>> rooms, int key) {
        if (visited[key]) return;
        visited[key] = true;
        for (Integer i : rooms.get(key)) {
            dfs(rooms, i);
        }
    }
}
