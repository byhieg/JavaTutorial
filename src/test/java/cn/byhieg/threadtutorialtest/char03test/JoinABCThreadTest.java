package cn.byhieg.threadtutorialtest.char03test;

import cn.byhieg.threadtutorial.char03.JoinThreadA;
import cn.byhieg.threadtutorial.char03.JoinThreadB;
import cn.byhieg.threadtutorial.char03.JoinThreadC;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/1/11.
 * Mail to byhieg@gmail.com
 */
public class JoinABCThreadTest extends TestCase {

    public void testRun() throws Exception{
        JoinThreadB b = new JoinThreadB();

        JoinThreadA a = new JoinThreadA(b);
        a.start();

        Thread.sleep(1000);
        JoinThreadC c = new JoinThreadC(b);
        c.start();

        Thread.sleep(1000 * 10);
    }
}
