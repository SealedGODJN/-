package com.how2j.java.interfaceAndExtend.defaultMethod;

public class ADAPHero implements AD,AP {
    @Override
    public void physicAttack() {
        System.out.println("物理攻击");
    }

    @Override
    public void attack() {
//        AD.super.attack();
        AP.super.attack();
    }

    @Override
    public void magicAttack() {
        System.out.println("魔法攻击");
    }

    public static void main(String[] args) {
        ADAPHero adapHero = new ADAPHero();
        adapHero.attack();
    }
}
