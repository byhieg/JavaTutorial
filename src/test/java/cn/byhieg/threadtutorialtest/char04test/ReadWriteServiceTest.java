package cn.byhieg.threadtutorialtest.char04test;

import cn.byhieg.threadtutorial.char04.ReadWriteService;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/1/28.
 * Mail to byhieg@gmail.com
 */
public class ReadWriteServiceTest extends TestCase {
    public void testRead() throws Exception {

        ReadWriteService service = new ReadWriteService();
        Thread a = new Thread(service::read);
        a.setName("AA");
        a.start();
        Thread.sleep(1000);

        Thread b = new Thread(service::write);
        b.setName("BB");


        b.start();
        Thread.sleep(1000 * 30);
    }

}