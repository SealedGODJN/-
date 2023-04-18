package com.NPU.线程八大基础.stopThreads.volatileDemo;

/**
 * @description 演示用volatile的局限：part1 看似可行
 */
public class WrongWayVolatile implements Runnable {

    /**
     * 可见性：多个线程都能看到该变量真实的值
     */
    private volatile boolean canceled = false;


    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile r = new WrongWayVolatile();
        Thread t = new Thread(r);
        t.start();
        Thread.sleep(5000);
        r.canceled = true;
    }
}
