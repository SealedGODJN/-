package com.NPU.threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description 关闭线程池
 */
public class ShutDown {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new ShutDownTask());
        }
        Thread.sleep(1500);

//        System.out.println("===================" + executorService.isShutdown() + "===================");
//
//        executorService.shutdown();
//
//        System.out.println("===================" + executorService.isShutdown() + "===================");
//
//        System.out.println("===================" + executorService.isTerminated() + "===================");
//
//        // 无法提交新任务
////        executorService.execute(new ShutDownTask());
//
//        Thread.sleep(10000);
//        System.out.println("===================" + executorService.isTerminated() + "===================");

//        executorService.shutdown();
//        boolean b = executorService.awaitTermination(3L, TimeUnit.SECONDS);
//        System.out.println(b);

        // 被中断的任务列表
        List<Runnable> runnableList = executorService.shutdownNow();
    }
}

class ShutDownTask implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(100);

            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断了");
        }
    }
}
