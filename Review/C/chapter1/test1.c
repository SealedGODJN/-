#include <stdio.h>

int main()
{
    float inch;
    printf("请输入英寸值:");
    scanf("%f", &inch);
    inch = inch * 2.54;
    printf("单位转换为厘米，结果为：%f", inch);
}