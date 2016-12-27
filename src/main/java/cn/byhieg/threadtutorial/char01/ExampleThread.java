package cn.byhieg.threadtutorial.char01;

/**
 * Created by shiqifeng on 2016/12/27.
 * Mail byhieg@gmail.com
 */
public class ExampleThread extends Thread{

    @Override
    public void run() {
        super.run();
        System.out.println("这是一个继承自Thread的ExampleThread");
    }
}
