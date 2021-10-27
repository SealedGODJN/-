package com.how2j.java.Generic;

import com.how2j.java.interfaceAndExtend.AP;

public class APHero extends Hero implements AP {

    @Override
    public void magicAttack() {
        System.out.println("进行魔法攻击");
    }
}
