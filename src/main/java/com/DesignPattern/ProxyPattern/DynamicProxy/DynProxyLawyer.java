package com.DesignPattern.ProxyPattern.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynProxyLawyer implements InvocationHandler {
    private Object target;//被代理的对象

    public DynProxyLawyer(Object obj){
        this.target=obj;
    }

    /**
     * Jdk的动态代理实现方法是依赖于接口的，首先使用接口来定义好操作的规范。
     * 然后通过Proxy类产生的代理对象调用被代理对象的操作，而这个操作又被分发给InvocationHandler接口的 invoke方法具体执行
     *
     * @param proxy 动态代理对象
     * @param method 正在执行的方法
     * @param args 当前执行方法传入的实参
     * @return 当前执行方法的返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("案件进展："+method.getName());
        Object result=method.invoke(target,args); // 动态调用method
        return result;
    }
}

