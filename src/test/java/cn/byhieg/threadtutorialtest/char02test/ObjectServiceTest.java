package cn.byhieg.threadtutorialtest.char02test;

import cn.byhieg.threadtutorial.char02.DoubleSynThreadA;
import cn.byhieg.threadtutorial.char02.DoubleSynThreadB;
import cn.byhieg.threadtutorial.char02.ObjectService;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/1/3.
 * Mail to byhieg@gmail.com
 */
public class ObjectServiceTest extends TestCase {
    public void testServiceMethod() throws Exception {
        ObjectService objectService = new ObjectService();
        DoubleSynThreadA a = new DoubleSynThreadA(objectService);
        a.setName("A");
        a.start();

        DoubleSynThreadB b = new DoubleSynThreadB(objectService);
        b.setName("B");
        b.start();

        Thread.sleep(1000 * 3);
    }



}