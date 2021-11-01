package com.DesignPattern.FactoryPattern;

public class MacComputerFactory implements ComputerFactory{
    @Override
    public Computer makeComputer() {
        return new MacComputer();
    }
}
