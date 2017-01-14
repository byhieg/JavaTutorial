package cn.byhieg.threadtutorial.char03;

/**
 * Created by byhieg on 17/1/15.
 * Mail to byhieg@gmail.com
 */
public class CommonWait {

    private Object object;
    public CommonWait(Object object){
        this.object = object;
    }

    public void doSomething() throws Exception{
        synchronized (object){
            System.out.println("begin wait  " + Thread.currentThread().getName());
            object.wait();
            System.out.println("end wait " + Thread.currentThread().getName());
        }
    }
}
