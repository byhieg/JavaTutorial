package cn.byhieg.collectiontutorialtest;

import cn.byhieg.collectiontutorial.listtutorial.SimpleArrayList;
import cn.byhieg.collectiontutorial.listtutorial.SimpleLinkedList;
import junit.framework.TestCase;

import java.net.SocketImpl;

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

    public void testGet() throws Exception {
        SimpleLinkedList<String> lists = new SimpleLinkedList<>();
        lists.add("byhieg");
        System.out.println(lists.get(0));
    }

    public void testIndexOf() throws Exception {
        SimpleLinkedList<Integer> lists = new SimpleLinkedList<>();
        lists.add(1);
        System.out.println(lists.indexOf(1));
    }

    public void testRemove() throws Exception {
        SimpleLinkedList<Integer> lists = new SimpleLinkedList<>();
        lists.add(111);
        lists.add(1231);
        lists.add(1513);
        for (int i = 0 ; i < lists.size();i++) {
            System.out.println(lists.get(i));
        }

        lists.remove(1);
        System.out.println(lists.get(1));
    }
}