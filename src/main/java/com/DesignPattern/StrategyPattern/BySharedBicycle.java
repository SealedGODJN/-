package com.DesignPattern.StrategyPattern;

//骑共享单车算法
public class BySharedBicycle implements CalculateStrategy {
    @Override
    public int calculateTrafficFee(int distance) {
        return 2;
    }
}