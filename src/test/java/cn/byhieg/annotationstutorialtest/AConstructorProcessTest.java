package cn.byhieg.annotationstutorialtest;

import cn.byhieg.annotationstutorial.AConstructorProcess;
import cn.byhieg.annotationstutorial.User;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/2/14.
 * Mail to byhieg@gmail.com
 */
public class AConstructorProcessTest extends TestCase {
    public void testInit() throws Exception {
        User user = new User();
        AConstructorProcess.init(user);
        System.out.println(user.toString());
    }

}