package cn.byhieg.threadtutorial.char01;

/**
 * Created by shiqifeng on 2016/12/27.
 * Mail byhieg@gmail.com
 */
public class NotShareDataThread extends Thread{

    private int count = 5;


    public NotShareDataThread(String name){
        super();
        this.setName(name);
    }

    @Override
    public void run() {
        super.run();
        System.out.println("++++++++++++++++++");
        while (count > 0){
            count--;
            System.out.println(currentThread().getName() + "计算，count=" +count);
        }
    }
}
