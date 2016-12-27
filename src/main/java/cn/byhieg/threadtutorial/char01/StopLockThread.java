package cn.byhieg.threadtutorial.char01;

/**
 * Created by byhieg on 16/12/28.
 * Mail to byhieg@gmail.com
 */
public class StopLockThread extends Thread{

    private SynchronizedObject object;

    public StopLockThread(SynchronizedObject object){
        this.object = object;
    }


    @Override
    public void run() {
        super.run();
        object.printString("b","bbb");
    }
}
