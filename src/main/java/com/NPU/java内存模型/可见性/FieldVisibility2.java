package com.NPU.java内存模型.可见性;

/**
 * @description 理解synchronized的可见性
 */
public class FieldVisibility2 {
    int a = 1;
    int b = 2;
    int c = 2;
    int d = 2;
    private void print() {
        int aa = 0;
        // 在读取时，把第一个元素放入synchronized中，利用synchronized的可见性
        synchronized (this) {
             aa = a;
        }
        int bb = b;
        int cc = c;
        int dd = d;
        System.out.println("aa = " + aa + ",bb = " + bb + ",cc = " + cc + ",dd = " + dd);
    }

    private void change() {
        a = 3;
        b = 4;
        c = 5;
        // 利用synchronized的可见性，在修改时，用synchronized来保证前面的操作都执行，并被其他线程可见
        synchronized (this) {
            d = 6;
        }
    }

    public static void main(String[] args) {
        while (true) {
            FieldVisibility2 test = new FieldVisibility2();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.change();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.print();
                }
            }).start();
        }
    }
}
