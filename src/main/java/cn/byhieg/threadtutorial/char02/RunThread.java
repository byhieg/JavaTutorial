package cn.byhieg.threadtutorial.char02;

/**
 * Created by shiqifeng on 2017/1/4.
 * Mail byhieg@gmail.com
 */
public class RunThread extends Thread{
    volatile private boolean isRunning = true;

    public boolean isRunning(){
        return isRunning;
    }

    public void setRunning(boolean isRunning){
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        System.out.println("进入run了");
        while (isRunning == true){

        }
        System.out.println("线程被停止");
    }
}
