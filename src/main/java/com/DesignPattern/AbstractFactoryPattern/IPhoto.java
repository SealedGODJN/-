package com.DesignPattern.AbstractFactoryPattern;

public class IPhoto extends MobilePhoto{
    @Override
    public void setOperationSystem() {
        System.out.println("苹果手机安装IOS系统");
    }
}
