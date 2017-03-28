package cn.byhieg.designpatterntutorial.proxy.dynamicproxy;


import java.lang.reflect.Proxy;

/**
 * Created by shiqifeng on 2017/3/17.
 * Mail byhieg@gmail.com
 */
public class Client {

    public static void main(String[] args) {
        Subject realSubject = new RealSubject();
        DynamicProxy proxy = new DynamicProxy(realSubject);
        ClassLoader classLoader = realSubject.getClass().getClassLoader();
        Subject subject = (Subject) Proxy.newProxyInstance(classLoader, new Class[]{Subject.class}, proxy);
        subject.visit();
    }
}
