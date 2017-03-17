package cn.byhieg.designpatterntutorial.proxy.staticproxy;

/**
 * Created by shiqifeng on 2017/3/17.
 * Mail byhieg@gmail.com
 */
public class ProxySubject implements Subject{

    private Subject subject;

    public ProxySubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void visit() {
        subject.visit();
    }
}
