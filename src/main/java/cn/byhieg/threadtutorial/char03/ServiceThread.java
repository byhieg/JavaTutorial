package cn.byhieg.threadtutorial.char03;

/**
 * Created by shiqifeng on 2017/1/10.
 * Mail byhieg@gmail.com
 */
public class ServiceThread extends Thread{
    private Object lock;

    public ServiceThread(Object lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        super.run();
        Service service = new Service();
        service.testMethod(lock);
    }
}
