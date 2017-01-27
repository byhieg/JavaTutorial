package cn.byhieg.threadtutorial.char04;

/**
 * Created by byhieg on 17/1/26.
 * Mail to byhieg@gmail.com
 */
public class ThreadA extends Thread{

    private MyConditionMoreService service;

    public ThreadA(MyConditionMoreService service){
        this.service = service;
    }

    @Override
    public void run() {
        service.methodA();
    }
}
