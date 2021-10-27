package com.how2j.java.Generic;

import com.how2j.java.interfaceAndExtend.AD;

public class ADHero extends Hero implements AD {
    @Override
    public void physicAttack() {
        System.out.println("进行物理攻击");
    }
}
