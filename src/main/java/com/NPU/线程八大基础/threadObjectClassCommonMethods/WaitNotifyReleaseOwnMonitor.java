package com.NPU.线程八大基础.threadObjectClassCommonMethods;

/**
 * @description wait只释放当前的那把锁
 */
public class WaitNotifyReleaseOwnMonitor {
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println(Thread.currentThread().getName() + "got resourceA lock");
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread().getName() + "got resourceB lock");
                    try {
                        resourceA.wait(); // 只会释放A对应的锁
                        System.out.println(Thread.currentThread().getName() + "releases resourceA lock");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (resourceA) {
                System.out.println(Thread.currentThread().getName() + "got resourceA lock");
                System.out.println(Thread.currentThread().getName() + "tries to get resourceB lock");
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread().getName() + "got resourceB lock");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
