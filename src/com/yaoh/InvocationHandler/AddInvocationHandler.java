package com.yaoh.InvocationHandler;

import com.yaoh.operate.Add;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yaoh.wu
 */
public class AddInvocationHandler implements InvocationHandler {

    private Add delegate;

    public AddInvocationHandler(Add delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable, InvocationTargetException {
        if ("add".equals(method.getName())) {
            Integer x = (Integer) args[0];
            Integer y = (Integer) args[1];
            System.out.print("x=" + x + " y=" + y + " result=");
            Integer result = delegate.add(x, y);
            System.out.println(result);
            return result;
        }
        return method.invoke(delegate, args);
    }
}
