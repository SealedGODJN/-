package com.java.huaweiJavaTraining;

// 遮蔽
class TestInnerClass {
    public void test(){
        System.out.println("other TestInnerClass's method test");
    }
}

public class WhoKnows{
    static String sentence = "I don't know";
    void test() {
        System.out.println("WhoKnows's method test");
    }
    void testInner() {
        TestInnerClass testInnerClass = new TestInnerClass(); // 实例化重名类
        testInnerClass.test(); // 调用重名类的方法
    }
    public class TestInnerClass { // "遮蔽"了外部的TestInnerClass
        public int testField = 1;

        public void test() { // "遮蔽"了WhoKnows的方法test()
            System.out.println("TestInnerClass method test");
        }

        public void testMethod() {
            String sentence = "I know"; // "遮蔽"了WhoKnows的属性sentence
            System.out.println(sentence);
            test();
        }
    }

    public static void main(String[] args) {
        WhoKnows whoKnows = new WhoKnows();
        WhoKnows.TestInnerClass testInnerClass = whoKnows.new TestInnerClass();
        testInnerClass.testMethod();
        whoKnows.testInner(); // 不能正确的执行第17行的代码（遮蔽导致不能正确引用简单名）
    }
}