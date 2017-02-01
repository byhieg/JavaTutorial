package cn.byhieg.threadtutorialtest.char05test;


import cn.byhieg.threadtutorial.char05.more2one.Customer;
import cn.byhieg.threadtutorial.char05.more2one.MyQueue;
import cn.byhieg.threadtutorial.char05.more2one.Producer;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/2/1.
 * Mail to byhieg@gmail.com
 */
public class More2oneListTest extends TestCase {

    public void testList() throws Exception{
        MyQueue queue = new MyQueue();

        new Thread(new Producer(queue)).start();
        new Thread(new Producer(queue)).start();
        new Thread(new Producer(queue)).start();
        new Thread(new Producer(queue)).start();
        new Thread(new Producer(queue)).start();

        new Thread(new Customer(queue)).start();

        Thread.sleep(1000 * 3);
    }
}
