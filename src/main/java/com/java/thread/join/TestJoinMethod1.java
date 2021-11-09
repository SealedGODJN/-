package com.java.thread.join;

public class TestJoinMethod1 extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-" + i);
        }
    }

    public static void main(String[] args) {
        TestJoinMethod1 t1 = new TestJoinMethod1();
        TestJoinMethod1 t2 = new TestJoinMethod1();
        TestJoinMethod1 t3 = new TestJoinMethod1();

        t1.start();
        try {
//            t1.join(); // 强制执行t1，等t1执行完之后，才能开始执行下面等语句（即t1不能和t2，t3一起执行）
            t1.join(1500); // 超时之后，t1开始和t2、t3一起参与线程调度
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();
        t3.start();
    }
}
