/*
    作者：黄江南
    目的：熟悉二项堆的实现
    时间：2020.7.14
*/

#include "BinomialHeap.h"
#include <stdio.h>
#include <stdlib.h>

#define swap(a,b) (a^=b,b^=a,a^=b)

/*
    查找：在二项堆heap中查找键值为key的节点
*/
BinomialNode* binomial_search(BinomialHeap heap, Type key)
{
    BinomialNode *child;
    BinomialNode *parent = heap;

    while (parent!=NULL)
    {
        if (parent->key == key)
        {
            return parent;
        }
        else
        {
            if ((child = binomial_search(parent->child,key)) != NULL)
            {
                return child;
            }
            // else
            // {
            //     return NULL;
            // }
            // else分支错误，刚刚是在以parent为根节点的树寻找该key
            // 接下来应该去寻找另一棵树
            parent = parent->next;
        }
    }
    return NULL;
}

/*
 * 获取二项堆中的最小根节点(*y)
 * 
 * 参数说明：
 *  heap    二项堆
 *  prev_y  [输出参数]最小根节点y的前一个根节点
 *  y       [输出参数]最小根节点
 */
static void _binomial_minimum(BinomialHeap heap, BinomialNode **prev_y, BinomialNode **y)
{
    BinomialNode *x, *prev_x; // x用来遍历的当前节点
    if(heap == NULL)
        return;

    prev_x = heap;
    x = heap->next;
    *prev_y = NULL;
    *y = heap;
    while (x!=NULL)
    {
        if(x->key < (*y)->key)
        {
            *y = x;
            *prev_y = prev_x;
        }
        prev_x = x;
        x = prev_x->next; // 相当于x=x->next
    }
    
}

/*
 * 返回二项堆中具有最小key的节点
 */
BinomialNode* binomial_minimum(BinomialHeap heap)
{
    BinomialNode *prev_y, *y;
    _binomial_minimum(heap, &prev_y, &y);
    return y;
}

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

/**
 * 新建二项堆的节点
 */
