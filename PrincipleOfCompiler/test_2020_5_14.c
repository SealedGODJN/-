#include <stdio.h>

int x = 10, y = 20;
main()
{
    static int m = 55, n = 66;
    int k;
    int *p = &x;
    for (k = 0; k < 6; k++)
        printf("%d,  ", *p++);
}
int a = 30, b = 40;
