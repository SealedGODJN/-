#include <iostream>
#include <cstring>
#include <algorithm>
#include "huffmancode.h"

#define inf 9999999

int n, m;

void Select(huffmantree &ht,int s, int &s1, int &s2){
    s1 = s2 = 0;
    int min1, min2;
    min1 = min2 = inf;
    for (int j = 1; j <= s; j++){
        if(ht[j].parent == 0){
            if(ht[j].weigth <= min1) { // 所有节点的weigth已经输入进去
                min2 = min1;
                min1 = ht[j].weigth;
                s2 = s1;
                s1 = j;
            }
            else if(ht[j].weigth <= min2) {
                min2 = ht[j].weigth;
                s2 = j;
            }
        }
    }
}

void createhuffmancode(huffmantree &ht, huffmancode &hc, int n)
{
    hc = new char *[n + 1]; // 记录huffmancode,这是一个二维数组
    char *cd = new char[n]; // char数组
    for (int i = 1; i <= n;i++){ // 处理每一个字符的哈夫曼编码
        int start = n - 1;
        // 根据哈夫曼树的拓扑图
        // 编码应该是从下往上开始，所以start=n-1
        int c = i;
        int f = ht[i].parent;
        while (f != 0) // parent != 0 意义：循环到根节点结束
        {
            --start;
            if(ht[f].lchild == c) // 左子树标‘0’，右子树标‘1’
                cd[start] = '0';
            else // hf[f].rchild == c
                cd[start] = 'l';
            c = f; // 向上迭代，更新c和f的值
            f = ht[f].parent;
        }
        hc[i] = new char[n - start]; // 新建一个char数组，代表第i个叶子节点对应的字符的哈夫曼编码
        strcpy(hc[i], &cd[start]); // 将构建的cd数组复制到hc[i]中
    }
    delete cd;
}

void createhuffmantree(huffmantree &ht, int n){
    if(n<=1)
        return;
    m = 2 * n - 1; // huffman树的总结点数为2n-1，n为叶子节点的数量
    ht = new htnode[m + 1];
    for (int i = 1; i <= m; ++i){ // 初始化所有节点
        ht[i].parent = 0;
        ht[i].lchild = 0;
        ht[i].rchild = 0;
    }
    for (int i = 1; i <= n; i++)
    {
        std::cin >> ht[i].weigth; // 输入n个叶子节点的权重
    }
    for (int i = n + 1; i <= m; i++) {
        int s1, s2;
        Select(ht, i - 1, s1, s2); // 选择权值最小的两个字符
        ht[s1].parent = i;
        ht[s2].parent = i;
        ht[i].lchild = s1;
        ht[i].rchild = s2;
        ht[i].weigth = ht[s1].weigth + ht[s2].weigth;
    }
}

int main(){
    std::cout << "请输入叶子节点数目(n>1):" << std::endl;
    std::cin >> n;
    std::cout << "请输入权值" << std::endl;
    huffmantree ht;
    huffmancode hc;
    createhuffmantree(ht, n);

    std::cout << std::endl << std::endl;
    std::cout << "输出权值" << std::endl;
    for (int i = 1; i <= m; i++) {

        std::cout << ht[i].weigth << ' ';
    }
    std::cout << std::endl << std::endl;

    std::cout << "输出哈夫曼编码" << std::endl;
    createhuffmancode(ht, hc, n);
    for (int i = 1; i <= n; i++) {
        std::cout << ht[i].weigth << ' ' << hc[i] << std::endl;
    }
    delete ht;

    // 测试if-else if，第一个if执行后，第二个else-if是否会执行
        // int a = 2, b = 3;
        // if (a > 0) {//执行
        //     std::cout << "a>0";
        // } else if (b > 0) {//不执行
        //     std::cout << "b>0";
        // }
        // //a>0
}