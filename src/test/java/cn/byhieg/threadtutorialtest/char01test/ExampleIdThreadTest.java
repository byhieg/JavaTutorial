package cn.byhieg.threadtutorialtest.char01test;

import cn.byhieg.threadtutorial.char01.ExampleIdThread;
import junit.framework.TestCase;

/**
 * Created by byhieg on 16/12/27.
 * Mail to byhieg@gmail.com
 */
public class ExampleIdThreadTest extends TestCase {
    public void testRun() throws Exception {
        ExampleIdThread thread = new ExampleIdThread();
        thread.start();
        Thread.sleep(1000);
    }

}