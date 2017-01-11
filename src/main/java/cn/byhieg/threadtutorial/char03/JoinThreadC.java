package cn.byhieg.threadtutorial.char03;

/**
 * Created by byhieg on 17/1/11.
 * Mail to byhieg@gmail.com
 */
public class JoinThreadC extends Thread{
    private JoinThreadB joinThreadB;
    public JoinThreadC(JoinThreadB joinThreadB){
        this.joinThreadB = joinThreadB;
    }

    @Override
    public void run() {
        joinThreadB.bService();
    }
}
