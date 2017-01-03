package cn.byhieg.threadtutorial.char02;

/**
 * Created by byhieg on 17/1/3.
 * Mail to byhieg@gmail.com
 */
public class StaticService {

    synchronized public static void printA(){
        try{
            System.out.println(" 线程名称为:" + Thread.currentThread().getName()
             + " 在 " + System.currentTimeMillis() + " 进入printA");
            Thread.sleep(1000 * 3);
            System.out.println(" 线程名称为:" + Thread.currentThread().getName()
                    + " 在 " + System.currentTimeMillis() + " 离开printA");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    synchronized public static void printB(){
        System.out.println(" 线程名称为:" + Thread.currentThread().getName()
        + " 在 " + System.currentTimeMillis() +  " 进入printB");
        System.out.println(" 线程名称为:" + Thread.currentThread().getName()
                + " 在 " + System.currentTimeMillis() +  " 离开printB");
    }

    synchronized public void printC(){
        System.out.println(" 线程名称为:" + Thread.currentThread().getName()
                + " 在 " + System.currentTimeMillis() +  " 进入printC");
        System.out.println(" 线程名称为:" + Thread.currentThread().getName()
                + " 在 " + System.currentTimeMillis() +  " 离开printC");
    }
}
