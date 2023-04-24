package com.NPU.线程八大基础.threadObjectClassCommonMethods;

/**
 * @description 演示join，注意语句输出顺序，会变化。
 */
public class Join {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完毕");
        });
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完毕");
        });

        t1.start();
        t2.start();
        System.out.println("开始等待子线程运行完毕");
//        t1.join();
//        t2.join();
        System.out.println("所有子线程执行完毕");
    }
}
