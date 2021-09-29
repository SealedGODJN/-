package com.how2j.java.interfaceAndExtend.polymorphism;

import com.how2j.java.interfaceAndExtend.AP;
import com.how2j.java.interfaceAndExtend.Hero;

public class APHero extends Hero implements AP, Mortal {

    @Override
    public void magicAttack() {
        System.out.println("进行魔法攻击");
    }

    @Override
    public void die() {
        System.out.println("AP die");

    }
}
