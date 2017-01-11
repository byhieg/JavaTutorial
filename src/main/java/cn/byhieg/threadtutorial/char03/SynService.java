package cn.byhieg.threadtutorial.char03;

/**
 * Created by byhieg on 17/1/11.
 * Mail to byhieg@gmail.com
 */
public class SynService {

    public Object o;
    public SynService(Object o){
        this.o = o;
    }
    public void doSomething(){
        synchronized (o){
            System.out.println("开始 " + System.currentTimeMillis());
            System.out.println(Thread.currentThread().getName() + " 执行中");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("结束" + System.currentTimeMillis());
        }
    }
}
