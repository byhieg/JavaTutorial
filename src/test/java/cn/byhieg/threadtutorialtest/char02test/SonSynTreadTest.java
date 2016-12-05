package cn.byhieg.threadtutorialtest.char02test;

import cn.byhieg.threadtutorial.char02.SonSynTread;
import junit.framework.TestCase;

/**
 * Created by shiqifeng on 2017/1/3.
 * Mail byhieg@gmail.com
 */
public class SonSynTreadTest extends TestCase {
    public void testRun() throws Exception {
        SonSynTread thread = new SonSynTread();
        thread.start();

        Thread.sleep(1000 * 10);
    }
}