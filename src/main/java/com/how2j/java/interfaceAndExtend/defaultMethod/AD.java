package com.how2j.java.interfaceAndExtend.defaultMethod;

public interface AD {
    // 物理伤害
    public void physicAttack();
    default public void attack() {
        System.out.println("ad attack");
    }
}
