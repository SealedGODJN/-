package com.how2j.java.TestVolatile;

public class Test {
    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    /**
     * 问题：实际执行的时候，为什么得不出结果？
     *
     * Debug的时候，反而有结果：10000
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        final Test test = new Test();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++)
                    test.increase();
            }).start();
        }

        while (Thread.activeCount() > 1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }
}