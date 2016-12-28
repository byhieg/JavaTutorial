package cn.byhieg.threadtutorial.char01;

import javafx.scene.SubScene;

/**
 * Created by shiqifeng on 2016/12/28.
 * Mail byhieg@gmail.com
 */
public class ExampleYieldThread extends Thread{

    @Override
    public void run() {
        super.run();
        long beginTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 0 ; i< 500000;i++){
            Thread.yield();
            count = count + (i + 1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(" 用时：" + (endTime - beginTime) + "毫秒！");
    }
}
