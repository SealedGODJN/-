package com.java.jConsole;

import java.util.Date;

public class ThreadLockDemo {

    /**
     * 线程死锁等待演示
     */
    static class SynAddRunalbe implements Runnable {
        int a, b;

        public SynAddRunalbe(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)) {
                System.out.println(new Date().toString() + "\t" + Thread.currentThread().getName() + "锁住a");
                try {
                    Thread.sleep(1000); // 此处等待是给另一个线程能锁住机会
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (Integer.valueOf(b)) {
                    System.out.println(new Date().toString() + "\t" + Thread.currentThread().getName() + "锁住b");
                    System.out.println(a + b);
                }
            }
        }
    }

    public static void main(String[] args) {
//        int a = 1, b = 2;
        int[] a = new int[2];
        a[0] = 1;
        a[1] = 2;
        for (int i = 0; i < 100; i++) {
//            new Thread(new SynAddRunalbe(a, b)).start();
//            new Thread(new SynAddRunalbe(b, a)).start();

            Thread t1 = new Thread(new SynAddRunalbe(a[0], a[1]));
            Thread t2 = new Thread(new SynAddRunalbe(a[1], a[0]));

            t1.start();
            t2.start();
        }
    }
}