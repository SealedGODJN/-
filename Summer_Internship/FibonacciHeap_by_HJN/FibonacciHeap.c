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
 * 将双向链表b链接到双向链表a的后面
 *
 * 注意： 此处a和b都是双向链表
*/
static void fib_node_cat(FibNode *a, FibNode *b)
{

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
 * 将h1, h2合并成一个堆，并返回合并后的堆
 */
FibHeap* fib_heap_union(FibHeap *h1, FibHeap *h2)
{
    FibNode *tmp;
    if(h1 == NULL)
        return h2;
    if(h2 == NULL)
        return h1;
    
}


/*
 * 将"堆的最小结点"从根链表中移除，
 * 这意味着"将最小节点所属的树"从堆中移除!
 */
static FibNode *fib_heap_remove_min(FibHeap *heap)
{

}

/*
 * 将node链接到root根结点
 */
static void fib_heap_link(FibNode * node, FibNode *root)
{

}

/*
 * 创建fib_heap_consolidate所需空间
 */
static void fib_heap_cons_make(FibHeap * heap)
{

}

/*
 * 合并斐波那契堆的根链表中左右相同度数的树
 */
static void fib_heap_consolidate(FibHeap *heap)
{

}


/*
 * 移除最小节点，并返回移除节点后的斐波那契堆
 */
FibNode* _fib_heap_extract_min(FibHeap *heap)
{

}

void fib_heap_extract_min(FibHeap *heap)
{

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