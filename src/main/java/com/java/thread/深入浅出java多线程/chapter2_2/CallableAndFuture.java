package com.java.thread.深入浅出java多线程.chapter2_2;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableAndFuture {
    public static void main(String[] args) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(3000);
                return new Random().nextInt(1500);
            }
        };
        FutureTask<Integer> future = new FutureTask<>(callable);
        new Thread(future).start();
        try {
            Thread.sleep(1000);
            System.out.println("hello begin");
            System.out.println(future.isDone()); // 该任务是否完成
            System.out.println(future.get());
            if (future.get() > 1000) future.cancel(false); // 这个参数代表什么？
            // mayinterruptIfRunning 如果该线程正在执行，该参数为true，则cancel方法会立刻让该线程interrupt
            // 否则，该线程可以一直执行完毕

            System.out.println(future.isDone()); // 该任务是否完成
            System.out.println("hello end");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}