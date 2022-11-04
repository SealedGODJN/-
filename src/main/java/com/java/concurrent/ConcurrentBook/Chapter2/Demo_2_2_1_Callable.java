package com.java.concurrent.ConcurrentBook.Chapter2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo_2_2_1_Callable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        // 模拟计算需要一秒
        Thread.sleep(1000);
        return 2;
    }

    public static void main(String[] args) throws Exception {
        // 使用线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        Demo_2_2_1_Callable task = new Demo_2_2_1_Callable();
        Future<Integer> result = executor.submit(task);
        // 注意调用get方法会阻塞当前线程，直到得到结果。
        // 所以实际编码中建议使用可以设置超时时间的重载get方法。
        System.out.println(result.get());
    }
}
