#include <iostream>
#include <unordered_map>
#include <queue>

using namespace std;

int main() {
    int startID, endID;
    cin >> startID >> endID;
    queue<int> resource;
    unordered_map<int, int> checked;
    unordered_map<int, int> cnt;
    for (int i = startID; i <= endID; i++) {
        resource.push(i);
        checked[i] = 0;
        cnt[i] = 0;
    }
    int n;
    cin >> n;
    int op, num;
    int less = endID - startID + 1;
    while (n--)
    {
        cin >> op >> num;
        if (op == 1) {
            if (less >= num) {
                less -= num;
                while (num--) {
                    less -= num;
                    while (num--) {
                        while (cnt[resource.front()] > 0) {
                            --cnt[resource.front()];
                            resource.pop();
                        }
                        checked[resource.front()] = 1;
                        resource.pop();
                    }
                }
            }
        } else if (op == 2) {
            if (checked[num] == 0) {
                ++cnt[num];
                checked[num] = 1;
                --less;
            }
        } else if (op == 3) {
            if (checked[num] == 1) {
                resource.push(num);
                ++less;
                checked[num] = 0;
            }
        }
    }
    while(cnt[resource.front()] > 0) {
        resource.pop();
    }
    cout << resource.front();
    return 0;
}