package com.DesignPattern.AbstractFactoryPattern;

public class MiPhoto extends MobilePhoto{
    @Override
    public void setOperationSystem() {
        System.out.println("小米手机安装Android系统");
    }
}
