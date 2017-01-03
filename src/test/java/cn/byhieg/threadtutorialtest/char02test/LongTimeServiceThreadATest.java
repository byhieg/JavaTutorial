package cn.byhieg.threadtutorialtest.char02test;

import cn.byhieg.threadtutorial.char02.CommonUtils;
import cn.byhieg.threadtutorial.char02.LongTimeServiceThreadA;
import cn.byhieg.threadtutorial.char02.LongTimeServiceThreadB;
import cn.byhieg.threadtutorial.char02.LongTimeTask;
import junit.framework.TestCase;

/**
 * Created by shiqifeng on 2017/1/3.
 * Mail byhieg@gmail.com
 */
public class LongTimeServiceThreadATest extends TestCase {

    public void testRun() throws Exception {
        LongTimeTask task = new LongTimeTask();
        LongTimeServiceThreadA threadA = new LongTimeServiceThreadA(task);
        threadA.start();

        LongTimeServiceThreadB threadB = new LongTimeServiceThreadB(task);
        threadB.start();

        try{
            Thread.sleep(1000 * 10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        long beginTime = CommonUtils.beginTime1;
        if (CommonUtils.beginTime2 < CommonUtils.beginTime1){
            beginTime = CommonUtils.beginTime2;
        }

        long endTime = CommonUtils.endTime1;
        if (CommonUtils.endTime2 < CommonUtils.endTime1){
            endTime = CommonUtils.endTime2;
        }
        System.out.println("耗时:" + ((endTime - beginTime) / 1000));

        Thread.sleep(1000 * 20);
    }

}