package cn.byhieg.threadtutorial.char03;

/**
 * Created by shiqifeng on 2017/1/10.
 * Mail byhieg@gmail.com
 */
public class WaitThread extends Thread{
    private Object lock;
    public WaitThread(Object lock){
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        super.run();
        try{
            synchronized (lock){
                if (MyList.size() != 5){
                    System.out.println("开始   wait time =" + System.currentTimeMillis());
                    lock.wait();
                    System.out.println("结束   wait time = " + System.currentTimeMillis());
                }

            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
