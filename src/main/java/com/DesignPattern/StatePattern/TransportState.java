package com.DesignPattern.StatePattern;

public class TransportState implements LogisticsState{
    @Override
    public void doAction(JdLogistics context) {
        System.out.println("商品正在运往天津分发中心");
    }
}
