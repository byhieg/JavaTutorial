package cn.byhieg.threadtutorialtest.char02test;

import cn.byhieg.threadtutorial.char02.StaticService;
import junit.framework.TestCase;

import javax.management.relation.RoleUnresolved;

/**
 * Created by byhieg on 17/1/3.
 * Mail to byhieg@gmail.com
 */
public class StaticServiceTest extends TestCase {

    public void testPrint() throws Exception{
        new Thread(new Runnable() {
            public void run() {
                StaticService.printA();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                StaticService.printB();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                new StaticService().printC();
            }
        }).start();

        Thread.sleep(1000 * 3);
    }

}