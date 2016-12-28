package cn.byhieg.threadtutorial.char01;

/**
 * Created by shiqifeng on 2016/12/28.
 * Mail byhieg@gmail.com
 */
public class ExampleDaemonThread extends Thread{

    private int i = 0;

    @Override
    public void run() {
        super.run();
        try{
            while (true){
                i++;
                System.out.println("i=" + (i));
                Thread.sleep(1000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
