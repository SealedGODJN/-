package com.how2j.java.interfaceAndExtend.Hide;


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

    public static void battleWin() {
        System.out.println("Hero win");
    }

    public static void main(String[] args) {
        Hero.battleWin();
        ADHero.battleWin();
    }
}
