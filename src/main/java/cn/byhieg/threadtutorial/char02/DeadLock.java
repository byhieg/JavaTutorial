package cn.byhieg.threadtutorial.char02;

/**
 * Created by shiqifeng on 2017/1/4.
 * Mail byhieg@gmail.com
 */
public class DeadLock implements Runnable{

    public String username;
    public Object lock1 = new Object();
    public Object lock2 = new Object();

    public void setFlag(String username){
        this.username = username;
    }
    public void run() {
        if ("a".equals(username)){
            synchronized (lock1){
                try{
                    System.out.println("username = " + username);
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                synchronized (lock2){
                    System.out.println("按照lock1 - > lock2代码顺序执行");
                }
            }
        }

        if ("b".equals(username)){
            synchronized (lock2){
                try{
                    System.out.println("username = " + username);
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                synchronized (lock1){
                    System.out.println("按 lock2 - > lock1代码顺序执行");
                }
            }
        }
    }
}
