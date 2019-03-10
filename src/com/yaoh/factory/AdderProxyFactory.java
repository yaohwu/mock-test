package com.yaoh.factory;

import com.yaoh.InvocationHandler.AddInvocationHandler;
import com.yaoh.operate.Add;

import java.lang.reflect.Proxy;

/**
 * @author yaoh.wu
 */
public class AdderProxyFactory {

    public static Add createAdderProxy(Add delegate) {
        return (Add) Proxy.newProxyInstance(
                delegate.getClass().getClassLoader(),
                delegate.getClass().getInterfaces(),
                new AddInvocationHandler(delegate));
    }
}
