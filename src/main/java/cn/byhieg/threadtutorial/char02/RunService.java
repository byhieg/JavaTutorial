package cn.byhieg.threadtutorial.char02;

/**
 * Created by byhieg on 17/1/5.
 * Mail to byhieg@gmail.com
 */
public class RunService {

    volatile private boolean isContinueRun = true;

    public void runMethod() {

        while (isContinueRun == true) {
        }

        System.out.println("停下来了");
    }

    public void stopMethod() {
        isContinueRun = false;
    }
}
