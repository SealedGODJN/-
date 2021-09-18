package com.how2j.java.TestVolatile;

public class TestVolatile {
    private static boolean ready;
    private static int number;
    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while(!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }
    public static void main(String[] args) throws InterruptedException {
//        new ReaderThread().start();
        Thread t1 = new ReaderThread();
        t1.start();
        t1.join(); // 主线程会等待该线程结束完毕， 才会往下运行。
        // t1线程加入到main线程中来，只有t1线程运行结束，才会继续往下走
        number = 42;
        ready = true;
    }
}