package cn.byhieg.threadtutorialtest.char03test;

import cn.byhieg.threadtutorial.char03.JoinThread;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/1/11.
 * Mail to byhieg@gmail.com
 */
public class JoinThreadTest extends TestCase {
    public void testRun() throws Exception {
        JoinThread joinThread = new JoinThread();
        joinThread.start();
        joinThread.join();
        System.out.println("我想当Join对象执行完毕后我再执行，我做到了");

    }

}