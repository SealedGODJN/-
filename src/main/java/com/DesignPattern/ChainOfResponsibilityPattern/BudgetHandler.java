package com.DesignPattern.ChainOfResponsibilityPattern;

public interface BudgetHandler {
    void setNextHandler(BudgetHandler nextHandler);

    boolean handle(int amount);
}

