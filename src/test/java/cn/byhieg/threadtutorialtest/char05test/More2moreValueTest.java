package cn.byhieg.threadtutorialtest.char05test;

import cn.byhieg.threadtutorial.char04.ThreadA;

import cn.byhieg.threadtutorial.char05.more2more.value.Customer;
import cn.byhieg.threadtutorial.char05.more2more.value.Producer;
import junit.framework.TestCase;
import sun.nio.cs.ext.MacThai;

/**
 * Created by byhieg on 17/2/1.
 * Mail to byhieg@gmail.com
 */
public class More2moreValueTest extends TestCase{
    public void testValue() throws Exception {
        String lock = "lock";

        Producer producer = new Producer(lock);
        Customer customer = new Customer(lock);

        Thread [] pThread = new Thread[2];
        Thread [] cThread = new Thread[2];

        for (int i = 0 ; i < 2;i++){
            pThread[i] = new Thread(()->{
                try {
                    for (int j = 0 ; j < 4;j++) {
                        producer.setValue();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            pThread[i].setName("生产者" + (i + 1));
            pThread[i].start();

            cThread[i] = new Thread(()->{
                try {
                    for (int j = 0 ; j< 4 ;j++){
                        customer.getValue();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            cThread[i].setName("消费者" + (i + 1));
            cThread[i].start();
        }

        Thread.sleep(1000);
        Thread[] threadArray = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
        Thread.currentThread().getThreadGroup().enumerate(threadArray);
        for (Thread aThreadArray : threadArray) {
            System.out.println(aThreadArray.getName() + " " + aThreadArray.getState());
        }



    }


}
