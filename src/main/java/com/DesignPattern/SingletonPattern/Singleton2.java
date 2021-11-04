package com.DesignPattern.SingletonPattern;

/**
 * 单null检查
 * 使用这个写法的程序员应该说水平不是太高，这种写法应该被抛弃。其不是线程安全的，也就是说在多线程环境下，系统中有可能存在多个实例
 */
public class Singleton2 {
    private static Singleton2 instance;
    private Singleton2() {
    }

//    public static Singleton2 getInstance() {
//        if (instance == null) {
//            instance = new Singleton2();
//        }
//        return instance;
//    }

    /**
     * 能否能防止多线程同时创建多个实例？
     *
     * 假设我们有两个线程 T1与T2并发访问getInstance方法。
     * 当T1执行完if (instance == null)且instance为null时，其CPU执行时间被T2抢占，
     * 所以T1还没有创建实例。T2也执行if (instance == null)，
     * 此时instance肯定还为null，T2执行创建实例的代码，
     *
     * 当T1再次获得CPU执行时间后，其从synchronized处恢复，又会创建一个<code>实例<code/>。
     *
     * @return
     */
    public static Singleton2 getInstance() {
        if (instance == null) {
            synchronized (Singleton2.class) {
                instance = new Singleton2();
            }
        }
        return instance;
    }
}
