/**
 * @author hjn
 * @time:2020.4.21
 * 测试编译原理——语义分析，类型检查，类型相容问题的检查（比如：浮点型和整型数能否相加）
 */

#include <stdio.h>

int main() {
    // int a[10];
    int a = 10;
    float b = 2.5;
    float c = b + a;
    printf("%f\n", c);
    // for (int i = 0; i <= 9; i++) {
    //     a[i] = i;
    // }
    // for (int i = 0; i <= 9; i++) {
    //     printf("%d\n", a[i]);
    // }
    return 0;
}