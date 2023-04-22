package com.NPU.synchronized深度解析;

/**
 * @description 类锁的第一种形式，修饰static方法
 */
public class SynchronizedClassStatic4 implements Runnable {
    private static SynchronizedClassStatic4 instant1 = new SynchronizedClassStatic4();
    private static SynchronizedClassStatic4 instant2 = new SynchronizedClassStatic4();

    @Override
    public void run() {
        method();
    }

    private static synchronized void method() {
        System.out.println("我是对象锁的方法修饰符形式。我叫" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instant1);
        Thread t2 = new Thread(instant2);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) ;
        System.out.println("finished");
    }
}
