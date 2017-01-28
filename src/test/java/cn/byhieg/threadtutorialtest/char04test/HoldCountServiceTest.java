package cn.byhieg.threadtutorialtest.char04test;

import cn.byhieg.threadtutorial.char04.HoldCountService;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/1/27.
 * Mail to byhieg@gmail.com
 */
public class HoldCountServiceTest extends TestCase {
    public void testServiceMethod1() throws Exception {

        HoldCountService service = new HoldCountService();
        service.serviceMethod1();

        Thread.sleep(1000 * 5);
    }

}