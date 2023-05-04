#include <vector>
#include <string>
#include <iostream>

using namespace std;

int main()
{
    vector<int> flights = {0,1,5};
    vector<int> clone(flights);
    for (const auto& item : flights) {
        cout << item << ",";
    }
    cout << endl;
    for (const auto& item : clone) {
        cout << item << ",";
    }
    cout << endl;
    cout << endl;
    flights.push_back(1);
    for (const auto& item : flights) {
        cout << item << ",";
    }
    cout << endl;
    for (const auto& item : clone) {
        cout << item << ",";
    }
    cin.get();
}