package com.java.concurrent.ConcurrentBook.Chapter2;

import java.util.concurrent.*;

public class Demo_2_2_3_FutureTask_TreiberStack {
    private static FutureTask<Integer> ft = new FutureTask<Integer>(new ComputeTask(0, "task"));

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(ft);
        executor.shutdown();
        for (int i = 0; i < 5; i++) {
            System.out.println("for loop " + i);
            System.out.println(Thread.currentThread() + ":" + ft.get());
        }
    }

    private static class ComputeTask implements Callable<Integer> {
        private Integer result;
        private String taskName;

        public ComputeTask(Integer init, String taskName) {
            result = init;
            this.taskName = taskName;
        }

        @Override
        public Integer call() throws Exception {
            for (int i = 0; i < 100; i++) {
                result = i;
            }
            Thread.sleep(5000);
            System.out.println(taskName + " finish!");
            return result;
        }

    }
}
