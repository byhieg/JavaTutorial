package cn.byhieg.threadtutorial.char02;

/**
 * Created by shiqifeng on 2017/1/3.
 * Mail byhieg@gmail.com
 */
public class SonSynService extends FatherSynService{

    synchronized public void operateISubMethod(){
        try{
            while (i > 0){
                i--;
                System.out.println("sub print i=" + i);
                Thread.sleep(1000);
                this.operateIMainMethod();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
