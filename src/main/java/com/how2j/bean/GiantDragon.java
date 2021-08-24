package com.how2j.bean;

/**
 * @author hjn
 * @date 2021-8-19
 *
 * 单例模式又叫做 Singleton模式，指的是一个类，在一个JVM里，只有一个实例存在。
 *
 * GiantDragon 应该只有一只，通过私有化其构造方法，使得外部无法通过new 得到新的实例。
 * GiantDragon 提供了一个public static的getInstance方法，外部调用者通过该方法获取12行定义的对象，而且每一次都是获取同一个对象。
 * 从而达到单例的目的。
 * 这种单例模式又叫做饿汉式单例模式，无论如何都会创建一个实例
 */
public class GiantDragon {
}
