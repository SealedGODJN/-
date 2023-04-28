#include <iostream>

using namespace std;

int main() {
    int num1, num2, num3;
    scanf("[%d,%d,%d]", &num1, &num2, &num3);
    int l = 2540;
    int r = 2630;
    int cnt = 0;
    for (int i = -5; i <= 5; ++i) {
        for (int j = -5; j <= 5; ++j) {
            for (int k = -5; k <= 5; ++k) {
                int sum = num1 * i + num2 * j + num3 * k;
                if (sum >= l && sum <= r) {
                    ++cnt;
                    if (cnt > 1) cout << endl;
                    cout << sum << " " << i << " " << j << " " << k;
                }
            }
        }
    }
    return 0;
}