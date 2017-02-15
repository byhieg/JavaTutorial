package cn.byhieg.collectiontutorialtest;

import cn.byhieg.collectiontutorial.listtutorial.LinkedListDemo;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/2/15.
 * Mail to byhieg@gmail.com
 */
public class LinkedListDemoTest extends TestCase {

    public void testList() throws Exception {
        LinkedListDemo demo = new LinkedListDemo();
        demo.getList().add(111);
        System.out.println(demo.getList().get(0));
        demo.getList().remove(0);
    }

}