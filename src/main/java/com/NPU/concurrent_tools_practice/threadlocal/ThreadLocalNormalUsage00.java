package com.NPU.concurrent_tools_practice.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description 两个线程打印日期
 */
public class ThreadLocalNormalUsage00 {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalNormalUsage00().date(10);
                System.out.println(date);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalNormalUsage00().date(104707);
                System.out.println(date);
            }
        }).start();
    }

    public String date(int seconds) {
        // 传入的参数，单位是ms
        // 从1970.1.1 00：00：00 GMT 开始计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return s.format(date);
    }
}
