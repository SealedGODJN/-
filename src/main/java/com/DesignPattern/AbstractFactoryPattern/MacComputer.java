package com.DesignPattern.AbstractFactoryPattern;

public class MacComputer extends Computer{
    @Override
    public void setOperationSystem() {
        System.out.println("Mac笔记本安装Mac系统");
    }
}
