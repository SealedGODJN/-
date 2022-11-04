package com.dataStructure.真题.考研2020;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class 最大值优先级队列_第5题 {


    public static void main(String[] args) {
//        /**
//         * java自带的优先级队列
//         */
//        Queue<Integer> priorityQueue = new PriorityQueue<>(10, new Comparator<Integer>() {
//            /**
//             *     private void siftUpUsingComparator(int k, E x) {
//             *         while (k > 0) {
//             *             int parent = (k - 1) >>> 1;
//             *             Object e = queue[parent];
//             *             if (comparator.compare(x, (E) e) >= 0)
//             *                 break;
//             *             queue[k] = e;
//             *             k = parent;
//             *         }
//             *         queue[k] = x;
//             *     }
//             * @param o1 第一个比较数
//             * @param o2 第二个比较数
//             * @return
//             */
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                if (o1 > o2) {
//                    return -1;
//                } else if (o1.equals(o2)) {
//                    return 0;
//                } else {
//                    return 1;
//                }
//            }
//        });
//        priorityQueue.add(5);
//        priorityQueue.add(1);
//        priorityQueue.add(2);
//        priorityQueue.add(-5);
//
//        priorityQueue.remove();
//
//        priorityQueue.forEach(System.out::println);

        PQueue phead = new PQueue(1, 5);
        phead = PQueue.add(phead, -1, 6);
        phead = PQueue.add(phead, 2, 8);

        System.out.println("打印：");
        PQueue.show(phead);

        phead = PQueue.remove(phead);

        System.out.println("打印：");
        PQueue.show(phead);
    }
}

class PQueue {
    Integer data;
    int priority; // 优先级
    PQueue next; // 下一个队列节点

    /**
     * 默认优先级为0
     *
     * @param data
     */
    public PQueue(Integer data) {
        this(data, 0, null);
    }

    public PQueue(Integer data, int priority) {
        this(data, priority, null);
    }

    public PQueue(Integer data, int priority, PQueue next) {
        this.data = data;
        this.priority = priority;
        this.next = next;
    }

    /**
     * 添加一个节点，返回新的队列头节点
     *
     * @param data
     * @param high
     */
    public static PQueue add(PQueue phead, Integer data, int high) {
        PQueue node = new PQueue(data, high);
        if (phead.priority <= high) {
            node.next = phead;
            phead = node;
            return phead;
        } else {
            PQueue ptemp = phead;
            while (ptemp.next != null) {
                if (ptemp.next.priority <= high) {
                    node.next = ptemp.next;
                    ptemp.next = node;
                    return phead;
                }
                ptemp = ptemp.next;
            }
        }
        return phead;
    }

    /**
     * 删除队头的节点<br>
     *
     * @return 返回删除的节点的值
     */
    public static PQueue remove(PQueue phead) {
        if (phead == null) {
            return null;
        } else {
            Integer result = phead.data;
            System.out.println("删除结果为：" + result);

            phead = phead.next;
            return phead;
        }
    }

    public static void show(PQueue phead) {
        PQueue ptemp = phead;
        while (ptemp != null) {
            System.out.print(ptemp.data + " ");
            ptemp = ptemp.next;
        }
        System.out.println();
    }
}