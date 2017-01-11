package cn.byhieg.threadtutorialtest.char03test;

import cn.byhieg.threadtutorial.char03.Add;
import cn.byhieg.threadtutorial.char03.AddThread;
import cn.byhieg.threadtutorial.char03.Subtract;
import cn.byhieg.threadtutorial.char03.SubtractThread;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/1/11.
 * Mail to byhieg@gmail.com
 */
public class AddSubTest extends TestCase {

    public void testRun() throws Exception{
        Object lock = new Object();
        Add add = new Add(lock);
        Subtract subtract1 = new Subtract(lock);
        Subtract subtract2 = new Subtract(lock);

        AddThread addThread = new AddThread(add);
        SubtractThread subtract1Thread = new SubtractThread(subtract1);
        SubtractThread subtract2Thread = new SubtractThread(subtract2);

        subtract1Thread.setName("sub1Thread");
        subtract1Thread.start();

        subtract2Thread.setName("sub2Thread");
        subtract2Thread.start();

        Thread.sleep(1000 * 1);
        addThread.setName("addThread");
        addThread.start();

        Thread.sleep(5000);

    }
}
