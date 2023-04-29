#include <bits/stdc++.h>
#include <iostream>
#include <unordered_map>
#include <queue>
#include <stdlib.h>
#include <random>

using namespace std;

class Solution {
public:
    unordered_map<int, int> unorderedMap;
    queue<int> q;

    int main(int size, vector<vector<int>> operation) {
        for (int i = 1; i <= size; i++) {
            unorderedMap[i] = 0;
            q.push(i);
        }
        for (const auto &item: operation) {
            if (item[0] == 1) {
                //动态分配
                int num = item[1];
                while(num){
                    if(unorderedMap[q.front()] == 0){
                        num--;
                    }
                    else{
                        unorderedMap[q.front()]--;
                    }
                    q.pop();
                }

            } else if (item[0] == 2) {
                //指定分配
                if(q.front() == item [1] &&  unorderedMap[item[1]] == 0){
                    q.pop();
                }
                else{
                    unorderedMap[item[1]]++;
                }

            } else if(item[0] == 3){
                //释放
                q.push(item[1]);
            }
        }
        while(!q.empty()) {
            if (unorderedMap[q.front()]) {
                unorderedMap[q.front()]--;
            }
            else {
                return q.front();
            }
            q.pop();
        }
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
    int result = s.main(100000, num4);
    auto t2 = std::chrono::high_resolution_clock::now();
    std::chrono::duration<double, std::milli> fp_ms = t2 - t1;
    
    std::cout << endl << "main() took " << fp_ms.count() << " ms";
    
    cout << result << endl;
    
    cin.get();
}