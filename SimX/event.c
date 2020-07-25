#include <event.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <float.h>

#if 0
#define LOG2(x) ({ \
        unsigned int _i = 0; \
        __asm__("bsr %1, %0" : "=r" (_i) : "r" ((x))); \
        _i; })
#else   // 注意：通过gcc编译时，要添加 -lm 选项。
#define LOG2(x) ((log((double)(x))) / (log(2.0)))
#endif

// 创建Fibonacci堆
struct EVENT_QUEUE* EVENT_QUEUE_make()
{
    struct EVENT_QUEUE *heap = (struct EVENT_QUEUE *)malloc(sizeof(struct EVENT_QUEUE));
    if (heap == NULL)
    {
        printf("内存空间不够，未能初始化struct EVENT_QUEUE");
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
static void EVENT_NODE_remove(struct EVENT_NODE *node)
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
static void EVENT_NODE_add(struct EVENT_NODE *node, struct EVENT_NODE *root)
{
    struct EVENT_NODE *tmp;
    tmp = root->left;
    root->left = node;
    node->left = tmp;
    node->right = root;
    tmp->right = node;

}


/*
 * 创建斐波那契堆的节点
 */
static struct EVENT_NODE* EVENT_NODE_make(Type key)
{
    struct EVENT_NODE *node = (struct EVENT_NODE *)malloc(sizeof(struct EVENT_NODE));
    if (node == NULL)
    {
        printf("内存空间不足，未能创建node");
        return NULL;
    }
    node->event_describe_table = (struct EVENT_DESCRIBE_TABLE *)malloc(sizeof(struct EVENT_DESCRIBE_TABLE));
    node->event_describe_table->EVENT_TIME = key;
    node->degree = 0;
    node->left = NULL;
    node->right = NULL;
    node->child = NULL;
    node->parent = NULL;
    node->marked = 0;

    return node;
}

/*
 * 根据event_describe_table，创建斐波那契堆的节点
 */
static struct EVENT_NODE* EVENT_NODE_make_by_TABLE(struct EVENT_DESCRIBE_TABLE *event_describe_table)
{
    struct EVENT_NODE *node = (struct EVENT_NODE *)malloc(sizeof(struct EVENT_NODE));
    if (node == NULL)
    {
        printf("内存空间不足，未能创建node");
        return NULL;
    }
    node->event_describe_table = event_describe_table;

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
static void EVENT_QUEUE_insert_node(struct EVENT_QUEUE *heap, struct EVENT_NODE *node)
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
        EVENT_NODE_add(node, heap->min);
        if(node->event_describe_table->EVENT_TIME < heap->min->event_describe_table->EVENT_TIME) {
            heap->min = node;
        }
        // heap->keyNum++; // 容易忘
    }
    heap->keyNum++;
}



/*
 * 将"堆的最小结点"从根链表中移除，
 * 这意味着"将最小节点所属的树"从堆中移除!
 * 
 * 返回值为：移除掉的heap->min
 */
static struct EVENT_NODE *EVENT_QUEUE_remove_min(struct EVENT_QUEUE *heap)
{
    struct EVENT_NODE *tmp;
    tmp = heap->min;
    if (heap->min->right == heap->min)
    {
        heap->min = NULL;
    }
    else
    {
        EVENT_NODE_remove(heap->min);
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
static void EVENT_QUEUE_link(struct EVENT_NODE * node, struct EVENT_NODE *root)
{
    // 注意！！！首先要将node从双向链表中移除
    EVENT_NODE_remove(node);

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
        EVENT_NODE_add(node, root->child);
    }
    node->parent = root;
    
    // 此外，root->degree也需要更新
    root->degree++;
    node->marked = 0; // 更新marked值（相当于以node为根节点的树重生了一次）
}

/*
 * 创建EVENT_QUEUE_consolidate所需空间
 */
static void EVENT_QUEUE_cons_make(struct EVENT_QUEUE * heap)
{
    // heap->cons = (struct EVENT_NODE **)malloc(sizeof(struct EVENT_NODE) * heap->maxDegree);
    // 错误！
    // 1、要考虑heap之前是否已经经过一次EVENT_QUEUE_extract_min
    // 如果已经经过了一次，则heap->cons所对应的内存空间不为空，需要重新分配内存
    // （如果之前分配的空间已经足够，则不需要再分配一次空间）
    // 应该使用reallloc函数（用于调整已分配的内存空间大小）
    // 2、由于此处cons是一个连续内存空间（数组）的指针的指针，sizeof的对象应该为struct EVENT_NODE*
    int old = heap->maxDegree;
    heap->maxDegree = LOG2(heap->keyNum) + 1;
    if(old >= heap->maxDegree)
    {
        return;
    }
    heap->cons = (struct EVENT_NODE **)realloc(heap->cons, sizeof(struct EVENT_NODE *) * (heap->maxDegree));
}

/*
 * 合并斐波那契堆的根链表中左右相同度数的树
 */
static void EVENT_QUEUE_consolidate(struct EVENT_QUEUE *heap)
{
    int i, d, D;
    struct EVENT_NODE *x, *y, *tmp;

    EVENT_QUEUE_cons_make(heap);
    D = heap->maxDegree + 1;
    // 因为要将heap中的根链表中的所有节点都移到heap->cons中，
    // 至少需要maxDegree+1个空间

    for (i = 0; i < D; i++)
    {
        heap->cons[i] = NULL;
    }

    while (heap->min != NULL)
    {
        x = EVENT_QUEUE_remove_min(heap);
        d = x->degree;

        while (heap->cons[d] != NULL)
        {
            y = heap->cons[d]; // 将之前放入cons[d]中的那棵树y和x合并
            // if(y->degree > x->degree) // 保证x的度更大
            // 错误！
            // 此处为保证最小堆的性质，才需要判断x->key和y->key的大小

            if (x->event_describe_table->EVENT_TIME > y->event_describe_table->EVENT_TIME)
            // 保证x的键值比y小
            {
                tmp = y;
                y = x;
                x = y;
            }
            EVENT_QUEUE_link(y, x);
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
                EVENT_NODE_add(heap->cons[i], heap->min);
                if(heap->min->event_describe_table->EVENT_TIME > heap->cons[i]->event_describe_table->EVENT_TIME)
                {
                    heap->min = heap->cons[i];
                }
            }
        }
    }
}


/*
 * 返回斐波那契堆的最小节点（保存在event_describe_table中）
 */
void EVENT_QUEUE_get_min(struct EVENT_QUEUE *heap, struct EVENT_DESCRIBE_TABLE *event_describe_table)
{
    if (heap==NULL || heap->min==NULL)
    {
        printf("不存在最小节点");
        return;
    }
    event_describe_table = heap->min->event_describe_table;
}


/*
 * 在最小堆root中查找键值为key的节点
 */
static struct EVENT_NODE* EVENT_NODE_search(struct EVENT_NODE *root, Type key)
{
    struct EVENT_NODE *t = root;    // 临时节点
    struct EVENT_NODE *p = NULL;    // 要查找的节点

