package com.DesignPattern.StatePattern;

/**
 * 策略模式定义了一组可互相代替的算法，这一组算法对象完成的是同一个任务，只是使用的方式不同，例如
 * 同样是亿万富翁，马云通过卖东西实现，而王思聪通过继承实现。
 *
 * 状态模式不同的状态完成的任务完全不一样。
 */
public class StateClient {
    public void buyKeyboard() {
        JdLogistics jdLogistics = new JdLogistics();

        // 接单
        OrderState orderState = new OrderState();
        jdLogistics.setLogisticsState(orderState); // 切换状态
        jdLogistics.doAction();

        // 出库
        ProductOutState productOutState = new ProductOutState();
        jdLogistics.setLogisticsState(productOutState); // 切换状态
        jdLogistics.doAction();

        //运往天津分发中心
        TransportState transportState = new TransportState();
        jdLogistics.setLogisticsState(transportState); // 切换状态
        jdLogistics.doAction();
    }

    public static void main(String[] args) {
        StateClient stateClient = new StateClient();
        stateClient.buyKeyboard();
    }
}
