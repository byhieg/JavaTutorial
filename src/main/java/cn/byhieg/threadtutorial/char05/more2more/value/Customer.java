package cn.byhieg.threadtutorial.char05.more2more.value;

/**
 * Created by byhieg on 17/2/1.
 * Mail to byhieg@gmail.com
 */
public class Customer {

    private String lock;

    public Customer(String lock) {
        this.lock = lock;
    }

    public void getValue() throws InterruptedException{
        synchronized (lock) {
            while (ValueObject.value.equals("")){
                System.out.println("消费者 "+ Thread.currentThread().getName() + " WAITING了☆");
                lock.wait();
            }
            System.out.println("消费者 " + Thread.currentThread().getName() + " RUNNING了");
            String value = ValueObject.value;
            ValueObject.value = "";
            lock.notifyAll();
        }
    }
}
