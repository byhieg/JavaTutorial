package cn.byhieg.threadtutorial.char02;

/**
 * Created by byhieg on 17/1/5.
 * Mail to byhieg@gmail.com
 */
public class VolatileThread extends Thread{
    volatile public static int count;

    synchronized private static void addCount(){
        for (int i = 0 ; i < 100 ; i++){
            count++;
        }
        System.out.println("count=" + count);
    }

    @Override
    public void run() {
        super.run();
        addCount();
    }
}
