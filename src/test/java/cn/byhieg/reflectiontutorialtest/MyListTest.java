package cn.byhieg.reflectiontutorialtest;

import cn.byhieg.reflectiontutorial.MyList;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/1/9.
 * Mail to byhieg@gmail.com
 */
public class MyListTest extends TestCase {

    public void testGeneric() throws Exception{
        Class clz = MyList.class;
        System.out.println(clz.getTypeParameters()[0]);
    }
}