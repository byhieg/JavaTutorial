package cn.byhieg.designpatterntutorial.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by shiqifeng on 2017/3/17.
 * Mail byhieg@gmail.com
 */
public class DynamicProxy implements InvocationHandler {
    private Object object;


    public DynamicProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(object, args);
        return result;
    }
}
