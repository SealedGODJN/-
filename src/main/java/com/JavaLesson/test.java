package com.JavaLesson;

import java.util.Scanner;

/**
 * 2021-10-17 助教
 *
 * 遇到了问题：两次调用try-with-resource，导致无法正常读取line
 */
public class test {
    /**
     * 20-面向对象-方世琦
     */
    public static void test1() {
        int x = 2;
        int y = (x++)/3;
        System.out.println(y);
    }

    /**
     * 某个学生问我，是否需要
     */
    public static void test0() {
        System.out.println("Student id>");
        String id;

        // 在try语句之后在括号中打开的资源仅在此处和现在需要。
        // .close()在try块中完成工作后，我将立即调用它们的方法。如果在try块中抛出异常，无论如何我都会关闭这些资源。
        try (Scanner scanner = new Scanner(System.in)) {
            id = scanner.nextLine();
        }
        System.out.println("Student name>");
        String name;
        try (Scanner scanner = new Scanner(System.in)) {
            name = scanner.nextLine();
        }

        System.out.println(id);
        System.out.println(name);

        /*
        System.out.println("Student id>");
        String id;
        Scanner scanner = new Scanner(System.in);
        id = scanner.nextLine();

        System.out.println("Student name>");
        String name;
        name = scanner.nextLine();

        scanner.close();

        System.out.println(id);
        System.out.println(name);
         */
    }

    public static void main(String[] args) {
        test1();
    }
}
