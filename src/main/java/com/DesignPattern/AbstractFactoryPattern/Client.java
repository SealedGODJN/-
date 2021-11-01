package com.DesignPattern.AbstractFactoryPattern;

public class Client {
    public static void main(String[] args) {
        //使用苹果工厂生产苹果公司的系列产品
        AbstractFactory appleFactory=new AppleFactory();
        appleFactory.makeComputer().setOperationSystem();
        appleFactory.makeMobilePhoto().setOperationSystem();

        //使用小米工厂生产小米公司的系列产品
        AbstractFactory miFactory=new XiaoMiFactory();
        miFactory.makeComputer().setOperationSystem();
        miFactory.makeMobilePhoto().setOperationSystem();
    }

}
