package cn.byhieg.threadtutorial.char03;

/**
 * Created by shiqifeng on 2017/1/10.
 * Mail byhieg@gmail.com
 */
public class NotifyServiceThread extends Thread{
    private Object lock;
    public NotifyServiceThread(Object lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        super.run();
        Service service = new Service();
        service.synNotifyMethod(lock);
    }
}
