package com.NPU.synchronized深度解析;

/**
 * @description 对象锁示例2，方法锁形式
 */
public class SynchronizedObjectMethod3 implements Runnable {
    private static SynchronizedObjectMethod3 instant = new SynchronizedObjectMethod3();

    @Override
    public void run() {
        method();
    }

    private synchronized void method() {
        System.out.println("我是对象锁的方法修饰符形式。我叫" + Thread.currentThread().getName());
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
