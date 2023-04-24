package com.NPU.线程八大基础.threadObjectClassCommonMethods;

/**
 * @description 先join再mainThread.getState()，通过debugger看线程join前后状态的对比
 */
public class JoinThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println(mainThread.getState());
                System.out.println("Thread-0运行结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        System.out.println("开始等待子线程运行完毕");
        t1.join();
        System.out.println("子线程执行完毕");
    }
}
