package com.how2j.java.interfaceAndExtend.noNameClass;

public abstract class Hero {
    String name; // 姓名
    float hp; // 血量
    float armor; // 护甲
    int moveSpeed; // 移动速度

    public abstract void attack();

    public static void main(String[] args) {
        ADHero adh = new ADHero();
        adh.attack();
        System.out.println(adh);

        Hero h = new Hero() {
            @Override
            public void attack() {
                System.out.println("新的进攻手段");
            }
        };
        h.attack();

        System.out.println(h);
    }
}
