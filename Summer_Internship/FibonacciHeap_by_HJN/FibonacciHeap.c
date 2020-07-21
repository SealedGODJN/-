/*
    作者：黄江南
    目的：熟悉斐波那契堆的实现
*/

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <float.h>
#include "FibonacciHeap.h"

#if 0
#define LOG2(x) ({ \
        unsigned int _i = 0; \
        __asm__("bsr %1, %0" : "=r" (_i) : "r" ((x))); \
        _i; })
#else   // 注意：通过gcc编译时，要添加 -lm 选项。
#define LOG2(x) ((log((double)(x))) / (log(2.0)))
#endif

static FibNode *fib_heap_search(FibHeap *heap, Type key);

// 创建Fibonacci堆
FibHeap* fib_heap_make()
{
    FibHeap *heap = (FibHeap *)malloc(sizeof(FibHeap));
    if (heap == NULL)
    {
        printf("内存空间不够，未能初始化FibHeap");
        return NULL;
    }
    
    heap->keyNum = 0;
    heap->maxDegree = 0;
    heap->min = NULL;
    heap->cons = NULL;

    return heap;
}

/*
 * 将node从双链表移除
 */
static void fib_node_remove(FibNode *node)
{
    node->left->right = node->right;
    node->right->left = node->left;
}

/*
 * 将"单个节点node"加入"链表root"之前
 *   a …… root
 *   a …… node …… root
 *
 * 注意： 此处node是单个节点，而root是双向链表
*/
static void fib_node_add(FibNode *node, FibNode *root)
{
    FibNode *tmp;
    tmp = root->left;
    root->left = node;
    node->left = tmp;
    node->right = root;
    tmp->right = node;

}


/*
 * 创建斐波那契堆的节点
 */
static FibNode* fib_node_make(Type key)
{
    FibNode *node = (FibNode *)malloc(sizeof(FibNode));
    if (node == NULL)
    {
        printf("内存空间不足，未能创建node");
        return NULL;
    }
    node->key = key;
    node->degree = 0;
    node->left = NULL;
    node->right = NULL;
    node->child = NULL;
    node->parent = NULL;
    node->marked = 0;

    return node;
}

/*
 * 将节点node插入到斐波那契堆heap中
 */
static void fib_heap_insert_node(FibHeap *heap, FibNode *node)
{
    // 不应该用heap->min==NULL判断
    //if (heap->min == NULL)
    if (heap->keyNum == 0)
    {
        heap->min = node;
        // heap->keyNum++;
        // 和else中的heap->keyNum++;合并

        // heap->maxDegree++;
        // 这一句是否需要呢？
        // heap->keyNum==0，说明原本的堆中没有节点
        // 添加一个节点后，堆中仅有一个节点，该节点没有孩子，所以maxDegree仍然=0
        // return;
    }
    else
    {
        fib_node_add(node, heap->min);
        if(node->key < heap->min->key) {
            heap->min = node;
        }
        // heap->keyNum++; // 容易忘
    }
    heap->keyNum++;
}

/*
 * 新建键值为key的节点，并将其插入到斐波那契堆中
 */
void fib_heap_insert_key(FibHeap *heap, Type key)
{
    if (heap == NULL) // 未初始化heap，不能进行接下来插入节点的操作
    {
        return;
    }

    FibNode *node = fib_node_make(node->key);
    if (node == NULL)
    {
        return;
    }
    fib_heap_insert_node(heap, node);
}

/*
 * 将双向链表b链接到双向链表a的后面
 *
 * 注意： 此处a和b都是双向链表
*/
static void fib_node_cat(FibNode *a, FibNode *b)
{
    FibNode *tmp;
    
    tmp = a->right;
    a->right = b->right;
    b->right->left = a;
    tmp->left = b;
    b->right = tmp;
}

/*
 * 将h1, h2合并成一个堆，并返回合并后的堆
 */
