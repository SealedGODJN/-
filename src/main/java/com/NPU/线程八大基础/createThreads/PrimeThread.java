package com.NPU.线程八大基础.createThreads;

/**
 * @author hjn
 * @time 2023.4.16
 * @description 实现线程的第一种方法
 */
public class PrimeThread extends Thread {
    public void run() {
        System.out.println("thread-Id: " + this.getId());
        System.out.println("thread-Name: " + this.getName());
        System.out.println("thread-Priority: " + this.getPriority());
        System.out.println("thread-isDaemon: " + this.isDaemon());
    }

    public static void main(String[] args) {
        PrimeThread t = new PrimeThread();
        t.start();
    }
}
