package com.NPU.线程八大基础.threadObjectClassCommonMethods;

/**
 * @description 3个线程：线程1和线程2被阻塞，线程3唤醒这两个线程
 * 1、notify和notifyAll的区别
 * 2、start先执行，不代表线程先启动
 */
public class WatiNotifyAll implements Runnable {

    private static final Object resourceA = new Object();

    @Override
    public void run() {
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + "got ResourceA lock");
            try {
                System.out.println(Thread.currentThread().getName() + "wait to start");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName() + "'s waiting to end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new WatiNotifyAll();
        Thread a = new Thread(r);
        Thread b = new Thread(r);
        Thread c = new Thread(() -> {
            synchronized (resourceA) {
//                resourceA.notifyAll();
//                System.out.println("Thread C notifyAll");
                resourceA.notify();
                System.out.println("Thread C notify");
            }
        });
        a.start();
        b.start();
//        Thread.sleep(200);
        c.start();
    }
}
