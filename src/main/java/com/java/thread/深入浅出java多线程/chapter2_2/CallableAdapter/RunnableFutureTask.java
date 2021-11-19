package com.java.thread.深入浅出java多线程.chapter2_2.CallableAdapter;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

public class RunnableFutureTask {
    /**
     * ExecutorService
     */
    static ExecutorService mExecutor = newSingleThreadExecutor();

    /**
     * runnable，无返回值
     */
    static void runnableDemo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable demo:" + fibc(20));
            }
        }).start();
    }

    /**
     * Runnable实现的是void run()方法，无返回值
     * Callable实现的是V call()方法，并且可以返回执行结果
     *
     * Runnable可以提交给Thread来包装，直接启动一个线程来执行
     * 而Callable一般是提交给ExecuteService执行
     */
    static void futureDemo() {
        try {
            /**
             * 提交runnable没有返回值，future没有数据
             */
            Future<?> result = mExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    fibc(20);
                }
            });

            System.out.println("future result from runnable:" + result.get());

            Future<Integer> result2 = mExecutor.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return fibc(20);
                }
            });

            System.out.println("future result from runnable:" + result2.get());

            FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return fibc(20);
                }
            });

            mExecutor.submit(futureTask); // 提交futureTask
            System.out.println("future result from futureTask:" + futureTask.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }


    /**
     * 效率底下的斐波那契数列, 耗时的操作
     *
     * @param num
     * @return
     */
    static int fibc(int num) {
        if (num == 0) {
            return 0;
        }
        if (num == 1) {
            return 1;
        }
        return fibc(num - 1) + fibc(num - 2);
    }


    public static void main(String[] args) {
        runnableDemo();
        futureDemo();
    }
}
