package com.how2j.java.interfaceAndExtend.UML;

public class Fish extends Animal implements pet {
    private String name;

    public Fish() {
        super(0);
    }

    @Override
    public void eat() {
        System.out.println("🐟在吃");
    }

    @Override
    public void walk() {
        System.out.println("🐟没有脚，不能走路");
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
        System.out.println("🐟在玩");
    }
}
