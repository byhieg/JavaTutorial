package cn.byhieg.threadtutorialtest.char01test;

import cn.byhieg.threadtutorial.char01.SleepInterruptThread;
import junit.framework.TestCase;

/**
 * Created by byhieg on 16/12/27.
 * Mail to byhieg@gmail.com
 */
public class SleepInterruptThreadTest extends TestCase {
    public void testRun() throws Exception {
        try{
            SleepInterruptThread thread = new SleepInterruptThread();
            thread.start();
            Thread.sleep(1000);
            thread.interrupt();
        }catch (InterruptedException e){
            System.out.println("main catch");
            e.printStackTrace();
        }
    }

}