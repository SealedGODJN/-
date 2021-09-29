package com.how2j.java.interfaceAndExtend.Super;


public class Hero {

    String name; //姓名

    float hp; //血量

    float armor; //护甲

    int moveSpeed; //移动速度

    //当没有写构造方法的时候，JVM会默认的提供一个无参的构造方法
    //当写了构造方法之后，JVM将不提供无参的构造方法，需要自己写一个
    public Hero(){
        System.out.println("Hero的构造方法");
    }

    public Hero(String name, float hp){
        this.name = name;
        this.hp = hp;
        System.out.println("Hero的有两个参数的构造方法");
    }

    public static void main(String[] args) {
//        new Hero();
    }
}
