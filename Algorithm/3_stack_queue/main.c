#include <stdio.h>
#include <stdlib.h>
#include "PriorityQueue.h"

PQueue enq(PQueue *phead, datatype data, int high)
{
    PQueue pnew = (PQueue)malloc(sizeof(Queue));
    pnew->data = data;
    pnew->high = high;
    pnew->pNext = NULL;
    if((*phead) == NULL)
    {
        (*phead) = pnew; // 直接插入
    } else {
        // 先判断头节点的优先级
        if((*phead)->high <= high) {
            pnew->pNext = (*phead);
            (*phead) = pnew;
        } else {
            // 从头结点开始，一直循环到一个节点的high小于新节点的high
            PQueue ptemp = (*phead);
            while (ptemp->pNext != NULL && ptemp->pNext->high > high) // 下一个节点不为空 且 优先级更大
            {
                ptemp = ptemp->pNext;
            }
            // 有可能没找到，则直接插入到ptemp后面
            pnew->pNext = ptemp->pNext; // 易错：要把ptemp后面的节点接到pnew后面
            ptemp->pNext = pnew;
        }
    }
    return (*phead);
}

// 出队(第一个元素)
PQueue deq(PQueue *phead,datatype *pdata)
{
    if((*phead) == NULL)
        return NULL;
    else {
        *pdata = (*phead)->data; // 获取弹出的数据
        PQueue ptemp = (*phead)->pNext;
        free(phead); // 易错：先free
        (*phead) = ptemp;
    }
    return (*phead);
}

// 显示
void show(PQueue phead)
{
    if(phead == NULL)
        return;
    else{
        printf("%5d(high:%d)", phead->data, phead->high);
        show(phead->pNext);
    }
}

int main()
{
    PQueue phead = NULL;
    enq(&phead, 'a', 5);
    enq(&phead, 'b', 10);
    enq(&phead, 'c', 7);
    show(phead);
    datatype *p = NULL;
    deq(&phead, p);
    printf("\n\n出队的元素为%d", *p);
}