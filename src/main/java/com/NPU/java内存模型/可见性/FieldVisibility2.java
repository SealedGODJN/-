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
