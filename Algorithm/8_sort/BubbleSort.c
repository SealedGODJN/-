#include <stdio.h>

typedef int ElemType;

void BubbleSort(ElemType a[],int n)
{
    for (int i = 0; i < n; i++)
    {
        int m = 0;
        for (int j = i + 1; j < n; j++)
        {
            if(a[i]>a[j])
            {
                m = a[i];
                a[i] = a[j];
                a[j] = m;
            }
        }
    }
    for (int i = 0; i < n; i++)
    {
        printf("%d ", a[i]);
    }
}

int main()
{
    ElemType a[] = {1, 3, 5, 11, 2, 6, 7};
    int n = 7;
    BubbleSort(a, n);
}