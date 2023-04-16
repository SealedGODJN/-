package com.NPU.线程八大基础.createThreads;

public class BothRunnableThread {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("来自Runnable");
            }
        }) {
            @Override
            public void run() {
                System.out.println("来自Thread");
            }
        }.start();
    }
}
