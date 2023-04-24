package com.NPU.线程八大基础.threadObjectClassCommonMethods;

/**
 * @description 展示wait 和 notify 的基本用法
 * 1、研究代码的执行顺序
 * 2、证明wait释放锁
 */
public class Wait {

    public static Object object = new Object();

    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "开始执行了");

                try {
                    // 释放锁
                    System.out.println("开始等待");
                    object.wait();
                    System.out.println("结束等待");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "获取到了锁");
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                // 能够执行以下的语句，证明wait()方法释放了锁
                object.notify();
                System.out.println(Thread.currentThread().getName() + "调用了notify()");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();
        t1.start();
        Thread.sleep(200);
        t2.start();
    }
}
