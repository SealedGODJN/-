package com.NPU.java内存模型.可见性;

/**
 * @description volatile能够确保某个属性被修改前的所有操作都被其他线程可见
 */
public class FieldVisibility1 {
    int a = 1;

    int abc = 1;

    int abcd = 1;

    volatile int b = 0;

    private void print() {
        if (b == 0)
            System.out.println("abc = " + abc + ",abcd = " + abcd + ",a = " + a);
    }

    private void change() {
        abc = 7;
        abcd = 70;
        a = 3;
        // b充当触发器的作用
        b = 0;
    }

    public static void main(String[] args) {
        while (true) {
            FieldVisibility1 test = new FieldVisibility1();
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
