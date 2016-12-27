package cn.byhieg.threadtutorial.char01;

/**
 * Created by byhieg on 16/12/28.
 * Mail to byhieg@gmail.com
 */
public class SynchronizedObject {
    private String username = "a";
    private String password = "aa";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    synchronized public void printString(String username,String password){
        try {
            this.username = username;
            Thread.sleep(1000000);
            this.password = password;
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
