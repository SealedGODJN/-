package com.NPU.concurrent_tools_practice.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description 1000个打印日期的任务，用线程池来执行
 */
public class ThreadLocalNormalUsage04 {

    static SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage00().date(finalI);
                    System.out.println(date);
                }
            });
//            Thread.sleep(100);
        }
        threadPool.shutdown();
    }

    public String date(int seconds) {
        // 传入的参数，单位是ms
        // 从1970.1.1 00：00：00 GMT 开始计时
        Date date = new Date(1000 * seconds);
        // 减少重复创建simpleDateFormate的问题
        // 问题：
        // 存在时间相同的输出结果

        String result = null;
        // 类锁
        synchronized (ThreadLocalNormalUsage04.class) {
//        synchronized (s) {
            result = s.format(date);
        }
        return result;
    }

}
