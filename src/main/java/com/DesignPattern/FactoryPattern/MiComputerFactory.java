package com.DesignPattern.FactoryPattern;

public class MiComputerFactory implements ComputerFactory{
    @Override
    public Computer makeComputer() {
        return new MiComputer();
    }
}
