package com.how2j.java.interfaceAndExtend.Object;


import com.how2j.java.interfaceAndExtend.Hide.ADHero;

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

    public String toString(){
        return name;
    }

    /**
     * 由虚拟机JVM调用
     */
    protected void finalize(){
        System.out.println("这个英雄正在被回收");
    }

    public boolean equals(Object o){
        if(o instanceof Hero){
            Hero h = (Hero) o;
            return this.hp == h.hp;
        }
        return false;
    }

    public static void main(String[] args) {
        Hero h1= new Hero();
        h1.hp = 300;
        Hero h2= new Hero();
        h2.hp = 400;
        Hero h3= new Hero();
        h3.hp = 300;

        // 这不是Object的方法，但是用于判断两个对象是否相同
        // 更准确的讲，用于判断两个引用，是否指向了同一个对象
        System.out.println(h1==h2);
        System.out.println(h1==h3);

    }

    //hashCode方法返回一个对象的哈希值，但是在了解哈希值的意义之前，讲解这个方法没有意义。
    //
    //hashCode的意义，将放在hashcode 原理章节讲解


    //Object还提供线程同步相关方法
    //wait()
    //notify()
    //notifyAll()
    //这部分内容的理解需要建立在对线程安全有足够的理解的基础之上，所以会放在线程交互 的章节讲解


    //getClass()会返回一个对象的类对象，属于高级内容，不适合初学者过早接触，关于类对象的详细内容请参考反射机制



}
