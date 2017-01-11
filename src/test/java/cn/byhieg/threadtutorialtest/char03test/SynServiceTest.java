package cn.byhieg.threadtutorialtest.char03test;

import cn.byhieg.threadtutorial.char03.SynService;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/1/11.
 * Mail to byhieg@gmail.com
 */
public class SynServiceTest extends TestCase {

    public void testDoSomething() throws Exception {
        Object object = new Object();

        new Thread(() -> new SynService(object).doSomething()).start();
        Thread.sleep(1000);
        new Thread(() -> new SynService(object).doSomething()).start();

        Thread.sleep(1000 * 15);
    }

}