    if (root==NULL)
        return root;

    do
    {
        if (t->event_describe_table->EVENT_TIME == key)
        {
            p = t;
            break;
        }
        else
        {
            if ((p = EVENT_NODE_search(t->child, key)) != NULL)
                break;
        }
        t = t->right;
    } while (t != root);

    return p;
}

/*
 * 在斐波那契堆heap中查找键值为key的节点
 */
static struct EVENT_NODE *EVENT_QUEUE_search(struct EVENT_QUEUE *heap, Type key)
{
    if (heap==NULL || heap->min==NULL)
        return NULL;

    return EVENT_NODE_search(heap->min, key);
}

/*
 * 在最小堆root中查找EVENT_ID_TYPE为key的节点
 */
static struct EVENT_NODE* EVENT_NODE_search_by_ID(struct EVENT_NODE *root, EVENT_ID_TYPE key)
{
    struct EVENT_NODE *t = root;    // 临时节点
    struct EVENT_NODE *p = NULL;    // 要查找的节点

    if (root==NULL)
        return root;

    do
    {
        if (t->event_describe_table->EVENT_ID == key)
        {
            p = t;
            break;
        }
        else
        {
            if ((p = EVENT_NODE_search_by_ID(t->child, key)) != NULL)
                break;
        }
        t = t->right;
    } while (t != root);

