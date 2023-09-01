package com.NPU.java内存模型.可见性;

public class DisappearRequest1 implements Runnable {

    static DisappearRequest1 instance = new DisappearRequest1();

    static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            synchronized (instance) {
                // 既保证了原子性 又保证了可见性
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(i);
    }
}
