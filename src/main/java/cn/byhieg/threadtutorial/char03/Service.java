package cn.byhieg.threadtutorial.char03;

/**
 * Created by shiqifeng on 2017/1/10.
 * Mail byhieg@gmail.com
 */
//对应3.1.4
public class Service {
    public void testMethod(Object lock){
        try{
            synchronized (lock){
                System.out.println("begin wait()");
                lock.wait();
                System.out.println(" end wait()");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void synNotifyMethod(Object lock){
        try{
            synchronized (lock){
                System.out.println("begin notify() ThreadName=" + Thread.currentThread().getName() +
                        " time=" +System.currentTimeMillis());
                lock.notify();
                Thread.sleep(1000 * 1);
                System.out.println("end notify() ThreadName=" + Thread.currentThread().getName() +
                        " time=" + System.currentTimeMillis());
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
