package cn.byhieg.threadtutorial.char02;

/**
 * Created by byhieg on 17/1/1.
 * Mail to byhieg@gmail.com
 */
public class SelfPrivateThreadA  extends Thread{
    private HasSelfPrivateNum num;


    public SelfPrivateThreadA(HasSelfPrivateNum num){
        this.num = num;
    }
    @Override
    public void run() {
        super.run();
        num.addI("a");
    }
}
