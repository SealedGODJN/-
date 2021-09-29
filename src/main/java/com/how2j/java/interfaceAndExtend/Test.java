package com.how2j.java.interfaceAndExtend;

/**
 * 两个子类的属性和方法完全一样，但是不能互相转换
 */
public class Test {
    String name = "Test";

    public static void main(String[] args) {
        TestA testA = new TestA();
        Test test = new Test();
        test = testA;
        TestB testB = (TestB) test;
    }
}

class TestA extends Test{
    String name = "Test";
}

class TestB extends Test{
    String name = "Test";
}