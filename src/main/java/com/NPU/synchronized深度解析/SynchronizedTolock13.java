package com.NPU.synchronized深度解析;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description 用lock的形式表现
 */
public class SynchronizedTolock13 {
    //    private final ReentrantLock lock = new ReentrantLock();
    Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        SynchronizedTolock13 s = new SynchronizedTolock13();
        s.method1();
        s.method2();
    }

    private synchronized void method1() {
        System.out.println("我是synchronized");
    }

    private void method2() {
        lock.lock();
        try {
            System.out.println("我是lock的形式");
        } finally {
            lock.unlock();
        }
    }
}
