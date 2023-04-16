package com.NPU.线程八大基础.createThreads.wrongways;

import java.util.Timer;
import java.util.TimerTask;

public class DemoTimer {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, 1000, 1000);
    }
}
