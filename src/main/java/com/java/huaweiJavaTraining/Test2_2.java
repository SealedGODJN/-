package com.java.huaweiJavaTraining;

public class Test2_2 {
    public static void main(String[] args) {
        System.out.println("----隐藏父类信息，显示子类信息----");
        UglyDuck child = new UglyDuck(); // 从子类访问
        child.fly(); // 隐藏父类的静态方法
        System.out.println(("UglyDuck weight is " + child.weight));
        UglyDuck.Blood childInner = child.new Blood(); // 隐藏父类的内部
        System.out.println("UglyDuck bp is " + childInner.bp); // 隐藏父类的内部类的方法
        childInner.circulate(); // 隐藏父类的内部类

        System.out.println("--------显示父类信息--------");
        Swan father = new UglyDuck(); // 从父类访问
        father.fly(); // 父类的静态方法
        System.out.println("Swan weight is " + father.weight); // 父类的属性
        Swan.Blood fatherInner = father.new Blood(); // 父类的内部类
        fatherInner.circulate(); // 父类的内部类的方法
        System.out.println("Swan bp is " + fatherInner.bp); // 父类的内部类的方法
    }
}

// 不清楚“隐藏(hide)”的用法和含义
class Swan{
    int weight = 1;
    public static void fly() {
        System.out.println(("Swan can fly"));
    }

    public class Blood{
        public int bp = 1;
        public void circulate(){
            System.out.println("Swan's blood circulate");
        }
    }
}

class UglyDuck extends Swan{
    int weight = 2; // 属性、静态方法和内部类可以隐藏在其父类中可以访问到的具有相同名字的成员。在其被隐藏后，阻止其被继承
    public static void fly(){
        System.out.println("ugly duck can't fly");
    }

    public class Blood{
        public int bp = 2;
        public void circulate() {
            System.out.println("UglyDuck's blood circulate");
        }
    }
}