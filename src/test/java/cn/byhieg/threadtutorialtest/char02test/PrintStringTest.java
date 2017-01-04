package cn.byhieg.threadtutorialtest.char02test;

import cn.byhieg.threadtutorial.char02.PrintString;
import junit.framework.TestCase;

/**
 * Created by shiqifeng on 2017/1/4.
 * Mail byhieg@gmail.com
 */
public class PrintStringTest extends TestCase {
    public void testPrintStringMethod() throws Exception {
        PrintString printStringService = new PrintString();
        new Thread(printStringService).start();
        System.out.println("我要停止他！stopThread=" + Thread.currentThread().getName());
        printStringService.setContinuePrint(false);
    }

}