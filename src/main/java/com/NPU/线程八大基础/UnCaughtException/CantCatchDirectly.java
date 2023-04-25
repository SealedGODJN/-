package com.NPU.线程八大基础.UnCaughtException;

/**
 * @description 1、不加try catch抛出4个异常，都带线程名字<br>
 * 2、加了try catch，期望捕获道第一个线程的异常，线程234不应该运行，希望看到打印出Caught Exception<br>
 * 3、执行时发现，根本没有Caught Exception，线程234依然运行并且抛出异常<br>
 * <p>
 * 说明线程的异常不能用传统方法捕获
 */
public class CantCatchDirectly implements Runnable {

    public static void main(String[] args) {
        new Thread(new CantCatchDirectly(), "MyThread-1").start();
        new Thread(new CantCatchDirectly(), "MyThread-2").start();
        new Thread(new CantCatchDirectly(), "MyThread-3").start();
        new Thread(new CantCatchDirectly(), "MyThread-4").start();
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
