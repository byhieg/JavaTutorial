package cn.byhieg.threadtutorialtest.char04test;

import cn.byhieg.threadtutorial.char04.MyConditionService;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/1/26.
 * Mail to byhieg@gmail.com
 */
public class MyConditionServiceTest extends TestCase {

    public void testTestMethod() throws Exception {
        MyConditionService service = new MyConditionService();
        new Thread(service::testMethod).start();
        new Thread(service::testMethod).start();
        new Thread(service::testMethod).start();
        new Thread(service::testMethod).start();
        new Thread(service::testMethod).start();

        Thread.sleep(1000 * 5);
    }

}