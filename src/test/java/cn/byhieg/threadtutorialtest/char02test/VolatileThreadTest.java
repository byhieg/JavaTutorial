package cn.byhieg.threadtutorialtest.char02test;

import cn.byhieg.threadtutorial.char02.VolatileThread;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/1/5.
 * Mail to byhieg@gmail.com
 */
public class VolatileThreadTest extends TestCase {
    public void testRun() throws Exception {
        VolatileThread[] threads = new VolatileThread[100];
        for (int i = 0 ; i < 100; i++){
            threads[i] = new VolatileThread();
        }
        for (int i = 0 ; i < 100 ; i++){
            threads[i].start();
        }

        Thread.sleep(1000 * 15);
    }

}