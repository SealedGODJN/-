package com.DesignPattern.ProxyPattern.DynamicProxy;

import com.DesignPattern.ProxyPattern.StaticProxy.ILawSuit;
import com.DesignPattern.ProxyPattern.StaticProxy.ProxyLawyer;
import com.DesignPattern.ProxyPattern.StaticProxy.SecondDogWang;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    /**
     * 产生静态对象
     * @return 代理律师
     */
    public static ILawSuit getProxy(){
        return new ProxyLawyer(new SecondDogWang());
    }

    public static Object getDynProxy(Object target) {
        InvocationHandler handler = new DynProxyLawyer(target);
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
    }
}
