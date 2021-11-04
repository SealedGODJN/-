package com.DesignPattern.SingletonPattern;

/**
 * 双重null检查
 * 为了解决上面单null检查的线程安全与程序性能的问题，出现了double-check的方式。
 * 此方式的关键一个点就在于volatile关键字，其阻止了虚拟机指令重排，使得我们的双检查得以实现。
 *
 * 在Java5之前，这种双重检查的方式即使加上了volatile也没有用，还是不能用，因为JVM有bug。
 *
 * 所以double-check方式一定要加volatile关键字，否则由于指令重拍会导致单例失败
 */
public class Singleton4 {
    private static volatile Singleton4 instance;
    private Singleton4() {
    }

    /**
     * 第一重check为了提高访问性能。
     * 因为一旦实例被创建，所有的check永远为假。
     * 其实你把第一重check去掉也没问题，只是访问性能降低了，那样就变成和直接同步方法一样了。
     *
     * 第二重check是为了线程安全，确保多线程环境下只生成一个实例。具体分析可以参考单check部分。
     *
     * 第一重ckeck可以被多个线程进入，但是第二重check却只能排队进入
     *
     * @return
     */
    public static Singleton4 getInstance() {
        if (instance == null) {
            synchronized (Singleton4.class) {
                if(instance ==null){
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}

