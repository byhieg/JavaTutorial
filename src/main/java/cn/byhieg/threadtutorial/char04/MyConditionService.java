package cn.byhieg.threadtutorial.char04;

import java.awt.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by byhieg on 17/1/26.
 * Mail to byhieg@gmail.com
 */
public class MyConditionService {

    private Lock lock = new ReentrantLock();
    public void testMethod(){
        lock.lock();
        for (int i = 0 ;i < 5;i++){
            System.out.println("ThreadName = " + Thread.currentThread().getName() + (" " + (i + 1)));
        }
        lock.unlock();
    }
}
