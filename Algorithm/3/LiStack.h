#ifndef _LINK_STACK_H_
#define _LINK_STACK_H_

typedef int ElemType;

typedef struct Linknode{
    ElemType data;
    struct Linknode *next;
} LiNode, *LiStack;

#endif