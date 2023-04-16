package com.NPU.线程八大基础.createThreads.wrongways;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool5 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(new Task());
        }
    }
}

class Task implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName());
    }
}
