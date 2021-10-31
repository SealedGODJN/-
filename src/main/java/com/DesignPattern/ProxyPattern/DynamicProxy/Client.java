package com.DesignPattern.ProxyPattern.DynamicProxy;

import com.DesignPattern.ProxyPattern.StaticProxy.ILawSuit;

public class Client {
    public static void main(String[] args) {
        ILawSuit proxy = (ILawSuit) ProxyFactory.getDynProxy(new CuiHuaNiu());
        // 上面牛翠花案例中，我们使用Proxy类的newProxyInstance()方法生成的代理对象proxy去调用了proxy.submit("工资流水在此");操作，
        // 那么系统就会将此方法分发给invoke().
        // 其中proxy对象的类是系统帮我们动态生产的，其实现了我们的业务接口ILawSuit。
        proxy.submit("工资流水在此");
        proxy.defend();
    }

}
