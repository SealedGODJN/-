package com.java.review.huawei;

public class Test {
    public static void main(String[] args) {
        // default块执行结束后，如果没有break，仍然会执行下一个case
//        switch (3){
//            default:
//                System.out.print(3);
//            case 0:
//                System.out.print(0);
//                break;
//            case 1:
//                System.out.print(1);
//        }

        // 测试int整型相除
//        System.out.print(6/10);

        // 测试switch语句的执行
            // SwitchBreak对象实例化
//            Test switchBreak = new Test();
//
//            switchBreak.choosePhoneBreak(1);

        // 测试：java的浮点数
//        if(2000000000f == 2000000045) {
//            System.out.println("true");
//        }

//        long a = 1089212234l;
//        float b = 1.0f;
//        b = a;
//        System.out.println(b);

//        short roomWidth = 533;
//        float singleRoomWidth = roomWidth / 7;
//        // 计算过程：roomWidth变为int，计算结果为76（int），再转为float，即76.0
//        System.out.println(singleRoomWidth);

        // 测试数据类型的精度
//        double hillHeight = 2147483642;
//        hillHeight += 1.0;
//        System.out.println(hillHeight);
//        short roomWidth = 533;
//        int treeTall = 6783;
//        long cableLen = 4664382371590123456L;
//        float singleRoomWidth = roomWidth / 5.0f;
//        double averageTall = treeTall / 30.;
//        double totalLen = (double) cableLen * 2;
//        System.out.println(singleRoomWidth);
//        System.out.println(averageTall);
//        System.out.println(totalLen);

        // 隐藏
//        Swan swan = new UglyDuck();
//        System.out.println(swan.weight);
//        swan.fly();
//        // 总结：隐藏会导致动态绑定（dynamic dispatch）失效

        // 遮蔽
        WhoKnows test = new WhoKnows();
        test.testInner();
    }

//    /**
//     * switch包含break语句的方法
//     *
//     * @param number
//     *            分支判断用的数值
//     */
//    private void choosePhoneBreak(int number) {
//        // 输出的结果值
//        String phoneBrand = "phonebrandc!";
//
//        switch (number) {
//            case 1:
//                phoneBrand = "phonebranda!";
//                System.out.println("You choose the " + phoneBrand);
//            case 2:
//                // TODO:给变量phoneBrand赋值，打印输出You choose the xx，xx指的是变量的值。
//                // 打印输出You choose the phonebrandb!
//                phoneBrand = "phonebrandb!";
//                System.out.println("You choose the" + phoneBrand);
//                break;
//            default:
//                System.out.println("You choose the " + phoneBrand);
//        }
//    }
}

//// 不清楚“隐藏(hide)”的用法和含义
//class Swan{
//    int weight = 1;
//    public static void fly() {
//        System.out.println(("Swan can fly"));
//    }
//
//    public class Blood{
//        public int bp = 1;
//        public void circulate(){
//            System.out.println("Swan's blood circulate");
//        }
//    }
//}
//
//class UglyDuck extends Swan{
//    int weight = 2; // 属性、静态方法和内部类可以隐藏在其父类中可以访问到的具有相同名字的成员。在其被隐藏后，阻止其被继承
//    public static void fly(){
//        System.out.println("ugly duck can't fly");
//    }
//
//    public class Blood{
//        public int bp = 2;
//        public void circulate() {
//            System.out.println("UglyDuck's blood circulate");
//        }
//    }
//}

//// 遮蔽
//class TestInnerClass {
//    public void test(){
//        System.out.println("other TestInnerClass's method test");
//    }
//}
//
//class WhoKnows{
//    static String sentence = "I don't know";
//    void test() {
//        System.out.println(("WhoKnows's method test"));
//    }
//    void testInner() {
//        TestInnerClass testInnerClass = new TestInnerClass(); // 实例化重名类
//        testInnerClass.test(); // 调用重名类的方法
//    }
//    public class TestInnerClass { // "遮蔽"了外部的TestInnerClass
//        public int testField = 1;
//
//        public void test() { // "遮蔽"了WhoKnows的方法test()
//            System.out.println(("TestInnerClass method test"));
//        }
//
//        public void testMethod() {
//            String sentence = "I know"; // "遮蔽"了WhoKnows的属性sentence
//            System.out.println(sentence);
//            test();
//        }
//    }
//}