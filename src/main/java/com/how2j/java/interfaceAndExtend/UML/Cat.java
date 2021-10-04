package com.how2j.java.interfaceAndExtend.UML;

public class Cat extends Animal implements pet {
    String name;

    public Cat() {
        // super(4);
        this("");
        System.out.println("请给本喵起个名字");
    }

    public Cat(String name) {
        super(4);
        this.name = name;
    }

    @Override
    public void eat() {
        System.out.println("猫在吃");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void play() {
        System.out.println("猫在玩");
    }
}
