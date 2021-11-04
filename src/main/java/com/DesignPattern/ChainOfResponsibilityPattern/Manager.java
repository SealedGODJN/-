package com.DesignPattern.ChainOfResponsibilityPattern;

import java.util.Objects;

public class Manager implements BudgetHandler {
    private BudgetHandler nextHandler = null;

    @Override
    public void setNextHandler(BudgetHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public boolean handle(int amount) {
        Objects.requireNonNull(nextHandler);
        if(amount<5000){
            System.out.println("小于2000块，我这个经理可以决定：同意！");
            return true;
        }
        System.out.println(String.format("%d超出Manager权限,请更高级管理层批复",amount));
        return nextHandler.handle(amount);
    }
}
