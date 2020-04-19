#include <stdio.h>
#include <stdlib.h>

#define MAXSIZE 10
#define NotFound 0
typedef int ElementType;

typedef int Position;
typedef struct LNode *List;
struct LNode {
    ElementType Data[MAXSIZE];
    Position Last; /* 保存线性表中最后一个元素的位置 */
};

List ReadInput(); /* 裁判实现，细节不表。元素从下标1开始存储 */
Position BinarySearch( List L, ElementType X );

int main()
{
    List L;
    ElementType X;
    Position P;

    L = ReadInput();
    scanf("%d", &X);
    P = BinarySearch( L, X );
    printf("%d\n", P);

    return 0;
}

/* 你的代码将被嵌在这里 */

/**
 * 返回初始化之后的List
 * @return:L 
 */
List ReadInput()
{
    List L = (List)malloc(sizeof(List));
    if(NULL == L)
    {
        printf("Malloc Failed!\n");
        exit(1); // 表示了一个进程的结束
    }
    L->Last = 5;
    int temp[6] = {0, 5, 2, 6, 2, 1};
    for (int i = 0; i <= L->Last; i++)
    {
        L->Data[i] = temp[i];
    }
    return L;
}

/**
 * 返回二分查找的元素位置
 * @return:i 元素的位置
 */
Position BinarySearch( List L, ElementType X )
{
    for (int i = 1; i <= L->Last; i++)
    {
        if(X == L->Data[i])
        {
            return i;
        }
    }
    return NotFound;
}