package com.NPU.synchronized深度解析;

/**
 * @description 类锁的第2种形式，synchronized(.class)【java有多个实例对象，而类对象只有一个】
 */
public class SynchronizedClassClass5 implements Runnable {
    private static SynchronizedClassClass5 instant1 = new SynchronizedClassClass5();
    private static SynchronizedClassClass5 instant2 = new SynchronizedClassClass5();

    @Override
    public void run() {
        method();
    }

    private void method() {
        synchronized (SynchronizedClassClass5.class) {
            System.out.println("我是类锁。我叫" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "运行结束");
        }
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
