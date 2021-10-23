package com.how2j.java.Exception;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class TestException {

    public static void test1() {
//        File f= new File("d:/LOL.exe");

        //试图打开文件LOL.exe，会抛出FileNotFoundException，如果不处理该异常，就会有编译错误
//        new FileInputStream(f);
    }

    public static void test2() {
        Scanner sc = new Scanner(System.in);
        int t;
        try {
            t = sc.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("请重新输入");
            t = sc.nextInt();
        }
    }

    public static void test3() {
        Scanner sc = new Scanner(System.in);
        double score = sc.nextDouble();
        String in = sc.nextLine();
        String length = in.trim();
    }

    public static void main(String[] args) {

    }
}