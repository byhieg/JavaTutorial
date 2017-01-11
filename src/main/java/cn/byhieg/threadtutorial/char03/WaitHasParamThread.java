package cn.byhieg.threadtutorial.char03;

/**
 * Created by shiqifeng on 2017/1/10.
 * Mail byhieg@gmail.com
 */
public class WaitHasParamThread {

    static private Object lock = new Object();
    static private Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            try{
                synchronized (lock){
                    System.out.println("wait begin timer=" + System.currentTimeMillis());
                    lock.wait(1000 * 5);
                    System.out.println("wait end timer=" + System.currentTimeMillis());
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    };

    static private Runnable runnable2 = () -> {
        synchronized (lock){
            System.out.println("notify begin timer=" + System.currentTimeMillis());
            lock.notify();
            System.out.println("notify end timer=" + System.currentTimeMillis());
        }
    };

    public static void main(String[] args) throws InterruptedException {
        new Thread(runnable1).start();
        Thread.sleep(1000);
        new Thread(runnable2).start();
    }
}
