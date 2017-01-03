package cn.byhieg.threadtutorial.char02;

/**
 * Created by shiqifeng on 2017/1/3.
 * Mail byhieg@gmail.com
 */
public class HalfSynTask {

    public void doLongTimeTask(){
        for(int i = 0 ;i < 4;i++){
            System.out.println("nosynchronized threadName=" + Thread.currentThread().getName() + " i=" + (i + 1));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("");

        synchronized (this){
            for (int i = 0 ; i < 4;i++){
                System.out.println("synchronized threadName=" +Thread.currentThread().getName() + " i=" + (i + 1));
            }
        }

        for (int i = 1000 ; i < 1005;i ++){
            System.out.println("after synchronize =" + Thread.currentThread().getName() + " i=" + (i + 1));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
