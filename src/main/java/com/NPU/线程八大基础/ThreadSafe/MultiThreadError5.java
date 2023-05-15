package com.NPU.线程八大基础.ThreadSafe;

import org.apache.poi.ss.formula.functions.T;

public class MultiThreadError5 {

    int count;

    public MultiThreadError5(MySource source) {
        source.registerListener(e -> System.out.println("\n我得到的数字是" + count));
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static void main(String[] args) {
        MySource mySource = new MySource();
        new Thread(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            mySource.eventCome(new Event() {
            });
        }).start();

        MultiThreadError5 multiThreadError5 = new MultiThreadError5(mySource);
    }

    /**
     * 观察者模式
     */
    static class MySource {
        private EventListener listener;

        void registerListener(EventListener eventListener) {
            this.listener = eventListener;
        }

        void eventCome(Event e) {
            if (listener != null) {
                listener.onEvent(e);
            } else {
                System.out.println("还未初始化完毕");
            }
        }
    }

    interface EventListener {
        void onEvent(Event e);
    }

    interface Event {

    }
}
