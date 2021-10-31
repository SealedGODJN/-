package com.DesignPattern.BridgePattern;

public class LargeCoffee extends RefinedCoffee{
    public LargeCoffee(ICoffeeAdditives additives) {
        super(additives);
    }

    @Override
    public void orderCoffee(int count) {
        System.out.println("大杯咖啡" + count + "杯");
        additives.addSomething(); // 加Milk or Sugar
    }
}
