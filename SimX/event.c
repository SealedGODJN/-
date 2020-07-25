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

static struct EVENT_NODE *EVENT_QUEUE_search(struct EVENT_QUEUE *heap, Type key);

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
 * 新建键值为key的节点，并将其插入到斐波那契堆中
 */
void EVENT_QUEUE_insert_key(struct EVENT_QUEUE *heap, Type key)
{
    if (heap == NULL) // 未初始化heap，不能进行接下来插入节点的操作
    {
        return;
    }

    struct EVENT_NODE *node = EVENT_NODE_make(node->event_describe_table->EVENT_TIME);
    if (node == NULL)
    {
        return;
    }
    EVENT_QUEUE_insert_node(heap, node);
}

/*
 * 将双向链表b链接到双向链表a的后面
 *
 * 注意： 此处a和b都是双向链表
*/
static void EVENT_NODE_cat(struct EVENT_NODE *a, struct EVENT_NODE *b)
{
    struct EVENT_NODE *tmp;
    
    tmp = a->right;
    a->right = b->right;
    b->right->left = a;
    tmp->left = b;
    b->right = tmp;
}

/*
 * 将h1, h2合并成一个堆，并返回合并后的堆
 */
struct EVENT_QUEUE* EVENT_QUEUE_union(struct EVENT_QUEUE *h1, struct EVENT_QUEUE *h2)
{
    struct EVENT_NODE *tmp;
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
        // EVENT_NODE_cat(h1, h2);
        // 错误，应该是h1->min和h2->min
        EVENT_NODE_cat(h1->min, h2->min);
        // if (h1->min > h2->min)
        // 错误！应该是对应的key比较大小！
        if (h1->min->event_describe_table->EVENT_TIME > h2->min->event_describe_table->EVENT_TIME)
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
 * 移除最小节点，并返回移除的min节点
 */
struct EVENT_NODE* _EVENT_QUEUE_extract_min(struct EVENT_QUEUE *heap)
{
    struct EVENT_NODE *child = NULL;
    struct EVENT_NODE *min = heap->min;

    while (min->child != NULL)
    {
        child = min->child;
        child->parent = NULL;
        EVENT_NODE_remove(child);
        // min->child = child->right;
        // 错误：未考虑到每一层是一个双向链表
        // min可能只有一个孩子，此时尽管执行了EVENT_NODE_remove(child)
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
        EVENT_QUEUE_insert_node(heap, child);
    }

    EVENT_NODE_remove(min);
    
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
        // EVENT_QUEUE_consolidate里面需要用到heap->min，
        // 需要不断地从heap中找出min节点，并将其放入heap->cons中
        // 所以需要指定一个heap->min
        heap->min = min->right;
        EVENT_QUEUE_consolidate(heap);
    }

    heap->keyNum--;

    return min;
}

