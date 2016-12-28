package cn.byhieg.threadtutorialtest.char01test;

import cn.byhieg.threadtutorial.char01.ComplexCurrentThread;
import junit.framework.TestCase;

/**
 * Created by byhieg on 16/12/27.
 * Mail to byhieg@gmail.com
 */
public class ComplexCurrentThreadTest extends TestCase {


    public void testRun() throws Exception {
        ComplexCurrentThread thread = new ComplexCurrentThread();
        thread.setName("byhieg");
        new Thread(thread).start();

        Thread.sleep(3000);
    }

}