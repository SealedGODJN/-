package com.DesignPattern.AbstractFactoryPattern;

public class AppleFactory implements AbstractFactory{
    @Override
    public Computer makeComputer() {
        return new MacComputer();
    }

    @Override
    public MobilePhoto makeMobilePhoto() {
        return new IPhoto();
    }
}
