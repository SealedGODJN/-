package com.DesignPattern.FactoryPattern;

/**
 * 优点：不直接在客户端创建具体产品的实例，降低了耦合性。
 * 缺点：每增加一种产品就要相应的增加一个工厂类，比较繁琐。
 *
 * @author hjn
 * @time 2021-11-1 17:02
 */
public class Client {
    public static void main(String[] args) {
        //生产Mac电脑
        ComputerFactory macFactory=new MacComputerFactory();
        macFactory.makeComputer().setOperationSystem();

        //生产小米电脑
        ComputerFactory miFactory=new MiComputerFactory();
        miFactory.makeComputer().setOperationSystem();
    }
}
