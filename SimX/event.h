#include <simx.h>

#ifndef __SIM_EVENT__
#define __SIM_EVENT__
//宏定义
#define bool int
#define True 1
#define False 0

//预定义
enum EVENT_STATUS_TYPE;
struct EVENT_DESCRIBE_TABLE;
struct EVENT_QUEUE;

/**
 * 事件描述表
 * 
 * */
//仿真时间类型
typedef long SIM_TIME_TYPE;
//事件ID类型
typedef long EVENT_ID_TYPE;
//事件入口点类型 
typedef int EVENT_ENTRY_POINT_TYPE;
//变量表指针类型
typedef void* VARIABLE_TABLE_TYPE;
//前置事件链表
struct PRE_EVENT_LIST{
    struct EVENT_DESCRIBE_TABLE *event;
    struct PRE_EVENT_LIST       *next;
};
//事件暂停标记类型
typedef bool SUSPEND_FLAG_TYPE;
//事件处理函数类型
typedef void (*EVENT_HANDLER_ENTRY_TYPE)(struct EVENT_DESCRIBE_TABLE*);
//事件状态类型
enum EVENT_STATUS_TYPE{
    ENDING,
    RUNNING,
    SUSPENDING,
    WAITING
};
// 事件描述表
struct EVENT_DESCRIBE_TABLE{
    // 事件ID
    EVENT_ID_TYPE               EVENT_ID;
    // 事件发生时间
    SIM_TIME_TYPE               EVENT_TIME;
    // 事件处理函数
    EVENT_HANDLER_ENTRY_TYPE    EVENT_HANDLER_ENTRY;
    // 当前事件所处的事件队列
    struct EVENT_QUEUE          *CURRENT_EVENT_QUEUE;
    // 事件入口点
    EVENT_ENTRY_POINT_TYPE      EVENT_ENTRY_POINT;
    // 变量表指针
    VARIABLE_TABLE_TYPE         VARIABLE_TABLE;
    // 前置事件链表
    struct PRE_EVENT_LIST       *PRE_EVENT_LIST;
    // 父事件指针
    struct EVENT_DESCRIBE_TABLE *ROOT_EVENT;
    // 事件状态
    enum EVENT_STATUS_TYPE      EVENT_STATUS;
    // 事件暂停标记
    SUSPEND_FLAG_TYPE           SUSPEND_FLAG;
};

//获取一个ID号
EVENT_ID_TYPE New_ID();
//获取当前仿真时间
SIM_TIME_TYPE Get_Now_Time();
//获取一个回调事件的事件描述表
//完成回调事件的全部初始化操作
// in: root_event <根事件的事件描述表>
// in: time <事件发生时间>
// out: recall_event <回调事件的事件描述表>
void Get_Recall_Event(struct EVENT_DESCRIBE_TABLE *root_event, 
                      SIM_TIME_TYPE time, 
                      struct EVENT_DESCRIBE_TABLE *recall_event);
//添加前置事件
void Add_Pre_Event(struct EVENT_DESCRIBE_TABLE *root_event,
                   struct EVENT_DESCRIBE_TABLE *pre_event);

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

// 定义Type为事件发生时间
typedef SIM_TIME_TYPE Type;

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

// 创建EVENT_QUEUEonacci堆
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
// 在斐波那契堆heap中是否存在键值为key的节点；存在返回1，否则返回0。
bool EVENT_QUEUE_contains(struct EVENT_QUEUE *heap, Type key);
// 获取最小节点，返回最小节点
void EVENT_QUEUE_get_min(struct EVENT_QUEUE *heap, struct EVENT_DESCRIBE_TABLE *event_describe_table);
// 销毁斐波那契堆
void EVENT_QUEUE_destroy(struct EVENT_QUEUE *heap);
// 打印"斐波那契堆"
void EVENT_QUEUE_print(struct EVENT_QUEUE *heap);


// 判断两个事件队列是否相同，
// 若不同，选择第一个参数中的事件队列，
// 同时将事件描述表中的事件队列改为第一个参数
void Insert_Event(struct EVENT_QUEUE *event_queue, struct EVENT_DESCRIBE_TABLE *event_describe_table);
void Search_Event(struct EVENT_QUEUE *event_queue, struct EVENT_DESCRIBE_TABLE *event_describe_table);
void Remove_Event(struct EVENT_QUEUE *event_queue, struct EVENT_DESCRIBE_TABLE *event_describe_table);
void getMin_Event(struct EVENT_QUEUE *event_queue, struct EVENT_DESCRIBE_TABLE *event_describe_table);
// 悬挂一个事件
// RUNNING -> SUSPENDING
void Suspend_Event(struct EVENT_QUEUE *event_queue, struct EVENT_DESCRIBE_TABLE *event_describe_table);
// 激活一个事件
// SUSPENDING -> WAITING
void Activate_Event(struct EVENT_QUEUE *event_queue, struct EVENT_DESCRIBE_TABLE *event_describe_table);
// 执行一个事件
// WAITING -> RUNNING
void Execute_Event(struct EVENT_QUEUE *event_queue, struct EVENT_DESCRIBE_TABLE *event_describe_table);
// 释放一个事件 
// ENDING -> remove
// WAITING -> remove
// SUSPENDING -> remove
// 1.在父事件的前置事件队列中删除该事件
// 2.在事件队列中删除该事件
// 3.释放描述表在堆中创建的空间
// 4.确保子事件释放完毕(前置事件链表为空)
void Release_Event(struct EVENT_QUEUE *event_queue, struct EVENT_DESCRIBE_TABLE *event_describe_table);
#endif