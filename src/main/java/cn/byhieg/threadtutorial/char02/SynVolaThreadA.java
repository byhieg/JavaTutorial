package cn.byhieg.threadtutorial.char02;

/**
 * Created by byhieg on 17/1/5.
 * Mail to byhieg@gmail.com
 */
public class SynVolaThreadA extends Thread{

    private RunService service;
    public SynVolaThreadA(RunService service){
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.runMethod();
    }
}
