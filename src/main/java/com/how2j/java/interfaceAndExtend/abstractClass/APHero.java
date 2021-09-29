package com.how2j.java.interfaceAndExtend.abstractClass;

public class APHero extends Hero {
    public void magicAttack() {
        System.out.println("进行魔法攻击");
    }

    @Override
    public void attack() {
        magicAttack();
    }
}
