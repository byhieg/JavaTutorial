package cn.byhieg.designpatterntutorial.proxy.dynamicproxy;


/**
 * Created by shiqifeng on 2017/3/17.
 * Mail byhieg@gmail.com
 */
public class RealSubject implements Subject {

    private String name = "byhieg";
    @Override
    public void visit() {
        System.out.println(name);
    }
}
