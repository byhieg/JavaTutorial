package cn.byhieg.threadtutorial.char02;

import com.sun.xml.internal.ws.api.pipe.SyncStartForAsyncFeature;

/**
 * Created by byhieg on 17/1/1.
 * Mail to byhieg@gmail.com
 */
public class MyObject {

    synchronized public void methodA(){
        try{
            System.out.println("begin methodA threadName=" + Thread.currentThread().getName() +
                                 " begin time =" + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("end");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    synchronized public void methodB(){
        try{
            System.out.println("begin methodB threadName=" + Thread.currentThread().getName() +
                                " begin time =" + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("end");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
