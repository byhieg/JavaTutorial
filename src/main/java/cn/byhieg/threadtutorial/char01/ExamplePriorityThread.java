package cn.byhieg.threadtutorial.char01;

/**
 * Created by shiqifeng on 2016/12/28.
 * Mail byhieg@gmail.com
 */
public class ExamplePriorityThread extends Thread{

    private int count = 0;
    @Override
    public void run() {
        super.run();
        while (true){
            count++;
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
