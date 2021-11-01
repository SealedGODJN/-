package com.DesignPattern.AbstractFactoryPattern;

public class XiaoMiFactory implements AbstractFactory{
    @Override
    public Computer makeComputer() {
        return new MiComputer();
    }

    @Override
    public MobilePhoto makeMobilePhoto() {
        return new MiPhoto();
    }
}