    return p;
}

/*
 * 在斐波那契堆heap中查找EVENT_ID_TYPE为key的节点
 */
static struct EVENT_NODE *EVENT_QUEUE_search_by_ID(struct EVENT_QUEUE *heap, EVENT_ID_TYPE key)
{
    if (heap==NULL || heap->min==NULL)
        return NULL;

    return EVENT_NODE_search_by_ID(heap->min, key);
}


/*
 * 删除结点node
 */
static void _EVENT_QUEUE_delete(struct EVENT_QUEUE *heap, struct EVENT_NODE *node)
{
    Type min = heap->min->event_describe_table->EVENT_TIME;
    EVENT_QUEUE_decrease(heap, node, min-1);
    _EVENT_QUEUE_extract_min(heap);
    free(node->event_describe_table); // 先销毁事件描述表
    free(node);
}

void EVENT_QUEUE_delete(struct EVENT_QUEUE *heap, Type key)
{
    struct EVENT_NODE *node;

    if (heap==NULL || heap->min==NULL)
        return ;

    node = EVENT_QUEUE_search(heap, key);
    if (node==NULL)
        return ;

    _EVENT_QUEUE_delete(heap, node);
}

/*
 * 销毁斐波那契堆
 */
static void EVENT_NODE_destroy(struct EVENT_NODE *node)
{

    struct EVENT_NODE *start = node;

    if(node == NULL)
        return;

    do {
        EVENT_NODE_destroy(node->child);
        // 销毁node，并将node指向下一个
        node = node->right;
        free(node->left);
    } while(node != start);
}

void EVENT_QUEUE_destroy(struct EVENT_QUEUE *heap)
{
    EVENT_NODE_destroy(heap->min);
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
static void _EVENT_QUEUE_print(struct EVENT_NODE *node, struct EVENT_NODE *prev, int direction)
{
    struct EVENT_NODE *start=node;

    if (node==NULL)
        return ;
    do
    {
        if (direction == 1)
            printf("%8d(%d) is %2d's child\n", node->event_describe_table->EVENT_TIME, node->degree, prev->event_describe_table->EVENT_TIME);
        else
            printf("%8d(%d) is %2d's next\n", node->event_describe_table->EVENT_TIME, node->degree, prev->event_describe_table->EVENT_TIME);

        if (node->child != NULL)
            _EVENT_QUEUE_print(node->child, node, 1);

        // 兄弟节点
        prev = node;
        node = node->right;
        direction = 2;
    } while(node != start);
}

void EVENT_QUEUE_print(struct EVENT_QUEUE *heap)
{
    int i=0;
    struct EVENT_NODE *p;

    if (heap==NULL || heap->min==NULL)
        return ;

    printf("== EVENT_QUEUE的详细信息: ==\n");
    p = heap->min;
    do {
        i++;
        printf("%2d. %4d(%d) is root\n", i, p->event_describe_table->EVENT_TIME, p->degree);

        _EVENT_QUEUE_print(p->child, p, 1);
        p = p->right;
    } while (p != heap->min);
    printf("\n");
}


// 插入一个EVENT
void Insert_Event(struct EVENT_QUEUE *event_queue, struct EVENT_DESCRIBE_TABLE *event_describe_table)
{
    // 判断两个事件队列的指针是否相同，
    // 若不同，选择第一个参数中的事件队列，
    // 同时将事件描述表中的事件队列改为第一个参数
    if(event_queue == NULL)
    {
        printf("该事件队列未初始化，请初始化事件队列");
        return;
    }
    if(event_describe_table == NULL)
    {
        printf("该事件描述表未初始化，请初始化事件描述表");
        return;
    }

    struct EVENT_QUEUE *curQueue = event_describe_table->CURRENT_EVENT_QUEUE;
    if( curQueue == NULL)
    {
        curQueue = event_queue;
    }
    else
    {
        
        if(curQueue != event_queue)
            curQueue = event_queue;
        
    }

    // 新建节点
    struct EVENT_NODE *node = EVENT_NODE_make_by_TABLE(event_describe_table);
    if(node == NULL)
    {
        printf("未能新建一个EVENT_NODE节点");
        return;
    }
    // 插入节点
    EVENT_QUEUE_insert_node(curQueue, node);
    // 整理队列
    EVENT_QUEUE_consolidate(curQueue);
}

// 根据事件ID（EVENT_ID）查找EVENT
void Search_Event(struct EVENT_QUEUE *event_queue, struct EVENT_DESCRIBE_TABLE *event_describe_table)
{
    // 判断两个事件队列的指针是否相同，
    // 若不同，选择第一个参数中的事件队列，
    // 同时将事件描述表中的事件队列改为第一个参数
    if(event_queue == NULL)
    {
        printf("该事件队列未初始化，请初始化事件队列");
        return;
    }
    if(event_describe_table == NULL)
    {
        printf("该事件描述表未初始化，请初始化事件描述表");
        return;
    }

    struct EVENT_QUEUE *curQueue = event_describe_table->CURRENT_EVENT_QUEUE;
    if( curQueue == NULL)
    {
        curQueue = event_queue;
    }
    else
    {
        
        if(curQueue != event_queue)
            curQueue = event_queue;
        
    }
    struct EVENT_NODE *tmp = EVENT_QUEUE_search_by_ID(curQueue, event_describe_table->EVENT_ID);
    if(tmp == NULL)
    {
        printf("未找到对应的节点");
        return;
    }
    else
    {
        event_describe_table = tmp->event_describe_table;
    }
    
}

// 根据事件ID（EVENT_ID）移除对应的EVENT
void Remove_Event(struct EVENT_QUEUE *event_queue, struct EVENT_DESCRIBE_TABLE *event_describe_table)
{
    // 判断两个事件队列的指针是否相同，
    // 若不同，选择第一个参数中的事件队列，
    // 同时将事件描述表中的事件队列改为第一个参数
    if(event_queue == NULL)
    {
        printf("该事件队列未初始化，请初始化事件队列");
        return;
    }
    if(event_describe_table == NULL)
    {
        printf("该事件描述表未初始化，请初始化事件描述表");
        return;
    }

    struct EVENT_QUEUE *curQueue = event_describe_table->CURRENT_EVENT_QUEUE;
    if( curQueue == NULL)
    {
        curQueue = event_queue;
    }
    else
    {
        
        if(curQueue != event_queue)
            curQueue = event_queue;
        
    }
    struct EVENT_NODE *tmp = EVENT_QUEUE_search_by_ID(curQueue, event_describe_table->EVENT_ID);
    if(tmp == NULL)
    {
        printf("未找到对应的节点");
        return;
    }
    else
    {
        Type key = tmp->event_describe_table->EVENT_TIME;
        EVENT_QUEUE_delete(curQueue, key);
    }
}

// 获得最小的Event
void getMin_Event(struct EVENT_QUEUE *event_queue, struct EVENT_DESCRIBE_TABLE *event_describe_table)
{
    // 判断两个事件队列的指针是否相同，
    // 若不同，选择第一个参数中的事件队列，
    // 同时将事件描述表中的事件队列改为第一个参数
    if(event_queue == NULL)
    {
        printf("该事件队列未初始化，请初始化事件队列");
        return;
    }
    
    if(event_describe_table == NULL)
    {
        printf("该事件描述表未初始化，请初始化事件描述表");
        return;
    }

    struct EVENT_QUEUE *curQueue = event_describe_table->CURRENT_EVENT_QUEUE;
    if( curQueue == NULL)
    {
        curQueue = event_queue;
    }
    else
    {
        
        if(curQueue != event_queue)
            curQueue = event_queue;
        
    }

    // 获取最小节点
    EVENT_QUEUE_get_min(curQueue, event_describe_table);
}