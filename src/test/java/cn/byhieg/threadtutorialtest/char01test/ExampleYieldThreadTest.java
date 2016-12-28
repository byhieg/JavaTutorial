package cn.byhieg.threadtutorialtest.char01test;

import cn.byhieg.threadtutorial.char01.ExampleYieldThread;
import junit.framework.TestCase;

/**
 * Created by shiqifeng on 2016/12/28.
 * Mail byhieg@gmail.com
 */
public class ExampleYieldThreadTest extends TestCase {
    public void testRun() throws Exception {
        ExampleYieldThread thread = new ExampleYieldThread();
        thread.start();


        Thread.sleep(1000 * 5);
    }

}