package cn.byhieg.threadtutorial.char02;

/**
 * Created by byhieg on 17/1/1.
 * Mail to byhieg@gmail.com
 */
public class PublicVarThreadA extends Thread {

    private PublicVar publicVar;
    public PublicVarThreadA(PublicVar publicVar){
        this.publicVar = publicVar;
    }

    @Override
    public void run() {
        super.run();
        publicVar.setValue("B","BB");
    }
}
