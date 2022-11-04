package com.java.concurrent.ConcurrentBook.Chapter2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 使用上与第一个Demo有一点小的区别。
 * 首先，调用submit方法是没有返回值的。
 * 这里实际上是调用的submit(Runnable task)方法，而上面的Demo，调用的是submit(Callable<T> task)方法。
 * <p>
 * 然后，这里是使用FutureTask直接取get取值，而上面的Demo是通过submit方法返回的Future去取值。
 * <p>
 * 在很多高并发的环境下，有可能Callable和FutureTask会创建多次。FutureTask能够在高并发环境下确保任务只执行一次。
 */
public class Demo_2_2_3_FutureTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        // 模拟计算需要一秒
        Thread.sleep(1000);
        return 2;
    }

    public static void main(String args[]) throws Exception {
        // 使用
        ExecutorService executor = Executors.newCachedThreadPool();
        FutureTask<Integer> futureTask = new FutureTask<>(new Demo_2_2_3_FutureTask());
        executor.submit(futureTask);
        System.out.println(futureTask.get());
    }
}
