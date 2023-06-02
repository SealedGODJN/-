package com.NPU.java内存模型.可见性;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description volatile不适用的情况2
 */
public class NoVolatile2 implements Runnable{

    volatile boolean done = false;

    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new NoVolatile();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(((NoVolatile2) r).done);
        System.out.println(((NoVolatile2) r).realA.get());
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            flipDone();
            realA.incrementAndGet();
        }
    }

    private void flipDone() {
        done = !done;
    }
}
