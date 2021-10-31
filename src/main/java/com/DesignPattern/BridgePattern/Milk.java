package com.DesignPattern.BridgePattern;

//加奶
public class Milk implements ICoffeeAdditives {
    @Override
    public void addSomething() {
        System.out.println("加奶");
    }
}