static BinomialNode* make_binomial_node(Type key)
{
    BinomialNode *node;
    node = (BinomialNode *)malloc(sizeof(BinomialNode));
    if (node == NULL)
    {
        printf("malloc BinomialNode failed!\n");
        return NULL;
    }

    node->key = key;
    node->degree = 0;
    node->parent = NULL;
    node->child = NULL;
    node->next = NULL;

    return node;
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
static BinomialNode* binomial_reverse(BinomialNode* heap)
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

/**
 * 移除最小节点，并返回移除节点后的二项堆
 */
BinomialNode* binomial_extract_minimum(BinomialHeap heap)
{
    BinomialNode *y, *prev_y; // y是最小节点
    if (heap == NULL)
    {
        return heap;
    }

    // 找到最小节点和最小节点的前一个节点
    _binomial_minimum(heap, &prev_y, &y);

    if (prev_y == NULL) // heap的根节点就是最小节点
    {
        heap = heap->next;
    }
    else
    {
        prev_y->next = y->next; // heap的根节点不是最小节点
    }
    
    // 反转最小节点的左孩子，得到最小堆child
    // 使最小节点所在的二项树独立出来
    BinomialNode *child = binomial_reverse(y->child);
    // 将“child”和“heap”合并
    heap = binomial_union(heap, child);

    // 删除最小节点
    free(y);

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

/**
 * 将二项堆heap的键值oldkey更新为newkey
 */
void binomial_update(BinomialHeap heap, Type oldkey, Type newkey)
{
    BinomialNode *node;
    if(heap == NULL)
    {
        return;
    }
    node = binomial_search(heap, oldkey);
    if(node != NULL)
    {
        binomial_update_key(heap, node, newkey);
    }
}

/**
 * 打印“二项堆”
 * 
 * 参数说明：
 *  node        当前节点
 *  prev        当前节点的前一个节点（父节点or兄弟节点）
 *  direction   1:表示当前节点是一个左孩子，2：表示当前节点是一个兄弟节点
 */
static void _binomial_print(BinomialNode *node, BinomialNode *prev, int direction)
{
    while (node!=NULL)
    {
        //printf("%2d \n", node->key);
        if (direction==1)
        {
            printf("\t%2d(%d) is %2d's child\n", node->key, node->degree, prev->key);
        }
        else
        {
            printf("\t%2d(%d) is %2d's next\n", node->key, node->degree, prev->key);
        }
        
        if (node->child != NULL)
        {
            _binomial_print(node->child, node, 1);
        }

        // 兄弟节点
        prev = node;
        node = node->next;
        direction = 2;
    }
    
}

/*
    打印二叉堆heap
*/
void binomial_print(BinomialHeap heap)
{
    if(heap == NULL)
        return;
    BinomialNode *p = heap;
    printf("== 二项堆（");
    while (p!=NULL)
    {
        printf("B%d ", p->degree);
        p = p->next;
    }
    printf("）的详细信息：\n");

    int i = 0;
    while (heap!=NULL)
    {
        i++;
        printf("%d. 二项树B%d: \n", i, heap->degree);
        printf("\t%2d(%d) is root\n", heap->key, heap->degree);

        _binomial_print(heap->child, heap, 1);
        heap = heap->next;
    }
    printf("\n");
}


#define DEBUG 1

#if DEBUG
#define log(x, ...)   printf(x, __VA_ARGS__)
#else
#define log(x, ...)
#endif

#define LENGTH(a) ( (sizeof(a)) / (sizeof(a[0])) )

// 共7个 = 1+2+4
int a[] = {12,  7, 25, 15, 28,
           33, 41};
// 共13个 = 1+4+8
int b[] = {18, 35, 20, 42,  9,
           31, 23,  6, 48, 11,
           24, 52, 13 };
// 验证"二项堆的插入操作"
void test_insert()
{
    int i;
    int alen=LENGTH(a);
    BinomialHeap ha=NULL;

    // 二项堆ha
    printf("== 二项堆(ha)中依次添加: ");
    for(i=0; i<alen; i++)
    {
        printf("%d ", a[i]);
        ha = binomial_insert(ha, a[i]);
    }
    printf("\n");
    // 打印二项堆ha
    printf("== 二项堆(ha)的详细信息: \n");
    binomial_print(ha);
}

// 验证"二项堆的合并操作"
void test_union()
{
    int i;
    int alen=LENGTH(a);
    int blen=LENGTH(b);
    BinomialHeap ha,hb;

    ha=hb=NULL;

    // 二项堆ha
    printf("== 二项堆(ha)中依次添加: ");
    for(i=0; i<alen; i++)
    {
        printf("%d ", a[i]);
        ha = binomial_insert(ha, a[i]);
    }
    printf("\n");
    printf("== 二项堆(ha)的详细信息: \n");
    binomial_print(ha); // 打印二项堆ha

    // 二项堆hb
    printf("== 二项堆(hb)中依次添加: ");
    for(i=0; i<blen; i++)
    {
        printf("%d ", b[i]);
        hb = binomial_insert(hb, b[i]);
    }
    printf("\n");
    printf("== 二项堆(hb)的详细信息: \n");
    binomial_print(hb); // 打印二项堆hb

    // 将"二项堆hb"合并到"二项堆ha"中。
    ha = binomial_union(ha, hb);
    printf("== 合并ha和hb后的详细信息:\n");
    binomial_print(ha); // 打印二项堆ha的详细信息
}

// 验证"二项堆的删除操作"
void test_delete()
{
    int i;
    int blen=LENGTH(b);
    BinomialHeap hb=NULL;

    // 二项堆hb
    printf("== 二项堆(hb)中依次添加: ");
    for(i=0; i<blen; i++)
    {
        printf("%d ", b[i]);
        hb = binomial_insert(hb, b[i]);
    }
    printf("\n");
    printf("== 二项堆(hb)的详细信息: \n");
    binomial_print(hb); // 打印二项堆hb

    // 删除二项堆hb中的节点
    i = 20;
    hb = binomial_delete(hb, i);
    printf("== 删除节点%d后的详细信息: \n", i);
    binomial_print(hb); // 打印二项堆hb
}

// 验证"二项堆的更新(减少)操作"
void test_decrease()
{
    int i;
    int blen=LENGTH(b);
    BinomialHeap hb=NULL;

    // 二项堆hb
    printf("== 二项堆(hb)中依次添加: ");
    for(i=0; i<blen; i++)
    {
        printf("%d ", b[i]);
        hb = binomial_insert(hb, b[i]);
    }
    printf("\n");
    printf("== 二项堆(hb)的详细信息: \n");
    binomial_print(hb); // 打印二项堆hb

    // 将节点20更新为2
    binomial_update(hb, 20, 2);
    printf("== 更新节点20->2后的详细信息: \n");
    binomial_print(hb); // 打印二项堆hb
}

// 验证"二项堆的更新(增加)操作"
void test_increase()
{
    int i;
    int blen=LENGTH(b);
    BinomialHeap hb=NULL;

    // 二项堆hb
    printf("== 二项堆(hb)中依次添加: ");
    for(i=0; i<blen; i++)
    {
        printf("%d ", b[i]);
        hb = binomial_insert(hb, b[i]);
    }
    printf("\n");
    printf("== 二项堆(hb)的详细信息: \n");
    binomial_print(hb); // 打印二项堆hb

    // 将节点6更新为20
    binomial_update(hb, 6, 60);
    printf("== 更新节点6->60后的详细信息: \n");
    binomial_print(hb); // 打印二项堆hb
}


void main()
{
    // 1. 验证"二项堆的插入操作"
    // test_insert();
    // 2. 验证"二项堆的合并操作"
    // test_union();
    // 3. 验证"二项堆的删除操作"
    // test_delete();
    // 4. 验证"二项堆的更新(减少)操作"
    // test_decrease();
    // 5. 验证"二项堆的更新(增加)操作"
    test_increase();
}