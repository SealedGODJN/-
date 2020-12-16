#include <stdio.h>
#include <stdlib.h>
#include "LinkList.h"

// 删除不带头结点的单链表L中所有值为x的节点（非递归）
void Del_X_1(Linklist &L, ElemType x)
{
    LNode *pre, *p,*q; // 要多创建一个节点，用于指向被删除的节点
    pre = L;
    p = L;
    while(p != NULL) {
        if(p->data == x)
        {
            if(p==L)
            {
                q = p;
                pre->next = p->next;
                free(q);
                p = pre->next;
                L = p; // 多一步，修改L的指向
            }
            else
            {
                q = p;
                pre->next = p->next;
                free(q);
                p = pre->next;
            }
            
        }
        else
        {
            pre = pre->next; // 或者这么写：pre=p;
            p = p->next;
        }
        
    }
}

void Del_X_2(Linklist &L, ElemType x)
{
    //L为不带头的单链表，本算法删除L中所有值为x的结点
    LNode *p=L, *r, *q, *temp_L; // *temp_L用于保存后插法建立的链表
    temp_L = (LNode *)malloc(sizeof(LNode));
    r = temp_L;
    while(p!=NULL){
        if (p->data!=x) {  //*p结点值不为x时将其链接到L尾部
            r->next=p;
            r=p;
            p=p->next;  //继续扫描
        }else{  //*p结点值为x时将其释放
            q=p;
            p=p->next;  //继续扫描
            free(q);  //释放空间
        }
    }//while
    r->next=NULL;  //插入结束后置尾结点指针为NULL
    L = temp_L->next;
}

// 删除不带头结点的单链表L中所有值为x的节点（递归）
void Del_X_3(Linklist &L, ElemType x)
{
    LNode *p;
    if(L == NULL)
        return;
    if(L->data == x)
    {
        p = L;
        L = L->next;
        free(p);
        Del_X_3(L, x);
    }
    else
    {
        Del_X_3(L->next, x);
    }
    
}

// 删除带头结点的单链表L中所有值为x的节点（递归）
void Del_X_4(Linklist &L, ElemType x)
{
    LNode *p;
    if(L->next == NULL)
        return;
    if(L->next->data == NULL)
    {
        p = L->next;
        L->next = L->next->next;
        free(p);
        Del_X_4(L, x);
    }
    else
    {
        Del_X_4(L->next, x);
    }
    
}

// 从尾到头反向输出带头节点的链表的每个节点的值(头插法)
void Reverse_Print_1(Linklist &L, ElemType x)
{
    Linklist new_L = (Linklist)malloc(sizeof(LNode));
    LNode *s, *p = L;
    // *q;
    int x;
    new_L->next = NULL;
    while(p->next != NULL) {
        // s = (LNode *)malloc(sizeof(LNode));
        // s->data = p->next->data;
        s = p->next;
        // q = p->next->next;
        // s->next = new_L->next;
        new_L->next = s;
        p = p->next;
    }
    s->next = NULL;
    L = new_L;
}

// 从尾到头反向输出带头节点的链表的每个节点的值(递归)
void Reverse_Print_2(Linklist &L)
{
    if(L->next == NULL)
        return;
    Reverse_Print_2(L->next);
    printf("%d", L->next->data);
}

// 在带头节点的单链表中删除一个最小值节点（最小值节点唯一）
void Del_Min(Linklist &L)
{
    if(L->next == NULL)
        return;
    LNode *pre, *min, *p;
    pre = L;
    min = L->next;
    p = L->next;
    while(p->next != NULL) {
        if(min->data > p->next->data)
        {
            pre = p;
            min = p->next;
        }
        p = p->next;
    }
    pre->next = min->next;
    free(min);
}

// 将带头结点的单链表就地逆置（就地——辅助空间复杂度O(1)）
void Reverse_Linklist(Linklist &L) {
    LNode *p, *q, *head, *tail;
    p = L;
    q = L->next;
    tail = L->next;
    head = p;
    while(q != NULL) {
        p->next = q->next;
        q->next = head;
        head = q;
        q = p->next;
    }
    tail->next = NULL;
    p->next = head;
    L = p;
}

int main() {
    
}