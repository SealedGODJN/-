package com.NPU.deadlock;

/**
 * @description 必定发生死锁的情况
 */
public class MustDeadLock implements Runnable {
    int flag = 1;
    static Object o1 = new Object();
    static Object o2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        MustDeadLock r1 = new MustDeadLock();
        MustDeadLock r2 = new MustDeadLock();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

//        t1.join();
//        t2.join();
    }

    @Override
    public void run() {
        System.out.println("flag = " + flag);

        if (flag == 1) {
            synchronized (o1) {
                try {
                    System.out.println(Thread.currentThread().getName() + " 获得o1");
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName() + " 等待o2");
                    synchronized (o2) {
                        System.out.println(Thread.currentThread().getName() + " 获得o1和o2");
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        if (flag == 0) {
            synchronized (o2) {
                try {
                    System.out.println(Thread.currentThread().getName() + " 获得o2");
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName() + " 等待o1");
                    synchronized (o1) {
                        System.out.println(Thread.currentThread().getName() + " 获得o1和o2");
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
