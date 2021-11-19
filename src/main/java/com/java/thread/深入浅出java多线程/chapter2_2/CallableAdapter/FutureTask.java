package com.java.thread.深入浅出java多线程.chapter2_2.CallableAdapter;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * 抽取FutureTask的一部分内容
 * @param <V>
 */
public class FutureTask<V> {

    private Callable<V> callable;

    /**
     * state 可能转变的状态如下：
     * NEW->COMPLETING->NORMAL
     * NEW->COMPLETING->EXCEPTIONAL
     * NEW->CANCELLED
     * NEW->INTERRUPTING->INTERRUPTED
     */
    private volatile int state;
    private static final int NEW          = 0;
    private static final int COMPLETING   = 1;
    private static final int NORMAL       = 2;
    private static final int EXCEPTIONAL  = 3;
    private static final int CANCELLED    = 4;
    private static final int INTERRUPTING = 5;
    private static final int INTERRUPTED  = 6;

    public FutureTask(Callable<V> callable) {
        if (callable == null)
            throw new NullPointerException();
        this.callable = callable;
        this.state = NEW;       // ensure visibility of callable
    }

    /**
     * 由于FutureTask实现了Runnable，因此它既可以通过Thread包装来直接执行，也可以提交给ExecuteService来执行。
     *
     * 并且还可以直接通过get()函数获取执行结果，该函数会阻塞，直到结果返回。因此FutureTask既是Future、
     *
     * Runnable，又是包装了Callable( 如果是Runnable最终也会被转换为Callable )， 它是这两者的合体。
     *
     * @param runnable
     * @param result
     */
    public FutureTask(Runnable runnable, V result) {
        this.callable = Executors.callable(runnable, result);
        this.state = NEW;       // ensure visibility of callable
    }
}
