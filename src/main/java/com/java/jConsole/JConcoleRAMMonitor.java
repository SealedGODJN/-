package com.java.jConsole;

import java.util.ArrayList;
import java.util.List;

/**
 * VM参数： -Xms100m -Xmx100m -XX:+UseSerialGC
 */
public class JConcoleRAMMonitor {

    /***
     * 内存占位符对象，一个OOMObject大约占64KB
     */
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<OOMObject>();
        for (int i = 0; i < num; i++) {
            // 稍作延时，令监视曲线的变化更加明显
            Thread.sleep(10); // 注释之后，会直接产生：java.lang.OutOfMemoryError: Java heap space
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws Exception {
        fillHeap(2000);
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(5000);
//                        System.gc();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        });
//        t.start();
    }
}