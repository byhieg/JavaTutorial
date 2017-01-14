package cn.byhieg.threadtutorial.char03;

/**
 * Created by byhieg on 17/1/15.
 * Mail to byhieg@gmail.com
 */
public class CommonNotify {

    private Object object;
    public CommonNotify(Object object){
        this.object = object;
    }

    public void doNotify(){
        synchronized (object){
            System.out.println("准备通知");
            object.notifyAll();
            System.out.println("通知结束");
        }
    }
}
