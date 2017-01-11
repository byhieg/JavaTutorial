package cn.byhieg.threadtutorial.char03;

/**
 * Created by byhieg on 17/1/11.
 * Mail to byhieg@gmail.com
 */
public class Subtract {
    private Object lock;
    public Subtract(Object lock){
        this.lock = lock;
    }

    public void subtract(){
        try{
            synchronized (lock){
                while (MyList.list.size() == 0){
                    System.out.println("wait begin ThreadName=" + Thread.currentThread().getName());
                    lock.wait();
                    System.out.println("wait end ThreadName=" + Thread.currentThread().getName());
                }
                MyList.list.remove(0);
                System.out.println("list size=" + MyList.list.size());
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
