package cn.byhieg.algorithmtutorialtest;

import cn.byhieg.algorithmtutorial.SingleLinkList;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/5/2.
 * Mail to byhieg@gmail.com
 */
public class SingleLinkListTest extends TestCase {


    SingleLinkList linkList;

    public void setUp() throws Exception {
        super.setUp();
        linkList = new SingleLinkList();
    }

    public void tearDown() throws Exception {
//        linkList.printLinkList(linkList.head);
//        System.out.println();
    }


    public void testInsertFromTail() throws Exception {
//        linkList.insertFromTail(1);
//        linkList.insertFromTail(2);
//        linkList.insertFromTail(3);
//        linkList.insertFromTail(4);
//        linkList.insertFromTail(5);
//        linkList.insertFromTail(6);
//        System.out.println("尾插入");
    }

    public void testInsertFromHead() throws Exception {
//        linkList.insertFromHead(1);
//        linkList.insertFromHead(2);
//        linkList.insertFromHead(3);
//        linkList.insertFromHead(4);
//        linkList.insertFromHead(5);
//        linkList.insertFromHead(6);
//        System.out.println("头插入");
    }
    public void testReverseLinkList() throws Exception {
        System.out.println();
        linkList.insertFromHead(1);
        linkList.insertFromHead(2);
        linkList.insertFromHead(3);
        linkList.insertFromHead(4);
        linkList.insertFromHead(5);
        linkList.insertFromHead(6);
        linkList.printLinkList(linkList.reverseLinkList());
    }

    public void testReverseLinkList2() throws Exception{
        System.out.println("递归反转链表");
        linkList.insertFromHead(1);
        linkList.insertFromHead(2);
        linkList.insertFromHead(3);
        linkList.insertFromHead(4);
        linkList.insertFromHead(5);
        linkList.insertFromHead(6);
        linkList.printLinkList(linkList.reverseLinkList(linkList.getHead()));
    }
    public void testGetHead() throws Exception {
    }

}