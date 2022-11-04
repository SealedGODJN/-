package com.java.concurrent.ConcurrentBook.Chapter3;

import java.util.stream.IntStream;

/**
 * Java提供一个线程调度器来监视和控制处于RUNNABLE状态的线程。线程的调度策略采用抢占式，优先级高的线程比优先级低的线程会有更大的几率优先执行。在优先级相同的情况下，按照“先到先得”的原则。每个Java程序都有一个默认的主线程，就是通过JVM启动的第一个线程main线程。
 * <p>
 * 还有一种线程称为守护线程（Daemon），守护线程默认的优先级比较低。
 * <p>
 * 如果某线程是守护线程，那如果所有的非守护线程都结束了，这个守护线程也会自动结束。
 * <p>
 * 应用场景是：当所有非守护线程结束时，结束其余的子线程（守护线程）自动关闭，就免去了还要继续关闭子线程的麻烦。
 * <p>
 * 一个线程默认是非守护线程，可以通过Thread类的setDaemon(boolean on)来设置。
 */
public class Demo_3_2_ThreadPriority_setPriority {
    public static class T1 extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.printf("当前执行的线程是：%s，优先级：%d%n",
                    Thread.currentThread().getName(),
                    Thread.currentThread().getPriority());
        }
    }

    public static void main(String[] args) {
        IntStream.range(1, 10).forEach(i -> {
            Thread thread = new Thread(new T1());
            thread.setPriority(i);
            thread.start();
        });
    }
}
