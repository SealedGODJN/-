#include <iostream>
#include <vector>

using namespace std;

#define typec int

const typec INF = 0x3f3f3f3f; // 防止后面溢出，这个不能太大

class Solution {
public:
    /**
     * @brief 对所有的边进行n-1轮松弛操作，因为在一个含有n个顶点的图中，任意两点之间的最短路径最多包含n-1边。换句话说，第1轮在对所有的边进行松弛后，得到的是源点最多经过一条边到达其他顶点的最短距离；第2轮在对所有的边进行松弛后，得到的是源点最多经过两条边到达其他顶点的最短距离；第3轮在对所有的边进行松弛后，得到的是源点最多经过3条边到达其他顶点的最短距离......
     * 
     * @param n 有 n 个城市通过一些航班连接
     * @param flights 给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，表示该航班都从城市 fromi 开始，以价格 pricei 抵达 toi。
     * @param src 现在给定所有的城市和航班，以及出发城市 src
     * @param dst  和目的地 dst
     * @param k 找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便宜
     * @return int 
     */
    int findCheapestPrice(int n, vector<vector<int>>& flights, int src, int dst, int k) {
        int eSize = flights.size();

        // if (k == 0) {
        //     for (int j = 0; j < eSize; j++) {
        //         int u = flights[j][0];
        //         int v = flights[j][1];
        //         int c = flights[j][2];
        //         if (u == src && v == dst)
        //             return c;
        //     }
        //     return -1;
        // }

        // 初始化要用的内容：
        vector<int> minCost(n, INF);
        // 一定要初始化，否则无法开始
        minCost[src] = 0;

        for (int i = 0; i < k + 1; i++)
        {
            // 每一轮只能改变一次
            vector<int> clone(minCost);
            for (vector<int> edge : flights) {
                int u = edge[0];
                int v = edge[1];
                int c = edge[2];
                if (minCost[v] > clone[u] + c) {
                    minCost[v] = clone[u] + c;
                }
            }
        }
        return minCost[dst] == INF ? -1 : minCost[dst];
    }
};

int main()
{
    vector<vector<int>> flights = {{0,1,5},{1,2,5},{0,3,2},{3,1,2},{1,4,1},{4,2,1}};
    int n = 5, src = 0, dst = 2, k = 2;
    Solution s;
    cout << s.findCheapestPrice(n, flights, src, dst, k);
    cin.get();
}