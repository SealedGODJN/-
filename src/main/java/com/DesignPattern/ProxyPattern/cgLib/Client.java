package com.DesignPattern.ProxyPattern.cgLib;

public class Client {
    public static void main(String[] args) {
        Frank cProxy= (Frank) ProxyFactory.getGcLibDynProxy(new Frank());
        cProxy.submit("工资流水在此");
        cProxy.defend();
    }

}
