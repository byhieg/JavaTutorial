package cn.byhieg.threadtutorialtest.concurrenttest.blockingtest;

import cn.byhieg.threadtutorial.concurrent.blocking.ArrayBlock;
import cn.byhieg.threadtutorial.concurrent.blocking.Costumer;
import cn.byhieg.threadtutorial.concurrent.blocking.Producer;
import junit.framework.TestCase;

import javax.swing.*;

/**
 * Created by byhieg on 17/5/3.
 * Mail to byhieg@gmail.com
 */
public class ArrayBlockTest extends TestCase {
    ArrayBlock block;
    public void setUp() throws Exception {
        super.setUp();
        block = new ArrayBlock(2);
    }

    public void tearDown() throws Exception {
    }


    public void testBlocking() throws Exception {
        Producer producer = new Producer(block);
        Costumer costumer = new Costumer(block);
        producer.start();
        costumer.start();
        producer.join();
        costumer.join();

    }
}