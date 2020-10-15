#ifndef _LINK_LIST_H_
#define _LINK_LIST_H_

typedef int ElemType;

typedef struct LNode{
    ElemType data;
    struct LNode *next;
} LNode, *Linklist;

#endif