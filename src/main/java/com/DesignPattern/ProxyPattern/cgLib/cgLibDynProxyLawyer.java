package com.DesignPattern.ProxyPattern.cgLib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class cgLibDynProxyLawyer implements MethodInterceptor {
    /**
     *
     * @param o
     * @param method
     * @param params
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
        if (method.getName().equals("submit"))
            System.out.println("案件提交成功,证据如下："+ Arrays.asList(params));
        Object result = methodProxy.invokeSuper(o, params);
        return result;
    }
}
