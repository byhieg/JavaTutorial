package cn.byhieg.collectiontutorialtest;

import cn.byhieg.collectiontutorial.listtutorial.SimpleArrayList;
import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by byhieg on 17/2/7.
 * Mail to byhieg@gmail.com
 */
public class SimpleArrayListTest extends TestCase {
    public void testAdd() throws Exception {
        SimpleArrayList<Integer> list = new SimpleArrayList<>();
        for (int i = 0 ; i < 20 ; i++){
            list.add(i);
        }

        list.add(5,15);

        System.out.println("aaa");
    }

}