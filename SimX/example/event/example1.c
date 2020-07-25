#include <event.h>
#include <stdlib.h>
struct example$event$example1_vartable{
    int a;
    int b;
};

void example$event$example1_init(struct EVENT_DESCRIBE_TABLE *event_describe_table){
    //初始化前置事件队列(链表实现)
    event_describe_table->PRE_EVENT_LIST = NULL;

    //初始化入口点
    event_describe_table->EVENT_ENTRY_POINT = 1;

    //初始化变量
    event_describe_table->VARIABLE_TABLE = (struct example$event$example1_vartable *)malloc(sizeof(struct example$event$example1_vartable));
    struct example$event$example1_vartable *vartable = event_describe_table->VARIABLE_TABLE;
    vartable->a = 0;
    vartable->b = 0;

    //初始化事件状态
    event_describe_table->EVENT_STATUS = WAITING;

    //初始化事件暂停标记
    event_describe_table->SUSPEND_FLAG = False;
};

EVENT_HANDLER_ENTRY_TYPE example$event$example1(struct EVENT_DESCRIBE_TABLE *event_describe_table){
    //获取变量表
    struct example$event$example1_vartable *vartable = event_describe_table->VARIABLE_TABLE;
    //跳转模块，恢复上次运行的位置
    switch (event_describe_table->EVENT_ENTRY_POINT)
    {
    case 1:
        goto t1;
    case 2:
        goto t2;
    case 3:
        goto t3;
    default:
        Remove_Event(event_describe_table->CURRENT_EVENT_QUEUE, event_describe_table);
        goto end;
    }

    t1:
    //a=1
    vartable->a = 1;
    //b=1
    vartable->b = 1;

    if(event_describe_table->SUSPEND_FLAG == True){
        event_describe_table->EVENT_STATUS = 2;
        event_describe_table->SUSPEND_FLAG = False;
        goto end;
    }

    t2:
    /*
    * call event e1(a) at time1
    */ 
    //创建事件描述表
    struct EVENT_DESCRIBE_TABLE *t2$e1$1 = (struct EVENT_DESCRIBE_TABLE*)malloc(sizeof(struct EVENT_DESCRIBE_TABLE));
    //初始化ID
    t2$e1$1->EVENT_ID = New_ID();
    //初始化事件时间
    t2$e1$1->EVENT_TIME = 0;//=time1, 此处略写
    //初始化事件入口点
    t2$e1$1->EVENT_ENTRY_POINT = NULL;//=example$event$e1, 此处略写
    //初始化当前事件所在的事件队列
    t2$e1$1->CURRENT_EVENT_QUEUE = event_describe_table->CURRENT_EVENT_QUEUE;
    //初始化父事件
    t2$e1$1->ROOT_EVENT = event_describe_table;
    //将该事件添加到前置事件链表
    struct PRE_EVENT_LIST *t2$e2$new_pre_event_list = (struct PRE_EVENT_LIST*)malloc(sizeof(struct PRE_EVENT_LIST));
    t2$e2$new_pre_event_list->next = event_describe_table->PRE_EVENT_LIST;
    t2$e2$new_pre_event_list->event = t2$e1$1;
    event_describe_table->PRE_EVENT_LIST = t2$e2$new_pre_event_list; //调用事件初始化函数
    //...
    //将当前事件插入到事件队列
    Insert_Event(t2$e1$1->CURRENT_EVENT_QUEUE, t2$e1$1);

    Suspend_Event(event_describe_table->CURRENT_EVENT_QUEUE, event_describe_table);
    event_describe_table->EVENT_ENTRY_POINT = 3;
    goto end;

    t3:
    //略 
    
    end:
};
