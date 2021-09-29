package com.how2j.java.interfaceAndExtend.Final;

public class ADHero extends Hero{
    int moveSpeed = 400;

    // 子类不能重写父类中用final修饰的方法[
    @Override
    public void useItem() {
        super.useItem();
    }
}
