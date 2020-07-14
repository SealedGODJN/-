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

/*
    新建key对应的节点，将其插入到二项堆中

    参数说明：
        heap 原始的二项树
        key  键值
    返回值：
        插入key之后的二项树

    备注：
        禁止插入相同key的节点（若想允许，则可以屏蔽binomial_search部分的代码）
*/
BinomialNode* binomial_insert(BinomialHeap heap, Type key)
{
    BinomialNode *node;

    if(binomial_search(heap,key)!=NULL)
    {
        printf("insert failed: the key(%d) is existed already!\n", key);
        return heap;
    }

    node = make_binomial_node(key);
    if (node == NULL)
    {
        return heap;
    }

    return binomial_union(heap, node);
}

/*
    反转二项堆heap

    将根的所有孩子独立出来（之前作为child的节点，现在变成parent，关系反转），并将这些孩子整合成二项堆
*/
static binomial_reverse(BinomialNode* heap)
{
    BinomialNode *next;
    BinomialNode *tail = NULL;

    if(!heap)
        return heap;

    heap->parent = NULL;
    while(heap->next)
    {
        next = heap->next; // 先将heap->next保存在next中
        heap->next = tail; // 第一次：置heap->next为空；之后：heap->next = 之前的heap（关系反转）
        tail = heap;
        heap = next; // 相当于heap = heap->next
        heap->parent = NULL;
    }
    heap->next = tail;

    return heap;
}

/*
    删除节点：删除键值为key的节点，并返回删除节点后的二项树
*/
BinomialNode* binomial_delete(BinomialHeap heap, Type key)
{
    BinomialNode *node;
    BinomialNode *parent, *prev, *pos;

    if (heap == NULL)
    {
        return heap;
    }

    // 查找键值为key的节点
    if ((node = binomial_search(heap,key)) == NULL)
    {
        return heap;
    }

    // 将被删除的节点的数据，上移到它所在的二项树的根节点
    parent = node->parent;
    while (parent != NULL)
    {
        // 交换数据
        swap(node->key, parent->key);
        // 下一个父节点
        node = parent;
        parent = node->parent;
    }
    
    // 找到node的前一个根节点(prev)
    prev = NULL;
    pos = heap;
    while (pos!=node)
    {
        prev = pos;
        pos = pos->next;
    }

    if (prev)
    {
        prev->next = node->next;
    }
    else
    {
        heap = node->next; // prev为空，说明node原本就是堆的根节点
    }
    heap = binomial_union(heap, binomial_reverse(node->child));

    free(node);

    return heap;
}

/*
    减少节点的key:将二项堆heap中的节点node的键值减少为key
*/
static void binomial_decrease_key(BinomialHeap heap, BinomialNode* node, Type key)
{
    if ((key >= node->key) || (binomial_search(heap, key) != NULL))
    {
        printf("decrease failed: the new key(%d) is exised already, \
         or is no smaller than current key(%d)\n", key, node->key);
        return;
    }

    node->key = key;

    BinomialNode *child, *parent;
    child = node;
    parent = node->parent;
    while (parent!=NULL && child->key < parent->key)
    {
        swap(child->key, parent->key);
        child = parent;
        parent = child->parent;
    }
    
}

/*
    增加关键字的值：将二项堆heap中的节点node的键值增加为key
*/
static void binomial_increase_key(BinomialHeap heap, BinomialNode* node, Type key)
{
    if ((key <= node->key) || (binomial_search(heap, key) != NULL))
    {
        printf("increase failed: the new key(%d) is exised already, \
         or is no bigger than current key(%d)\n", key, node->key);
        return;
    }

    node->key = key;

    BinomialNode *cur, *child, *least;
    cur = node;
    child = cur->child;
    while (child!=NULL)
    {
        if(cur->key > child->key)
        {
            // 如果“cur的key” < “cur的左孩子的key”
            // 则在cur的所有孩子中找出具有最小key的节点
            // 将最小key的节点和cur节点的key对换
            least = child;
            while (child->next != NULL)
            {
                if (child->key > child->next->key)
                {
                    least = child->next;
                }
                child = child->next;
            }
            swap(least->key, cur->key);

            // 交换数据之后，再对”原最小节点“进行调整，使它满足最小堆的性质：父节点<=子节点
            cur = least;
            child = cur->child;
        }
        else
        {
            child = child->next;
        }
        
    }
    
}

/*
    更新二项堆heap的节点node的键值为key
*/
static void binomial_update_key(BinomialHeap heap, BinomialNode* node, Type key)
{
    if (node == NULL)
    {
        return;
    }

    if (key<node->key)
    {
        binomial_decrease_key(heap, node, key);
    }
    else if (key > node->key)
    {
        binomial_increase_key(heap, node, key);
    }
    else
    {
        printf("No need to update!!!\n");
    }
}

