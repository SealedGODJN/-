package com.how2j.java.interfaceAndExtend.abstractClass;


public class ADHero extends Hero{
    public void physicAttack() {
        System.out.println("进行物理攻击");
    }

    @Override
    public void attack() {
        physicAttack();
    }
}
