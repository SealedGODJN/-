#include <iostream>
#include <unordered_map>
#include <queue>

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
    Solution s;
    cout << s.main(3, num2);
}