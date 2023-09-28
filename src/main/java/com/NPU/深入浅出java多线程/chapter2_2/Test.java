package com.NPU.深入浅出java多线程.chapter2_2;

public class Test {
    /**
     * 如果是顺序执行的程序，则应当先输出“我是被创建的线程，我执行了…”，然后再输出“main process end…”（因为代码顺序是这样的），
     * 但是大家通过测试结果会发现不一定，而且一般是先输出“main process end…”，
     * 这是因为run()方法被另一个线程调用了，main()方法启动线程后就直接向下执行，
     * 不过启动线程还需要做一些内核调用的处理，最后才会由C区域的方法回调Java中的run()方法，此时main线程可能已经输出了内容。
     *
     * @param args
     */
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "我是被创建的线程，我执行了...");
            }
        }.start();
        System.out.println(Thread.currentThread().getName() + "main process end ...");
    }
}
