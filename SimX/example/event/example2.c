#include <event.h>
void example$event$example2(struct EVENT_DESCRIBE_TABLE *event_describe_table){

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
    SIM_TIME_TYPE f$time = GET_NOW_TIME();
    //work1
    struct EVENT_DESCRIBE_TABLE *t1;
    //获取回调激活事件
    Get_Recall_Event(event_describe_table, f$time + 10, t1);
    //将回调事件添加到前置事件链表
    Add_Pre_Event(event_describe_table, t1);
    //将回调事件添加到事件队列
    Insert_Event(t1->CURRENT_EVENT_QUEUE, t1);
    //悬挂当前事件
    Suspend_Event(event_describe_table->CURRENT_EVENT_QUEUE, event_describe_table);
    
    t2:

    t3:

    end:

}