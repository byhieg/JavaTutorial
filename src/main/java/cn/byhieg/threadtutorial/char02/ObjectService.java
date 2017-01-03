package cn.byhieg.threadtutorial.char02;

import cn.byhieg.threadtutorial.char01.ExampleSuspendThread;

/**
 * Created by byhieg on 17/1/3.
 * Mail to byhieg@gmail.com
 */
public class ObjectService {

    public void serviceMethodA(){
        try{
            synchronized (this){
                System.out.println("A begin time=" + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("A end time =" + System.currentTimeMillis());
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void serviceMethodB(){
        synchronized (this){
            System.out.println("B begin time=" + System.currentTimeMillis());
            System.out.println("B end time=" + System.currentTimeMillis());
        }
    }


}
