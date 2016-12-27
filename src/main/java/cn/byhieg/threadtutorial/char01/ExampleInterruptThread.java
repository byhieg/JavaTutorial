package cn.byhieg.threadtutorial.char01;

/**
 * Created by byhieg on 16/12/27.
 * Mail to byhieg@gmail.com
 */
public class ExampleInterruptThread extends Thread{

    @Override
    public void run() {
        super.run();
        try{
            for(int i = 0 ; i < 50000000 ; i++){
                if (interrupted()){
                    System.out.println("已经是停止状态，我要退出了");
                    throw new InterruptedException("停止.......");
                }
                System.out.println("i=" + (i + 1));
            }
        }catch (InterruptedException e){
            System.out.println("顺利停止");
        }


    }
}
