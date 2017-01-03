package cn.byhieg.threadtutorial.char02;

/**
 * Created by shiqifeng on 2017/1/3.
 * Mail byhieg@gmail.com
 */
public class SynchronizedServiceThread extends Thread{
    @Override
    public void run() {
        super.run();
        SynchronizedService service = new SynchronizedService();
        service.service1();
    }
}
