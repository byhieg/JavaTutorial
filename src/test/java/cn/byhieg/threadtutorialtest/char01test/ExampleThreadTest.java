package cn.byhieg.threadtutorialtest.char01test;

import cn.byhieg.threadtutorial.char01.ExampleThread;
import junit.framework.TestCase;

/**
 * Created by shiqifeng on 2016/12/27.
 * Mail byhieg@gmail.com
 */
public class ExampleThreadTest extends TestCase {

    public void testRun() throws Exception {
        ExampleThread exampleThread = new ExampleThread();
        exampleThread.start();

        Thread.sleep(1000);
    }

}