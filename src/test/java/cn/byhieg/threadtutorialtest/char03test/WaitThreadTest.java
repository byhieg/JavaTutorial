package cn.byhieg.threadtutorialtest.char03test;

import cn.byhieg.threadtutorial.char03.NotifyThread;
import cn.byhieg.threadtutorial.char03.WaitThread;
import junit.framework.TestCase;

/**
 * Created by shiqifeng on 2017/1/10.
 * Mail byhieg@gmail.com
 */
public class WaitThreadTest extends TestCase {

    public void testWait()throws Exception{
        Object lock = new Object();
        new WaitThread(lock).start();
        Thread.sleep(50);
        new NotifyThread(lock).start();

        Thread.sleep(1000 * 15);
    }

}