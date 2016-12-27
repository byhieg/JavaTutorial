package cn.byhieg.threadtutorialtest.char01test;

import cn.byhieg.threadtutorial.char01.AliveOtherThread;
import junit.framework.TestCase;

/**
 * Created by byhieg on 16/12/27.
 * Mail to byhieg@gmail.com
 */
public class AliveOtherThreadTest extends TestCase {
    public void testRun() throws Exception {
        AliveOtherThread run = new AliveOtherThread();
        Thread thread = new Thread(run);
        System.out.println("main begin thread isAlive=" + thread.isAlive());
        thread.setName("byhieg");
        thread.start();
        System.out.println("main end thread isAlive=" + thread.isAlive());

        Thread.sleep(3000);
    }

}