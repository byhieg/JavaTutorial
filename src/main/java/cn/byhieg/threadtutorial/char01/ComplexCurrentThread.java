package cn.byhieg.threadtutorial.char01;

/**
 * Created by byhieg on 16/12/27.
 * Mail to byhieg@gmail.com
 */
public class ComplexCurrentThread extends Thread{

    public ComplexCurrentThread() {
        System.out.println("begin=========");
        System.out.println("Thread.currentThread().getName=" + Thread.currentThread().getName());

        System.out.println("this.getName()=" + this.getName());
        System.out.println("end===========");
    }

    @Override
    public void run() {
        super.run();
        System.out.println("run begin=======");
        System.out.println("Thread.currentThread().getName=" + Thread.currentThread().getName());
        System.out.println("this.getName()=" + this.getName());
        System.out.println("run end==========");
    }
}
