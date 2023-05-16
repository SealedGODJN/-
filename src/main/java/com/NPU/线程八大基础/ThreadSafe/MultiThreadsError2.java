package com.NPU.线程八大基础.ThreadSafe;

/**
 * @description 两个线程死锁
 */
public class MultiThreadsError2 implements Runnable {
    int flag = 1;
    static Object o1 = new Object();
    static Object o2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        // 两个不同的MultiThreadsError2对象
        Thread t1 = new Thread(new MultiThreadsError2(), "Thread-0");
        Thread t2 = new Thread(new MultiThreadsError2(), "Thread-1");
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-1")) {
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + "已经拿到锁o1");
                // 死锁的条件是同时竞争（先等待1s，然后两个线程都拿到各自的o1 o2锁，都去争取对方的锁，导致死锁）
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + "已经拿到锁o2");
                }
            }

        } else {
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + "已经拿到锁o2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + "已经拿到锁o1");
                }
            }

        }
    }
}
