package cn.byhieg.threadtutorial.char01;

/**
 * Created by shiqifeng on 2016/12/27.
 * Mail byhieg@gmail.com
 */
public class ExampleCurrentThread extends Thread{

    public ExampleCurrentThread(){
        System.out.println("构造方法的打印：" + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        super.run();
        System.out.println("run方法的打印：" + Thread.currentThread().getName());
    }
}
