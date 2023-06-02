package com.NPU.java内存模型.可见性;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description 不适用于volatile的场景
 */
public class NoVolatile implements Runnable{

    volatile int a;

    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new NoVolatile();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(((NoVolatile) r).a);
        System.out.println(((NoVolatile) r).realA.get());
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            a++;
            realA.incrementAndGet();
        }
    }
}
