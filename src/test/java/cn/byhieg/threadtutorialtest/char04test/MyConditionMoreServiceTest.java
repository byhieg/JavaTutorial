package cn.byhieg.threadtutorialtest.char04test;

import cn.byhieg.threadtutorial.char04.MyConditionMoreService;
import cn.byhieg.threadtutorial.char04.ThreadA;
import cn.byhieg.threadtutorial.char04.ThreadB;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/1/26.
 * Mail to byhieg@gmail.com
 */
public class MyConditionMoreServiceTest extends TestCase {


    public void testMethod() throws Exception {
        MyConditionMoreService service = new MyConditionMoreService();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();

        ThreadA aa = new ThreadA(service);
        aa.setName("AA");
        aa.start();

        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();

        ThreadB bb = new ThreadB(service);
        bb.setName("BB");
        bb.start();

        Thread.sleep(1000 * 30);
    }

}