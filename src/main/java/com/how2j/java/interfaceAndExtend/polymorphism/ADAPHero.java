package com.how2j.java.interfaceAndExtend.polymorphism;

import com.how2j.java.interfaceAndExtend.AD;
import com.how2j.java.interfaceAndExtend.AP;
import com.how2j.java.interfaceAndExtend.Hero;

public class ADAPHero extends Hero implements AD, AP, Mortal {
    @Override
    public void physicAttack() {
        System.out.println("进行物理攻击");
    }

    @Override
    public void magicAttack() {
        System.out.println("进行M魔法攻击");
    }

    @Override
    public void die() {
        System.out.println("ADAP die");
    }
}
