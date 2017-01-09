package cn.byhieg.reflectiontutorial;

/**
 * Created by shiqifeng on 2017/1/9.
 * Mail byhieg@gmail.com
 */
public abstract class FatherObject implements Runnable{


    protected String father = "";
    public void doSomething(){
        System.out.println("做事情......");
    }
}
