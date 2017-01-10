package cn.byhieg.threadtutorial.char03;

/**
 * Created by shiqifeng on 2017/1/10.
 * Mail byhieg@gmail.com
 */
public class NotifyThread extends Thread{
    private Object lock;
    public NotifyThread(Object lock){
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        super.run();
        synchronized (lock){
            for (int i = 0 ; i < 10;i++){
                MyList.add();
                if (MyList.size() == 5) {
                    lock.notify();
                    System.out.println("已经发出了通知");
                }
                System.out.println("添加了 " + (i + 1) + " 个");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
