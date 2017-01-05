package cn.byhieg.threadtutorial.char02;

/**
 * Created by byhieg on 17/1/1.
 * Mail to byhieg@gmail.com
 */
public class PublicVar {

    public String username = "A";
    public String password = "AA";

    synchronized public void setValue(String username,String password){
        try{
            this.username = username;
            Thread.sleep(3000);
            this.password = password;
            System.out.println("setValue method thread name=" + Thread.currentThread().getName() + " username="
                                + username + " password=" + password);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

     public void getValue(){
        System.out.println("getValue method thread name=" + Thread.currentThread().getName() + " username=" + username
                            + " password=" + password);
    }
}
