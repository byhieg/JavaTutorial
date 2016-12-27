package cn.byhieg.threadtutorialtest.char01test;

import cn.byhieg.threadtutorial.char01.StopLockThread;
import cn.byhieg.threadtutorial.char01.SynchronizedObject;
import junit.framework.TestCase;

/**
 * Created by byhieg on 16/12/28.
 * Mail to byhieg@gmail.com
 */
public class StopLockThreadTest extends TestCase {
    public void testRun() throws Exception {

        try{
            SynchronizedObject object = new SynchronizedObject();
            StopLockThread thread = new StopLockThread(object);
            thread.start();
            Thread.sleep(500);
            thread.stop();
            System.out.println(object.getUsername() + " " + object.getPassword());

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}