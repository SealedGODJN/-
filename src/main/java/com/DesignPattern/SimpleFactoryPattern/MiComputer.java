package com.DesignPattern.SimpleFactoryPattern;

public class MiComputer extends Computer {
    @Override
    public void setOperationSystem() {
        System.out.println("小米笔记本安装Win10系统");
    }
}