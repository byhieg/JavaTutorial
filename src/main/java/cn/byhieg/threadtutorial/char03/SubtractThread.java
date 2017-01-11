package cn.byhieg.threadtutorial.char03;

/**
 * Created by byhieg on 17/1/11.
 * Mail to byhieg@gmail.com
 */
public class SubtractThread extends Thread{
    private Subtract r;

    public SubtractThread(Subtract r){
        this.r = r;
    }

    @Override
    public void run() {
        super.run();
        r.subtract();
    }
}
