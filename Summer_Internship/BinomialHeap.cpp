/*
    作者：黄江南
    目的：熟悉二项堆的实现
    时间：2020.7.14
*/

#include <stdio.h>

#ifndef _BINOMIAL_HEAP_H_
#define _BIONMIAL_HEAP_H_

typedef int Type;

typedef struct _BinomialNode{
    Type key;                       // 关键字（键值）:用于比较节点大学
    int degree;                     // 节点的度数：用来表示当前节点的度数
    /*
        根节点的度数=C(1,k)，即代表该二项树的k
        在合并“根节点度数相同的二项树”时，
        另一位博主的做法为：专门维护一个变量k（保存树的k值）
        而这位博主为树的每个节点都记录其度数
        （个人感觉比较浪费，除了根节点有用到度数，其他节点没有用到度数？）
    */
    struct _BinomialNode *child;    // 左孩子
    struct _BinomialNode *parent;   // 父节点
    struct _BinomialNode *next;     // 兄弟
} BinomialNode, *BinomialHeap;

// 新建key对应的节点，并将其插入到二项堆中
BinomialNode *binomial_insert(BinomialHeap heap, Type key);
// 删除节点：删除键值为key的节点，并返回删除节点后的二项树
BinomialNode *binomial_delete(BinomialHeap heap, Type key);
// 将二项堆heap的键值oldkey更新为newkey
void binomial_update(BinomialHeap heap, Type oldkey, Type newkey);

// 合并二项堆：将h1, h2合并成一个堆，并返回合并后的堆
BinomialNode *binomial_union(BinomialHeap h1, BinomialHeap h2);

// 查找：在二项堆中查找键值为key的节点
BinomialNode *binomial_search(BinomialHeap heap, Type key);
// 获取二项堆中的最小节点
BinomialNode *binomial_minimum(BinomialHeap heap);
// 移除最小节点，并返回移除节点后的二项堆
BinomialNode *binomial_extract_minimum(BinomialHeap heap);

// 打印“二项堆”
void binomial_print(BinomialHeap heap);

#endif

/*
    将h1,h2中的根表合并成一个按度数递增的链表，返回合并后的根节点
*/
static BinomialNode* binomial_merge(BinomialHeap h1, BinomialHeap h2)
{
    BinomialNode *head = NULL; // heap为指向新堆的根节点
    BinomialNode **pos = &head;

    while(h1 && h2) // 当h1和h2都不为空时
    {
        if (h1->degree < h2->degree)
        {
            *pos = h1; // 将h1的根节点作为新堆的根节点（因为h1的度数更小）
            h1 = h1->next; // 寻找h1的下一颗树
        }
        else
        {
            *pos = h2;
            h2 = h2->next;
        }
        pos = &(*pos)->next; // 下一次循环，给兄弟节点赋值
    }
    if (h1)
    {
        *pos = h1;
    }
    else if (h2)
    {
        *pos = h2;
    }
    return head;
}

/*
    合并两个二项堆：将child合并到heap中
*/
static void binomial_link(BinomialHeap child, BinomialHeap heap)
{
    child->parent = heap;
    child->next = heap->child;
    heap->child = child;
    heap->degree += 1;
}

/*
    合并二项堆：将h1，h2合并成一个堆，并返回合并后的堆
*/
BinomialNode* binomial_union(BinomialHeap h1, BinomialHeap h2)
{
    BinomialNode *heap;
    BinomialNode *prev_x, *x, *next_x;

    // 将h1, h2中的根表合并成一个按度数递增的链表heap
    heap = binomial_merge(h1, h2);
    if (heap == NULL)
    {
        return NULL;
    }

    prev_x = NULL;
    x = heap;
    next_x = x->next;

    while (next_x != NULL)
    {
        if( (x->degree != next_x->degree)
            || ((next_x->next != NULL) && (next_x->degree == next_x->next->degree)) )
        {
            // Case1: x->degree != next_x->degree
            // Case2: x->degree == next_x->degree == next_x->next->degree ???
            prev_x = x;
            x = next_x;
            // 不做任何操作？继续循环？
            // 对，对于Case2:三个度数相同的，则合并后面两个
        }
        else if (x->key <= next_x->key)
        {
            // Case3: x->degree == next_x->degree != next_x->next->degree
            //          && x->key <= next_x->key
            x->next = next_x->next;
            binomial_link(next_x, x);
        }
        else
        {
            // Case4: x->degree == next_x->dgree != next_x->next->degree
            //          && x->key > next_x->key
            if (prev_x == NULL)
            {
                heap = next_x;
                // 注意，之前有过一次赋值：x=heap，
                // 现在改变heap的指向，并不会改变x指向的对象
                // 因此，下面得重新指定x指向的对象
            }
            else
            {
                prev_x->next = next_x;
            }
            binomial_link(x, next_x);
            x = next_x;
        }
        next_x = x->next;
    }
    return heap;
}