FibHeap* fib_heap_union(FibHeap *h1, FibHeap *h2)
{
    FibNode *tmp;
    if(h1 == NULL)
        return h2;
    if(h2 == NULL)
        return h1;
    
    if(h2->maxDegree > h1->maxDegree) // 保证h1的maxDegree比h2的maxDegree更大
    {
        tmp = h1;
        h1 = h2;
        h2 = tmp;
    }

    if (h1->min == NULL) // 间接的表明h1为空？
    {
        // free(h1);
        // free(h1->cons);
        // 错误！

        h1->min = h2->min; // 如果h1为空，则将h2的内容复制给h1
        h1->keyNum = h2->keyNum;
        free(h2->cons);
        free(h2);
    }
    else if (h2->min == NULL) // h1->min!=NULL && h2->min==NULL
    {
        // free(h2);
        // free(h2->cons);
        // 顺序错误！
        // 如果先free(h2)，则进程已经找不到h2->cons所在的内存位置了

        free(h2->cons); // 有可能h2经历过extract_min，创建过h2->cons对应的内存空间（需要释放）
        free(h2);

        // return h1;
        // 放到最后再return
    }
    else if (h1->min != NULL && h2->min != NULL)
    {
        // fib_node_cat(h1, h2);
        // 错误，应该是h1->min和h2->min
        fib_node_cat(h1->min, h2->min);
        // if (h1->min > h2->min)
        // 错误！应该是对应的key比较大小！
        if (h1->min->key > h2->min->key)
        {
            h1->min = h2->min;
        }
        // h1和h2合并，节点总数改变了！
        h1->keyNum += h2->keyNum;
        free(h2->cons);
        // 少了free(h2)
        free(h2);
    }

    return h1;
}


/*
 * 将"堆的最小结点"从根链表中移除，
 * 这意味着"将最小节点所属的树"从堆中移除!
 * 
 * 返回值为：移除掉的heap->min
 */
static FibNode *fib_heap_remove_min(FibHeap *heap)
{
    FibNode *tmp;
    tmp = heap->min;
    if (heap->min->right == heap->min)
    {
        heap->min = NULL;
    }
    else
    {
        fib_node_remove(heap->min);
        // 此时应该将heap->min往右移动一位
        heap->min = tmp->right;
    }

    // 还应该规范移除下来的min节点的left、right
    tmp->left = tmp->right = tmp;

    return tmp;
}

/*
 * 将node链接到root根结点
 */
static void fib_heap_link(FibNode * node, FibNode *root)
{
    // 注意！！！首先要将node从双向链表中移除
    fib_node_remove(node);

    if (root->child == NULL)
    {
        root->child = node;

        // if和else中都需要设置，合并到外面
        //node->parent = root;

        // 不需要设置？
        // node->left = node->right = node;
    }
    else
    {
        fib_node_add(node, root->child);
    }
    node->parent = root;
    
    // 此外，root->degree也需要更新
    root->degree++;
    node->marked = 0; // 更新marked值（相当于以node为根节点的树重生了一次）
}

/*
 * 创建fib_heap_consolidate所需空间
 */
static void fib_heap_cons_make(FibHeap * heap)
{
    // heap->cons = (FibNode **)malloc(sizeof(FibNode) * heap->maxDegree);
    // 错误！
    // 1、要考虑heap之前是否已经经过一次fib_heap_extract_min
    // 如果已经经过了一次，则heap->cons所对应的内存空间不为空，需要重新分配内存
    // （如果之前分配的空间已经足够，则不需要再分配一次空间）
    // 应该使用reallloc函数（用于调整已分配的内存空间大小）
    // 2、由于此处cons是一个连续内存空间（数组）的指针的指针，sizeof的对象应该为FibNode*
    int old = heap->maxDegree;
    heap->maxDegree = LOG2(heap->keyNum) + 1;
    if(old >= heap->maxDegree)
    {
        return;
    }
    heap->cons = (FibNode **)realloc(heap->cons, sizeof(FibNode *) * (heap->maxDegree));
}

/*
 * 合并斐波那契堆的根链表中左右相同度数的树
 */
static void fib_heap_consolidate(FibHeap *heap)
{
    int i, d, D;
    FibNode *x, *y, *tmp;

    fib_heap_cons_make(heap);
    D = heap->maxDegree + 1;
    // 因为要将heap中的根链表中的所有节点都移到heap->cons中，
    // 至少需要maxDegree+1个空间

    for (i = 0; i < D; i++)
    {
        heap->cons[i] = NULL;
    }

    while (heap->min != NULL)
    {
        x = fib_heap_remove_min(heap);
        d = x->degree;

        while (heap->cons[d] != NULL)
        {
            y = heap->cons[d]; // 将之前放入cons[d]中的那棵树y和x合并
            // if(y->degree > x->degree) // 保证x的度更大
            // 错误！
            // 此处为保证最小堆的性质，才需要判断x->key和y->key的大小

            if (x->key > y->key)        // 保证x的键值比y小
            {
                tmp = y;
                y = x;
                x = y;
            }
            fib_heap_link(y, x);
            heap->cons[d] = NULL;
            d++;
        }
        heap->cons[d] = x;
    }

    for (i = 0; i < D; i++)
    {
        if(heap->cons[i] != NULL)
        {
            if(heap->min == NULL)
            {
                heap->min = heap->cons[i];
            }
            else
            {
                fib_node_add(heap->cons[i]);
                if(heap->min->key > heap->cons[i]->key)
                {
                    heap->min = heap->cons[i];
                }
            }
        }
    }
}


