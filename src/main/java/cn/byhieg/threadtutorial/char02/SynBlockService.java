package cn.byhieg.threadtutorial.char02;

/**
 * Created by byhieg on 17/1/3.
 * Mail to byhieg@gmail.com
 */
public class SynBlockService {

    private String usernameParam;
    private String passwrodParam;


    public void setUSernamePassword(String username,String password){
        String anyString = new String();
        try{
            synchronized (anyString){
                System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " +
                        System.currentTimeMillis() + " 进入同步块");

                usernameParam = username;
                Thread.sleep(1000 * 3);
                passwrodParam = password;
                System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " +
                        System.currentTimeMillis() + " 离开同步块");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
