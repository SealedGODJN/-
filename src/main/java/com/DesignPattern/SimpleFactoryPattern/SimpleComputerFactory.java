package com.DesignPattern.SimpleFactoryPattern;

/**
 * 静态工厂
 *
 * 通过一个静态工厂方法，通过传入不同的参数类型来构建不同的对象实例了，我们将对象的构建过程完全交给了工厂方法类。
 */
public class SimpleComputerFactory {
    public static Computer makeComputer(String brand) {
        Computer computer=null;
        switch (brand) {
            case "mac":
                computer=new MacComputer();
                break;
            case "mi":
                computer=new MiComputer();
                break;
            default:
                break;
        }
        return computer;
    }

    public static void main(String[] args) {
        Computer computer= SimpleComputerFactory.makeComputer("mi");
        computer.setOperationSystem();
    }
}
