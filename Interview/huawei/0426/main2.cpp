#include <iostream>
#include <unordered_map>
#include <queue>
#include <stdlib.h>
#include <ctime>

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
            if (item[0] == 1) {//动态分配
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

            } else if (item[0] == 2) {//指定分配
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
    vector<vector<int>> num1 = {{1, 1}, {3, 1}};
    vector<vector<int>> num2 = {{2, 2}, {3, 2}, {1, 1}};

    std::srand(std::time(nullptr)); // use current time as seed for random generator
    int random_variable = std::rand();

    vector<vector<int>> num4;
    for (int i = 0; i < 100000; i++)
    {
        vector<int> num3;
        int x = 1 + std::rand()/((RAND_MAX + 1u)/3);
        cout << x;
        num3.push_back(x);
        if (x == 1) {
            int y = 1 + std::rand()/((RAND_MAX + 1u)/200);
            cout << y << endl;
            num3.push_back(y);
        }
        else {
            int y = 1 + std::rand()/((RAND_MAX + 1u)/100000);
            cout << y << endl;
            num3.push_back(y);
        }
        num4.push_back(num3);
    }

    Solution s;
    cout << s.main(100000, num4);
}