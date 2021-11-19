package com.java.thread.深入浅出java多线程.chapter2_2;

public class SuspendAndResume {
    private final static Object object = new Object();

    static class ThreadA extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("start...");

                try {
//                Thread.currentThread().suspend();
                    Thread.currentThread().wait(); // "Thread-1" java.lang.IllegalMonitorStateException
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread end...");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadA t1 = new ThreadA();
        ThreadA t2 = new ThreadA();
        t1.start();
        t2.start();
        Thread.sleep(100);
        System.out.println(t1.getState());
        System.out.println(t2.getState());

//        t1.resume();
//        t2.resume();

        t1.notify();
        t2.notify();
    }

    /*
start...
RUNNABLE // 有一个线程进入了run方法，尽管调用了suspend方法，但是其状态仍然是RUNNABLE，而不是WAITING
BLOCKED // 有一个线程被阻塞了（syn）
thread end... // 说明resume()操作确实生效了
start... // 另一个线程进入了synchronized区域，但是神奇的事情发生了，另一个线程也被主线程调用过resume()方法，但实际情况是这个线程在这里卡住了，没有释放掉，为何？

// main方法所在的线程对第2个进入synchronized区域的线程做的resume()操作很可能发生在它未进入synchronized区域之前，
// 也自然发生在它调用suspend()操作之前，在线程没有调用suspend()方法之前调用resume()是无效的，
// 也不会使得线程在其后面调用suspend()方法直接被唤醒。当该线程被挂起时，相应持有的锁就释放不掉了（因为它的操作与锁无关），
// 而外部认为已经将这个线程释放掉了，因为外部看到的状态是RUNNING，而且已经调用过resume()方法了，
// 由于这些信息的不一致就导致了各种资源无法释放的问题。
     */
}
