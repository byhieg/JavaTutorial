package cn.byhieg.threadtutorialtest.char02test;

import cn.byhieg.threadtutorial.char02.PublicVar;
import cn.byhieg.threadtutorial.char02.PublicVarThreadA;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/1/1.
 * Mail to byhieg@gmail.com
 */
public class PublicVarThreadATest extends TestCase {
    public void testRun() throws Exception {
        PublicVar publicVarRef = new PublicVar();
        PublicVarThreadA threadA = new PublicVarThreadA(publicVarRef);
        threadA.start();
        Thread.sleep(40);
        publicVarRef.getValue();

        Thread.sleep(1000 * 5);
    }

}