package cn.byhieg.threadtutorialtest.char02test;

import cn.byhieg.threadtutorial.char02.MyObject;
import cn.byhieg.threadtutorial.char02.SynchronizedMethodThread;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/1/1.
 * Mail to byhieg@gmail.com
 */
public class SynchronizedMethodThreadTest extends TestCase {
    public void testRun() throws Exception {
        MyObject object = new MyObject();
        SynchronizedMethodThread a = new SynchronizedMethodThread(object);
        a.setName("A");
        SynchronizedMethodThread b = new SynchronizedMethodThread(object);
        b.setName("B");

        a.start();
        b.start();

        Thread.sleep(1000 * 15);
    }

}