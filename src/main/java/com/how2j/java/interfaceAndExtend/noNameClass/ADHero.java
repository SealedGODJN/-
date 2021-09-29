package com.how2j.java.interfaceAndExtend.noNameClass;

public class ADHero extends Hero{
    @Override
    public void attack() {
        System.out.println(super.name + "attack");
    }
}
