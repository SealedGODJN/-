package com.java.thread.notify;

/**
 * Thread类的notify()方法用于唤醒单个线程。 此方法仅为一个等待特定对象的线程提供通知。
 * 如果使用notify()方法并且有多个线程正在等待通知，那么只有一个线程获得通知，而剩下的线程必须等待进一步的通知。
 *
 * //原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/java_multithreading/java-thread-notify-method.html
 */
public class JavaNotifyExample {
    public static void main(String[] args) throws InterruptedException {
        Notify1 notify1 = new Notify1();
        Notify2 notify2 = new Notify2(notify1);
        Notify3 notify3 = new Notify3(notify1);
//        Notify3 notify3 = new Notify3(notify1, notify2);

        // creating the threads
        Thread t1 = new Thread(notify1, "Thread-1");
        Thread t2 = new Thread(notify2, "Thread-2");
        Thread t3 = new Thread(notify3, "Thread-3");

        // call run() method
        t1.start();
        t2.start();
        Thread.sleep(5000);
        t3.start();
    }
}


class Notify1 extends Thread {
    @Override
    public void run() {
        synchronized (this) {
            System.out.println("Starting of " + Thread.currentThread().getName());
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "...notified");
            this.stop(); // 使其停止
            System.out.println(Thread.currentThread().getName() + "...stop");
        }
    }
}

/**
 * notify2 让 notify1 wait
 * 当notify3 唤醒 notify1 后，notify2不知道thread1 被唤醒了，一直在等待？
 *
 * notify2也需要被唤醒
 */
class Notify2 extends Thread {
    Notify1 notify1;
    Notify2(Notify1 notify1) {
        this.notify1 = notify1;
    }

    @Override
    public void run() {
        synchronized (this.notify1) {
            System.out.println("Starting of " + Thread.currentThread().getName());
            try {
                this.notify1.wait(); // this.notify1 是临界资源？
//                if(!this.notify1.isAlive()) {
//                    this.stop();
//                    System.out.println(Thread.currentThread().getName() + "...stop");
//                }
                // 上面syn之后，如果notify1被唤醒，则该方法无法继续运行，需要等待notify1结束？notify2才能结束？
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "...notified");
        }

    }
}

class Notify3 extends Thread {
    Notify1 notify1;
//    Notify2 notify2;
    Notify3(Notify1 notify1) {
        this.notify1 = notify1;
    }

//    Notify3(Notify1 notify1, Notify2 notify2) {
//        this.notify1 = notify1;
//        this.notify2 = notify2;
//    }

    @Override
    public void run() {
        synchronized (this.notify1) {
            System.out.println("Starting of " + Thread.currentThread().getName());
            // call the notify() method
            this.notify1.notify();
//            this.notify2.notify();
            System.out.println(Thread.currentThread().getName() + "...notified");
        }
    }
}
