package com.java.thread.深入浅出java多线程.chapter2_2;

import java.util.concurrent.*;

public class Task1 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        // 模拟计算需要1s
        Thread.sleep(1000);
        return 2;
    }

    public static void main(String args[]) throws ExecutionException, InterruptedException {
        // 使用
        ExecutorService executorService = Executors.newCachedThreadPool();
        FutureTask<Integer> futureTask = new FutureTask<>(new Task1());
        executorService.submit(futureTask);
        System.out.println(futureTask.get()); // 使用FutureTask直接取get取值
    }
}
