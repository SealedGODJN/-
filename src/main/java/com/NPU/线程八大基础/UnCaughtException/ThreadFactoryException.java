package com.NPU.线程八大基础.UnCaughtException;

import java.util.concurrent.*;

/**
 * @description 实现一个线程池的ThreadFactory，在ThreadFactory中指定UncaughtExceptionHandler
 */
public class ThreadFactoryException {
    public static void main(String[] args) throws InterruptedException {
        // 实现一个线程池工厂
        ThreadFactory factory = (Runnable r) -> {
            Thread t = new Thread(r);
            t.setUncaughtExceptionHandler((Thread thread, Throwable e) -> {
                System.out.println("线程工程设置的exceptionHandler: " + e.getMessage());
            });
            return t;
        };

        ExecutorService executorService = new ThreadPoolExecutor(
                1,
                1,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                factory
        );

        // 说明UncaughtExceptionHandler在submit中并没有被调用
        // execute和submit最大的区别就是execute没有返回值，submit有返回值
        // submit返回的是一个future ，可以通过这个future取到线程执行的结果或者异常信息

        // submit方法内部已经捕获了异常
        // submit源码在底层还是调用的execute方法，只不过多一层Future封装，并返回了这个Future

        // 根本原因：FutureTask的run()方法内部使用了try-catch，捕获了异常
        executorService.submit(new Task());

        Thread.sleep(1000);

        // execute 方法被线程工厂factory 的UncaughtExceptionHandler捕捉到异常
        executorService.execute(new Task());
    }
}

/**
 * //submit()方法
 * public <T> Future<T> submit(Callable<T> task) {
 * if (task == null) throw new NullPointerException();
 * <p>
 * //execute内部执行这个对象内部的逻辑，然后将结果或者异常 set到这个ftask里面
 * RunnableFuture<T> ftask = newTaskFor(task);
 * // 执行execute方法
 * execute(ftask);
 * //返回这个ftask
 * return ftask;
 * }
 * <p>
 * 作者：小马哥Y
 * 链接：https://juejin.cn/post/7183499348376813625
 * 来源：稀土掘金
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */

class Task implements Runnable {

    @Override
    public void run() {
        System.out.println("进入了Task的run方法");
        int i = 1 / 0;
    }
}