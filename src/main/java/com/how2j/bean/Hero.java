package com.how2j.bean;

import java.io.Serializable;

public class Hero {

    // 测试hero的初始化的顺序
    // 对象属性初始化有3种
    //1. 声明该属性的时候初始化
    //2. 构造方法中初始化
    //3. 初始化块

    //类属性初始化有2种
    //1. 声明该属性的时候初始化
    //2. 静态初始化块
    public String name = "some hero";
//
//    public Hero(){
//        name = "one hero";
//    }
    // 显式初始化
//    {
//        name = "the hero";
//    }
//    public String name;
    public float hp;
    public int damage;
    // 用于测试static对象会初始化几次
    // 测试结果：只会初始化一次（因为一个ClassLoader下，一种类，只会有一个类对象存在。通常一个JVM下，只会有一个ClassLoader)
    static String copyright;

    static {
        System.out.println("初始化 copyright");
        copyright = "版权由Riot Games公司所有";
    }

    public void attackhero(Hero h) {
        // 暂停时间去掉，线程会尽力去争取cpu资源
//        try{
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        h.hp -= damage;
        System.out.format("%s 正在攻击 %s， %s 的血变成了 %.0f\n", name, h.name, h.name, h.hp);

        if(h.isDead()) {
            System.out.println(h.name + "死了！");
        }
    }

    public boolean isDead() {
        return 0 >= hp;
    }

    public void recover() {
        this.hp++;
//        System.out.printf("恢复1---盖伦的血量为%.0f%n\n", this.hp);
    }

    public void hurt() {
        synchronized (this) {
            this.hp--;
        }
//        System.out.printf("减少1---盖伦的血量为%.0f%n\n", this.hp);
    }

}
