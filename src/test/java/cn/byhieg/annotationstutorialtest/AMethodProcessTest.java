package cn.byhieg.annotationstutorialtest;

import cn.byhieg.annotationstutorial.AMethodProcess;
import cn.byhieg.annotationstutorial.User;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/2/14.
 * Mail to byhieg@gmail.com
 */
public class AMethodProcessTest extends TestCase {
    public void testInitMethod() throws Exception {
        AMethodProcess.initMethod(new User());
    }

}