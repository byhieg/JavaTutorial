package cn.byhieg.threadtutorial.char03;

import com.sun.org.apache.xml.internal.res.XMLErrorResources_tr;

/**
 * Created by byhieg on 17/1/11.
 * Mail to byhieg@gmail.com
 */
public class JoinThreadB extends Thread{

    @Override
    public void run() {
        super.run();
        try{
            System.out.println(" b run begin timer=" + System.currentTimeMillis());
            Thread.sleep(1000 * 2);
            System.out.println(" b run end timer=" + System.currentTimeMillis());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    synchronized public void bService(){
        System.out.println(" 打印了bService timer= " + System.currentTimeMillis());
    }
}
