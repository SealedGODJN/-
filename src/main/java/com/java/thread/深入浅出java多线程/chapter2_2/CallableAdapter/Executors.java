package com.java.thread.深入浅出java多线程.chapter2_2.CallableAdapter;

import java.util.concurrent.Callable;

/**
 * 抽取一部分的Executors的源码
 */
public class Executors {
    public static <T> Callable<T> callable(Runnable task, T result) {
        if (task == null) throw new NullPointerException();
        return new RunnableAdapter<>(task, result); // 使用适配器模式
    }

    static final class RunnableAdapter<T> implements Callable<T> {
        final Runnable task;
        final T result;
        RunnableAdapter(Runnable task, T result) {
            this.task = task;
            this.result = result;
        }
        public T call() {
            task.run();
            return result;
        }
    }
}