void EVENT_QUEUE_extract_min(struct EVENT_QUEUE *heap)
{
    if(heap == NULL)
        return;
    if(heap->min == NULL)
        return;
    struct EVENT_NODE *node = _EVENT_QUEUE_extract_min(heap); // node接收heap->min
    if(node != NULL)
    {
        free(node);
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
 * 修改度数
 */
static void renew_degree(struct EVENT_NODE *parent, int degree)
{
    parent->degree -= degree;
    if(parent->parent != NULL) {
        renew_degree(parent->parent, degree);
    }
}

/*
 * 将node从父节点parent的子链接中剥离出来，
 * 并使node成为"堆的根链表"中的一员。
 */
static void EVENT_QUEUE_cut(struct EVENT_QUEUE *heap, struct EVENT_NODE *node, struct EVENT_NODE *parent)
{
    if(node->right == node) // 此时parent只有node一个孩子
    {
        parent->child = NULL;
        // node->parent = NULL;
        // EVENT_NODE_add(node, heap->min);
        // 和else中相同的语句合并
    }
    else
    {
        parent->child = node->right;
        EVENT_NODE_remove(node);
        // node->parent = NULL;
        // EVENT_NODE_add(node, heap->min);
    }
    renew_degree(parent, 1);
    node->parent = NULL;
    // 注意！！！
    // 要更新node的marked值为0（重生）
    node->marked = 0;

    // 注意！！！
    // 函数EVENT_NODE_add是将node的left和right指针指向min和min->left中间
    // 但是不需要用到node->left和node->right的值

    EVENT_NODE_add(node, heap->min);
}

/*
 * 对节点node进行"级联剪切"
 *
 * 级联剪切：如果减小后的结点破坏了最小堆性质，
 *     则把它切下来(即从所在双向链表中删除，并将
 *     其插入到由最小树根节点形成的双向链表中)，
 *     然后再从"被切节点的父节点"到所在树根节点递归执行级联剪枝
 */
static void EVENT_QUEUE_cascading_cut(struct EVENT_QUEUE *heap, struct EVENT_NODE *node)
{
    if(node->parent == NULL) // 如果node是根节点，则不需要对其进行级联剪
        return;
    if (node->marked == 0)
    {
        node->marked = 1;
        return;
    }
    else
    {
        EVENT_QUEUE_cut(heap, node, node->parent);
        EVENT_QUEUE_cascading_cut(heap, node->parent);
    }
    
    
}

/*
 * 将斐波那契堆heap中节点node的值减少为key
 */
static void EVENT_QUEUE_decrease(struct EVENT_QUEUE *heap, struct EVENT_NODE *node, Type key)
{
    struct EVENT_NODE *parent;
    if(heap == NULL || node == NULL || heap->min == NULL)
        return;
    
    if(key >= node->event_describe_table->EVENT_TIME)
    {
        printf("decrease failed: the new key(%d) is no smaller than current key(%d)\n", key, node->event_describe_table->EVENT_TIME);
        return ;
    }

    node->event_describe_table->EVENT_TIME = key;
    parent = node->parent;
    // if(parent != NULL)
    // 注意：只有当node的key减小后，破坏了最小堆的性质（子节点的key都比父母的key小）
    // 即node->event_describe_table->EVENT_TIME < parent->key
    // 才需要进行如下操作
    if(parent != NULL && node->event_describe_table->EVENT_TIME < parent->event_describe_table->EVENT_TIME)
    {
        EVENT_QUEUE_cut(heap, node, parent);
        EVENT_QUEUE_cascading_cut(heap, parent);
    }
    if(node->event_describe_table->EVENT_TIME < heap->min->event_describe_table->EVENT_TIME)
        heap->min = node;
}

/*
 * 将斐波那契堆heap中节点node的值增加为key
 */
static void EVENT_QUEUE_increase(struct EVENT_QUEUE *heap, struct EVENT_NODE *node, Type key)
{
    // 增加node的key之后，node的key和孩子的key有多种大小关系
    // 此时，只要把node的孩子都挂到根链表上，再把被增加的节点挂到根链表上，
    // 就能保持最小堆的性质
    // 最后，级联剪！！！
    
    struct EVENT_NODE *parent, *child, *right;
    if(heap == NULL || node == NULL || heap->min == NULL)
        return;
    
    if(key <= node->event_describe_table->EVENT_TIME)
    {
        printf("increase failed: the new key(%d) is no bigger than current key(%d)\n", key, node->event_describe_table->EVENT_TIME);
        return ;
    }

    node->event_describe_table->EVENT_TIME = key;
    parent = node->parent;

    while (node->child != NULL)
    {
        child = node->child;
        EVENT_NODE_remove(child);
        EVENT_NODE_add(child, heap->min);
        // node->degree--;
        // 在循环体之外，修改node->degree=0即可

        // child = child->right;
        // 错误：
        // 1、注意while的循环条件为node->child != NULL
        // 则此处应该去改变node->child的指向
        // 2、要考虑node只有一个child的情况
        if(child->right == child)
            node->child = NULL;
        else
        {
            node->child = child->right;
        }
        
        // 还应该修改child的parent（转移到根链表上了）
        child->parent = NULL;
    }
    node->degree = 0;
    // node->marked = 1;
    // 注意！！！key被修改的node不需要改变其marked值，只有node的父母才需要修改marked

    if(parent != NULL && node->event_describe_table->EVENT_TIME < parent->event_describe_table->EVENT_TIME) // 如果没有破坏最小堆的性质，就不需要进行级联剪？
    {
        EVENT_QUEUE_cut(heap, node, parent);
        EVENT_QUEUE_cascading_cut(heap, parent);
    }
    else if (heap->min == node)
    {
        // 由于此时node为最小的节点
        // 增大node的值，需要更新整个heap的min值
        right = node->right;
        while(right != node)
        {
            if(node->event_describe_table->EVENT_TIME > right->event_describe_table->EVENT_TIME)
                heap->min = right;
            right = right->right;
        }
    }
    
    
}

/*
 * 更新二项堆heap的节点node的键值为key
 */
void _EVENT_QUEUE_update(struct EVENT_QUEUE *heap, struct EVENT_NODE *node, Type key)
{
    if(key < node->event_describe_table->EVENT_TIME)
        EVENT_QUEUE_decrease(heap, node, key);
    else if(key > node->event_describe_table->EVENT_TIME)
        EVENT_QUEUE_increase(heap, node, key);
    else
        printf("No need to update\n");
}

void EVENT_QUEUE_update(struct EVENT_QUEUE *heap, Type oldkey, Type newkey)
{
    struct EVENT_NODE *node;

    if (heap==NULL)
        return ;

    node = EVENT_QUEUE_search(heap, oldkey);
    if (node!=NULL)
        _EVENT_QUEUE_update(heap, node, newkey);
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
 * 在斐波那契堆heap中是否存在键值为key的节点。
 * 存在返回1，否则返回0。
 */
bool EVENT_QUEUE_contains(struct EVENT_QUEUE *heap, Type key)
{
    return EVENT_QUEUE_search(heap,key)!=NULL ? True: False;
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