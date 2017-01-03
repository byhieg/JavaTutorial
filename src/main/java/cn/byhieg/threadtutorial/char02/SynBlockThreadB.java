package cn.byhieg.threadtutorial.char02;

/**
 * Created by byhieg on 17/1/3.
 * Mail to byhieg@gmail.com
 */
public class SynBlockThreadB extends Thread{

    private SynBlockService service;

    public SynBlockThreadB(SynBlockService service){
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.setUSernamePassword("b","bb");
    }
}
