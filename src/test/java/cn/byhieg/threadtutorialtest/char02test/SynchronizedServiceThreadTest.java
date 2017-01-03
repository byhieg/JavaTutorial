package cn.byhieg.threadtutorialtest.char02test;

import cn.byhieg.threadtutorial.char02.SynchronizedServiceThread;
import junit.framework.TestCase;

/**
 * Created by shiqifeng on 2017/1/3.
 * Mail byhieg@gmail.com
 */
public class SynchronizedServiceThreadTest extends TestCase {
    public void testRun() throws Exception {
        SynchronizedServiceThread thread = new SynchronizedServiceThread();
        thread.start();

        Thread.sleep(1000 * 3);
    }

}