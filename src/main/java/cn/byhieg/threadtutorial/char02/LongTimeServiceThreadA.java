package cn.byhieg.threadtutorial.char02;

/**
 * Created by shiqifeng on 2017/1/3.
 * Mail byhieg@gmail.com
 */
public class LongTimeServiceThreadA extends Thread{

    private LongTimeTask task;
    public LongTimeServiceThreadA(LongTimeTask task){
        super();
        this.task = task;
    }

    @Override
    public void run() {
        super.run();
        CommonUtils.beginTime1 = System.currentTimeMillis();
        task.doLongTimeTask();
        CommonUtils.endTime1 = System.currentTimeMillis();
    }
}
