#include <bits/stdc++.h>
#include <iostream>
#include <unordered_map>
#include <queue>
#include <stdlib.h>
#include <random>

using namespace std;

class Solution {
public:
    int l, r, n, m, op, x;
    int main(int size, vector<vector<int>> operation)
    {
        // scanf("%d %d", &l, &r);
        l = 0;
        r = size;
        n = r - l + 1;
        vector<int> vis(n + 1, 0);
        vector<int> nxt(n + 2);
        vector<int> pre(n + 2);
        for (int i = 0; i <= n; ++i)
            nxt[i] = i + 1, pre[i + 1] = i;
        // scanf("%d", &m);
        for (const auto &item: operation)
        {
            // scanf("%d %d", &op, &x);
            op = item[0];
            x = item[1];
            if (op == 1)
            {
                while (x-- && nxt[0] != n + 1)
                {
                    vis[nxt[0]] = 1;
                    nxt[0] = nxt[nxt[0]];
                }
                pre[nxt[0]] = 0;
            }
            else if (op == 2)
            {
                if (x < l || x > r)
                    continue;
                x = x - l + 1;
                if (!vis[x])
                {
                    vis[x] = 1;
                    int xp = pre[x], xn = nxt[x];
                    nxt[xp] = xn;
                    pre[xn] = xp;
                }
            }
            else if (op == 3)
            {
                if (x < l || x > r)
                    continue;
                x = x - l + 1;
                if (vis[x])
                {
                    vis[x] = 0;
                    int last = pre[n + 1];
                    nxt[last] = x;
                    pre[x] = last;
                    nxt[x] = n + 1;
                    pre[n + 1] = x;
                }
            }
        }
        printf("%d", nxt[0]);
        // system("pause");
        return 0;
    }
};

int main() {
    // vector<vector<int>> num1 = {{1, 1}, {3, 1}};
    // vector<vector<int>> num2 = {{2, 2}, {3, 2}, {1, 1}};

    // 以随机值播种，若可能
    random_device r;
    default_random_engine e1(r());
    uniform_int_distribution<int> uniform_dist1(1, 3);
    uniform_int_distribution<int> uniform_dist2(1, 200);
    uniform_int_distribution<int> uniform_dist3(1, 100000);
    vector<vector<int>> num4;
    for (int i = 0; i < 100000; i++)
    {
        vector<int> num3;
        int x = uniform_dist1(e1);
        
        num3.push_back(x);
        if (x == 1) {
            int y = uniform_dist2(e1);
            // cout << x << " " << y << endl;
            num3.push_back(y);
        }
        else {
            int y = uniform_dist3(e1);
            num3.push_back(y);
        }
        num4.push_back(num3);
    }

    Solution s;
    auto t1 = std::chrono::high_resolution_clock::now();
    cout << s.main(100000, num4);
    auto t2 = std::chrono::high_resolution_clock::now();
    std::chrono::duration<double, std::milli> fp_ms = t2 - t1;
    std::cout << endl << "main() took " << fp_ms.count() << " ms";
}