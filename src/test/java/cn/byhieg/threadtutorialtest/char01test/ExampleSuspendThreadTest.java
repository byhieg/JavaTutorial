package cn.byhieg.threadtutorialtest.char01test;

import cn.byhieg.threadtutorial.char01.ExampleSuspendThread;
import junit.framework.TestCase;

/**
 * Created by shiqifeng on 2016/12/28.
 * Mail byhieg@gmail.com
 */
public class ExampleSuspendThreadTest extends TestCase {
    public void testRun() throws Exception {
        ExampleSuspendThread thread = new ExampleSuspendThread();
        thread.start();
        Thread.sleep(5000);
        thread.suspend();
        System.out.println("A= " + System.currentTimeMillis() + " i= " + thread.getI());
        Thread.sleep(5000);
        System.out.println("A=" + System.currentTimeMillis() + " i=" +thread.getI());

        thread.resume();
        Thread.sleep(5000);

        thread.suspend();
        System.out.println("B= " + System.currentTimeMillis() + " i=" + thread.getI());
        Thread.sleep(5000);
        System.out.println("B= " + System.currentTimeMillis() + " i=" + thread.getI());
    }

}