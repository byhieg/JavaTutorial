package cn.byhieg.threadtutorial.char01;

/**
 * Created by byhieg on 16/12/27.
 * Mail to byhieg@gmail.com
 */
public class SleepInterruptThread extends Thread{

    @Override
    public void run() {
        super.run();
        try{
            System.out.println("run begin");
            Thread.sleep(20000000);
            System.out.println("run end");
        }catch (Exception e){
            System.out.println("在沉睡中被停止!进入catch!" + this.isInterrupted());

        }
    }
}
