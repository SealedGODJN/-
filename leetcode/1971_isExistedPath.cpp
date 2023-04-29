//
// Created by HJN13 on 2023/4/28.
//
#include <iostream>
#include <queue>
#include <vector>

using namespace std;

class Solution
{
public:
    bool validPath(int n, vector<vector<int>> &edges, int source, int destination)
    {
        // 1.构建邻接表
        vector<vector<int>> adj(n);
        for (vector<int> edge : edges)
        {
            int start = edge[0], end = edge[1];
            // adj[start].push_back(end);
            adj[start].emplace_back(end);
            adj[end].emplace_back(start);
        }
        vector<bool> isVisited(n, false);
        queue<int> queue;
        queue.emplace(source);
        isVisited[source] = true;
        while (!queue.empty())
        {
            int des = queue.front();
            queue.pop();
            // 如果des==目标，则
            if (des == destination)
            {
                break;
            }
            for (int next : adj[des])
            {
                if (!isVisited[next])
                {
                    queue.emplace(next);
                    isVisited[next] = true;
                }
            }
        }
        return isVisited[destination];
    }
};

int main()
{
    int n = 3;
    vector<vector<int>> edges = {{0, 1}, {1, 2}, {2, 0}};
    int source = 0;
    int destination = 2;
    Solution s;
    std::cout << s.validPath(n, edges, source, destination);
}