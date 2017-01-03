package cn.byhieg.threadtutorial.char02;

/**
 * Created by shiqifeng on 2017/1/3.
 * Mail byhieg@gmail.com
 */
public class FatherSynService {

    public int i = 10;
    synchronized public void operateIMainMethod(){
        try{
            i--;
            System.out.println("main print i=" +i);
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
