package cn.byhieg.threadtutorial.char03;

/**
 * Created by byhieg on 17/1/11.
 * Mail to byhieg@gmail.com
 */
public class AddThread extends Thread{
    private Add p;
    public AddThread(Add p){
        this.p = p;
    }

    @Override
    public void run() {
        super.run();
        p.add();
    }
}
