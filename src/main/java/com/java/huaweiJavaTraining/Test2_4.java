package com.java.huaweiJavaTraining;

// 遮掩（变量导致类不能正确的引用）
public class Test2_4 {
    static String System;

    public static void main(String[] args) {
        // Next line won't compile
//        System.out.println("hello");
    }
}
