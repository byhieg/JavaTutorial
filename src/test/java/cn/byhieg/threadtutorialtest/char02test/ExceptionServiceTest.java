package cn.byhieg.threadtutorialtest.char02test;

import cn.byhieg.threadtutorial.char02.ExceptionService;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/1/5.
 * Mail to byhieg@gmail.com
 */
public class ExceptionServiceTest extends TestCase {


    public void testGetFile() throws Exception{
        ExceptionService service = new ExceptionService();

        new Thread(()->{
            service.getFile();
        }).start();
        Thread.sleep(100);
        new Thread(()->{
            service.getFile();
        }).start();

        Thread.sleep(1000 * 10);
    }

}