#include <stdio.h>

int main() {
    int a[10];
    for (int i = 0; i <= 9; i++) {
        a[i] = i;
    }
    for (int i = 0; i <= 9; i++) {
        printf("%d\n", a[i]);
    }
    return 0;
}