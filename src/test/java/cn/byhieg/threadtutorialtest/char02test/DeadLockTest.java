package cn.byhieg.threadtutorialtest.char02test;

import cn.byhieg.threadtutorial.char02.DeadLock;
import junit.framework.TestCase;

/**
 * Created by shiqifeng on 2017/1/4.
 * Mail byhieg@gmail.com
 */
public class DeadLockTest extends TestCase {
    public void testRun() throws Exception {
        DeadLock deadLock = new DeadLock();
        deadLock.setFlag("a");

        Thread threadA = new Thread(deadLock);
        threadA.start();
        Thread.sleep(100);

        deadLock.setFlag("b");
        Thread threadB = new Thread(deadLock);
        threadB.start();

        Thread.sleep(1000 * 150);

    }

}