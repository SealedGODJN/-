package com.how2j.java.interfaceAndExtend;

public class ADAPHero extends Hero implements AD,AP{
    @Override
    public void physicAttack() {
        System.out.println("进行物理攻击");
    }

    @Override
    public void magicAttack() {
        System.out.println("进行M魔法攻击");
    }
}
