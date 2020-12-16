#include <stdio.h>
#include <stdlib.h>
#include "LiStack.h"

// 带头结点的链栈

// 初始化
void InitStack(LiStack &S)
{
    S = (LiStack)malloc(sizeof(LiStack));
    S->next = NULL;
}

// 判栈空
bool StackEmpty(LiStack S)
{
    if(S->next == NULL)
        return true;
    else
        return false;
}

bool Push(LiStack &S, ElemType x)
{
    LiNode *p, *s = (LiNode *)malloc(sizeof(LiNode));
    if(s == NULL)
        return false; // 无法动态分配空间
    s->data = x;
    s->next = NULL;
    p = S->next;
    S->next = s;
    s->next = p;
    return true;
}

bool Pop(LiStack &S, ElemType &x)
{
    LiNode *p;
    p = S->next;
    if(p==NULL)
        return false; // 栈为空，无法pop
    x = p->data;
    S->next = p->next;
    free(p);
    return true;
}

bool GetTop(LiStack &S, ElemType &x)
{
    LiNode *p;
    p = S->next;
    if(p==NULL)
        return false; // 栈为空，无法pop
    x = p->data;
    return true;
}

// 不带头节点的链栈
void InitStack1(LiStack &S)
{
    S = NULL;
}

bool StackEmpty1(LiStack S)
{
    if(S == NULL)
        return true;
    else
        return false;
}

bool Push1(LiStack &S,ElemType x)
{
    LiNode *p, *s = (LiNode *)malloc(sizeof(LiNode));
    if(s == NULL)
        return false;
    s->data = x;
    s->next = NULL;
    p = S->next; // 记录链栈的下一个元素
    S = s;
    s->next = p;
}

bool Pop1(LiStack &S, ElemType &x)
{
    if(S == NULL)
        return false; // 栈为空
    LiNode *p, *q;
    p = S;
    x = p->data;
    q = S->next;
    S = q;
    free(p);
    return true;
}

bool GetTop1(LiStack &S, ElemType &x)
{
    if(S == NULL)
        return false; // 栈为空
    x = S->data;
    return true;
}