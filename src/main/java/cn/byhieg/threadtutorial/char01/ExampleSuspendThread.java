package cn.byhieg.threadtutorial.char01;

/**
 * Created by shiqifeng on 2016/12/28.
 * Mail byhieg@gmail.com
 */
public class ExampleSuspendThread extends Thread{

    private long i = 0;
    public long getI(){
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }

    @Override
    public void run() {
        super.run();
        while (true){
            i++;
        }
    }
}
