package com.NPU.线程八大基础.threadObjectClassCommonMethods;

/**
 * @description 通过讲解join原理，分析出join的代替写法
 */
public class JoinPrinciple {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完毕");
        });

        t1.start();
        System.out.println("开始等待子线程运行完毕");
//        t1.join();
        synchronized (t1) {
            // wait需要在synchronized代码块中执行
            // 调用t1.wait()之后，当前线程（mainThread）被阻塞
            // 此时t1仍然在运行中
            // t1运行完毕后，t1触发lock.notify_all()，使当前线程mainThread从阻塞被唤醒，同时锁t1也被释放，mainThread拿到锁，继续执行
            t1.wait();
        }
        System.out.println("所有子线程执行完毕");
    }
}
