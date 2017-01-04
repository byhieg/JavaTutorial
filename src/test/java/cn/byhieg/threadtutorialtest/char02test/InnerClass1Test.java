package cn.byhieg.threadtutorialtest.char02test;

import cn.byhieg.threadtutorial.char02.OutClass;
import junit.framework.TestCase;

/**
 * Created by shiqifeng on 2017/1/4.
 * Mail byhieg@gmail.com
 */
public class InnerClass1Test extends TestCase {
    public void testMethod() throws Exception {
        final OutClass.InnerClass1 in1 = new OutClass.InnerClass1();
        final OutClass.InnerClass2 in2 = new OutClass.InnerClass2();

        new Thread(new Runnable() {
            public void run() {
                in1.method1(in2);
            }
        },"T1").start();

        new Thread(new Runnable() {
            public void run() {
                in1.method2();
            }
        },"T2").start();

        new Thread(new Runnable() {
            public void run() {
                in2.method1();
            }
        },"T3").start();

        Thread.sleep(1000 * 10);
    }

}