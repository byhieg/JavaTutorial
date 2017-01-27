package cn.byhieg.threadtutorialtest.char04test;

import cn.byhieg.threadtutorial.char04.ConditionWaitNotifyService;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/1/27.
 * Mail to byhieg@gmail.com
 */
public class ConditionWaitNotifyServiceTest extends TestCase {
    public void testAwait() throws Exception {
        ConditionWaitNotifyService service = new ConditionWaitNotifyService();
        new Thread(service::await).start();
        Thread.sleep(1000 * 3);
        service.signal();
        Thread.sleep(1000);
    }

}