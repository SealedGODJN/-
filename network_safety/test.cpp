#include <iostream>
#include <cstring>

using namespace std;

int main() {
    string input; // 输入数据
    cout << "请输入需要加密的数据：";
    cin >> input;
    int temp = input.length();
    char *input_char = new char[temp]; // 转为char数组
    strcpy(input_char, input.c_str());
    char *output_char = new char[temp];
    char last = input_char[temp - 1]; // 记录最后一位
    for (int i = 0; i < temp - 1;i++) {
        output_char[i + 1] = input_char[i];
    }
    output_char[temp] = '\0';
    output_char[0] = last;
    cout << "加密后的数据：" << output_char << endl;
    return 0;
}

/**
 * 总结：
 * 问题1：input.length()返回的是Long类型
 * 解决方案：int temp = input.length();
 * 
 * 问题2：error: 'strcpy' was not declared in this scope
 * 解决方案：#include <cstring>
 * 
 * 问题3：请输入需要加密的数据：i love you 加密后的数据：i
 * 解决方案：原因是cin以空格、制表符、换行符，输入中不要带空格
 */