#include <iostream>
#include <vector>
#include <queue>

using namespace std;

#define typec int

const typec INF = 0x3f3f3f3f; // 防止后面溢出，这个不能太大

struct qnode {
    int vertex;
    int cost;
    qnode(int _vertex=0,int _cost=0):vertex(_vertex),cost(_cost){}
    bool operator <(const qnode &r) const {
        return cost > r.cost;
    }
};

class Solution
{
public:
    /**
     * @brief
     *
     * @param times times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
     * @param n 有 n 个网络节点
     * @param k 从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号
     * @return int 所有节点都收到信号的最小时间
     */
    int networkDelayTime(vector<vector<int>> &times, int n, int k)
    {
        vector<vector<int>> cost(n + 1);
        for (int i = 1; i < n + 1; i++)
        {
            for (int j = 0; j < n + 1; j++)
            {
                cost[i].emplace_back(INF);
            }
        }
        // 初始化每条边的权值
        for (vector<int> edge : times)
        {
            cost[edge[0]][edge[1]] = edge[2];
        }
        // 初始化要用的内容：
        priority_queue<qnode> que;
        while(!que.empty())
            que.pop();
        vector<int> minCost(n + 1);
        vector<bool> isVisited(n + 1);
        for (int i = 1; i < n + 1; i++)
        {
            // 不能初始化minCost
            // minCost[i] = cost[k][i];
            minCost[i] = INF;
            isVisited[i] = false;
        }
        // isVisited[k] = true;
        minCost[k] = 0;

        que.push(qnode(k, 0));
        qnode tmp;

        while(!que.empty())
        {
            tmp = que.top();
            que.pop();
            int cur = tmp.vertex;
            if (isVisited[cur]) continue;
            isVisited[cur] = true;
            for (int j = 1; j < cost[cur].size(); j++)
            {
                if (!isVisited[j] && minCost[cur] + cost[cur][j] < minCost[j])
                {
                    // 更新每一个节点的minCost
                    minCost[j] = minCost[cur] + cost[cur][j];
                    que.push(qnode(j, minCost[j]));
                }
            }
        }
        int result = 0;
        for (int i = 1; i < n + 1; i++)
        {
            if (minCost[i] > result)
                result = minCost[i];
        }
        return result == INF ? -1 : result;
    }
};

int main()
{
    vector<vector<int>> times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
    int n = 4, k = 2;
    Solution s;
    cout << s.networkDelayTime(times, n, k);
    cin.get();
}