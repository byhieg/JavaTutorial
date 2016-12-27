package cn.byhieg.threadtutorial.char01;

/**
 * Created by byhieg on 16/12/27.
 * Mail to byhieg@gmail.com
 */
public class SleepThread extends Thread{

    @Override
    public void run() {
        try{
            super.run();
            System.out.println("run threadName=" + currentThread().getName() + " begin");
            Thread.sleep(2000);
            System.out.println("run threadName=" + currentThread().getName() +  " end");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
