package com.NPU.线程八大基础.sixstates;

/**
 * @description 展示线程的NEW、RUNNABLE、Terminated状态。即使是正在运行，也是RUNNABLE状态，而不是Running
 */
public class NewRunnableTerminated implements Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new NewRunnableTerminated());
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 打印出RUNNABLE的状态，即使是正在运行，也是RUNNABLE，而不是Running
        System.out.println(thread.getState());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getState());
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(i);
        }
    }
}
