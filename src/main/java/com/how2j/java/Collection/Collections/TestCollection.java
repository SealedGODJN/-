package com.how2j.java.Collection.Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TestCollection {
    public static void test1() {
        //初始化集合numbers
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        System.out.println("集合中的数据:");
        System.out.println(numbers);

        Collections.reverse(numbers);

        System.out.println("翻转后集合中的数据:");
        System.out.println(numbers);
    }

    public static void test2() {
        //初始化集合numbers
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        System.out.println("集合中的数据:");
        System.out.println(numbers);

        Collections.shuffle(numbers);

        System.out.println("混淆后集合中的数据:");
        System.out.println(numbers);
    }

    public static void test3() {
        //初始化集合numbers
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        System.out.println("集合中的数据:");
        System.out.println(numbers);

        Collections.shuffle(numbers);
        System.out.println("混淆后集合中的数据:");
        System.out.println(numbers);

        Collections.sort(numbers);
        System.out.println("排序后集合中的数据:");
        System.out.println(numbers);
    }

    public static void test4() {
        //初始化集合numbers
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        System.out.println("集合中的数据:");
        System.out.println(numbers);

        Collections.swap(numbers,0,5);
        System.out.println("交换0和5下标的数据后，集合中的数据:");
        System.out.println(numbers);
    }

    public static void test5() {
        //初始化集合numbers
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        System.out.println("集合中的数据:");
        System.out.println(numbers);

        Collections.rotate(numbers,2);
        System.out.println("把集合向右滚动2个单位，标的数据后，集合中的数据:");
        System.out.println(numbers);
    }

    public static void test6() {
        List<Integer> numbers = new ArrayList<>();

        System.out.println("把非线程安全的List转换为线程安全的List");
        List<Integer> synchronizedNumbers = (List<Integer>) Collections.synchronizedList(numbers);
    }

    public static void test6_1() {


        /**
         * 使用线程安全的List
         *
         * 输出结果：
         * [1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, ...
         *
         * 问题：
         * 仍然存在线程不安全？并不是，只不过有时候，t1先执行，有时候t2先执行，是线程安全的
         */
//        List<Integer> numbers = new ArrayList<>();
//        System.out.println("把非线程安全的List转换为线程安全的List");
//        List<Integer> synchronizedNumbers = (List<Integer>) Collections.synchronizedList(numbers);

        /**
         * 使用线程不安全的List
         *
         * 输出结果：
         * 1、分析报错：有些排在后面的t1线程先执行了，有些排在前面的t1线程先执行了
         * Exception in thread "Thread-21" java.lang.ArrayIndexOutOfBoundsException: 22
         * 	at java.util.ArrayList.add(ArrayList.java:465)
         * 	at com.how2j.java.Collection.Collections.TestCollection$1.run(TestCollection.java:120)
         * 	at java.lang.Thread.run(Thread.java:748)
         *
         * 	    2、分析null：原因在于Thread.sleep()，有些线程并没有add进去？
         * 	[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, null,
         */
        List<Integer> synchronizedNumbers = new ArrayList<>();


        final int[] i = new int[1000];

        for (int j = 0; j < 1000; j++) {
            int finalJ = j;
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronizedNumbers.add(i[finalJ]);

                    Random random = new Random();
                    try {
                        Thread.sleep(100);
//                        Thread.sleep(random.nextInt(100));
                        // 直接原地起飞，多个java.lang.ArrayIndexOutOfBoundsException: 355
                        // 有些排在后面的t1线程先执行了，有些排在前面的t1线程先执行了
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t1.start();
        }


        for (int j = 0; j < 1000; j++) {
            int finalJ = j;
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
//                    i[finalJ]++;

                    /**
                     * 不管是线程安全，还是不安全，都会出现问题
                     *
                     * java.lang.IndexOutOfBoundsException: Index: 883, Size: 883
                     */
                    synchronizedNumbers.remove(synchronizedNumbers.size()-1);
                }
            });
            t2.start();
        }

        System.out.println(synchronizedNumbers.size());
    }

    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
        test6_1();
    }
}
