package com.NPU.synchronized深度解析;

/**
 * @description 抛出异常，释放锁
 */
public class SynchronizedException9 implements Runnable {
    private static SynchronizedException9 instant = new SynchronizedException9();

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            method1();
        } else {
            method2();
        }
    }

    private synchronized void method1() {
        System.out.println("我是加锁的方法1，非static。我叫" + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException();
    }

    private synchronized void method2() {
        System.out.println("我是加锁的方法2，非static。我叫" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
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