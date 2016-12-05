package cn.byhieg.threadtutorial.char02;

import java.io.*;

/**
 * Created by byhieg on 17/1/5.
 * Mail to byhieg@gmail.com
 */
public class ExceptionService {

    synchronized public void getFile(){
        System.out.println("begin   " + System.currentTimeMillis() + "  " + Thread.currentThread().getName());
        File file = new File("111");
        try {
            Thread.sleep(1000 * 1);
            System.out.println(5 / 0);
            InputStream in = new FileInputStream(file);
            System.out.println("end" + System.currentTimeMillis() + "  " + Thread.currentThread().getName());

        } catch (FileNotFoundException | InterruptedException e) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println("end Catch" + System.currentTimeMillis() + "  " + Thread.currentThread().getName());
        }
    }
}
