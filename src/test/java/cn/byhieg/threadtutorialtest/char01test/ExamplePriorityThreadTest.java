package cn.byhieg.threadtutorialtest.char01test;

import cn.byhieg.threadtutorial.char01.ExamplePriorityThread;
import junit.framework.TestCase;

/**
 * Created by shiqifeng on 2016/12/28.
 * Mail byhieg@gmail.com
 */
public class ExamplePriorityThreadTest extends TestCase {


    public void testRun() throws Exception {
        ExamplePriorityThread threadA = new ExamplePriorityThread();
        threadA.setPriority(Thread.NORM_PRIORITY - 3 );
        threadA.start();
        ExamplePriorityThread threadB = new ExamplePriorityThread();
        threadB.setPriority(Thread.NORM_PRIORITY + 3);
        threadB.start();
        Thread.sleep(20000);
        threadA.stop();
        threadB.stop();

        System.out.println("a=" + threadA.getCount());
        System.out.println("b=" + threadB.getCount());

    }

}