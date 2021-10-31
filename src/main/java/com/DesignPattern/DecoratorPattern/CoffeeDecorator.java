package com.DesignPattern.DecoratorPattern;

public abstract class CoffeeDecorator implements ICoffee{
    ICoffee coffee; // icoffee的引用

    public CoffeeDecorator(ICoffee iCoffee) {
        this.coffee = iCoffee;
    }

    @Override
    public void makeCoffee() {
        coffee.makeCoffee(); // 调用OriginalCoffee的makeCoffee()方法
    }
}
