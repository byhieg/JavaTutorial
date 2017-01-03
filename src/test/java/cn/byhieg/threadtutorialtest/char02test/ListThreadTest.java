package cn.byhieg.threadtutorialtest.char02test;

import cn.byhieg.threadtutorial.char02.ListThread;
import cn.byhieg.threadtutorial.char02.MyOneList;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/1/3.
 * Mail to byhieg@gmail.com
 */
public class ListThreadTest extends TestCase {
    public void testRun() throws Exception {
        MyOneList list = new MyOneList();
        ListThread a = new ListThread(list,"A");
        a.setName("A");
        a.start();

        ListThread b = new ListThread(list,"B");
        b.setName("B");
        b.start();

        Thread.sleep(1000 * 6);

        System.out.println("listSize=" + list.getSize());
    }

}