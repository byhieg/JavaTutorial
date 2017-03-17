package cn.byhieg.designpatterntutorial.proxy.staticproxy;

/**
 * Created by shiqifeng on 2017/3/17.
 * Mail byhieg@gmail.com
 */
public class Client {

    public static void main(String[] args) {
        ProxySubject subject = new ProxySubject(new RealSubject());
        subject.visit();
    }
}
