package com.java.thread.daemon;

/**
 * 当主线程结束的时候，守护线程将立刻结束
 */
public class ThreadDaemonTest extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println("thread="+i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadDaemonTest test = new ThreadDaemonTest();
        test.setDaemon(true);
        test.start();

        for (int i = 0; i < 50; i++) {
            System.out.println("main="+i);
        }
    }
}
