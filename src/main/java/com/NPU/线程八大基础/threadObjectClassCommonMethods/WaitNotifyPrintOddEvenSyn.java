package com.NPU.线程八大基础.threadObjectClassCommonMethods;

/**
 * @description 面试题：两个线程交替打印0~100的奇偶数，用synchronized关键字实现
 */
public class WaitNotifyPrintOddEvenSyn {
    private static int count = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            while (count < 100) {
                // 自由竞争锁
                synchronized (lock) {
                    if ((count & 1) == 0) {
                        System.out.println(Thread.currentThread().getName() + " : " + count++);
                    }
                }
            }
        }, "偶数").start();
        new Thread(() -> {
            while (count < 100) {
                // 自由竞争锁
                synchronized (lock) {
                    if ((count & 1) == 1) {
                        System.out.println(Thread.currentThread().getName() + " : " + count++);
                    }
                }
            }
        }, "奇数").start();
    }
}
