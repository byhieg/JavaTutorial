package cn.byhieg.threadtutorialtest.char04test;

import cn.byhieg.threadtutorial.char04.MyConditionService;
import junit.framework.TestCase;

import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by byhieg on 17/1/26.
 * Mail to byhieg@gmail.com
 */
public class MyConditionServiceTest extends TestCase {

    public void testTestMethod() throws Exception {
        MyConditionService service = new MyConditionService();
        new Thread(service::testMethod).start();
        new Thread(service::testMethod).start();
        new Thread(service::testMethod).start();
        new Thread(service::testMethod).start();
        new Thread(service::testMethod).start();

        Lock lock = new ReentrantLock(true);
        lock.lockInterruptibly();
        Thread.sleep(1000 * 5);
    }

}