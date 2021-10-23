package com.java.iterable;

import java.util.Iterator;

public class TestIterable implements Iterable<TestIterable>{
    @Override
    public Iterator<TestIterable> iterator() {
        return this.iterator();
    }
}

// iterable接口其实是java集合大家庭的最顶级的接口之一了，实现这个接口，可以视为拥有了获取迭代器的能力。
// Iterable接口出现在JDK1.5，那个时候只有iterator()方法，主要是定义了迭代集合内元素的规范。从字面的意思看，是指可以迭代的接口。
//
//源码如下：
//
//// 返回一个内部元素为T类型的迭代器（JDK1.5只有这个接口）
//Iterator<T> iterator();
//
//// 遍历内部元素，action意思为动作，指可以对每个元素进行操作（JDK1.8添加）
//default void forEach(Consumer<? super T> action) {}
//
//// 创建并返回一个可分割迭代器（JDK1.8添加），分割的迭代器主要是提供可以并行遍历元素的迭代器，可以适应现在cpu多核的能力，加快速度。
//default Spliterator<T> spliterator() {
//    return Spliterators.spliteratorUnknownSize(iterator(), 0);
//}

//1.Iterator接口的核心方法next()或者hashNext()，previous()等，都是严重依赖于指针的，也就是迭代的目前的位置。
// 如果Collection直接实现Iterator接口，那么集合对象就拥有了指针的能力，内部不同方法传递，就会让next()方法互相受到阻挠。
// 只有一个迭代位置，互相干扰。

//2.Iterable 每次获取迭代器，就会返回一个从头开始的，不会和其他的迭代器相互影响。

//3.这样子也是解耦合的一种，有些集合不止有一个Iterator内部类，可能有两个，比如ArrayList，LinkedList，可以获取不同的Iterator执行不一样的操作。