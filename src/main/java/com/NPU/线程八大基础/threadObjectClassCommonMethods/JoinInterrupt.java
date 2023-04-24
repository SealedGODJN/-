package com.NPU.线程八大基础.threadObjectClassCommonMethods;

/**
 * @description 演示join被中断的效果
 */
public class JoinInterrupt {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread t1 = new Thread(() -> {
            try {
                mainThread.interrupt();
                Thread.sleep(5000);
                System.out.println("Thread1执行完毕");
            } catch (InterruptedException e) {
                System.out.println("子线程中断");
                e.printStackTrace();
            }
        });
        t1.start();
        System.out.println("等待子线程运行完毕");
        try {
            t1.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "主线程中断了");
            // 中断传递给子线程
            t1.interrupt();
            e.printStackTrace();
        }
        // 实际子线程还未执行完毕
        System.out.println("子线程运行完毕");
    }
}
