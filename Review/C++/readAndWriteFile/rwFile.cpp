#include<iostream>
#include <fstream>

using namespace std;

void test()
{
    ifstream fin;
    ofstream fout;

    fin.open("D:/test.txt", ios::out);

    if (!fin.is_open())
    {
        std::cerr << "cannot open the file";
    }
    char buf[1024] = {0}; // 申请1024字节的读空间
    while (fin >> buf)
    {
        cout << buf << endl; // 以回车键分开
    }
}

int main()
{
    test();

    return 0;
}