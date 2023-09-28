package com.NPU.threadpool;

import java.util.concurrent.Executors;

public class ForLoop {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new Task());
            thread.start();
        }
        Executors.newFixedThreadPool(1);
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("新建了任务");
        }
    }
}
