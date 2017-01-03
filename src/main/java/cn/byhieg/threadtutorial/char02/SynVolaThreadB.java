package cn.byhieg.threadtutorial.char02;

/**
 * Created by byhieg on 17/1/5.
 * Mail to byhieg@gmail.com
 */
public class SynVolaThreadB extends Thread{

    private RunService service;

    public SynVolaThreadB(RunService service){
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.stopMethod();
    }
}
