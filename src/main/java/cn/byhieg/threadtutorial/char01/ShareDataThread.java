package cn.byhieg.threadtutorial.char01;

/**
 * Created by shiqifeng on 2016/12/27.
 * Mail byhieg@gmail.com
 */
public class ShareDataThread extends Thread{

    private int count = 5;

    @Override
     public void run() {
        super.run();
        count--;
        System.out.println(currentThread().getName() + "count=" + count);
    }
}
