package com.how2j.java.interfaceAndExtend.polymorphism;

import com.how2j.java.interfaceAndExtend.AD;
import com.how2j.java.interfaceAndExtend.Hero;

public class ADHero extends Hero implements AD, Mortal {
    @Override
    public void physicAttack() {
        System.out.println("进行物理攻击");
    }

    @Override
    public void die() {
        System.out.println("AD die");

    }
}
