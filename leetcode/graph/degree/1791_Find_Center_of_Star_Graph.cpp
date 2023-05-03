#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    /*
        易错点：
        1、边的数量和图的顶点数量之间的关系
        2、无向图还是有向图？
        3、考点？出度入度？还是DFS、BFS、并查集？
        
    */
    int findCenter(vector<vector<int>>& edges) {
        // 判断edges[0][0]是否出现了两次？
        return edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1] ? edges[0][0] : edges[0][1];
    }
    // int findCenter(vector<vector<int>>& edges) {
    //     int n = edges.size() + 1;
    //     vector<int> Degrees(n + 1);
    //     for (vector<int> edge : edges) {
    //         int start = edge[0];
    //         int end = edge[1];
    //         Degrees[end]++;
    //         Degrees[start]++;
    //     }
    //     for (int i = 0; i <= n; i++) {
    //         if (Degrees[i] == n - 1)
    //             return i;
    //     }
    // }
};

int main()
{
    vector<vector<int>> trust = {{1,2},{2,3},{4,2}};
    Solution s;
    cout << s.findCenter(trust);
    cin.get();
}