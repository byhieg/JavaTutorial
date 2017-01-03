package cn.byhieg.threadtutorial.char02;

/**
 * Created by byhieg on 17/1/3.
 * Mail to byhieg@gmail.com
 */
public class DoubleSynThreadA extends Thread{
    private ObjectService objectService;

    public DoubleSynThreadA(ObjectService objectService){
        this.objectService = objectService;
    }

    @Override
    public void run() {
        super.run();
        objectService.serviceMethodA();
    }
}
