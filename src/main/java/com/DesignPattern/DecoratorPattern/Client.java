package com.DesignPattern.DecoratorPattern;

public class Client {
    public static void main(String[] args) {
        // 原味咖啡
        ICoffee coffee = new OriginalCoffee();
        coffee.makeCoffee();

        System.out.println();

        // 加奶的咖啡
        coffee = new MilkDecorator(new OriginalCoffee());
        coffee.makeCoffee();

        System.out.println();

        // 先加奶后加糖的咖啡
        coffee = new SugarDecorator(new MilkDecorator(new OriginalCoffee())); // 动态的给对象增减功能
        coffee.makeCoffee();

        System.out.println();

        // 优点：
        // 可以提供比继承更加灵活的方式去扩展对象的功能，通过排列组合，可以对某个类的一些对象做动态的功能扩展，而不需要装饰的对象却可以保持原样。
        // 缺点：
        // 仍然是设计模式的通用缺点：类的个数会增加，会产生很多装饰者类，相应的就增加了复杂度。

    }
}
