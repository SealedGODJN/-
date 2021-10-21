package com.java.io.SystemOutAndSystemErr;

public class Test2 {
    static{
        System.out.println("1");
    }
    public static void main(String[] args) {
        System.err.println("2");
        new Test2();
    }
    public Test2() {
        System.out.println("3");
    }

}
