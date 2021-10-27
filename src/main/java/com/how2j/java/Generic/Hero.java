package com.how2j.java.Generic;

import com.how2j.java.interfaceAndExtend.AD;

/**
 * 步骤 4 : 练习-传参     顶折纠问 姿势不对,事倍功半! 点击查看做练习的正确姿势
 * 在方法中，使参数引用指向一个新的对象
 *
 * 外面的引用是指向原来的对象？还是新的对象？
 */
public class Hero {

    String name; //姓名

    float hp; //血量

    float armor; //护甲

    int moveSpeed; //移动速度

    public Hero(){

    }

    public Hero(String name, float hp){
        this.name = name;
        this.hp = hp;
    }

    // 复活
    // 类类型传参是把类的首地址传过去了，给形参弄个新对象，相当于放弃了传过来的地址，换成了新赋予的地址，原来地址的变量和形参就没有半毛钱关系了。
    public void revive(Hero h){
        h = new Hero("提莫",383);
        System.out.println("新建对象"+h);

        //this.hp =h.hp;
    }


    public void revive1(Hero h){            // 类类型-参数传递-传递的是引用的地址
        h = new Hero("提莫",383);  // new了一个对象，因此，这是一个新的对象引用（地址变了），而不是我们想要修改的那个对象
        h.hp = 12f;                         // 此时改变该对象的值，但并不能改变原对象的值（因为两个对象引用的地址不一样）
        System.out.println("revive复活后："+h.hp);
        System.out.println("h地址："+h);
        System.out.println();
    }

    // java值传递：当变量作为参数传递到方法内部时，过程执行的是拷贝，不论方法对形参如何修改，都不会影响实参数据。
    // 当变量为类类型时传递，实质传递的是该引用的地址，内部对形参修改影响的只是引用的地址新指向，除非修改对象.属性值，才能真正影响外部实参。
    public void revive2(Hero h){
        h.hp = 1f;                          // 此时改变该对象的值，可以改变原对象的值（因为两个对象引用的地址一样）
        System.out.println("revive2复活后："+h.hp);
        System.out.println("h地址："+h);
        System.out.println();
    }


    public static void main(String[] args) {
        /**
         *          什么时候行呢？
         *          父类转子类
         */
//        Hero h =new Hero();
//        ADHero ad = new ADHero();
//        h = ad;
//        ad = (ADHero) h;

        /**
         * 什么时候不行？
         * 引用对应的类和将要转换到的类是没有继承或者实现关系，则无法进行强制转换
         */
//        Hero h =new Hero();
//        ADHero ad = new ADHero();
//        Support s =new Support();
//        h = s;
//        ad = (ADHero)h;

        /**
         * 实现类转换成接口(向上转型)
         */
//        ADHero ad = new ADHero();
//
//        AD adi = ad;

        /**
         *instanceof
         *
         * 练习-类型转换
         */

        ADHero ad = new ADHero();
        Hero h = ad;
        AD adi = (AD) h; // 相当于把ADHero转换成AD【可以成功】
        APHero ap = (APHero) adi; // 【错误】
    }

}
