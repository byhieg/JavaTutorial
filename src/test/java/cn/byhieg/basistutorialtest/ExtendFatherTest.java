package cn.byhieg.basistutorialtest;

import cn.byhieg.basistutorial.ExtendFather;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/1/16.
 * Mail to byhieg@gmail.com
 */
public class ExtendFatherTest extends TestCase {

    public void testGetAge() throws Exception {
        System.out.println(new ExtendFather(12).getAge());
    }

}