package cn.byhieg.threadtutorial.char02;

/**
 * Created by byhieg on 17/1/1.
 * Mail to byhieg@gmail.com
 */
public class HasSelfPrivateNum {

    private int num = 0;

    public void addI(String username){
        try{
            if (username.equals("a")){
                num = 100;
                System.out.println("a set over!");
                Thread.sleep(2000);
            }else {
                num = 200;
                System.out.println("b set over!");
            }
            System.out.println(username + "  num=" + num);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
