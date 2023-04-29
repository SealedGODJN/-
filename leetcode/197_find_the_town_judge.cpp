#include <iostream>
#include <unordered_map>
#include <vector>

using namespace std;

class Solution
{
public:
    int findJudge(int n, vector<vector<int>> &trust)
    {
        // 入度
        vector<int> inDegrees(n + 1);
        // 出度
        vector<int> outDegrees(n + 1);
        for (vector<int> edge : trust)
        {
            int start = edge[0];
            int end = edge[1];
            inDegrees[end]++;
            outDegrees[start]++;
        }
        for (int i = 1; i <= n; i++)
        {
            if (inDegrees[i] == n - 1 && outDegrees[i] == 0)
            {
                return i;
            }
        }
        return -1;
    }
};

int main()
{
    int n = 2;
    vector<vector<int>> trust = {{1, 2}};
    Solution s;
    cout << s.findJudge(n, trust);
    cin.get();
}