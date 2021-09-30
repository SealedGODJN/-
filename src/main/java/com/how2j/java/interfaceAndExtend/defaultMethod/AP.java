package com.how2j.java.interfaceAndExtend.defaultMethod;

public interface AP {
    // 进行魔法攻击
    public void magicAttack();

    default public void attack() {
        System.out.println("ap attack");
    }
}
