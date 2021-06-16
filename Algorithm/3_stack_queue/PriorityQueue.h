#ifndef _P_QUEUE_H_
#define _P_QUEUE_H_

typedef char datatype;

typedef struct queue
{
    datatype data;
    int high;
    struct queue *pNext;
}Queue,*PQueue;


#endif