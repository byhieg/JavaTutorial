package cn.byhieg.reflectiontutorial;

/**
 * Created by shiqifeng on 2017/1/9.
 * Mail byhieg@gmail.com
 */
public class FatherObject implements Runnable{


    public void run() {
        System.out.println("运行中......");
    }

    public void doSomething(){
        System.out.println("做事情......");
    }
}
