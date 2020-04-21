/**
 * @author hjn
 * @time: 2020.4.19
 */
#include <stdio.h>
#include <stdlib.h>

typedef int ElementType;
typedef struct Node *PtrToNode;
struct Node {
    ElementType Data;
    PtrToNode   Next;
};
typedef PtrToNode List;

List Read(); /* 细节在此不表 */
void Print( List L ); /* 细节在此不表；空链表将输出NULL */

List Merge( List L1, List L2 );

int main()
{
    List L1, L2, L;
    L1 = Read();
    L2 = Read();
    L = Merge(L1, L2);
    Print(L);
    Print(L1);
    Print(L2);
    return 0;
}

/* 你的代码将被嵌在这里 */

/**
 * 初始化List，并返回初始化之后的List
 * @return:L
 */
List Read()
{
    int length = 0;
    int temp = 0;
    scanf("%d", &length); // 输入链表的长度

    List L = (List)malloc(sizeof(List)); // 创建头节点
    if(NULL == L)
    {
        printf("头节点 Malloc Failed!\n");
        exit(1); // 表示了一个进程的结束
    }
    scanf("%d", &temp);
    L->Data = temp;

    List *temp_l = (List*)malloc(sizeof(List)); // 作为中间变量
    if(NULL == *temp_l)
    {
        printf("中间变量 Malloc Failed!\n");
        exit(1); // 表示了一个进程的结束
    }
    *temp_l = L;
    for (int i = 1; i < length; i++)
    {
        scanf("%d", &temp);
        List l = (List)malloc(sizeof(List));
        if(NULL == l)
        {
            printf("头节点 Malloc Failed!\n");
            exit(1); // 表示了一个进程的结束
        }
        l->Data = temp;
        (*temp_l)->Next = l;
        *temp_l = l;
        if(NULL == l)
        {
            printf("Malloc Failed!\n");
            exit(1); // 表示了一个进程的结束
        }
    }
    (*temp_l)->Next = NULL;

    return L;
}

/**
 * 如果L不为空，则输出链表中所有的元素；如果L为空，则输出NULL
 * @arg: L
 */
void Print( List L )
{
    List *temp_l = (List*)malloc(sizeof(List));
    if(NULL == *temp_l)
    {
        printf("中间变量 Malloc Failed!\n");
        exit(1); // 表示了一个进程的结束
    }
    *temp_l = L;

    if(*temp_l == NULL) // 链表为空，则输出NULL
    {
        printf("NULL\n");
    }

    while((*temp_l) != NULL)
    {
        printf("%d", (*temp_l)->Data);
        *temp_l = (*temp_l)->Next;
        if((*temp_l) != NULL)
        {
            printf(" ");
        }
    }
    printf("\n");
}

/**
 * 将L1和L2合并为一个非递减的整数序列。应直接使用原序列中的结点，返回归并后的带头结点的链表头指针。
 * @arg: L1 L2 L1和L2是给定的带头结点的单链表，其结点存储的数据是递增有序的
 */
List Merge(List L1, List L2)
{
    if(L1 == NULL)
    {
        return L2;
    }
    if(L2 == NULL)
    {
        return L1;
    }
    List L = (List)malloc(sizeof(List));
    if(NULL == L)
    {
        printf("中间变量 Malloc Failed!\n");
        exit(1); // 表示了一个进程的结束
    }

    List *nextNode = (List*)malloc(sizeof(List));
    if(NULL == *nextNode)
    {
        printf("中间变量 Malloc Failed!\n");
        exit(1); // 表示了一个进程的结束
    }
    *nextNode = L1;

    // List *preNode = (List*)malloc(sizeof(List));
    // if(NULL == *preNode)
    // {
    //     printf("中间变量 Malloc Failed!\n");
    //     exit(1); // 表示了一个进程的结束
    // }
    // *preNode = L1;

    List *temp_1 = (List*)malloc(sizeof(List));
    if(NULL == *temp_1)
    {
        printf("中间变量 Malloc Failed!\n");
        exit(1); // 表示了一个进程的结束
    }
    *temp_1 = L;

    List *temp_2 = (List*)malloc(sizeof(List));
    if(NULL == *temp_2)
    {
        printf("中间变量 Malloc Failed!\n");
        exit(1); // 表示了一个进程的结束
    }
    *temp_2 = L2;
    while((*nextNode) != NULL)
    {
        while((*temp_2) != NULL)
        {
            if((*temp_2)->Data <= (*nextNode)->Data)
            {
                (*temp_1)->Data = (*temp_2)->Data;
                (*temp_1) = *temp_2;
                (*temp_1) = (*temp_1)->Next;
                (*temp_2) = (*temp_2)->Next;
                // (*temp_1)->Next = NULL; // 有问题
            }
            else
            {  
               (*temp_2) = (*temp_2)->Next;
            }
            
        }
        (*temp_1)->Data = (*nextNode)->Data;
        (*temp_1) = *nextNode;
        (*temp_1) = (*temp_1)->Next;
        *nextNode = (*nextNode)->Next;
        // (*temp_1)->Next = NULL; // 有问题
    }
    return L;
    // while((*nextNode) != NULL)
    // {
    //     printf("%d", (*nextNode)->Data);
    //     *preNode = *nextNode;
    //     *nextNode = (*nextNode)->Next;
    //     while((*temp_2) != NULL)
    //     {
    //         if((*temp_2)->Data <=(*nextNode)->Data)
    //         { // 插入节点
    //             List *temp = (List*)malloc(sizeof(List));
    //             if(NULL == *temp)
    //             {
    //                 printf("中间变量 Malloc Failed!\n");
    //                 exit(1); // 表示了一个进程的结束
    //             }
    //             *temp = (*temp_2)->Next; // 保存当前节点的信息
    //             (*preNode)->Next = *temp_2;
    //             (*temp_2)->Next = *nextNode;
    //             *temp_2 = *temp;
    //         }
    //         else *temp_2 = (*temp_2)->Next;
    //     }
    // }   
}