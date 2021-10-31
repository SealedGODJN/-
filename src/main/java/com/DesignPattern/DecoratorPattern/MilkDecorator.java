package com.DesignPattern.DecoratorPattern;

public class MilkDecorator extends CoffeeDecorator{

    public MilkDecorator(ICoffee iCoffee) {
        super(iCoffee);
    }

    @Override
    public void makeCoffee() {
        super.makeCoffee();
        addMilk();
    }

    private void addMilk() {
        System.out.print("加奶");
    }
}
