#include <unordered_map>
#include <iostream>

using namespace std;

class LRUCache {
    typedef struct doubleLink {
        int key;
        int value;
        struct doubleLink *next;
        struct doubleLink *last;
        doubleLink(): key(-1), value(-1), next(nullptr), last(nullptr) {}
        doubleLink(int key, int value, struct doubleLink *next, struct doubleLink *last):
                key(key), value(value), next(next), last(last) {}
    }DLink;

    unordered_map<int, DLink*> cache;
    DLink *head, *tail;
    int maxSize;

    void moveTo(DLink *p) {
        // 1、先把p从原来的位置上拿下来
        p->next->last = p->last;
        p->last->next = p->next;
        p->next = tail;
        // 2、再插入到tail前面
        tail->last->next = p;
        p->last = tail->last;
        tail->last = p;
    }

    void addTo(int key, int value) {
        tail->last->next = new DLink(key, value, tail, tail->last);
        tail->last = tail->last->next;
    }

public:
    LRUCache(int capacity):head(new DLink), tail(new DLink), maxSize(capacity) {
        head->next = tail;
        tail->last = head;
    }
    
    int get(int key) {
        if (cache.count(key) != 1) {
            return -1;
        } else {
            DLink *p = cache[key];
            moveTo(p);
            return p->value;
        }
    }
    
    void put(int key, int value) {
        if (cache.count(key) != 1) {
            // 不存在
//            if (maxSize == 0) {
            if (maxSize == cache.size()) {
                // 关键字超过capcity
                // 删掉最久未使用的key
                cache.erase(head->next->key);
               // 插入到最久未使用的节点位置
               cache.emplace(key, head->next);
               cache[key]->key = key;
               cache[key]->value = value;
               // 移动到最近未使用？
               moveTo(cache[key]);

               // 没有给出删除的方法，所以执行以下两行代码，原节点仍然在LRUCache中
                // addTo(key, value);
                // cache.emplace(key, tail->last);
            } else {
                addTo(key, value);
                // 替换之前的key-value键值对
                cache.emplace(key, tail->last);
            }
        } else {
            // 存在
            DLink *p = cache[key];
            p->value = value;
            if (tail->last !=cache[key]) moveTo(p);
        }
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */

int main() {
    LRUCache lRUCache = *new LRUCache(2);
    lRUCache.put(1, 1); // 缓存是 {1=1}
    lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
    lRUCache.get(1);    // 返回 1
    lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
    lRUCache.get(2);    // 返回 -1 (未找到)
    lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
    cout << lRUCache.get(1) << endl;    // 返回 -1 (未找到)
    cout << lRUCache.get(3) << endl;    // 返回 3
    cout << lRUCache.get(4) << endl;    // 返回 4
    cin.get();
}