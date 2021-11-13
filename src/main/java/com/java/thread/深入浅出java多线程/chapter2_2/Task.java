package com.java.thread.深入浅出java多线程.chapter2_2;

import java.util.concurrent.*;

public class Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        // 模拟计算需要1s
        Thread.sleep(1000);
        return 2;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> result = executorService.submit(task);
        // 调用get方法会阻塞当前线程，直到得到结果
        // 实际使用时，会设置超时时间
//        System.out.println(result.get(1, TimeUnit.SECONDS)); // 超时
//        System.out.println(result.get(2, TimeUnit.SECONDS)); // 是否超时？不超时
        System.out.println(result.get(2, TimeUnit.SECONDS)); // 不超时
    }
}
