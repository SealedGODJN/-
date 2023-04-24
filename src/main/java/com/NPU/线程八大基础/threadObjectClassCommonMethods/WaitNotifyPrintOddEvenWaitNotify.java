package com.NPU.线程八大基础.threadObjectClassCommonMethods;

/**
 * @description 面试题：两个线程交替打印0~100的奇偶数，用wait和notify提高效率
 */
public class WaitNotifyPrintOddEvenWaitNotify {
    private static final Object lock = new Object();
    private static int count = 0;

    static class Even implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i <= 100; i++) {
                synchronized (lock) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + " : " + i);
                        // 唤醒其他人
                        lock.notify();
                        try {
                            // 释放锁
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    static class Odd implements Runnable {
        @Override
        public void run() {

            for (int i = 0; i <= 100; i++) {
                synchronized (lock) {
                    if (i % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + " : " + i);
                        // 唤醒其他人
                        lock.notify();
                        try {
                            // 释放锁
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    static class TurningRunner implements Runnable {
        @Override
        public void run() {
            while (count <= 100) {
                synchronized (lock) {
                    // 1. 拿到锁，就立刻开始打印
                    System.out.println(Thread.currentThread().getName() + " : " + count++);
                    // 2. 打印完，通知对方可以唤醒
                    lock.notify();
                    if (count <= 100) {
                        try {
                            // 如果任务还未结束，就让出当前的锁
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 方法1：使用两个Runnable
//        new Thread(new Even(), "偶数").start();
//        new Thread(new Odd(), "奇数").start();
        // 方法2：使用一个Runnable，由两个线程互相通知对方（wait+notify）
        new Thread(new TurningRunner(), "偶数").start();
        Thread.sleep(100);
        new Thread(new TurningRunner(), "奇数").start();
    }
}
