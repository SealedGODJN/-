#ifndef _BINOMIAL_HEAP_H_
#define _BIONMIAL_HEAP_H_

typedef int Type;

typedef struct _BinomialNode{
    Type key;                       // 关键字（键值）:用于比较节点大学
    int degree;                     // 节点的度数：用来表示当前节点的度数
    /*
        根节点的度数=C(1,k)，即代表该二项树的k
        在合并“根节点度数相同的二项树”时，
        另一位博主的做法为：专门维护一个变量k（保存树的k值）
        而这位博主为树的每个节点都记录其度数
        （个人感觉比较浪费，除了根节点有用到度数，其他节点没有用到度数？）
    */
    struct _BinomialNode *child;    // 左孩子
    struct _BinomialNode *parent;   // 父节点
    struct _BinomialNode *next;     // 兄弟
} BinomialNode, *BinomialHeap;

// 新建key对应的节点，并将其插入到二项堆中
BinomialNode *binomial_insert(BinomialHeap heap, Type key);
// 删除节点：删除键值为key的节点，并返回删除节点后的二项树
BinomialNode *binomial_delete(BinomialHeap heap, Type key);
// 将二项堆heap的键值oldkey更新为newkey
void binomial_update(BinomialHeap heap, Type oldkey, Type newkey);

// 合并二项堆：将h1, h2合并成一个堆，并返回合并后的堆
BinomialNode *binomial_union(BinomialHeap h1, BinomialHeap h2);

// 查找：在二项堆中查找键值为key的节点
BinomialNode *binomial_search(BinomialHeap heap, Type key);
// 获取二项堆中的最小节点
BinomialNode *binomial_minimum(BinomialHeap heap);
// 移除最小节点，并返回移除节点后的二项堆
BinomialNode *binomial_extract_minimum(BinomialHeap heap);

// 打印“二项堆”
void binomial_print(BinomialHeap heap);

#endif
