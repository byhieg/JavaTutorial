package cn.byhieg.threadtutorialtest.char01test;

import cn.byhieg.threadtutorial.char01.ExampleInterruptThread;
import junit.framework.TestCase;

/**
 * Created by byhieg on 16/12/27.
 * Mail to byhieg@gmail.com
 */
public class ExampleInterruptThreadTest extends TestCase {
    public void testRun() throws Exception {
        ExampleInterruptThread thread = new ExampleInterruptThread();
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

}