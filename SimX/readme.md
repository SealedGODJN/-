# event.h和event.c结构体及函数实现说明



## 1 event.h中预定义的结构体

### 1.1 定义事件队列的节点

```c
/**
 * 事件队列的节点
 */
struct EVENT_NODE
{
    struct EVENT_DESCRIBE_TABLE *event_describe_table; //事件描述表
    int degree;                        // 度数
    struct EVENT_NODE *left;    // 左兄弟
    struct EVENT_NODE *right;    // 右兄弟
    struct EVENT_NODE *child;    // 第一个孩子节点
    struct EVENT_NODE *parent;    // 父节点
    int marked;                       //是否被删除第1个孩子(1表示删除，0表示未删除)
};
```



### 1.2 重定义SIM_TIME_TYPE为Type

```c
// 定义Type为事件发生时间
typedef SIM_TIME_TYPE Type;
```



### 1.3 定义事件队列

```c
/**
 * 事件队列
 * 
 * */
struct EVENT_QUEUE{
    int   keyNum;                    // 堆中节点的总数
    int   maxDegree;                // 最大度
    struct EVENT_NODE *min;        // 最小节点(某个最小堆的根节点)
    struct EVENT_NODE **cons;    // 最大度的内存区域
};
```



### 1.4 声明事件队列的相关函数

```c
// 创建Fibonacci堆
struct EVENT_QUEUE* EVENT_QUEUE_make();
// 新建键值为key的节点，并将其插入到斐波那契堆中
void EVENT_QUEUE_insert_key(struct EVENT_QUEUE *heap, Type key);
// 删除键值为key的结点
void EVENT_QUEUE_delete(struct EVENT_QUEUE *heap, Type key);
// 移除最小节点
void EVENT_QUEUE_extract_min(struct EVENT_QUEUE *heap);
// 更新heap的中的oldkey为newkey
void EVENT_QUEUE_update(struct EVENT_QUEUE *heap, Type oldkey, Type newkey);
// 将h1, h2合并成一个堆，并返回合并后的堆
struct EVENT_QUEUE* EVENT_QUEUE_union(struct EVENT_QUEUE *h1, struct EVENT_QUEUE *h2);
// 返回斐波那契堆的最小节点（保存在event_describe_table中）
void EVENT_QUEUE_get_min(struct EVENT_QUEUE *heap, struct EVENT_DESCRIBE_TABLE *event_describe_table);
// 销毁斐波那契堆
void EVENT_QUEUE_destroy(struct EVENT_QUEUE *heap);
// 打印"斐波那契堆"
void EVENT_QUEUE_print(struct EVENT_QUEUE *heap);

```



## 2 event.c实现的函数说明

### 2.1 预定义的LOG2函数

```c
#if 0
#define LOG2(x) ({ \
        unsigned int _i = 0; \
        __asm__("bsr %1, %0" : "=r" (_i) : "r" ((x))); \
        _i; })
#else   // 注意：通过gcc编译时，要添加 -lm 选项。
#define LOG2(x) ((log((double)(x))) / (log(2.0)))
#endif
```



2.2 函数声明