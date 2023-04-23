package com.NPU.synchronized深度解析;

/**
 * @description 同时访问同步方法和非同步方法
 */
public class SynchronizedYesAndNo6 implements Runnable {
    private static SynchronizedYesAndNo6 instant = new SynchronizedYesAndNo6();

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            method1();
        } else {
            method2();
        }
    }

    private synchronized void method1() {
        System.out.println("我是类lock的method1方法。我叫" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }

    private void method2() {
        System.out.println("我是类lock的method2方法。我叫" + Thread.currentThread().getName());
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
