package com.dataStructure.真题.考研2018.第4题;

public class Node {
    public double conf; // 多项式的系数（可以是小数）
    public int exp; // 多项式的幂数（一般是整数）
    public Node next; // 多项式的下一项

    /**
     * @param conf
     * @param exp
     * @param rear result结果的尾指针
     * @return
     */
    public Node attach(double conf, int exp, Node rear) {
        Node p = new Node(); // 结果中的新节点
        p.conf = conf;
        p.exp = exp;
        rear.next = p;
        rear = p;
        p.next = null;
        return rear;
    }

    /**
     * ah + bh
     *
     * @param ah
     * @param bh
     * @return
     */
    public Node add(Node ah, Node bh) {
        Node ai = ah;
        Node bi = bh;
        Node result = new Node(); // 新建结果
        Node c = result; // c是尾指针
        while (ai != null && bi != null) {
            if (ai.exp > bi.exp) {
                c = attach(ai.conf, ai.exp, c);
                ai = ai.next;
            } else if (ai.exp == bi.exp) {
                double sum = ai.conf + bi.conf;
                if (sum != 0) {
                    c = attach(sum, ai.exp, c);
                    ai = ai.next;
                    bi = bi.next;
                }
            } else {
//                if (pa.exp < pb.exp)
                c = attach(bi.conf, bi.exp, c);
                bi = bi.next;
            }
        }
        // ai处理完了，但是bi还没处理完
        while (bi != null) {
            c = attach(bi.conf, bi.exp, c);
            bi = bi.next;
        }
        // bi处理完了，但是ai还没处理完
        while (ai != null) {
            c = attach(ai.conf, ai.exp, c);
            ai = ai.next;
        }

        c.next = null;
        return result;
    }
}
