package com.NPU.synchronized深度解析;

/**
 * @description 对象锁：1、代码块形式
 */
public class SynchronizedObjectCodeBlock2 implements Runnable {

    private static SynchronizedObjectCodeBlock2 instant = new SynchronizedObjectCodeBlock2();

    Object lock1 = new Object();

    Object lock2 = new Object();

    @Override
    public void run() {
        synchronized (lock1) {
//        synchronized (this) {
            System.out.println("我是lock1对象锁的代码块形式。我叫" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "运行结束lock1");
        }

        synchronized (lock2) {
//        synchronized (this) {
            System.out.println("我是lock2对象锁的代码块形式。我叫" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "运行结束lock2");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instant);
        Thread t2 = new Thread(instant);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) ;
        System.out.println("finished");
    }
}