/*
 * 移除最小节点，并返回移除的min节点
 */
FibNode* _fib_heap_extract_min(FibHeap *heap)
{
    FibNode *child = NULL;
    FibNode *min = heap->min;

    while (min->child != NULL)
    {
        child = min->child;
        child->parent = NULL;
        fib_node_remove(child);
        // min->child = child->right;
        // 错误：未考虑到每一层是一个双向链表
        // min可能只有一个孩子，此时尽管执行了fib_node_remove(child)
        // child->right还是child
        // 需要对其进行判断
        if (child->right == child)
        {
            min->child = NULL;
        }
        else
        {
            min->child = child->right;
        }

        // renew_degree(min, 1);
        // 不需要更新degree，因为min节点马上就要被删除了
        fib_heap_insert_node(heap, child);
    }

    fib_node_remove(min);
    
    // if (heap->keyNum == 1)
    // 考虑min是堆中唯一节点
    // 两个if条件是否等价呢？
    // 个人认为是等价的
    if (heap->min->right == heap->min)
    {
        heap->min = NULL;
    }
    else // min不是堆中唯一节点
    {
        // 此时需要临时指定一个heap->min，为什么呢？
        // fib_heap_consolidate里面需要用到heap->min，
        // 需要不断地从heap中找出min节点，并将其放入heap->cons中
        // 所以需要指定一个heap->min
        heap->min = min->right;
        fib_heap_consolidate(heap);
    }

    heap->keyNum--;

    return min;
}

void fib_heap_extract_min(FibHeap *heap)
{
    if(heap == NULL)
        return;
    if(heap->min == NULL)
        return;
    FibNode *node = _fib_heap_extract_min(heap); // node接收heap->min
    if(node != NULL)
    {
        free(node);
    }
}

/*
 * 在斐波那契堆heap中是否存在键值为key的节点；存在返回1，否则返回0。
 */
int fib_heap_get_min(FibHeap *heap, Type *pkey)
{

}

/*
 * 修改度数
 */
static void renew_degree(FibNode *parent, int degree)
{

}

/*
 * 将node从父节点parent的子链接中剥离出来，
 * 并使node成为"堆的根链表"中的一员。
 */
static void fib_heap_cut(FibHeap *heap, FibNode *node, FibNode *parent)
{

}

/*
 * 对节点node进行"级联剪切"
 *
 * 级联剪切：如果减小后的结点破坏了最小堆性质，
 *     则把它切下来(即从所在双向链表中删除，并将
 *     其插入到由最小树根节点形成的双向链表中)，
 *     然后再从"被切节点的父节点"到所在树根节点递归执行级联剪枝
 */
static void fib_heap_cascading_cut(FibHeap *heap, FibNode *node)
{

}

/*
 * 将斐波那契堆heap中节点node的值减少为key
 */
static void fib_heap_decrease(FibHeap *heap, FibNode *node, Type key)
{

}

/*
 * 将斐波那契堆heap中节点node的值增加为key
 */
static void fib_heap_increase(FibHeap *heap, FibNode *node, Type key)
{

}

/*
 * 更新二项堆heap的节点node的键值为key
 */
void _fib_heap_update(FibHeap *heap, FibNode *node, Type key)
{

}

void fib_heap_update(FibHeap *heap, Type oldkey, Type newkey)
{

}

/*
 * 在最小堆root中查找键值为key的节点
 */
static FibNode* fib_node_search(FibNode *root, Type key)
{

}

/*
 * 在斐波那契堆heap中查找键值为key的节点
 */
static FibNode *fib_heap_search(FibHeap *heap, Type key)
{

}

/*
 * 在斐波那契堆heap中是否存在键值为key的节点。
 * 存在返回1，否则返回0。
 */
int fib_heap_contains(FibHeap *heap, Type key)
{

}

/*
 * 删除结点node
 */
static void _fib_heap_delete(FibHeap *heap, FibNode *node)
{

}

void fib_heap_delete(FibHeap *heap, Type key)
{

}

/*
 * 销毁斐波那契堆
 */
static void fib_node_destroy(FibNode *node)
{

}

void fib_heap_destroy(FibHeap *heap)
{
    fib_node_destroy(heap->min);
    free(heap->cons);
    free(heap);
}

/*
 * 打印"斐波那契堆"
 *
 * 参数说明：
 *     node       -- 当前节点
 *     prev       -- 当前节点的前一个节点(父节点or兄弟节点)
 *     direction  --  1，表示当前节点是一个左孩子;
 *                    2，表示当前节点是一个兄弟节点。
 */
static void _fib_print(FibNode *node, FibNode *prev, int direction)
{

}

void fib_print(FibHeap *heap)
{

}