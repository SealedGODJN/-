#include <iostream>
#include <vector>

using namespace std;

const int INF = 0x3f3f3f3f;

class Solution {
public:
    int minCost(int maxTime, vector<vector<int>>& edges, vector<int>& passingFees) {
        int maxVertex = -1;
        for (vector<int> edge : edges) {
            if (edge[0] > maxVertex)
                maxVertex = edge[0];
            if (edge[1] > maxVertex )
                maxVertex = edge[1];
        }
        vector<vector<int>> cost(maxVertex + 1);
        for (int i = 0; i < maxVertex + 1; i++) {
            for (int j = 0; j < maxVertex + 1; j++) {
                cost[i].emplace_back(INF);
            }
        }
        // 初始化每条边的权值
        for (vector<int> edge : edges) {
            cost[edge[0]][edge[1]] = edge[2];
        }
        vector<int> preVertex(maxVertex + 1, -1);
        vector<int> minCost(maxVertex + 1, INF);
        vector<int> isVisted(maxVertex + 1, false);
        minCost[0] = 0;
        for (int i = 0; i < maxVertex + 1; i++) {
            int cur = -1;
            int min = INF;
            for (int j = 0; j < maxVertex + 1; j++) {
                if (!isVisted[j] && minCost[j] < min) {
                    cur = j;
                    min = minCost[j];
                }
            }
            if (cur == -1) break;
            isVisted[cur] = true;
            for (int j = 0; j < maxVertex + 1; j++) {
                if (!isVisted[j] && minCost[j] > minCost[cur] + cost[cur][j]) {
                    minCost[j] = minCost[cur] + cost[cur][j];
                    preVertex[j] = cur;
                }
            }
        }
        int minTime = minCost[maxVertex];
        if (minTime < maxTime) {
            int fees = 0;
            int pre = preVertex[maxVertex];
            while (pre != 0) {
                fees += passingFees[pre];
                pre = preVertex[pre];
            }
            fees += passingFees[0];
            return fees;
        }
        return -1;
    }
};

int main()
{
    int maxTime = 30;
    vector<vector<int>> edges = {{0, 1, 10}, {1, 2, 10}, {2, 5, 10}, {0, 3, 1}, {3, 4, 10}, {4, 5, 15}};
    vector<int> passingFees = {5, 1, 2, 20, 20, 3};
    Solution s;
    cout << s.minCost(maxTime, edges, passingFees);
    cin.get();
}