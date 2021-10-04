package com.how2j.java.interfaceAndExtend.UML;

public class Spider extends Animal {
    protected Spider(int legs) {
        super(legs);

        // 所有蜘蛛都是8条腿
        this.legs = 8;
    }

    @Override
    public void eat() {
        System.out.println("蜘蛛吃东西");
    }
}
