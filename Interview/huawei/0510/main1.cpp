#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

int main() {
    freopen("in.txt", "r", stdin);
    freopen("out.txt", "w", stdout);
    long long int num;
    vector<long long> nums;
    while (cin >> num) {
        nums.push_back(num);
    }
    unordered_map<long long, int> sums;
    long long tmp = 0;
    for (int i = nums.size() - 1; i >= 0; --i) {
        tmp += nums[i];
        sums[tmp] = i;
    }
    long long pre = 0;
    int index = nums.size() - 1;
    while (index >= 0) {
        long long cur = pre + 2 * nums[index];
        while (sums.find(cur) != sums.end()) {
            index = sums[cur];
            cur = pre + (cur - pre) * 2;
        }
        // 此时未找到cur, ((cur - pre) / 2)即为上次循环中的(cur - pre)或者nums[index]
        cout << (cur - pre) / 2 << " ";
        pre = pre + (cur - pre) / 2;
        --index;
    }
    return 0;
}