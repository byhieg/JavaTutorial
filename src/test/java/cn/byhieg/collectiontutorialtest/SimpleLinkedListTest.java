package cn.byhieg.collectiontutorialtest;

import cn.byhieg.collectiontutorial.listtutorial.SimpleArrayList;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/2/15.
 * Mail to byhieg@gmail.com
 */
public class SimpleLinkedListTest extends TestCase {
    public void testAdd() throws Exception {
        new SimpleArrayList().add(11111);
    }

    public void testAdd1() throws Exception {
        new SimpleArrayList().add(0,10);
    }

}