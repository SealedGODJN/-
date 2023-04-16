package com.NPU.线程八大基础.createThreads;

/**
 * @author hjn
 * @time 2023.4.16
 * @description 新建线程的第二种方法
 */
public class PrimeRun implements Runnable {

    @Override
    public void run() {
        System.out.println("实现Runnable接口，新建线程的第二种方法");
    }

    public static void main(String[] args) {
        PrimeRun p = new PrimeRun();
        new Thread(p).start();
    }
}
