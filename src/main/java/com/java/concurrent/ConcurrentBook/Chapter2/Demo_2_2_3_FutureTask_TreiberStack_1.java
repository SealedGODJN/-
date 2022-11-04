package com.java.concurrent.ConcurrentBook.Chapter2;

import java.util.concurrent.*;

public class Demo_2_2_3_FutureTask_TreiberStack_1 {
    private static FutureTask<Integer> ft = new FutureTask<>(new ComputeTask(0, "task"));

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(ft);
        executor.shutdown();
        MyThread thread1 = new MyThread();
        thread1.setName("thread1");
        MyThread thread2 = new MyThread();
        thread2.setName("thread2");

        System.out.println(Thread.currentThread().getName() + " : " + ft.get());

        ft.cancel(true);

        thread1.start();
        thread2.start();
//        System.out.println(Thread.currentThread().getName() + " : " + ft.get());
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

    private static class MyThread extends Thread {
        public void run() {
            try {
                System.out.println(this.getName() + " : " + ft.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
