package cn.byhieg.threadtutorial.char04;

/**
 * Created by byhieg on 17/1/26.
 * Mail to byhieg@gmail.com
 */
public class ThreadB extends Thread{

    private MyConditionMoreService service;

    public ThreadB(MyConditionMoreService service){
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.methodB();
    }
}
