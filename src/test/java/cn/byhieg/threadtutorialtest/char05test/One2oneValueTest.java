package cn.byhieg.threadtutorialtest.char05test;

import cn.byhieg.threadtutorial.char05.one2one.value.Customer;
import cn.byhieg.threadtutorial.char05.one2one.value.Producer;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/2/1.
 * Mail to byhieg@gmail.com
 */
public class One2oneValueTest extends TestCase {

    public void testValue() throws Exception{
        String lock = "lock";
        Producer producer = new Producer(lock);
        Customer customer = new Customer(lock);

        new Thread(() -> {
            try {
                for (;;) {
                    producer.setValue();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(()->{
            try {
                for (;;) {
                    customer.getValue();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(1000 * 5);
    }

}
