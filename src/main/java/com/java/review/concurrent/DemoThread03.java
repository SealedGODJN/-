package com.java.review.concurrent;

public class DemoThread03 {
    // 同步执行
    public synchronized void print1() {
        System.out.println(Thread.currentThread().getName() + ">hello!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //异步执行
    public synchronized void print2() {
        System.out.println(Thread.currentThread().getName()+">hello!");
    }

    public static void main(String[] args) {
        final DemoThread03 thread = new DemoThread03();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                thread.print1();
            }
        }, "thread1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                thread.print2();
            }
        }, "thread2");

        t1.start();
        t2.start();
    }
}
