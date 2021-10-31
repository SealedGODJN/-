package com.DesignPattern.ProxyPattern.StaticProxy;

public class ProxyFactory {
    /**
     * 产生静态对象
     * @return 代理律师
     */
    public static ILawSuit getProxy(){
        return new ProxyLawyer(new SecondDogWang());
    }
}
