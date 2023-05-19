package com.NPU.线程八大基础.ThreadSafe;

/**
 * @description 用工厂模式修复count初始化问题
 */
public class MultiThreadsError7 {

    int count;
    private final EventListener listener;

    // 不再使用构造方法
    private MultiThreadsError7(MySource source) {
        listener = e -> System.out.println("\n我得到的数字是" + count);
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    /**
     * 工厂方法
     *
     * @param source
     * @return
     */
    public static MultiThreadsError7 getInstance(MySource source) {
        // 初始化工作完成后，才会注册safeListener
        // 以前把两步工作设为一步，容易出现线程不安全的情况
        MultiThreadsError7 safeListener = new MultiThreadsError7(source);
        source.registerListener(safeListener.listener);
        return safeListener;
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

        MultiThreadsError7 multiThreadError7 = MultiThreadsError7.getInstance(mySource);
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
